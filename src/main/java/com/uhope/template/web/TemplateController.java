package com.uhope.template.web;

import com.uhope.base.constants.Constant;
import com.uhope.template.domain.Template;
import com.uhope.template.service.TemplateService;
import com.uhope.base.result.ResponseMsgUtil;
import com.uhope.base.result.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uhope.template.service.Test;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 模版表-Controller类
 *
 * @author ChenBin on 2018/09/04
 */
@RestController
@RequestMapping("/v1/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private Test test;

    @GetMapping("/hello")
    public Result<PageInfo<Template>> hello(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                            @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize){
        return test.list(pageNumber,pageSize);
    }

    @PostMapping("/add")
    public Result<Template> add(@RequestParam String name,
                                String version) {
        Template template = new Template();
        template.setName(name);
        template.setVersion(version);
        templateService.insert(template);
        return ResponseMsgUtil.success(template);
    }


    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        templateService.remove(id);
        return ResponseMsgUtil.success();
    }


    @PutMapping("/update")
    public Result<Template> update(@RequestParam String id,
                                   String name,
                                   String version) {

        Template template = new Template();
        template.setId(id);
        template.setName(name);
        template.setVersion(version);
        templateService.update(template);
        return ResponseMsgUtil.success(template);
    }


    @GetMapping("/detail")
    public Result<Template> detail(@RequestParam String id) {
        Template template = templateService.get(id);
        return ResponseMsgUtil.success(template);
    }

    /**
     * 带分页的查询,根据条件获取菜单列表,可分页，默认查询第一页,一次" + DEFAULT_PAGE_SIZE + "条记录
     *
     * @param pageNumber 默认第{@link com.uhope.base.constants.Constant#DEFAULT_PAGE_NUMBER}页，如果小于1，则查出所有的记录
     * @param pageSize   分页大小，如果小于1，则查出所有的记录,默认{@link com.uhope.base.constants.Constant#DEFAULT_PAGE_SIZE}条
     * @return
     */
    @GetMapping("/list")
    public Result<PageInfo<Template>> list(@RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) Integer pageNumber,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Template> list = templateService.find();
        PageInfo<Template> pageInfo = new PageInfo(list);
        return ResponseMsgUtil.success(pageInfo);
    }
}
