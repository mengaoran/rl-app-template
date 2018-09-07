package com.uhope.template.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uhope.base.dto.ModuleConfig;
import com.uhope.base.dto.ModuleFunction;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: JsonParseUtil
 * @Description: JSON数据解析帮助类
 * @author: yangjian
 * @date:2018年2月28日 上午11:17:29
 * @Copyright: 2018 www.uhope.com Inc. All rights reserved.
 */
public class JsonParseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParseUtil.class);
    private static final String DESCRIPTION = "description";
    private static final String SEQ_ID = "seqId";
    private static final String REQUIRE_JS = "requireJS";

    private JsonParseUtil() {
    }

    public static ModuleConfig parseJsonConfig(String path) {

        //path "config/config.json"
        String jsonConfig = ConfigFileUtil.readConfigFile(path);
        if (StringUtils.isEmpty(jsonConfig)) {
            LOGGER.error("read json config failure.");
            return null;
        }

        // 将json字符串转换为json对象
        JSONObject jsonObj = JSON.parseObject(jsonConfig);

        ModuleConfig moduleConfig = new ModuleConfig();

        moduleConfig.setId(jsonObj.getString("id"));
        moduleConfig.setModuleName(jsonObj.getString("moduleName"));
        moduleConfig.setVersion(jsonObj.getString("version"));
        moduleConfig.setDescription(jsonObj.getString(DESCRIPTION));

        //设置菜单列表
        List<ModuleFunction> menuList = new ArrayList<>();
        JSONArray menusArray = jsonObj.getJSONArray("menus");

        //获取菜单列表
        for (int i = 0; i < menusArray.size(); i++) {
            JSONObject menuObj = menusArray.getJSONObject(i);

            ModuleFunction menu = new ModuleFunction();

            menu.setSeqId(menuObj.getString(SEQ_ID));
            menu.setParentId(menuObj.getString("parentId"));
            menu.setIsLeaf(menuObj.getBoolean("isLeaf"));
            menu.setIsRoot(menuObj.getBoolean("isRoot"));
            menu.setName(menuObj.getString("name"));
            menu.setIcon(menuObj.getString("icon"));
            menu.setUrl(menuObj.getString("url"));
            menu.setMenuType(menuObj.getInteger("menuType"));
            List<String> requireJSList = new ArrayList<>();
            JSONArray requireJS = menuObj.getJSONArray(REQUIRE_JS);
            if (requireJS != null && !requireJS.isEmpty()) {
                for (int j = 0; j < requireJS.size(); j++) {
                    requireJSList.add(requireJS.getString(j));
                }
            }
            menu.setRequireJS(requireJSList);
            menu.setDescription(menuObj.getString(DESCRIPTION));
            menuList.add(menu);
        }
        moduleConfig.setMenus(menuList);

        //设置组件列表
        JSONArray components = jsonObj.getJSONArray("components");
        List<ModuleFunction> comptList = getModuleFunctions(components);
        moduleConfig.setComponents(comptList);

        //设置页面列表
        JSONArray pages = jsonObj.getJSONArray("pages");

        //获取page列表
        List<ModuleFunction> pageList = getModuleFunctions(pages);
        moduleConfig.setPages(pageList);

        return moduleConfig;

    }

    /**
     * 获取组件列表
     *
     * @param components
     * @return
     */
    private static List<ModuleFunction> getModuleFunctions(JSONArray components) {
        List<ModuleFunction> comptList = new ArrayList<>();
        //获取组件列表
        for (int i = 0; i < components.size(); i++) {
            JSONObject componentObj = components.getJSONObject(i);

            ModuleFunction compt = new ModuleFunction();
            //组件列表当前只涉及这几个字段，后续看情况调整补齐
            compt.setSeqId(componentObj.getString(SEQ_ID));

            compt.setName(componentObj.getString("name"));

            compt.setUrl(componentObj.getString("url"));
            List<String> requireJSList = new ArrayList<>();
            JSONArray requireJS = componentObj.getJSONArray(REQUIRE_JS);
            if (requireJS != null && !requireJS.isEmpty()) {
                for (int j = 0; j < requireJS.size(); j++) {
                    requireJSList.add(requireJS.getString(j));
                }
            }
            compt.setRequireJS(requireJSList);
            compt.setDescription(componentObj.getString(DESCRIPTION));
            comptList.add(compt);
        }
        return comptList;
    }


}
