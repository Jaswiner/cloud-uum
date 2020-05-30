package com.jaswine.uum.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 角色-权限中间表
 *
 * @author jaswine
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_role_permission")
public class TbRolePermission extends BasePojo{

  /** 角色ID */
  private long roleId;
  /** 权限ID */
  private long permissionId;


}
