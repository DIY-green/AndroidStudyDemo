package com.cheng.mvcframestudy.asyncmvc.view;

import static com.cheng.mvcframestudy.asyncmvc.controller.ControllerProtocol.*;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cheng.mvcframestudy.R;
import com.cheng.mvcframestudy.asyncmvc.controller.Controller;
import com.cheng.mvcframestudy.asyncmvc.model.Model;
import com.cheng.mvcframestudy.asyncmvc.model.ModelData;
import com.cheng.utils.Logger;

/**
 * 基于消息的 MVC
 * 参考
 * http://blog.csdn.net/binyao02123202/article/details/7733835
 */
public class AsyncMVCDemoActivity extends Activity implements Handler.Callback {
	
	private TextView mDataTV;
	private ProgressBar mLoadingPB;
	
	private Controller mController;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyncmvcmemo);

		Logger.TAG = "AsyncMVCDemoActivity";
		initView();
		initController();

    }

	private void initView() {
		mDataTV = (TextView) findViewById(R.id.tv_data);
		mLoadingPB = (ProgressBar) findViewById(R.id.pb_loading);
	}

	private void initController() {
		mController = new Controller(new Model());
		mController.addOutboxHandler(new Handler(this)); // messages will go to .handleMessage()
		mController.getInboxHandler().sendEmptyMessage(V_REQUEST_DATA); // request initial data
	}

	@Override
	protected void onDestroy() {
		// I think it is a good idea to not fail in onDestroy()
		try {
			mController.dispose();
		} catch (Throwable t) {
			Logger.e("Failed to destroy the mController", t);
		} 
		super.onDestroy();
	}

	@Override
	public boolean handleMessage(Message msg) {
		Logger.d("Received message: " + msg);
		switch (msg.what) {
		case C_QUIT:
			onQuit();
			return true;
		case C_DATA:
			onData((ModelData) msg.obj);
			return true;
		case C_UPDATE_STARTED:
			onUpdateStarted();
			return true;
		case C_UPDATE_FINISHED:
			onUpdateFinished();
			return true;
		}
		return false;
	}

	private void onUpdateStarted() {
		mLoadingPB.setVisibility(View.VISIBLE);
	}

	private void onUpdateFinished() {
		mLoadingPB.setVisibility(View.GONE);
		// request the updated data
		mController.getInboxHandler().sendEmptyMessage(V_REQUEST_DATA);
	}

	private void onData(ModelData data) {
		mDataTV.setText("The answer is " + data.getAnswer());
	}

	private void onQuit() {
		Logger.d("Activity quitting");
		finish();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.update:
			mController.getInboxHandler().sendEmptyMessage(V_REQUEST_UPDATE);
			break;
		case R.id.quit:
			mController.getInboxHandler().sendEmptyMessage(V_REQUEST_QUIT);
			break;
		}
	}
}