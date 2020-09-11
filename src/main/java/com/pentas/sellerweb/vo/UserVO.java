package com.pentas.sellerweb.vo;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails {
	
	private static final long serialVersionUID = -513243367094871788L;

	/* 회원 아이디 */
	private String bnMbrId;

	/* 비밀번호 */
	private String pwdNo;

	/* 입력된 비밀번호 */
	private String inputPwdNo;

	/* 회원 이름 */
	private String mbrNm;

	/* 비밀번호 초기화 여부 */
	private String pwnoInitYn;

	/* 비밀번호 오류 횟수 */
	private String pwerrCnt;

	/* 로그인 가능 여부 */
	private String lginAvlYn;

	/* 퇴사 회원 여부 */
	private String actvYn;

	/* 계정이 가지고 있는 권한목록 */
	private Collection<SimpleGrantedAuthority> authorities;

	/**
	* GETTER & SETTER
	*/

	public String getBnMbrId() { return bnMbrId; }

	public void setBnMbrId(String bnMbrId) { this.bnMbrId = bnMbrId; }

	public String getPwdNo() { return pwdNo; }

	public void setPwdNo(String pwdNo) { this.pwdNo = pwdNo; }

	public String getInputPwdNo() { return inputPwdNo; }

	public void setInputPwdNo(String inputPwdNo) { this.inputPwdNo = inputPwdNo; }

	public String getMbrNm() { return mbrNm; }

	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}

	public String getPwnoInitYn() { return pwnoInitYn; }

	public void setPwnoInitYn(String pwnoInitYn) { this.pwnoInitYn = pwnoInitYn; }

	public String getPwerrCnt() { return pwerrCnt; }

	public void setPwerrCnt(String pwerrCnt) { this.pwerrCnt = pwerrCnt; }

	public String getLginAvlYn() { return lginAvlYn; }

	public void setLginAvlYn(String lginAvlYn) { this.lginAvlYn = lginAvlYn; }

	public String getActvYn() { return actvYn; }

	public void setActvYn(String actvYn) { this.actvYn = actvYn; }

	/**
	 * UserDetails 구현 메소드
	 */

	@Override
	public String getUsername() {
		return bnMbrId;
	}

	@Override
	public String getPassword() {
		return pwdNo;
	}

	/**
	 * 계정이 만료되지 않았는지를 리턴(true를 리턴하면 만료되지 않음을 의미)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true; // 체크를 하지 않겠다는 의미
	}

	/**
	 * 계정이 잠겨있지 않은지를 리턴(true를 리턴하면 계정이 잠겨있지 않음을 의미)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return false; // 체크를 하지 않겠다는 의미
	}

	/**
	 * 계정의 패스워드가 만료되지 않았는지를 리턴(true를 리턴하면 패스워드가 만료되지 않음을 의미)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return false; // 체크를 하지 않겠다는 의미
	}

	/**
	 * 계정이 사용가능한 계정인지를 리턴(true를 리턴하면 사용가능한 계정을 의미)
	 */
	@Override
	public boolean isEnabled() {
		return false; // 체크를 하지 않겠다는 의미
	}
}
