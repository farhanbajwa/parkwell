package com.app;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/***
 *  The Application that simply compare two files
 *  provide the standard output.
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

    static JLabel labelpathA = new JLabel();
    static JLabel labelpathB = new JLabel();

    static JFrame frame = new JFrame("Please Upload (.text Files) ");
    static JPanel panel = new JPanel();
    static JPanel panel2 = new JPanel();

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
        System.out.print("A" + FileA);
        System.out.print("\n");
        System.out.print("B" + FileB);

        java.util.List<String> listA = new ArrayList<String>(FileA);
        for (int i = 0; i < listA.size(); i++) {
            if (specialCharactersString.contains(listA.get(i))) {
                System.out.print("\n File 1 contains special character \"" + listA.get(i) + "\" \n");
                logger.warn("\n File 1 contains special character \"" + listA.get(i) + "\" ");
                //    InfoMsgForSpecialCh(listA.get(i));
                System.out.print("\t" + FileA + "\t" + FileB);
                System.out.print("Reset");
                //        return;
            }
        }
        List<String> listB = new ArrayList<String>(FileB);
        for (int i = 0; i < listB.size(); i++) {
            if (specialCharactersString.contains(listB.get(i))) {
                System.out.print("\n File 2 contains special character \"" + listB.get(i) + "\" \n");
                logger.warn("\n File 1 contains special character \"" + listB.get(i) + "\" ");
                //   InfoMsgForSpecialCh(listB.get(i));
                System.out.print("\t" + FileA + "\t" + FileB);
                System.out.print("Reset");
                //    return;
            }
        }
    }

    /**
     * all components ui styling
     * grid setting and insertion
     */
    static void UiStyling() {
        /**
         * remove the focus border from buttons
         */
        Btnreset.setFocusPainted(false);
        Btnfile1.setFocusPainted(false);
        Btnfile2.setFocusPainted(false);
        Btndownload.setFocusPainted(false);

        //panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
       /// panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // JLabel label1 = new JLabel("Card Number for Billing");
        JLabel head = new JLabel("Parkwell File Comparison Utility");
        head.setFont(new Font("Dialog", Font.BOLD, 45));
        head.setBounds(400,150,50,50);
       // JLabel label1 = new JLabel("Card Number for Billing");
        JLabel label1 = new JLabel("Card Number for Billing");
        label1.setFont(new Font("Dialog", Font.BOLD, 25));
       // JLabel label2 = new JLabel("Access List Card Only");
        JLabel label2 = new JLabel("Access List Card Only");
        label2.setFont(new Font("Dialog", Font.BOLD, 25));
        JLabel label3 = new JLabel("    Get your PDF File ");
        label3.setFont(new Font("Dialog", Font.BOLD, 25));

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        head.setForeground(Color.YELLOW);

        labelpathA.setHorizontalAlignment(JLabel.CENTER);
        labelpathB.setHorizontalAlignment(JLabel.CENTER);

        labelpathA.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));
        labelpathB.setFont(new Font("Helvetica Neue", Font.PLAIN, 19));

        panel.setBackground(new Color(95, 158, 160));
        panel2.setBackground(new Color(95, 158, 160));
        labelpathA.setForeground(Color.yellow);
        labelpathB.setForeground(Color.yellow);
        //   labelpathB.setForeground(new Color(0,100,0)); // darkgreen RGB

        Btnfile1.setText("Browse...");
        Btnfile1.setFont(new Font("Helvetica Neue", Font.PLAIN, 23));
        Btnfile2.setText("Browse...");
        Btnfile2.setFont(new Font("Helvetica Neue", Font.PLAIN, 23));
        Btndownload.setText("Compare");
        Btndownload.setFont(new Font("Helvetica Neue", Font.PLAIN, 23));
        Btnreset.setText("   Reset   ");
        Btnreset.setFont(new Font("Helvetica Neue", Font.PLAIN, 23));


        /**
         * setting lable buttons and
         * heigth and width using grid
         */
        gbc.ipadx = 90;
        //gbc.ipady = 10; not add

        gbc.fill = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
       // gbc.weightx = 130;
        gbc.insets = new Insets(20, 10, 10, 35);
        panel2.add(head,gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 30, 10, 35);
        panel.add(label1, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 30, 10, 35);
        panel.add(Btnfile1, gbc);
        gbc.fill = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 4;
        panel.add(labelpathA, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 30, 10, 35);
        panel.add(label2, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 30, 10, 35);
        panel.add(Btnfile2, gbc);
        gbc.fill = GridBagConstraints.EAST;
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.weightx = 4;
        panel.add(labelpathB, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(label3, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(Btndownload, gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.insets = new Insets(30, 30, 10, 35);
        panel.add(Btnreset, gbc);
        frame.add(panel2);
        frame.add(panel);
        frame.pack();
        frame.setSize(990, 600);
        panel.setSize(500, 600);
        panel2.setSize(990,100);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Show Special Character Message for frontend
     * clear files enable btn's
     */
    static void InfoMsgForSpecialCh(String str) {
        JOptionPane.showMessageDialog(panel, "\n File 1 contains special character \"" + str + "\" ", "INFO", JOptionPane.INFORMATION_MESSAGE);
        FileA.clear();
        FileB.clear();
        Btnfile1.setEnabled(true);
        Btnfile2.setEnabled(true);
    }

    /**
     * Reset All files
     */
    public static void reset() {
        FileA.clear();
        FileB.clear();
        Btnfile1.setEnabled(true);
        Btnfile2.setEnabled(true);
        labelpathA.setText("");
        labelpathB.setText("");
        System.out.print("\t" + FileA + "\t" + FileB);
        System.out.print("Reset");
    }

    /**
     * Same font size and font family
     */
    public static void font() {
        UIManager.put("Label.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.BOLD, 25)));
        UIManager.put("TextField.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("Background.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("panel.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("Button.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("ComboBox.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("List.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
        UIManager.put("MenuBar.font", new FontUIResource(new Font("Dialog", Font.PLAIN, 25)));
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

