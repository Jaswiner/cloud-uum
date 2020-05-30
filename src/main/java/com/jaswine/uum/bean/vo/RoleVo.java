package com.jaswine.uum.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 角色VO
 *
 * @author jaswine
 */
@Data
public class RoleVo {


	private long id;

	private String cnName;

	private String enName;

	private List<PermissionVO> permissions;
}
