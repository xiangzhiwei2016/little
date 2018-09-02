package com.fengdu.service;

import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 * 案例表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 15:44:39
 */
public interface UploadImageService {

	String upload(MultipartFile file) throws Exception;

	String upload(InputStream inputStream, String path);
}
