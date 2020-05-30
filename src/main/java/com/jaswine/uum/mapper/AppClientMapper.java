package com.jaswine.uum.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanswon.commons.web.auth.pojo.OauthClientDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppClientMapper extends BaseMapper<OauthClientDetails> {

}
