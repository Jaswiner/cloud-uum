package com.jaswine.uum.bean.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色表
 *
 * @author jaswine
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "tb_role")
public class TbRole extends BasePojo{

  /** 父ID */
  private long parentId;

  /** 角色名 */
  @NotNull(message = "角色名不可为空")
  @NotBlank(message = "角色名不可为null")
  private String cnName;

  /** 英文名 */
  @NotNull(message = "角色名不可为空")
  @NotBlank(message = "角色名不可为null")
  private String enName;

  /** 描述 */
  private String description;

}
