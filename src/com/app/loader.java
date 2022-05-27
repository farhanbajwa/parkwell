package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/***
 *  The Application that simply compare two files
 *  provide the standard output.
 *
 * @author  Mahnoor
 * @version 1.0
 * @since   2022-04-20
 *
 * */

public class loader {

    Font  f;

    {
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.home")+"/parkwellMain/Montserrat-Bold.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Font font = f.deriveFont(Font.PLAIN , 25f);

    JFrame frame;
    JLabel image = new JLabel(new ImageIcon("src/com/app/imageLoader.gif"));
    JLabel text = new JLabel(" Comparing, Please wait... ");
    JLabel message = new JLabel();
    JProgressBar progressBar=new JProgressBar();
    loader() {
        createGUI();
        //addImage();
        addText();
        Timer timer = new Timer(6000, new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                frame.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void createGUI() {
        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(810, 390);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(95, 158, 160));
        frame.setVisible(true);

    }

    public void addImage() {
        image.setSize(600, 500);
        image.setBounds(150, 300, 650, 100);
        frame.add(image);
    }

    public void addText() {
        Font font = f.deriveFont(Font.BOLD , 30f);
        text.setFont(font);
        text.setBounds(90, 200, 750, 100);
        text.setForeground(Color.YELLOW);
        frame.add(text);
    }
}


