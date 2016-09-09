package org.equinoxosgi.toast.backend.emergency;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.equinoxosgi.toast.core.emergency.IEmergencyConstants;
public class EmergencyServlet extends HttpServlet{

	private static final long serialVersionUID = 1254058137169373802L;

	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request,HttpServletResponse response) 
			throws IOException, ServletException{
		String id = getParameter(request,response,"id");
		int latitude = NumberUtils.stringToInt(getParameter(request, response, IEmergencyConstants.LATITUDE_PARAMETER),0);
		int heading = NumberUtils.stringToInt(getParameter(request, response, IEmergencyConstants.HEANDING_PARAMETER),0);
		int longitude = NumberUtils.stringToInt(getParameter(request, response, IEmergencyConstants.LONGITUDE_PARAMETER),0);
		int speed = NumberUtils.stringToInt(getParameter(request, response, IEmergencyConstants.SPEED_PARAMETER),0);
		System.out.println("id:"+id+"latitude:"+latitude+"heading£º"+heading+"longitude:"+longitude+"speed:"+speed);
		PrintWriter print = response.getWriter();
		print.write("help is on this way");
	}

	private String getParameter(HttpServletRequest request,
			HttpServletResponse response, String object) 
					throws IOException, ServletException {
		String value = request.getParameter(object);
		if(StringUtils.isEmpty(value)){
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,object);
			throw new ServletException(object);
		}
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
