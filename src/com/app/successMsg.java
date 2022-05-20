package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    JFrame frame;
    JLabel image = new JLabel(new ImageIcon("src/com/app/imageLoader.gif"));
    JLabel text = new JLabel(" Both Files Compared Successfully ");
    JLabel message = new JLabel();
    JProgressBar progressBar=new JProgressBar();
    successMsg() {
        createGUI();
      //  addImage();
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
        text.setFont(new Font("arial", Font.BOLD, 30));
        text.setBounds(90, 200, 650, 100);
        text.setForeground(Color.YELLOW);
        frame.add(text);
    }
}
