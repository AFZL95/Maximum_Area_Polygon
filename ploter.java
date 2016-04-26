import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public  class ploter extends JFrame {
    CartesianPanel panel;

    public void plot() {
        panel = new CartesianPanel();
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cartesian");
        setSize(1000, 1000);
        setVisible(true);
        setResizable(false);

    }

    public static void main(String args[]) {


        ploter frame = new ploter();
        frame.plot();

    }


    class CartesianPanel extends JPanel {

        public void paintComponent(Graphics g) {
            Scanner SCN = new Scanner(System.in);

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;

            //preparing the panel

            g2.translate(500, 500);
            g2.scale(1.0, -1.0);
            g2.setBackground(Color.DARK_GRAY);
            g2.setColor(Color.black);
            g2.drawLine(0, 500, 0, -500);
            g2.drawLine(-500, 0, 500, 0);

            System.out.print("how many coordinates you want to enter?");
            int n = SCN.nextInt();

            g2.setColor(Color.black);

            g2.fillRect(-600 , 0 , 4 ,4);
            g2.fillRect(-300 , -200 , 4 ,4);
            g2.fillRect(0 , -400 , 4 ,4);
            g2.fillRect(100 , -200 ,4 ,4);
            g2.fillRect(300 , 0 ,4 ,4);
            g2.fillRect(100 , 100 , 4 ,4);
            g2.fillRect(-200 , 200 , 4 , 4);
            g2.fillRect(-100 , 300 , 4 ,4);
            g2.fillRect(-100 , 500 , 4 ,4);
            g2.fillRect(200 , 400 , 4 , 4);
            g2.fillRect(800 , 0 , 4, 4);
            g2.fillRect(600 , 300 , 4 , 4);

        

        }
    }
}
