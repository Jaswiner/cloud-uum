package com.jaswine.uum.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 权限树VO
 *
 * @author jaswine
 */
@Data
public class PermissionTreeVo {

	/** id */
	private long id;

	/** 名称 */
	private String title;

	private long pid;

	private String spread = "true";

	private boolean disabled = true;

	/** 子菜单 */
	private List<PermissionTreeVo> children;
}
