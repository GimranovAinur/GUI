package main.app;


import main.Exceptions.emptyNameException;
import main.Repo.CategoryRepo;
import main.entities.Category;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class CategoryForm {


    private JPanel panel;
    private JLabel lName;
    private JTextField name;

    public JPanel getJPanel(){
        panel = new JPanel(new GridBagLayout());

        lName = new JLabel("Name",JLabel.LEFT);
        name = new JTextField("",45);

        panel.add(lName,App.labelConstraints());
        panel.add(name, App.textFieldConstraints());


        JButton add = new JButton("ADD");
        panel.add(add);


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                try {
                    CategoryRepo.add(new Category(n));
                    JOptionPane.showMessageDialog(panel,
                            "Category was successfully added!");
                    name.setText("");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (emptyNameException e1) {
                    JOptionPane.showMessageDialog(panel,
                            e1.getMessage(),"Invalid input",JOptionPane.WARNING_MESSAGE);
                    name.setText("");
                }
            }
        });
        panel.setBorder(new TitledBorder("Category"));

        return panel;
    }
}
