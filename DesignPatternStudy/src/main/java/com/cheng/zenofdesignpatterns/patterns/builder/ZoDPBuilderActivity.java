package com.cheng.zenofdesignpatterns.patterns.builder;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.builder.common.Director;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BMWBuilder;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BMWModel;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BenzBuilder;
import com.cheng.zenofdesignpatterns.patterns.builder.section1.BenzModel;
import com.cheng.zenofdesignpatterns.patterns.builder.section2.CarDirector;

import java.util.ArrayList;

public class ZoDPBuilderActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("建造者模式");
        String content = "定义：\n" +
                "Separate the construction of a complex object from its representation " +
                "so that the same construction process can create different representation.\n" +
                "将一个负责对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。\n\n" +
                "建造者模式的优点\n" +
                "- 封装性\n" +
                "使用建造者模式可以始客户端不必知道产品内部组成的细节。\n" +
                "- 建造者独立，容易扩展\n" +
                "- 便于控制细节风险\n" +
                "由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。\n\n" +
                "使用场景\n" +
                "- 相同的方法，不同的执行顺序，产生不同的事件结果时，可以采用建造者模式\n" +
                "- 多个部件或零件，都可以装配到一个对象中，但是产生的运行结果又不相同时，则可以使用\n" +
                "- 产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能，这个时候该模式非常合适\n" +
                "- 在对象创建过程中会使用到系统中的一些其他对象，这些对象在产品对象的创建过程中不易" +
                "得到时，也可以采用该模式封装该对象的创建过程。该种场景只能是一个补偿方法，因为一个" +
                "对象不容易获得，而在设计阶段竟然没有发觉，而要通过创建者模式柔化创建过程，本身已经" +
                "违反设计的最初目标。\n\n" +
                "注意事项\n" +
                "建造者模式关注的是零件类型和装配工艺（顺序），这是它与工厂方法模式最大不同的地方，虽" +
                "然同为创建类模式，但是注重点不同。\n\n" +
                "建造者模式的扩展\n" +
                "建造者模式最主要的功能是基本方法的调用顺序安排，也就是这些基本方法已经实现了，通俗地" +
                "说就是零件的装配，顺序不同产生的对象也不同；而工厂方法则重点是创建，创建零件是它的主" +
                "要职责，组装顺序则不是它关心的。\n\n" +
                "最佳实践\n" +
                "在使用建造者模式的时候考虑以下模板方法模式，别孤立地思考一个模式，僵化地套用一个模" +
                "式会让你受害无穷。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. Section1
        ArrayList<String> sequence  = new ArrayList<String>();  //存放run的顺序
        sequence.add("engine boom");  //客户要求，run的时候时候先发动引擎
        sequence.add("start");  //启动起来
        sequence.add("stop");  //开了一段就挺下来
        //要一个奔驰车：
        BenzBuilder benzBuilder = new BenzBuilder();
        //把顺序给这个builder类，制造出这样一个车出来
        benzBuilder.setSequence(sequence);
        //制造出一个奔驰车
        BenzModel benz = (BenzModel)benzBuilder.getCarModel();
        //奔驰车跑一下看看
        benz.run();
        //按照同样的顺序，我再要一个宝马
        BMWBuilder bmwBuilder = new BMWBuilder();
        bmwBuilder.setSequence(sequence);
        BMWModel bmw = (BMWModel)bmwBuilder.getCarModel();
        bmw.run();

        // 2. Section2
        CarDirector carDirector = new CarDirector();
        // 1W辆A类型的奔驰车
        for(int i=0;i<10000;i++){
            carDirector.getABenzModel().run();
        }
        // 100W辆B类型的奔驰车
        for(int i=0;i<1000000;i++){
            carDirector.getBBenzModel().run();
        }
        // 1000W量C类型的宝马车
        for(int i=0;i<10000000;i++){
            carDirector.getCBMWModel().run();
        }

        // 3. 通用建造者模式演示
        Director director = new Director();
        director.getAProduct();
    }
}
