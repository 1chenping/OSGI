package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.gps.IGps;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ActivatorOne implements BundleActivator {

	private static BundleContext context;

	//private FakeAirbag airbag;
	
	private IGps gps;
	
	private EmergencyMonitor monitor;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		ActivatorOne.context = bundleContext;
		/*
		System.out.println("Launching");
		gps = new FakeGps();
		airbag = new FakeAirbag();
		monitor = new EmergencyMonitor();
		monitor.setAirbag(airbag);
		monitor.setGps(gps);
		monitor.startup();
		airbag.deploy();
		*/
	}

	
	public void stop(BundleContext bundleContext) throws Exception {
		ActivatorOne.context = null;
		monitor.shutdown();
		System.out.println("stoping");
	}

}
