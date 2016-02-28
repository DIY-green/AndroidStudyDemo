package com.cheng.zenofdesignpatterns.principle.isp;

/**
 * 最标准的美女，脸蛋、身材、气质都具备
 */
public class PettyGirl implements IGoodBodyGirl,IGreatTemperamentGirl {

    private String name;

    public PettyGirl(String _name) {
        this.name = _name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "---脸蛋很漂亮");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "---身材非常棒");
    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "---气质非常好");
    }
}
