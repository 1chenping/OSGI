package org.equinoxosgi.toast.backend.emergency;

import javax.servlet.ServletException;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

public class Component {
	
	private HttpService http;

	private String servletAlias;
	
	public void setHttp(HttpService value){
		this.http = value;
	}
	
	protected void shutdown(){
		http.unregister(servletAlias);
	}
	
	protected void startup() {
		//TODO
		try {
			String servletRoot = "";
			EmergencyServlet servlet = new EmergencyServlet();
			http.registerServlet(servletAlias, servlet, null, null);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamespaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
