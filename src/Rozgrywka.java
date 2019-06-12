import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Rozgrywka extends JPanel implements KeyListener, ActionListener {

    public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int framewidth = (int) (0.8*(dimension.width)) - ((int) (0.8*(dimension.width))%25);
    public static final int frameheight =(int) (0.8*(dimension.height)) - ((int) (0.8*(dimension.height))%25);
    private int[] dlugoscWezaX = new int[500];
    private int[] dlugoscWezaY = new int[500];
    private int dlugoscSnake = 3;
    private int ruch = 0;

    private Random randomFoodPoss = new Random( );
    private int foodXpom = randomFoodPoss.nextInt(1000) ;
    private int foodYpom = randomFoodPoss.nextInt(500) ;
    private int foodX = foodXpom- (foodXpom%25)+1;
    private int foodY = foodYpom - (foodYpom%25)+1;
    private ImageIcon head_down;
    private ImageIcon head_up;
    private ImageIcon head_left;
    private ImageIcon head_right;
    private ImageIcon body;
    private ImageIcon wall;
    private ImageIcon food;
    private Timer timer;

    private boolean gameOver = false;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    public Rozgrywka(){
        System.out.println(foodX);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(100,this);
        timer.start();
        if (gameOver == true)
            timer.stop();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        System.out.println(dlugoscWezaX[0]+"\t"+dlugoscWezaY[0]);
        food = new ImageIcon("img/apple.png");
        food.paintIcon(this,graphics,foodX,foodY);

        if (foodX == dlugoscWezaX[0] && foodY == dlugoscWezaY[0]){
            dlugoscSnake++;
            foodXpom = randomFoodPoss.nextInt(1000) ;
            foodYpom = randomFoodPoss.nextInt(500) ;
            foodX = foodXpom- (foodXpom%25)+1;
            foodY = foodYpom - (foodYpom%25)+1;
        }

        graphics.setColor(Color.RED);
//        System.out.println(frameheight);
        graphics.drawRect(0,0,framewidth-17,frameheight-40);

////            graphics.setColor(Color.WHITE);
        if(ruch == 0 ){

            dlugoscWezaY [0]     = 51;
            dlugoscWezaY [1] = 26;
            dlugoscWezaY [2] = 1;

            dlugoscWezaX [0] = 1;
            dlugoscWezaX [1] = 1;
            dlugoscWezaX [2] = 1;

        }


        head_down = new ImageIcon("img/head_down.png");
        head_down.paintIcon(this,graphics,dlugoscWezaX[0],dlugoscWezaY[0]);
        for(int i = 0 ; i<dlugoscSnake; i++){

            if(i==0 && down){
                head_down = new ImageIcon("img/head_down.png");
                head_down.paintIcon(this,graphics,dlugoscWezaX[i],dlugoscWezaY[i]);
            }

            if(i==0 && up){
                head_up = new ImageIcon("img/head_up.png");
                head_up.paintIcon(this,graphics,dlugoscWezaX[i],dlugoscWezaY[i]);
            }

            if(i==0 && left){
                head_left = new ImageIcon("img/head_left.png");
                head_left.paintIcon(this,graphics,dlugoscWezaX[i],dlugoscWezaY[i]);
            }

            if(i==0 && right){
                head_right = new ImageIcon("img/head_right.png");
                head_right.paintIcon(this,graphics,dlugoscWezaX[i],dlugoscWezaY[i]);
            }

            if (i!=0){
                body = new ImageIcon("img/body.jpg");
                body.paintIcon(this,graphics,dlugoscWezaX[i],dlugoscWezaY[i]);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();

        if (right){

            for (int i =dlugoscSnake-1; i>=0 ;i--){
                dlugoscWezaY[i+1]= dlugoscWezaY[i];
            }
            for (int i = dlugoscSnake; i>=0; i--){
                if(i==0){
                    dlugoscWezaX[i]= dlugoscWezaX[i]+25;
                }
                else {
                    dlugoscWezaX[i] = dlugoscWezaX[i-1];
                }
                if( dlugoscWezaX[i] == 1501){
                   gameOver = true;
                }
            }
            repaint();
        }
        if (left){
            for (int i =dlugoscSnake-1; i>=0 ;i--){
                dlugoscWezaY[i+1]= dlugoscWezaY[i];
            }
            for (int i = dlugoscSnake; i>=0; i--){
                if(i==0){
                    dlugoscWezaX[i]= dlugoscWezaX[i]-25;
                }
                else {
                    dlugoscWezaX[i] = dlugoscWezaX[i-1];
                }
                if( dlugoscWezaX[i]== 1){
                    gameOver = true;
                }
            }
            repaint();
        }
        if (down){
            for (int i =dlugoscSnake-1; i>=0 ;i--){
                dlugoscWezaX[i+1]= dlugoscWezaX[i];
            }
            for (int i = dlugoscSnake; i>=0; i--){
                if(i==0){
                    dlugoscWezaY[i]= dlugoscWezaY[i]+25;
                }
                else {
                    dlugoscWezaY[i] = dlugoscWezaY[i-1];
                }
                if( dlugoscWezaY[i]<26){
                    gameOver=true;
                }
            }
            repaint();
        }
        if (up){
            for (int i =dlugoscSnake-1; i>=0 ;i--){
                dlugoscWezaX[i+1]= dlugoscWezaX[i];
            }
            for (int i = dlugoscSnake; i>=0; i--){
                if(i==0){
                    dlugoscWezaY[i]= dlugoscWezaY[i]-25;
                }
                else {
                    dlugoscWezaY[i] = dlugoscWezaY[i-1];
                }
                if( dlugoscWezaY[i]>frameheight-40){
                   gameOver = true;
                }
            }
            repaint();
        }
        if (gameOver){
            timer.stop();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            ruch++;
            right = true;

            if(!left){
                right = true;
            }else{
                right=false;
                left=true;
            }
            up = false;
            down = false;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            ruch++;
            left = true;

            if(!right){
                left = true;
            }else{
                left=false;
                right=true;
            }
            up = false;
            down = false;

        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            ruch++;
            up = true;

            if(!down){
                up = true;
            }else{
                up=false;
                down=true;
            }
            left = false;
            right = false;

        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            ruch++;
            down = true;

            if(!up){
                down = true;
            }else{
                down=false;
                up=true;
            }
            left = false;
            right = false;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
