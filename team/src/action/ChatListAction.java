package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ChetListService;
import vo.ActionForward;
import vo.ChatBean;

public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		ChetListService chatListService = new ChetListService();
		int listCount = chatListService.getListCount();
		
		ArrayList<ChatBean> articleList = new ArrayList<ChatBean>();
		
	
		
		
		
		
		
		forward = new ActionForward();
		forward.setPath("/Chat/ChatWriteForm.jsp");
		return forward;
	}

}
