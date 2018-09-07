package com.uhope.template.service.impl;

import com.uhope.template.mapper.TemplateMapper;
import com.uhope.template.domain.Template;
import com.uhope.template.service.TemplateService;
import com.uhope.template.dto.TemplateDTO;
import com.uhope.core.AbstractService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 模版表-ServiceImpl接口实现类
 *
 * @author ChenBin on 2018/09/04
 */
@Service
public class TemplateServiceImpl extends AbstractService<Template, TemplateDTO, String> implements TemplateService {
    @Resource
    private TemplateMapper templateMapper;

}
