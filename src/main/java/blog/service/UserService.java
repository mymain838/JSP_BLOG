package blog.service;

import blog.domain.user.User;
import blog.domain.user.dto.JoinReqDto;
import blog.domain.user.dto.LoginReqDto;
import blog.domain.user.dto.UserUpdateReqDto;

public class UserService {
	
	//회원가입,수정,로그인,로그아웃, 아이디 중복체크
	
	public int 회원가입(JoinReqDto dto) {
		
		
		return -1; 
		
	}
	public User 로그인(LoginReqDto dto) {
		
		return null;
	}
	
	public int 회원수정(UserUpdateReqDto dto) {
		
		return -1;
		
	}
	public int 아이디중복체크(String username) {
		return -1;
	}
	

}
