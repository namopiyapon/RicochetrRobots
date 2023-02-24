package testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class wall {

    private int width, height;
    private BufferedImage wall;

    public wall(int size) {
        this.width = size;
        this.height = size;
    }

    //ออกแบบ BodyPart
    public void draw(Graphics g) {
        try {

            wall = ImageIO.read(new File("image/wall.png"));

        } catch (IOException ex) {
            // handle exception...
        }

        //8-9
        g.drawImage(wall,7 * width, 7 * height, 40, 40, null);//--
        g.drawImage(wall,8 * width, 7 * height, 40, 40, null);
        g.drawImage(wall,7 * width, 8 * height, 40, 40, null);//__
        g.drawImage(wall,8 * width, 8 * height, 40, 40, null);

        g.setColor(new Color(102,51,0));
        //1
        g.fillRect(4 * width, 0 * height, 5, 40);// |
        g.fillRect(12 * width, 0 * height, 5, 40);// |
        //2
        g.fillRect(1 * width, 1 * height, 40, 5);//-|
        g.fillRect(2 * width, 1 * height, 5, 40);
        g.fillRect(14 * width, 1 * height, 5, 40);//_|
        g.fillRect(13 * width, 2 * height, 40, 5);
        //3
        g.fillRect(10 * width, 2 * height, 5, 40);//-|
        g.fillRect(9 * width, 2 * height, 40, 5);
        //4
        g.fillRect(2 * width, 3 * height, 5, 40);//_|
        g.fillRect(1 * width, 4 * height, 40, 5);
        g.fillRect(6 * width, 3 * height, 5, 40);//|_
        g.fillRect(6 * width, 4 * height, 40, 5);
        g.fillRect(13 * width, 3 * height, 5, 40);//|_
        g.fillRect(13 * width, 4 * height, 40, 5);
        g.fillRect(15 * width, 4 * height, 40, 5);// _
        //6
        g.fillRect(9 * width, 5 * height, 40, 5);//|-
        g.fillRect(9 * width, 5 * height, 5, 40);
        //7
        g.fillRect(0 * width, 6 * height, 40, 5);//-
        g.fillRect(4 * width, 6 * height, 40, 5);//|-
        g.fillRect(4 * width, 6 * height, 5, 40);
        
        //10
        g.fillRect(3 * width, 10 * height, 5, 40);//_|
        g.fillRect(2 * width, 11 * height, 40, 5);
        g.fillRect(13 * width, 10 * height, 5, 40);//|_
        g.fillRect(13 * width, 11 * height, 40, 5);
        g.fillRect(15 * width, 10 * height, 40, 5); //_
        //11
        g.fillRect(7 * width, 11 * height, 5, 40);//-|
        g.fillRect(6 * width, 11 * height, 40, 5);
        //12
        g.fillRect(0 * width, 13 * height, 40, 5);// _
        g.fillRect(4 * width, 12 * height, 5, 40);//|_
        g.fillRect(4 * width, 13 * height, 40, 5);
        g.fillRect(9 * width, 12 * height, 40, 5);//|-
        g.fillRect(9 * width, 12 * height, 5, 40);
        g.fillRect(14 * width, 12 * height, 5, 40);//_|
        g.fillRect(13 * width, 13 * height, 40, 5);
        //13
        g.fillRect(10 * width, 13 * height, 40, 5);//-|
        g.fillRect(11 * width, 13 * height, 5, 40);
        //15
        g.fillRect(1 * width, 14 * height, 40, 5);//|-
        g.fillRect(1 * width, 14 * height, 5, 40);
        //16
        g.fillRect(3 * width, 15 * height, 5, 40);// |
        g.fillRect(12 * width, 15 * height, 5, 40);// |
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
