package com.jaswine.uum.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.auth.pojo.AuthRole;
import com.lanswon.uum.bean.pojo.TbRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 角色mapper
 * @author jaswine
 */
@Repository
@Mapper
public interface RoleMapper extends BaseMapper<TbRole> {

	List<AuthRole> getRoleByUserId(long id);

	IPage<TbRole> getAllRoles(@Param("page") Page<Object> objectPage,
	                          @Param("asc") int asc);
}
