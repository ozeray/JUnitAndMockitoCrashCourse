package com.ahmet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServletTest {
	
	@Mock
	private HttpServletRequest request;
	
	@Mock
	private HttpServletResponse response;
	
	@Mock
	private RequestDispatcher requestDispatcher;

	@Test
	public void doGet() throws IOException {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(printWriter);
		new CouponServlet().doGet(request, response);
		assertEquals("SUPERSALE",stringWriter.toString());
	}

	@Test
	public void doPost() throws ServletException, IOException {
		when(request.getParameter("coupon")).thenReturn("SUPERSALE");
		when(request.getRequestDispatcher("response.jsp")).thenReturn(requestDispatcher);
		new CouponServlet().doPost(request, response);
		verify(request).setAttribute("discount", "Discount for coupon SUPERSALE is 50%");
		verify(requestDispatcher).forward(request, response);
	}

}