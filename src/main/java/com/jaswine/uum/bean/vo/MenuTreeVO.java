package com.jaswine.uum.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * 菜单VO
 *
 * @author jaswine
 */
@Data
public class MenuTreeVO {

	private long id;
	private String name;
	private String url;
	private long pid;
	private List<MenuTreeVO> children;

}
