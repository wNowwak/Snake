import javax.swing.*;

import java.awt.*;


public class Main {
//    private JPanel panel;
    private JFrame frame;

    public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int framewidth = (int) (0.8*(dimension.width)) - ((int) (0.8*(dimension.width))%25)+13;
    public static final int frameheight =(int) (0.8*(dimension.height)) - ((int) (0.8*(dimension.height))%25);
    public Main(){
        Rozgrywka rozgrywka = new Rozgrywka();
        frame = new JFrame("SNAKE");
        frame.setBounds(100,100, framewidth, frameheight);
        frame.getContentPane().setBackground( new Color(125,160,54));
        frame.add(rozgrywka);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println(framewidth+ "\n" + frameheight);
    }

    public static void main(String[] args) {
       Main snake = new Main();

    }
}
