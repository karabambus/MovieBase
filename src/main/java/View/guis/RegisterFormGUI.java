package View.guis;

import Constants.BasicConstants;
import Controller.UserController;
import Exceptions.RegistrationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFormGUI extends CredentialsForm {
    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
    }

    private void addGuiComponents() {
        //Title label
        JLabel registerLabel = new JLabel("Register");
        registerLabel.setBounds(0, 25, 520, 100);
        registerLabel.setForeground(BasicConstants.TEXT_COLOR);
        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(registerLabel);
        //Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(BasicConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 25));
        //username textfield
        JTextField usernameTF = new JTextField();
        usernameTF.setBounds(30, 185, 450, 55);
        usernameTF.setBackground(BasicConstants.SECONDARY_COLOR);
        usernameTF.setForeground(BasicConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 30));

        add(usernameLabel);
        add(usernameTF);
        
        //password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(30, 255, 400, 25);
        passwordLabel.setForeground(BasicConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 30));
        //password textfield
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(30, 285, 450, 55);
        passwordTF.setBackground(BasicConstants.SECONDARY_COLOR);
        passwordTF.setForeground(BasicConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 25));

        add(passwordLabel);
        add(passwordTF);

        //re-enter password
        JLabel rePasswordLabel = new JLabel("Re-enter Password: ");
        rePasswordLabel.setBounds(30, 365, 400, 25);
        rePasswordLabel.setForeground(BasicConstants.TEXT_COLOR);
        rePasswordLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 30));
        //re-enter password textfield
        JPasswordField rePasswordTF = new JPasswordField();
        rePasswordTF.setBounds(30, 395, 450, 55);
        rePasswordTF.setBackground(BasicConstants.SECONDARY_COLOR);
        rePasswordTF.setForeground(BasicConstants.TEXT_COLOR);
        rePasswordTF.setFont(new Font("DejaVu Serif", Font.PLAIN, 25));

        add(rePasswordLabel);
        add(rePasswordTF);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));

        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(BasicConstants.TEXT_COLOR);
        registerButton.setBounds(30,520, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText();

                String password = new String(passwordTF.getPassword());

                String rePassword = new String(rePasswordTF.getPassword());

                if (password.equals(rePassword)) {
                    UserController userController = new UserController();
                    try {
                        if(userController.registerUser(username, password)) {
                            //Switch to login screen
                            RegisterFormGUI.this.dispose();
                            LoginFormGUI loginFormGUI = new LoginFormGUI();
                            loginFormGUI.setVisible(true);
                            //create Success dialog
                            JOptionPane.showMessageDialog(loginFormGUI, "User successfully registered!");
                        } else {
                            JOptionPane.showMessageDialog(RegisterFormGUI.this, "User registration failed!");
                        }
                    } catch (RegistrationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        add(registerButton);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(BasicConstants.TEXT_COLOR);
        loginLabel.setBounds(125,600,250,30);
        // go to login
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI and lunch register
                RegisterFormGUI.this.dispose();

                new LoginFormGUI().setVisible(true);
            }
        });
        add(loginLabel);
    }
}