package com.rays.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.JobBean;
import com.rays.model.JobModel;

@WebServlet("/JobAddCtl")
public class JobAddCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JobModel model = new JobModel();
		JobBean bean = new JobBean();
		String id = request.getParameter("jobId");

		if (id != null) {
			try {
				bean = model.findByPk(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("JobAddView.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		JobBean bean = new JobBean();
		JobModel model = new JobModel();

		String jobCode = request.getParameter("jobCode");
		String jobName = request.getParameter("jobName");
		String cronExpresion = request.getParameter("cronExpression");
		String status = request.getParameter("status");

		try {
			bean.setJobCode(jobCode);
			bean.setJobName(jobName);
			bean.setCronExpression(cronExpresion);
			bean.setStatus(status);

			if (op.equalsIgnoreCase("update")) {
				bean.setJobId(Integer.parseInt(request.getParameter("id")));
				model.update(bean);
				request.setAttribute("bean", bean);
				request.setAttribute("successMsg", "User Updated Successfully");
			} else {
				model.add(bean);
				request.setAttribute("successMsg", "Job Added Successfully");
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("JobAddView.jsp");
		rd.forward(request, response);
	}
}
