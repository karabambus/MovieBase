package View.guis;

import Constants.BasicConstants;
import Controller.UserController;
import Model.TableModel.MovieTableModel;

import javax.swing.*;
import java.awt.*;

public class TableFormGUI extends MainForm {

    public TableFormGUI() {
        super("title");
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


        //create movieTable model
        UserController userController = new UserController();
        JTable table = new JTable(userController.createMovieTable());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 520, 400);
        add(scrollPane, BorderLayout.CENTER);
    }
}
