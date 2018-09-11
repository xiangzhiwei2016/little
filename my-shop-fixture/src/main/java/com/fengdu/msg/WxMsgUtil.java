package com.fengdu.msg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.utils.HttpUtil;

public class WxMsgUtil {
	private Logger logger = LoggerFactory.getLogger(WxMsgUtil.class);

	// 微信发送地址
	private static final String WX_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

	// 获取所有模板
	private static final String WX_MSG_TEMP_URL = "https://api.weixin.qq.com/cgi-bin/wxopen/template/list?access_token=";
	// 模板id
	private static final String TEMP_ID = "9NVj45hFEA2B0TQK-03OvqQL9vMZjL0UjyyJYDXqKEU";
	// 发送模板消息
	private static final String SEND_WX_MSG_TEMP = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
	// 统一服务消息
	private static final String uniform= "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=";

	/**
	 * 
	 * 微信公共账号发送给账号
	 * 
	 * @param content
	 *            文本内容
	 * 
	 * @param toUser
	 *            微信用户
	 * 
	 * @return
	 */

	public void sendTextMessageToUser(String accessToken, String content,
			String toUser) {
		// 参数
		String json = "{\"touser\": \"" + toUser
				+ "\",\"msgtype\": \"text\", \"text\": {\"content\": \""
				+ content + "\"}}";

		// 获取请求路径
		String action = WX_MSG_URL + accessToken;

		logger.info("发送微信消息参数：{}", json);

		try {
			sendHttpPost(action, json);
		} catch (Exception e) {
			logger.error("发送微信消息失败", e);
		}
	}

	public static void getAllTemps(String accessToken) throws IOException {
		String url = WX_MSG_TEMP_URL + accessToken;
		JSONObject obj = new JSONObject();
		obj.put("offset", 0);
		obj.put("count", 20);

		String result = new WxMsgUtil().sendHttpPost(url, obj.toJSONString());
		System.out.println(result);
	}

	// 发送模板消息
	public void sendTemplateMsg(String accessToken, TempMsgParams param) {
		param = new TempMsgParams();
		param.setTouser("oOYnb4rO45RGvt_mgkGgQjIb3wLE");
		param.setTemplate_id(TEMP_ID);
		param.setForm_id("sdfs");
		Content data = new Content();
		KeyValue keyword1 = new KeyValue();
		keyword1.setValue("hello,word");
		data.setKeyword1(keyword1);
		param.setData(data);
		System.out.println("发送模板消息，请求参数：" + JSONObject.toJSONString(param));
		String url = SEND_WX_MSG_TEMP + accessToken;
		String result = new WxMsgUtil().sendHttpPost(url,
				JSONObject.toJSONString(param));
		System.out.println(result);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String sendHttpPost(String url, String body) {
		String responseContent = null;
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;

		try {
			httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(body));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				responseContent = EntityUtils.toString(entity, "UTF-8");
				logger.info("响应返回数据：{}", responseContent);
				System.out.println(responseContent);
			} else {
				logger.error("响应状态码:{} ", statusCode);
			}
		} catch (Exception e) {
			logger.error("post请求失败，原因:{} ", e);
		} finally {
			try {
				if (null != response) {
					response.close();
				}
				if (null != httpClient) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error("关闭流失败，原因:{} ", e);
			}
		}
		return responseContent;
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

	class TempMsgParams {
		private String touser;
		private String template_id;
		private String form_id;
		private Content data;

		public String getTouser() {
			return touser;
		}

		public void setTouser(String touser) {
			this.touser = touser;
		}

		public String getTemplate_id() {
			return template_id;
		}

		public void setTemplate_id(String template_id) {
			this.template_id = template_id;
		}

		public String getForm_id() {
			return form_id;
		}

		public void setForm_id(String form_id) {
			this.form_id = form_id;
		}

		public Content getData() {
			return data;
		}

		public void setData(Content data) {
			this.data = data;
		}
	}

	// msg模板内容
	class Content {
		private KeyValue keyword1;
		private KeyValue keyword2;
		private KeyValue keyword3;
		private KeyValue keyword4;
		private KeyValue keyword5;

		public KeyValue getKeyword1() {
			return keyword1;
		}

		public void setKeyword1(KeyValue keyword1) {
			this.keyword1 = keyword1;
		}

		public KeyValue getKeyword2() {
			return keyword2;
		}

		public void setKeyword2(KeyValue keyword2) {
			this.keyword2 = keyword2;
		}

		public KeyValue getKeyword3() {
			return keyword3;
		}

		public void setKeyword3(KeyValue keyword3) {
			this.keyword3 = keyword3;
		}

		public KeyValue getKeyword4() {
			return keyword4;
		}

		public void setKeyword4(KeyValue keyword4) {
			this.keyword4 = keyword4;
		}

		public KeyValue getKeyword5() {
			return keyword5;
		}

		public void setKeyword5(KeyValue keyword5) {
			this.keyword5 = keyword5;
		}

	}

	class KeyValue {
		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		String access_token = "13_uz-BXs09ijMW-yWyi3a9SPWswGt_cfD6zbPIIPS5xPeAchJNs-O2SvebLIL9A1Lt5CUKqkxnZ_AOo55XquagZqHiPzpqhnoBYrxuHxjvZuhTRmT3v6B30nZn7p8a6gD3qvkh1xuCvEPSCCl_DSOdAHAWLH";
		String content = "hello,world";
		String toUser = "oOYnb4rO45RGvt_mgkGgQjIb3wLE";
		// new WxMsgUtil().sendTextMessageToUser(access_token, content, toUser);
		// getAllTemps(access_token);
		// TempMsgParams pa;ram = new TempMsgParams();
		new WxMsgUtil().sendTemplateMsg(access_token, null);
	}
}
