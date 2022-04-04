package com.xiaoya.mapper;

import com.xiaoya.domain.rate.entity.Model;
import com.xiaoya.domain.rate.entity.Sort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BatchRateMapper {
    List<Model> getModels();
    List<Sort> getSorts();
}
