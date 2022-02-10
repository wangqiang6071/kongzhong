package com.jingbo.kongzhong.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 保证序列化json的时候,如果是null的对象,key也会消失
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {
	private static final long serialVersionUID = -4404287366319130355L;
	
	private int status;
	private String contenerId;
	private String Port;
	private String cookies;
	private String msg;
	private String contenerName;
	private T data;
	
	public ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	public ServerResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public ServerResponse(int status,String contenerName,String Port,String contenerId,String cookies,String msg) {
		this.cookies=cookies;
		this.status = status;
		this.contenerId=contenerId;
		this.Port=Port;
		this.msg = msg;
		this.contenerName=contenerName;
	}

	/**
	 * 使之不在json序列化结果当中
	 */
	@JsonIgnore
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS.getCode();
	}
	
	public static <T> ServerResponse<T> createBySuccess(T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}
	public static <T> ServerResponse<T> createBySuccess(String msg) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
	}
	public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> ServerResponse<T> createBySuccess(int status,String contenerName,String Port,String contenerId,String cookies,String msg) {
		return new ServerResponse<T>(status,contenerName,Port,contenerId,cookies,msg);
	}

	public static <T> ServerResponse<T> createByError(String msg, T data) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg, data);
	}
	public static <T> ServerResponse<T> createByError(String msg) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), msg);
	}
	public static <T> ServerResponse<T> createByError(T data) {
		return new ServerResponse<T>(ResponseCode.ERROR.getCode(), data);
	}
	
	public static <T> ServerResponse<T> createByError(ResponseCode response,String msg) {
		return new ServerResponse<T>(response.getCode(), msg);
	}

	
	public Map<String, Object>toMap(){
    	Map<String, Object> map=new HashMap<String, Object>();
    	map.put("status", status);
    	map.put("msg", msg);
    	map.put("data", data);
    	return map;
    }
}
