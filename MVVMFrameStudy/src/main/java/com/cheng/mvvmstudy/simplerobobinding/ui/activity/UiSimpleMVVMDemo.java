package com.cheng.mvvmstudy.simplerobobinding.ui.activity;


import android.os.Bundle;
import android.view.View;

import com.cheng.base.BaseActivity;
import com.cheng.mvvmstudy.R;
import com.cheng.mvvmstudy.simplerobobinding.viewmodel.PresentationModel;

import org.robobinding.ViewBinder;
import org.robobinding.binder.BinderFactory;
import org.robobinding.binder.BinderFactoryBuilder;

/**
*
* @since 1.0
* @version $Revision: 1.0 $
* @author Cheng Wei
*/
public class UiSimpleMVVMDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		PresentationModel presentationModel = new PresentationModel();
		ViewBinder viewBinder = createViewBinder();
		View rootView = viewBinder.inflateAndBind(R.layout.ui_simplemvvmdemo
				, presentationModel);
		setContentView(rootView);
    }

	private ViewBinder createViewBinder() {
		BinderFactory reusableBinderFactory = new BinderFactoryBuilder().build();
		return reusableBinderFactory.createViewBinder(this);
	}

}
