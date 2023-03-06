package com.manokel.tinysensor.javamysql.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.manokel.tinysensor.javamysql.Main;
import org.mindrot.jbcrypt.BCrypt;

import com.manokel.tinysensor.javamysql.service.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginPage() {
        setTitle("Database Account Login");
        // setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/resources/eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setForeground(new Color(165, 42, 42));
        lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername.setBounds(64, 32, 110, 36);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblPassword.setForeground(new Color(165, 42, 42));
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setBounds(45, 77, 130, 36);
        contentPane.add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setBounds(184, 37, 162, 27);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(45, 159, 330, 1);
        contentPane.add(separator);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputUsername = txtUsername.getText().trim();
                String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
                String password = System.getenv("TS_ADMIN_PASSWORD");
                String hashedPassword;

                if (inputUsername.equals("") || inputPassword.equals("")) {
                    return;
                }

                if (inputUsername.equals("admin") && (inputPassword.equals(password))) {
                    Main.getLoginPage().setVisible(false);
                    Main.getSearchUserForm().setVisible(true);
                } else {
                    String sql = "SELECT PASSWORD FROM DBACCOUNTS WHERE USERNAME = ?";

                    try (Connection conn = DBUtil.getConnection();
                         PreparedStatement p = conn.prepareStatement(sql)) {

                        p.setString(1, inputUsername);
                        ResultSet rs = p.executeQuery();

                        if (rs.next()) {
                            hashedPassword = rs.getString("PASSWORD");
                        } else {
                            JOptionPane.showMessageDialog(null, "User not found", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (BCrypt.checkpw(inputPassword, hashedPassword)) {
                            Main.getMenu().setVisible(true);
                            Main.getLoginPage().setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error in password", "Login Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnLogin.setForeground(new Color(0, 0, 255));
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLogin.setBounds(211, 188, 135, 54);
        contentPane.add(btnLogin);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(185, 82, 162, 27);
        contentPane.add(txtPassword);
    }
}

