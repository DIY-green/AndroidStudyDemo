package com.cheng.mvcframestudy.asyncmvc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.SystemClock;

@ThreadSafe
public class Model {
	public interface Listener {
		void onModelStateUpdated(Model model);
	}
	
	private ModelData data = new ModelData(0);
	
	private final List<Listener> listeners = new ArrayList<Listener>();
	
	public Model() {
		
	}
	
	public final ModelData getData() {
		synchronized (this) {
			return data;
		}
	}
	
	public final void updateData() { // takes a while!
		SystemClock.sleep(5000);
		ModelData newData = new ModelData(new Random().nextInt(10) + 1);
		
		synchronized (this) {
			data = newData;
		}
		
		synchronized (listeners) {
			for (Listener listener : listeners) {
				listener.onModelStateUpdated(this);
			}
		}
	}
	
	public final void addListener(Listener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}
	
	public final void removeListener(Listener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}
}
