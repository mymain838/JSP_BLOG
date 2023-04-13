package blog.domain.reply;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	
	private int id;
	private int userid;
	private int boardId;
	private String content;
	private Timestamp createDate;

}
