package main;


import main.app.App;
import javax.swing.*;

public class test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().createGui();
            }
        });
    }
}