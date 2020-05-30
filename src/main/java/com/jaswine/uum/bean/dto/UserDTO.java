package com.jaswine.uum.bean.dto;


import com.lanswon.commons.web.auth.pojo.AuthRole;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * 用户传输对象
 *
 * @author jaswine
 */
public class UserDTO {


	/** id */
	@Getter
	@Setter
	private long id;
	/** 用户名 */
	@Getter
	@Setter
	private String username;
	/** 昵称 */
	@Getter
	@Setter
	private String nickname;
	/** 密码 */
	@Getter
	@Setter
	private String password;
	/** 地点 */
	@Getter
	@Setter
	private String location;
	/** 头像图片Url */
	@Getter
	@Setter
	private String pic;
	/** 电话 */
	@Getter
	@Setter
	private String phone;
	/** 邮箱 */
	@Getter
	@Setter
	private String email;
	/** 状态 */
	@Getter
	@Setter
	private int status;
	/** 微信id */
	@Getter
	@Setter
	private String wxid;
	/** 创建时间 */
	@Getter
	@Setter
	private java.sql.Timestamp createtime;
	/** 创建人 */
	@Getter
	@Setter
	private long createUser;
	/** 更新时间 */
	@Getter
	@Setter
	private java.sql.Timestamp updatetime;
	/** 年龄 */
	@Getter
	@Setter
	private int age;
	/** 性别 */
	@Getter
	@Setter
	private int sex;

	/** 角色集合 */
	@Getter
	@Setter
	private Collection<AuthRole> authRoles;
}
