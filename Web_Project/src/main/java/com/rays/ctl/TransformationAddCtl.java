package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.TransformationBean;
import com.rays.model.TransformationModel;

@WebServlet("/TransformationAddCtl")
public class TransformationAddCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TransformationBean bean = new TransformationBean();
		TransformationModel model = new TransformationModel();
		String id = request.getParameter("id");

		if (id != null) {
			try {
				bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("TransformationAddView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		TransformationBean bean = new TransformationBean();
		TransformationModel model = new TransformationModel();

		try {
			bean.setCode(request.getParameter("code"));
			bean.setName(request.getParameter("name"));
			bean.setLogic(request.getParameter("logic"));
			bean.setStatus(request.getParameter("status"));

			if (op.equalsIgnoreCase("update")) {
				bean.setId(Integer.parseInt(request.getParameter("id")));
				model.update(bean);
				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Transformation Updated successfully");
			} else {
				model.add(bean);
				request.setAttribute("successMsg", "Transformation added successfully.");

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("TransformationAddView.jsp");
		rd.forward(request, response);

	}

}
