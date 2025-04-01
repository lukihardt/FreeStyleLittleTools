package org.example;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.junit.jupiter.api.Test;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBaoShiJiaGong implements NativeKeyListener {
    private volatile boolean inLoop=false;
    @Test
    synchronized void run() {
        try {
            Thread.sleep(4100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            throw new RuntimeException(e);
        }
        GlobalScreen.addNativeKeyListener(new TestBaoShiJiaGong());

        while (true) {
            try {
                Thread.sleep(10); // 让主线程休眠，不让程序退出
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

// 以下使用图片识别各种宝石
//    void run() throws InterruptedException {
//
//        Screen screen=new Screen();
//        String tan="D:\\PICTURE\\fs\\tan.png";
//        String yin="D:\\PICTURE\\fs\\yin.png";
//        String jin="D:\\PICTURE\\fs\\jin.png";
//        String hong="D:\\PICTURE\\fs\\hong.png";
//        String lan="D:\\PICTURE\\fs\\lan.png";
//
//        Pattern tanpa=new Pattern(tan);
//        Pattern yinpa=new Pattern(yin);
//        Pattern jinpa=new Pattern(jin);
//        Pattern hongpa=new Pattern(hong);
//        Pattern lanpa=new Pattern(lan);
//
//        Thread.sleep(7100);
//
//        while (true){
//            if (screen.exists(tanpa) != null){
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//            if (screen.exists(yinpa) != null){
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//            if (screen.exists(jinpa) != null){
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//            if (screen.exists(hongpa) != null){
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//            if (screen.exists(lanpa) != null){
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.SPACE);
//                screen.type(Key.DOWN);
//            }
//        }
//
//    }

    @Override
    synchronized public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        String keyText = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        Screen screen = new Screen();
        if ("A".equals(keyText)) {
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.DOWN);
        }
        if ("S".equals(keyText)) {
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.DOWN);
        }
        if ("D".equals(keyText)) {
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.DOWN);
        }
        if ("F".equals(keyText)) {
            screen.type(Key.SPACE);
            screen.type(Key.SPACE);
            screen.type(Key.DOWN);
        }
        if ("G".equals(keyText)) {
            screen.type(Key.SPACE);
            screen.type(Key.DOWN);
        }
        int keyCode=nativeEvent.getKeyCode();
        if (keyCode==NativeKeyEvent.VC_ESCAPE) {
            System.out.println("ESC被按下退出监听");
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (NativeHookException e) {
                throw new RuntimeException(e);
            }
        }
        if ("R".equals(keyText) && !inLoop){
            inLoop=true;
            long timeStamps=System.currentTimeMillis();
            new Thread(
                    ()->{
                        while (inLoop && (((System.currentTimeMillis()-timeStamps)/1000)<17)){
                            // keyText = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
                                screen.type(Key.SPACE);
                                screen.type(Key.DOWN);
                        }
                    }
            ).start();

        }
        if ("Q".equals(keyText) && inLoop){
            inLoop=false;
        }

    }
}
