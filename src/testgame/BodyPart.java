
package testgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static testgame.Scene.HEIGHT;
import static testgame.Scene.WIDTH;

public class BodyPart {
    private int x,y,width,height;
    
    private BufferedImage bot;
    
    public BodyPart(int x,int y,int size){
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }
    //ออกแบบ BodyPart
    public void draw(Graphics g){
        try {

            bot = ImageIO.read(new File("image/number1.png"));

        } catch (IOException ex) {
            // handle exception...
        }
        g.drawImage(bot, x*width, y*height, width, height, null);
        //g.setColor(Color.BLACK);
        //g.fillRect(x*width+5, y*height+5, width-10, height-10);
        //g.setColor(Color.orange);
        //g.setFont(new Font("Tahoma", Font.BOLD, 30));
        //g.drawString("1", x*width+10, y*height+30);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}

