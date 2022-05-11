package com.app;

import javax.swing.*;
import java.awt.*;



/***
 *  The Application that simply compare two files
 *  provide the standard output.
 *
 * @author  Mahnoor
 * @version 1.0
 * @since   2022-04-20
 *
 * */

public class splash extends JWindow {
    JFrame frame;

    JLabel image=new JLabel(new ImageIcon("src/com/app/image.gif"));
    JLabel text=new JLabel("PARKWELL");
    JLabel version=new JLabel(" V 1.0  -  2022 ");
    JProgressBar progressBar=new JProgressBar();
    JLabel message=new JLabel();//Crating a JLabel for displaying the message
    splash()
    {
        createGUI();
        addImage();
        addText();
        addVersion();
        addProgressBar();
        addMessage();
        runningPBar();
    }
    public void createGUI(){
        frame=new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(95,158,160));
        frame.setVisible(true);

    }
    public void addImage(){
        image.setSize(600,200);
        frame.add(image);
    }
    public void addText()
    {
        text.setFont(new Font("arial",Font.BOLD,70));
        text.setBounds(100,120,650,50);
        text.setForeground(Color.WHITE);
        frame.add(text);
    }
    public void addVersion(){
        version.setFont(new Font("arial",Font.BOLD,20));
        version.setBounds(310,190,200,40);
        version.setForeground(Color.WHITE);
        frame.add(version);
    }
    public void addMessage()
    {
        message.setBounds(250,320,200,40);//Setting the size and location of the label
        message.setForeground(Color.black);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        frame.add(message);//adding label to the frame
    }
    public void addProgressBar(){
        progressBar.setBounds(100,280,400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.yellow);
        progressBar.setValue(0);
        frame.add(progressBar);
    }
    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=100)
        {
            try{
                Thread.sleep(10);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
              //  message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100)
                    frame.dispose();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
