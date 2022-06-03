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

public class successMsg {

    Font  f;

    {
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.home")+"/parkwellMain/Montserrat-Bold.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }


    JFrame frame;

    JLabel text = new JLabel(" Both Files Compared Successfully ");

    successMsg() {
        createGUI();
        addText();

        Timer timer = new Timer(8000, new ActionListener(){
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
        frame.setSize(850, 390);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(95, 158, 160));
        frame.setVisible(true);

    }


    public void addText() {
        Font font = f.deriveFont(Font.PLAIN , 30f);
        text.setFont(font);
        text.setBounds(90, 200, 650, 100);
        text.setForeground(Color.YELLOW);
        frame.add(text);
    }
}