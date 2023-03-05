package com.manokel.tinysensor.javamysql.controllerview;

import com.manokel.tinysensor.javamysql.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchDBAccountForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUsername;
    private String inputLastname;

    public SearchDBAccountForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtUsername.setText("");
            }
        });
        setTitle("Αναζήτηση Εκπαιδευτών");
        //setIconImage(Toolkit.getDefaultToolkit().getImage(SearchDBAccountForm.class.getResource("/resources/eduv2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 428, 354);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLastname = new JLabel("Username");
        lblLastname.setForeground(new Color(165, 42, 42));
        lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLastname.setBounds(154, 30, 80, 20);
        contentPane.add(lblLastname);

        txtUsername = new JTextField();
        txtUsername.setBounds(87, 57, 214, 25);
        contentPane.add(txtUsername);
        txtUsername.setColumns(50);

        JButton btnSearch = new JButton("Αναζήτηση");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputLastname = txtUsername.getText().trim();
                Main.getSearchUserForm().setEnabled(false);
                Main.getUpdateDeleteUserForm().setVisible(true);
            }
        });
        btnSearch.setForeground(new Color(0, 0, 255));
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(122, 89, 144, 44);
        contentPane.add(btnSearch);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setForeground(new Color(0, 0, 255));
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClose.setBounds(248, 255, 96, 49);
        contentPane.add(btnClose);

        JButton btnInsert = new JButton("Εισαγωγή");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchDBAccountForm().setEnabled(false);
                Main.getInsertDBAccountForm().setVisible(true);
            }
        });
        btnInsert.setForeground(Color.BLUE);
        btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnInsert.setBounds(122, 179, 144, 44);
        contentPane.add(btnInsert);

        JPanel panel = new JPanel();
        panel.setBounds(44, 15, 300, 134);
        contentPane.add(panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(44, 161, 300, 81);
        contentPane.add(panel_1);
    }

    public String getInputLastname() {
        return inputLastname;
    }
}
