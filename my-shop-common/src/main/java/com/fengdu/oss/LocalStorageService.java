package com.fengdu.oss;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.constant.MyshopConstants;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ImageZipUtils;
import com.fengdu.utils.SpringContextUtils;
import com.fengdu.utils.property.Configuration;

public class LocalStorageService extends CloudStorageService {

	private static Logger logger = LoggerFactory
			.getLogger(LocalStorageService.class);

	private static Configuration configuration;


	static{
		configuration = (Configuration) SpringContextUtils.getBean("configuration");
		logger.info(JSONObject.toJSONString(configuration));
	}

	@Override
	public String upload(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
	
		String oldFilePath = upload(file.getInputStream(), getFullPath(prefix));
		
		String newFilePath = getZipFullPath(prefix);
		// 压缩
		ImageZipUtils.zipImag(oldFilePath, newFilePath);
		return newFilePath;
	}

	@Override
	public String upload(byte[] data, String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(InputStream inputStream, String path) {
		return SaveFileFromInputStream(inputStream, path);
	}

	/**
	 * 重新定义文件名及全路径
	 * 
	 * @param prefix
	 * @return
	 */
	private String getFullPath(String prefix) {
		String linuxPath = configuration.getProperty(MyshopConstants.LINUX_OLD_PATH,
				MyshopConstants.DEFAULT_LINUX_OLD_PATH);
		// 生成uuid
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		// 文件全路径
		String path = linuxPath + DateUtils.format(new Date(), "HHmmssS")
				+ uuid.substring(0, 5) + "." + prefix;
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			// 创建目录
			logger.info("创建目录：{}",file.getParentFile());
			file.getParentFile().mkdirs();
		}
		logger.info("上传文件路径path:{}", path);
		return path;
	}


	/**
	 * 压缩后图片路径
	 * 
	 * @param prefix
	 * @return
	 */
	private String getZipFullPath(String prefix) {
		String linuxPath = configuration.getProperty(MyshopConstants.LINUX_PATH,
				MyshopConstants.DEFAULT_LINUX_PATH);
		// 生成uuid
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		// 文件全路径
		String path = linuxPath + DateUtils.format(new Date(), "HHmmssS")
				+ uuid.substring(0, 5) + "_zip"+"." + prefix;
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			// 创建目录
			logger.info("创建目录：{}",file.getParentFile());
			file.getParentFile().mkdirs();
		}
		logger.info("上传压缩文件路径path:{}", path);
		return path;
	}
	/**
	 * 生成文件
	 * 
	 * @param stream
	 * @param path
	 * @return
	 */
	private String SaveFileFromInputStream(InputStream stream, String path) {
		String filePath = path;
		try {
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				// 创建目录
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			FileUtils.copyInputStreamToFile(stream, file);
		} catch (Exception e) {
			logger.error("上传文件失败，原因：{}", e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("io流关闭失败，原因：{}", e);
			}
		}
		return filePath;
	}
}
