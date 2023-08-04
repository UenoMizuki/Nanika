package code.utils;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class KeyManager implements KeyListener {

    private static boolean []keyPre = new boolean[128];
    private static boolean []keyNow = new boolean[128];
    private static boolean []keyNext = new boolean[128];

    public void init(JFrame fr){
        fr.addKeyListener(this);
        Arrays.fill(keyPre, false);
        Arrays.fill(keyNow, false);
        Arrays.fill(keyNext, false);
    }

    public static void update(){
        keyPre=keyNow.clone();
        keyNow=keyNext.clone();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 128)
            keyNext[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 128)
            keyNext[e.getKeyCode()] = false;
    }

    public static boolean isPress(int key){
        if (key < 128)
            return keyNow[key];
        return false;
    }

    public static boolean onPress(int key){
        if (key < 128)
            return keyNow[key]&&!keyPre[key];
        return false;
    }
}
