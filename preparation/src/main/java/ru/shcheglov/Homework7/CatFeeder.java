package ru.shcheglov.Homework7; /**
 * Java 1. Homework 7. Cat Feeder game.
 * Class: MainClass
 *
 * @author Alexey Shcheglov
 * @version dated Dec 16, 2017
 * @link https://github.com/Pharmazon
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class CatFeeder extends JFrame{

    final String TITLE_OF_PROGRAM = "Cat Feeder by Alexey Shcheglov";
    final int WINDOW_WIDTH = 300;
    final int WINDOW_HEIGHT = 400;
    final int NUMBER_OF_CATS = 10;
    final int PLATE_VOLUME = 50;
    final int PLATE_INIT = 25;
    final int PLATE_ADD = 5;
    final int MAX_APPETITE = 20;
    final String BTN_RESET = "Reset";
    final String BTN_ADDFOOD = "Add food";
    final String BTN_FEEDCATS = "Feed cats";
    
    Panel panel;
    PlateG plate;
    CatG[] cats;

    public static void main(String[] args) {       
        new CatFeeder();
    }
    
    public CatFeeder() {
        //main window customization
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        //initializzation of Objects
        panel = new Panel();
        panel.setBackground(Color.white);
        Random rand = new Random();
        plate = new PlateG(PLATE_INIT, PLATE_VOLUME);
        cats = new CatG[NUMBER_OF_CATS];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new CatG("Cat" + i + 1, rand.nextInt(MAX_APPETITE) + 1);                
        }
        
        //add "Add food" button and event for it
        JButton addfood = new JButton(BTN_ADDFOOD);
        addfood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                plate.addFood(PLATE_ADD);
                panel.repaint();
            }
        });          
        
        //add "Feed cats" button and event for it
        JButton feedcats = new JButton(BTN_FEEDCATS);
        feedcats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(CatG cat : cats) {
                    cat.eat(plate);
                }
                panel.repaint();
            }
        });          

        //add "Reset" button and event for it
        JButton reset = new JButton(BTN_RESET);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(CatG cat : cats) {
                    cat.setFull(false);
                }
                panel.repaint();
            }
        });        
        
        //create panel for buttons group
        JPanel panelBtn = new JPanel();
        
        //add grid layout to button panel
        panelBtn.setLayout(new GridLayout());
        
        //add buttons to panel
        panelBtn.add(addfood);
        panelBtn.add(feedcats);
        panelBtn.add(reset);
        
        //add border layout to button panel
        setLayout(new BorderLayout());
        
        //add border layouts to main panel and button panel
        add(panel, BorderLayout.CENTER);
        add(panelBtn, BorderLayout.NORTH);
        
        //show main window
        setVisible(true);
    }
    
    class Panel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Dimension size = this.getSize();
            plate.paint(g, (int)size.getWidth(), 20);
            for (int i = 0; i < cats.length; i++)
                cats[i].paint(g, (i + 1) * 30 + 10,
                    (int)(size.getWidth()) / PLATE_VOLUME, 20);
        }        
    }

}



