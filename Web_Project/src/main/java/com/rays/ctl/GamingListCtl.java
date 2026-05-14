package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.GamingBean;
import com.rays.model.GamingModel;

@WebServlet("/GamingListCtl")
public class GamingListCtl extends HttpServlet {

	public GamingBean populateBean(HttpServletRequest request) {

		GamingBean bean = new GamingBean();

		bean.setCode(request.getParameter("code"));
		bean.setName(request.getParameter("name"));

		String prizePool = request.getParameter("prizePool");

		if (prizePool != null && prizePool.length() > 0) {
			bean.setPrizePool(Double.parseDouble(prizePool));
		}

		bean.setStatus(request.getParameter("status"));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GamingModel model = new GamingModel();
		GamingBean bean = populateBean(request);

		int pageNo = 1;
		int pageSize = 10;

		try {

			List<GamingBean> list = (List) model.search(bean, pageNo, pageSize);
			List<GamingBean> nextList = (List) model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("GamingListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		GamingModel model = new GamingModel();
		GamingBean bean = populateBean(request);

		int pageNo = 1;
		int pageSize = 10;

		String[] ids = request.getParameterValues("ids");

		if (op.equalsIgnoreCase("next")) {

			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;

		}

		if (op.equalsIgnoreCase("previous")) {

			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;

		}

		if (op.equalsIgnoreCase("delete")) {

			if (ids != null && ids.length > 0) {

				for (String id : ids) {

					GamingBean bean1 = new GamingBean();

					bean1.setId(Integer.parseInt(id));

					try {

						model.delete(bean1);

						request.setAttribute("successMsg", "Record Deleted Successfully");

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} else {

				request.setAttribute("errorMsg", "Select at least one record");

			}

		}

		try {

			List<GamingBean> list = (List) model.search(bean, pageNo, pageSize);
			List<GamingBean> nextList = (List) model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("GamingListView.jsp");
		rd.forward(request, response);

	}

}