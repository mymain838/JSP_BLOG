package blog.domain.board;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	private int id;
	private int userId;
	private String title;
	private String content;
	private String readCount;
	private Timestamp createDate;
}
