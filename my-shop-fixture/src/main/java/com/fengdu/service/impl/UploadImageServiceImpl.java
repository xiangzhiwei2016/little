package com.fengdu.service.impl;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import com.fengdu.constants.Constants;
import com.fengdu.oss.LocalStorageService;
import com.fengdu.service.UploadImageService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.property.Configuration;
/**
 * 
 * @author xiangzhiwei
 *
 */
@Service("uploadImageService")
public class UploadImageServiceImpl implements UploadImageService {

	private static Logger logger = LoggerFactory
			.getLogger(LocalStorageService.class);

	@Autowired
	private Configuration configuration;

	@Override
	public String upload(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return upload(file.getInputStream(), getFullPath(prefix));
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
		String linuxPath = configuration.getProperty(Constants.LINUX_PATH,
				Constants.DEFAULT_LINUX_PATH);
		// 生成uuid
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		// 文件全路径
		String path = linuxPath + DateUtils.format(new Date(), "HHmmssS")
				+ uuid.substring(0, 5) + "." + prefix;
		logger.info("上传文件路径path:{}", path);
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
