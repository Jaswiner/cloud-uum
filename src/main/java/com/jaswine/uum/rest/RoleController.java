package com.jaswine.uum.rest;


import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.uum.bean.pojo.TbRole;
import com.lanswon.uum.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 角色REST
 *
 * @author jaswine
 */
@RestController
@RequestMapping(value = "/role")
@Api(value = "ROLE'S APIS")
public class RoleController {


	@Resource
	private RoleService roleService;


	/**
	 * 插入角色
	 * @param role  角色信息
	 * @return dto
	 */
	@PostMapping
	@ApiOperation(value = "新增角色信息")
	public DTO insertRole(@RequestBody @Valid TbRole role){
		return roleService.insertRole(role);
	}

	/**
	 * 删除角色信息
	 * @param rid 角色id
	 * @return dto
	 */
	@DeleteMapping("/{rid}")
	@ApiOperation(value = "删除角色信息")
	public DTO deleteRole(@PathVariable long rid){
		return roleService.deleteRole(rid);
	}

	/**
	 * 更新角色信息
	 * @param role 角色
	 * @return dto
	 */
	@PutMapping
	@ApiOperation(value = "更新角色信息")
	public DTO updateRole(@RequestBody TbRole role){
		return roleService.updateRole(role);
	}

	/**
	 * 获得所有角色信息
	 * @return dto
	 */
	@GetMapping
	@ApiOperation(value = "获得所有角色信息-分页")
	public DTO getAllRolePage(@RequestParam(value = "page") int page,
	                          @RequestParam(value = "limit") int limit,
	                          @RequestParam(value = "asc") int asc){
		return roleService.getAllRolePage(page,limit,asc);
	}

	@GetMapping("/all")
	@ApiOperation(value = "获得所有角色")
	public DTO getAllRole(){
		return roleService.getAllRole();
	}

	@GetMapping("/{uid}")
	@ApiOperation(value = "依据uid获得所有角色")
	public DTO getAllRolesByUid(@PathVariable(value = "uid") long uid){
		return roleService.getAllRolesByUid(uid);
	}

	/**
	 * 角色绑定权限
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return dto
	 */
	@PostMapping("/role/{rid}/permission/{pid}")
	@ApiOperation(value = "绑定角色和权限")
	public DTO roleBindPermission(@PathVariable long rid,@PathVariable long pid){
		return roleService.roleBindPermission(rid,pid);
	}

	/**
	 * 角色解绑权限
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return dto
	 */
	@DeleteMapping("/role/{rid}/permission/{pid}")
	@ApiOperation(value = "绑定解绑和权限")
	public DTO roleUnbindPermission(@PathVariable long rid,@PathVariable long pid){
		return roleService.roleUnbindPermission(rid,pid);
	}


}
