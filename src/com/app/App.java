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
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;


import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import static java.awt.Font.createFont;


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

    static String filenameA = null;
    static String filenameB = null;


    public static void main(String[] args) throws IOException, FontFormatException {

        new splash();
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

        /**
         * set and get applog text file in user home ... for logs generation
         */
        File logfile = new File(System.getProperty("user.home")+"/parkwellMain/applog.txt");

        PatternLayout layout = new PatternLayout();
        String conversionPattern = " %-7p %d [%t] %c %x - %m%n ";
        layout.setConversionPattern(conversionPattern);

        // creates console appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();

        // creates file appender for logs
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(String.valueOf(logfile));
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
                logger.warn("\n method call");
                FileReadActionPerformed(e);
            }

            private void FileReadActionPerformed(ActionEvent evt) {
                System.out.println("Select a file"); logger.warn("\n Select a file ");

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(600, 550));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text Files(*.txt)", "txt");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System
                        .getProperty("user.home")));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    FileA.clear();
                    File selectedFile = fileChooser.getSelectedFile();
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader(selectedFile));
                        StringBuilder sb = new StringBuilder();
                        //    System.out.print(sb);
                        String line = br.readLine();
                        while (line != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            line = br.readLine();
                            // System.out.print(line);
                            //null empty not added
                            if(line != null) {
                                if(!line.equals(" ")) {
                                    String lineAfterTrim = line.replaceAll("\\s", ""); // using built in method
                                    FileA.addAll(Collections.singleton(lineAfterTrim));
                                    System.out.println(lineAfterTrim);
                                    System.out.println(FileA);

                                }
                            }
                        }
                        if(FileA.isEmpty()) {
                            System.out.println("File One is empty"); logger.warn("\n File is empty.Please upload a valid file");
                            JOptionPane.showMessageDialog(panel, "File is empty.Please upload a valid file", "Info", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        String pathA = String.valueOf(selectedFile);
                        System.out.println(pathA.length() + "length here");
                        for(int x= 0 ; x <= pathA.length(); x++){
                            if(x > 47){
                                labelpathA.setText(pathA.substring(0,47)+"....");
                            }
                            else{
                                labelpathA.setText(String.valueOf(pathA));
                            }
                        }
                        filenameA = selectedFile.getName();
                        Btnreset.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            logger.warn(JPanel.class.getName());
                            Logger.getLogger(JPanel.class.getName()).log(Level.ALL, null, ex);
                        }
                    }
                }
//                if(!FileA.isEmpty()) {
//                    Btnfile1.setEnabled(false);
//                }
            }
        });

        /**
         * btnfile2 perform an action listener
         * on click of file2 Button
         */

        Btnfile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("method call"); logger.warn("\n 2nd method call");
                FileReadActionPerformed(e);
            }

            private void FileReadActionPerformed(ActionEvent evt) {
                System.out.println("select 2nd file"); logger.warn("\n Select 2nd file ");

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setPreferredSize(new Dimension(600, 550));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Text Files(*.txt)", "txt");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File(System
                        .getProperty("user.home")));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    FileB.clear();
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
                                if(!line.equals(" ")) {
                                    String lineAfterTrim = line.replaceAll("\\s", ""); // using built in method
                                    FileB.addAll(Collections.singleton(lineAfterTrim));
                                    System.out.println(lineAfterTrim);
                                    System.out.println(FileB);
                                }
                            }
                        }
                        if(FileB.isEmpty()) {
                            System.out.println(" File is empty.Please upload a valid file "); logger.warn("\n File is empty.Please upload a valid file ");
                            JOptionPane.showMessageDialog(panel, "File is empty.Please upload a valid file", "Info", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        String pathB = String.valueOf(selectedFile);
                        System.out.println(pathB.length() + "length here");
                        for(int x= 0 ; x <= pathB.length(); x++){
                            if(x > 47){
                                labelpathB.setText(pathB.substring(0,47)+"....");
                            }
                            else{
                                labelpathB.setText(String.valueOf(pathB));
                            }
                        }
                        filenameB = selectedFile.getName();
                        Btnreset.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(JPanel.class.getName()).log(Level.ALL, null, ex);
                            logger.warn("\n Select Both Files");
                        }
                    }
                }
