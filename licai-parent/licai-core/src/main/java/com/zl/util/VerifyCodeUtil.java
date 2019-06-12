package com.zl.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.zl.pojo.VerifyCodeInfo;
/*import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;*/
public class VerifyCodeUtil {
	private static int[] codes = {0,1,2,3,4,5,6,7,8,9};
	/**
	 * 发送短信验证码
	 * @param phoneNum 接收验证码的手机号
	 */
	public static void sendMessage(String phoneNum) {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
		try {
			/*HttpPost post = new HttpPost("http://v.juhe.cn/sms/send");
			List<NameValuePair> paramList = new ArrayList<>();
			paramList.add(new BasicNameValuePair("mobile", phoneNum));
			paramList.add(new BasicNameValuePair("tpl_id", "158039"));
			String code = getRandomCode(6);
			paramList.add(new BasicNameValuePair("tpl_value", "#code#=" + code));
			paramList.add(new BasicNameValuePair("key", "0b2dfc0529705e13af4616d6c889076f"));
			// 模拟form表单
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
			post.setEntity(entity);
			response = httpClient.execute(post);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");*/
			String code = "1234";
			resultString = "{\"reason\":\"操作成功\",\"result\":{\"sid\":\"201906101711319363788juhehy\",\"fee\":1,\"count\":1},\"error_code\":0}";
			VerifyCodeInfo verifyCodeInfo = JSONObject.parseObject(resultString, VerifyCodeInfo.class);
			if(verifyCodeInfo.getError_code() == 0){
				System.out.println(phoneNum);
				verifyCodeInfo.setPhoneNumber(phoneNum);
				verifyCodeInfo.setVerifyCode(code);
				verifyCodeInfo.setLastTime(new Date());
				UserContext.setVerifyCodeInSession(verifyCodeInfo);
			}else{
				throw new RuntimeException("发送失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 随机生成验证码
	 * @param length 验证码长度
	 * @return
	 */
	public static String getRandomCode(int length){
		if(length <= 0){
			throw new IndexOutOfBoundsException("length:"+length);
		}
		
		StringBuffer code = new StringBuffer();
		for (int i=0;i<length;i++) {
			int index = (int)(Math.random()*codes.length);
			code = code.append(codes[index]);
		}
		return code.toString();
	}
	
	
}
