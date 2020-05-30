package com.jaswine.uum.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.auth.pojo.AuthUrl;
import com.lanswon.uum.bean.pojo.TbPermission;
import com.lanswon.uum.bean.vo.ButtonVO;
import com.lanswon.uum.bean.vo.MenuTreeVO;
import com.lanswon.uum.bean.vo.PermissionTreeVo;
import com.lanswon.uum.bean.vo.Url2methodVO;
import com.lanswon.uum.bean.vo.page.PermissionPageVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * permission mapper
 *
 * @author jaswine
 */
@Repository
@Mapper
public interface PermissionMapper extends BaseMapper<TbPermission> {

	Set<MenuTreeVO> getMenusByRoleId(@Param("id") long id);

	@Select("SELECT t.method,t.url FROM tb_permission t " +
			"LEFT JOIN tb_role_permission t1 ON t1.permission_id = t.id " +
			"LEFT JOIN tb_user_role t2 ON t1.role_id = t2.role_id " +
			"LEFT JOIN tb_user t3 ON t2.user_id = t3.id " +
			"WHERE t.type= 2 " +
			"AND t3.username = #{username}")
	List<Url2methodVO> getAllUrlByUsername(String username);

	@Select("SELECT t.method,t.url FROM tb_permission t LEFT JOIN tb_role_permission t1 ON t.id = t1.permission_id WHERE t1.role_id = #{id} ")
	Set<AuthUrl> findUrlsByRid(long id);


	List<MenuTreeVO> getAllMenu(@Param("uid") long uid);

	Set<TbPermission> getAllUrlByRid(long rid);

	List<PermissionTreeVo> getPermissionTreeInfo();

	IPage<PermissionPageVO> getPermissionPageInfo(Page<Object> objectPage, int asc);

	List<MenuTreeVO> getAllMenuInfo();


	int checkUserHasPermission(@Param("uid") long uid,
	                           @Param("id") long id);

	List<ButtonVO> getButtonByPidAndUid(@Param("uid") Long uid,
	                                    @Param("pid") Long pid);

	@Delete("DELETE FROM tb_role_permission  WHERE permission_id = #{id} ")
	void unBindRoleById(@Param("id") long id);
}
