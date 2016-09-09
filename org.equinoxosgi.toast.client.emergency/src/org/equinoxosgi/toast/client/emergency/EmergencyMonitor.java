package org.equinoxosgi.toast.client.emergency;

import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;
import org.equinoxosgi.toast.dev.gps.IGps;

public class EmergencyMonitor implements IAirbagListener{

	private IGps gps;
	
	private IAirbag airbag;

	
	public void startup(){
		airbag.addListener(this);
	}
	
	public void shutdown(){
		airbag.removeListener(this);
	}
	
	public IGps getGps() {
		return gps;
	}

	public void setGps(IGps gps) {
		this.gps = gps;
	}

	public IAirbag getAirbag() {
		return airbag;
	}

	public void setAirbag(IAirbag airbag) {
		this.airbag = airbag;
	}

	@Override
	public void deployed() {
		System.out.println(String.format("emergency at heanding : %s,lat:%s,long:%s,speed:%s", 
				gps.getHeanding(),gps.getLatitude(),gps.getLongitude(),gps.getSpeed()));
	}
	
}
