package blog.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.config.DB;
import blog.domain.user.dto.JoinReqDto;
import blog.domain.user.dto.LoginReqDto;

public class UserDao {

	public int save(JoinReqDto dto) { // 회원가입
		String sql = "INSERT INTO user(username,password,email,address,userRole,createDate) VALUES(?,?,?,?,'USER',now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddress());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}

	public void update() { // 회원수정

	}

	public int usernameCheck(String username) { // 아이디 중복 체크
		String sql = "SELECT * FROM user WHERE username= ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1; //존재
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return -1;

	}

	public void finByid() { // 회원정보보기

	}

	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id,username,email,address FROM user WHERE username = ? And password =?";
		Connection conn = DB.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUsername());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.username(rs.getString("username"))
						.email(rs.getString("email"))
						.address(rs.getString("address"))
						.build();
				return user;
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DB.close(conn, pstmt, rs);
			
		}
		return null;

	}
	

}
