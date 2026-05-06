package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.MappingBean;
import com.rays.model.MappingModel;

@WebServlet("/MappingCtl")
public class MappingCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("MappingView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		MappingBean bean = new MappingBean();
		MappingModel model = new MappingModel();

		try {
			bean.setCode(request.getParameter("code"));
			bean.setSourceField(request.getParameter("sourceField"));
			bean.setTargetField(request.getParameter("targetField"));
			bean.setStatus(request.getParameter("status"));

			model.add(bean);
			request.setAttribute("successMsg", "Data Mapping is Added");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("MappingView.jsp");
		rd.forward(request, response);

	}

}
