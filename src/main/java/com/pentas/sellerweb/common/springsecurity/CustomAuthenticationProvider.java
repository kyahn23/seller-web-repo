package com.pentas.sellerweb.common.springsecurity;

import com.pentas.sellerweb.common.dao.CmmnDao;
import com.pentas.sellerweb.common.module.util.CmmnUtil;
import com.pentas.sellerweb.common.module.util.DevMap;
import com.pentas.sellerweb.vo.UserVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	CmmnDao cmmnDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String userPw = authentication.getCredentials().toString();
        
        // 사용자 정보 가져오기
        DevMap userInfo = null;
        try {
        	userInfo = cmmnDao.selectOne("e1happy.member.getUserInfo", userId);
        } catch(RuntimeException ex) {
        	log.error(CmmnUtil.getExceptionMsg(ex));
        	throw new BadCredentialsException("로그확인이 필요합니다.");
        }
        if(userInfo == null || !StringUtils.equals(userPw, userInfo.getString("password"))) {
        	throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        
        UserVO userVO = new UserVO();
        userVO.setUsername(userId);
        userVO.setPassword(userPw);
        userVO.setNickname(userInfo.getString("nickname"));
        
        // 사용자 role(auth) 가져오기
        // List<String> authList = cmmnDao.selectList("com.pentas.member.getUserAuth", userId);
        // System.out.println(authList);
        
        // Collection<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
        // for(String auth : authList) {
        // 	auths.add(new SimpleGrantedAuthority(auth));
        // }
        // userVO.setAuthorities(auths);
		
		return new UsernamePasswordAuthenticationToken(userVO, userPw);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
