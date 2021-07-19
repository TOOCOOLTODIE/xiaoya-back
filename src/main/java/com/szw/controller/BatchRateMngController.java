package com.szw.controller;

import com.szw.entity.Model;
import com.szw.entity.Sort;
import com.szw.service.BatchRateService;
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
