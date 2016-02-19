package com.cheng.mvcframestudy.asyncmvc.controller;

import static com.cheng.mvcframestudy.asyncmvc.controller.ControllerProtocol.*;

import android.os.Message;

final class ReadyState implements ControllerState {

	private final Controller controller;
	
	public ReadyState(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public final boolean handleMessage(Message msg) {
		switch (msg.what) {
		case V_REQUEST_QUIT:
			onRequestQuit();
			return true;
		case V_REQUEST_UPDATE:
			onRequestUpdate();
			return true;
		case V_REQUEST_DATA:
			onRequestData();
			return true;
		}
		return false;
	}

	private void onRequestData() {
		// send the data to the outbox handlers (view)
		controller.notifyOutboxHandlers(C_DATA, 0, 0, controller.getModel().getData());
	}

	private void onRequestUpdate() {
		// we can't just call model.updateState() here because it will block
		// the inbox thread where this processing is happening.
		// thus we change the state to UpdatingState that will launch and manage
		// a background thread that will do that operation

		controller.changeState(new UpdatingState(controller));
	}

	private void onRequestQuit() {
		controller.quit();
	}
}
