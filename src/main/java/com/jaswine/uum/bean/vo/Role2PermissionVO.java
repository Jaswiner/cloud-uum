package com.jaswine.uum.bean.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色～权限键值对
 *
 * @author jaswine
 */
@ToString
public class Role2PermissionVO {

	/**
	 * 角色
	 */
	@Getter
	@Setter
	private String role;
	/**
	 * 权限
	 */
	@Getter
	@Setter
	private String permission;
}
