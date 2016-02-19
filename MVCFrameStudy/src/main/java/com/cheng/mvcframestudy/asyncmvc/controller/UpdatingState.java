package com.cheng.mvcframestudy.asyncmvc.controller;

import static com.cheng.mvcframestudy.asyncmvc.controller.ControllerProtocol.*;
import android.os.Message;
import android.util.Log;

final class UpdatingState implements ControllerState {

	private static final String TAG = UpdatingState.class.getSimpleName();
	
	private final Controller controller;
	private final Thread updateThread; 
	
	public UpdatingState(Controller controller) {
		this.controller = controller;

		// Remember, the model is thread-safe in our example so we can modify
		// it from multiple threads
		updateThread = new Thread("Model Update") {
			@Override
			public void run() {
				Controller controller = UpdatingState.this.controller;
				try {
					controller.getModel().updateData();
				} catch (Throwable t) {
					Log.e(TAG, "Error in the update thread", t);
				} finally {
					notifyControllerOfCompletion();
				}
			}
		};
		updateThread.start();
		controller.notifyOutboxHandlers(C_UPDATE_STARTED, 0, 0, null);
	}
	
	private void notifyControllerOfCompletion() {
		// this method will be called from the background thread.
		// avoid Controller synchronization - do this in the inbox thread
		// by using Handler.post()
		controller.getInboxHandler().post(new Runnable() {
			@Override
			public void run() {
				controller.changeState(new ReadyState(controller));
				controller.notifyOutboxHandlers(C_UPDATE_FINISHED, 0, 0, null);
			}
		});		
	}
	
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case V_REQUEST_QUIT:
			onRequestQuit();
			return true;
		}
		// ignore all other messages
		return false;
	}

	private void onRequestQuit() {
		updateThread.interrupt();
		controller.quit();
	}
}
