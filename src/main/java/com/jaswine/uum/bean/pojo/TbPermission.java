package com.jaswine.uum.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 权限
 *
 * @author jaswine
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_permission")
@ApiModel(value = "权限对象")
public class TbPermission extends BasePojo{

  /** 副ID */
  private long parentId;

  /** 名称 */
  @NotBlank(message = "权限名称不可为空")
  @ApiModelProperty(value = "中文名称",required = true)
  private String cnName;

  /** 英文名称 */
  @NotBlank(message = "权限名称不可为空")
  @ApiModelProperty(value = "英文名称",required = true)
  private String enName;

  /** 请求方法 */
  @NotBlank(message = "请求方法不可为空")
  @ApiModelProperty(value = "请求方法",required = true)
  private String method;

  /** 权限路径 */
  @NotBlank(message = "权限路径不可为空")
  @ApiModelProperty(value = "请求路径",required = true)
  private String url;

  /** 权限类型1.页面;2.按钮*/
  @NotNull(message = "权限类型不可为空")
  @ApiModelProperty(value = "权限类型1.页面;2.按钮",required = true)
  private int type;

  /** 权限描述 */
  private String description;


}
