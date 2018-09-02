package com.fengdu.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.constant.MyshopConstants;
import com.fengdu.utils.property.Configuration;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 16:17<br>
 * 描述: Base64 <br>
 */
public class ImageZipUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageZipUtils.class);
	
	private static Configuration configuration;

	static{
		configuration = (Configuration) SpringContextUtils.getBean("configuration");
		logger.info(JSONObject.toJSONString(configuration));
	}

	public static void zipImag(String oldFilePath,
			String newFilePath){
		String zipType = configuration.getProperty(MyshopConstants.ZIP_TYPE,
				MyshopConstants.DEFAULT_ZIP_TYPE);
		// 指定大小
		if(MyshopConstants.ZIP_TYPE_SIZE.equals(zipType)){
			int width = Integer.parseInt(configuration.getProperty(MyshopConstants.ZIP_SIZE_WIDTH,
					MyshopConstants.DEFAULT_ZIP_SIZE_WIDTH));
			
			int height = Integer.parseInt(configuration.getProperty(MyshopConstants.ZIP_SIZE_HEIGHT,
					MyshopConstants.DEFAULT_ZIP_SIZE_HEIGHT));
			zipBySize( width,  height,  oldFilePath, newFilePath);
		}else if(MyshopConstants.ZIP_TYPE_RATIO.equals(zipType)){
			// 按比例
			float ratio = Float.parseFloat(configuration.getProperty(MyshopConstants.ZIP_RATIO,
					MyshopConstants.DEFAULT_ZIP_RATIO));
			zipByRatio(ratio,oldFilePath, newFilePath);
		}else{
			logger.info("暂不支持其他压缩类型");
		}
	}
	/**
	 * 按照指定大小压缩
	 * 
	 * @param ratio
	 * @param oldFilePath
	 * @param newFilePath
	 */
	public static void zipByRatio(float ratio, String oldFilePath,
			String newFilePath) {
		logger.info("图片【{}】按比例【{}】压缩",oldFilePath, ratio);
		try {
			Thumbnails.of(oldFilePath).scale(1f).outputQuality(ratio)
					.toFile(newFilePath);
		} catch (IOException e) {
			logger.info("图片【{}】按比例【{}】压缩失败，原因：{}", oldFilePath, ratio, e);
		}
	}

	/**
	 * 按指定大小压缩
	 * @param width
	 * @param height
	 * @param oldFilePath
	 * @param newFilePath
	 */
	public static void zipBySize(int width, int height, String oldFilePath,
			String newFilePath) {
		logger.info("图片【{}】按指定宽【{}】高【{}】压缩",oldFilePath, width,
				height);

		try {
			Thumbnails.of(oldFilePath).size(width, height).toFile(newFilePath);
		} catch (IOException e) {
			logger.info("图片【{}】按指定宽【{}】高【{}】压缩失败，原因：{}", oldFilePath, width,
					height, e);
		}
	}
}