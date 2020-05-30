package com.jaswine.uum.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.web.auth.pojo.AuthUser;
import com.lanswon.uum.bean.dto.SimpleUserDTO;
import com.lanswon.uum.bean.pojo.UserPojo;
import com.lanswon.uum.bean.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * UserMapper
 *
 * @author Jaswine
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserPojo> {

	/**
	 * 依据用户名获得用户信息
	 * @param username 用户名
	 * @return 用户
	 */
	AuthUser getUserInfoByUsername(String username);

	UserVO getUserInfoByUid(long uid);

	IPage<UserVO> getuserInfoPage(Page<UserPojo> page, int asc);

	@Select("SELECT t.username ,t.nickname FROM tb_user t WHERE t.id = #{uid} ")
	SimpleUserDTO getSimpleUserInfoByUid(Long uid);

	boolean changePassword(@Param("uid") Long uid,
	                       @Param("password") String password);

}
