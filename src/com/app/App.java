package com.app;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class App extends JPanel {

    public  static  void main(String[] args){

        final JPanel panelError = new JPanel();
        panelError.setSize(200,200);
        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = " %-7p %d [%t] %c %x - %m%n ";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("applog.txt");
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();

        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);

        // creates a custom logger and log messages
        Logger logger = Logger.getLogger(App.class);
        //logger.debug("this is a debug log message");
        //logger.info("this is a information log message");
        //logger.warn("this is a warning log message");

        runfont();

        Set<String> a = new HashSet<String>();
        Set<String> b = new HashSet<String>();

        JFrame frame = new JFrame("Upload Files");
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

    JLabel label1 = new JLabel("Upload File One"); label1.setHorizontalAlignment(JLabel.CENTER);
    JLabel label2 = new JLabel("Upload File Two");label2.setHorizontalAlignment(JLabel.CENTER);
    JLabel label3 = new JLabel("Get your File");label3.setHorizontalAlignment(JLabel.CENTER);

    JButton file1 = new JButton();
    JButton file2 = new JButton();
    JButton download = new JButton();
    JButton reset = new JButton();

    file1.setText("File 1");file1.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
    file2.setText("File 2");file2.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
    download.setText("Download");download.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
    reset.setText("Reset");download.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));

        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 70;
    panel.add(label1,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets =  new Insets(17,17,20,17);
    panel.add(file1,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
    panel.add(label2,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 3;
    panel.add(file2,gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 5;
    panel.add(label3,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 5;
    panel.add(download,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 6;
    panel.add(reset,gbc);

    frame.add(panel);

        frame.setSize(900,900);
        panel.setSize(1000,1000);

       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);

    //file 1
    file1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("method call");
            FileReadActionPerformed(e);
        }

        private void FileReadActionPerformed(java.awt.event.ActionEvent evt) {
            System.out.println("Select a file");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize(new Dimension(700, 700));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files(*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(System
                    .getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                        //null empty not added
                        if(line != null) {
                            if(!line.equals("")) {
                                a.addAll(Collections.singleton(line));
                                System.out.println(line);
                            }
                        }
                    }
                    String all = sb.toString();
                 // textArea1.setText(all);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JPanel.class.getName()).log(Level.ALL, null, ex);
                    }
                }
            }
            if(!a.isEmpty()) {
                file1.setEnabled(false);
            }
        }
    });

