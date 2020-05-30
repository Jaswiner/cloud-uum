package com.jaswine.uum.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.auth.pojo.AuthRole;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.commons.web.custom.dto.SimpleRtnDTO;
import com.lanswon.commons.web.custom.rtn.CustomRtnEnum;
import com.lanswon.uum.bean.DataBaseConstants;
import com.lanswon.uum.bean.pojo.TbRole;
import com.lanswon.uum.bean.pojo.TbRolePermission;
import com.lanswon.uum.bean.pojo.TbUserRole;
import com.lanswon.uum.mapper.RoleMapper;
import com.lanswon.uum.mapper.RolePermissionMapper;
import com.lanswon.uum.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * 角色service
 *
 * @author jaswine
 */
@Service
@Slf4j
public class RoleService {

	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private RolePermissionMapper rolePermissionMapper;


	public List<TbRolePermission> getAllRole2Url(){
		log.info("获得所有角色-权限对应");
		return rolePermissionMapper.selectList(null);
	}

	/**
	 * 插入角色
	 * @param role  角色信息
	 * @return dto
	 */
	public DTO insertRole(TbRole role) {
		//role.setId(SnowFlake.nextId());
		role.setCreatedTime(new Date());

		if (roleMapper.insert(role) == 0){

			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	/**
	 * 删除角色信息
	 * @param rid 角色id
	 * @return dto
	 */
	@Transactional(rollbackFor = Exception.class)
	public DTO deleteRole(long rid) {

		log.info("删除角色-->{}", rid);

		// 删除角色
		if (roleMapper.deleteById(String.valueOf(rid)) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());

			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		// 删除用户-角色关系
		if (!userRoleMapper.selectList(new QueryWrapper<TbUserRole>().eq(DataBaseConstants.USER_ROLE_ROLEID, rid)).isEmpty()){
			if (userRoleMapper.delete(new QueryWrapper<TbUserRole>().eq(DataBaseConstants.USER_ROLE_ROLEID, rid)) == 0){
				log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
				return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
			}
		}

		// 删除角色-权限之间的关系
		if (!rolePermissionMapper.selectList(new QueryWrapper<TbRolePermission>().eq(DataBaseConstants.ROLE_PERMISSION_ROLEID, rid)).isEmpty()){
			if (rolePermissionMapper.delete(new QueryWrapper<TbRolePermission>().eq(DataBaseConstants.ROLE_PERMISSION_ROLEID, rid)) == 0){
				log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
				return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
			}
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	/**
	 * 更新角色信息
	 * @param role 角色
	 * @return dto
	 */
	public DTO updateRole(TbRole role) {
		log.info("更新角色-->{}",role.getId());
		role.setUpdatedTime(new Date());
		if (roleMapper.updateById(role) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());

			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	/**
	 * 获得所有角色信息
	 * @return dto
	 */
	public DTO getAllRolePage(int page,int limit, int asc) {
		log.info("获得所有角色信息");
		IPage<TbRole> rolesPage = roleMapper.getAllRoles(new Page<>(page, limit), asc);
		if (rolesPage.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());

			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(),rolesPage);

	}

	/**
	 * 角色绑定权限
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return dto
	 */
	public DTO roleBindPermission(long rid, long pid) {
		TbRolePermission tbRolePermission = new TbRolePermission(rid, pid);
		tbRolePermission.setCreatedTime(new Date());
		if (rolePermissionMapper.insert(tbRolePermission) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());

			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	/**
	 * 角色解绑权限
	 * @param rid 角色id
	 * @param pid 权限id
	 * @return dto
	 */
	public DTO roleUnbindPermission(long rid, long pid) {
		Wrapper<TbRolePermission> queryWrapper = new QueryWrapper<TbRolePermission>().eq(DataBaseConstants.ROLE_PERMISSION_ROLEID, rid).eq(DataBaseConstants.ROLE_PERMISSION_PERMISSIONID, pid);

		if (rolePermissionMapper.selectOne(queryWrapper) == null){
			log.error("不存在角色-->{}和权限-->{}的对应关系", rid,pid);
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		if (rolePermissionMapper.delete(queryWrapper) == 0){
			log.error("角色-权限解绑失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getAllRolesByUid(long uid) {
		log.info("获得用户id为{}的所有角色信息",uid);
		List<AuthRole> roles = roleMapper.getRoleByUserId(uid);
		if (roles.isEmpty()){
			log.info("用户-->{}没有绑定角色",uid);
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),roles);

	}

	public DTO getAllRole() {
		log.info("获得所有角色");
		List<TbRole> roles = roleMapper.selectList(null);

		if(roles.isEmpty()){

			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());

			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(),roles);

	}
}
