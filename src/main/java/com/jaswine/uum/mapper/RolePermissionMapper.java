package com.jaswine.uum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.uum.bean.pojo.TbRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface RolePermissionMapper extends BaseMapper<TbRolePermission> {
}
