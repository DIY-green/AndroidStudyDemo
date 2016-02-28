package com.cheng.zenofdesignpatterns.extension.newpatterns.objectpool;

import java.util.Hashtable;

/**
 * 对象池示例
 */
public abstract class ObjectPool<T> {

    // 容器，容纳对象
    private Hashtable<T, ObjectStatus> pool = new Hashtable<>();

    // 初始化时创建对象，并放入到池中
    public ObjectPool() {
        pool.put(create(), new ObjectStatus());
    }

    // 从Hashtable中取出空闲元素
    public synchronized T checkOut() {
        // 这是最简单的策略
        for (T t : pool.keySet()) {
            if (pool.get(t).validate()) {
                pool.get(t).setUsing();
                return t;
            }
        }
        return null;
    }

    // 创建池化对象
    public abstract T create();

    // 归还元素
    public synchronized void checkIn(T t) {
        pool.get(t).setFree();
    }

    class ObjectStatus {
        // 占用
        public void setUsing() {

        }
        // 释放
        public void setFree() {
            // 注意：若T是有状态，则需要回归到初始状态
        }
        // 检查是否可用
        public boolean validate() {
            return false;
        }
    }

}
