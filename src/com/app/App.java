package com.app;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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

/***
 *  The Application that simply compare two files
 * provide the standard output.
 *
 * @author  Mahnoor
 * @version 1.0
 * @since   2022-04-20
 *
 * */
public class App extends JPanel implements helper {


    public static void main(String[] args){

        /**
         * Set Same Font Size for Lables Buttons Text Area
         * Teaxt Fields And Pannels
         *
         */
       helper.font();

        /**
         * UiStyling contains all lables buttons
         * panel frame
         * and text fields alignment
         */
       helper.UiStyling();

        /**
         *  creates pattern layout
         */
            PatternLayout layout = new PatternLayout();
            String conversionPattern = " %-7p %d [%t] %c %x - %m%n ";
            layout.setConversionPattern(conversionPattern);

            // creates console appender
            ConsoleAppender consoleAppender = new ConsoleAppender();
            consoleAppender.setLayout(layout);
            consoleAppender.activateOptions();

            // creates file appender for logs
            FileAppender fileAppender = new FileAppender();
            fileAppender.setFile("applog.txt");
            fileAppender.setLayout(layout);
            fileAppender.activateOptions();

            // configures the root logger
            Logger rootLogger = Logger.getRootLogger();
            rootLogger.setLevel(Level.DEBUG);
            rootLogger.addAppender(consoleAppender);
            rootLogger.addAppender(fileAppender);


        /**
         * btnfile1 perform an action listener
         * on click of file1 Button
         */
        Btnfile1.addActionListener(new ActionListener() {
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
                                    FileA.addAll(Collections.singleton(line));
                                    System.out.println(line);
                                }
                            }
                        }
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
                if(!FileA.isEmpty()) {
                    Btnfile1.setEnabled(false);
                }
            }
        });

             /**
               * btnfile2 perform an action listener
               * on click of file2 Button
              */

        Btnfile2.addActionListener(new ActionListener() {
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
                                    FileB.addAll(Collections.singleton(line));
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
                if(!FileB.isEmpty()) {
                    Btnfile2.setEnabled(false);
                }
            }
        });

        /**
         * btndownload perform an action listener
         * on click of download Button
         */
        Btndownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * special Character checking method called here
                 */
               helper.checkingforSpecialCharacter();

                if(FileA.isEmpty() || FileB.isEmpty()){
                    System.out.print(" both files should be selected\n");
                    logger.warn("\n both files should be selected ");
                    JOptionPane.showMessageDialog(panel, "\n Both Files should be Selected ", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                    helper.reset();
                    System.out.print("\t"+FileA+"\t"+FileB);
                    System.out.print("Reset");
                    return;
                }
                // To find intersection
                Set<String> intersection1 = new HashSet<String>(FileA);
                intersection1.removeAll(FileB);
                System.out.print("\nIntersection of the two Set a-b");
                System.out.println("\n"+intersection1);
                // To find intersection
                Set<String> intersection2 = new HashSet<String>(FileB);
                intersection2.removeAll(FileA);
                System.out.print("\nIntersection of the two Set b-a");
                System.out.println("\n"+intersection2);
                /**
                 *  Re-set to empty
                 *
                 */
                helper.reset();
                if(intersection1.isEmpty() && intersection2.isEmpty()){
                    System.out.print("\n File 1 and File 2 are Same");
                    logger.warn("\n File 1 and File 2 are Same");
                    JOptionPane.showMessageDialog(panel, "\n BOth Files are Same", "INFO",  JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                /**
                 * Generating PDF
                 *
                 */

                /**
                 * get dynamic path for download PDF
                 */
                String path ="";
                JFileChooser j  = new JFileChooser();
                /**
                 * setting height and width of file chooser dialog box
                 */
                j.setPreferredSize(new Dimension(700, 700));
                /**
                 * go to home directory when click on downlaad
                 */
                j.setCurrentDirectory(new File(System.getProperty("user.home")));
                int x=j.showSaveDialog(frame);
                if(x== JFileChooser.APPROVE_OPTION){
                    path = j.getSelectedFile().getPath();
                }
                /**
                 *   Creating
                 *   PDF one for intersection FileA-FileB
                 */
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
                /**
                 *   Creating
                 *   PDF two for intersection FileB-FileA
                 */
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

        /**
         * Btnreset perform an action listener
         * on click of reset
         */
        Btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helper.reset();
                JOptionPane.showMessageDialog(panelError, " Clear, Select New Files ", "INFO", JOptionPane.INFORMATION_MESSAGE);
               }
        });
    }
}
