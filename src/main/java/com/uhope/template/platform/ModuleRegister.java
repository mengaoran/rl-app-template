package com.uhope.template.platform;

import com.uhope.base.dto.ModuleConfig;
import com.uhope.template.utils.JsonParseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @ClassName: PlatformRegistrer
 * @Description: 提供平台回调的控制类
 * @author: yangjian
 * @date:2017年10月19日 下午1:50:16
 * @Copyright: 2017 www.uhope.com Inc. All rights reserved.
 */
@RestController
public class ModuleRegister {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleRegister.class);

	/**
	 * @Title: moduleregister
	 * @Description: 提供给OMS 平台将AppId 回写到业务，同时 返回
	 *         业务模块支持的功能和菜单列表 每个业务模块应用，都必须提供该接口
	 * @throws
	 */
	@PostMapping("/moduleregister")
	public ModuleConfig moduleregister(@PathParam(value = "moduleId") String moduleId) {

		if (StringUtils.isEmpty(moduleId)) {
			LOGGER.error("moduleregister failure!!!, the moduleId is null!");
			return null;
		}
		// 返回业务模块 提供的菜单、组件、Page等信息给 平台
		ModuleConfig config = JsonParseUtil.parseJsonConfig("static/config/config.json");

		if (config != null) {
			return config;
		} else {
			return null;
		}
	}


}