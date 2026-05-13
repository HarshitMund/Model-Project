package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.ParkingBean;
import com.rays.model.ParkingModel;

@WebServlet("/ParkingCtl")
public class ParkingCtl extends HttpServlet {

	public ParkingBean populateBean(HttpServletRequest request) {

		ParkingBean bean = new ParkingBean();

		String op = request.getParameter("operation");
		if (op.equalsIgnoreCase("update")) {
			bean.setId(Long.parseLong(request.getParameter("id")));
		}
		bean.setCode(request.getParameter("code"));
		bean.setVehicleNumber(request.getParameter("vechile_number"));
		bean.setSlotNumber(request.getParameter("slot_number"));
		bean.setStatus(request.getParameter("status"));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		ParkingModel model = new ParkingModel();

		if (id != null) {
			try {
				ParkingBean bean = model.findByPk(Long.parseLong(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("ParkingView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		ParkingModel model = new ParkingModel();

		if (op.equalsIgnoreCase("save")) {
			ParkingBean bean = populateBean(request);
			try {
				model.add(bean);
				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Parking Slot Added");
			} catch (Exception e) {
				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Vechile Number already exist");
			}

		} else if (op.equalsIgnoreCase("update")) {
			long id = Long.parseLong(request.getParameter("id"));
			ParkingBean bean = populateBean(request);
			try {
				if (id > 0) {
					model.update(bean);
				}

				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "Parking Slot updated");
			} catch (Exception e) {
				request.setAttribute("bean", bean);
				request.setAttribute("errorMsg", "Vechile Number already exist");
			}

		} else if (op.equalsIgnoreCase("cancel")) {
			response.sendRedirect("ParkingListCtl");
			return;
		} else if (op.equalsIgnoreCase("reset")) {
			response.sendRedirect("ParkingCtl");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("ParkingView.jsp");
		rd.forward(request, response);

	}

}
