package com.xiaoya.service;

import com.xiaoya.domain.rate.entity.Model;
import com.xiaoya.domain.rate.entity.Sort;

import java.util.List;

public interface BatchRateService {
    List<Model> getModels();
    List<Sort> getSorts();
}
