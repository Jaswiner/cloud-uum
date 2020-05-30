package com.jaswine.uum.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 用户角色中间表
 *
 * @author jaswine
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_user_role")
public class TbUserRole extends BasePojo{

  /** 用户ID */
  private long userId;
  /** 角色ID */
  private long roleId;



}
