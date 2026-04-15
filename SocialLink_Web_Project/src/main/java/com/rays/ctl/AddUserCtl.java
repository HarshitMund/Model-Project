package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.SocialLinkBean;
import com.rays.model.SocialLinkModel;

@WebServlet("/AddUserCtl")
public class AddUserCtl extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SocialLinkModel model = new SocialLinkModel();
		SocialLinkBean bean = new SocialLinkBean();
		String id = req.getParameter("id");

		if (id != null) {
			try {
				bean = model.findByPk(Long.parseLong(id));
				req.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("SocialLinkView.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel model = new SocialLinkModel();

		String socialCode = req.getParameter("socialCode");
		String userName = req.getParameter("userName");
		String platform = req.getParameter("platform");
		String status = req.getParameter("status");

		try {
			bean.setSocialCode(socialCode);
			bean.setUserName(userName);
			bean.setPlatform(platform);
			bean.setStatus(status);
			model.add(bean);
			req.setAttribute("successMsg", "Social Link Added successfully");
		} catch (Exception e) {
			req.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("SocialLinkView.jsp");
		rd.forward(req, resp);

	}

}
