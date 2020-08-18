package com.pentas.sellerweb.common.conf;

import com.pentas.sellerweb.common.springsecurity.AuthFailureHandler;
import com.pentas.sellerweb.common.springsecurity.AuthSuccessHandler;
import com.pentas.sellerweb.common.springsecurity.CustomAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    CustomAuthenticationProvider authenticationProvider;
    
    @Autowired
    AuthFailureHandler authFailureHandler;
    
    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Override
	public void configure(WebSecurity web) throws Exception {
		// 허용되어야 할 경로들
		web.ignoring().antMatchers("/resources/**", 
								"/dist/**", 
								"/test/**",
								"/weather", 
								"/user/password/find", 
								"/user/join",
								"/user/email", 
								"/user/send/temppw", 
								"/findpw", 
								"/user/findpw", 
								"/user/cert/check", 
								"/join",
								"/getLanguage/**", 
								"/getMessage");
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
//          .headers().frameOptions().disable() // h2콘솔때문에 임시설정
//            	.and()
            .formLogin() // 로그인 페이지 및 성공 url, handler 그리고 로그인시 사용되는 id, password 파라미터 정의
	            .loginPage("/login") // 밑의 authorizeRequests 에 설정한 규칙에 어긋난경우 로그인페이지 호출 URL
	            .loginProcessingUrl("/loginproc") // 로그인처리시 호출할 URL
	            .defaultSuccessUrl("/afterLoginProc", true) // 로그인 성공후 이동할 URL
	            .failureUrl("/loginFail")  // 로그인 실패후 이동할 URL
//	            .successHandler(authSuccessHandler) // 로그인 성공시 처리할 핸들러(spring을 단지 api용의 백앤드개념으로만 사용할때 활용)
//	            .failureHandler(authFailureHandler) // 로그인 실패시 처리할 핸들러(spring을 단지 api용의 백앤드개념으로만 사용할때 활용)
	            .usernameParameter("userId") // 로그인페이지에서 로그인 ID 파라메타 명
	            .passwordParameter("userPw") // 로그인페이지에서 로그인 password 파라메타 명
	            .and()
            .logout()
            	.logoutSuccessUrl("/") // 로그아웃후 이동할 URL
            	.invalidateHttpSession(true)
            	.and()
            .authorizeRequests()
//              .antMatchers("/home", "/registration", "/error", "/blog/**", "/post/**", "/h2-console/**", "/webjars/**", "/css/**").permitAll()
//              .antMatchers("/newPost/**", "/commentPost/**", "/createComment/**").hasAnyRole("USER")
//              .anyRequest().authenticated()
//            	.antMatchers("/h2-console/**").permitAll() // h2콘솔때문에 임시설정
            	.antMatchers("/hsbnd/test2/**").authenticated()
           		.antMatchers("/mypage").authenticated()
           		.antMatchers("/**").permitAll()	
            	// .antMatchers("/board/**").authenticated()
            	.and()
            .authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void  configure(AuthenticationManagerBuilder auth) {
		auth.eraseCredentials(false);
	}

}