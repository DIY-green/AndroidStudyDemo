package com.cheng.bigtalkdesignpatterns.factorymethod;

/**
 * 社区志愿者工厂
 */
public class VolunteerFactory implements IFactory {
    @Override
    public Volunteer createLeiFeng() {
        return new Volunteer();
    }
}
