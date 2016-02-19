/**
 * Copyright (C) 2015. Keegan小钢（http://keeganlee.me）
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cheng.mvcframestudy.keeganmvc.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cheng.mvcframestudy.R;
import com.cheng.mvcframestudy.keeganmvc.core.ActionCallbackListener;


/**
 * 注册
 *
 * @version 1.0 创建时间：15/6/26
 */
public class RegisterActivity extends KBaseActivity {

    private EditText phoneEdit;
    private EditText codeEdit;
    private EditText passwordEdit;
    private Button sendCodeBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        phoneEdit = (EditText) findViewById(R.id.edit_phone);
        codeEdit = (EditText) findViewById(R.id.edit_code);
        passwordEdit = (EditText) findViewById(R.id.edit_password);
        sendCodeBtn = (Button) findViewById(R.id.btn_send_code);
        registerBtn = (Button) findViewById(R.id.btn_register);
    }

    // 准备发送验证码
    public void toSendCode(View view) {
        String phoneNum = phoneEdit.getText().toString();
        sendCodeBtn.setEnabled(false);
        this.appAction.sendSmsCode(phoneNum, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_code_has_sent, Toast.LENGTH_SHORT).show();
                sendCodeBtn.setEnabled(true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                sendCodeBtn.setEnabled(true);
            }
        });
    }

    // 准备注册
    public void toRegister(View view) {
        String phoneNum = phoneEdit.getText().toString();
        String code = codeEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        registerBtn.setEnabled(false);
        this.appAction.register(phoneNum, code, password, new ActionCallbackListener<Void>() {
            @Override
            public void onSuccess(Void data) {
                Toast.makeText(context, R.string.toast_register_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, CouponListActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                registerBtn.setEnabled(true);
            }
        });
    }
}
