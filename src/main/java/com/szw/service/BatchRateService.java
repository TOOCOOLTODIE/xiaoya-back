package com.szw.service;

import com.szw.entity.Model;
import com.szw.entity.Sort;

import java.util.List;

public interface BatchRateService {
    List<Model> getModels();
    List<Sort> getSorts();
}
