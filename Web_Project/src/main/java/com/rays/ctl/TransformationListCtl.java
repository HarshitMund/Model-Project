package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.TransformationBean;
import com.rays.model.TransformationModel;

@WebServlet("/TransformationListCtl")
public class TransformationListCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TransformationBean bean = new TransformationBean();
		TransformationModel model = new TransformationModel();
		int pageNo = 1;
		int pageSize = 10;

		try {
			List<TransformationBean> list = model.search(bean, pageNo, pageSize);
			List<TransformationBean> nextList = model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("TransformationListView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		TransformationBean bean = new TransformationBean();
		TransformationModel model = new TransformationModel();
		int pageSize = 10;
		int pageNo = 1;

		String ids[] = request.getParameterValues("ids");

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
					TransformationBean transformationBean = new TransformationBean();
					transformationBean.setId(Integer.parseInt(id));

					try {
						model.delete(transformationBean);
						request.setAttribute("successMsg", "Record deleted successfully");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				request.setAttribute("errorMsg", "Select atleast one record");
			}
		}

		if (op.equalsIgnoreCase("search")) {
			bean.setName(request.getParameter("name"));
		}

		try {
			List<TransformationBean> list = model.search(bean, pageNo, pageSize);
			List<TransformationBean> nextList = model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("TransformationListView.jsp");
		rd.forward(request, response);

	}

}
