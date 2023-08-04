package code;

import code.scene.Scene;
import code.scene.TitleScene;
import code.utils.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static final int FPS = 60;


    public static JFrame fr;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;

    public static Scene scene;

    static BufferedImage bi;

    public static void main(String[] args) {
        fr=new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
        fr.setBounds(0,0,WIDTH,HEIGHT);

        bi=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);

        KeyManager km=new KeyManager();
        km.init(fr);

        replaceScene(new TitleScene());

        long error = 0;
        long idealSleep = (1000 << 16) / FPS;
        long oldTime;
        long newTime = System.currentTimeMillis() << 16;
        while (true) {
            oldTime = newTime;
            update();
            newTime = System.currentTimeMillis() << 16;
            long sleepTime = idealSleep - (newTime - oldTime) - error; // 休止できる時間
            if (sleepTime < 0)
                sleepTime = 0;
            oldTime = newTime;
            try {
                Thread.sleep(sleepTime >> 16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newTime = System.currentTimeMillis() << 16;
            error = newTime - oldTime - sleepTime;
        }
    }
    public static void replaceScene(Scene s){
        scene=s;
        scene.init();
    }
    static void update(){
        KeyManager.update();
        scene.update();
        Graphics2D g2=(Graphics2D) bi.getGraphics();
        scene.updateGraphics(g2);
        ((Graphics2D)fr.getContentPane().getGraphics()).drawImage(bi,0,0,null);
    }
}