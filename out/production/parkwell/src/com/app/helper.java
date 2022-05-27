package com.app;

import com.sun.javaws.Main;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.itextpdf.text.pdf.BaseFont.createFont;
import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

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
    static JLabel message = new JLabel("reset all fields");

    static JFrame frame = new JFrame("Parkwell - Compare .txt Files");
    static JPanel panel = new JPanel();
    static JPanel panel2 = new JPanel();

    /**
     * creates a custom logger and log messages
     * in file applog.txt
     */
    public static Logger logger = Logger.getLogger(com.app.App.class);

    /**
     * method for special character checking
     */
    public static void checkingforSpecialCharacter() {

        FileA.removeIf(x -> x.isEmpty());
        FileB.removeIf(x -> x.isEmpty());

        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        String matchspecialCharacterswithStrings = "[^0-9 ]";
        System.out.println("File - A" + FileA);
        System.out.print("\n");
        System.out.println("File - B" + FileB);

        java.util.List<String> listA = new ArrayList<String>(FileA);
        System.out.println("A" + listA);
        for (int i = 0; i < listA.size(); i++) {
            if (specialCharactersString.contains(listA.get(i))) {
                System.out.print("\n File 1 (card number for billing) contains special characters or strings \"" + listA.get(i) + "\" \n");
                logger.warn("\n File 1 (card number for billing) contains special character  or strings \"" + listA.get(i) + "\" ");
                //    InfoMsgForSpecialCh(listA.get(i));
                //    return;
            }
            Pattern p =  Pattern.compile(matchspecialCharacterswithStrings);
            Matcher m  = p.matcher(listA.get(i));
            if (m.find()) {
                System.out.print("\n File 1 (card number for billing) finds special characters or strings \"" + listA.get(i) + "\" \n");
                logger.warn("\n File 1 (card number for billing) contains special characters or strings \"" + listA.get(i) + "\" ");
            }


        }
        List<String> listB = new ArrayList<String>(FileB);
        System.out.println("B" + listB);
        for (int i = 0; i < listB.size(); i++) {
            if (specialCharactersString.contains(listB.get(i))) {
                System.out.print("\n File 2 (Access Card List) contains special character \"" + listB.get(i) + "\" \n");
                logger.warn("\n File 2 (Access Card List) contains special character \"" + listB.get(i) + "\" ");
                // InfoMsgForSpecialCh(listB.get(i));
                //   return;
            }
            Pattern p =  Pattern.compile(matchspecialCharacterswithStrings);
            Matcher m =  p.matcher(listB.get(i));
            if (m.find()) {
                System.out.print("\n File 2 (Access Card List) finds special characters \"" + listB.get(i) + "\" \n");
                logger.warn("\n File 2 (Access Card List) contains special character \"" + listB.get(i) + "\" ");
            }
        }
    }

    /**
     * all components ui styling
     * grid setting and insertion
     */
    static void UiStyling() throws IOException, FontFormatException {

        Font  f = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.home")+"/parkwell/Montserrat-Bold.ttf"));
        Font font = f.deriveFont(Font.PLAIN , 25f);

        /**
         * remove the focus border from buttons
         */
        Btnreset.setFocusPainted(false);
        Btnfile1.setFocusPainted(false);
        Btnfile2.setFocusPainted(false);
        Btndownload.setFocusPainted(false);

        //panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        // panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // JLabel label1 = new JLabel("Card Number for Billing");
        JLabel head = new JLabel("Parkwell File Comparison Utility");
      // head.setFont(new Font("Montserrat", Font.BOLD, 45));
        Font headfont = f.deriveFont(Font.BOLD , 45f);
        head.setFont(headfont);

        // head.setBounds(200,400,50,50);


        JLabel label1 = new JLabel("Card Number for Billing");
        label1.setFont(font);
        JLabel label2 = new JLabel("Access Card List ");
        label2.setFont(font);
        JLabel label3 = new JLabel("Get your PDF File ");
        label3.setFont(font);


        message.setText("Reset Files");
        Font resetfont = f.deriveFont(Font.BOLD , 20f);
        message.setFont(resetfont);
        message.setForeground(new Color(211, 211, 211));

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        head.setForeground(Color.YELLOW);

        labelpathA.setHorizontalAlignment(JLabel.LEFT);
        labelpathB.setHorizontalAlignment(JLabel.LEFT);
        head.setHorizontalAlignment(JLabel.CENTER);

        labelpathA.setFont(new Font("Montserrat", Font.PLAIN, 19));
        labelpathB.setFont(new Font("Montserrat", Font.PLAIN, 19));

        panel.setBackground(new Color(95, 158, 160));
        panel2.setBackground(new Color(95, 158, 160));
        labelpathA.setForeground(Color.yellow);
        labelpathB.setForeground(Color.yellow);

        Font  regularfont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.home")+"/parkwell/Montserrat-Regular.ttf"));
        Font btnfont = regularfont.deriveFont(Font.BOLD , 25f);
        Btnfile1.setText("Browse...");
        Btnfile1.setFont(btnfont);
        Btnfile2.setText("Browse...");
        Btnfile2.setFont(btnfont);
        Btndownload.setText("Compare");
        Btndownload.setFont(btnfont);
        Btnreset.setText("   Reset   ");
        Btnreset.setFont(btnfont);


        /**
         * setting lable buttons and
         * heigth and width using grid
         */
        gbc.ipadx = 90;

        gbc.fill = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        // gbc.weightx = 130;
        gbc.insets = new Insets(20, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        panel2.add(head,gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 30, 10, 35);
        gbc.anchor = GridBagConstraints.WEST;
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
        gbc.fill = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = new Insets(10, 30, 10, 35);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label3, gbc);
        gbc.fill = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(Btndownload, gbc);
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.insets = new Insets(30, 30, 10, 35);
        panel.add(Btnreset, gbc);Btnreset.setEnabled(false);
        frame.add(panel2);
        frame.add(panel);
        frame.pack();
        frame.setSize(890, 600);
        panel.setSize(890, 600);
        panel2.setSize(890,100);
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
    public static void font() throws IOException, FontFormatException {

        Font  f = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.home")+"/parkwell/Montserrat-Regular.ttf"));
  //      Font f = volution.deriveFont(Font.BOLD , 48f);
        UIManager.put("Label.font", new FontUIResource(f.deriveFont(Font.BOLD,25f)));

        //  UIManager.put("Label.font", Font.createFont(Font.TRUETYPE_FONT , new File("src/com/app/Montserrat-Regular.ttf")));
        //UIManager.put("Label.font", new FontUIResource(new Font("Montserrat", Font.BOLD, 25)));

        UIManager.put("Button.font",new FontUIResource(f.deriveFont(Font.BOLD,25f)));
        UIManager.put("TextField.font", new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("Background.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("panel.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("Button.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("ComboBox.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("List.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("MenuBar.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("MenuItem.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("TextArea.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("TextPane.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("EditorPane.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("TitledBorder.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("ToolBar.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("ToolTip.font", new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("Tree.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
        UIManager.put("JOptionPane.font",  new FontUIResource(f.deriveFont(Font.PLAIN,25f)));
    }
}