//                if(!FileB.isEmpty()) {
//                    Btnfile2.setEnabled(false);
//                }
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
                String timestamp = new SimpleDateFormat("dd.MM.YYYY_HH.mm.ss", Locale.US).format(new Date());
                System.out.print("\n here" + timestamp);
                String times = String.valueOf(timestamp);
                /**
                 * special Character checking method called here
                 */
                helper.checkingforSpecialCharacter();

                if (FileA.isEmpty() && FileB.isEmpty()) {
                    System.out.print("Please upload both files for comparison \n");
                    logger.warn("\nPlease upload both files for comparison");
                    JOptionPane.showMessageDialog(panel, "Please upload both files for comparison", "Upload files", JOptionPane.INFORMATION_MESSAGE);
                    helper.reset();    logger.warn("\n Reset");
                    System.out.print("\t" + FileA + "\t" + FileB);
                    System.out.print("Reset");
                    return;
                }
                if (FileA.isEmpty()) {
                    System.out.print("Select file card number for billing \n");
                    logger.warn("\nSelect file card number for billing ");
                    JOptionPane.showMessageDialog(panel, "Select file card number for billing", "Select file", JOptionPane.INFORMATION_MESSAGE);
                    System.out.print("\t" + FileA + "\t" + FileB);
                    return;
                }
                if (FileB.isEmpty()) {
                    System.out.print("Select file access card list\n");
                    logger.warn("\n Select file access card list ");
                    JOptionPane.showMessageDialog(panel, "Select file access card list", "Select file", JOptionPane.INFORMATION_MESSAGE);
                    System.out.print("\t" + FileA + "\t" + FileB);
                    return;
                }
                // To find intersection
                Set<String> intersection1 = new HashSet<String>(FileA);
                intersection1.removeAll(FileB);
                System.out.print("\nIntersection of the two Set a-b");
                System.out.println("\n" + intersection1);
                // To find intersection
                Set<String> intersection2 = new HashSet<String>(FileB);
                intersection2.removeAll(FileA);
                System.out.print("\nIntersection of the two Set b-a");
                System.out.println("\n" + intersection2);

                /***
                 *
                 * removing empty value from
                 * intersectionOne and intersectionTwo
                 *
                 */
                intersection1.removeIf(x -> x.isEmpty());
                intersection2.removeIf(x -> x.isEmpty());
                System.out.print("\n+1+\n" + intersection1 + "\n+2+\n" + intersection2);

                /**
                 *
                 *   show dialog box for same file name..
                 */
                if(filenameA.equals(filenameB)){
                    System.out.print("\nSource and target files are same.Please upload different files for comparison ");
                    logger.warn("\nSource and target files are same.Please upload different files for comparison");
                    JOptionPane.showMessageDialog(panel, "Source and target files are same.Please upload \ndifferent files for comparison", "Same Files",  JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                /**
                 *
                 *   show dialog box
                 */
//                if(FileA.c){
//                    System.out.print("\n can not create pdf ");
//                    logger.warn("\n can not create pdf");
//                    JOptionPane.showMessageDialog(panel, "\n can not create pdf", "INFO",  JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
                /**
                 * show dialog box when
                 *
                 */
                JOptionPane.showMessageDialog(panel, "Please specify location where you want to save PDF", "Save pdf", JOptionPane.INFORMATION_MESSAGE);
                /**
                 * Generating PDF
                 *
                 */

                /**
                 * get dynamic path for download PDF
                 */
                String path = "";
                JFileChooser j = new JFileChooser();
                /**
                 * setting height and width of file chooser dialog box
                 */
                j.setPreferredSize(new Dimension(600, 550));
                /**
                 * go to home directory when click on downlaad
                 */
                j.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = j.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    Btnreset.setEnabled(false);
                    path = j.getSelectedFile().getPath();
                    /**
                     *   Creating
                     *   PDF for After Comparison FileA-FileB
                     */
                    Document document = new Document();
                    try {

                        String date = new SimpleDateFormat("dd/MM/YYYY", Locale.US).format(new Date());

                        String getPath = path + ".pdf";
                        String setPath = path + "_" + times + ".pdf";
                        System.out.print(setPath + "   set path  \n");

                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(setPath));
                        document.open();
                        Font fontStyle_Bold = FontFactory.getFont("Montserrat", 10f, Font.BOLD);
                        document.add(new Paragraph("Cards that are Active in Card Access, but are not being Billed in Paris"+
                                "\t"+"                             Printed "+ date, fontStyle_Bold));
                        document.add(Chunk.NEWLINE);
                        for (String temp : intersection2) {
                            //if (!temp.equals(",")) {
                            document.add(new Paragraph(String.valueOf(temp)));
                            //}
                        }
                        document.add(Chunk.NEWLINE);
                        document.addHeader("header", "header content");
                        document.add(new Paragraph("Count: " + intersection2.size() , fontStyle_Bold));
                        /**
                         *   Creating
                         *   new Page while generating PDf
                         */
                        document.add(Chunk.NEWLINE);
                        document.add(Chunk.NEWLINE);
                        document.add(Chunk.NEWLINE);
                        Font fontStyle = FontFactory.getFont("Montserrat", 10f, Font.BOLD);
                        document.add(new Paragraph("Cards that are being Billed, but are not Active in Card Access\n", fontStyle));
                        //document.add(new Paragraph("Cards that are Active in Card Access, but are not being Billed in Paris\n",fontStyle_Bold));
                        document.add(Chunk.NEWLINE);
                        for (String temp : intersection1) {
                            //  if (!temp.equals(",")) {
                            document.add(new Paragraph(String.valueOf(temp)));
                            //   }
                        }
                        document.add(Chunk.NEWLINE);
                        document.add(new Paragraph("Count: " + intersection1.size() , fontStyle));
                        document.close();
                        writer.close();

                        /**
                         *  Re-set to empty
                         *
                         */
                        helper.reset();

                        /**
                         *  Show loader or show success message
                         *
                         */

                        new successMsg();
                        new loader();

                    } catch (DocumentException error) {
                        error.printStackTrace();
                    } catch (FileNotFoundException error) {
                        error.printStackTrace();
                    }
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.print("\n cancle");    logger.warn("\n cancle");
                    return;
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

                JLabel messageConf = new JLabel("Are you sure you want to reset all fields ? " );

                if (!(FileA.isEmpty()) || !(FileB.isEmpty())) {
                    int result = JOptionPane.showConfirmDialog(
                            frame, messageConf , "Reset Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        helper.reset();
                        Btnreset.setEnabled(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                    else if (result == JOptionPane.NO_OPTION)
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    return;
                }
            }
        });
    }
}