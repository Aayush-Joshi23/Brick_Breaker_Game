import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int TotalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int playerX = 320;
    private int ballposX = 120;
    private int ballposY = 350;

    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    public GamePlay()
    {
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        //bg
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);


        // drawing map
        map.draw((Graphics2D)g);

        //Border
        g.setColor(Color.orange);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        //paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(play)
        {
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
            {
                ballYdir = -ballYdir;
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0)
            {
                ballXdir = -ballXdir;
            }
            if(ballposY < 0)
            {
                ballYdir = -ballYdir;
            }
            if(ballposX > 670)
            {
                ballXdir = -ballXdir;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(playerX >= 600)
            {
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(playerX<10)
            {
                playerX = 10;
            }
            else
            {
                moveLeft();
            }
        }

    }


    private void moveRight() {

        play = true;
        playerX += 20;
    }
    private void moveLeft() {

        play = true;
        playerX -= 20;
    }


}