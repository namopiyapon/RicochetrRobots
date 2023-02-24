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

public class BodyPart1 {

    private int x, y, width, height;
    private BufferedImage bot;

    public BodyPart1(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
    }

    //ออกแบบ BodyPart
    public void draw(Graphics g) {
        try {

            bot = ImageIO.read(new File("image/number2.png"));

        } catch (IOException ex) {
            // handle exception...
        }
        g.drawImage(bot, x * width, y * height, width, height, null);
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
