package org.corporateforce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.corporateforce.client.port.AbstractPort;
import org.corporateforce.helper.PojoToHtml;
import org.corporateforce.helper.RestHelper;

/**
 * Servlet implementation class users
 */
public class tester extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public tester() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String service = request.getParameter("service");
		Class<?> serviceclass = null;
		try {
			serviceclass = Class.forName("org.corporateforce.server.model."
					+ service);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		String action = request.getParameter("action");
		if (action != null && action.equals("list")) {
			List<?> entities = RestHelper.convertToList(
					AbstractPort.list(service), serviceclass);
			for (Object entity : entities) {
				out.write(PojoToHtml.dump(entity) + ".<br/>");
			}
		} else if (action != null && action.equals("get")) {
			String id = request.getParameter("id");
			if (id != null) {
				Object entity = RestHelper.convertToEntity(
						AbstractPort.get(service, Integer.valueOf(id)),
						serviceclass);
				out.write(PojoToHtml.dump(entity) + ".<br/>");
			}
		}
	}
}
