package svc;

import java.sql.Connection;

import dao.ChatDAO;
import vo.ChatBean;

import static db.JdbcUtill.*;

public class ChatWriteProService {
		
	public boolean registChat(ChatBean chatBean) {
     System.out.println("ChatWriteProService - registChat()");
		
		boolean isWriteSuccess = false;
		
		Connection con = getConnection();
		
		ChatDAO chatDAO = ChatDAO.getInstance();
		
		chatDAO.setConnection(con);
		
		int insertCount = ChatDAO.insertArticle(chatBean);
		
		if(insertCount > 0) {
        	commit(con);
        	isWriteSuccess = true;
        }else{
        	rollback(con);
        }
		
	        
	   close(con);
	   return isWriteSuccess;
       
	}

}