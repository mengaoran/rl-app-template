package com.uhope.template.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName: FileUtil
 * @Description:文件操作帮助类
 * @author: yangjian
 * @date:2017年10月19日 下午2:36:17
 * @Copyright: 2017 www.uhope.com Inc. All rights reserved.
 */
public class ConfigFileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFileUtil.class);

    private ConfigFileUtil() {
    }

    /**
     * @throws
     * @Title: readFile
     * @Description: 根据文件路径和编码格式, 读取文件内容字符串并返回
     * @param: @param Path
     * @param: @param encoding
     * @param: @return
     * @return: String
     */
    public static String readFile(String path, String encoding) {

        StringBuilder sb = new StringBuilder("");
        try (
                FileInputStream fileInputStream = new FileInputStream(path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
                BufferedReader reader = new BufferedReader(inputStreamReader);
        ) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }

        } catch (IOException e) {
            LOGGER.error("readFile IOException:", e);
        }
        return sb.toString();
    }

    /**
     * 返回值参考 {@link String}
     *
     * @param relativePath
     * @return String {@link String}
     * @Title: readConfigFile
     * @Description: 根据相对路径获取文件内容String, 支持两种模式下的文件读取:jar|war
     * @throw
     */
    public static String readConfigFile(String relativePath) {

        StringBuilder sb = new StringBuilder("");

        InputStream is = ConfigFileUtil.class.getClassLoader().getResourceAsStream(relativePath);
        if (is == null) {
            return sb.toString();
        }


        String tempString = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
        } catch (IOException e) {
            LOGGER.error("readConfigFile IOException:", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOGGER.error("close InputStream is IOException:", e);
            }
        }

        return sb.toString();
    }
}
