package com.jaswine.uum.bean;


/**
 * 数据库常量
 *
 * @author jaswine
 */
public class DataBaseConstants {

	/** ID */
	public static final String BASE_ID = "id";
	/** 创建时间 */
	public static final String BASE_CT = "created_time";
	/** 更新时间 */
	public static final String BASE_UT = "updated_time";
	/** 创建人 */
	public static final String BASE_CU = "created_by";
	/** 更新人 */
	public static final String BASE_UU = "updated_by";

	//////////oauth_client_details/////////

	public static final String OAUTH_CLIENT_DEATILS_CLIENT_ID = "client_id";


	//////////Tb-USER/////////

	public static final String USER_USERNAME = "username";
	public static final String USER_PASSWORD = "password";
	public static final String USER_NICKNAME = "nickname";
	public static final String USER_PHONE = "phone";
	public static final String USER_EMAIL= "email";
	public static final String USER_STATUS = "status";
	public static final String USER_PIC = "pic";
	public static final String USER_WXID = "wxid";
	public static final String USER_LOCATION = "location";
	public static final String USER_SEX = "sex";

	//////////Tb-USER-ROLE/////////

	public static final String USER_ROLE_USERID = "user_id";
	public static final String USER_ROLE_ROLEID = "role_id";


	//////////Tb-ROLE-PERMISSION/////////

	public static final String ROLE_PERMISSION_ROLEID = "role_id";
	public static final String ROLE_PERMISSION_PERMISSIONID = "permission_id";



}
