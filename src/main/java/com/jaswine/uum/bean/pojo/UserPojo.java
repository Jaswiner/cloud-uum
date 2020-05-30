package com.jaswine.uum.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 用户表
 *
 * @author jaswine
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user")
@ApiModel(value = "用户对象")
public class UserPojo extends BasePojo{


  /** 用户名 */
  @NotBlank(message = "用户名不可为空")
  @NotNull(message = "用户名不可为空")
  @ApiModelProperty(value = "用户名",required = true)
  private String username;

  /** 密码 */
  @NotBlank(message = "密码不可为空")
  @NotNull(message = "密码不可为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;

  /** 昵称 */
  @NotNull(message = "昵称不可为空")
  @NotBlank(message = "昵称不可为空")
  @ApiModelProperty(value = "昵称",required = true)
  private String nickname;

  /** 电话 */
  @ApiModelProperty(value = "电话")
  private String phone;

  /** email */
  @ApiModelProperty(value = "email")
  private String email;

  /** 画像 */
  @ApiModelProperty(value = "画像")
  private String pic;

  /** wxid */
  @ApiModelProperty(value = "wxid",hidden = true)
  private String wxid;

  /** 地址  */
  @ApiModelProperty(value = "地址")
  private String location;

  /** 性别 */
  @ApiModelProperty(value = "性别")
  private Long sex;

  /** 账户是否可用 */
  @ApiModelProperty(value = "账户是否可用",required = true)
  private Integer accountEnable;

  /** 账户是否过期 */
  @ApiModelProperty(value = "账户是否过期",hidden = true)
  private Integer accountNonExpired;

  /** 证书是否过期 */
  @ApiModelProperty(value = "证书是否过期",hidden = true)
  private Integer credentialNonExpired;

  /** 账户是否锁定 */
  @ApiModelProperty(value = "账户是否锁定",hidden = true)
  private Integer accountNonLock;

}
