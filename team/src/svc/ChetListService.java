package svc;

import static db.JdbcUtill.close;
import static db.JdbcUtill.getConnection;

import java.sql.Connection;

import dao.ChatDAO;

public class ChetListService {

	public int getListCount() throws Exception {
		
		int listCount = 0;
		
		// 1(공통). Connection 객체 가져오기
		Connection con = getConnection();
				
		// 2(공통). BoardDAO 객체 가져오기
		ChatDAO chatDAO = ChatDAO.getInstance();
				
		// 3(공통). BoardDAO 객체에 Connection 객체 전달
		chatDAO.setConnection(con);
				
		// 4. BoardDAO 객체의 selectListCount() 메서드 호출하여
		//    전체 게시물 수 가져오기
		listCount = chatDAO.selectListCount();
				
		// 5(공통). Connection 객체 반환하기
		close(con);
				
		// 6. 전체 게시물 수 리턴
		return listCount;
		
	}
	
	
	

}
