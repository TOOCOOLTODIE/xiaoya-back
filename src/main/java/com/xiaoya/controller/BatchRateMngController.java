package com.xiaoya.controller;

import com.xiaoya.domain.rate.entity.Model;
import com.xiaoya.domain.rate.entity.Sort;
import com.xiaoya.service.BatchRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @descriptions: 批量书籍处理
 * @author: luoxy
 * @date: 2021/5/17
 * @version: 1.0
 */
@RestController
@RequestMapping(value="/batch")
public class BatchRateMngController {
    @Autowired
    BatchRateService batchRateService;

    @GetMapping("/getModels")
    public List<Model> getModels(){
        return batchRateService.getModels();
    }
    @GetMapping("/getSorts")
    public List<Sort> getSorts(){
        return batchRateService.getSorts();
    }

}
