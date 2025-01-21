package View;

import View.guis.LoginFormGUI;
import View.guis.RegisterFormGUI;
import View.guis.TableFormGUI;

import javax.swing.*;

public class AppLuncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                //run login form GUI
      //          new LoginFormGUI().setVisible(true);
              //  new RegisterFormGUI().setVisible(true);
                    new TableFormGUI().setVisible(true);
            }
        });
    }
}
