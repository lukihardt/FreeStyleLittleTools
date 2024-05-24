package org.example;
import org.junit.jupiter.api.Test;
import org.sikuli.script.*;

public class Shaizi {
    @Test
    void
    run(){

        Screen screen=new Screen();
        try {
            Thread.sleep(7100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 410; i++) {
            screen.type(Key.ENTER);
            screen.type(Key.ENTER);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            int locationX=screen.getX();
//            int locationY=screen.getY();
//            try {
//                screen.click(locationX,locationY);
//                screen.click(locationX,locationY);
//            } catch (FindFailed e) {
//                throw new RuntimeException(e);
//            }

            screen.mouseDown(Button.LEFT);
            screen.mouseUp(Button.LEFT);
            screen.mouseDown(Button.LEFT);
            screen.mouseUp(Button.LEFT);
        }
    }
}
