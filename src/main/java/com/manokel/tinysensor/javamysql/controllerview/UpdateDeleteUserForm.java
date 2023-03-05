package com.manokel.tinysensor.javamysql.controllerview;

import com.manokel.tinysensor.javamysql.Main;
import com.manokel.tinysensor.javamysql.dao.IUserDAO;
import com.manokel.tinysensor.javamysql.dao.UserDAOImpl;
import com.manokel.tinysensor.javamysql.dao.exceptions.DAOException;
import com.manokel.tinysensor.javamysql.dto.UserDTO;
import com.manokel.tinysensor.javamysql.service.IUserService;
import com.manokel.tinysensor.javamysql.service.UserServiceImpl;
import com.manokel.tinysensor.javamysql.model.User;
import com.manokel.tinysensor.javamysql.service.exceptions.UserNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

public class UpdateDeleteUserForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUserId;
    private JTextField txtLastname;
    private JTextField txtFirstname;
    private JTextField txtEmail;
    private JTextField txtPostcode;
    private JSeparator separator;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnClose;
    private JButton btnFirstRecord;
    private JButton btnPrevRecord;
    private JButton btnNextRecord;
    private JButton btnLastRecord;

    private IUserDAO userDAO = new UserDAOImpl();
    private IUserService userService = new UserServiceImpl(userDAO);
    // for the buttons that pivot previous-next entry from result list
    private int listPosition;
    private int listSize;
    List<User> users;

    public UpdateDeleteUserForm() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    users = userService.getUsersByLastname(Main.getSearchUserForm().getInputLastname());

                    listPosition = 0;
                    listSize = users.size();

                    if (listSize == 0) {
                        txtUserId.setText("");
                        txtLastname.setText("");
                        txtFirstname.setText("");
                        txtEmail.setText("");
                        txtPostcode.setText("");
                        return;
                    }

                    txtUserId.setText(Integer.toString(users.get(listPosition).getUserId()));
                    txtLastname.setText(users.get(listPosition).getLastname());
                    txtFirstname.setText(users.get(listPosition).getFirstname());
                    txtEmail.setText(users.get(listPosition).getEmail());
                    txtPostcode.setText(users.get(listPosition).getPostcode());
                } catch (DAOException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message,
                            "Error in fetching users operation", JOptionPane.ERROR_MESSAGE);
                }
            }});

        //setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateDeleteUserForm.class.getResource("/resources/eduv2.png")));
        setTitle("Update / Delete User");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 452, 378);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setHorizontalAlignment(SwingConstants.TRAILING);
        lblId.setBounds(37, 29, 61, 16);
        contentPane.add(lblId);

        JLabel lblLastname = new JLabel("Lastname");
        lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblLastname.setBounds(37, 67, 61, 16);
        contentPane.add(lblLastname);

        JLabel lblFirstname = new JLabel("Firstname");
        lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
        lblFirstname.setBounds(24, 105, 74, 16);
        contentPane.add(lblFirstname);

        txtUserId = new JTextField();
        txtUserId.setEditable(false);
        txtUserId.setBounds(115, 23, 61, 26);
        contentPane.add(txtUserId);
        txtUserId.setColumns(10);

        txtLastname = new JTextField();
        txtLastname.setBounds(115, 61, 130, 26);
        contentPane.add(txtLastname);
        txtLastname.setColumns(50);

        txtFirstname = new JTextField();
        txtFirstname.setBounds(115, 99, 130, 26);
        contentPane.add(txtFirstname);
        txtFirstname.setColumns(50);

        separator = new JSeparator();
        separator.setBackground(new Color(0, 0, 0));
        separator.setForeground(new Color(0, 0, 0));
        separator.setBounds(35, 170, 376, -8);
        contentPane.add(separator);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int response;
                    String idStr = txtUserId.getText();
                    int id;

                    if (idStr.equals("")) return;
                    id = Integer.parseInt(txtUserId.getText());

                    response = JOptionPane.showConfirmDialog(null,
                            "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        userService.deleteUser(id);
                        JOptionPane.showMessageDialog(null, "User was deleted successfully.",
                                "DELETE USER", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (DAOException | UserNotFoundException e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnDelete.setBounds(29, 288, 117, 29);
        contentPane.add(btnDelete);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = txtUserId.getText().trim();
                String inputLastname = txtLastname.getText().trim();
                String inputFirstname = txtFirstname.getText().trim();
                String inputEmail = txtEmail.getText().trim();
                String inputPostcode = txtPostcode.getText().trim();

                // Validations
                if (inputLastname.equals("") || inputFirstname.equals("")
                        || inputEmail.equals("") || inputPostcode.equals("")) {
                    JOptionPane.showMessageDialog(null,
                            "Not all fields where inserted", "INSERT ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setUserId(Integer.parseInt(userId));
                    userDTO.setLastname(inputLastname);
                    userDTO.setFirstname(inputFirstname);
                    userDTO.setEmail(inputEmail);
                    userDTO.setPostcode(inputPostcode);

                    // -- Validation of white spaces or unique fields can take place here.
                    // e.g. emailExists() method created in Service layer --
                    User user = userService.updateUser(userDTO);
                    // Instead of Swing viewer. here would be an HTML page or JSON object conversion (the viewer)
                    JOptionPane.showMessageDialog(null,
                            "User " + user.getLastname() + " " + user.getFirstname() + " with USERID: " + userId
                                    + " was updated successfully", "Update Successful!", JOptionPane.PLAIN_MESSAGE);
                } catch (DAOException | UserNotFoundException e1) {
                    // Service Layer Exceptions catch
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnUpdate.setBounds(169, 288, 117, 29);
        contentPane.add(btnUpdate);

        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getSearchUserForm().setEnabled(true);
                Main.getUpdateDeleteUserForm().setVisible(false);
            }
        });
        btnClose.setBounds(309, 288, 117, 29);
        contentPane.add(btnClose);

        btnFirstRecord = new JButton(" |< ");
        btnFirstRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = 0;
                    txtUserId.setText(String.format("%s", users.get(listPosition).getUserId()));
                    txtLastname.setText(users.get(listPosition).getLastname());
                    txtFirstname.setText(users.get(listPosition).getFirstname());
                    txtEmail.setText(users.get(listPosition).getEmail());
                    txtPostcode.setText(users.get(listPosition).getPostcode());
                }

            }
        });
        // btnFirstRecord.setIcon(new ImageIcon(UpdateDeleteUserForm.class.getResource("/resources/First record.png")));
        btnFirstRecord.setBounds(33, 249, 61, 29);
        contentPane.add(btnFirstRecord);

        btnPrevRecord = new JButton(" < ");
        btnPrevRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((listSize > 0) && (listPosition != 0)) {
                    listPosition--;
                    txtUserId.setText(String.format("%s", users.get(listPosition).getUserId()));
                    txtLastname.setText(users.get(listPosition).getLastname());
                    txtFirstname.setText(users.get(listPosition).getFirstname());
                    txtEmail.setText(users.get(listPosition).getEmail());
                    txtPostcode.setText(users.get(listPosition).getPostcode());
                }
            }
        });
        // btnPrevRecord.setIcon(new ImageIcon(UpdateDeleteUserForm.class.getResource("/resources/Previous_record.png")));
        btnPrevRecord.setBounds(103, 247, 61, 29);
        contentPane.add(btnPrevRecord);

        btnNextRecord = new JButton(" > ");
        btnNextRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listPosition <= listSize - 2) {
                    listPosition++;
                    txtUserId.setText(String.format("%s", users.get(listPosition).getUserId()));
                    txtLastname.setText(users.get(listPosition).getLastname());
                    txtFirstname.setText(users.get(listPosition).getFirstname());
                    txtEmail.setText(users.get(listPosition).getEmail());
                    txtPostcode.setText(users.get(listPosition).getPostcode());
                }
            }
        });
        // btnNextRecord.setIcon(new ImageIcon(UpdateDeleteUserForm.class.getResource("/resources/Next_track.png")));
        btnNextRecord.setBounds(172, 247, 61, 29);
        contentPane.add(btnNextRecord);

        btnLastRecord = new JButton(" >| ");
        btnLastRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listSize > 0) {
                    listPosition = listSize - 1;
                    txtUserId.setText(String.format("%s", users.get(listPosition).getUserId()));
                    txtLastname.setText(users.get(listPosition).getLastname());
                    txtFirstname.setText(users.get(listPosition).getFirstname());
                    txtEmail.setText(users.get(listPosition).getEmail());
                    txtPostcode.setText(users.get(listPosition).getPostcode());
                }
            }
        });
        // btnLastRecord.setIcon(new ImageIcon(UpdateDeleteUserForm.class.getResource("/resources/Last_Record.png")));
        btnLastRecord.setBounds(241, 247, 61, 29);
        contentPane.add(btnLastRecord);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
        lblEmail.setBounds(36, 147, 61, 16);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setColumns(50);
        txtEmail.setBounds(114, 141, 130, 26);
        contentPane.add(txtEmail);

        JLabel lblPostcode = new JLabel("Postcode");
        lblPostcode.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPostcode.setBounds(23, 185, 74, 16);
        contentPane.add(lblPostcode);

        txtPostcode = new JTextField();
        txtPostcode.setColumns(50);
        txtPostcode.setBounds(114, 179, 130, 26);
        contentPane.add(txtPostcode);
    }
}
