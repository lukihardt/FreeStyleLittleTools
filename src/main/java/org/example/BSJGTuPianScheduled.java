package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.sikuli.script.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BSJGTuPianScheduled implements NativeKeyListener {
    private final AtomicBoolean inLoop = new AtomicBoolean(false);
    private final AtomicBoolean running = new AtomicBoolean(true);

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();  // 定时任务线程池
    private final Screen screen = new Screen();
    private final Region region = new Region(810,290,300,500);  // **限定检测区域**

    private final String[] images = {
            "D:\\PICTURE\\fs\\tanSmall-fotor-20250401.png",
            "D:\\PICTURE\\fs\\yinSmall-fotor-20250401.png",
            "D:\\PICTURE\\fs\\jinSmall-fotor-20250401.png",
            "D:\\PICTURE\\fs\\hongSmall-fotor-20250401.png",
            "D:\\PICTURE\\fs\\lanSmall-fotor-20250401.png"
    };

    private final Pattern[] patterns = new Pattern[images.length];

    public BSJGTuPianScheduled() {
        for (int i = 0; i < images.length; i++) {
            patterns[i] = new Pattern(images[i]).similar(0.9);
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String keyText = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());

        if ("R".equals(keyText) && !inLoop.get()) {
            inLoop.set(true);
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                while (inLoop.get() && ((System.currentTimeMillis() - startTime) / 1000) < 17) {
                    screen.type(Key.SPACE);
                    screen.type(Key.DOWN);
                }
                inLoop.set(false);
            }).start();
        }

        if ("Q".equals(keyText) && inLoop.get()) {
            inLoop.set(false);
        }
    }

    public void startDetection() {
        scheduler.scheduleWithFixedDelay(() -> {
            if (!running.get()) return;

            for (int i = 0; i < patterns.length; i++) {
                try {
                    Match match = region.find(patterns[i]);
                    if (match != null) {
                        System.out.println("发现 " + images[i]);
                        for (int j = 0; j <= i; j++) {
                            screen.type(Key.SPACE);
                        }
                        screen.type(Key.DOWN);
                        break;
                    }
                } catch (Exception e) {
                    // e.printStackTrace();
                }
            }

//            Match match=region.exists(patterns[0]);
//            if(match!=null){
//                System.out.println("发现碳");
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//
//        }, 0, 7, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(() -> {
//            if (!running.get()) return;
//            Match match=region.exists(patterns[1]);
//            if(match!=null){
//                System.out.println("发现银");
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//
//        }, 0, 7, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(() -> {
//            if (!running.get()) return;
//            Match match=region.exists(patterns[2]);
//            if(match!=null){
//                System.out.println("发现金");
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//
//        }, 0, 7, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(() -> {
//            if (!running.get()) return;
//            Match match=region.exists(patterns[3]);
//            if(match!=null){
//                System.out.println("发现红宝石");
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//
//        }, 0, 7, TimeUnit.MILLISECONDS);
//        scheduler.scheduleAtFixedRate(() -> {
//            if (!running.get()) return;
//            Match match=region.exists(patterns[4]);
//            if(match!=null){
//                System.out.println("发现蓝宝石");
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//
        }, 0, 100, TimeUnit.MILLISECONDS);
    }


    public static void main(String[] args) throws NativeHookException {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        GlobalScreen.registerNativeHook();
        BSJGTuPianScheduled instance = new BSJGTuPianScheduled();
        GlobalScreen.addNativeKeyListener(instance);

        instance.startDetection();
    }
}
