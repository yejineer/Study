package controller.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.Community;

public class ViewCommunityController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	Community comm = null;
		UserManager manager = UserManager.getInstance();
		int commId = Integer.parseInt(request.getParameter("commId"));
		comm = manager.findCommunity(commId);		// 커뮤니티 정보 검색			
		
		request.setAttribute("community", comm);	// 커뮤니티 정보 저장				
		return "/community/view.jsp";				// 커뮤니티 보기 화면으로 이동
    }
}
