package com.jaswine.uum.bean.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lanswon.commons.core.serial.SnowFlake;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * POJO公共属性
 *
 * @author jaswine
 */
@Data
@ApiModel(value = "公共属性")
public abstract class BasePojo implements Serializable {


	/** 主键 */
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id",hidden = true)
	private long id = SnowFlake.nextId();

	/** 创建人 */
	@ApiModelProperty(value = "创建人",hidden = true)
	private Long createdBy = null;

	/** 更新人 */
	@ApiModelProperty(value = "更新人",hidden = true)
	private Long updatedBy = null;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间",hidden = true)
	private Date createdTime = new Date();

	/** 更新时间 */
	@ApiModelProperty(value = "更新时间",hidden = true)
	private Date updatedTime = new Date();

}
