package com.cheng.improve151suggest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.Arrays;
import com.cheng.highqualitycodestudy.R;
import com.cheng.improve151suggest.adapter.I151SuggestListAdapter;

/**
 第9章 多线程和并发
 建议118： 不推荐覆写start方法
 建议119： 启动线程前stop方法是不可靠的
 建议120： 不使用stop方法停止线程
 建议121： 线程优先级只使用三个等级
 建议122： 使用线程异常处理器提升系统可靠性
 建议123： volatile不能保证数据同步
 建议124： 异步运算考虑使用callable接口
 建议125： 优先选择线程池
 建议126： 适时选择不同的线程池来实现
 建议127： lock与synchronized是不一样的
 建议128： 预防线程死锁
 建议129： 适当设置阻塞队列长度
 建议130： 使用countdownlatch协调子线程
 建议131： cyclicbarrier让多线程齐步走
 */
public class I151SChapter09Activity extends AppCompatActivity {
    private static final String TAG = "I151SChapter09Activity";

    private ListView mChapterLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i151suggestchapter);
        initView();
        initData();
    }

    private void initView() {
        this.mChapterLV = (ListView) findViewById(R.id.isi_chapter_lv);
    }

    private void initData() {
        String[] suggests = getResources().getStringArray(R.array.i151schapter9);
        I151SuggestListAdapter adapter = new I151SuggestListAdapter(this, Arrays.asList(suggests));
        mChapterLV.setAdapter(adapter);
    }

    /**
     * 建议118： 不推荐覆写start方法
     */
    private void suggest118() {
        /*
        查看Thread的源码可以发现在start方法中调用了一个start0本地方法，该方法实现了启动线程、申请栈内
        存、运行run方法、修改线程状态等职责，线程管理和栈内存管理都是由JVM负责的，如果覆盖了start方法，
        也就是撤销了线程管理和栈内存管理的能力。事实上，不需要关注线程和栈内存的管理，只需要编码者实现
        多线程的逻辑即可（即run方法体），这也是JVM比较聪明的地方，简化多线程应用
         */
        /**
         * 注意
         * 继承自Thread类的多线程类不必覆写start方法
         */
    }

    /**
     * 建议119： 启动线程前stop方法是不可靠的
     */
    private void suggest119() {
        /*
        Thread类的stop方法会根据线程状态类判断是终结线程还是设置线程为不可运行状态，对于未启动的线程（
        线程状态为NEW）来说，会设置其标志位为不可启动，而其他的状态则是直接停止
         */
        /**
         * 注意
         * 不要使用stop方法进行状态设置，直接通过判断条件来决定线程是否可启用
         */
    }

    /**
     * 建议120： 不使用stop方法停止线程
     */
    private void suggest120() {
        /*
        线程启动完毕后，在运行时可能需要终止，Java提供的终止方法只有一个stop，但是不建议使用这个方法，
        因为它有以下三个问题：
        1）stop方法是过时的
        2）stop方法会导致代码逻辑不完整
        stop方法是一种“恶意”的中断，一旦执行stop方法，即终止当前正在运行的线程，不管线程逻辑是否完整，
        这是非常危险的。因为我们不知道子线程会在什么时候被终止，stop连基本的逻辑完整性都无法保证。而且
        此种操作也是非常隐蔽的，子线程执行到何处会被关闭很难定位，这为以后的维护带来很多麻烦
        3）stop方法会破坏原子逻辑
        多线程为了解决共享资源抢占的问题，使用了锁概念，避免资源不同步，但是正因此原因，stop方法却会带
        来更大的麻烦：它会丢弃所有的锁，导致原子逻辑受损
        既然终止一个线程不能使用stop方法，那怎样才能终止一个正在运行的线程呢？答案很简单，使用自定义的
        标志位决定线程的执行情况。这是很简单的办法，在线程体中判断是否需要停止运行，即可保证线程体的逻
        辑完整性，而且也不会破坏原子逻辑。
        可能有这样的疑问：Thread不是还提供了interrupt中断线程的方法吗？这个方法可不是过时方法，那可以
        使用吗？它可以终止一个线程吗？
        interrupt，名字看上去很像是终止一个线程的方法，但是我可以很明确地告诉你，它不行，它不能终止一个
        正在执行着的线程，它只是修改中断标志而已。如果在thread.interrupt()前后输出thread.isInterrupted()
        则会发现分别输出了false和true。如果需要终止线程，需要自行进行判断，例如可以使用interrupt编写出
        更加简洁、安全的终止线程代码：
        class SafeStopThread extends Thread {
            @Override
            public void run() {
                // 判断线程体是否运行
                while (!isInterrupted()) {
                    // DO Something
                }
            }
        }
         */
        /**
         * 注意
         * 如果期望终止一个正在运行的线程，则不能使用已经过时的stop方法，需要自行编码实现，如此即可保证
         * 原子逻辑不被破坏，代码逻辑不出现异常。当然，如果使用的是线程池（比如ThreadPoolExecutor类），
         * 那么可以通过shutdown方法逐步关闭线程池中的线程，它采用的是比较温和、安全的关闭线程方法，完全
         * 不会产生类似stop方法的弊端
         */
    }

    /**
     * 建议121： 线程优先级只使用三个等级
     */
    private void suggest121() {
        /*
        线程的优先级决定了线程获得CPU运行的机会，优先级越高获得的运行机会越大，优先级越低获得的机会越
        小。Java的线程有10个级别（准确地说是11个级别，级别为0的线程是JVM的，应用程序不能设置该级别），
        那是不是说级别是10的线程肯定比级别为9的线程线运行呢？来看如下一个多线程类：
        class TestThread implements Runnable {
            // 启动线程
            public void start(int _priority) {
                Thread t = new Thread(this);
                // 设置线程优先级
                t.setPriority(_priority);
                t.start();
            }

            @Override
            public void run() {
                // 消耗CPU的计算，性能差的机器，请修改循环限制
                for (int i=0; i<100000; i++) {
                    Math.hypot(Math.pow(924526789, i), Math.cos(i));
                }
                // 输出线程优先级
                System.out.println("Priority:" + Thread.currentThread().getPriority());
            }
        }
        该多线程类实现了Runnable接口，实现了run方法，注意在run方法中有一个比较占用CPU的计算，该计算
        毫无意义，只是为了保证一个线程尽可能多地消耗CPU资源，目的是为了观察CPU繁忙时不同优先级线程的
        执行顺序。需要说明的是，如果此处使用了Thread.sleep()方法，则不能体现出线程优先级的本质了，因
        为CPU并不繁忙，线程调度不会遵循优先级顺序来进行调度。
        客户端的代码如下：
        public static void main(String[] args) {
            // 启动20个不同优先级的线程
            for (int i=0; i<20; i++) {
                new TestThread().start(i%10 + 1);
            }
        }
        这里创建了20个线程，每个线程在运行时都耗尽了CPU资源，因为优先级不同，线程调度应该最先处理优先
        级最高的，然后处理优先级最低的...但是结果却并不是这样的，查看运行结果：
        可以发现如下事实：
        1）并不是严格遵照线程优先级别来执行的
        2）优先级差别越大，运行机会差别越明显
        这两个现象是线程优先级的一个重要表现，之所以会出现这种情况，是因为线程运行是需要获得CPU资源的，
        那谁能决定哪个线程先获得哪个线程后获得？这是依照操作系统设定的线程优先级来分配的，也就是说，每
        个线程要运行，需要操作系统分配优先级和CPU资源，对于Java来说，JVM调用操作系统的接口设置优先级
        如果优先级相同呢？这很好办，也是由操作系统决定的，基本上是按照FIFO原则，但也是不能完全保证
         */
        /**
         * 注意
         * 线程优先级推荐使用MIN_PRIORITY、NORM_PRIORITY、MAX_PRIORITY三个级别，不建议使用其他级别
         */
    }

    /**
     * 建议122： 使用线程异常处理器提升系统可靠性
     */
    private void suggest122() {
        /*
        Java 1.5 版本以后在Thread类中增加了setUncaughtExceptionHandler方法，实现了线程异常的捕捉和
        处理。来看一个异常处理器应用的例子，代码如下：
        class TcpServer implements Runnable {
            // 创建后即运行
            public TcpServer() {
                Thread t = new Thread(this);
                t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
                t.start();
            }

            @Override
            public void run() {
                // 正常业务运行，运行3秒
                for (int i=0; i<3; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("系统正常运行：" + i);
                    } catch (InterruptedExcption e) {
                        e.printStackTrace();
                    }
                }
                // 抛出异常
                throw new RuntimeException();
            }

            // 异常处理器
            private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    // 记录线程异常信息
                    System.out.println("线程 " + t.getName() + " 出现异常，自行重启，请分析原因");
                    e.printStackTrace();
                    // 重启线程，保证业务不中断
                    new TcpServer();
                }
            }
        }
        从运行结果上可以看出，当Thread-0出现异常时，系统自动启动了Thread-1线程，继续提供服务，大大提
        高了系统的可靠性。这段程序只是一个实例程序，若要在实际环境中应用，则需要注意以下三个方面：
        1）共享资源锁定
        如果线程异常产生的原因是资源被锁定，自动重启应用只会增加系统的负担，无法提供不间断服务。例如一
        个即时通信服务器（XMPP Server）出现信息不能写入的情况时，即使再怎么重启服务，也是无法解决问题
        的。在此情况下最好的办法是停止所有的线程，释放资源
        2）脏数据引起系统逻辑混乱
        异常的产生中断了正在执行的业务逻辑，特别是如果正在执行一个原子操作（像即时通信服务器的用户验证
        和签到这两个事件应该在一个操作中处理，不允许出现验证成功但签到不成功的情况），但如果此时抛出了
        运行期异常就有可能破坏正常的业务逻辑，例如出现用户认证通过了，但签到不成功的情况，这种情景下重
        启应用服务器，虽然可以提供服务，但对部分用户则产生了逻辑异常
        3）内存溢出
        线程异常了，但由该线程创建的对象并不会马上回收，如果再重新启动新线程，再创建一批新对象，特别是
        加入了场景接管，就非常危险了，例如即时通信服务，重新启动一个新线程必须保证原在线用户的透明性，
        即用户不会察觉服务重启，在此情况下，就需要在线程初始化时加载大量对象以保证用户的状态信息，但是
        如果线程反复重启，很可能会引起OOM内存泄漏问题
         */
        /**
         * 注意
         * Thread类中提供了setUncaughtExceptionHandler方法，实现了异常的捕捉和处理
         */
    }

    /**
     * 建议123： volatile不能保证数据同步
     */
    private void suggest123() {
        /*
        volatile关键字比较少用，原因无外乎两点：一是在Java 1.5 之前该关键字在不同的操作系统上有不同
        的表现，所带来的问题就是移植性较差；二是比较难设计，而且误用较多，这也导致它的“名誉”受损
        在一个变量前加上volatile关键字，可以确保每个线程对本地变量的访问和修改都是直接与主内存交互的，
        而不是与本线程的工作内存交互的，保证每个线程都能获得最“新鲜”的变量值
        思考以下：volatile变量是否能够保证数据的同步性呢？两个线程同时修改一个i额volatile是否会产生
        脏数据呢？来看下面的代码：
        class UnsafeThread implements Runnable {
            // 共享资源
            private volatile int count = 0;
            @Override
            public void run() {
                // 增加CUP的繁忙程度，不用关心逻辑含义
                for (int i=0; i<1000; i++) {
                    Math.hypot(Math.pow(92456789, i), Math.cos(i));
                }
                // 自增运算
                count ++;
            }
            public int getCount() {
                return count;
            }
        }
        模拟多线程的代码如下：
        public static void main(String[] args) throws Exception {
            // 理想值，并作为最大循环次数
            int value = 1000;
            // 循环次数，防止出现无限循环造成死机情况
            int loops = 0;
            // 主线程组，用于估计活动线程数
            ThreadGroup tg = Thread.currentThread().getThreadGroup();
            while (loops++ < value) {
                // 共享资源清零
                UnsafeThread ut = new UnsafeThread();
                for (int i=0; i<value; i++) {
                    new Thread(ut).start();
                }
                // 先等15毫秒，等待活动线程数量成为1
                do {
                    Thread.sleep(15);
                } while (tg.activeCount() != 1);
                // 检查实际值与理论值是否一致
                if (ut.getCount != value) {
                    // 出现线程不安全的情况
                    System.out.println("循环到第 " + loops + " 遍，出现线程不安全情况");
                    System.out.println("此时，count= " + ut.getCount());
                    System.exit(0);
                }
            }
        }
        查看运行结果，说明count变量没有实现数据同步，在多个线程修改的情况下，count的实际值与理论值产生
        了偏差，直接说明了volatile关键字并不能保证线程安全
         */
        /**
         * 注意
         * volatile关键字并不能保证线程安全，它只能保证当线程需要该变量的值时能够获得最新的值，而不能
         * 保证多个线程修改的安全性
         */
    }

    /**
     * 建议124： 异步运算考虑使用callable接口
     */
    private void suggest124() {
        /*
        多线程应用有两种实现方式，一种是实现Runnable接口，另一种是继承Thread类，这两个方式都有缺点：run
        方法没有返回值，不能抛出异常（这两个缺点归根到底是Runnable接口的缺陷，Thread也是实现了Runnable
        接口），如果需要知道一个线程运行结果就需要用户自行设计，线程类自身也不能提供返回值和异常。但是从
        Java 1.5 开始引入了一个新的接口Callable，它类似于Runnable接口，实现它就可以实现多线程任务，该
        接口定义如下：
        public interface Callable<T> {
            // 具有返回值，并可抛出异常
            V call() throws Exception;
        }
        实现Callable接口的类，只是表明它是一个可调用的任务，并不表示它具有多线程运算能力，还是需要执行器
        来执行的。先编写一个任务类，代码如下：
        // 税款计算器
        class TaxCalculator implements Callable<Integer> {
            // 本金
            private int seedMoney;
            // 接收主线程提供的参数
            public TaxCalculator(int _seedMoney) {
                seedMoney = _seedMoney;
            }
            @Override
            public Integer call() throws Exception {
                // 复杂计算，运行一次需要10秒
                TimeUnit.MILLISECONDS.sleep(10000);
                return seedMoney / 10;
            }
        }
        这里模拟了一个复杂运算，该运算可能要花费10秒钟的时间。在这里启一个新线程来运算，让main线程做进度
        提示，代码如下：
        public static void main(String[] args) throws Exception {
            // 生成一个单线程的异步执行器
            ExecutorService es = Executors.newSingleThreadExecutor();
            // 线程执行后的期望值
            Future<Integer> future = es.submit(new TaxCalculator(100));
            while(!future.isDone()) {
                // 还没有运算完成，等待200毫秒
                TimeUnit.MILLISECONDS.sleep(200);
                // 输出进度符号
                System.out.print("#");
            }
            System.out.println("\n计算完成，税金是：" + future.get() + " 元");
            es.shutdown();
        }
        在该段代码中，Executors是一个静态工具类，提供了异步执行器的创建能力，如单线程执行器
        newSingleThreadExecutor、固定线程数量的执行器newFixedThreadPool等，一般它是异步计算的入口类。
        Future关注的是线程执行后的结果，比如有没有运行完毕，执行结果是多少等
         */
        /**
         * 注意
         * Callable可以监控线程执行的情况，比如是否执行完毕、是否有返回值、是否有异常等
         */
    }

    /**
     * 建议125： 优先选择线程池
     */
    private void suggest125() {
        /*
        ExecutorService就是实现了线程池的执行器，如下示例代码：
        public static void main(String[] args) {
            // 2个线程的线程池
            ExecutorService es = Executors.newFixedThreadPool(2);
            // 多次执行线程体
            for (int i=0; i<4; i++) {
                es.submit(new Runnable() {
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                });
            }
            // 关闭执行器
            es.shutdown();
        }
        查看运行结果，此处的2个线程可以反复使用，这是要搞清楚的重点。
        线程池的实现涉及以下三个名词：
        1）工作线程（Worker）
        线程池中的线程，只有两个状态：可运行状态和等待状态，在没有任务时它们处于等待状态，运行时可以循
        环地执行任务
        2）任务接口（Task）
        这是每个任务必须实现的接口，以供工作线程调度器调度，它主要规定了任务的入口、任务执行完的场景处
        理、任务的执行状态等。这里有两种类型的任务：具有返回值（或异常）的Callable接口任务和无返回值并
        兼容旧版本的Runnable接口任务
        3）任务队列（Work Queue）
        也叫做工作队列，用于存放等待处理的任务，一般是BlockingQueue的实现类，用来实现任务的排队处理
        首先从线程池的创建说起，Executors.newFixedThreadPool(2)表示创建一个具有2个线程的线程池，源
        代码如下：
        public class Executors {
            public static ExecutorService newFixedThreadPool(int nThreads) {
                // 生成一个最大为nThreads的线程池执行器
                return new ThreadPoolExecutor(nThreads, nThreads, 0L,
                    TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnbale>());
            }
        }
        这里使用了LinkedBlockingQueue作为任务队列管理器，所有等待处理的任务都会放在该队列中，需要注
        意的是，此队列是一个阻塞式的单端队列。线程池建立好了，那就需要线程在其中运行了，线程池中的线程
        是在submit第一次提交任务时建立的，代码如下：
        public Future<?> submit(Runnable task) {
            // 检查任务是否为null
            if (task == null) throw new NullPointerException();
            // 把Runnable任务包装成具有返回值的任务对象，不过此时并没有执行，只是包装
            RunnableFuture<Object> ftask = new TaskFor(task, null);
            // 执行此任务
            execute(ftask);
            // 返回任务预期执行结果
            return ftask;
        }
        此处的代码关键是execute方法，它实现了三个职责：
        创建足够多的工作线程数，数量不超过最大线程数量，并保持线程处于运行或等待状态
        把等待处理的任务放到任务队列中
        从任务队列中取出任务来执行
        其中此处的关键是工作线程的创建，它也是通过new Thread方式创建的一个线程，只是它创建的并不是我们
        的任务线程（虽然我们的任务线程实现了Runnable接口，但它只是起一个标志性的作用），而是经过包装的
        Worker线程，代码如下：
        private final class Worker implements Runnable {
            // 运行一次任务
            private void runTask(Runnable task) {
                // 这里的task才是我们自定义实现Runnable接口的任务
                task.run();
            }
            // 工作线程也是线程，必须实现的run方法
            public void run() {
                while (task!=null || (task=getTask())!=null) {
                    runTask(task);
                    task = null;
                }
            }
            // 从任务队列中获得任务
            Runnable getTask() {
                for (;;) {
                    return workQueue.take();
                }
            }
        }
        此处为示意代码，删除了大量的判断条件和锁资源。execute方法是通过Worker类启动的一个工作线程，执
        行的是我们的第一个任务，然后该线程通过getTask方法从任务队列中获取任务，之后再继续执行，但问题
        是任务队列是一个BlockingQueue，是阻塞式的，也就是说如果该队列元素为0，则保持等待状态，直到有
        任务进入为止，来看看LinkedBlockingQueue的take方法，代码如下：
        public E take() throws InterruptedException {
            // 如果队列中元素数量为0，则等待
            while (count.get() == 0)
                notEmpty.await();
            // 等待状态结束，弹出头元素
            x = extract();
            // 如果队列数量还多于1个，唤醒其他线程
            if (c > 1) {
                notEmpty.signal();
            }
            // 返回头元素
            return x;
        }
        线程池的创建过程：创建一个阻塞队列以容纳任务，在第一次执行任务时创建足够多的线程（不超过许可线
        程数），并处理任务，之后每个工作线程自行从任务队列中获得任务，直到任务队列中的任务数量为0为止，
        此时，线程将处于等待状态，一旦有任务再加入到队列中，即唤醒工作线程进行处理，实现线程可复用性
         */
        /**
         * 注意
         * 使用线程池减少的是线程的创建和销毁时间，这对于多线程应用来说非常有帮助，比如常用的Servlet容
         * 器，每次请求处理的都是一个线程，如果不采用线程池技术，每次请求都会创建一个线程，这会导致系统
         * 的性能负荷加大，响应效率下降，降低了系统的友好性
         */
    }

    /**
     * 建议126： 适时选择不同的线程池来实现
     */
    private void suggest126() {
        /*
        Java的线程池实现从最根本上来说只有两个：ThreadPoolExecutor类和ScheduledThreadPoolExecutor
        类，这两个类还是父子关系，但是Java为了简化并行计算，还提供了一个Executors的静态类，它可以直接
        生成多种不同的线程池执行器，比如单线程执行器、带缓冲功能的执行器等，但归根结底还是使用
        ThreadPoolExecutor类或ScheduledThreadPoolExecutor类的封装类
        为了理解这些执行器，我们首先来看ThreadPoolExecutor类，其中它复杂的构造函数可以很好解释该线程
        池的作用，代码如下：
        public class ThreadPoolExecutor extends AbstractExecutorService {
            // 最完整的构造函数
            public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
                RejectedExecutionHandler handler) {
                // 检验输入条件
                if (corePoolSize < 0 || maximumPoolSize<=0 || maximumPoolSize<corePoolSize
                || keepAliveTime<0)
                    throw new ILLegalArgumentException();
                this.corePoolSize = corePoolSize;
                this.maximumPoolSize = maximumPoolSize;
                this.workQueue = workQueue;
                this.keepAliveTime = unit.toNanos(keepAliveTime);
                this.threadFactory = threadFactory;
                this.handler = handler;
            }
        }
        这是ThreadPoolExecutor最完整的构造函数，其他的构造函数都是引用该构造函数实现的，我们逐步来解释
        这些参数的含义
        -corePoolSize：最小线程数
        线程池启动后，在池中保持线程的最小数量。需要说明的是线程数量是逐步到达corePoolSize值的，例如
        corePoolSize被设置为10，而任务数量只有5，则线程池中最多会启动5个线程，而不是一次性启动10个
        -maximumPoolSize：最大线程数量
        这是池中能够容纳的最大线程数量，如果超出，则使用RejectedExceptionHandler拒绝策略处理
        -keepAliveTime：线程最大生命期
        这里的生命期有两个约束条件：一是该参数针对的超过corePoolSize数量的线程；二是处于非运行状态的线
        程。这么说吧，如果corePoolSize为10，maximumPoolSize为20，此时线程池中有15个线程在运行，一段
        时间后，其中有3个线程处于等待状态的时间超过了keepAliveTime指定的时间，则结束这个线程，此时线程
        池中则还有12线程正在运行
        -unit：时间单位
        这是keepAliveTime的时间单位，可以是纳秒、毫秒、秒、分钟等选项
        -workQueue：任务队列
        当线程池中的线程都处于运行状态，而此时任务数量继续增加，则需要有一个容器来容纳这些任务，这就是任
        务队列
        -threadFactory：线程工厂
        定义如何启动一个线程，可以设置线程名称，并且可以确认是否是后台线程等
        -handler：拒绝任务处理器
        由于超出线程数量和队列容量而对继续增加的任务进行处理的程序
        线程池的管理是这样一个过程：首先创建线程池，然后根据任务的数量逐步将线程增大到corePoolSize数量，
        如果此时仍有任务增加，则放置到workQueue中，直到workQueue爆满为止，然后继续增加池中的线程数量（
        增强处理能力），最终达到maximumPoolSize，那如果此时还有任务要增加进来呢？这就需要handler来处理
        了，或者丢弃新任务，或者拒绝新任务，或者挤占已有任务等
        在任务队列和线程池都饱和的情况下，一旦有线程处于等待（任务处理完毕，没有新任务增加）状态的时间超
        过keepAliveTime，则该线程终止，也就是说池中的线程数量会逐渐降低，直到为corePoolSize数量为止
        再来看看Executors提供的几个创建线程池的便捷方法：
        -newSingleThreadExecutor：单线程池
        就是一个池中只有一个线程在运行，该线程永不超时。而且由于是一个线程，当有多个任务需要处理时，会将
        它们放置到一个无界阻塞队列中逐个处理，它的实现代码如下：
        public static ExecutorServices newSingleThreadExecutor() {
            return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.MILLISECOMDS, new LinkedBlockingQueue<Runnable>()));
        }
        它的使用方法也很简单，如下示例代码：
        public static void main(String[] args) throws Exception {
            // 创建单线程执行器
            ExecutorService es = Executors.newSingleThreadExecutor();
            // 执行一个任务
            Future<String> future = es.submit(new Callbale<String>() {
                public String call() throws Exception {
                    return "";
                }
            });
            // 获得任务执行后的返回值
            System.out.println("返回值：" + future.get());
            // 关闭执行器
            es.shutdown();
        }
        -newCachedThreadPool：缓冲功能的线程池
        建立一个线程池，线程数量没有限制（当然，不能超过Integer的最大值），新增一个任务即有一个线程处理，
        或者复用之前空闲的线程，或者新启动一个线程，但是一旦一个线程在60秒内一直处于等待状态时（也就是1分
        钟没有工作可做），则会被终止，其源代码如下：
        public static ExecutorService newCachedThreadPool() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new
                SynchronousQueue<Runnable>());
        }
        这里需要说明的是，任务队列使用了同步阻塞队列，这意味着向队列中加入一个元素，即可唤醒一个线程（新
        创建的线程或复用池中空闲线程）来处理，这种队列已经没有队列深度的概念了
        -newFixedThreadPool：固定线程数量的线程池
        在初始化时已经决定了线程的最大数量，若任务添加的能力超出了线程处理能力，则建立阻塞队列容纳多余的
        任务，其源代码如下：
        public static ExecutorService newFixedThreadPool(int nThreads) {
            return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new
                LinkedBlockingQueue<Runnable>());
        }
        该线程池的corePoolSize和maximumPoolSize是相等的，也就是说，最大线程数量也是nThreads。如果任务
        增长速度非常快，超过了LinkedBlockingQueue的最大容量（Integer的最大值），那此时会如何处理呢？会
        按照ThreadPoolExecutor默认的拒绝策略（默认是DiscardPolicy，直接丢弃）来处理
         */
        /**
         * 注意
         * ThreadPoolExecutor提供了简化版的三种线程池执行器，当然，有时候这三种线程池不能满足要求，此时
         * 则可以直接操作ThreadPoolExecutor来实现复杂的多线程运算。可以这样来比喻：
         * newSingleThreadExecutor、newCachedThreadPool、newFixedThreadPool是线程池的简化版，而
         * ThreadPoolExecutor则是旗舰版--简化版更容易操作，需要了解的知识相对少些，方便实用，而旗舰版功
         * 能齐全，适用面广，但难于驾驭
         */
    }

    /**
     * 建议127： Lock与synchronized是不一样的
     */
    private void suggest127() {
        /*
        很多人会说，Lock类和synchronized关键字用在代码块的并发性和内存上时语义是一样的，都是保持代码块同
        时只有一个线程具有执行权。这样的说法只对了一半，我们以一个任务提交给多个线程运行为例，来看看使用显
        式锁（Lock类）和内部锁（synchronized关键字）有什么不同。首先定义一个任务：
        class Task {
            public void doSomething() {
                try {
                    // 每个线程等待2秒钟，注意将此时的线程转为WAITING状态
                    Thread.sleep(2000);
                } catch (Exception e) {
                    // 异常处理
                }
                StringBuffer sb = new StringBuffer();
                // 线程名称
                sb.append("线程名称：" + Thread.currentThread().getName());
                // 运行的时间戳
                sb.append("，执行时间：" + Calendar.getInstance().get(13) + " s");
                System.out.println(sb);
            }
        }
        该类模拟了一个执行时间比较长的计算，注意这里使用的是模拟方式，在使用sleep方法时线程的状态会从运行
        状态转变为等待状态。该任务要具备多线程能力时必须实现Runnable接口，我们分别建立两种不同的锁实现机
        制，首先看显式锁实现：
        // 显式锁任务
        class TaskWithLock extends Task implements Runnable {
            // 声明显式锁
            private final Lock lock = new ReentrantLock();
            @Override
            public void run() {
                try {
                    // 开始锁定
                    lock.lock();
                    doSomething();
                } finally {
                    // 释放锁
                    lock.unlock;
                }
            }
        }
        这里有一点需要说明的是，显式锁的锁定和释放必须在一个try...finally块中，这是为了确保即使出现运
        行期异常也能正常释放锁，保证其他线程能够顺利执行。
        内部锁的处理也非常简单，代码如下：
        // 内部锁任务
        class TaskWithSync extends Task implements Runnable {
            @Override
            public void run() {
                // 内部锁
                synchronized("A") {
                    doSomething();
                }
            }
        }
        这两个任务看着非常相似，应该能够产生相似的结果吧？建立一个模拟场景，保证同时有三个线程在运行，代
        码如下：
        public static void runTasks(Class<? extends Runnable> clz) throws Exception {
            ExecutorService es = Executors.newCachedThreadPool();
            System.out.println("*** 开始执行" + clz.getSimpleName() + " 任务 ****");
            // 启动三个线程
            for (int i=0; i<3; i++) {
                es.submit(clz.newInstance());
            }
            // 等待足够长的时间，然后关闭执行器
            TimeUnit.SECONDS.sleep(10);
            System.out.println("------" + clz.getSimpleName() + " 任务执行完毕------\n");
            // 关闭执行器
            es.shutdown();
        }

        public static void main(String[] args) throws Exception {
            // 运行显式锁任务
            runTasks(TaskWithLock.class);
            // 运行内部锁任务
            runTasks(TaskWithSync.class);
        }
        按照一般的理解，Lock和synchronized的处理方式是相同的，输出应该没有差别，但是很遗憾，输出差别其
        实很大。输出后注意看运行的时间戳，显式锁是同时运行的，很显然在pool-1-thread-1线程执行到sleep
        时，其他两个线程也会运行到这里，一起等待，然后一起输出，这还具有线程互斥的概念吗？
        而内部锁的输出则是我们的预期结果：pool-2-thread-1线程在运行时其他线程处于等待状态，pool-2-thread-1
        执行完毕后，JVM从等待线程池中随机获得一个线程执行，最后在执行第三个线程，这正是我们希望的
        现在问题来了：Lock锁为什么不出现互斥情况呢？
        这是因为对于同步资源来说（示例中是代码块），显式锁是对象级别的锁，而内部锁是类级别的锁，也就是说
        Lock锁是跟随对象的，synchronized锁是跟随类的，更简单地说把Lock定义为多线程类的私有属性是起不到
        资源互斥作用的，除非是把Lock定义为所有线程的共享变量。来看一个Lock锁资源的代码：
        public static void main(String[] args) {
            // 多个线程共享锁
            final Lock lock = new ReentrantLock();
            // 启动三个线程
            for (int i=0; i<3; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            lock.lock();
                            // 休眠2秒钟
                            Thread.sleep(2000);
                            System.out.println(Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    }
                }).start();
            }
        }
        执行一下，会发现线程Thread-0、Thread-1、Thread-2会逐渐输出，也就是一个线程在执行时，其他线程就
        处于等待状态。注意，这里三个线程运行的实例对象是同一个类（都是Client$1类的实例）。
        那除了这一点不同之外，显式锁和内部锁还有什么不同呢？还有以下4点不同：
        1）Lock支持更细粒度的锁控制
        假设读写锁分离，写操作时不允许有读写操作存在，而读操作时读写可以并发执行，这一点内部锁就很难实现。
        显式锁的实例代码如下：
        class Foo {
            // 可重入的读写锁
            private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
            // 读锁
            private final Lock r = rwl.readLock();
            // 写锁
            private final Lock w = rwl.writeLock();

            // 读操作，可并发执行
            public void read() {
                try {
                    r.lock();
                    Thread.sleep(1000);
                    System.out.println("read ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    r.unlock();
                }
            }
            // 写操作，同时只允许一个写操作
            public void write(Object _obj) {
                try {
                    w.lock();
                    Thread.sleep(1000);
                    System.out.println("Writing ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    w.unlock();
                }
            }
        }
        可以编写一个Runnable实现类，把Foo类作为资源进行调用（注意多线程是共享这个资源的），然后就会发
        现这样的现象：读写锁允许同时有多个读操作但只允许有一个写操作，也就是当有一个写线程在执行时，所
        有的读线程和写线程都会阻塞，直到写线程释放锁资源为止，而读锁则可以有多个线程同时执行
        2）Lock是无阻塞锁，synchronized是阻塞锁
        当线程A持有锁时，线程B也期望获得锁，此时，如果程序中使用的是显式锁，则B线程为等待状态（在通常的
        描述中，也认为此线程被阻塞了），若使用的是内部锁则为阻塞状态
        3）Lock可实现公平锁，synchronized只能是非公平锁
        什么叫非公平锁呢？当一个线程A持有锁，而线程B、C处于阻塞（或等待）状态时，若线程A释放锁，JVM将从
        线程B、C中随机选择一个线程持有锁并使其获得执行权，这叫做非公平锁（因为它抛弃了先来后到的顺序）；
        若JVM选择了等待时间最长的线程持有锁，则为公平锁（保证每个线程的等待时间均衡）、需要注意的是，即使
        是公平锁，JVM也无法准确做到“公平”，在程序中不能以此作为精确计算
        显式锁默认是非公平锁，但可以在构造函数中加入参数true来声明出公平锁，而synchronized实现的是非公
        平锁，它不能实现公平锁
        4）Lock是代码级别的，synchronized是JVM级的
        Lock是通过编码实现的，synchronized是在运行期由JVM解释的，相对来说synchronized的优化可能性更高，
        毕竟是在最核心部分支持的，Lock的优化则需要用户自行考虑
        显式锁和内部锁的功能各不相同，在性能上也稍有差别，但随着JDK的不断推进，相对来说，显式锁使用起来更
        加便利和强大，在实际开发中选择哪种类型的锁就需要根据实际情况考虑了：灵活、强大则选择Lock，快捷、
        安全则选择synchronized
         */
        /**
         * 注意
         * 两种不同的锁机制，根据不同的情况来选择
         */
    }

    /**
     * 建议128： 预防线程死锁
     */
    private void suggest128() {
        /*
        线程死锁（DeadLock）是多线程编程中最头疼的问题，也是最难重现的问题，因为Java是单进程多线程语言，
        一旦线程死锁，则很难通过外科手术式的方法使其起死回生，很多时候只有借助外部进程重启应用才能解决问
        题。看看下面的多线程代码是否会产生死锁：
        class Foo implements Runnable {
            public void run() {
                // 执行递归函数
                fun(10);
            }
            // 递归函数
            public synchronized void fun(int i) {
                if (--i > 0) {
                    for (int j=0; j<i; j++) {
                        System.out.print("*");
                    }
                    System.out.println(i);
                    fun(i);
                }
            }
        }
        注意fun方法是一个递归函数，而且还加上了synchronized关键字，它保证同时只有一个线程能够执行，想想
        synchronized关键字的作用：当一个带有synchronized关键字的方法在执行时，其他synchronized方法会
        被阻塞，因为线程持有该对象的锁。比如有这样的代码：
        static class Foo {
            public synchronized void m1() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 异常处理
                }
                System.out.println("m1执行完毕");
            }
            public synchronized void m2() {
                System.out.println("m2执行完毕");
            }
        }
        public static void main(String[] args) throws Exception {
            final Foo foo = new Foo();
            // 定义一个线程
            Thread t = new Thread(new Runnable() {
                public void run() {
                    foo.m1();
                }
            });
            t.start();
            // 等待t1线程启动完毕
            Thread.sleep(10);
            // m2方法需要等待m1执行完毕
            foo.m2();
        }
        思考一下上例中带有synchronized的递归函数是否能执行？会不会产生死锁？运行成功，打印一个倒三角
        形，没有产生死锁，正常执行，这是为何呢？很奇怪，是吗？那是因为在运行时当前线程（Thread-0）获
        得了foo对象的锁（synchronized虽然是标注在方法上的，但实际作用的是整个对象），也就是该线程持
        有了foo对象的锁，所以它可以多次重入fun方法，也就是递归了。可以这样来思考问题，一个宝箱有N把钥
        匙，分别由N个海盗持有（也就是我们Java中的线程了），但是同一时间只能由一把钥匙打开宝箱，获取宝
        物，只有在上一个海盗关闭了宝箱（释放锁）后，其他海盗才能继续打开锁获取宝物，这里有一个规则：一
        旦一个海盗打开了宝箱，则该宝箱内的所有宝物对他来说都是开放的，即使是“宝箱中的宝箱”（即内箱）对
        他也是开放的。可以用以下代码来表述。
        class Foo implements Runnable {
            public void run() {
                method1();
            }
            public synchronized void method1() {
                method2();
            }
            public synchronized void method2() {
                // Do Something
            }
        }
        方法method1是synchronized修饰的，方法method2也是synchronized修饰的，method1调用method2没有
        任何问题的，因为是同一个线程持有对象锁，在一个线程内多个synchronized方法重入完全是可行的，此种
        情况不会产生死锁。
        那什么情况下会产生死锁呢？看如下代码：
        // 资源 A
        static class A {
            public synchronized void a1(B b) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " 进入A.a1()");
                try {
                    // 休眠1秒，仍然持有锁
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 异常处理
                }
                System.out.println(name + " 视图访问B.b2()");
                b.b2();
            }
            public synchronized void a2() {
                System.out.println("进入 a.a2()");
            }
        }
        // 资源 B
        static class B {
            public synchronized void b1(A a) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " 进入B.b1()");
                try {
                    // 休眠1秒，仍然持有锁
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // 异常处理
                }
                System.out.println(name + "视图访问A.a2()");
                a.a2();
            }
            public synchronized void b2() {
                System.out.println("进入B.b2()");
            }
        }
        public static void main(String[] args) {
            final A a = new A();
            final B b = new B();
            // 线程 A
            new Thread(new Runnable() {
                public void run() {
                    a.a1(b);
                }
            }, "线程 A").start();
            // 线程 B
            new Thread(new Runnable() {
                public void run() {
                    b.b1(a);
                }
            }, "线程 B").start();
        }
        此段程序定义了两个资源A和B，然后两个线程A、B中使用了该资源，由于两个资源之间有交互操作，并且都
        是同步方法，因此在线程A休眠1秒钟后，它会试图访问资源B的b2方法，但是线程B持有该类的锁，并同时在
        等待A线程释放其锁资源，所以此时就出现了两个线程在相互等待释放资源的情况，也就是死锁了，运行结果
        如下：
        线程 A 进入A.a1()
        线程 B 进入B.b1()
        线程 B 试图访问A.a2()
        线程 A 试图访问B.b2()
        此种情况下，线程A和线程B会一直互等下去，直到有外界干扰为止，比如终止一个线程，或者某一线程自行
        放弃资源的争抢，否则这两个线程就始终处于死锁状态了。我们直到要到达线程死锁需要四个条件：
        -互斥条件：一个资源每次只能被一个线程使用
        -资源独占条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放
        -不剥夺条件：线程已获得的资源在未使用完之前，不能强行剥夺
        -循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系
        只有满足了这些条件才可能产生线程死锁，这也同时告诫我们如果要解决线程死锁问题，就必须从这四个条件
        入手，一般情况下可以按照以下两种方式来解决：
        1）避免或减少资源共享
        一个资源被多个线程共享，若采用了同步机制，则产生的死锁可能性很大，特别是在项目比较庞大的情况下，
        很难杜绝死锁，对此最好的解决方法就是减少资源共享
        2）使用自旋锁
        回到前面的例子，线程A在等待线程B释放资源，而线程B又在等待线程A释放资源，僵持不下，那如果线程B设
        置了超时是不是就可以解决该死锁问题了呢？比如线程B在等待2秒后还是无法获得资源，则自行终结该任务，
        代码如下：
        public void b2() {
            try {
                // 立刻获得锁，或者2秒等待锁资源
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("进入B.b2()");
                }
            } catch (InterruptedException e) {
                // 异常处理
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
        上面代码中使用tryLock实现了自旋锁（Spin Lock），它跟互斥锁一样，如果一个执行单元要想访问被自旋
        锁保护的共享资源，则必须先得到锁，在访问完共享资源后，也必须释放锁。如果在获取自旋锁时，没有任何
        执行单元保持该锁，那么将立即得到锁；如果在获取自旋锁时锁已经有保持者，那么获取锁操作将“自旋”在那
        里，直到该自旋锁的保持者释放了锁为止。在上例中就是线程A等待线程B释放锁，在2秒内不断尝试是否能够
        获得锁，达到2秒后还未获得锁资源，线程A则结束运行，线程B将获得资源继续执行，死锁解除
        对于死锁的描述最经典的案例是哲学家进餐（五位哲学家围坐在一张圆形餐桌旁，人手一根筷子，做以下两件
        事情：吃饭和思考。要求吃东西的时候停止思考，思考的时候停止吃东西，而且必须使用两根筷子才能吃东西），
        解决此问题的方法很多，比如引入服务生（资源调度）、资源分级处理等方法都可以很好地解决此类死锁问题。
        在Java多线程并发编程中，死锁很难避免，也不容易预防，对付它的最好办法就是测试：提高测试覆盖率，建
        立有效的边界测试，加强资源监控，这些方法能使死锁无处遁形，即使发生了死锁现象也能迅速查找到原因，
        提高系统的可靠性
         */
        /**
         * 注意
         * 形成死锁的四个条件（互斥、资源独占、不剥夺、循环等待）
         * 解决死锁：避免或减少资源共享、使用自旋锁、提高测试覆盖率
         */
    }

    /**
     * 建议129： 适当设置阻塞队列长度
     */
    private void suggest129() {
        /*
        阻塞队列BlockingQueue扩展了Queue、Collection接口，对元素的插入和提取使用了“阻塞”处理，我们知
        道Collection下的实现类都采用了长度自行管理方式（也就是变长）。那BlockingQueue也是集合，也实现
        了Collection接口，它的容量是否会自行管理呢？来看代码：
        public static void main(String[] args) throws Exception {
            // 定义初始长度为5
            BlockingQueue<String> bq = new ArrayBlockingQueue<String>(5);
            // 加入10个元素
            for (int i=0; i<10; i++) {
                bq.add("");
            }
        }
        BlockingQueue能够自行扩容吗？答案是不能，运行结果如下：
        ...IllegalStateException:Queue full
        报队列已满异常，这是阻塞队列和非阻塞队列一个重要区别：阻塞队列的容量是固定的，非阻塞队列则是变长
        的。阻塞队列可以在声明时指定队列的容量，若指定了容量，则元素的数量不可超过该容量，若不指定，队列
        的容量为Integer的最大值
        阻塞队列和非阻塞队列有此区别的原因是阻塞队列是为了容纳（或排序）多线程任务而存在的，其服务的对象
        是多线程应用，而非阻塞队列容纳的则是普通的数据元素。ArrayBlockingQueue在加入元素时，如果判断出
        当前队列已满，则返回false，表示插入失败，之后在包装成队列满异常。此处需要注意offer方法，如果直
        接调用offer方法插入元素，在超出容量的情况下，它除了返回false外，不会提供任何其他信息，如果我们
        的代码不做插入判断，那就会造成数据的“默默”丢失，这就是它与非阻塞队列的不同之处
        阻塞队列的这种机制对异步计算是非常有帮助的，例如定义深度为100的阻塞队列容纳100个任务，多个线程
        从该队列中获取任务并处理，当所有的线程都在繁忙，并且队列中任务数量已经为100时，也预示着系统运算
        压力非常巨大，而且处理结果的时间也会比较长，于是在第101个任务期望加入时，队列拒绝加入，而且返回
        异常，由系统自行处理，避免了异步运算的不可知性。但是如果应用期望无论等待多长时间都要运行该任务，
        不希望返回异常，那该怎么处理呢？
        此时就需要用BlockingQueue接口定义的put方法了，它的作用也是把元素加入到队列中，但它与add、offer
        方法不同，它会等待队列空出元素，再让自己加入进去，通俗地讲，put方法提供的是一种“无赖”式的插入，
        无论等待多长时间都要把该元素插入到队列中，它的实现代码如下：
        public void put(E e) throws InterruptedException {
            // 容纳元素的数组
            final E[] items = this.items;
            final ReentrantLock lock = this.lock;
            // 可中断锁
            lock.lockInterruptibly();
            try {
                try {
                    // 队列满，等待其他线程移除元素
                    while (count == items.length)
                        notFull.await();
                } catch (InterruptedException ie) {
                    // 被中断了，唤醒其他线程
                    notFull.signal();
                    throw ie;
                }
                // 插入元素
                insert(e);
            } finally {
                // 释放锁
                lock.unlock();
            }
        }
        put方法的目的就是确保元素肯定会加入到队列中，问题是此种等待是一个循环，会不停地消耗系统资源，
        当等待加入的元素数量较多时势必会对系统性能产生影响，那该如何解决呢？JDK已经想到了这个问题，它
        提供了带有超时时间的offer方法，其实现方法与put比较类似，只是使用Condition的awaitNanos方法来
        判断当前线程已经等待了多少纳秒，超时则返回false
        与插入元素相对应，取出元素也有不同的实现，例如remove、poll、take等方法，对于此类方法的理解要
        建立在阻塞队列的长度固定的基础上，然后根据是否阻塞、阻塞是否超时等实际情况选用不同的方法
         */
        /**
         * 注意
         * 阻塞队列的长度是固定的
         */
    }

    /**
     * 建议130： 使用CountDownLatch协调子线程
     */
    private void suggest130() {
        /*
        思考这样一个案例：百米赛跑，多个参加赛跑的人员在听到发令枪响后，开始跑步，到达终点后结束计时，
        然后统计平均成绩。这里有两点需要考虑：一是发令枪响，这是所有跑步者（线程）接受到的出发信号，此
        处涉及裁判（主线程）如何通知跑步者（子线程）的问题：二是如何获知所有的跑步者完成了赛跑，也就是
        主线程如何直到子线程已经全部完成，这有很多种实现方式，此处我们使用CountDownLatch工具类来实现，
        代码如下：
        static class Runner implements Callable<Integer> {
            // 开始信号
            private CountDownLatch begin;
            // 结束信号
            private CountDownLatch end;

            public Runner(CountDownLatch _begin, CountDownLatch _end) {
                begin = _begin;
                end = _end;
            }

            @Override
            public Integer call() throws Exception {
                // 跑步的成绩
                int score = new Random().nextInt(25);
                // 等待发令枪响起
                begin.await();
                // 跑步中...
                TimeUnit.MILLISECONDS.sleep(score);
                // 跑步者已经跑完全程
                end.countDown();
                return score;
            }
        }

        public static void main(String[] args) throws Exception {
            // 参加赛跑人数
            int num = 10;
            // 发令枪只响一次
            CountDownLatch begin = new CountDownLatch(1);
            // 参与跑步有多个
            CountDownLatch end = new CountDownLatch(num);
            // 每个跑步者一个跑道
            ExecutorService es = Executors.newFixedThreadPool(num);
            // 记录比赛成绩
            List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
            // 跑步者就位，所有线程处于等待状态
            for (int i=0; i<num; i++) {
                futures.add(es.submit(new Runner(begin, end)));
            }
            // 发令枪响，跑步者开始跑步
            begin.countDown();
            // 等待所有跑步者跑完全程
            end.await();
            int count = 0;
            // 统计总分
            for (Future<Integer> f : futures) {
                count += f.get();
            }
            // 统计总分
            System.out.println("平均分数为：" + count / num);
        }
        CountDownLatch类是一个倒数的同步计数器，在程序中启动了两个计数器：一个是开始计数器begin，表
        示的是发令枪；另外是结束计数器，一共有10个，表示的是每个线程的执行情况，也就是跑步者是否跑完比
        赛。程序执行逻辑如下：
        1）10个线程都开始运行，执行到begin.await后线程阻塞，等待begin的计数变为0
        2）主线程调用begin的countDown方法，是begin的计数器为0
        3）10个线程继续运行
        4）主线程继续运行下一个语句，end的计数器不为0，主线程等待
        5）每个线程运行结束时把end的计数器减1，标志者本线程运行完毕
        6）10个线程全部结束，end计数器为0
        7）主线程继续执行，打印出成绩平均值
        CountDownLatch的作用是控制一个计数器，每个线程在运行完毕后会执行countDown，表示自己运行结束，
        这对于多个子任务的计算特别有效，比如一个异步任务需要拆分成10个子任务执行，主任务必须要知道子任
        务是否完成，所有子任务完成后才能进行合并计算，从而保证了一个主任务的逻辑正确性。这和我们的实际
        工作非常类似，比如领导安排了一个大任务为我，我一个人不可能完成，于是我把该任务分解给10个人做，
        在10个人全部完成后，我把这10个结果组合起来返回给领导--这就是CountDownLatch的作用
         */
        /**
         * 注意
         * 详细了解下CountDownLatch的使用和原理
         */
    }

    /**
     * 建议131： CyclicBarrier让多线程齐步走
     */
    private void suggest131() {
        /*
        思考这样一个案例：两个工人从两端挖掘隧道，各自独立奋战，中间不沟通，如果两人在汇合点处碰头了，
        则表明隧道已经挖通。这描述的也是在多线程编程中，两个线程独立运行，没有线程间通信的情况下，如何
        解决两个线程汇集在同一原点的问题。Java提供了CyclicBarrier（关卡，也有翻译为栅栏）工具类来实
        现，代码如下：
        static class Worker implements Runnable {
            // 关卡
            private Worker(CyclicBarrier _cb) {
                cb = _cb;
            }
            public void run() {
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + " - 到达汇合点");
                    // 到达汇合点
                    cb.await();
                } catch (Exception e) {
                    // 异常处理
                }
            }
        }
        public static void main(String[] args) throws Exception {
            // 设置汇集数量，以及汇集完成后的任务
            CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
                public void run() {
                    System.out.println("隧道已经打通！");
                }
            });
            // 工人1挖隧道
            new Thread(new Worker(cb), "工人1").start();
            // 工人2挖隧道
            new Thread(new Worker(cb), "工人2").start();
        }
        在这段程序中，定义了一个需要等待2个线程汇集的CyclicBarrier关卡，并且定义了完成汇集后的任务
        （输出“隧道已经打通！”），然后启动了2个线程（也就是2个工人）开始执行任务。代码逻辑如下：
        1）2个线程同时开始运行，实现不同的任务，执行时间不同
        2）“工人1”线程首先到达汇合点（也就是cb.await语句），转变为等待状态
        3）“工人2”线程到达汇合点，满足预先的关卡条件（2个线程到达关卡），继续执行。此时还会额外的执
        行两个动作，执行关卡任务（也就是run方法）和唤醒“工人1”线程
        4）“工人1”线程继续执行
        CyclicBarrier关卡可以让所有线程全部处于等待状态（阻塞），然后在满足条件的情况下继续执行，这
        就好比是一条起跑线，不管是如何到达起跑线的，只要到达这条起跑线就必须等待其他人员，待人员到齐
        后再各奔东西，CyclicBarrier关注的是汇合点的信息，而不在乎之前或之后做何处理
        CyclicBarrier可以用在系统的性能测试中，例如编写了一个核心算法，但不能确定其可靠性和效率如何，
        就可以让N个线程汇集到测试原点上，然后“一声令下”，所有的线程都引用该算法，即可观察出算法是否有
        缺陷
         */
        /**
         * 注意
         * 详细了解下CyclicBarrier的使用和原理
         */
    }
}
