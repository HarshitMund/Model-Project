package com.rays.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.ParkingBean;
import com.rays.model.ParkingModel;

@WebServlet("/ParkingListCtl")
public class ParkingListCtl extends HttpServlet {

	public ParkingBean populateBean(HttpServletRequest request) {

		ParkingBean bean = new ParkingBean();

		bean.setCode(request.getParameter("code"));
		bean.setVehicleNumber(request.getParameter("vechile_number"));
		bean.setSlotNumber(request.getParameter("slot_number"));
		bean.setStatus(request.getParameter("status"));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParkingModel model = new ParkingModel();
		ParkingBean bean = populateBean(request);

		int pageNo = 1;
		int pageSize = 10;

		try {
			List<ParkingBean> list = (List) model.search(bean, pageNo, pageSize);
			List<ParkingBean> nextList = (List) model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ParkingListView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");

		ParkingModel model = new ParkingModel();
		ParkingBean bean = populateBean(request);

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
					ParkingBean bean1 = new ParkingBean();
					bean1.setId(Integer.parseInt(id));
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

		try {
			List<ParkingBean> list = (List) model.search(bean, pageNo, pageSize);
			List<ParkingBean> nextList = (List) model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("ParkingListView.jsp");
		rd.forward(request, response);
	}

}
