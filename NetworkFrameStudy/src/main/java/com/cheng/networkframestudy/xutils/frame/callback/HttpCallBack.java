package com.cheng.networkframestudy.xutils.frame.callback;

import org.xutils.common.Callback;

public class HttpCallBack<ResultType> implements Callback.ProgressCallback<ResultType>{

	@Override
	public void onSuccess(ResultType result) {
		//根据公司业务需求，处理相应业务逻辑
	}

	@Override
	public void onError(Throwable ex, boolean isOnCallback) {
		//根据公司业务需求，处理相应业务逻辑
	}

	@Override
	public void onCancelled(CancelledException cex) {
		
	}

	@Override
	public void onFinished() {
		
	}

	@Override
	public void onWaiting() {
		
	}

	@Override
	public void onStarted() {
		
	}

	@Override
	public void onLoading(long total, long current, boolean isDownloading) {
		
	}

}
