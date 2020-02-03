package com.blog.common.result;

import lombok.Data;


import java.io.Serializable;

/**
*@Author Yangcb
*@Descrintion  统一返回结果类
*@Date create 2018/5/22 9:43
*@Version 1.0
*/
@Data
public abstract class BaseResult implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 返回状态码 */
	private Integer code;
	
	/** 返回信息 */
	private String message;
	
	/** 返回数据 */
	private Object data;

	public BaseResult(){
		super();
	}
	
	public BaseResult(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseResult(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
