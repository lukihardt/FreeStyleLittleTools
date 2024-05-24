package org.example;

import org.sikuli.script.Screen;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        Screen screen=new Screen();

        try {
            Thread.sleep(7100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            for (int i = 0; i < 410; i++) {
                screen.type("d");

                screen.keyDown("i");
                Thread.sleep(2710);
                screen.keyUp("i");

                screen.keyDown("j");
                Thread.sleep(210);
                screen.keyUp("j");

                screen.keyDown("k");
                Thread.sleep(410);
                screen.keyUp("k");

                screen.keyDown("k");
                Thread.sleep(50);
                screen.keyUp("k");

                screen.type("s");
                screen.keyDown("s");
                Thread.sleep(270);
                screen.keyUp();

                screen.keyDown("i");
                Thread.sleep(527);
                screen.keyUp("i");

                screen.keyDown("i");
                Thread.sleep(5272);
                screen.keyUp("i");

                screen.keyDown("l");
                Thread.sleep(2710);
                screen.keyUp("l");

                screen.keyDown("d");
                Thread.sleep(270);
                screen.keyUp();

                screen.keyDown("s");
                Thread.sleep(521);
                screen.keyUp();

                screen.keyDown("s");
                Thread.sleep(21);
                screen.keyUp();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}