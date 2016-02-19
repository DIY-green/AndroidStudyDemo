package com.cheng.mvcframestudy.asyncmvc.controller;

import static com.cheng.mvcframestudy.asyncmvc.controller.ControllerProtocol.*;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.cheng.mvcframestudy.asyncmvc.model.Model;
import com.cheng.utils.Logger;

public class Controller {
	
	private final Model model;
	
	private final HandlerThread inboxHandlerThread;
	private final Handler inboxHandler;
	private final List<Handler> outboxHandlers = new ArrayList<Handler>();
	
	private ControllerState state;
	
	public Controller(Model model) {
		Logger.TAG = "Controller";
		this.model = model;
		
		inboxHandlerThread = new HandlerThread("Controller Inbox"); // note you can also set a priority here
		inboxHandlerThread.start();
		 
		this.state = new ReadyState(this);

		inboxHandler = new Handler(inboxHandlerThread.getLooper()) {
			@Override
			public void handleMessage(Message msg) {
				Controller.this.handleMessage(msg);
			}
		};
	}
	
	public final void dispose() {
		// ask the inbox thread to exit gracefully
		inboxHandlerThread.getLooper().quit();
	}
	
	public final Handler getInboxHandler() {
		return inboxHandler;
	}
	
	public final void addOutboxHandler(Handler handler) {
		outboxHandlers.add(handler);
	}

	public final void removeOutboxHandler(Handler handler) {
		outboxHandlers.remove(handler);
	}
	
	final void notifyOutboxHandlers(int what, int arg1, int arg2, Object obj) {
		if (outboxHandlers.isEmpty()) {
			Logger.w(String.format("No outbox handler to handle outgoing message (%d)", what));
		} else {
			for (Handler handler : outboxHandlers) {
				Message msg = Message.obtain(handler, what, arg1, arg2, obj);
				msg.sendToTarget();
			}
		}
	}

	private void handleMessage(Message msg) {
		Logger.d("Received message: " + msg);
		
		if (! state.handleMessage(msg)) {
			Logger.w("Unknown message: " + msg);
		}
	}
	
	final Model getModel() {
		return model;
	}

	final void quit() {
		notifyOutboxHandlers(C_QUIT, 0, 0, null);
	}
	
	final void changeState(ControllerState newState) {
		Logger.d(String.format("Changing state from %s to %s", state, newState));
		state = newState;
	}
}
