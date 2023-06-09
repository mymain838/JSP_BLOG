package blog.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import blog.config.DB;
import blog.domain.user.User;
import blog.domain.user.UserDao;
import blog.domain.user.dto.JoinReqDto;
import blog.domain.user.dto.LoginReqDto;
import blog.domain.user.dto.UserUpdateReqDto;

public class UserService {
	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public int 회원가입(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;

	

	}

	public User 로그인(LoginReqDto dto) {
			
		return userDao.findByUsernameAndPassword(dto);
		
	}

	public int 회원수정(UserUpdateReqDto dto) {

		return -1;

	}

	public int 유저네임중복체크(String username) {
		int result = userDao.usernameCheck(username);
		return result;
	}

}
