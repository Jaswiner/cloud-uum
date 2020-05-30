package com.jaswine.uum.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanswon.commons.core.password.PasswordRegex;
import com.lanswon.commons.web.auth.pojo.AuthUrl;
import com.lanswon.commons.web.auth.pojo.AuthUser;
import com.lanswon.commons.web.auth.rtn.AuthRtnEnum;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.commons.web.custom.dto.SimpleRtnDTO;
import com.lanswon.commons.web.custom.rtn.CustomRtnEnum;
import com.lanswon.uum.bean.DataBaseConstants;
import com.lanswon.uum.bean.dto.SimpleUserDTO;
import com.lanswon.uum.bean.pojo.TbUserRole;
import com.lanswon.uum.bean.pojo.UserPojo;
import com.lanswon.uum.bean.vo.UserVO;
import com.lanswon.uum.mapper.PermissionMapper;
import com.lanswon.uum.mapper.RoleMapper;
import com.lanswon.uum.mapper.UserMapper;
import com.lanswon.uum.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * 用户服务实现类
 *
 * @author Jaswine
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public DTO insertUser(UserPojo user) {
        log.info("新增用户{}", user);

        DTO dto = new SimpleRtnDTO();
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setAccountNonExpired(1);
        user.setCredentialNonExpired(1);
        user.setAccountNonLock(1);

        user.setCreatedTime(new Date());

        if (userMapper.insert(user) == 0){
            dto.setStatus(CustomRtnEnum.ERROR_BAD_SQL.getStatus());
            dto.setMsg(CustomRtnEnum.ERROR_BAD_SQL.getMsg());

            log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
            return dto;
        }

        dto.setStatus(CustomRtnEnum.SUCCESS.getStatus());
        dto.setMsg(CustomRtnEnum.SUCCESS.getMsg());

        log.info(CustomRtnEnum.SUCCESS.toString());

        return dto;
    }

    /**
     * 删除用户
     * @param uid 用户ID
     * @return dto
     */
    @Transactional(rollbackFor = Exception.class)
    public DTO deleteUser(long uid) {
        log.info("删除用户---->{}",uid);

        /* 删除用户 */
        DTO dto = new SimpleRtnDTO();

        if (userMapper.deleteById(String.valueOf(uid)) != 0){

            log.info(" 删除用户绑定的角色");
            int delete = userRoleMapper.delete(new QueryWrapper<TbUserRole>().eq(DataBaseConstants.USER_ROLE_USERID, uid));
            if (delete == 0){
                log.info("用户-->{}没有绑定角色",uid);
            }
        }else {
            dto.setStatus(AuthRtnEnum.ERROR_NON_USER.getStatus());
            dto.setMsg(AuthRtnEnum.ERROR_NON_USER.getMsg());

            log.error(AuthRtnEnum.ERROR_NON_USER.toString());
            return dto;
        }


        dto.setStatus(CustomRtnEnum.SUCCESS.getStatus());
        dto.setMsg(CustomRtnEnum.SUCCESS.getMsg());

        log.info(CustomRtnEnum.SUCCESS.toString());
        return dto;

    }

    /**
     * 更新用户
     * @param user 用户信息
     * @return dto
     */
    public DTO updateUser(UserPojo user) {

        log.info("更新用户{}",user);

        /* 更新 */
        DTO dto = new SimpleRtnDTO();

        if (user.getPassword() != null){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }
		user.setUpdatedTime(new Date());
        if (userMapper.updateById(user) == 0){
            dto.setStatus(CustomRtnEnum.ERROR_BAD_SQL.getStatus());
            dto.setMsg(CustomRtnEnum.ERROR_BAD_SQL.getMsg());

            log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
            return dto;
        }

        dto.setStatus(CustomRtnEnum.SUCCESS.getStatus());
        dto.setMsg(CustomRtnEnum.SUCCESS.getMsg());

        log.info(CustomRtnEnum.SUCCESS.toString());
        return dto;

    }

    /**
     * 获得所有用户信息
     * @return dto
     */
    public DTO getAllUser(Page<UserPojo> page, int asc) {
        log.info("获得所有用户信息");

        // 分页信息
        IPage<UserVO> userPage = userMapper.getuserInfoPage(page, asc);
        if (userPage.getRecords().isEmpty()){
            log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
            return new DataRtnDTO<>(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(),CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg(),userPage);
        }

        log.info(CustomRtnEnum.SUCCESS.toString());
        return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),userPage);
    }

	/**
	 * 依据用户名获得用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	public DataRtnDTO<AuthUser> getUserInfoByUsername(String username) {
		log.info("依据用户名-->{}获得用户信息", username);
		AuthUser user = userMapper.getUserInfoByUsername(username);
		// 用户不存在
		if (user == null){
			log.error(AuthRtnEnum.ERROR_NON_USER.toString());
			return new DataRtnDTO<>(AuthRtnEnum.ERROR_NON_USER.getStatus(),AuthRtnEnum.ERROR_NON_USER.getMsg(),null);
		}
		// 赋角色
		user.setRoles(roleMapper.getRoleByUserId(user.getId()));
		Set<AuthUrl> authUrls = new HashSet<>();
		user.getRoles().forEach(authRole -> {
			authUrls.addAll(permissionMapper.findUrlsByRid(authRole.getId()));
		});
		user.setUrls(authUrls);
		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),user);
	}


	public DTO getUserInfoByUid(Long uid) {
		log.info("依据用户id-->{}获得用户信息", uid);
		UserVO user = userMapper.getUserInfoByUid(uid);
		// 用户不存在
		if (user == null){
			log.error(AuthRtnEnum.ERROR_NON_USER.toString());
			return new DataRtnDTO<>(AuthRtnEnum.ERROR_NON_USER.getStatus(),AuthRtnEnum.ERROR_NON_USER.getMsg(),null);
		}

		// 赋角色
		user.setRoles(roleMapper.getRoleByUserId(user.getId()));

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),user);
	}



    /**
     * 用户绑定角色
     * @param uid 用户id
     * @param rid 角色id
     * @return dto
     */
    public DTO userBindRole(long uid, long rid) {
        log.info("用户-->{}绑定角色-->{}",uid,rid);

        TbUserRole userRole = new TbUserRole(uid, rid);
        //userRole.setId(SnowFlake.nextId());
        userRole.setCreatedTime(new Date());
        if (userRoleMapper.insert(userRole) == 0){
            log.error("用户绑定角色失败");

            log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
            return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
        }

        log.info(CustomRtnEnum.SUCCESS.toString());
        return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());
    }

    /**
     * 解绑用户角色关系
     * @param uid 用户id
     * @param rid 角色id
     * @return dto
     */
    public DTO userUnbindRole(long uid, long rid) {
        log.info("解绑用户-->{}和角色-->{}",uid,rid);

        Wrapper<TbUserRole> userRoleQueryWrapper = new QueryWrapper<TbUserRole>().eq(DataBaseConstants.USER_ROLE_USERID, uid).eq(DataBaseConstants.USER_ROLE_ROLEID, rid);

        // 判断是否存在对应关系
        if (userRoleMapper.selectOne(userRoleQueryWrapper) == null){
            log.error("用户角色不存在对应");

            log.error(CustomRtnEnum.ERROR_EMPTY_RESULT.toString());
            return new SimpleRtnDTO(CustomRtnEnum.ERROR_EMPTY_RESULT.getStatus(), CustomRtnEnum.ERROR_EMPTY_RESULT.getMsg());
        }

        // 删除失败
        if (userRoleMapper.delete(userRoleQueryWrapper) == 0){
            log.error("用户角色解绑失败");

            log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
            return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
        }

        log.info(CustomRtnEnum.SUCCESS.toString());
        return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg());

    }


	public DataRtnDTO<SimpleUserDTO> getSimpleUserInfoByUid(Long uid) {
		log.info("获得用户id为-->{}的简单信息",uid);
		SimpleUserDTO simpleUserInfoByUid = userMapper.getSimpleUserInfoByUid(uid);

		if (simpleUserInfoByUid == null){
			log.error(CustomRtnEnum.RESOURCE_NON_EXIST.toString());
			return new DataRtnDTO<>(CustomRtnEnum.RESOURCE_NON_EXIST.getStatus(),CustomRtnEnum.RESOURCE_NON_EXIST.getMsg(),simpleUserInfoByUid);
		}

		log.info(CustomRtnEnum.SUCCESS.toString());
		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),simpleUserInfoByUid);
	}

	public DTO changePassword(Long uid, String password) {
		log.info("修改密码为:{}",password);
    	// 正则表达式校验
		if (!Pattern.matches(PasswordRegex.CHAR_NUM_8, password)){
			log.error("密码不符合要求");
			throw new RuntimeException("密码不符合要求");
		}
		if (!userMapper.changePassword(uid,passwordEncoder().encode(password))){
			log.error("修改密码失败");
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(), CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}
		return new SimpleRtnDTO(CustomRtnEnum.SUCCESS.getStatus(), CustomRtnEnum.SUCCESS.getMsg());
	}




}
