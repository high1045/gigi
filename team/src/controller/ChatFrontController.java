package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import action.Action;
import action.ChatDeleteFormAction;
import action.ChatDeleteProAction;
import action.ChatListAction;
import action.ChatWriteProAction;
import vo.ActionForward;


@WebServlet("*.ch")
public class ChatFrontController extends HttpServlet {
	
	
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//사용자 입력 문자 인코딩
    	request.setCharacterEncoding("utf-8");
    	//사용자 응답 웹페이지 문자 인코딩
    	
    	
    	String commend = request.getServletPath();
    	System.out.println("요청 서블릿 주소 :" + commend);
    	String chat_creator_id = request.getParameter("chat_creator_id");
		String chat_editor_id = request.getParameter("chat_editor_id");
		if(chat_creator_id == null || chat_editor_id.trim().length() == 0){
	    response.sendRedirect("/chat/Chatting.jsp");
	   
	    
    	
    	
    	Action action = null;
    	
    	ActionForward forward =null;
    	
    	if(commend.equals("/ChatWriteForm.ch")) {
    		
    		forward = new ActionForward();
    		
    		forward.setPath("/Chat/ChatWriteForm.jsp");
    		
            }else if(commend.equals("/ChatWritePro.ch")) {
    		
    		action = new ChatWriteProAction(); 
    			
    		try {	
    			forward = action.execute(request,response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}		
    		
            }else if (commend.equals("/ChatListPro.ch")){
				action = new ChatListAction();
				
			try {
			forward = action.execute(request, response);
		    } catch (Exception e) {
				e.printStackTrace();
		    }	
				
		    } else if(commend.equals("/ChatDeleteForm.ch")) {
				action = new ChatDeleteFormAction();
				
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
					
				e.printStackTrace();
			}
				
		    }else if(commend.equals("/ChatDeletePro.ch")) {
		    	action = new ChatDeleteProAction();
		    	
		    try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}	
		}
    		
    			
//====================================================================================================    	
    		if(forward != null) {
    			
    			if(forward.isRedirect()) {
    				response.sendRedirect(forward.getPath());
    				
    			}else {
    				
    				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    				
    				dispatcher.forward(request, response);
    				
    			}
    		
    		 }
    		}
    		//=========================================================================================
    	
    		}
            
	
	
	
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doprocess(request, response);
		
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doprocess(request, response);
		
	}

}