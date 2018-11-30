/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Cladius
 */
public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setTitle("Snake Game");
        obj.setBounds(10,10,900,700);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.setLocationRelativeTo(null);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
    
}
