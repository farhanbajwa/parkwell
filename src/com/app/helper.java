package com.app;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/***
 *  The Application that simply compare two files
 * provide the standard output.
 *
 * @author  Mahnoor
 * @version 1.0
 * @since   2022-04-20
 *
 * */
public interface helper {
    /**
     * this pannel used for display the
     * any message or any information to user
     */
    final JPanel panelError = new JPanel();


    /**
     * Set used here for hold the data of files
     * FileA and FileB both separately hold the
     * files.
     */
    static Set<String> FileA = new HashSet<String>();
    static Set<String> FileB = new HashSet<String>();

    static JButton Btnfile1 = new JButton();
    static JButton Btnfile2 = new JButton();
    static JButton Btndownload = new JButton();
    static JButton Btnreset = new JButton();

    static JFrame frame = new JFrame("Upload Files");
    static JPanel panel = new JPanel();

    /**
     * creates a custom logger and log messages
     * in file applog.txt
     */
    public static Logger logger = Logger.getLogger(App.class);

    /**
     * method for special character checking
     */
    public static void checkingforSpecialCharacter() {
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        System.out.print("A"+FileA);
        System.out.print("\n");
        System.out.print("B"+FileB);

        java.util.List<String> listA = new ArrayList<String>(FileA);
        for(int i = 0 ; i < listA.size() ; i++){
            if(specialCharactersString.contains(listA.get(i))) {
                System.out.print("\n File 1 contains special character \""  + listA.get(i) + "\" \n");
                logger.warn("\n File 1 contains special character \""  + listA.get(i) + "\" ");
            //    InfoMsgForSpecialCh(listA.get(i));
                System.out.print("\t"+FileA+"\t"+FileB);
                System.out.print("Reset");
            //        return;
            }
        }
        List<String> listB = new ArrayList<String>(FileB);
        for(int i = 0 ; i < listB.size() ; i++){
            if(specialCharactersString.contains(listB.get(i))) {
                System.out.print("\n File 2 contains special character \""  + listB.get(i) + "\" \n");
                logger.warn("\n File 1 contains special character \""  + listB.get(i) + "\" ");
             //   InfoMsgForSpecialCh(listB.get(i));
                System.out.print("\t"+FileA+"\t"+FileB);
                System.out.print("Reset");
              //    return;
            }
        }

    }
                   /**
                     * all components ui styling
                     * grid setting and insertion
                     */
    static void UiStyling(){

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel label1 = new JLabel("Upload File One"); label1.setHorizontalAlignment(JLabel.CENTER);
        JLabel label2 = new JLabel("Upload File Two");label2.setHorizontalAlignment(JLabel.CENTER);
        JLabel label3 = new JLabel("Get your File");label3.setHorizontalAlignment(JLabel.CENTER);

        Btnfile1.setText("File 1");Btnfile1.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
        Btnfile2.setText("File 2");Btnfile2.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
        Btndownload.setText("Download");Btndownload.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
        Btnreset.setText("Reset");Btnreset.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));

        /**
         * setting lable buttons and
         * heigth and width using grid
         */
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 70;
        panel.add(label1,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets =  new Insets(17,17,20,17);
        panel.add(Btnfile1,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(label2,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 3;
        panel.add(Btnfile2,gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(label3,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 5;
        panel.add(Btndownload,gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(Btnreset,gbc);

        frame.add(panel);

        frame.setSize(900,900);
        panel.setSize(1000,1000);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
                   /**
                   *  Show Special Character Message for frontend
                   *  clear files enable btn's
                   */
    static void InfoMsgForSpecialCh(String str) {
         JOptionPane.showMessageDialog(panel, "\n File 1 contains special character \""  + str + "\" ", "INFO", JOptionPane.INFORMATION_MESSAGE);
                        FileA.clear();
                        FileB.clear();
                        Btnfile1.setEnabled(true);
                        Btnfile2.setEnabled(true);
    }
                    /**
                    * Reset All files
                    */
    public static void reset(){
        FileA.clear();
        FileB.clear();
        Btnfile1.setEnabled(true);
        Btnfile2.setEnabled(true);
        System.out.print("\t"+FileA+"\t"+FileB);
        System.out.print("Reset");
    }
                    /**
                    *  Same font size and font family
                    */
    public static void font(){
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
