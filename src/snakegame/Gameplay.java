/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Cladius
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    // Folder of the image assets
    static final File dirImages = new File("src/img");

    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[700];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightMouth;
    private ImageIcon leftMouth;
    private ImageIcon upMouth;
    private ImageIcon downMouth;

    private int lengthOfSnake = 3;

    private Timer timer;
    private int delay = 100;
    
    private int[] enemyYPos ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800};
    private int[] enemyXPos ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800};
    
    private ImageIcon enemyImage;
    private Random rand = new Random();
    private int xpos = rand.nextInt(34);
    private int ypos = rand.nextInt(23);
    
    private int score = 0;
    private int moves = 0;

    private ImageIcon snakeImage;
    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        
        if (moves == 0) {
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;
            
            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }
        // Draw title image border
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);

        // Draw the title image
        titleImage = new ImageIcon(dirImages + "/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        // Draw border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(50, 74, 801, 577);

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(51, 75, 800, 576);
        
        // Draw score
        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: "+score, 780, 30);
        
        // Draw score length
        g.setColor(Color.YELLOW);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: "+lengthOfSnake, 780, 50);

        rightMouth = new ImageIcon(dirImages + "/rightmouth.png");
        rightMouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);

        for (int i = 0; i < lengthOfSnake; i++) {
            if (i == 0 && right) {
                rightMouth = new ImageIcon(dirImages + "/rightmouth.png");
                rightMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && left) {
                leftMouth = new ImageIcon(dirImages + "/leftmouth.png");
                leftMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && up) {
                upMouth = new ImageIcon(dirImages + "/upmouth.png");
                upMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            if (i == 0 && down) {
                downMouth = new ImageIcon(dirImages + "/downmouth.png");
                downMouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
            
            if (i!=0) {
                snakeImage = new ImageIcon(dirImages + "/snakeimage.png");
                snakeImage.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
            }
        }

        enemyImage = new ImageIcon(dirImages + "/enemy.png");
        
        if (enemyXPos[xpos] == snakeXlength[0] && enemyYPos[ypos] == snakeYlength[0]) {
            score++;
            lengthOfSnake++;
            xpos = rand.nextInt(34);
            ypos = rand.nextInt(23);
        }
        
        enemyImage.paintIcon(this, g, enemyXPos[xpos], enemyYPos[ypos]);
        
        for (int i = 1; i < lengthOfSnake; i++) {
            if (snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0]) {
                right = false;
                left = false;
                up = false;
                down = false;
                
                g.setColor(Color.WHITE);
                g.setFont(new Font("arial", Font.BOLD,50));
                g.drawString("Game Over", 300, 300);
                
                g.setFont(new Font("arial", Font.BOLD,20));
                g.drawString("Hit SPACE to RESTART", 350, 340);
            }
        }
        
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            moves = 0;
            score = 0;
            lengthOfSnake = 3;
            repaint();
        }
        
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moves++;
            right = true;
            if (!left) {
                right = true;
            } else {
                right = false;
                left = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moves++;
            left = true;
            if (!right) {
                left = true;
            } else {
                left = false;
                right = true;
            }
            up = false;
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moves++;
            up = true;
            if (!down) {
                up = true;
            } else {
                up = false;
                down = true;
            }
            left = false;
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moves++;
            down = true;
            if (!up) {
                down = true;
            } else {
                down = false;
                up = true;
            }
            left = false;
            right = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int i = lengthOfSnake-1; i >=0 ; i--) {
                snakeYlength[i+1] = snakeYlength[i];
            }
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeXlength[i] = snakeXlength[i] + 25;
                } else {
                    snakeXlength[i] = snakeXlength[i-1];
                }
                if (snakeXlength[i] > 850) {
                    snakeXlength[i] = 25;
                }
            }
            repaint();
        }
        if (left) {
            for (int i = lengthOfSnake-1; i >=0 ; i--) {
                snakeYlength[i+1] = snakeYlength[i];
            }
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeXlength[i] = snakeXlength[i] - 25;
                } else {
                    snakeXlength[i] = snakeXlength[i-1];
                }
                if (snakeXlength[i] < 25) {
                    snakeXlength[i] = 850;
                }
            }
            repaint();
        }
        if (up) {
            for (int i = lengthOfSnake-1; i >=0 ; i--) {
                snakeXlength[i+1] = snakeXlength[i];
            }
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] - 25;
                } else {
                    snakeYlength[i] = snakeYlength[i-1];
                }
                if (snakeYlength[i] > 850) {
                    snakeYlength[i] = 75;
                }
            }
            repaint();
        }
        if (down) {
            for (int i = lengthOfSnake-1; i >=0 ; i--) {
                snakeXlength[i+1] = snakeXlength[i];
            }
            for (int i = lengthOfSnake; i >= 0; i--) {
                if (i == 0) {
                    snakeYlength[i] = snakeYlength[i] + 25;
                } else {
                    snakeYlength[i] = snakeYlength[i-1];
                }
                if (snakeYlength[i] < 25) {
                    snakeYlength[i] = 850;
                }
            }
            repaint();
        }
    }
}
