package com.jaswine.uum.bean.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.lanswon.commons.web.auth.pojo.AuthRole;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户VO对象
 *
 * @author jaswine
 */
@Data
public class UserVO {

	/** id */
	private long id;

	/** 用户名 */
	private String username;

	/** 昵称 */
	private String nickname;

	/** 电话 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 头像 */
	private String pic;

	/** 地址 */
	private String location;

	/** 性别代码 */
	private int sexCode;
	/** 性别 */
	private String sex;

	/** 账户是否可用代码 */
	private int accountEnableCode;
	/** 账户是否是否可用 */
	private String accountEnable;

	/** 账户是否过期代码 */
	private String accountNonExpiredCode;
	/** 账户是否过期 */
	private String accountNonExpired;

	/** 密码是否过期 */
	private int passwordNonExpiredCode;
	/** 密码是否过期 */
	private String passwordNonExpired;

	/** 账户是否锁定 */
	private int accountNonLockedCode;
	/** 账户是否锁定 */
	private String accountNonLocked;

	/** 角色列表 */
	private List<AuthRole> roles;

	/** 创建人 */
	private String createdBy;

	/** 更新人 */
	private String updatedBy;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
	private Date createdTime;

	/** 更新时间 */
	@JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
	private Date updatedTime ;

}
