package com.uv.log4jReload;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        int pid = getProcessID();

        Thread t = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                log.debug(pid + "debug:" + i);
                log.info(pid + "info:" + i);
                log.error(pid + "error:" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
        });
        t.setDaemon(false);
        t.start();
        SignalHandler sh = signal -> {
            log.debug("signal:" + signal.getName() + "@" + signal.getNumber());

            switch (signal.getNumber()) {
                case 12://signal：USR2 //reload Reloadable component and goon
                    break;
                case 20://signal: TSTP //把可暂停的组件暂停
                    break;
                case 18://signal: CONT //把暂停的组件表示继续
                    break;
                case 28://signal: WINCH,表示要处理跨月测点状态
                    break;
                case 25:
                    log.debug("reload configure file!");
                    PropertyConfigurator.configure(loadProperties());
                    log.debug("reload configure file ok!");
                    break;
                default:
                    log.debug("signal:can not recognize");
            }
        };

        Signal.handle(new Signal("USR2"), sh);
        //SIGTSTP
        Signal.handle(new Signal("TSTP"), sh);
        //SIGCONT
        Signal.handle(new Signal("CONT"), sh);

        Signal.handle(new Signal("WINCH"), sh);//处理跨月测点状态信号

        Signal.handle(new Signal("XFSZ"), sh);//25
        Signal.handle(new Signal("VTALRM"), sh);//26
        Signal.handle(new Signal("PROF"), sh);//27

    }

    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0]);
    }

    private static Properties loadProperties() {
        Properties p = null;

        InputStream is;
        is = Object.class.getResourceAsStream("log4j.properties");
        if (null == is) {
            is = ClassLoader.getSystemResourceAsStream("log4j.properties");
        }
        if (null == is) {
            try {
                is = new FileInputStream("log4j.properties");
            } catch (FileNotFoundException e) {
                log.error("log4j.properties can not found!", e);
            }
        }
        if (is != null) {
            p = new Properties();
            try {
                p.load(is);
            } catch (IOException e) {
                log.error("load log4j.properties error!", e);
            }
        }
        return p;
    }

}
