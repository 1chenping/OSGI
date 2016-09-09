package org.equinoxosgi.toast.dev.airbag;

import org.equinoxosgi.toast.internal.dev.airbag.FakeAirbag;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	private static BundleContext context;

	private FakeAirbag airbag;
	
	private ServiceRegistration<?>  registration;
	
	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		airbag = new FakeAirbag();
		airbag.startup();
		registration = context.registerService(
				IAirbag.class.getName(), airbag, null);
				
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		registration.unregister();
		airbag.shutdown();
	}

}
