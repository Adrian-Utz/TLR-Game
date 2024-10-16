package TLRCode.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

public abstract class entity {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle2D.Float hitboxRectangle;

    public entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void hitboxDraw(Graphics g){
        /* This part of the code is for debugging */
        g.setColor(Color.RED);
        g.drawRect((int)hitboxRectangle.x, (int)hitboxRectangle.y, (int)hitboxRectangle.width, (int)hitboxRectangle.height);
    }

    protected void hitboxInitialize(float x, float y, int width, int height){
        hitboxRectangle = new Rectangle2D.Float(x, y, width, height);
    }

    protected void hitboxUpdate(){
        hitboxRectangle.x = (int) x;
        hitboxRectangle.y = (int) y;
    }

    public Rectangle2D.Float gethitbox(){
        return hitboxRectangle;
    }
}
