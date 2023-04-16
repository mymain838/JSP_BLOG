package blog.service;

import java.util.List;

import blog.domain.board.Board;
import blog.domain.board.BoardDao;
import blog.domain.board.dto.DetailRespDto;
import blog.domain.board.dto.SaveReqDto;
import blog.domain.board.dto.UpdateReqDto;

public class BoardService {
	private BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int 글개수() {
		
		return boardDao.count();
	}
	//오버로드
	public int 글개수(String keyword) {
	
		return boardDao.count(keyword);
	}

	public int 글쓰기(SaveReqDto dto) {
		
		int result = boardDao.save(dto);
		return result;
	}

	public List<Board> 글목록보기(int page) {
		
		return boardDao.findAll(page);
	}
//하나의 서비스안에 여러가지 DB관련 로직이 섞여있음 - 여기서 처리
	public DetailRespDto 글상세보기(int id) {
		int result = boardDao.updateReadCount(id);
		if(result == 1) {
			return boardDao.findById(id);
		}
		else {
			return null;
		}
		
	}
	public int 글삭제(int id) {
		return boardDao.deleteById(id);
	}

	public int 글수정(UpdateReqDto dto) {
		return boardDao.update(dto);
	}
	public List<Board> 글검색(String keyword, int page){
		return boardDao.findByKeyword(keyword, page);
		
		
	}



}
