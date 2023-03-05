package com.manokel.tinysensor.javamysql.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.mindrot.jbcrypt.BCrypt;

import com.manokel.tinysensor.javamysql.Main;
import com.manokel.tinysensor.javamysql.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class InsertDBAccountForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;


    public InsertDBAccountForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtUsername.setText("");
                txtPassword.setText("");
            }
        });
        setTitle("Εισαγωγή Εκπαιδευτή");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 248, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername.setForeground(new Color(139, 0, 0));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUsername.setBounds(74, 68, 80, 26);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(162, 71, 150, 20);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword.setForeground(new Color(139, 0, 0));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPassword.setBounds(74, 105, 80, 26);
        contentPane.add(lblPassword);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputPassword;
                String inputUsername;
                int n;

                String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
                try (Connection conn = DBUtil.getConnection();
                     PreparedStatement p = conn.prepareStatement(sql)) {

                    inputUsername = txtUsername.getText().trim();
                    inputPassword = String.valueOf(txtPassword.getPassword()).trim();

                    if (inputPassword.equals("") || (inputUsername.equals(""))) {
                        return;
                    }

                    int workload = 12;
                    String salt = BCrypt.gensalt(workload);
                    String hashedPassword = BCrypt.hashpw(inputPassword, salt);

                    p.setString(1, inputUsername);
                    p.setString(2, hashedPassword);

                    n = p.executeUpdate();
                    JOptionPane.showMessageDialog(null, n + " records inserted",
                            "INSERT USER", JOptionPane.PLAIN_MESSAGE);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnInsert.setForeground(new Color(0, 0, 255));
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(175, 188, 96, 43);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchDBAccountForm().setEnabled(true);
                Main.getInsertDBAccountForm().setVisible(false);
            }
        });
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(263, 188, 96, 43);
        contentPane.add(btnClose);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 170, 390, 1);
        contentPane.add(separator);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(162, 108, 150, 20);
        contentPane.add(txtPassword);

        JPanel panel = new JPanel();
        panel.setBounds(37, 36, 322, 123);
        contentPane.add(panel);
    }
}
