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
package com.cheng.mvcframestudy.keeganmvc.core;


import com.cheng.mvcframestudy.keeganmvc.model.CouponBO;

import java.util.List;

/**
 * 接收app层的各种Action
 *
 * @author Keegan小钢
 * @date 15/6/25
 * @version 1.0
 */
public interface AppAction {
    /**
     * 发送验证码
     *
     * @param phoneNum 手机号
     * @param listener 回调监听器
     */
    public void sendSmsCode(String phoneNum, ActionCallbackListener<Void> listener);

    /**
     * 注册
     *
     * @param phoneNum 手机号
     * @param code     验证码
     * @param password 密码
     * @param listener 回调监听器
     */
    public void register(String phoneNum, String code, String password, ActionCallbackListener<Void> listener);

    /**
     * 登录
     *
     * @param loginName 登录名
     * @param password  密码
     * @param listener  回调监听器
     */
    public void login(String loginName, String password, ActionCallbackListener<Void> listener);

    /**
     * 券列表
     *
     * @param currentPage 当前页数
     * @param listener    回调监听器
     */
    public void listCoupon(int currentPage, ActionCallbackListener<List<CouponBO>> listener);
}
