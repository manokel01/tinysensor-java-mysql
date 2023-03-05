package com.manokel.tinysensor.javamysql.controllerview;

import com.manokel.tinysensor.javamysql.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;

public class Menu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public Menu() {
        // for window icon
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource("eduv2.png");
        setIconImage(Toolkit.getDefaultToolkit().getImage(url));

        setTitle("tinysensor Database administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 451, 256);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMotto = new JLabel("Please choose an entity:");
        lblMotto.setFont(new Font("Helvetica", Font.PLAIN, 20));
        lblMotto.setBounds(82, 11, 244, 46);
        contentPane.add(lblMotto);

        JSeparator separator = new JSeparator();
        separator.setBounds(40, 68, 345, 1);
        contentPane.add(separator);

        JButton btnUsers = new JButton("");
        btnUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getMenu().setEnabled(false);
                Main.getSearchUserForm().setVisible(true);
            }
        });
        btnUsers.setBounds(27, 94, 32, 32);
        contentPane.add(btnUsers);

        JLabel lblUsers = new JLabel("Users");
        lblUsers.setForeground(new Color(165, 42, 42));
        lblUsers.setFont(new Font("Helvetica", Font.PLAIN, 16));
        lblUsers.setBounds(66, 97, 95, 27);
        contentPane.add(lblUsers);

        JButton btnDevices = new JButton("");
        btnDevices.setBounds(27, 134, 32, 32);
        contentPane.add(btnDevices);

        JLabel lblDevices = new JLabel("Devices");
        lblDevices.setForeground(new Color(165, 42, 42));
        lblDevices.setFont(new Font("Helvetica", Font.PLAIN, 16));
        lblDevices.setBounds(66, 137, 115, 27);
        contentPane.add(lblDevices);
    }
}
