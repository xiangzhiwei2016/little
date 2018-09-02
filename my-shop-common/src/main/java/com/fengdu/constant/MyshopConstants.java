package com.fengdu.constant;

public class MyshopConstants {
	
	 // 过期时间为24小时
    public static final String USER_ID =  "userid";
	 // 过期时间为24小时
    public static final long EXPIRE_TIME =  24 * 60 * 60;
    
    // linux服务器地址压缩后图片地址（properties配置中的key）
    public static final String LINUX_PATH = "linux_path";
 	
 	// linux存放未压缩前的图片
    public static final String LINUX_OLD_PATH = "linux_old_path";


 	// 压缩后的路径（若没配置）
    public static final String DEFAULT_LINUX_PATH = "/home/test/upload/";
 	
 	// 压缩前的
    public static final String DEFAULT_LINUX_OLD_PATH = "/home/test/upload/old/";
    
    // 指定压缩类型#说明，0指定大小 1是比例
    public static final String ZIP_TYPE_RATIO = "1";
    
    // 指定压缩类型#说明，0指定大小 1是比例
    public static final String ZIP_TYPE_SIZE = "0";
    
    // 指定压缩类型
    public static final String ZIP_TYPE = "zip_type";
    
    // 指定压缩类型#说明，0指定大小 1是比例
    public static final String DEFAULT_ZIP_TYPE = "0";
    
    public static final String ZIP_RATIO = "zip_ratio";
    
    public static final String ZIP_SIZE_WIDTH = "zip_size_width";
    
    public static final String ZIP_SIZE_HEIGHT = "zip_size_height";
    
    // 默认比例缩放
    public static final String DEFAULT_ZIP_RATIO = "0.5";
    
    // 默认缩放宽
    public static final String DEFAULT_ZIP_SIZE_WIDTH = "200";
    
    // 默认缩放高
    public static final String DEFAULT_ZIP_SIZE_HEIGHT = "300";
}
