import javax.swing.*;

import java.awt.*;


public class Main {
    private JPanel panel;
    private JFrame frame;

    public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public Main(){
        Rozgrywka rozgrywka = new Rozgrywka();
        frame = new JFrame("SNAKE");
        frame.setBounds(100,100, (int) (0.8*(dimension.width)), (int) (0.8*dimension.height));
        frame.getContentPane().setBackground( new Color(125,160,54));
        frame.add(rozgrywka);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


    }

    public static void main(String[] args) {
        System.out.println(dimension);
        Main snake = new Main();
    }
}
