package com.xiaoya.service.impl;

import com.xiaoya.domain.rate.entity.Model;
import com.xiaoya.domain.rate.entity.Sort;
import com.xiaoya.mapper.BatchRateMapper;
import com.xiaoya.service.BatchRateService;
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
