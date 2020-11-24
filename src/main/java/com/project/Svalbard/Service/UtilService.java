package com.project.Svalbard.Service;

import com.project.Svalbard.Dao.DatasetRepository;
import com.project.Svalbard.Model.db.Dataset;
import com.project.Svalbard.Util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class UtilService {

    @Autowired
    private DatasetRepository datasetRepository;

    @Transactional
    public List<String> getDatasetMetrics()
    {
        Dataset d = datasetRepository.getRandomRow();
        List<String> metricList = new ArrayList<>();
        List<String> excludedList = new ArrayList<>();

        excludedList.add("fid");
        excludedList.add("id");
        excludedList.add("name");
        excludedList.add("hibernateLazyInitializer");

        Mapper.getfromObject(d).forEach((k,v) -> {
            if(!excludedList.contains(k))
            {
                metricList.add(k);
            }
        });

        return metricList;
    }
}
