package org.equinoxosgi.toast.dev.airbag;

public interface IAirbag {

	void removeListener(IAirbagListener listener);

	void addListener(IAirbagListener listener);

}