package com.szw.service.impl;

import com.szw.entity.Model;
import com.szw.entity.Sort;
import com.szw.mapper.BatchRateMapper;
import com.szw.service.BatchRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchRateServiceImpl implements BatchRateService {
    @Autowired
    BatchRateMapper batchRateMapper;
    @Override
    public List<Model> getModels() {
        return batchRateMapper.getModels();
    }

    @Override
    public List<Sort> getSorts() {
        return batchRateMapper.getSorts();
    }
}
