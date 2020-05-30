package com.jaswine.uum.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lanswon.commons.core.serial.SerialEnum;
import com.lanswon.commons.core.serial.SerialNo;
import com.lanswon.commons.web.auth.pojo.OauthClientDetails;
import com.lanswon.commons.web.custom.dto.DTO;
import com.lanswon.commons.web.custom.dto.DataRtnDTO;
import com.lanswon.commons.web.custom.dto.SimpleRtnDTO;
import com.lanswon.commons.web.custom.rtn.CustomRtnEnum;
import com.lanswon.uum.bean.DataBaseConstants;
import com.lanswon.uum.mapper.AppClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * APP CLIENT SERVICE
 *
 * @author jaswine
 */
@Service
@Slf4j
public class AppClientService {

	@Resource
	private AppClientMapper appClientMapper;
	@Resource
	private PasswordEncoder passwordEncoder;

	/**
	 * 注册APP(在OAUTH2系统中)
	 * @param clientDetails APP信息
	 * @return
	 */
	public DTO registerAppClient(OauthClientDetails clientDetails) {
		log.info("注册APP:{}",clientDetails.getClientId());
		clientDetails.setClientSecret(passwordEncoder.encode(SerialNo.builder(SerialEnum.PREFIX_TIME_YEAR).withPrefix("cloud").generateSerial()));

		if (appClientMapper.insert(clientDetails) == 0){
			log.error(CustomRtnEnum.ERROR_BAD_SQL.toString());
			return new SimpleRtnDTO(CustomRtnEnum.ERROR_BAD_SQL.getStatus(),CustomRtnEnum.ERROR_BAD_SQL.getMsg());
		}

		log.info(CustomRtnEnum.SUCCESS.toString());

		Wrapper<OauthClientDetails> wrapper = new QueryWrapper<OauthClientDetails>().eq(DataBaseConstants.OAUTH_CLIENT_DEATILS_CLIENT_ID, clientDetails.getClientId());

		OauthClientDetails details = appClientMapper.selectOne(wrapper);

		return new DataRtnDTO<>(CustomRtnEnum.SUCCESS.getStatus(),CustomRtnEnum.SUCCESS.getMsg(),details);
	}
}
