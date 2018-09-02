package com.fengdu.utils.property;

import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.oss.LocalStorageService;

@Service("configuration")
public class ConfigureProperties implements Configuration {
	private static Logger logger = LoggerFactory
			.getLogger(LocalStorageService.class);
	private Properties properties = new Properties();

	public ConfigureProperties() {
		try {
			this.properties.load(new InputStreamReader(
					ConfigureProperties.class.getClassLoader()
							.getResourceAsStream("configure.properties"),
					"UTF-8"));
			logger.info("加载properties文件成功，内容：{}", JSONObject.toJSON(this.properties));
		} catch (Exception e) {
			logger.error("加载properties文件失败，原因：{}", e);
		}
	}

	public String getProperty(String key) {
		logger.info("properties文件，内容：{}", JSONObject.toJSON(this.properties));
		return this.properties.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		logger.info("properties文件，内容：{}", JSONObject.toJSON(this.properties));
		String value = this.properties.getProperty(key);
		return value == null ? defaultValue : value;
	}
}
