package com.jaswine.uum.rest;


import com.lanswon.commons.web.auth.pojo.OauthClientDetails;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.uum.service.AppClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * APP CLIENT REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/client")
@Api(tags = "客户端管理")
public class AppClientController {

	@Resource
	private AppClientService appClientService;

	@PostMapping
	@ApiOperation(value = "新增(注册客户端)")
	public DTO registerAppClient(@RequestBody OauthClientDetails clientDetails){
		return appClientService.registerAppClient(clientDetails);
	}


}



