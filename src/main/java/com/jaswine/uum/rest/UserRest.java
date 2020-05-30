package com.jaswine.uum.rest;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.auth.pojo.AuthUser;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.uum.bean.dto.SimpleUserDTO;
import com.lanswon.uum.bean.pojo.UserPojo;
import com.lanswon.uum.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 用户Controller
 * @author jaswine
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserRest {


	@Resource
	private UserService userService;


	@PostMapping
	@ApiOperation("新增用户")
	public DTO insertUser(@RequestBody @Valid UserPojo user){
		return userService.insertUser(user);
	}

	@DeleteMapping("/{uid}")
	@ApiOperation(value = "删除用户")
	public DTO deleteUser(@PathVariable  long uid){

		return userService.deleteUser(uid);
	}

	@PutMapping
	@ApiOperation(value = "更新用户")
	public DTO updateUser(@RequestBody UserPojo user ){
		return userService.updateUser(user);
	}

	@GetMapping
	@ApiOperation(value = "获得所有用户信息-分页")
	public DTO getAllUser(@RequestParam(value = "page") long page,
	                      @RequestParam(value = "limit")long limit,
	                      @RequestParam(value = "asc")int asc){
		return userService.getAllUser(new Page<>(page,limit),asc);
	}


	@GetMapping("/{username}")
	@ApiOperation(value = "根据用户名获得用户信息(权限用户-实现userdetail)")
	public DataRtnDTO<AuthUser> getUserInfoByUsername(@PathVariable String username){
		return userService.getUserInfoByUsername(username);
	}

	@GetMapping(value = "/detail/{uid}")
	@ApiOperation(value = "依据用户id获得用户详情")
	public DTO getUserInfoByUid(@PathVariable(value = "uid") long uid){
		return userService.getUserInfoByUid(uid);
	}

	@GetMapping(value = "/simple/{uid}")
	@ApiOperation(value = "依据用户id获得简单用户信息")
	public DataRtnDTO<SimpleUserDTO> getUserInfoByUid(@PathVariable(value = "uid")Long uid){
		return userService.getSimpleUserInfoByUid(uid);
	}

	@PutMapping(value = "/password/{uid}/{password}")
	@ApiOperation(value = "更改用户密码")
	public DTO changePassword(@PathVariable(value = "uid") Long uid,
	                          @PathVariable(value = "password") String password){
		return userService.changePassword(uid,password);
	}

	@PostMapping("/user/{uid}/role/{rid}")
	@ApiOperation(value = "用户绑定角色")
	public DTO userBindRole(@PathVariable(value = "uid") Long uid,
	                        @PathVariable(value = "rid") Long  rid){
		return userService.userBindRole(uid,rid);
	}

	@DeleteMapping("/user/{uid}/role/{rid}")
	@ApiOperation(value = "用户-角色解绑")
	public DTO userUnbindRole(@PathVariable(value = "uid") Long uid,
	                          @PathVariable(value = "rid") Long rid){
		return userService.userUnbindRole(uid,rid);
	}

}
