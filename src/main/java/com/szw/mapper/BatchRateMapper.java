package com.szw.mapper;

import com.szw.entity.Model;
import com.szw.entity.Sort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchRateMapper {
    List<Model> getModels();
    List<Sort> getSorts();
}
