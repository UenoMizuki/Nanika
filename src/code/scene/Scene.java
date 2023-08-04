package code.scene;

import java.awt.*;

public abstract class Scene {
    public void init(){}
    public void update(){}
    public Graphics2D updateGraphics(Graphics2D g2){return g2;}
}
