package com.jaswine.uum.bean;

import lombok.Getter;


/**
 * Admin Enum
 *
 * @author jaswine
 */
public enum  RtnAdminEnum {


	/** 没有菜单信息 */
	NO_MENUS(60000,"没有菜单信息");


	@Getter
	private int status;
	@Getter
	private String msg;



	RtnAdminEnum(int status, String msg){
		this.status = status;
		this.msg = msg;
	}
}
