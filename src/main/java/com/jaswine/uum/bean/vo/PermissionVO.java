package com.jaswine.uum.bean.vo;

import lombok.Data;

/**
 * 权限VO
 *
 * @author jaswine
 */
@Data
public class PermissionVO {

	/** id */
	private long id;

	/** 权限名称 */
	private String name;

	/** 请求方法 */
	private String method;

	/** 请求url */
	private String url;

	/** 权限类型 1.菜单 2.按钮*/
	private int typeCode;

	/** 权限类型 */
	private String type;

	/** 说明 */
	private String description;






}
