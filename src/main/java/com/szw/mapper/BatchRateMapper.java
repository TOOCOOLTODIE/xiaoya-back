package com.szw.mapper;

import com.szw.entity.Model;
import com.szw.entity.Sort;

import java.util.List;

public interface BatchRateMapper {
    List<Model> getModels();
    List<Sort> getSorts();
}
