package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.ChatBean;

import static db.JdbcUtill.*;


public class ChatDAO {

	
	private static ChatDAO instance = new ChatDAO();
	
	private ChatDAO() {}


	public static ChatDAO getInstance() {
		return instance;
	}
	
//===============================================================================	
	
    static Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	
	}
	
//--------------------------------------insert----------------------------------
//게시물 상세 내용을 조회	
 public static int insertArticle(ChatBean chatBean) {
  
	int insertCount = 0; //INSERT 작업수행결과를 저장할 변수
		
    PreparedStatement pstmt = null;
		
    ResultSet rs = null;
		
    int num=1; 
		
		//현재 게시물 번호(board_num)중 가장 큰 번호를 조회하여
		//해당 번호 + 1 값을 새글 번호 (num)으로 저장
    String sql = "SELECT MAX(board_id) FROM chat";
	try {
		pstmt = con.prepareStatement(sql);
		rs= pstmt.executeQuery();
			
			//조회된 결과가 있을 경우 조회된 번호 +1 값을 num에 저장
			//=> 조회 결과가 없을경우 새글 번호는 1번이므로 기본값 그대로 사용
		if(rs.next()) {
		  num=rs.getInt(1)+1; //새글번호만들기
	}
	}catch (Exception e) {
		e.printStackTrace();
	}
		
		try {
				
		 sql = "INSERT INTO Chat VALUES (?,?,?,?,?,?)";
	     pstmt = con.prepareStatement(sql);
					
		 pstmt.setInt(1, chatBean.getChat_id());
		 pstmt.setString(2, chatBean.getChat_editor_id());
		 pstmt.setString(3, chatBean.getChat_creator_id());
		 pstmt.setString(4, chatBean.getChat_content());
		 pstmt.setTimestamp(5, chatBean.getChat_date());
		 pstmt.setInt(6, chatBean.getBoard_id());
				
		insertCount = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("insertChat 오류!" + e.getMessage());
			e.printStackTrace();
		}finally {
			
			close(rs);
			close(pstmt);
				
		}   

		return insertCount; 
  }

//조회수 증가========================================================================
	
public int selectListCount() {
	int listCount = 0;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		String sql = "select count(chat_num) from chat";
		pstmt = con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			listCount = rs.getInt(1);
		}
		
	} catch (SQLException e) {
		System.out.println("selectListCount()오류-" + e.getMessage());
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
		
		
	}
	
	
	return listCount;
  }

//==================================================================
public boolean isArticleChatWriter(String chat_editor_id, String chat_creator_id) {
	boolean isArticleWriter = false;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		String sql = "select chat_editor_id from chat where chat_id?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, chat_editor_id);
		pstmt.setString(2, chat_creator_id);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			if(chat_editor_id.equals(rs.getString("chat_editor_id"))) {
				isArticleWriter = false;
			}
		}
		
		
	} catch (SQLException e) {
		System.out.println("isArticleChatWriter()오류 - " + e.getMessage());
		
		e.printStackTrace();
	}finally {
		close(rs);
		close(pstmt);
	}
	
	
	return isArticleWriter;
}
 
}

//===========================================================================
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 