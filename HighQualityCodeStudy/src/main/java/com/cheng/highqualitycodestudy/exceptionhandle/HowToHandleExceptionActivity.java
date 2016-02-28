package com.cheng.highqualitycodestudy.exceptionhandle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cheng.highqualitycodestudy.R;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HowToHandleExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtohandleexception);
    }

    /**
     * 参考：
     * http://www.2cto.com/kf/200506/5460.html
     */
    /**
     * 下面的代码片段中存在六个异常处理的问题
     * 改进的代码见goodCodeDemo()
     */
    private void badCodeDemo() {
        /* 1 */OutputStreamWriter out = null;
        /* 2 */java.sql.Connection conn = null;
        /* 3 */try { // (5)
        /* 4 */    Statement stat = conn.createStatement();
        /* 5 */    ResultSet rs = stat.executeQuery(
        /* 6 */            "select uid, name from user");
        /* 7 */    while (rs.next())
        /* 8 */    {
        /* 9 */        System.out.println("ID:"+rs.getString("uid") // (6)
        /* 10 */                +",name:"+rs.getString("name"));
        /* 11 */    }
        /* 12 */    conn.close(); // (3)
        /* 13 */    out.close();
        /* 14 */}
        /* 15 */catch (Exception ex) // (2)
        /* 16 */{
        /* 17 */    ex.printStackTrace(); // (1),(4)
        /* 18 */}
    }

    /**
     * “反例”分析
     *
     * 反例之一：丢弃异常
     * 代码：15行-18行
     *      这段代码捕获了异常却不作任何处理，可以算得上Java编程中的杀手。从问题出现的频繁程度
     * 和祸害程度来看，它或许可以和C/C++程序的一个恶名远播的问题相提并论？？不检查缓冲区是否已
     * 满。如果你看到了这种丢弃（而不是抛出）异常的情况，可以99%地肯定代码存在问题（在极少数情
     * 况下，这段代码有存在的理由，但最好加上完整的注释，以免引起误解）。
     *      这段代码的错误在于，异常（几乎）总是意味着某些事情不对劲了，或者说至少发生了某些不
     * 寻常的事情，我们不应该对程序发出的求救信号保持沉默和无动于衷。调用一下priintStackTrace
     * 算不上“处理异常”。不错，调用printStackTrace对调试程序有帮助，但程序调试阶段结束之后，
     * printStackTrace就不应该再在异常处理模块中负担主要责任了。
     *      丢弃异常的情形非常普遍。打开JDK的ThreadDeath类的文档，可以看到下面这段说明：“特别
     * 地，虽然出现ThreadDeath是一种‘正常的情形’，但ThreadDeath类是Error而不是Exception的子
     * 类，因为许多应用会捕获所有的Exception然后丢弃它不再理睬。”这段话的意思是，虽然ThreadDeath
     * 代表的是一种普通的问题，但鉴于许多应用会试图捕获所有异常然后不予以适当的处理，所以JDK把
     * ThreadDeath定义为Error的子类，因为Error类代表的是一般的应用不应该去捕获的严重问题。可
     * 见，丢去异常这一坏习惯是如此常见，它甚至已经影响到了Java本身的设计。
     *      那么，应该怎么改正呢？主要有四个选择：
     *      1、处理异常。针对该异常采取一些行动，例如修正问题、提醒某个人或进行其他一些处理，要
     * 根据具体的情形确定应该采取的动作。再次说明，调用printStackTrace算不上已经“处理好了异常”。
     *      2、重新抛出异常。处理异常的代码在分析异常之后，认为自己不能处理它，重新抛出异常也
     * 不失为一种选择。
     *      3、把该异常转换成另一种异常。大多数情况下，这是指把一个低级的异常转换成应用级的异常
     * （其含义更容易被用户了解的异常）。
     *      4、不要捕获异常。
     *      结论一：既然捕获了异常，就要对它进行适当的处理。不要捕获异常之后又把它丢弃，不予理睬。
     *
     * 反例之二：不指定具体的异常
     * 代码：15行。
     *      许多时候人们会被这样一种“美妙的”想法吸引：用一个catch语句捕获所有的异常。最常见的
     * 情形就是使用catch(Exception ex)语句。但实际上，在绝大多数情况下，这种做法不值得提倡。
     * 为什么呢？
     *      要理解其原因，我们必须回顾一下catch语句的用途。catch语句表示我们预期会出现某种异常，
     * 而且希望能够处理该异常。异常类的作用就是告诉Java编译器我们想要处理的是哪一种异常。由于
     * 绝大多数异常都直接或间接从java.lang.Exception派生，catch(Exception ex)就相当于说我们
     * 想要处理几乎所有的异常。
     *      再来看看前面的代码例子。我们真正想要捕获的异常是什么呢？最明显的一个是SQLException，
     * 这是JDBC操作中常见的异常。另一个可能的异常是IOException，因为它要操作OutputStreamWriter。
     * 显然，在同一个catch块中处理这两种截然不同的异常是不合适的。如果用两个catch块分别捕获
     * SQLException和IOException就要好多了。这就是说，catch语句应当尽量指定具体的异常类型，
     * 而不应该指定涵盖范围太广的Exception类。
     *      另一方面，除了这两个特定的异常，还有其他许多异常也可能出现。例如，如果由于某种原因，
     * executeQuery返回了null，该怎么办？答案是让它们继续抛出，即不必捕获也不必处理。实际上，
     * 我们不能也不应该去捕获可能出现的所有异常，程序的其他地方还有捕获异常的机会??直至最后由
     * JVM处理。
     *      结论二：在catch语句中尽可能指定具体的异常类型，必要时使用多个catch。不要试图处理
     * 所有可能出现的异常。
     *
     * 反例之三：占用资源不释放
     * 代码：3行-14行
     *      异常改变了程序正常的执行流程。这个道理虽然简单，却常常被人们忽视。如果程序用到了
     * 文件、Socket、JDBC连接之类的资源，即使遇到了异常，也要正确释放占用的资源。为此，Java
     * 提供了一个简化这类操作的关键词finally。
     *      finally是样好东西：不管是否出现了异常，Finally保证在try/catch/finally块结束之前，
     * 执行清理任务的代码总是有机会执行。遗憾的是有些人却不习惯使用finally。
     *      当然，编写finally块应当多加小心，特别是要注意在finally块之内抛出的异常??这是执行
     * 清理任务的最后机会，尽量不要再有难以处理的错误。
     *      结论三：保证所有资源都被正确释放。充分运用finally关键字
     *
     * 反例之四：不说明异常的详细信息
     * 代码：3行-18行
     *      仔细观察这段代码：如果循环内部出现了异常，会发生什么事情？我们可以得到足够的信息
     * 判断循环内部出错的原因吗？不能。我们只能知道当前正在处理的类发生了某种错误，但却不能
     * 获得任何信息判断导致当前错误的原因。
     *      printStackTrace的堆栈跟踪功能显示出程序运行到当前类的执行流程，但只提供了一些
     * 最基本的信息，未能说明实际导致错误的原因，同时也不易解读。
     *      因此，在出现异常时，最好能够提供一些文字信息，例如当前正在执行的类、方法和其他状态
     * 信息，包括以一种更适合阅读的方式整理和组织printStackTrace提供的信息。
     *      结论四：在异常处理模块中提供适量的错误原因信息，组织错误信息使其易于理解和阅读。
     *
     * 反例之五：过于庞大的try块
     * 代码：3行-14行。
     *      经常可以看到有人把大量的代码放入单个try块，实际上这不是好习惯。这种现象之所以常见，
     * 原因就在于有些人图省事，不愿花时间分析一大块代码中哪几行代码会抛出异常、异常的具体类型
     * 是什么。把大量的语句装入单个巨大的try块就象是出门旅游时把所有日常用品塞入一个大箱子，
     * 虽然东西是带上了，但要找出来可不容易。
     *      一些新手常常把大量的代码放入单个try块，然后再在catch语句中声明Exception，而不是
     * 分离各个可能出现异常的段落并分别捕获其异常。这种做法为分析程序抛出异常的原因带来了困难，
     * 因为一大段代码中有太多的地方可能抛出Exception。
     *      结论五：尽量减少try块的体积。
     *
     * 反例之六：输出数据不完整
     * 代码：7行-11行。
     *      不完整的数据是Java程序的隐形杀手。仔细观察这段代码，考虑一下如果循环的中间抛出了
     * 异常，会发生什么事情。循环的执行当然是要被打断的，其次，catch块会执行??就这些，再也没有
     * 其他动作了。已经输出的数据怎么办？使用这些数据的人或设备将收到一份不完整的（因而也是错
     * 误的）数据，却得不到任何有关这份数据是否完整的提示。对于有些系统来说，数据不完整可能比
     * 系统停止运行带来更大的损失。
     *      较为理想的处置办法是向输出设备写一些信息，声明数据的不完整性；另一种可能有效的办法
     * 是，先缓冲要输出的数据，准备好全部数据之后再一次性输出。
     *      结论六：全面考虑可能出现的异常以及这些异常对执行流程的影响。
     */

    /**
     * 比较完备的异常处理机制演示
     */
    private void goodCodeDemo() throws ApplicatinException {
        OutputStreamWriter out = null;
        java.sql.Connection conn = null;
        try {
            out.write("");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(
                    "select uid, name from user");
            while (rs.next()) {
                System.out.println("ID：" + rs.getString("uid") + "，姓名: " + rs.getString("name"));
            }
        } catch (SQLException sqlex) {
            System.out.println("警告：数据不完整");
            throw new ApplicatinException("读取数据时出现SQL错误", sqlex);
        } catch (IOException ioex) {
            throw new ApplicatinException("写入数据时出现IO错误", ioex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex2) {
                    System.err.printf(this.getClass().getName() + ".mymethod - 不能关闭数据库连接: " + sqlex2.toString());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ioex2) {
                    System.err.printf(this.getClass().getName() + ".mymethod - 不能关闭文件输出流: " + ioex2.toString());
                }
            }
        }
    }

}
