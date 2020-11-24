package com.project.Svalbard.ServiceTests;

import com.project.Svalbard.Exceptions.EntryNotFoundException;
import com.project.Svalbard.Exceptions.InvalidInputException;
import com.project.Svalbard.Model.Requests.ApiAuthenticationRequest;
import com.project.Svalbard.Model.db.Classification;
import com.project.Svalbard.Service.AgentAuthService;
import com.project.Svalbard.Service.AppService;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Service Layer Tests")
public class ApplicationTests {

    @Autowired
    AppService appService;

    @Autowired
    AgentAuthService authService;

    @Test
    @DisplayName("Check if the database is returning the classification objects")
    void pingTest() {
        assertNotNull(appService.getCards(10));
    }

    @Test
    @DisplayName("Hyper parameter filter for the classification objects")
    void hpSearchTest()
    {
        HashMap<String,String> hpSettings = new HashMap<>();
        hpSettings.put("Classifier", "KNN");
        hpSettings.put("K", "11");
        hpSettings.put("Weights", "uniform");
        hpSettings.put("Algorithm", "brute");
        try {
            assertTrue(appService.hpsearch(hpSettings).get(0) instanceof Classification);
            List<Classification> classificationList = appService.hpsearch(hpSettings);
            assertTrue(classificationList != null);
            assertTrue(classificationList.stream().anyMatch(x -> x.getEid().toString().equals("40641ab2-91f5-47a9-9d54-c741fd26f23d")));
        } catch (InvalidInputException e) {
            e.printStackTrace();
        } catch (EntryNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Results with accuracy filter")
    void accfilterTest()
    {
        float cutoff = 0.9f;
        float executedValue = 0;
        try {
            executedValue = appService.accuracyfilter(cutoff).stream().min(Comparator.comparing(c -> c.getResults().getAccuracy())).get().getResults().getAccuracy();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertTrue( executedValue >= 0.9);
    }

    @Test
    @DisplayName("Results with fscore filter")
    void fscorefilterTest()
    {
        float cutoff = 0.9f;
        float executedvalue = 0;
        try {
            executedvalue = appService.fscorefilter(cutoff).stream().min(Comparator.comparing(c -> c.getResults().getF1_score())).get().getResults().getF1_score();
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        assertTrue( executedvalue >= 0.9);
    }

    @Test
    @DisplayName("FlexCards Test")
    void flexcardTest()
    {
        assertNotNull(appService.getFlexiCards(10));
    }

    @Test
    @DisplayName("Authentication of Agent's Access")
    void agentAccessTest()
    {
        ApiAuthenticationRequest request = new ApiAuthenticationRequest();
        request.setApikey("1");
        request.setPlatform("Python");
        assertTrue(authService.authenticateAgent(request));
    }

    @Test
    @DisplayName("Generate Agent's access tokens")
    void generateAccessTokenTest()
    {
        assertNotNull(authService.generateAPItoken("Python"));
        authService.clearAccessList();
    }

    @Test
    @DisplayName("Verify Agent's current access")
    void verifyAccessTest()
    {
        String token = authService.generateAPItoken("Python");
        assertTrue(authService.verifyAgentAccess("Python", token));
    }

    @Nested
    @DisplayName("Tests with unexpected/irregular inputs")
    class UnexpectedInputs{
        @Test
        @DisplayName("Empty Hyper parameter set")
        void emptyHPTest() {
            assertThrows(InvalidInputException.class, () -> appService.hpsearch(new HashMap<>()));
        }

        @Test
        @DisplayName("Unrelated Hyper parameters")
        void unrelatedHPTest()
        {
            HashMap<String,String> hpSettings = new HashMap<>();
            hpSettings.put("Classifier", "MLP");
            hpSettings.put("K", "11");
            hpSettings.put("Weights", "uniform");
            hpSettings.put("Algorithm", "brute");
            assertThrows(InvalidInputException.class, ()-> appService.hpsearch(hpSettings));
        }

        @Test
        @DisplayName("Invalid Hyper parameters")
        void invalidHPTest()
        {
            HashMap<String,String> hpSettings = new HashMap<>();
            hpSettings.put("SFASF", "MLP");
            hpSettings.put("Kqwf", "32411");
            hpSettings.put("asf", "uniadfsform");
            hpSettings.put("Algoasdfrithm", "brute");
            assertThrows(InvalidInputException.class, ()-> appService.hpsearch(hpSettings));
        }

        @Test
        @DisplayName("Invalid Classifier test")
        void invalidClfTest()
        {
            HashMap<String,String> hpSettings = new HashMap<>();
            hpSettings.put("Classifier", "QDSR");
            hpSettings.put("Kqwf", "32411");
            hpSettings.put("asf", "uniadfsform");
            hpSettings.put("Algoasdfrithm", "brute");
            assertThrows(EntryNotFoundException.class, ()-> appService.hpsearch(hpSettings));
        }

        @Test
        @DisplayName("Results with accuracy filter")
        void accfilterTest()
        {
            assertThrows(InvalidInputException.class,
                    () -> appService.accuracyfilter(-0.9f));

            assertThrows(InvalidInputException.class,
                    () -> appService.accuracyfilter(2.0f));
        }

        @Test
        @DisplayName("Results with F1 score filter")
        void f1scorefilterTest()
        {
            assertThrows(InvalidInputException.class,
                    () -> appService.fscorefilter(-0.9f));

            assertThrows(InvalidInputException.class,
                    () -> appService.fscorefilter(2.0f));
        }

        @Test
        @DisplayName("Unknown program trying to get access")
        void agentAuthenticationTest()
        {
            ApiAuthenticationRequest request = new ApiAuthenticationRequest();
            request.setApikey("1");
            request.setPlatform("Haskell");
            assertThrows(BadCredentialsException.class, () ->authService.authenticateAgent(request));
        }

        @Test
        @DisplayName("Non Authenticated Program getting verified in the list")
        void agentAccessTest()
        {
            assertFalse(authService.verifyAgentAccess("Python", "1"));
        }
    }

}
