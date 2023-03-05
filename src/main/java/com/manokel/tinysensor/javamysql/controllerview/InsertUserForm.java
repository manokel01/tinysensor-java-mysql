package com.manokel.tinysensor.javamysql.controllerview;

import com.manokel.tinysensor.javamysql.Main;
import com.manokel.tinysensor.javamysql.dao.IUserDAO;
import com.manokel.tinysensor.javamysql.dao.UserDAOImpl;
import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.dto.UserDTO;
import com.manokel.tinysensor.javamysql.model.User;
import com.manokel.tinysensor.javamysql.service.IUserService;
import com.manokel.tinysensor.javamysql.service.UserServiceImpl;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertUserForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    private JPanel contentPane;
    private JTextField txtLastname;
    private JTextField txtFirstname;
    private JTextField txtEmail;
    private JTextField txtPostcode;

    public InsertUserForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                txtLastname.setText("");
                txtFirstname.setText("");
                txtEmail.setText("");
                txtPostcode.setText("");
            }
        });
        setTitle("Insert User");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 424);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputLastname = txtLastname.getText().trim();
                String inputFirstname = txtFirstname.getText().trim();
                String inputEmail = txtEmail.getText().trim();
                String inputPostcode = txtPostcode.getText().trim();

                // Validations
                if (inputLastname.equals("") || inputFirstname.equals("")
                        || inputEmail.equals("") || inputPostcode.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Not all fields where inserted", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setLastname(inputLastname);
                        userDTO.setFirstname(inputFirstname);
                        userDTO.setEmail(inputEmail);
                        userDTO.setPostcode(inputPostcode);

                        // -- Validation of white spaces or unique fields can take place here.
                        // e.g. emailExists() method created in Service layer --

                        User user = userService.insertUser(userDTO);
                        // Instead of Swing viewer. here would be an HTML page or JSON object conversion (the viewer)
                        JOptionPane.showMessageDialog(null,
                                "User " + user.getLastname() + " " + user.getFirstname() + " was inserted successfully.",
                                "Insert User Successful.", JOptionPane.PLAIN_MESSAGE);

                    } catch (DAOException e1) {
                        // Service Layer Exceptions catch
                        String message = e1.getMessage();
                        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        btnInsert.setBounds(164, 324, 117, 29);
        contentPane.add(btnInsert);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getInsertUserForm().setVisible(false);
                Main.getSearchUserForm().setEnabled(true);
            }
        });
        btnClose.setBounds(301, 324, 117, 29);
        contentPane.add(btnClose);

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(19, 168, 399, 1);
        contentPane.add(separator);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(60, 51, 330, 240);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblLastname = new JLabel("Lastname");
        lblLastname.setBounds(39, 29, 66, 20);
        panel.add(lblLastname);
        lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblLastname.setForeground(new Color(148, 17, 0));
        lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(39, 133, 66, 20);
        panel.add(lblEmail);
        lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
        lblEmail.setForeground(new Color(148, 17, 0));
        lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

        JLabel lblFirstname = new JLabel("Firstname");
        lblFirstname.setBounds(28, 78, 77, 26);
        panel.add(lblFirstname);
        lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFirstname.setForeground(new Color(148, 17, 0));
        lblFirstname.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

        JLabel lblPostcode = new JLabel("Postcode");
        lblPostcode.setBounds(28, 182, 77, 26);
        panel.add(lblPostcode);
        lblPostcode.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPostcode.setForeground(new Color(148, 17, 0));
        lblPostcode.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

        txtLastname = new JTextField();
        txtLastname.setBounds(131, 27, 150, 26);
        panel.add(txtLastname);
        txtLastname.setColumns(50);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(131, 80, 150, 26);
        panel.add(txtFirstname);
        txtFirstname.setColumns(50);


        txtEmail = new JTextField();
        txtEmail.setBounds(131, 131, 150, 26);
        panel.add(txtEmail);
        txtEmail.setColumns(50);

        txtPostcode = new JTextField();
        txtPostcode.setBounds(131, 186, 150, 26);
        panel.add(txtPostcode);
        txtPostcode.setColumns(50);
    }
}




