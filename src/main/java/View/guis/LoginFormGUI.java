package View.guis;

import Constants.BasicConstants;
import Controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFormGUI extends CredentialsForm {

    public LoginFormGUI() {
        super("Login");
        addGuiComponents();
    }

    private void addGuiComponents() {
        //Title label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setForeground(BasicConstants.TEXT_COLOR);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);
        //Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(BasicConstants.TEXT_COLOR);
        usernameLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
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
        passwordLabel.setBounds(30, 305, 400, 25);
        passwordLabel.setForeground(BasicConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
        //password textfield
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(30, 340, 450, 55);
        passwordTF.setBackground(BasicConstants.SECONDARY_COLOR);
        passwordTF.setForeground(BasicConstants.TEXT_COLOR);
        passwordLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 30));

        add(passwordLabel);
        add(passwordTF);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(BasicConstants.TEXT_COLOR);
        loginButton.setBounds(125,520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText();
                String password = passwordTF.getText();

                UserController userController = new UserController();
                if (userController.loginUser(username, password)){
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed");
                }
            }
        });
        add(loginButton);

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(BasicConstants.TEXT_COLOR);
        registerLabel.setBounds(125,600,250,30);
        //Lunch register form
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // dispose of this GUI and lunch register
                LoginFormGUI.this.dispose();
                new RegisterFormGUI().setVisible(true);
            }
        });
        add(registerLabel);


    }
}
