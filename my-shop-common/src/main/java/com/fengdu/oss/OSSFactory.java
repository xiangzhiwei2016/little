package com.fengdu.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.service.SysConfigService;
import com.fengdu.utils.ConfigConstant;
import com.fengdu.utils.Constant;
import com.fengdu.utils.SpringContextUtils;

/**
 * 文件上传Factory
 *
 * @author tiankong
 * @email 2366207000@qq.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {
	private static SysConfigService sysConfigService;
	private static Logger logger = LoggerFactory.getLogger(OSSFactory.class);
	static {
		OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils
				.getBean("sysConfigService");
	}

	public static CloudStorageService build() {
		// 获取云存储配置信息
		CloudStorageConfig config = sysConfigService.getConfigObject(
				ConfigConstant.CLOUD_STORAGE_CONFIG_KEY,
				CloudStorageConfig.class);
		logger.info("获取云存储配置信息：{}", JSONObject.toJSON(config));
		CloudStorageService storageService = null;
		if (null == config.getType()) {
			storageService = new LocalStorageService();
		} else if (config.getType() == Constant.CloudService.QINIU.getValue()) {
			storageService = new QiniuCloudStorageService(config);
		} else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
			storageService = new AliyunCloudStorageService(config);
		} else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
			storageService = new QcloudCloudStorageService(config);
		}
		storageService = new LocalStorageService();
		logger.info("上传文件类的选择为：{}", JSONObject.toJSON(storageService));
		return storageService;
	}

}
