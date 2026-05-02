package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.JobBean;
import com.rays.bean.UserBean;
import com.rays.model.JobModel;

@WebServlet("/JobListCtl")
public class JobListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JobBean bean = new JobBean();
		JobModel model = new JobModel();
		int pageNo = 1;
		int pageSize = 10;

		try {
			List<JobBean> list = model.search(bean, pageNo, pageSize);
			List<JobBean> nextList = model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("JobListView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		JobBean bean = new JobBean();
		JobModel model = new JobModel();
		int pageNo = 1;
		int pageSize = 10;

		String[] ids = request.getParameterValues("ids");

		if (op.equalsIgnoreCase("delete")) {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					JobBean bean1 = new JobBean();
					bean1.setJobId(Integer.parseInt(id));
					try {
						model.delete(bean1);
						request.setAttribute("successMsg", "Record Deleted Successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "Select at least on record");
			}
		}

		if (op.equals("next")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}

		if (op.equals("previous")) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}

		try {
			List<JobBean> list = model.search(bean, pageNo, pageSize);
			List<JobBean> nextList = model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("JobListView.jsp");
		rd.forward(request, response);
		;

	}

}
