package blog.domain.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blog.config.DB;
import blog.domain.board.dto.SaveReqDto;
import blog.domain.user.User;

public class BoardDao {

	public int save(SaveReqDto dto) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO board(userId, title, content, createDate)VALUES(?,?,?,now())";
		Connection conn= DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			return result;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt);
		}
		return -1;
	}

	public List<Board> findAll(int page) {
		String sql = "SELECT * FROM board ORDER BY id DESC LIMIT ?, 4";
		Connection conn = DB.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Board> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, page*4); //0-> 0 1->4 2->8
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = Board.builder()
						.id(rs.getInt("id"))
						.userId(rs.getInt("userId"))
						.title(rs.getString("title"))
						.content(rs.getString("content"))
						.readCount(rs.getInt("readCount"))
						.createDate(rs.getTimestamp("createDate"))
						.build();
				boards.add(board);
			}
			return boards;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			DB.close(conn, pstmt, rs);
			
		}
		return null;
	}

	public int count() {
		String sql = "SELECT count(*)FROM board";
		
		Connection conn = DB.getConnection();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<Board> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			DB.close(conn, pstmt, rs);
		}
		return -1;
	}
	
	

}
