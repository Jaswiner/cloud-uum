package com.jaswine.uum.rest;


import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.uum.service.GeneralAdminService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理页面rest
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "通用管理页面")
public class GeneralAdminRest {

	@Resource
	private GeneralAdminService generalAdminService;

	@GetMapping(value = "/menu/{uid}")
	public DTO getMenusByUid(@PathVariable(value = "uid") long uid){
		return generalAdminService.getMenusByUid(uid);
	}

}
