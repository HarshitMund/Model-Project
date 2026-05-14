package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.GamingBean;
import com.rays.model.GamingModel;

@WebServlet("/GamingCtl")
public class GamingCtl extends HttpServlet {

	public GamingBean populateBean(HttpServletRequest request) {

		GamingBean bean = new GamingBean();

		String op = request.getParameter("operation");

		if (op.equalsIgnoreCase("update")) {
			bean.setId(Long.parseLong(request.getParameter("id")));
		}

		bean.setCode(request.getParameter("code"));
		bean.setName(request.getParameter("name"));
		bean.setPrizePool(Double.parseDouble(request.getParameter("prizePool")));
		bean.setStatus(request.getParameter("status"));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		GamingModel model = new GamingModel();

		if (id != null) {

			try {
				GamingBean bean = model.findByPk(Long.parseLong(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("GamingView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		GamingModel model = new GamingModel();

		if (op.equalsIgnoreCase("save")) {

			GamingBean bean = populateBean(request);

			try {

				model.add(bean);

				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Tournament Added Successfully");

			} catch (Exception e) {

				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Tournament Code Already Exists");
			}

		} else if (op.equalsIgnoreCase("update")) {

			long id = Long.parseLong(request.getParameter("id"));

			GamingBean bean = populateBean(request);

			try {

				if (id > 0) {
					model.update(bean);
				}

				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Tournament Updated Successfully");

			} catch (Exception e) {

				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Tournament Code Already Exists");
			}

		} else if (op.equalsIgnoreCase("cancel")) {

			response.sendRedirect("GamingListCtl");
			return;

		} else if (op.equalsIgnoreCase("reset")) {

			response.sendRedirect("GamingCtl");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("GamingView.jsp");
		rd.forward(request, response);

	}

}