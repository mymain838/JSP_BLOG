package blog.domain.board.dto;

import lombok.Data;

@Data
public class CommonRespDto<T> {
	private int statusCode; //1: 정상 -1: 실패
	private T data; 

}
