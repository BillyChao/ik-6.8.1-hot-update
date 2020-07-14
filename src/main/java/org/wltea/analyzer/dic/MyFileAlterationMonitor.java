package org.wltea.analyzer.dic;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class MyFileAlterationMonitor {
    private String path;
    /**
     * 事件处理类对象
     */
    private FileAlterationListenerAdaptor listener;
    /**
     * 监听间隔，默认监听间隔10s
     */
    private long interval;
    private static final long DEFAULT_INTERVAL = 10 * 1000;
    private String fileSuffix;

    public MyFileAlterationMonitor(String path, FileAlterationListenerAdaptor listener, long interval, String fileSuffix) {
        this.path = path;
        this.listener = listener;
        this.interval = interval;
        this.fileSuffix = fileSuffix;
    }

    /***
     * 开启监听
     */
    public void start() {
        if (path == null) {
            throw new IllegalStateException("Listen path must not be null");
        }
        if (listener == null) {
            throw new IllegalStateException("Listener must not be null");
        }

        // 设定观察者，监听.dic 文件，有变化则重新加载字典
        FileAlterationObserver observer = new FileAlterationObserver(path,
                FileFilterUtils.suffixFileFilter(fileSuffix));

        // 给观察者添加监听事件
        observer.addListener(listener);

        // 开启一个监视器，监听频率是5s一次
        // FileAlterationMonitor本身实现了 Runnable，是单独的一个线程，按照设定的时间间隔运行，默认间隔是 10s
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);

        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
