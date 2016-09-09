package org.equinoxosgi.toast.internal.dev.gps.fake;

import org.equinoxosgi.toast.dev.gps.IGps;

public class FakeGps implements IGps {

	/* (non-Javadoc)
	 * @see org.equinoxosgi.toast.dev.gps.IGps#getHeanding()
	 */
	@Override
	public int getHeanding(){
		return 90;
	}
	
	/* (non-Javadoc)
	 * @see org.equinoxosgi.toast.dev.gps.IGps#getLatitude()
	 */
	@Override
	public int getLatitude(){
		return 37373737;
	}
	
	/* (non-Javadoc)
	 * @see org.equinoxosgi.toast.dev.gps.IGps#getLongitude()
	 */
	@Override
	public int getLongitude(){
		return -1221;
	}
	
	/* (non-Javadoc)
	 * @see org.equinoxosgi.toast.dev.gps.IGps#getSpeed()
	 */
	@Override
	public int getSpeed(){
		return 50;
	}
}
