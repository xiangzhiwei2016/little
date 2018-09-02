package com.fengdu.utils;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
  
/** 
* 获取经纬度
* 
*/
public class GetLatAndLngByBaiduUtils {
	private static final Logger logger = LoggerFactory.getLogger(GetLatAndLngByBaiduUtils.class);

	private static final String AK = "ICtHAcsMhciEVa3vhYirZV9GgBdySEwH";

	public static String getLarLng(String address) {
		JSONObject result = new JSONObject();
		// 经度
		Double lng = 116.404;
		// 纬度
		Double lat = 39.915;
		try {
			String addressUrl = "http://api.map.baidu.com/geocoder/v2/?address="
					+ address
					+ "&output=json&ak="
					+ AK
					+ "&callback=showLocation";

			URL url = new URL(addressUrl);

			InputStream inputStream = url.openStream();

			String string = IOUtils.toString(inputStream, "gbk");

			int len = string.length();

			String substring = string.substring(27, len - 1);

			JSONObject jsonObject = JSONObject.parseObject(substring);

			String status = jsonObject.getString("status");

			if (status.equals("0")) {

				lng = jsonObject.getJSONObject("result")
						.getJSONObject("location").getDouble("lng");

				lat = jsonObject.getJSONObject("result")
						.getJSONObject("location").getDouble("lat");
			}
		} catch (Exception e) {
			logger.error("获取{}的经纬度失败，原因：{}", address, e);
		}
		result.put("lng",
				new BigDecimal(lng).setScale(6, BigDecimal.ROUND_HALF_UP));
		result.put("lat",
				new BigDecimal(lat).setScale(6, BigDecimal.ROUND_HALF_UP));
		return result.toJSONString();
	}

}