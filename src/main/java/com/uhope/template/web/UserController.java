package com.uhope.template.web;

import com.uhope.base.constants.Constant;
import com.uhope.base.dto.PageInfo;
import com.uhope.base.result.Result;
import com.uhope.uip.dto.UserDTO;
import com.uhope.uip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 测试封装方法
 *
 * @author a4994
 * @create 2018-09-07 14:05
 */
@RestController
@RequestMapping("/v1/userController")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public Result<PageInfo<UserDTO>> hello(@RequestParam(defaultValue = "") String var1,
                                           @RequestParam(defaultValue = "") Long var2,
                                           @RequestParam(defaultValue = "") String var3,
                                           @RequestParam(defaultValue = "") String var4,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_NUMBER) int var5,
                                           @RequestParam(defaultValue = Constant.DEFAULT_PAGE_SIZE) int var6){
        return userService.queryUserList(var1,var2,var3,var4,var5,var6);
    }
}
