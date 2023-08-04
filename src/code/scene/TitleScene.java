package code.scene;

import code.Main;
import code.utils.KeyManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleScene extends Scene{
    int x=100;
    int y=100;

    int WIDTH= Main.WIDTH;
    int HEIGHT= Main.HEIGHT;

    public void init(){
        super.init();
    }
    public void update(){
        super.update();
        if(KeyManager.isPress(KeyEvent.VK_RIGHT)){
            x+=5;
            x%=WIDTH;
        }
        if(KeyManager.isPress(KeyEvent.VK_LEFT)){
            x-=5;
            x=(x+WIDTH)%WIDTH;
        }
        if(KeyManager.isPress(KeyEvent.VK_UP)){
            y-=5;
            y=(y+HEIGHT)%HEIGHT;
        }
        if(KeyManager.isPress(KeyEvent.VK_DOWN)){
            y+=5;
            y%=HEIGHT;
        }
    }
    public Graphics2D updateGraphics(Graphics2D g2){
        g2=super.updateGraphics(g2);
        g2.setColor(Color.white);
        g2.fillRect(0,0,1280,960);

        g2.setColor(Color.BLACK);
        g2.fillOval(x,y,30,30);
        g2.drawString("0",0,10);
        return null;
    }
}
