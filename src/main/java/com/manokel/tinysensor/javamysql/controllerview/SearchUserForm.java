package com.manokel.tinysensor.javamysql.controllerview;

import com.manokel.tinysensor.javamysql.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchUserForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textLastname;
    private String inputLastname;

    public SearchUserForm() {
        // setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().
         //       getContextClassLoader().getResource("eduv2.png")));
        setTitle("Search Teacher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Lastname");
        lblLastname.setForeground(new Color(148, 17, 0));
        lblLastname.setBounds(189, 21, 61, 16);
        contentPane.add(lblLastname);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputLastname = textLastname.getText().trim();
                Main.getSearchUserForm().setEnabled(false);
                Main.getUpdateDeleteUserForm().setVisible(true);
            }
        });
        btnSearch.setForeground(new Color(0, 150, 255));
        btnSearch.setBounds(147, 97, 144, 42);
        contentPane.add(btnSearch);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(true);
                Main.getSearchUserForm().setVisible(false);

            }
        });
        btnClose.setBounds(306, 229, 117, 29);
        contentPane.add(btnClose);

        textLastname = new JTextField();
        textLastname.setBounds(53, 49, 339, 34);
        contentPane.add(textLastname);
        textLastname.setColumns(50);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUserForm().setEnabled(false);
                Main.getInsertUserForm().setVisible(true);
            }
        });
        btnInsert.setForeground(new Color(0, 150, 255));
        btnInsert.setBounds(147, 166, 144, 42);
        contentPane.add(btnInsert);

        JPanel panel = new JPanel();
        panel.setBounds(38, 8, 370, 139);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(38, 161, 370, 56);
        contentPane.add(panel_1);
    }

    public String getInputLastname() {
        return inputLastname;
    }

    public void setInputLastname(String inputLastname) {
        this.inputLastname = inputLastname;
    }
}

