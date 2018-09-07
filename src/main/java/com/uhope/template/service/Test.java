package com.uhope.template.service;

import com.github.pagehelper.PageInfo;
import com.uhope.base.constants.Constant;
import com.uhope.base.result.Result;
import com.uhope.template.domain.Template;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${uip.base.service.name:RL-TEMPLATE-SERVICE-TEST-SHUJIHUI}",path="/template/v1/template")
public interface Test {
    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<Template>> list(@RequestParam(name = "pageNumber",required = false) Integer pageNumber,
                                           @RequestParam(name = "pageSize",required = false) Integer pageSize);

}
