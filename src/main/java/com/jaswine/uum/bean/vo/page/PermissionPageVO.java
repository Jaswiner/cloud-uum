package com.jaswine.uum.bean.vo.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PermissionPageVO {


	private long id;
	private long parentId;
	private String parent;
	private String cnName;
	private String enName;
	private String method;
	private String url;
	private int typeCode;
	private String type;
	private String description;

	@JsonFormat(pattern = "yyyy年MM月dd日 HH:MM:ss")
	private Date createdTime;
	@JsonFormat(pattern = "yyyy年MM月dd日 HH:MM:ss")
	private Date updatedTime;

	private String createdBy;
	private String updatedBy;
}
