package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class successMsg {

    JFrame frame;
    JLabel image = new JLabel(new ImageIcon("src/com/app/image.gif"));
    JLabel text = new JLabel(" Both files compared successfully ");
    JLabel message = new JLabel();
    JProgressBar progressBar=new JProgressBar();
    successMsg() {
        createGUI();
        addImage();
        addText();

        //   addProgressBar();
        //  runningPBar();
//        Timer timer1 = new Timer(3000, new ActionListener(){
//            public void actionPerformed(ActionEvent evt) {
//                addText();
//
//            }
//        });
//        timer1.setRepeats(false);
//        timer1.start();


        Timer timer = new Timer(13000, new ActionListener(){
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
        frame.setSize(990, 600);
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
    public void addProgressBar(){
        progressBar.setBounds(300,380,400,50);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.yellow);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=500)
        {
            try{

                //Thread.sleep(10);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100){}
                //  frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
