package com.cheng.zenofdesignpatterns.patterns.prototype;

import android.view.View;

import com.cheng.zenofdesignpatterns.ZoDPChapterBaseActivity;
import com.cheng.zenofdesignpatterns.patterns.prototype.notes.DeepCopy;
import com.cheng.zenofdesignpatterns.patterns.prototype.notes.ShallowCopy;
import com.cheng.zenofdesignpatterns.patterns.prototype.notes.Thing;
import com.cheng.zenofdesignpatterns.patterns.prototype.sendmail.AdvTemplate;
import com.cheng.zenofdesignpatterns.patterns.prototype.sendmail.Mail;

import java.util.Random;

public class ZoDPPrototypeActivity extends ZoDPChapterBaseActivity {

    @Override
    protected void initData() {
        mTitleTV.setText("原型模式");
        String content = "定义：\n" +
                "Specify the kinds of objects to create using a prototypical instance," +
                "and create new objects by copying this prototype.\n" +
                "用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。\n\n" +
                "原型模式的优点\n" +
                "- 性能优良\n" +
                "原型模式是在内存二进制流的拷贝，要比直接new一个对象性能好很多，特别是要在一个循" +
                "环体内产生大量的对象时，原型模式可以更好地体现其优点。\n" +
                "- 逃避构造函数的约束\n" +
                "这既是它的优点也是缺点，直接在内存中拷贝，构造函数是不会执行的。\n\n" +
                "使用场景\n" +
                "- 资源优化场景\n" +
                "类初始化需要消耗非常多的资源，这个资源包括数据、硬件资源等。\n" +
                "- 性能和安全要求的场景\n" +
                "通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。\n" +
                "- 一个对象多个修改者的场景\n" +
                "一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以" +
                "考虑使用原型模式拷贝多个对象供调用者使用。\n\n" +
                "注意事项\n" +
                "1. 构造函数不会被执行\n" +
                "2. 浅拷贝和深拷贝\n" +
                "注意 使用原型模式时，引用的成员变量必须满足两个条件才不会被拷贝：一是类的" +
                "成员变量，而不是方法内变量；二是必须是一个可变的引用对象，而不是一个原始类" +
                "型或不可变对象。\n" +
                "注意 深拷贝和浅拷贝建议不要混合使用，特别是在涉及类的继承时，父类有多个引用" +
                "的情况就非常复杂，建议的方案是深拷贝和浅拷贝分开实现。\n" +
                "3. clone和final两个冤家\n" +
                "对象的clone和对象内的final关键字是有冲突的。\n" +
                "注意 要使用clone方法，类的成员变量上不要增加final关键字。\n\n" +
                "最佳实践\n" +
                "原型模式先产生出一个包含大量共有信息的类，然后可以拷贝出副本，修正细节信息，" +
                "建立了一个完整的个性对象。可以这样理解：一个对象的产生可以不由零起步，直接从" +
                "一个已经具备一定雏形的对象克隆，然后再修改为生产需要的对象。";
        mContentTV.setText(content);
    }

    @Override
    public void onClick(View v) {
        // 1. 批量发送邮件
        // 发送账单的数量，这个值是从数据库中获得
        final int MAX_COUNT = 6;
        // 模拟发送邮件
        int i=0;
        // 把模板定义出来，这个是从数据中获得
        Mail mail = new Mail(new AdvTemplate());
        mail.setTail("XX银行版权所有");
        while(i<MAX_COUNT){
            // 以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setAppellation(getRandString(5)+" 先生（女士）");
            cloneMail.setReceiver(getRandString(5) + "@" + getRandString(8)+".com");
            // 然后发送邮件
            sendMail(cloneMail);
            i++;
        }

        // 2. 构造函数不会被执行
        // 产生一个对象
        Thing thing = new Thing();
        // 拷贝一个对象
        Thing cloneThing = thing.clone();

        // 3. 浅拷贝
        // 产生一个对象
        ShallowCopy shallowCopy = new ShallowCopy();
        // 设置一个值
        shallowCopy.setValue("张三");
        // 拷贝一个对象
        ShallowCopy cloneShallowCopy = shallowCopy.clone();
        cloneShallowCopy.setValue("李四");
        System.out.println(shallowCopy.getValue());

        // 4. 深拷贝
        // 产生一个对象
        DeepCopy deepCopy = new DeepCopy();
        // 设置一个值
        deepCopy.setValue("张三");
        deepCopy.setValue("abcd");
        // 拷贝一个对象
        DeepCopy cloneDeepCopy = deepCopy.clone();
        cloneDeepCopy.setValue("李四");
        System.out.println(deepCopy.getValue());
    }

    //发送邮件
    private void sendMail(Mail mail){
        System.out.println("标题："+mail.getSubject() + "\t收件人："+mail.getReceiver()+"\t....发送成功！");
    }

    //获得指定长度的随机字符串
    private String getRandString(int maxLength){
        String source ="abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for(int i=0;i<maxLength;i++){
            sb.append(source.charAt(rand.nextInt(source.length())));
        }
        return sb.toString();

    }
}
