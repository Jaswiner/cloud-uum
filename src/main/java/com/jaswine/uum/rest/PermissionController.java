package com.jaswine.uum.rest;


import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.uum.bean.pojo.TbPermission;
import com.lanswon.uum.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 权限REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/permission")
@Api(tags = "权限管理")
public class PermissionController {

	@Resource
	private PermissionService permissionService;

	/**
	 * 新增权限信息
	 * @param permission 权限信息
	 * @return dto
	 */
	@PostMapping
	@ApiOperation(value = "新增权限")
	public DTO insertPermission(@RequestBody @Valid TbPermission permission){
		return permissionService.insertPermission(permission);
	}

	/**
	 * 删除权限信息
	 * @param pid 权限id
	 * @return dto
	 */
	@DeleteMapping("/{pid}")
	@ApiOperation(value = "删除权限信息")
	public DTO deletePermission(@PathVariable long pid){
		return permissionService.deletePermission(pid);
	}

	/**
	 * 更新权限信息
	 * @param permission 权限信息
	 * @return dto
	 */
	@PutMapping
	@ApiOperation(value = "更新权限信息")
	public DTO updatePermission(@RequestBody TbPermission permission){
		return permissionService.updatePermission(permission);
	}


	/**
	 * 获得所有权限信息
	 * @return dto
	 */
	@GetMapping
	@ApiOperation(value = "获得所有的权限信息-分页")
	public DTO getAllPermissionInfo(@RequestParam(value = "page") int page,
	                                @RequestParam(value = "limit") int limit,
	                                @RequestParam(value = "asc")int asc){
		return permissionService.getAllPermissionPageInfo(page,limit,asc);
	}


	@GetMapping(value = "/user/{username}")
	@ApiModelProperty(value = "获得用户名对应的URL")
	public DTO getAllUrlByUsername(@PathVariable(value = "username") String username){
		return permissionService.getAllUrlByUsername(username);
	}


	@GetMapping("/menu/{uid}")
	@ApiOperation(value = "获得树形菜单")
	public DTO getAllMenu(@PathVariable(value = "uid") long uid){
		return permissionService.getAllMenuTreeInfo(uid);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有的权限信息")
	public DTO getAllPermissionInfo(){
		return permissionService.getAllPermissionInfo();
	}

	@GetMapping(value = "/{rid}")
	@ApiOperation(value = "依据角色id获得权限信息")
	public DTO getAllUrlsByRid(@PathVariable(value = "rid") long rid){
		return permissionService.getAllUrlByRid(rid);
	}

	@GetMapping(value = "/tree")
	@ApiOperation(value = "权限树")
	public DTO getPermissionTreeInfo(){
		return permissionService.getPermissionTreeInfo();
	}

	@GetMapping(value = "/button/{pid}/{uid}")
	@ApiOperation(value = "获得菜单下的按钮")
	public DTO getButtonByPid(@PathVariable(value = "pid")Long pid,
	                          @PathVariable(value = "uid")Long uid){
		return permissionService.getButtonByPid(pid,uid);
	}
}
