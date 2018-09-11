package com.fengdu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取经纬度
 * 
 */
public class GetLatAndLngByBaiduUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(GetLatAndLngByBaiduUtils.class);

	private static final String AK = "ICtHAcsMhciEVa3vhYirZV9GgBdySEwH";

	private static final String TXAK = "RBUBZ-4B2CI-K2WG2-5MBBV-VROP7-LRF4X";

	private static final String LAR_LNG_TX = "https://apis.map.qq.com/ws/geocoder/v1/?key="
			+ TXAK + "&address=";

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
				System.out.println("百度经纬度lng：" + lng);
				System.out.println("百度经纬度lat：" + lat);
				// Map<String,Double> res =
				// convert(Double.valueOf(lng),Double.valueOf(lat));
				// if(null != res && !res.isEmpty()){
				// result.put("lng",
				// new BigDecimal(lng).setScale(6, BigDecimal.ROUND_HALF_UP));
				// result.put("lat",
				// new BigDecimal(lat).setScale(6, BigDecimal.ROUND_HALF_UP));
				// }
			}
		} catch (Exception e) {
			logger.error("获取{}的经纬度失败，原因：{}", address, e);
		}
		result.put("lng",
				new BigDecimal(lng).setScale(2, BigDecimal.ROUND_HALF_UP));
		result.put("lat",
				new BigDecimal(lat).setScale(2, BigDecimal.ROUND_HALF_UP));
		return result.toJSONString();
	}

	public static Map<String, Double> convert(double lng, double lat) {
		Map<String, Double> result = new HashMap<String, Double>();
		double x_pi = Math.PI * 3000.0 / 180.0;
		double x = lng - 0.0065, y = lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		lng = z * Math.cos(theta);
		lat = z * Math.sin(theta);
		result.put("lng", lng);
		result.put("lat", lat);
		System.out.println("腾讯lng:" + lng);
		System.out.println("腾讯lat:" + lat);
		return result;
	}

	public static void translate(String locations) throws Exception {
		String url = "https://apis.map.qq.com/ws/coord/v1/translate?locations="
				+ locations + "&type=3&key=RBUBZ-4B2CI-K2WG2-5MBBV-VROP7-LRF4X";
		System.out.println(url);
		String result = sendGetRequest(url);
		System.out.println(result);
	}

	public static String getLarLngByTx(String address) {
		String url;
		try {
			url = LAR_LNG_TX + URLEncoder.encode(address, "utf-8");

			String result = HttpUtil.URLGet(url, null, null);
			logger.info("调用腾讯地图返回：" + result);
			Map<String, Object> resultMap = JSONObject.parseObject(result,
					Map.class);
			System.out.println(resultMap);
			if (null != resultMap && !resultMap.isEmpty()) {
				if ("0".equals(resultMap.get("status").toString())) {
					Object res = resultMap.get("result");
					Object rs = JSONObject.parseObject(res.toString(), Map.class).get(
							"location");
					return JSONObject.toJSONString(rs);
				} else {
					logger.error("调用腾讯地图失败：" + resultMap.get("message"));
					throw new Exception("调用腾讯地图失败：" + resultMap.get("message"));
				}
			}
		} catch (Exception e) {
			logger.error("调用腾讯地图失败：", e);
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		String address = "上海东方明珠广播电视塔";
		String result = getLarLngByTx(address);
		System.out.println(result);
		JSONObject object = JSONObject.parseObject(result);
		System.out.println(object.getString("lng"));
		System.out.println(object.getString("lat"));
//		String res = getLarLng("东方明珠广播电视塔");
//		System.out.println(res);
//		Map<String, BigDecimal> map = JSONObject.parseObject(res, Map.class);
//		String locations = map.get("lat").toPlainString().concat(",")
//				.concat(map.get("lng").toPlainString());
//		translate(locations);
	}

	/**
	 * 发送http get请求
	 * 
	 * @param getUrl
	 * @return
	 * @throws IOException
	 */
	public static String sendGetRequest(String getUrl) throws IOException {
		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			URL url = new URL(getUrl);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setAllowUserInteraction(false);
			isr = new InputStreamReader(url.openStream());
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
			isr.close();

		}
		System.out.println(sb.toString());
		return sb.toString();
	}

}