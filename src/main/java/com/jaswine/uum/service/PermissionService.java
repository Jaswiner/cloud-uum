package com.jaswine.uum.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.commons.web.custom.dto.SimpleRtnDTO;
import com.lanswon.commons.web.custom.rtn.CustomRtnEnum;
import com.lanswon.uum.bean.DataBaseConstants;
import com.lanswon.uum.bean.pojo.TbPermission;
import com.lanswon.uum.bean.pojo.TbRolePermission;
import com.lanswon.uum.bean.vo.ButtonVO;
import com.lanswon.uum.bean.vo.MenuTreeVO;
import com.lanswon.uum.bean.vo.PermissionTreeVo;
import com.lanswon.uum.bean.vo.page.PermissionPageVO;
import com.lanswon.uum.mapper.PermissionMapper;
import com.lanswon.uum.mapper.RolePermissionMapper;
import com.lanswon.uum.util.TreeUtil;
import com.lanswon.uum.util.TreeUtil1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


/**
 * 权限service
 * @author jaswine
 */
@Service
@Slf4j
public class PermissionService {

	@Resource
	private PermissionMapper permissionMapper;
	@Resource
	private RolePermissionMapper rolePermissionMapper;


	public DTO insertPermission(TbPermission permission) {
		log.info("插入权限信息");

		if (permissionMapper.insert(permission) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	@Transactional(rollbackFor = Exception.class)
	public DTO deletePermission(long pid) {
		log.info("删除权限信息");
		// 删除权限
		if (permissionMapper.deleteById(String.valueOf(pid)) == 0){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new SimpleRtnDTO(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg());
		}

		// 删除角色-权限绑定关系
		Wrapper<TbRolePermission> wrapper = new QueryWrapper<TbRolePermission>().eq(DataBaseConstants.ROLE_PERMISSION_PERMISSIONID, pid);
		if (rolePermissionMapper.delete(wrapper) == 0){
			log.error("该权限没有绑定角色");
		}
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO updatePermission(TbPermission permission) {
		log.info("更新权限信息");

		if (permissionMapper.updateById(permission) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}

	public DTO getAllPermissionPageInfo(int page,int limit,int asc) {

		log.info("查询所有权限信息");

		IPage<PermissionPageVO> permissionList = permissionMapper.getPermissionPageInfo(new Page<>(page,limit),asc);

		if (permissionList.getRecords().isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg(),permissionList);
	}

	public DTO getAllUrlByUsername(String username) {
		log.info("依据用户名：{}查询用户可以访问的url",username);
		permissionMapper.getAllUrlByUsername(username);
		return null;
	}


	public DTO getAllMenuTreeInfo(long uid) {
		log.info("获得树形菜单信息");

		List<MenuTreeVO> allMenu = permissionMapper.getAllMenu(uid);

		if (allMenu.isEmpty()){
			log.error("没有菜单信息");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		List<MenuTreeVO> menuTreeVOS = TreeUtil.toTree(allMenu);

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),menuTreeVOS);
	}



	public DTO getAllUrlByRid(long rid) {
		log.info("获得角色:{}的所有url",rid);

		Set<TbPermission> allUrlByRid = permissionMapper.getAllUrlByRid(rid);

		if (allUrlByRid.isEmpty()){
			log.error("没有菜单信息");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),allUrlByRid);
	}

	public DTO getPermissionTreeInfo() {

		log.info("获得权限树");

		List<PermissionTreeVo> permissionTreeInfo = permissionMapper.getPermissionTreeInfo();

		if (permissionTreeInfo.isEmpty()){
			log.info(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),permissionTreeInfo);
		}


		List<PermissionTreeVo> permissionTreeVos = TreeUtil1.toTree(permissionTreeInfo);



		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),permissionTreeVos);

	}

	public DTO getAllPermissionInfo() {
		List<MenuTreeVO> allMenuInfo = permissionMapper.getAllMenuInfo();
		if (allMenuInfo.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),allMenuInfo);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),allMenuInfo);
	}

	public DTO getButtonByPid(Long pid,Long uid) {
		log.info("获得用户:{}在菜单:{}下的按钮",uid,pid);
		List<ButtonVO> buttons = permissionMapper.getButtonByPidAndUid(uid,pid);
		if (buttons.isEmpty()){
			log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
			return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),buttons);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),buttons);
	}
}
