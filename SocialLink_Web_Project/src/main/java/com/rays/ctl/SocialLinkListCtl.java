package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.SocialLinkBean;
import com.rays.model.SocialLinkModel;

@WebServlet("/SocialLinkListCtl")
public class SocialLinkListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel model = new SocialLinkModel();
		int pageNo = 1;
		int pageSize = 10;

		try {
			List<SocialLinkBean> list = model.search(bean, pageNo, pageSize);
			List<SocialLinkBean> nextList = model.search(bean, pageNo + 1, pageSize);
			req.setAttribute("list", list);
			req.setAttribute("nextList", nextList);
			req.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("SocialLinkListView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("operation");
		SocialLinkBean bean = new SocialLinkBean();
		SocialLinkModel model = new SocialLinkModel();
		int pageNo = 1;
		int pageSize = 10;

		String[] ids = req.getParameterValues("ids");

		if (op.equals("delete")) {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					bean.setSocialId(Integer.parseInt(id));
					try {
						model.delete(bean);
						req.setAttribute("successMsg", "record deleted successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				req.setAttribute("errorMsg", "select at least one record");
			}
		}

		if (op.equals("next")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			pageNo--;
		}

		if (op.equals("search")) {
			bean.setUserName(req.getParameter("userName"));
			bean.setStatus(req.getParameter("status"));
		}

		try {
			List<SocialLinkBean> list = model.search(bean, pageNo, pageSize);
			List<SocialLinkBean> nextList = model.search(bean, pageNo + 1, pageSize);
			req.setAttribute("list", list);
			req.setAttribute("nextList", nextList);
			req.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("SocialLinkListView.jsp");
		rd.forward(req, resp);
	}

}
