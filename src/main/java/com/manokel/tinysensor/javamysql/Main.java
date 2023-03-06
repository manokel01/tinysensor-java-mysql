package com.manokel.tinysensor.javamysql;

import com.manokel.tinysensor.javamysql.controllerview.*;

import javax.swing.*;
import java.awt.EventQueue;

public class Main {

    private static Menu menu;
    private static SearchUserForm searchUserForm;
    private static InsertUserForm insertUserForm;
    private static UpdateDeleteUserForm updateDeleteUserForm;
    private static LoginPage loginPage;
    private static InsertDBAccountForm insertDBAccount;
    private static SearchDBAccountForm searchDBAccount;

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    loginPage = new LoginPage();
                    loginPage.setLocationRelativeTo(null);
                    loginPage.setVisible(true);

                    menu = new Menu();
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(false);

                    searchUserForm = new SearchUserForm();
                    searchUserForm.setLocationRelativeTo(null);
                    searchUserForm.setVisible(false);

                    insertUserForm = new InsertUserForm();
                    insertUserForm.setLocationRelativeTo(null);
                    insertUserForm.setVisible(false);

                    updateDeleteUserForm = new UpdateDeleteUserForm();
                    updateDeleteUserForm.setLocationRelativeTo(null);
                    updateDeleteUserForm.setVisible(false);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Menu getMenu() {
        return menu;
    }

    public static SearchUserForm getSearchUserForm() {
        return searchUserForm;
    }

    public static InsertUserForm getInsertUserForm() {
        return insertUserForm;
    }

    public static UpdateDeleteUserForm getUpdateDeleteUserForm() {
        return updateDeleteUserForm;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static InsertDBAccountForm getInsertDBAccountForm() {
        return insertDBAccount;
    }

    public static SearchDBAccountForm getSearchDBAccountForm() {
        return searchDBAccount;
    }

}
