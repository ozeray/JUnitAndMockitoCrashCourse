package com.ahmet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/coupon")
public class CouponServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = -4107172832802398477L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().print("SUPERSALE");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String coupon = request.getParameter("coupon");
		request.setAttribute("discount", "Discount for coupon " + coupon + " is 50%");
		request.getRequestDispatcher("response.jsp").forward(request, response);
	}
}
