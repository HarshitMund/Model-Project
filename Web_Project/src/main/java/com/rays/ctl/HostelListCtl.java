package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.HostelBean;
import com.rays.model.HostelModel;

@WebServlet("/HostelListCtl")
public class HostelListCtl extends HttpServlet {

	public HostelBean populateBean(HttpServletRequest request) {

		HostelBean bean = new HostelBean();

		bean.setName(request.getParameter("name"));
		bean.setWardenName(request.getParameter("wardenName"));

		String rooms = request.getParameter("rooms");
		if (rooms != null && rooms.length() > 0) {
			bean.setRooms(Integer.parseInt(rooms));
		}

		String fees = request.getParameter("fees");
		if (fees != null && fees.length() > 0) {
			bean.setFees(Double.parseDouble(fees));
		}

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HostelModel model = new HostelModel();
		HostelBean bean = populateBean(request);

		int pageNo = 1;
		int pageSize = 10;

		try {

			List<HostelBean> list = (List<HostelBean>) model.search(bean, pageNo, pageSize);
			List<HostelBean> nextList = (List<HostelBean>) model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("HostelListView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		HostelModel model = new HostelModel();
		HostelBean bean = populateBean(request);

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

					HostelBean bean1 = new HostelBean();

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

			List<HostelBean> list = (List<HostelBean>) model.search(bean, pageNo, pageSize);
			List<HostelBean> nextList = (List<HostelBean>) model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("HostelListView.jsp");
		rd.forward(request, response);

	}

}