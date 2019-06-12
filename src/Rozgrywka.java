import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Rozgrywka extends JPanel implements KeyListener, ActionListener {

    public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenHeight = (int) (dimension.height*0.8);
    private int screenWidth = (int) (dimension.width*0.8);
    private int[] dlugoscWezaX = new int[500];
    private int[] dlugoscWezaY = new int[500];
    private int dlugoscSnake = 3;
    private int ruch = 0;

    private ImageIcon head_down;
    private ImageIcon head_up;
    private ImageIcon head_left;
    private ImageIcon head_right;
    private ImageIcon body;
    private ImageIcon food;
    private Timer timer;

    private boolean gameOver = false;
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;

    public Rozgrywka(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(200,this);
        timer.start();
    }

    public void paint(Graphics graphics){

        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,screenWidth,screenHeight);
//            graphics.setColor(Color.WHITE);
        if(ruch == 0 ){

            dlugoscWezaY [0]     = 50;
            dlugoscWezaY [1] = 25;
            dlugoscWezaY [2] = 0;

            dlugoscWezaX [0] = 10;
            dlugoscWezaX [1] = 10;
            dlugoscWezaX [2] = 10;

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
//        head_up = new ImageIcon("img/head_up.png");
//        head_right = new ImageIcon("img/head_right.png");
//        head_left = new ImageIcon("img/head_left.png");
        graphics.dispose();
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
                if( dlugoscWezaX[i]>screenWidth){
                    dlugoscWezaX[i]=0;
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
                if( dlugoscWezaX[i]<0){
                    dlugoscWezaX[i]=screenWidth;
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
                if( dlugoscWezaY[i]<0){
                    dlugoscWezaY[i]=screenHeight;
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
                if( dlugoscWezaY[i]>screenHeight){
                    dlugoscWezaY[i]=0;
                }
            }
            repaint();
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
