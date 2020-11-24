//package com.project.Svalbard.UnitTests;
//
//import com.project.Svalbard.Controller.AppController;
//import com.project.Svalbard.Model.Angular.GeneralCard;
//import com.project.Svalbard.Model.db.Classification;
//import com.project.Svalbard.Service.AppService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(AppController.class,)
//public class AppControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AppService appService;
//
//    @Test
//    public void pingTest() throws Exception
//    {
//        List<GeneralCard> li = new ArrayList<>();
//        Mockito.when(appService.getCards(10)).thenReturn(li);
//        this.mockMvc.perform(get("/home")).andExpect(status().isOk());
//    }
//}
