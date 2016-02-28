package com.cheng.zenofdesignpatterns.patterns.builder.section2;

import com.cheng.zenofdesignpatterns.patterns.builder.section1.BMWBuilder;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BMWModel;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BenzBuilder;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BenzModel;

import java.util.ArrayList;

/**
 * 导演安排顺序，生产车辆模型
 */
public class CarDirector {

    private ArrayList<String> sequence = new ArrayList<>();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /*
	 * A类型的奔驰车模型，先start,然后stop,其他什么引擎了，喇叭一概没有
	 */
    public BenzModel getABenzModel() {
        // 清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();

        // 这是ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");

        // 按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
	 * B型号的奔驰车模型，是先发动引擎，然后启动，然后停止，没有喇叭
	 */
    public BenzModel getBBenzModel(){
        this.sequence.clear();

        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");

        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
	 * C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
	 */
    public BMWModel getCBMWModel(){
        this.sequence.clear();

        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");

        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    /*
	 * D类型的宝马车只有一个功能，就是跑，启动起来就跑，永远不停止，牛叉
	 */
    public BMWModel getDBMWModel(){
        this.sequence.clear();

        this.sequence.add("start");

        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel)this.benzBuilder.getCarModel();
    }

	/*
	 * 这边很可以有很多方法，你可以先停止，然后再启动，或者一直停着不动，静态的嘛
	 * 导演类嘛，按照什么顺序是导演说了算
	 */

}
