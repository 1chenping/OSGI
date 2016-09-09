package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.gps.IGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private IAirbag airbag;
	
	private IGps gps;
	
	private EmergencyMonitor monitor;
	
	private ServiceReference<?> gpsref ;

	private ServiceReference<?> airbagref ;
	
	private ServiceTracker airbagTracker;
	
	private ServiceTracker gpsTracker;
	
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("Launching");
		monitor = new EmergencyMonitor();
		/**
		 * 第一版本服务：服务的注册和事件监听
		 */
		/*
		gpsref = context.getServiceReference(IGps.class.getName());

		airbagref = context.getServiceReference(IAirbag.class.getName());
		
		if(gpsref == null || airbagref == null){
			System.out.println("unable to require gps or airbag!");
			return;
		}
		gps = (IGps) context.getService(gpsref);
		airbag = (IAirbag) context.getService(airbagref);
		if(gps == null || airbag == null){
			System.out.println("unable to require gps or airbag!");
			return;
		}
		monitor.setAirbag(airbag);
		monitor.setGps(gps);
		monitor.startup();
		*/
		/**
		 * 第二版本服务：服务追踪器
		 */
	    ServiceTrackerCustomizer<IGps, Object> gpsCustomer = createGpsCustomer();
		gpsTracker = new ServiceTracker<IGps, Object>(context, IGps.class.getName(), gpsCustomer);
	    ServiceTrackerCustomizer<IAirbag, Object> airCustomer = createAirbagCustomer();
	    airbagTracker = new ServiceTracker<IAirbag, Object>(context, IAirbag.class.getName(), airCustomer);
	    gpsTracker.open();
	    airbagTracker.open();
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		monitor.shutdown();
		if(gpsref!=null){
			context.ungetService(gpsref);
		}
		if(airbagref!=null){
			context.ungetService(airbagref);
		}
		System.out.println("stoping");
	}
	
	private void bind(){
		if(gps == null){
			gps = (IGps) gpsTracker.getService();
			if(gps == null){
				return;
			}
		}
		if(airbag == null){
			airbag = (IAirbag) gpsTracker.getService();
			if(airbag == null){
				return;
			}
		}
		monitor.setGps(gps);
		monitor.setAirbag(airbag);
		monitor.startup();
	}
	

	protected void unbind() {
		if(gps ==null || airbag == null){
			return;
		}
		monitor.shutdown();
		gps = null;
		airbag = null;
	}
	
	private ServiceTrackerCustomizer<IAirbag, Object> createAirbagCustomer(){
		return new ServiceTrackerCustomizer<IAirbag, Object>() {

			@Override
			public Object addingService(ServiceReference<IAirbag> reference) {
				Object service = context.getService(reference);
				if(Activator.this.airbag == null){
					Activator.this.airbag =  (IAirbag) service;
					Activator.this.bind();
				}	
				return service;
			}

			@Override
			public void modifiedService(ServiceReference<IAirbag> reference, 
					Object service) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removedService(ServiceReference<IAirbag> reference, 
					Object service) {
				if(service != Activator.this.airbag){
					return;
				}
				Activator.this.unbind();
				Activator.this.bind();
			}
		};
	}
	
	private ServiceTrackerCustomizer<IGps, Object> createGpsCustomer(){
		return new ServiceTrackerCustomizer<IGps, Object>() {

			@Override
			public Object addingService(ServiceReference<IGps> reference) {
				Object service = context.getService(reference);
				if(Activator.this.gps == null){
					Activator.this.gps =  (IGps) service;
					Activator.this.bind();
				}	
				return service;
			}

			@Override
			public void modifiedService(ServiceReference<IGps> reference, Object service) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void removedService(ServiceReference<IGps> reference, Object service) {
				if(service != Activator.this.gps){
					return;
				}
				Activator.this.unbind();
				Activator.this.bind();
			}
		};
	}
		

}