// file 2
    file2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("method call");
            FileReadActionPerformed(e);
        }

        private void FileReadActionPerformed(java.awt.event.ActionEvent evt) {
            System.out.println("select 2nd file");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setPreferredSize(new Dimension(700, 700));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files(*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File(System
                    .getProperty("user.home")));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                        // null empty not added
                        if(line != null) {
                            if(!line.equals("")) {
                                b.addAll(Collections.singleton(line));
                                System.out.println(line);
                            }
                        }
                    }
                    String all = sb.toString();
                       //textArea2.setText(all);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JPanel.class.getName()).log(Level.ALL, null, ex);
                    }
                }
            }
            if(!b.isEmpty()) {
                file2.setEnabled(false);
            }
        }
    });

        //download
        download.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
                System.out.print("A"+a);
                System.out.print("\n");
                System.out.print("B"+b);

                List<String> listA = new ArrayList<String>(a);
                for(int i = 0 ; i < listA.size() ; i++){
                    if(specialCharactersString.contains(listA.get(i))) {
                        System.out.print("\n File 1 contains special character \""  + listA.get(i) + "\" \n");
                        logger.warn("\n File 1 contains special character \""  + listA.get(i) + "\" ");
                        JOptionPane.showMessageDialog(panel, "\n File 1 contains special character \""  + listA.get(i) + "\" ", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        //set empty
                        a.clear();
                        b.clear();
                        file1.setEnabled(true);
                        file2.setEnabled(true);
                        System.out.print("\t"+a+"\t"+b);
                        System.out.print("Reset");
                        return;
                    }
                }
                List<String> listB = new ArrayList<String>(b);
                for(int i = 0 ; i < listB.size() ; i++){
                    if(specialCharactersString.contains(listB.get(i))) {
                        System.out.print("\n File 2 contains special character \""  + listB.get(i) + "\" \n");
                        logger.warn("\n File 1 contains special character \""  + listB.get(i) + "\" ");
                        JOptionPane.showMessageDialog(panel, "\n File 1 contains special character \""  + listB.get(i) + "\" ", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                        //set empty
                        a.clear();
                        b.clear();
                        file1.setEnabled(true);
                        file2.setEnabled(true);
                        System.out.print("\t"+a+"\t"+b);
                        System.out.print("Reset");
                        return;
                    }
                }
                if(a.isEmpty() || b.isEmpty()){
                    System.out.print(" both files should be selected\n");
                    logger.warn("\n both files should be selected ");
                    JOptionPane.showMessageDialog(panel, "\n Both Files should be Selected ", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                    //set empty
                    a.clear();
                    b.clear();
                    file1.setEnabled(true);
                    file2.setEnabled(true);
                    System.out.print("\t"+a+"\t"+b);
                    System.out.print("Reset");
                    return;
                }
                // To find intersection
                Set<String> intersection1 = new HashSet<String>(a);
                intersection1.removeAll(b);
                System.out.print("\nIntersection of the two Set a-b");
                System.out.println("\n"+intersection1);
                // To find intersection
                Set<String> intersection2 = new HashSet<String>(b);
                intersection2.removeAll(a);
                System.out.print("\nIntersection of the two Set b-a");
                System.out.println("\n"+intersection2);
                a.clear();
                b.clear();
                file1.setEnabled(true);
                file2.setEnabled(true);
                if(intersection1.isEmpty() && intersection2.isEmpty()){
                    System.out.print("\n File 1 and File 2 are Same");
                    logger.warn("\n File 1 and File 2 are Same");
                    JOptionPane.showMessageDialog(panel, "\n BOth Files are Same", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                // creating PDf
                String path ="";
                JFileChooser j  = new JFileChooser();
                j.setCurrentDirectory(new File(System
                        .getProperty("user.home")));
                int x=j.showSaveDialog(frame);
                if(x== JFileChooser.APPROVE_OPTION){
                    path = j.getSelectedFile().getPath();
                }

                Document document = new Document();
                try
                {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"_File1.pdf"));
                    document.open();
                    document.add(new Paragraph("1st File"));
                    for (String temp : intersection1) {
                        if (!temp.equals(",")) {
                            document.add(new Paragraph(String.valueOf(temp)));
                        }
                    }
                    document.close();
                    writer.close();
                } catch (DocumentException error)
                {
                    error.printStackTrace();
                } catch (FileNotFoundException error)
                {
                    error.printStackTrace();
                }
                //pdf 2
                Document document1 = new Document();
                try
                {
                    PdfWriter writer1 = PdfWriter.getInstance(document1, new FileOutputStream(path+"_File2.pdf"));
                    document1.open();
                    document1.add(new Paragraph("2nd File"));
                    for (String temp : intersection2) {
                        if (!temp.equals(",")) {
                            document1.add(new Paragraph(String.valueOf(temp)));
                        }
                    }
                    document1.close();
                    writer1.close();
                   // JOptionPane.showMessageDialog(panel, "\n Files Downloaded Successfully  ", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                } catch (DocumentException error)
                {
                    error.printStackTrace();
                } catch (FileNotFoundException error)
                {
                    error.printStackTrace();
                }
            }

        });

        //reset
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.clear();
                b.clear();
                file1.setEnabled(true);
                file2.setEnabled(true);
                System.out.print("\t"+a+"\t"+b);
                System.out.print("Reset");
                JOptionPane.showMessageDialog(panelError, " Clear, Select New Files ", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        });
}

public static void runfont(){
    UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.BOLD, 25)));
    UIManager.put("TextField.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("Background.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("panel.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("ComboBox.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("List.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("MenuBar.font",new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("MenuItem.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("TextArea.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("TextPane.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("EditorPane.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("TitledBorder.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("ToolBar.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("ToolTip.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
    UIManager.put("Tree.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
}

}

