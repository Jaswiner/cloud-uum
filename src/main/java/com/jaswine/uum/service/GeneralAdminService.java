package com.jaswine.uum.service;


import com.lanswon.commons.web.auth.pojo.AuthRole;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.commons.web.custom.dto.SimpleRtnDTO;
import com.lanswon.commons.web.custom.rtn.CustomRtnEnum;
import com.lanswon.uum.bean.RtnAdminEnum;
import com.lanswon.uum.bean.vo.MenuTreeVO;
import com.lanswon.uum.mapper.PermissionMapper;
import com.lanswon.uum.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * 通用管理service
 *
 *
 * @author jaswine
 */
@Service
@Slf4j
public class GeneralAdminService {


	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PermissionMapper permissionMapper;

	public DTO getMenusByUid(long uid) {
		log.info("获得用户id为：{}的菜单列表",uid);
		List<AuthRole> roles = roleMapper.getRoleByUserId(uid);
		HashSet<MenuTreeVO> menuSet = new HashSet<>();
		roles.forEach(authRole -> {
			// SET 并集
			menuSet.addAll(permissionMapper.getMenusByRoleId(authRole.getId()));
		});

		if (menuSet.size() == 0){
			log.error("当前用户没有可以访问的菜单信息");
			return new SimpleRtnDTO(RtnAdminEnum.NO_MENUS.getStatus(),RtnAdminEnum.NO_MENUS.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),menuSet);
	}
}
