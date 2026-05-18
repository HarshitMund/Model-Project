package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.HostelBean;
import com.rays.model.HostelModel;

@WebServlet("/HostelCtl")
public class HostelCtl extends HttpServlet {

	public HostelBean populateBean(HttpServletRequest request) {

		HostelBean bean = new HostelBean();

		String op = request.getParameter("operation");

		if (op.equalsIgnoreCase("update")) {
			bean.setId(Integer.parseInt(request.getParameter("id")));
		}

		bean.setName(request.getParameter("name"));
		bean.setRooms(Integer.parseInt(request.getParameter("rooms")));
		bean.setWardenName(request.getParameter("wardenName"));
		bean.setFees(Double.parseDouble(request.getParameter("fees")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		HostelModel model = new HostelModel();

		if (id != null) {

			try {
				HostelBean bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("HostelView.jsp");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		HostelModel model = new HostelModel();

		if (op.equalsIgnoreCase("save")) {

			HostelBean bean = populateBean(request);

			try {

				model.add(bean);

				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Hostel Added Successfully");

			} catch (Exception e) {

				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Hostel Name Already Exists");
			}

		} else if (op.equalsIgnoreCase("update")) {

			int id = Integer.parseInt(request.getParameter("id"));

			HostelBean bean = populateBean(request);

			try {

				if (id > 0) {
					model.update(bean);
				}

				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Hostel Updated Successfully");

			} catch (Exception e) {

				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Hostel Name Already Exists");
			}

		} else if (op.equalsIgnoreCase("cancel")) {

			response.sendRedirect("HostelListCtl");
			return;

		} else if (op.equalsIgnoreCase("reset")) {

			response.sendRedirect("HostelCtl");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("HostelView.jsp");
		rd.forward(request, response);

	}

}