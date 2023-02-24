//หน้าจอย่อ
package testgame;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Scene extends JPanel implements Runnable {

    private BufferedImage background;
//Score----------------
    int WIN = 0;
    int numbot = 0;
    int numplayer = 0;
    int playerScore = 0;
    int botScore = 0;
    int markx1, markx2, markx3, markx;
    int marky1, marky2, marky3, marky;
//end Score------------  
//Bot--------------------
    int botx1, botx2, botx3, botx;
    int boty1, boty2, boty3, boty;
    int Mbotx1, Mbotx2, Mbotx3, Mbotx;
    int Mboty1, Mboty2, Mboty3, Mboty;
    int num = 2;
    boolean stopbot = true;
//End bot----------------

    public static int WIDTH = 940;
    public static int HEIGHT = 640;
    private Thread th;
    private boolean running = false;

    //กำหนดคุณสมบัติของ ผู้เล่น 1
    private int n = 0;
    private BodyPart b;
    private ArrayList<BodyPart> robotArr;
    private int x = new Random().nextInt(10), y = new Random().nextInt(10);//จุดที่บอทเริ่ม
    private int size = 1;//ขนาดช่อง
    //กำหนดคุณสมบัติของ ผู้เล่น 2
    private BodyPart1 b1;
    private ArrayList<BodyPart1> robotArr1;
    private int x1 = new Random().nextInt(10), y1 = new Random().nextInt(10);//จุดที่บอทเริ่ม
    private int size1 = 1;//ขนาดช่อง
    //กำหนดคุณสมบัติของ ผู้เล่น 3
    private BodyPart2 b2;
    private ArrayList<BodyPart2> robotArr2;
    private int x2 = new Random().nextInt(10), y2 = new Random().nextInt(10);//จุดที่บอทเริ่ม
    private int size2 = 1;//ขนาดช่อง
    //กำหนดคุณสมบัติของ ผู้เล่น 4
    private BodyPart3 b3;
    private ArrayList<BodyPart3> robotArr3;
    private int x3 = new Random().nextInt(10), y3 = new Random().nextInt(10);//จุดที่บอทเริ่ม
    private int size3 = 1;//ขนาดช่อง

    //คุณสมบัติการเคลื่อนที่
    private boolean right = false, left = false, up = false, down = false;
    private int count = 0;
    //คุณสมบัติของไอเทม
    private Item i;
    private ArrayList<Item> itemArr;
    int xitem;
    int yitem;
    //กำแพง
    private wall j;
    private ArrayList<wall> wallArr;

    public Scene() {
        try {

                background = ImageIO.read(new File("image/background.jpg"));

            } catch (IOException ex) {
                // handle exception...
            }

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        itemArr = new ArrayList<>();
        robotArr = new ArrayList<>();
        robotArr1 = new ArrayList<>();
        robotArr2 = new ArrayList<>();
        robotArr3 = new ArrayList<>();
        wallArr = new ArrayList<>();
        running = true;
        th = new Thread(this);
        th.start();
        addKeyListener(new KeyInner());
        addMouseListener(new MouseControl());
        setFocusable(true);

    }
    //public void startgame(){
    //running=true;
    //th = new Thread(this);
    //th.start();
    //}

    @Override
    public void run() {
        while (running) {
            tick();
            if (num % 2 == 0) {
                F();
            }
            repaint();//การวาดใหม่
        }
    }

    //บันทึกมาร์คใหม่
    private void mark() {
        markx = x;
        marky = y;
        markx1 = x1;
        marky1 = y1;
        markx2 = x2;
        marky2 = y2;
        markx3 = x3;
        marky3 = y3;
    }

    //ควบคุมการเคลื่อนที่ของวัตถุ
    private void tick() {

        //------------------
        if (wallArr.size() == 0) {
            j = new wall(40);
            wallArr.add(j);
        }
        //------------------
        if (itemArr.size() == 0) {
            int[] Ritem = {0, 15};
            int xxitem = new Random().nextInt(Ritem.length);
            int yyitem = new Random().nextInt(Ritem.length);
            xitem = Ritem[xxitem];
            yitem = Ritem[yyitem];
            if ((xitem == 7 && yitem == 7) || (xitem == 7 && yitem == 8) || (xitem == 8 && yitem == 7) || (xitem == 8 && yitem == 8)) {
                xxitem = new Random().nextInt(Ritem.length);
                yyitem = new Random().nextInt(Ritem.length);
                xitem = Ritem[xxitem];
                yitem = Ritem[yyitem];
            }
            i = new Item(xitem, yitem, 40);
            itemArr.add(i);
        }
        if (robotArr.size() == 0) {
            while ((x == 7 && y == 7) || (x == 7 && y == 8) || (x == 8 && y == 7) || (x == 8 && y == 8)) {
                x = new Random().nextInt(10);
                y = new Random().nextInt(10);
            }
            b = new BodyPart(x, y, 40);
            markx = x;
            marky = y;
            botx = x;
            boty = y;
            Mbotx = x;
            Mboty = y;
            robotArr.add(b);
        }
        if (robotArr1.size() == 0) {
            while ((x1 == 7 && y1 == 7) || (x1 == 7 && y1 == 8) || (x1 == 8 && y1 == 7) || (x1 == 8 && y1 == 8) || (x1 == x && y1 == y)) {
                x1 = new Random().nextInt(10);
                y1 = new Random().nextInt(10);
            }
            b1 = new BodyPart1(x1, y1, 40);
            markx1 = x1;
            marky1 = y1;
            botx1 = x1;
            boty1 = y1;
            Mbotx1 = x1;
            Mboty1 = y1;
            robotArr1.add(b1);
        }
        if (robotArr2.size() == 0) {
            if ((x2 == 7 && y2 == 7) || (x2 == 7 && y2 == 8) || (x2 == 8 && y2 == 7) || (x2 == 8 && y2 == 8) || (x2 == x && y2 == y) || (x2 == x1 && y2 == y1)) {
                x2 = new Random().nextInt(10);
                y2 = new Random().nextInt(10);
            }
            b2 = new BodyPart2(x2, y2, 40);
            markx2 = x2;
            marky2 = y2;
            botx2 = x2;
            boty2 = y2;
            Mbotx2 = x2;
            Mboty2 = y2;
            robotArr2.add(b2);
        }
        if (robotArr3.size() == 0) {
            if ((x3 == 7 && y3 == 7) || (x3 == 7 && y3 == 8) || (x3 == 8 && y3 == 7) || (x3 == 8 && y3 == 8) || (x3 == x && y3 == y) || (x3 == x1 && y3 == y1) || (x3 == x2 && y3 == y2)) {
                x3 = new Random().nextInt(10);
                y3 = new Random().nextInt(10);
            }
            b3 = new BodyPart3(x3, y3, 40);
            markx3 = x3;
            marky3 = y3;
            botx3 = x3;
            boty3 = y3;
            Mbotx3 = x3;
            Mboty3 = y3;
            robotArr3.add(b3);
        }

        //ชนกำแพง
        count++;

        //ชนกำแพง Player----------------------
        if (count > 100000) {
            if (n == 1) {
                if (Move(x, y) == 1) {
                    x++;
                } else if (Move(x, y) == 2) {
                    x--;
                } else if (Move(x, y) == 3) {
                    y--;
                } else if (Move(x, y) == 4) {
                    y++;
                }
                botx = x;
                boty = y;
            }

            if (n == 2) {
                if (Move(x1, y1) == 1) {
                    x1++;
                } else if (Move(x1, y1) == 2) {
                    x1--;
                } else if (Move(x1, y1) == 3) {
                    y1--;
                } else if (Move(x1, y1) == 4) {
                    y1++;
                }
                botx1 = x1;
                boty1 = y1;
            }
            if (n == 3) {
                if (Move(x2, y2) == 1) {
                    x2++;
                } else if (Move(x2, y2) == 2) {
                    x2--;
                } else if (Move(x2, y2) == 3) {
                    y2--;
                } else if (Move(x2, y2) == 4) {
                    y2++;
                }
                botx2 = x2;
                boty2 = y2;
            }
            if (n == 4) {
                if (Move(x3, y3) == 1) {
                    x3++;
                }
                if (Move(x3, y3) == 2) {
                    x3--;
                }
                if (Move(x3, y3) == 3) {
                    y3--;
                }
                if (Move(x3, y3) == 4) {
                    y3++;
                }
                botx3 = x3;
                boty3 = y3;
            }
            count = 0;
            b = new BodyPart(x, y, 40);
            robotArr.add(b);
            if (robotArr.size() > size) {
                robotArr.remove(0);
            }
            b1 = new BodyPart1(x1, y1, 40);
            robotArr1.add(b1);
            if (robotArr1.size() > size1) {
                robotArr1.remove(0);
            }
            b2 = new BodyPart2(x2, y2, 40);
            robotArr2.add(b2);
            if (robotArr2.size() > size2) {
                robotArr2.remove(0);
            }
            b3 = new BodyPart3(x3, y3, 40);
            robotArr3.add(b3);
            if (robotArr3.size() > size3) {
                robotArr3.remove(0);
            }
        }
    }

    //กราฟฟิก
    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(background, 0, 0, 640, 640, this);

        if (!running) {
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 40));
            g.drawString("ROBOT GAME", WIDTH / 2 - 120, HEIGHT / 2);
            g.setColor(Color.black);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("Plase Enter SpaceBar To Start Game", WIDTH / 2 - 130, HEIGHT / 2 + 50);
        } else {
            
            
            
            g.setColor(Color.black);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("Player = " + numplayer + " ", 660, 100);//x=940 y=640
            g.setColor(Color.black);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("BOT = " + numbot + " ", 660, 130);//x=940 y=640 

            g.setColor(Color.black);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("PlayerScore = " + playerScore + " ", 660, 220);//x=940 y=640
            g.setColor(Color.black);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("BOTScore = " + botScore + " ", 660, 250);//x=940 y=640 

            //BUTTOM RESET
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(660, 150, 60, 30);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("RESET ", 665, 170);//x=940 y=640

            //BUTTOM PLAY AGANE
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(660, 300, 110, 30);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("PLAY AGANE ", 665, 320);//x=940 y=640

            for (int i = 0; i < 680 / 40; i++) {
                g.drawLine(i * 40, 0, i * 40, 680);
            }
            for (int i = 0; i < 640 / 40; i++) {
                g.drawLine(0, i * 40, 640, i * 40);
            }
            for (int i = 0; i < itemArr.size(); i++) {
                itemArr.get(i).draw(g);

            }
            for (int i = 0; i < wallArr.size(); i++) {
                wallArr.get(i).draw(g);
            }
            for (int i = 0; i < robotArr.size(); i++) {
                robotArr.get(i).draw(g);
            }
            for (int i = 0; i < robotArr1.size(); i++) {
                robotArr1.get(i).draw(g);
            }
            for (int i = 0; i < robotArr2.size(); i++) {
                robotArr2.get(i).draw(g);
            }
            for (int i = 0; i < robotArr3.size(); i++) {
                robotArr3.get(i).draw(g);
            }
            
        }

    }

    class KeyInner extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_RIGHT) {
                up = false;
                down = false;
                left = false;
                right = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                up = false;
                down = false;
                left = true;
                right = false;
            }
            if (key == KeyEvent.VK_UP) {
                up = true;
                down = false;
                left = false;
                right = false;
            }
            if (key == KeyEvent.VK_DOWN) {

                up = false;
                down = true;
                left = false;
                right = false;
            }
            //if (key == KeyEvent.VK_SPACE) {
            //startgame();
            //}
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
    //Event Mouse

    class MouseControl extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            int xMouse = e.getX();
            int yMouse = e.getY();
            if (x == xMouse / 40 && y == yMouse / 40) {
                n = 1;
                up = false;
                down = false;
                left = false;
                right = false;
            }
            if (x1 == xMouse / 40 && y1 == yMouse / 40) {
                n = 2;
                up = false;
                down = false;
                left = false;
                right = false;
            }
            if (x2 == xMouse / 40 && y2 == yMouse / 40) {
                n = 3;
                up = false;
                down = false;
                left = false;
                right = false;
            }
            if (x3 == xMouse / 40 && y3 == yMouse / 40) {
                n = 4;
                up = false;
                down = false;
                left = false;
                right = false;
            }
            if (11 == xMouse / 60 && 5 == yMouse / 30) {
                //g.fillRect(670, 150, 60, 30);
                System.out.print("Buttom");
                x = markx;
                y = marky;
                x1 = markx1;
                y1 = marky1;
                x2 = markx2;
                y2 = marky2;
                x3 = markx3;
                y3 = marky3;
                numplayer = 0;
            }
            if (6 == xMouse / 110 && 10 == yMouse / 30) {
                //g.fillRect(660, 300, 110, 30);
                if (numplayer < numbot && WIN == 1) {
                    playerScore++;
                } else if (numplayer >= numbot && numbot != 99) {
                    botScore++;
                }

                itemArr.remove(i);

                numbot = 0;
                F();
                numplayer = 0;
                markx = x;
                markx1 = x1;
                markx2 = x2;
                markx3 = x3;
                marky = y;
                marky1 = y1;
                marky2 = y2;
                marky3 = y3;
                WIN = 0;
            }

            //System.out.print(n + "n\n");
        }
    }

    public int Move(int xx, int yy) {
        int x0 = xx;
        int y0 = yy;
        int move = 0;
        if (right) {
            if ((x0 == 3 && y0 == 0) || (x0 == 11 && y0 == 0) || (x0 == 1 && y0 == 1)
                    || (x0 == 13 && y0 == 1) || (x0 == 9 && y0 == 2) || (x0 == 1 && y0 == 3)
                    || (x0 == 5 && y0 == 3) || (x0 == 12 && y0 == 3) || (x0 == 8 && y0 == 5)
                    || (x0 == 3 && y0 == 6) || (x0 == 6 && y0 == 7) || (x0 == 6 && y0 == 8)
                    || (x0 == 2 && y0 == 10) || (x0 == 12 && y0 == 10) || (x0 == 6 && y0 == 11)
                    || (x0 == 3 && y0 == 12) || (x0 == 8 && y0 == 12) || (x0 == 13 && y0 == 12)
                    || (x0 == 10 && y0 == 13) || (x0 == 0 && y0 == 14) || (x0 == 2 && y0 == 15)
                    || (x0 == 11 && y0 == 15) || (x0 == 15)
                    || (x0 == x - 1 && y0 == y) || (x0 == x1 - 1 && y0 == y1) || (x0 == x2 - 1 && y0 == y2) || (x0 == x3 - 1 && y0 == y3)) {

                //System.out.print(n + "ติดกำแพงright\n");
                right = false;
                numplayer++;

                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        WIN = 1;
                        move = 0;
                    }
                }
            } else {
                move = 1;
            }
        }
        //-----------------------------------------------------------------------------------right
        //-----------------------------------------------------------------------------------left
        if (left) {
            if ((x0 == 4 && y0 == 0) || (x0 == 12 && y0 == 0) || (x0 == 2 && y0 == 1)
                    || (x0 == 14 && y0 == 1) || (x0 == 10 && y0 == 2) || (x0 == 2 && y0 == 3)
                    || (x0 == 6 && y0 == 3) || (x0 == 13 && y0 == 3) || (x0 == 9 && y0 == 5)
                    || (x0 == 4 && y0 == 6) || (x0 == 9 && y0 == 7) || (x0 == 9 && y0 == 8)
                    || (x0 == 3 && y0 == 10) || (x0 == 13 && y0 == 10) || (x0 == 7 && y0 == 11)
                    || (x0 == 4 && y0 == 12) || (x0 == 9 && y0 == 12) || (x0 == 14 && y0 == 12)
                    || (x0 == 11 && y0 == 13) || (x0 == 1 && y0 == 14) || (x0 == 3 && y0 == 15)
                    || (x0 == 12 && y0 == 15) || (x0 == 0)
                    || (x0 == x + 1 && y0 == y) || (x0 == x1 + 1 && y0 == y1) || (x0 == x2 + 1 && y0 == y2) || (x0 == x3 + 1 && y0 == y3)) {
                //System.out.print(n + "ติดกำแพงleft\n");
                left = false;
                numplayer++;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        WIN = 1;
                        move = 0;
                    }
                }
            } else {
                move = 2;
            }
        }
        //-----------------------------------------------------------------------------------left
        //-----------------------------------------------------------------------------------up
        if (up) {
            if ((x0 == 1 && y0 == 1) || (x0 == 9 && y0 == 2) || (x0 == 13 && y0 == 2)
                    || (x0 == 1 && y0 == 4) || (x0 == 6 && y0 == 4) || (x0 == 13 && y0 == 4)
                    || (x0 == 9 && y0 == 5) || (x0 == 0 && y0 == 6) || (x0 == 4 && y0 == 6)
                    || (x0 == 7 && y0 == 9) || (x0 == 8 && y0 == 9) || (x0 == 15 && y0 == 10)
                    || (x0 == 2 && y0 == 11) || (x0 == 6 && y0 == 11) || (x0 == 13 && y0 == 11)
                    || (x0 == 9 && y0 == 12) || (x0 == 0 && y0 == 13) || (x0 == 4 && y0 == 13)
                    || (x0 == 10 && y0 == 13) || (x0 == 13 && y0 == 13) || (x0 == 1 && y0 == 14)
                    || (x0 == 15 && y0 == 4) || (y0 == 0)
                    || (x0 == x && y0 == y + 1) || (x0 == x1 && y0 == y1 + 1) || (x0 == x2 && y0 == y2 + 1) || (x0 == x3 && y0 == y3 + 1)) {
                //System.out.print(n + "ติดกำแพงup\n");
                up = false;
                numplayer++;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        WIN = 1;
                        move = 0;
                    }
                }
            } else {
                move = 3;
            }

        }
        //-----------------------------------------------------------------------------------up
        //-----------------------------------------------------------------------------------down
        if (down) {
            if ((x0 == 1 && y0 == 0) || (x0 == 9 && y0 == 1) || (x0 == 13 && y0 == 1)
                    || (x0 == 1 && y0 == 3) || (x0 == 6 && y0 == 3) || (x0 == 13 && y0 == 3)
                    || (x0 == 9 && y0 == 4) || (x0 == 0 && y0 == 5) || (x0 == 4 && y0 == 5)
                    || (x0 == 7 && y0 == 6) || (x0 == 8 && y0 == 6) || (x0 == 15 && y0 == 9)
                    || (x0 == 2 && y0 == 10) || (x0 == 6 && y0 == 10) || (x0 == 13 && y0 == 10)
                    || (x0 == 9 && y0 == 11) || (x0 == 0 && y0 == 12) || (x0 == 4 && y0 == 12)
                    || (x0 == 10 && y0 == 12) || (x0 == 13 && y0 == 12) || (x0 == 1 && y0 == 13)
                    || (x0 == 15 && y0 == 3) || (y0 == 15)
                    || (x0 == x && y0 == y - 1) || (x0 == x1 && y0 == y1 - 1) || (x0 == x2 && y0 == y2 - 1) || (x0 == x3 && y0 == y3 - 1)) {
                //System.out.print(n + "ติดกำแพงdown\n");
                down = false;
                numplayer++;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        WIN = 1;
                        move = 0;
                    }
                }
            } else {
                move = 4;
            }
        }
        return move;

    }

    public int Movebot(int xx, int yy) {
        int x0 = xx;
        int y0 = yy;
        int move = 0;
        if (right) {
            if ((x0 == 3 && y0 == 0) || (x0 == 11 && y0 == 0) || (x0 == 1 && y0 == 1)
                    || (x0 == 13 && y0 == 1) || (x0 == 9 && y0 == 2) || (x0 == 1 && y0 == 3)
                    || (x0 == 5 && y0 == 3) || (x0 == 12 && y0 == 3) || (x0 == 8 && y0 == 5)
                    || (x0 == 3 && y0 == 6) || (x0 == 6 && y0 == 7) || (x0 == 6 && y0 == 8)
                    || (x0 == 2 && y0 == 10) || (x0 == 12 && y0 == 10) || (x0 == 6 && y0 == 11)
                    || (x0 == 3 && y0 == 12) || (x0 == 8 && y0 == 12) || (x0 == 13 && y0 == 12)
                    || (x0 == 10 && y0 == 13) || (x0 == 0 && y0 == 14) || (x0 == 2 && y0 == 15)
                    || (x0 == 11 && y0 == 15) || (x0 == 15)
                    || (x0 == botx - 1 && y0 == boty) || (x0 == botx1 - 1 && y0 == boty1) || (x0 == botx2 - 1 && y0 == boty2) || (x0 == botx3 - 1 && y0 == boty3)) {
                //System.out.print(n + "ติดกำแพงright\n");
                right = false;
                move = 0;

                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        stopbot = false;
                    }
                }
            } else {
                move = 1;
            }
        }
        //-----------------------------------------------------------------------------------right
        //-----------------------------------------------------------------------------------left
        if (left) {
            if ((x0 == 4 && y0 == 0) || (x0 == 12 && y0 == 0) || (x0 == 2 && y0 == 1)
                    || (x0 == 14 && y0 == 1) || (x0 == 10 && y0 == 2) || (x0 == 2 && y0 == 3)
                    || (x0 == 6 && y0 == 3) || (x0 == 13 && y0 == 3) || (x0 == 9 && y0 == 5)
                    || (x0 == 4 && y0 == 6) || (x0 == 9 && y0 == 7) || (x0 == 9 && y0 == 8)
                    || (x0 == 3 && y0 == 10) || (x0 == 13 && y0 == 10) || (x0 == 7 && y0 == 11)
                    || (x0 == 4 && y0 == 12) || (x0 == 9 && y0 == 12) || (x0 == 14 && y0 == 12)
                    || (x0 == 11 && y0 == 13) || (x0 == 1 && y0 == 14) || (x0 == 3 && y0 == 15)
                    || (x0 == 12 && y0 == 15) || (x0 == 0)
                    || (x0 == botx + 1 && y0 == boty) || (x0 == botx1 + 1 && y0 == boty1) || (x0 == botx2 + 1 && y0 == boty2) || (x0 == botx3 + 1 && y0 == boty3)) {
                //System.out.print(n + "ติดกำแพงleft\n");
                left = false;
                move = 0;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        stopbot = false;
                    }
                }
            } else {
                move = 2;
            }
        }
        //-----------------------------------------------------------------------------------left
        //-----------------------------------------------------------------------------------up
        if (up) {
            if ((x0 == 1 && y0 == 1) || (x0 == 9 && y0 == 2) || (x0 == 13 && y0 == 2)
                    || (x0 == 1 && y0 == 4) || (x0 == 6 && y0 == 4) || (x0 == 13 && y0 == 4)
                    || (x0 == 9 && y0 == 5) || (x0 == 0 && y0 == 6) || (x0 == 4 && y0 == 6)
                    || (x0 == 7 && y0 == 9) || (x0 == 8 && y0 == 9) || (x0 == 15 && y0 == 10)
                    || (x0 == 2 && y0 == 11) || (x0 == 6 && y0 == 11) || (x0 == 13 && y0 == 11)
                    || (x0 == 9 && y0 == 12) || (x0 == 0 && y0 == 13) || (x0 == 4 && y0 == 13)
                    || (x0 == 10 && y0 == 13) || (x0 == 13 && y0 == 13) || (x0 == 1 && y0 == 14)
                    || (x0 == 15 && y0 == 4) || (y0 == 0)
                    || (x0 == botx && y0 == boty + 1) || (x0 == botx1 && y0 == boty1 + 1) || (x0 == botx2 && y0 == boty2 + 1) || (x0 == botx3 && y0 == boty3 + 1)) {
                //System.out.print(n + "ติดกำแพงup\n");
                up = false;
                move = 0;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        stopbot = false;
                    }
                }
            } else {
                move = 3;
            }

        }
        //-----------------------------------------------------------------------------------up
        //-----------------------------------------------------------------------------------down
        if (down) {
            if ((x0 == 1 && y0 == 0) || (x0 == 9 && y0 == 1) || (x0 == 13 && y0 == 1)
                    || (x0 == 1 && y0 == 3) || (x0 == 6 && y0 == 3) || (x0 == 13 && y0 == 3)
                    || (x0 == 9 && y0 == 4) || (x0 == 0 && y0 == 5) || (x0 == 4 && y0 == 5)
                    || (x0 == 7 && y0 == 6) || (x0 == 8 && y0 == 6) || (x0 == 15 && y0 == 9)
                    || (x0 == 2 && y0 == 10) || (x0 == 6 && y0 == 10) || (x0 == 13 && y0 == 10)
                    || (x0 == 9 && y0 == 11) || (x0 == 0 && y0 == 12) || (x0 == 4 && y0 == 12)
                    || (x0 == 10 && y0 == 12) || (x0 == 13 && y0 == 12) || (x0 == 1 && y0 == 13)
                    || (x0 == 15 && y0 == 3) || (y0 == 15)
                    || (x0 == botx && y0 == boty - 1) || (x0 == botx1 && y0 == boty1 - 1) || (x0 == botx2 && y0 == boty2 - 1) || (x0 == botx3 && y0 == boty3 - 1)) {
                //System.out.print(n + "ติดกำแพงdown\n");
                down = false;
                move = 0;
                //เช็คการชน
                for (int i = 0; i < itemArr.size(); i++) {
                    if (x0 == itemArr.get(i).getX() && y0 == itemArr.get(i).getY()) {
                        //size++;
                        stopbot = false;
                    }
                }
            } else {
                move = 4;
            }
        }
        return move;

    }

    public void key(int x) {
        if (x == 0) {
            up = false;
            down = false;
            left = false;
            right = true;
        } else if (x == 1) {
            up = false;
            down = false;
            left = true;
            right = false;
        } else if (x == 2) {
            up = true;
            down = false;
            left = false;
            right = false;
        } else if (x == 3) {
            up = false;
            down = true;
            left = false;
            right = false;
        }

    }

    public int[] botmove(int botx, int boty) {
        int a = 0;
        while (Movebot(botx, boty) == 1) {
            botx++;
            a = 1;
        }
        while (Movebot(botx, boty) == 2) {
            botx--;
            a = 2;
        }
        while (Movebot(botx, boty) == 3) {
            boty--;
            a = 3;
        }
        while (Movebot(botx, boty) == 4) {
            boty++;
            a = 4;
        }
        return new int[]{botx, boty, a};
    }

    public int[] BOT1() {
        //int botx1, botx2, botx3, botx;
        //int boty1, boty2, boty3, boty;
        int num2 = 0;
        int num1 = 0;
        int numsum = 0;
        int Cbotx = -1;
        int Cboty = -1;
        int Movebot = 0;
        int sum = 99;
        String tall = "";
        //save1
        int botx1a[] = new int[]{botx, botx, botx, botx};
        int boty1a[] = new int[]{boty, boty, boty, boty};
        //System.out.print(botx + " botx/ " + boty + " boty เริ่มต้น\n");
        int botx11[] = new int[4];
        int boty11[] = new int[4];
        //save2
        int botx2a[] = new int[4];
        int boty2a[] = new int[4];
        int botx22[] = new int[4];
        int boty22[] = new int[4];
        //save3
        int botx3a[] = new int[4];
        int boty3a[] = new int[4];
        int botx33[] = new int[4];
        int boty33[] = new int[4];
        //save4
        int botx4[] = new int[4];
        int boty4[] = new int[4];
        int botx44[] = new int[4];
        int boty44[] = new int[4];
        //save5
        int botx5[] = new int[4];
        int boty5[] = new int[4];
        int botx55[] = new int[4];
        int boty55[] = new int[4];
        //save6
        int botx6[] = new int[4];
        int boty6[] = new int[4];
        int botx66[] = new int[4];
        int boty66[] = new int[4];
        //save7
        int botx7[] = new int[4];
        int boty7[] = new int[4];
        int botx77[] = new int[4];
        int boty77[] = new int[4];
        //save8
        int botx8[] = new int[4];
        int boty8[] = new int[4];
        int botx88[] = new int[4];
        int boty88[] = new int[4];
        //save9
        int botx9[] = new int[4];
        int boty9[] = new int[4];
        int botx99[] = new int[4];
        int boty99[] = new int[4];
        //save10
        int botx0[] = new int[4];
        int boty0[] = new int[4];
        int botx00[] = new int[4];
        int boty00[] = new int[4];

        //บันทึกการเดิน
        int botS[] = new int[10];
        int botSS[] = new int[10];

        for (int i = 0; i < 4; i++) {// ROUND 1
            //System.out.print(i + "ชั้นหนึ่ง++++++++++++++++++++++++++++++++++++++++++++++\n");
            key(i);
            int a[] = new int[2];
            a = botmove(botx1a[i], boty1a[i]);
            botx11[i] = a[0];
            boty11[i] = a[1];
            botSS[0] = a[2];
            botx = botx11[i];
            boty = boty11[i];
            //System.out.print(a[2] + "ai ");

            for (int j = 0; j < 4; j++) {// ROUND 2
                botx2a[0] = botx11[i];
                botx2a[1] = botx11[i];
                botx2a[2] = botx11[i];
                botx2a[3] = botx11[i];//coppy x
                boty2a[0] = boty11[i];
                boty2a[1] = boty11[i];
                boty2a[2] = boty11[i];
                boty2a[3] = boty11[i];//coppy y
                //System.out.print(j + "ชั้นสอง-");
                key(j);
                a = botmove(botx2a[j], boty2a[j]);
                botx22[j] = a[0];
                boty22[j] = a[1];
                botSS[1] = a[2];
                botx = botx22[j];
                boty = boty22[j];
                //System.out.print(a[2] + "aj");

                for (int k = 0; k < 4; k++) {// ROUND 3
                    botx3a[0] = botx22[j];
                    botx3a[1] = botx22[j];
                    botx3a[2] = botx22[j];
                    botx3a[3] = botx22[j];//coppy x
                    boty3a[0] = boty22[j];
                    boty3a[1] = boty22[j];
                    boty3a[2] = boty22[j];
                    boty3a[3] = boty22[j];//coppy y
                    //System.out.print(k + "ชั้นสาม-");
                    key(k);
                    a = botmove(botx3a[k], boty3a[k]);
                    botx33[k] = a[0];
                    boty33[k] = a[1];
                    botSS[2] = a[2];
                    botx = botx33[k];
                    boty = boty33[k];
                    //System.out.print(a[2] + "ak " + a[0] + "x " + a[1] + "y " + "a\n");

                    for (int l = 0; l < 4; l++) {// ROUND 4
                        botx4[0] = botx33[k];
                        botx4[1] = botx33[k];
                        botx4[2] = botx33[k];
                        botx4[3] = botx33[k];//coppy x
                        boty4[0] = boty33[k];
                        boty4[1] = boty33[k];
                        boty4[2] = boty33[k];
                        boty4[3] = boty33[k];//coppy y
                        //System.out.print(l + "ชั้นสี่-");
                        key(l);
                        a = botmove(botx4[l], boty4[l]);
                        botx44[l] = a[0];
                        boty44[l] = a[1];
                        botSS[3] = a[2];
                        botx = botx44[l];
                        boty = boty44[l];
                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                        for (int m = 0; m < 4; m++) {// ROUND 5
                            botx5[0] = botx44[l];
                            botx5[1] = botx44[l];
                            botx5[2] = botx44[l];
                            botx5[3] = botx44[l];//coppy x
                            boty5[0] = boty44[l];
                            boty5[1] = boty44[l];
                            boty5[2] = boty44[l];
                            boty5[3] = boty44[l];//coppy y
                            //System.out.print(m + "ชั้นห้า-");
                            key(m);
                            a = botmove(botx5[m], boty5[m]);
                            botx55[m] = a[0];
                            boty55[m] = a[1];
                            botSS[4] = a[2];
                            botx = botx55[m];
                            boty = boty55[m];
                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                            for (int n = 0; n < 4; n++) {// ROUND 6
                                botx6[0] = botx55[m];
                                botx6[1] = botx55[m];
                                botx6[2] = botx55[m];
                                botx6[3] = botx55[m];//coppy x
                                boty6[0] = boty55[m];
                                boty6[1] = boty55[m];
                                boty6[2] = boty55[m];
                                boty6[3] = boty55[m];//coppy y
                                //System.out.print(n + "ชั้นห้า-");
                                key(n);
                                a = botmove(botx6[n], boty6[n]);
                                botx66[n] = a[0];
                                boty66[n] = a[1];
                                botSS[5] = a[2];
                                botx = botx66[n];
                                boty = boty66[n];
                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                for (int o = 0; o < 4; o++) {// ROUND 7
                                    botx7[0] = botx66[n];
                                    botx7[1] = botx66[n];
                                    botx7[2] = botx66[n];
                                    botx7[3] = botx66[n];//coppy x
                                    boty7[0] = boty66[n];
                                    boty7[1] = boty66[n];
                                    boty7[2] = boty66[n];
                                    boty7[3] = boty66[n];//coppy y
                                    //System.out.print(n + "ชั้นห้า-");
                                    key(o);
                                    a = botmove(botx7[o], boty7[o]);
                                    botx77[o] = a[0];
                                    boty77[o] = a[1];
                                    botSS[6] = a[2];
                                    botx = botx77[o];
                                    boty = boty77[o];
                                    //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                    for (int p = 0; p < 4; p++) {// ROUND 8
                                        botx8[0] = botx77[o];
                                        botx8[1] = botx77[o];
                                        botx8[2] = botx77[o];
                                        botx8[3] = botx77[o];//coppy x
                                        boty8[0] = boty77[o];
                                        boty8[1] = boty77[o];
                                        boty8[2] = boty77[o];
                                        boty8[3] = boty77[o];//coppy y
                                        //System.out.print(n + "ชั้นห้า-");
                                        key(p);
                                        a = botmove(botx8[p], boty8[p]);
                                        botx88[p] = a[0];
                                        boty88[p] = a[1];
                                        botSS[7] = a[2];
                                        botx = botx88[p];
                                        boty = boty88[p];
                                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");
                                        for (int q = 0; q < 4; q++) {// ROUND 9
                                            botx9[0] = botx88[p];
                                            botx9[1] = botx88[p];
                                            botx9[2] = botx88[p];
                                            botx9[3] = botx88[p];//coppy x
                                            boty9[0] = boty88[p];
                                            boty9[1] = boty88[p];
                                            boty9[2] = boty88[p];
                                            boty9[3] = boty88[p];//coppy y
                                            //System.out.print(n + "ชั้นห้า-");
                                            key(q);
                                            a = botmove(botx9[q], boty9[q]);
                                            botx99[q] = a[0];
                                            boty99[q] = a[1];
                                            botSS[8] = a[2];
                                            botx = botx99[q];
                                            boty = boty99[q];
                                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                            for (int r = 0; r < 4; r++) {// ROUND 9
                                                botx0[0] = botx99[q];
                                                botx0[1] = botx99[q];
                                                botx0[2] = botx99[q];
                                                botx0[3] = botx99[q];//coppy x
                                                boty0[0] = boty99[q];
                                                boty0[1] = boty99[q];
                                                boty0[2] = boty99[q];
                                                boty0[3] = boty99[q];//coppy y
                                                //System.out.print(n + "ชั้นห้า-");
                                                key(r);
                                                a = botmove(botx9[r], boty9[r]);
                                                botx00[r] = a[0];
                                                boty00[r] = a[1];
                                                botSS[9] = a[2];
                                                botx = botx00[r];
                                                boty = boty00[r];
                                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                                //System.out.print("ทาง--> " + botSS[0] + "/ " + botSS[1] + "/ " + botSS[2] + "/ " + botSS[3] + "/ " + botSS[4] + "/ " + botSS[5] + "\n");
                                                int x = Math.abs(xitem - botx00[r]);//------------------บันทึก
                                                int y = Math.abs(yitem - boty00[r]);
                                                int s = x + y;

                                                //System.out.print("-----------------------------------------" + s + "ระยะห่างX \n");
                                                if (s <= sum) {
                                                    if (botSS[0] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[1] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[2] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[3] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[4] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[5] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[6] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[7] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[8] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[9] != 0) {
                                                        num2++;
                                                    }

                                                    //System.out.print(num2 + "num 2 \n");
                                                    if ((s < sum) || (s == sum && numsum > num2)) {
                                                        sum = s;
                                                        Movebot = 1;
                                                        botS[0] = botSS[0];
                                                        botS[1] = botSS[1];
                                                        botS[2] = botSS[2];
                                                        botS[3] = botSS[3];
                                                        botS[4] = botSS[4];
                                                        botS[5] = botSS[5];
                                                        botS[6] = botSS[6];
                                                        botS[7] = botSS[7];
                                                        botS[8] = botSS[8];
                                                        botS[9] = botSS[9];
                                                        botS[0] = botSS[0];
                                                        numsum = num2;
                                                    }
                                                }
                                                num2 = 0;
                                            }
                                        }
                                        botx = botx88[p];
                                        boty = boty88[p];//ย้ายไปตำแหน่งเดิม
                                    }
                                    botx = botx77[o];
                                    boty = boty77[o];//ย้ายไปตำแหน่งเดิม
                                }
                                botx = botx66[n];
                                boty = boty66[n];//ย้ายไปตำแหน่งเดิม
                            }
                            botx = botx55[m];
                            boty = boty55[m];//ย้ายไปตำแหน่งเดิม
                        }
                        botx = botx44[l];
                        boty = boty44[l];//ย้ายไปตำแหน่งเดิม
                    }
                    botx = botx33[k];
                    boty = boty33[k];//ย้ายไปตำแหน่งเดิม
                }
                botx = botx22[j];
                boty = boty22[j];//ย้ายไปตำแหน่งเดิม
                //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
            }
            botx = botx11[i];
            boty = boty11[i];//ย้ายไปตำแหน่งเดิม
            //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
        }
        botx = Mbotx;
        boty = Mboty;
        System.out.print("sum " + sum + " " + botS[0] + "/" + botS[1] + "/" + botS[2] + "/" + botS[3] + "/" + botS[4] + "/" + botS[5] + "/" + botS[6] + "/" + botS[7] + "/" + botS[8] + "/" + botS[9] + " จำนวน" + numsum + "move #####-----1\n");
        return new int[]{sum, numsum};

    }

    public int[] BOT2() {
        //int botx1, botx2, botx3, botx;
        //int boty1, boty2, boty3, boty;
        int num2 = 0;
        int num1 = 0;
        int numsum = 0;
        int Cbotx = -1;
        int Cboty = -1;
        int Movebot = 0;
        int sum = 99;
        String tall = "";
        //save1
        int botx1a[] = new int[]{botx1, botx1, botx1, botx1};
        int boty1a[] = new int[]{boty1, boty1, boty1, boty1};
        //System.out.print(botx + " botx/ " + boty + " boty เริ่มต้น\n");
        int botx11[] = new int[4];
        int boty11[] = new int[4];
        //save2
        int botx2a[] = new int[4];
        int boty2a[] = new int[4];
        int botx22[] = new int[4];
        int boty22[] = new int[4];
        //save3
        int botx3a[] = new int[4];
        int boty3a[] = new int[4];
        int botx33[] = new int[4];
        int boty33[] = new int[4];
        //save4
        int botx4[] = new int[4];
        int boty4[] = new int[4];
        int botx44[] = new int[4];
        int boty44[] = new int[4];
        //save5
        int botx5[] = new int[4];
        int boty5[] = new int[4];
        int botx55[] = new int[4];
        int boty55[] = new int[4];
        //save6
        int botx6[] = new int[4];
        int boty6[] = new int[4];
        int botx66[] = new int[4];
        int boty66[] = new int[4];
        //save7
        int botx7[] = new int[4];
        int boty7[] = new int[4];
        int botx77[] = new int[4];
        int boty77[] = new int[4];
        //save8
        int botx8[] = new int[4];
        int boty8[] = new int[4];
        int botx88[] = new int[4];
        int boty88[] = new int[4];
        //save9
        int botx9[] = new int[4];
        int boty9[] = new int[4];
        int botx99[] = new int[4];
        int boty99[] = new int[4];
        //save10
        int botx0[] = new int[4];
        int boty0[] = new int[4];
        int botx00[] = new int[4];
        int boty00[] = new int[4];

        //บันทึกการเดิน
        int botS[] = new int[10];
        int botSS[] = new int[10];

        for (int i = 0; i < 4; i++) {// ROUND 1
            //System.out.print(i + "ชั้นหนึ่ง++++++++++++++++++++++++++++++++++++++++++++++\n");
            key(i);
            int a[] = new int[2];
            a = botmove(botx1a[i], boty1a[i]);
            botx11[i] = a[0];
            boty11[i] = a[1];
            botSS[0] = a[2];
            botx1 = botx11[i];
            boty1 = boty11[i];
            //System.out.print(a[2] + "ai ");

            for (int j = 0; j < 4; j++) {// ROUND 2
                botx2a[0] = botx11[i];
                botx2a[1] = botx11[i];
                botx2a[2] = botx11[i];
                botx2a[3] = botx11[i];//coppy x
                boty2a[0] = boty11[i];
                boty2a[1] = boty11[i];
                boty2a[2] = boty11[i];
                boty2a[3] = boty11[i];//coppy y
                //System.out.print(j + "ชั้นสอง-");
                key(j);
                a = botmove(botx2a[j], boty2a[j]);
                botx22[j] = a[0];
                boty22[j] = a[1];
                botSS[1] = a[2];
                botx1 = botx22[j];
                boty1 = boty22[j];
                //System.out.print(a[2] + "aj");

                for (int k = 0; k < 4; k++) {// ROUND 3
                    botx3a[0] = botx22[j];
                    botx3a[1] = botx22[j];
                    botx3a[2] = botx22[j];
                    botx3a[3] = botx22[j];//coppy x
                    boty3a[0] = boty22[j];
                    boty3a[1] = boty22[j];
                    boty3a[2] = boty22[j];
                    boty3a[3] = boty22[j];//coppy y
                    //System.out.print(k + "ชั้นสาม-");
                    key(k);
                    a = botmove(botx3a[k], boty3a[k]);
                    botx33[k] = a[0];
                    boty33[k] = a[1];
                    botSS[2] = a[2];
                    botx1 = botx33[k];
                    boty1 = boty33[k];
                    //System.out.print(a[2] + "ak " + a[0] + "x " + a[1] + "y " + "a\n");

                    for (int l = 0; l < 4; l++) {// ROUND 4
                        botx4[0] = botx33[k];
                        botx4[1] = botx33[k];
                        botx4[2] = botx33[k];
                        botx4[3] = botx33[k];//coppy x
                        boty4[0] = boty33[k];
                        boty4[1] = boty33[k];
                        boty4[2] = boty33[k];
                        boty4[3] = boty33[k];//coppy y
                        //System.out.print(l + "ชั้นสี่-");
                        key(l);
                        a = botmove(botx4[l], boty4[l]);
                        botx44[l] = a[0];
                        boty44[l] = a[1];
                        botSS[3] = a[2];
                        botx1 = botx44[l];
                        boty1 = boty44[l];
                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                        for (int m = 0; m < 4; m++) {// ROUND 5
                            botx5[0] = botx44[l];
                            botx5[1] = botx44[l];
                            botx5[2] = botx44[l];
                            botx5[3] = botx44[l];//coppy x
                            boty5[0] = boty44[l];
                            boty5[1] = boty44[l];
                            boty5[2] = boty44[l];
                            boty5[3] = boty44[l];//coppy y
                            //System.out.print(m + "ชั้นห้า-");
                            key(m);
                            a = botmove(botx5[m], boty5[m]);
                            botx55[m] = a[0];
                            boty55[m] = a[1];
                            botSS[4] = a[2];
                            botx1 = botx55[m];
                            boty1 = boty55[m];
                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                            for (int n = 0; n < 4; n++) {// ROUND 6
                                botx6[0] = botx55[m];
                                botx6[1] = botx55[m];
                                botx6[2] = botx55[m];
                                botx6[3] = botx55[m];//coppy x
                                boty6[0] = boty55[m];
                                boty6[1] = boty55[m];
                                boty6[2] = boty55[m];
                                boty6[3] = boty55[m];//coppy y
                                //System.out.print(n + "ชั้นห้า-");
                                key(n);
                                a = botmove(botx6[n], boty6[n]);
                                botx66[n] = a[0];
                                boty66[n] = a[1];
                                botSS[5] = a[2];
                                botx1 = botx66[n];
                                boty1 = boty66[n];
                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                for (int o = 0; o < 4; o++) {// ROUND 7
                                    botx7[0] = botx66[n];
                                    botx7[1] = botx66[n];
                                    botx7[2] = botx66[n];
                                    botx7[3] = botx66[n];//coppy x
                                    boty7[0] = boty66[n];
                                    boty7[1] = boty66[n];
                                    boty7[2] = boty66[n];
                                    boty7[3] = boty66[n];//coppy y
                                    //System.out.print(n + "ชั้นห้า-");
                                    key(o);
                                    a = botmove(botx7[o], boty7[o]);
                                    botx77[o] = a[0];
                                    boty77[o] = a[1];
                                    botSS[6] = a[2];
                                    botx1 = botx77[o];
                                    boty1 = boty77[o];
                                    //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                    for (int p = 0; p < 4; p++) {// ROUND 8
                                        botx8[0] = botx77[o];
                                        botx8[1] = botx77[o];
                                        botx8[2] = botx77[o];
                                        botx8[3] = botx77[o];//coppy x
                                        boty8[0] = boty77[o];
                                        boty8[1] = boty77[o];
                                        boty8[2] = boty77[o];
                                        boty8[3] = boty77[o];//coppy y
                                        //System.out.print(n + "ชั้นห้า-");
                                        key(p);
                                        a = botmove(botx8[p], boty8[p]);
                                        botx88[p] = a[0];
                                        boty88[p] = a[1];
                                        botSS[7] = a[2];
                                        botx1 = botx88[p];
                                        boty1 = boty88[p];
                                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");
                                        for (int q = 0; q < 4; q++) {// ROUND 9
                                            botx9[0] = botx88[p];
                                            botx9[1] = botx88[p];
                                            botx9[2] = botx88[p];
                                            botx9[3] = botx88[p];//coppy x
                                            boty9[0] = boty88[p];
                                            boty9[1] = boty88[p];
                                            boty9[2] = boty88[p];
                                            boty9[3] = boty88[p];//coppy y
                                            //System.out.print(n + "ชั้นห้า-");
                                            key(q);
                                            a = botmove(botx9[q], boty9[q]);
                                            botx99[q] = a[0];
                                            boty99[q] = a[1];
                                            botSS[8] = a[2];
                                            botx1 = botx99[q];
                                            boty1 = boty99[q];
                                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                            for (int r = 0; r < 4; r++) {// ROUND 9
                                                botx0[0] = botx99[q];
                                                botx0[1] = botx99[q];
                                                botx0[2] = botx99[q];
                                                botx0[3] = botx99[q];//coppy x
                                                boty0[0] = boty99[q];
                                                boty0[1] = boty99[q];
                                                boty0[2] = boty99[q];
                                                boty0[3] = boty99[q];//coppy y
                                                //System.out.print(n + "ชั้นห้า-");
                                                key(r);
                                                a = botmove(botx9[r], boty9[r]);
                                                botx00[r] = a[0];
                                                boty00[r] = a[1];
                                                botSS[9] = a[2];
                                                botx1 = botx00[r];
                                                boty1 = boty00[r];
                                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                                //System.out.print("ทาง--> " + botSS[0] + "/ " + botSS[1] + "/ " + botSS[2] + "/ " + botSS[3] + "/ " + botSS[4] + "/ " + botSS[5] + "\n");
                                                int x = Math.abs(xitem - botx00[r]);//------------------บันทึก
                                                int y = Math.abs(yitem - boty00[r]);
                                                int s = x + y;

                                                //System.out.print("-----------------------------------------" + s + "ระยะห่างX \n");
                                                if (s <= sum) {
                                                    if (botSS[0] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[1] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[2] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[3] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[4] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[5] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[6] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[7] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[8] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[9] != 0) {
                                                        num2++;
                                                    }

                                                    //System.out.print(num2 + "num 2 \n");
                                                    if ((s < sum) || (s == sum && numsum > num2)) {
                                                        sum = s;
                                                        Movebot = 1;
                                                        botS[0] = botSS[0];
                                                        botS[1] = botSS[1];
                                                        botS[2] = botSS[2];
                                                        botS[3] = botSS[3];
                                                        botS[4] = botSS[4];
                                                        botS[5] = botSS[5];
                                                        botS[6] = botSS[6];
                                                        botS[7] = botSS[7];
                                                        botS[8] = botSS[8];
                                                        botS[9] = botSS[9];
                                                        botS[0] = botSS[0];
                                                        numsum = num2;
                                                    }
                                                }
                                                num2 = 0;
                                            }
                                        }
                                        botx1 = botx88[p];
                                        boty1 = boty88[p];//ย้ายไปตำแหน่งเดิม
                                    }
                                    botx1 = botx77[o];
                                    boty1 = boty77[o];//ย้ายไปตำแหน่งเดิม
                                }
                                botx1 = botx66[n];
                                boty1 = boty66[n];//ย้ายไปตำแหน่งเดิม
                            }
                            botx1 = botx55[m];
                            boty1 = boty55[m];//ย้ายไปตำแหน่งเดิม
                        }
                        botx1 = botx44[l];
                        boty1 = boty44[l];//ย้ายไปตำแหน่งเดิม
                    }
                    botx1 = botx33[k];
                    boty1 = boty33[k];//ย้ายไปตำแหน่งเดิม
                }
                botx1 = botx22[j];
                boty1 = boty22[j];//ย้ายไปตำแหน่งเดิม
                //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
            }
            botx1 = botx11[i];
            boty1 = boty11[i];//ย้ายไปตำแหน่งเดิม
            //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
        }
        botx1 = Mbotx1;
        boty1 = Mboty1;
        System.out.print("sum " + sum + " " + botS[0] + "/" + botS[1] + "/" + botS[2] + "/" + botS[3] + "/" + botS[4] + "/" + botS[5] + "/" + botS[6] + "/" + botS[7] + "/" + botS[8] + "/" + botS[9] + " จำนวน" + numsum + "move #####-----1\n");
        return new int[]{sum, numsum};

    }

    public int[] BOT3() {
        //int botx1, botx2, botx3, botx;
        //int boty1, boty2, boty3, boty;
        int num2 = 0;
        int num1 = 0;
        int numsum = 0;
        int Cbotx = -1;
        int Cboty = -1;
        int Movebot = 0;
        int sum = 99;
        String tall = "";
        //save1
        int botx1a[] = new int[]{botx2, botx2, botx2, botx2};
        int boty1a[] = new int[]{boty2, boty2, boty2, boty2};
        //System.out.print(botx + " botx/ " + boty + " boty เริ่มต้น\n");
        int botx11[] = new int[4];
        int boty11[] = new int[4];
        //save2
        int botx2a[] = new int[4];
        int boty2a[] = new int[4];
        int botx22[] = new int[4];
        int boty22[] = new int[4];
        //save3
        int botx3a[] = new int[4];
        int boty3a[] = new int[4];
        int botx33[] = new int[4];
        int boty33[] = new int[4];
        //save4
        int botx4[] = new int[4];
        int boty4[] = new int[4];
        int botx44[] = new int[4];
        int boty44[] = new int[4];
        //save5
        int botx5[] = new int[4];
        int boty5[] = new int[4];
        int botx55[] = new int[4];
        int boty55[] = new int[4];
        //save6
        int botx6[] = new int[4];
        int boty6[] = new int[4];
        int botx66[] = new int[4];
        int boty66[] = new int[4];
        //save7
        int botx7[] = new int[4];
        int boty7[] = new int[4];
        int botx77[] = new int[4];
        int boty77[] = new int[4];
        //save8
        int botx8[] = new int[4];
        int boty8[] = new int[4];
        int botx88[] = new int[4];
        int boty88[] = new int[4];
        //save9
        int botx9[] = new int[4];
        int boty9[] = new int[4];
        int botx99[] = new int[4];
        int boty99[] = new int[4];
        //save10
        int botx0[] = new int[4];
        int boty0[] = new int[4];
        int botx00[] = new int[4];
        int boty00[] = new int[4];

        //บันทึกการเดิน
        int botS[] = new int[10];
        int botSS[] = new int[10];

        for (int i = 0; i < 4; i++) {// ROUND 1
            //System.out.print(i + "ชั้นหนึ่ง++++++++++++++++++++++++++++++++++++++++++++++\n");
            key(i);
            int a[] = new int[2];
            a = botmove(botx1a[i], boty1a[i]);
            botx11[i] = a[0];
            boty11[i] = a[1];
            botSS[0] = a[2];
            botx2 = botx11[i];
            boty2 = boty11[i];
            //System.out.print(a[2] + "ai ");

            for (int j = 0; j < 4; j++) {// ROUND 2
                botx2a[0] = botx11[i];
                botx2a[1] = botx11[i];
                botx2a[2] = botx11[i];
                botx2a[3] = botx11[i];//coppy x
                boty2a[0] = boty11[i];
                boty2a[1] = boty11[i];
                boty2a[2] = boty11[i];
                boty2a[3] = boty11[i];//coppy y
                //System.out.print(j + "ชั้นสอง-");
                key(j);
                a = botmove(botx2a[j], boty2a[j]);
                botx22[j] = a[0];
                boty22[j] = a[1];
                botSS[1] = a[2];
                botx2 = botx22[j];
                boty2 = boty22[j];
                //System.out.print(a[2] + "aj");

                for (int k = 0; k < 4; k++) {// ROUND 3
                    botx3a[0] = botx22[j];
                    botx3a[1] = botx22[j];
                    botx3a[2] = botx22[j];
                    botx3a[3] = botx22[j];//coppy x
                    boty3a[0] = boty22[j];
                    boty3a[1] = boty22[j];
                    boty3a[2] = boty22[j];
                    boty3a[3] = boty22[j];//coppy y
                    //System.out.print(k + "ชั้นสาม-");
                    key(k);
                    a = botmove(botx3a[k], boty3a[k]);
                    botx33[k] = a[0];
                    boty33[k] = a[1];
                    botSS[2] = a[2];
                    botx2 = botx33[k];
                    boty2 = boty33[k];
                    //System.out.print(a[2] + "ak " + a[0] + "x " + a[1] + "y " + "a\n");

                    for (int l = 0; l < 4; l++) {// ROUND 4
                        botx4[0] = botx33[k];
                        botx4[1] = botx33[k];
                        botx4[2] = botx33[k];
                        botx4[3] = botx33[k];//coppy x
                        boty4[0] = boty33[k];
                        boty4[1] = boty33[k];
                        boty4[2] = boty33[k];
                        boty4[3] = boty33[k];//coppy y
                        //System.out.print(l + "ชั้นสี่-");
                        key(l);
                        a = botmove(botx4[l], boty4[l]);
                        botx44[l] = a[0];
                        boty44[l] = a[1];
                        botSS[3] = a[2];
                        botx2 = botx44[l];
                        boty2 = boty44[l];
                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                        for (int m = 0; m < 4; m++) {// ROUND 5
                            botx5[0] = botx44[l];
                            botx5[1] = botx44[l];
                            botx5[2] = botx44[l];
                            botx5[3] = botx44[l];//coppy x
                            boty5[0] = boty44[l];
                            boty5[1] = boty44[l];
                            boty5[2] = boty44[l];
                            boty5[3] = boty44[l];//coppy y
                            //System.out.print(m + "ชั้นห้า-");
                            key(m);
                            a = botmove(botx5[m], boty5[m]);
                            botx55[m] = a[0];
                            boty55[m] = a[1];
                            botSS[4] = a[2];
                            botx2 = botx55[m];
                            boty2 = boty55[m];
                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                            for (int n = 0; n < 4; n++) {// ROUND 6
                                botx6[0] = botx55[m];
                                botx6[1] = botx55[m];
                                botx6[2] = botx55[m];
                                botx6[3] = botx55[m];//coppy x
                                boty6[0] = boty55[m];
                                boty6[1] = boty55[m];
                                boty6[2] = boty55[m];
                                boty6[3] = boty55[m];//coppy y
                                //System.out.print(n + "ชั้นห้า-");
                                key(n);
                                a = botmove(botx6[n], boty6[n]);
                                botx66[n] = a[0];
                                boty66[n] = a[1];
                                botSS[5] = a[2];
                                botx2 = botx66[n];
                                boty2 = boty66[n];
                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                for (int o = 0; o < 4; o++) {// ROUND 7
                                    botx7[0] = botx66[n];
                                    botx7[1] = botx66[n];
                                    botx7[2] = botx66[n];
                                    botx7[3] = botx66[n];//coppy x
                                    boty7[0] = boty66[n];
                                    boty7[1] = boty66[n];
                                    boty7[2] = boty66[n];
                                    boty7[3] = boty66[n];//coppy y
                                    //System.out.print(n + "ชั้นห้า-");
                                    key(o);
                                    a = botmove(botx7[o], boty7[o]);
                                    botx77[o] = a[0];
                                    boty77[o] = a[1];
                                    botSS[6] = a[2];
                                    botx2 = botx77[o];
                                    boty2 = boty77[o];
                                    //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                    for (int p = 0; p < 4; p++) {// ROUND 8
                                        botx8[0] = botx77[o];
                                        botx8[1] = botx77[o];
                                        botx8[2] = botx77[o];
                                        botx8[3] = botx77[o];//coppy x
                                        boty8[0] = boty77[o];
                                        boty8[1] = boty77[o];
                                        boty8[2] = boty77[o];
                                        boty8[3] = boty77[o];//coppy y
                                        //System.out.print(n + "ชั้นห้า-");
                                        key(p);
                                        a = botmove(botx8[p], boty8[p]);
                                        botx88[p] = a[0];
                                        boty88[p] = a[1];
                                        botSS[7] = a[2];
                                        botx2 = botx88[p];
                                        boty2 = boty88[p];
                                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");
                                        for (int q = 0; q < 4; q++) {// ROUND 9
                                            botx9[0] = botx88[p];
                                            botx9[1] = botx88[p];
                                            botx9[2] = botx88[p];
                                            botx9[3] = botx88[p];//coppy x
                                            boty9[0] = boty88[p];
                                            boty9[1] = boty88[p];
                                            boty9[2] = boty88[p];
                                            boty9[3] = boty88[p];//coppy y
                                            //System.out.print(n + "ชั้นห้า-");
                                            key(q);
                                            a = botmove(botx9[q], boty9[q]);
                                            botx99[q] = a[0];
                                            boty99[q] = a[1];
                                            botSS[8] = a[2];
                                            botx2 = botx99[q];
                                            boty2 = boty99[q];
                                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                            for (int r = 0; r < 4; r++) {// ROUND 9
                                                botx0[0] = botx99[q];
                                                botx0[1] = botx99[q];
                                                botx0[2] = botx99[q];
                                                botx0[3] = botx99[q];//coppy x
                                                boty0[0] = boty99[q];
                                                boty0[1] = boty99[q];
                                                boty0[2] = boty99[q];
                                                boty0[3] = boty99[q];//coppy y
                                                //System.out.print(n + "ชั้นห้า-");
                                                key(r);
                                                a = botmove(botx9[r], boty9[r]);
                                                botx00[r] = a[0];
                                                boty00[r] = a[1];
                                                botSS[9] = a[2];
                                                botx2 = botx00[r];
                                                boty2 = boty00[r];
                                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                                //System.out.print("ทาง--> " + botSS[0] + "/ " + botSS[1] + "/ " + botSS[2] + "/ " + botSS[3] + "/ " + botSS[4] + "/ " + botSS[5] + "\n");
                                                int x = Math.abs(xitem - botx00[r]);//------------------บันทึก
                                                int y = Math.abs(yitem - boty00[r]);
                                                int s = x + y;

                                                //System.out.print("-----------------------------------------" + s + "ระยะห่างX \n");
                                                if (s <= sum) {
                                                    if (botSS[0] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[1] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[2] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[3] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[4] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[5] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[6] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[7] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[8] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[9] != 0) {
                                                        num2++;
                                                    }

                                                    //System.out.print(num2 + "num 2 \n");
                                                    if ((s < sum) || (s == sum && numsum > num2)) {
                                                        sum = s;
                                                        Movebot = 1;
                                                        botS[0] = botSS[0];
                                                        botS[1] = botSS[1];
                                                        botS[2] = botSS[2];
                                                        botS[3] = botSS[3];
                                                        botS[4] = botSS[4];
                                                        botS[5] = botSS[5];
                                                        botS[6] = botSS[6];
                                                        botS[7] = botSS[7];
                                                        botS[8] = botSS[8];
                                                        botS[9] = botSS[9];
                                                        botS[0] = botSS[0];
                                                        numsum = num2;
                                                    }
                                                }
                                                num2 = 0;
                                            }
                                        }
                                        botx2 = botx88[p];
                                        boty2 = boty88[p];//ย้ายไปตำแหน่งเดิม
                                    }
                                    botx2 = botx77[o];
                                    boty2 = boty77[o];//ย้ายไปตำแหน่งเดิม
                                }
                                botx2 = botx66[n];
                                boty2 = boty66[n];//ย้ายไปตำแหน่งเดิม
                            }
                            botx2 = botx55[m];
                            boty2 = boty55[m];//ย้ายไปตำแหน่งเดิม
                        }
                        botx2 = botx44[l];
                        boty2 = boty44[l];//ย้ายไปตำแหน่งเดิม
                    }
                    botx2 = botx33[k];
                    boty2 = boty33[k];//ย้ายไปตำแหน่งเดิม
                }
                botx2 = botx22[j];
                boty2 = boty22[j];//ย้ายไปตำแหน่งเดิม
                //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
            }
            botx2 = botx11[i];
            boty2 = boty11[i];//ย้ายไปตำแหน่งเดิม
            //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
        }
        botx2 = Mbotx2;
        boty2 = Mboty2;
        System.out.print("sum " + sum + " " + botS[0] + "/" + botS[1] + "/" + botS[2] + "/" + botS[3] + "/" + botS[4] + "/" + botS[5] + "/" + botS[6] + "/" + botS[7] + "/" + botS[8] + "/" + botS[9] + " จำนวน" + numsum + "move #####-----1\n");
        return new int[]{sum, numsum};
    }

    public int[] BOT4() {
        //int botx1, botx2, botx3, botx;
        //int boty1, boty2, boty3, boty;
        int num2 = 0;
        int num1 = 0;
        int numsum = 0;
        int Cbotx = -1;
        int Cboty = -1;
        int Movebot = 0;
        int sum = 99;
        String tall = "";
        //save1
        int botx1a[] = new int[]{botx3, botx3, botx3, botx3};
        int boty1a[] = new int[]{boty3, boty3, boty3, boty3};
        //System.out.print(botx + " botx/ " + boty + " boty เริ่มต้น\n");
        int botx11[] = new int[4];
        int boty11[] = new int[4];
        //save2
        int botx2a[] = new int[4];
        int boty2a[] = new int[4];
        int botx22[] = new int[4];
        int boty22[] = new int[4];
        //save3
        int botx3a[] = new int[4];
        int boty3a[] = new int[4];
        int botx33[] = new int[4];
        int boty33[] = new int[4];
        //save4
        int botx4[] = new int[4];
        int boty4[] = new int[4];
        int botx44[] = new int[4];
        int boty44[] = new int[4];
        //save5
        int botx5[] = new int[4];
        int boty5[] = new int[4];
        int botx55[] = new int[4];
        int boty55[] = new int[4];
        //save6
        int botx6[] = new int[4];
        int boty6[] = new int[4];
        int botx66[] = new int[4];
        int boty66[] = new int[4];
        //save7
        int botx7[] = new int[4];
        int boty7[] = new int[4];
        int botx77[] = new int[4];
        int boty77[] = new int[4];
        //save8
        int botx8[] = new int[4];
        int boty8[] = new int[4];
        int botx88[] = new int[4];
        int boty88[] = new int[4];
        //save9
        int botx9[] = new int[4];
        int boty9[] = new int[4];
        int botx99[] = new int[4];
        int boty99[] = new int[4];
        //save10
        int botx0[] = new int[4];
        int boty0[] = new int[4];
        int botx00[] = new int[4];
        int boty00[] = new int[4];

        //บันทึกการเดิน
        int botS[] = new int[10];
        int botSS[] = new int[10];

        for (int i = 0; i < 4; i++) {// ROUND 1
            //System.out.print(i + "ชั้นหนึ่ง++++++++++++++++++++++++++++++++++++++++++++++\n");
            key(i);
            int a[] = new int[2];
            a = botmove(botx1a[i], boty1a[i]);
            botx11[i] = a[0];
            boty11[i] = a[1];
            botSS[0] = a[2];
            botx3 = botx11[i];
            boty3 = boty11[i];
            //System.out.print(a[2] + "ai ");

            for (int j = 0; j < 4; j++) {// ROUND 2
                botx2a[0] = botx11[i];
                botx2a[1] = botx11[i];
                botx2a[2] = botx11[i];
                botx2a[3] = botx11[i];//coppy x
                boty2a[0] = boty11[i];
                boty2a[1] = boty11[i];
                boty2a[2] = boty11[i];
                boty2a[3] = boty11[i];//coppy y
                //System.out.print(j + "ชั้นสอง-");
                key(j);
                a = botmove(botx2a[j], boty2a[j]);
                botx22[j] = a[0];
                boty22[j] = a[1];
                botSS[1] = a[2];
                botx3 = botx22[j];
                boty3 = boty22[j];
                //System.out.print(a[2] + "aj");

                for (int k = 0; k < 4; k++) {// ROUND 3
                    botx3a[0] = botx22[j];
                    botx3a[1] = botx22[j];
                    botx3a[2] = botx22[j];
                    botx3a[3] = botx22[j];//coppy x
                    boty3a[0] = boty22[j];
                    boty3a[1] = boty22[j];
                    boty3a[2] = boty22[j];
                    boty3a[3] = boty22[j];//coppy y
                    //System.out.print(k + "ชั้นสาม-");
                    key(k);
                    a = botmove(botx3a[k], boty3a[k]);
                    botx33[k] = a[0];
                    boty33[k] = a[1];
                    botSS[2] = a[2];
                    botx3 = botx33[k];
                    boty3 = boty33[k];
                    //System.out.print(a[2] + "ak " + a[0] + "x " + a[1] + "y " + "a\n");

                    for (int l = 0; l < 4; l++) {// ROUND 4
                        botx4[0] = botx33[k];
                        botx4[1] = botx33[k];
                        botx4[2] = botx33[k];
                        botx4[3] = botx33[k];//coppy x
                        boty4[0] = boty33[k];
                        boty4[1] = boty33[k];
                        boty4[2] = boty33[k];
                        boty4[3] = boty33[k];//coppy y
                        //System.out.print(l + "ชั้นสี่-");
                        key(l);
                        a = botmove(botx4[l], boty4[l]);
                        botx44[l] = a[0];
                        boty44[l] = a[1];
                        botSS[3] = a[2];
                        botx3 = botx44[l];
                        boty3 = boty44[l];
                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                        for (int m = 0; m < 4; m++) {// ROUND 5
                            botx5[0] = botx44[l];
                            botx5[1] = botx44[l];
                            botx5[2] = botx44[l];
                            botx5[3] = botx44[l];//coppy x
                            boty5[0] = boty44[l];
                            boty5[1] = boty44[l];
                            boty5[2] = boty44[l];
                            boty5[3] = boty44[l];//coppy y
                            //System.out.print(m + "ชั้นห้า-");
                            key(m);
                            a = botmove(botx5[m], boty5[m]);
                            botx55[m] = a[0];
                            boty55[m] = a[1];
                            botSS[4] = a[2];
                            botx3 = botx55[m];
                            boty3 = boty55[m];
                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                            for (int n = 0; n < 4; n++) {// ROUND 6
                                botx6[0] = botx55[m];
                                botx6[1] = botx55[m];
                                botx6[2] = botx55[m];
                                botx6[3] = botx55[m];//coppy x
                                boty6[0] = boty55[m];
                                boty6[1] = boty55[m];
                                boty6[2] = boty55[m];
                                boty6[3] = boty55[m];//coppy y
                                //System.out.print(n + "ชั้นห้า-");
                                key(n);
                                a = botmove(botx6[n], boty6[n]);
                                botx66[n] = a[0];
                                boty66[n] = a[1];
                                botSS[5] = a[2];
                                botx3 = botx66[n];
                                boty3 = boty66[n];
                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                for (int o = 0; o < 4; o++) {// ROUND 7
                                    botx7[0] = botx66[n];
                                    botx7[1] = botx66[n];
                                    botx7[2] = botx66[n];
                                    botx7[3] = botx66[n];//coppy x
                                    boty7[0] = boty66[n];
                                    boty7[1] = boty66[n];
                                    boty7[2] = boty66[n];
                                    boty7[3] = boty66[n];//coppy y
                                    //System.out.print(n + "ชั้นห้า-");
                                    key(o);
                                    a = botmove(botx7[o], boty7[o]);
                                    botx77[o] = a[0];
                                    boty77[o] = a[1];
                                    botSS[6] = a[2];
                                    botx3 = botx77[o];
                                    boty3 = boty77[o];
                                    //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                    for (int p = 0; p < 4; p++) {// ROUND 8
                                        botx8[0] = botx77[o];
                                        botx8[1] = botx77[o];
                                        botx8[2] = botx77[o];
                                        botx8[3] = botx77[o];//coppy x
                                        boty8[0] = boty77[o];
                                        boty8[1] = boty77[o];
                                        boty8[2] = boty77[o];
                                        boty8[3] = boty77[o];//coppy y
                                        //System.out.print(n + "ชั้นห้า-");
                                        key(p);
                                        a = botmove(botx8[p], boty8[p]);
                                        botx88[p] = a[0];
                                        boty88[p] = a[1];
                                        botSS[7] = a[2];
                                        botx3 = botx88[p];
                                        boty3 = boty88[p];
                                        //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");
                                        for (int q = 0; q < 4; q++) {// ROUND 9
                                            botx9[0] = botx88[p];
                                            botx9[1] = botx88[p];
                                            botx9[2] = botx88[p];
                                            botx9[3] = botx88[p];//coppy x
                                            boty9[0] = boty88[p];
                                            boty9[1] = boty88[p];
                                            boty9[2] = boty88[p];
                                            boty9[3] = boty88[p];//coppy y
                                            //System.out.print(n + "ชั้นห้า-");
                                            key(q);
                                            a = botmove(botx9[q], boty9[q]);
                                            botx99[q] = a[0];
                                            boty99[q] = a[1];
                                            botSS[8] = a[2];
                                            botx3 = botx99[q];
                                            boty3 = boty99[q];
                                            //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                            for (int r = 0; r < 4; r++) {// ROUND 9
                                                botx0[0] = botx99[q];
                                                botx0[1] = botx99[q];
                                                botx0[2] = botx99[q];
                                                botx0[3] = botx99[q];//coppy x
                                                boty0[0] = boty99[q];
                                                boty0[1] = boty99[q];
                                                boty0[2] = boty99[q];
                                                boty0[3] = boty99[q];//coppy y
                                                //System.out.print(n + "ชั้นห้า-");
                                                key(r);
                                                a = botmove(botx9[r], boty9[r]);
                                                botx00[r] = a[0];
                                                boty00[r] = a[1];
                                                botSS[9] = a[2];
                                                botx3 = botx00[r];
                                                boty3 = boty00[r];
                                                //System.out.print(a[2] + "al " + a[0] + "x " + a[1] + "y " + "a\n");

                                                //System.out.print("ทาง--> " + botSS[0] + "/ " + botSS[1] + "/ " + botSS[2] + "/ " + botSS[3] + "/ " + botSS[4] + "/ " + botSS[5] + "\n");
                                                int x = Math.abs(xitem - botx00[r]);//------------------บันทึก
                                                int y = Math.abs(yitem - boty00[r]);
                                                int s = x + y;

                                                //System.out.print("-----------------------------------------" + s + "ระยะห่างX \n");
                                                if (s <= sum) {
                                                    if (botSS[0] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[1] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[2] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[3] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[4] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[5] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[6] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[7] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[8] != 0) {
                                                        num2++;
                                                    }
                                                    if (botSS[9] != 0) {
                                                        num2++;
                                                    }

                                                    //System.out.print(num2 + "num 2 \n");
                                                    if ((s < sum) || (s == sum && numsum > num2)) {
                                                        sum = s;
                                                        Movebot = 1;
                                                        botS[0] = botSS[0];
                                                        botS[1] = botSS[1];
                                                        botS[2] = botSS[2];
                                                        botS[3] = botSS[3];
                                                        botS[4] = botSS[4];
                                                        botS[5] = botSS[5];
                                                        botS[6] = botSS[6];
                                                        botS[7] = botSS[7];
                                                        botS[8] = botSS[8];
                                                        botS[9] = botSS[9];
                                                        botS[0] = botSS[0];
                                                        numsum = num2;
                                                    }
                                                }
                                                num2 = 0;
                                            }
                                        }
                                        botx3 = botx88[p];
                                        boty3 = boty88[p];//ย้ายไปตำแหน่งเดิม
                                    }
                                    botx3 = botx77[o];
                                    boty3 = boty77[o];//ย้ายไปตำแหน่งเดิม
                                }
                                botx3 = botx66[n];
                                boty3 = boty66[n];//ย้ายไปตำแหน่งเดิม
                            }
                            botx3 = botx55[m];
                            boty3 = boty55[m];//ย้ายไปตำแหน่งเดิม
                        }
                        botx3 = botx44[l];
                        boty3 = boty44[l];//ย้ายไปตำแหน่งเดิม
                    }
                    botx3 = botx33[k];
                    boty3 = boty33[k];//ย้ายไปตำแหน่งเดิม
                }
                botx3 = botx22[j];
                boty3 = boty22[j];//ย้ายไปตำแหน่งเดิม
                //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
            }
            botx3 = botx11[i];
            boty3 = boty11[i];//ย้ายไปตำแหน่งเดิม
            //System.out.print(botx + " botx/ " + boty + " boty ย้ายกลับที่เดิม \n");
        }
        botx3 = Mbotx3;
        boty3 = Mboty3;
        System.out.print("sum " + sum + " " + botS[0] + "/" + botS[1] + "/" + botS[2] + "/" + botS[3] + "/" + botS[4] + "/" + botS[5] + "/" + botS[6] + "/" + botS[7] + "/" + botS[8] + "/" + botS[9] + " จำนวน" + numsum + "move #####-----1\n");
        return new int[]{sum, numsum};
    }

    public void F() {
        System.out.print("1:ขวา 2:ซ้าย 3:ขึ้น 4:ลง 0:ไม่มี \n");
        int a[] = BOT1();
        int b[] = BOT2();
        int c[] = BOT3();
        int d[] = BOT4();

        int sum[] = {a[0], b[0], c[0], d[0]};
        int numsum[] = {a[1], b[1], c[1], d[1]};
        int min[] = {99, 99, 99, 99};
        int choose = 1;
        int minn = 99;
        for (int i = 0; i < 4; i++) {
            if (sum[i] == 0) {
                min[i] = numsum[i];
            }
        }
        //System.out.print("min" + min[0] + min[1] + min[2] + min[3] + "\n");

        for (int i = 1; i < 4; i++) {
            if (min[i - 1] >= min[i] && min[i] < minn) {
                choose = i + 1;
                //System.out.print(choose + "choose\n");
                minn = min[i];

            } else if (min[i - 1] < min[i] && min[i - 1] < minn) {
                choose = i;
                minn = min[i - 1];
                //System.out.print(choose + "choose\n");
            }
        }
        System.out.print("CHOOSE: BOT" + choose + " = " + min[choose - 1] + "\n");

        numbot = min[choose - 1];

        num++;

    }

}
