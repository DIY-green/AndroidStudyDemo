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
package com.cheng.mvcframestudy.keeganmvc.model;

import java.io.Serializable;

/**
 * 券的业务模型类，封装了券的基本信息。
 * 券分为了三种类型：现金券、抵扣券、折扣券。
 * 现金券是拥有固定面值的券，有固定的售价；
 * 抵扣券是满足一定金额后可以抵扣的券，比如满100减10元；
 * 折扣券是可以打折的券。
 *
 * @author Keegan小钢
 * @date 15/6/21
 * @version 1.0
 */
public class CouponBO implements Serializable {
    private static final long serialVersionUID = -8022957276104379230L;

    public static final int TYPE_CASH = 1; // 现金券
    public static final int TYPE_DEBIT = 2; // 抵扣券
    public static final int TYPE_DISCOUNT = 3; // 折扣券

    private int id;                // 券id
    private String name;           // 券名称
    private String introduce;      // 券简介
    private int modelType;         // 券类型，1为现金券，2为抵扣券，3为折扣券
    private double faceValue;      // 现金券的面值
    private double estimateAmount; // 现金券的售价
    private double debitAmount;    // 抵扣券的抵扣金额
    private double discount;       // 折扣券的折扣率（0-100）
    private double miniAmount;     // 抵扣券和折扣券的最小使用金额

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    public double getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(double faceValue) {
        this.faceValue = faceValue;
    }

    public double getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(double estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getMiniAmount() {
        return miniAmount;
    }

    public void setMiniAmount(double miniAmount) {
        this.miniAmount = miniAmount;
    }
}
