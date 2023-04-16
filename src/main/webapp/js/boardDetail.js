function addReply(data){

var replyItem = `<li id="reply-${data.id}" class="media">`;
	replyItem += `<div class="media-body">`;
	replyItem += `<strong class="text-primary">${data.userId}</strong>`;
	replyItem += `<p>${data.content}.</p></div>`;
	replyItem += `<div class="m-2">`;
	
	replyItem += `<i onclick="deleteReply(${data.id})" class="material-icons">delete</i></div></li>`;
	
	$("#reply__list").prepend(replyItem);
}

function deleteReply(id){
	
	alert("댓글 아이디: :" + id);
	
	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=delete&id="+id,
		dataType: "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			alert('성공');
			$("#reply-"+id).remove();
			//location.reload(); 페이지 리로드 (다시 그려줌)
			
		} else {
			alert('댓글삭제 실패');
		}
	});
}

function replySave(userId, boardId) {
	
	
	var data = {
		userId : userId,
		boardId : boardId,
		content: $("#content").val()
	}


	$.ajax({
		type: "post",
		url: "/blog/reply?cmd=save",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			addReply(result.data);
			$("#content").val("");
		} else {
			alert('댓글쓰기 실패');
		}
	});
}
function deleteById(boardId) {
	//요청과 응답을 json 으로 통일
	var data = {
		boardId: boardId //키 : 값
	}


	$.ajax({
		type: "post",
		url: "/blog/board?cmd=delete&id=" + boardId,
		dataType: "json"
	}).done(function(result) {
		if (result.statusCode == 1) {
			location.href = "index.jsp";
		} else {
			alert("삭제에 실패하였습니다.");
		}
	});
}