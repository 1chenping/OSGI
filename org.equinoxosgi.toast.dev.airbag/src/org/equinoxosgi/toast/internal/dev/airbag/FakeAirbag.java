package org.equinoxosgi.toast.internal.dev.airbag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.equinoxosgi.toast.dev.airbag.IAirbag;
import org.equinoxosgi.toast.dev.airbag.IAirbagListener;

public class FakeAirbag implements IAirbag {

	private List<IAirbagListener> list;
	
	private Job job;
	
	private boolean isRunning;
	
	public FakeAirbag() {
		super();
		list = new ArrayList<IAirbagListener>();
	}
	
	@Override
	public synchronized void addListener(IAirbagListener listener){
		list.add(listener);
	}
	
	@Override
	public synchronized void removeListener(IAirbagListener listener){
		list.remove(listener);
	}
	
	public void deploy(){
		System.out.println("deploy~"+this.getClass().getName());
		for(Iterator<IAirbagListener> i = list.iterator();i.hasNext();){
			i.next().deployed();
		}
	}
	
	public synchronized void shutdown(){
		isRunning = false;
		job.cancel();
		try {
			job.join();
		} catch (InterruptedException e) {
			
		}
	}
	
	public synchronized void startup(){
		isRunning = true;
		job = new Job("FakeAirbag") {
			@Override
			protected IStatus run(IProgressMonitor arg0) {
				deploy();
				if(isRunning){
					schedule(5000);
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule(5000);
		}
}
