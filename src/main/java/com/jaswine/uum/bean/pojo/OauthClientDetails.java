package com.jaswine.uum.bean.pojo;


import lombok.Getter;
import lombok.Setter;

/**
 * 客户端信息
 *
 * @author jaswine
 */
public class OauthClientDetails {

  /**
   * 用于唯一标识每一个客户端(client)
   * 在注册时必须填写(也可由服务端自动生成)
   */
  @Getter
  @Setter
  private String clientId;
  /**
   * 客户端所能访问的资源id集合
   * 多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource".
   */
  @Getter
  @Setter
  private String resourceIds;
  /**
   * 用于指定客户端(client)的访问密匙
   * 在注册时必须填写(也可由服务端自动生成).
   */
  @Getter
  @Setter
  private String clientSecret;
  /**
   * 指定客户端申请的权限范围
   * 可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: "read,write".
   * */
  @Getter
  @Setter
  private String scope;
  /**
   * 指定客户端支持的grant_type
   *
   * 可选值包括
   * - authorization_code
   * - password
   * - refresh_token
   * - implicit,
   * -client_credentials
   *
   * 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".
   * */
  @Getter
  @Setter
  private String authorizedGrantTypes;
  /**
   * 客户端的重定向URI
   * */
  @Getter
  @Setter
  private String webServerRedirectUri;
  /**
   * 指定客户端所拥有的Spring Security的权限值,可选
   *
   * 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".
   * */
  @Getter
  @Setter
  private String authorities;
  /**
   * 设定客户端的access_token的有效时间值(单位:秒),可选
   *
   * 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
   * */
  @Getter
  @Setter
  private long accessTokenValidity;
  /**
   * 设定客户端的refresh_token的有效时间值(单位:秒),可选
   * 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
   * */
  @Getter
  @Setter
  private long refreshTokenValidity;
  /**
   * 这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据
   * */
  @Getter
  @Setter
  private String additionalInformation;
  /**
   * 设置用户是否自动Approval操作
   *
   * 默认值为 'false', 可选值包括 'true','false', 'read','write'.
   * */
  @Getter
  @Setter
  private String autoapprove;

}
