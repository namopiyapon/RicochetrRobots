
package testgame;
import java.awt.HeadlessException;
import java.awt.GridLayout;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class testgame extends JFrame {
    // กำหนดหน้าจอเฟรม
    public testgame(){
        setTitle("ROBOT");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1,0,0));
        start();
        setVisible(true);
    }
    
    //ใส่หน้าจอย่อยลงในจอหลัก
    public void start(){
        Scene s =new Scene();
        
        add(s);
        pack();
        setLocationRelativeTo(null);
    }
    //รัน
    public static void main(String[] args){
        new testgame();
        
    }
}
