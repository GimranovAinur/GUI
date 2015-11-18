package main.app;


import main.Exceptions.*;
import main.Repo.CategoryRepo;
import main.Repo.ProdRepo;
import main.entities.Product;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

public class ProductForm {

    private JPanel panel;
    private JLabel lName;
    private JTextField name;
    private JLabel lPrice;
    private JTextField price;
    private JLabel lWeight;
    private JTextField weight;
    private JLabel lMan;
    private JComboBox man;
    private JLabel lCategory;
    private JComboBox categoryBox;
    private JLabel info;
    private JButton add;

    public JPanel getJPanel(){
        panel = new JPanel(new GridBagLayout());

        lName = new JLabel("Name",JLabel.LEFT);
        name = new JTextField("",45);

        panel.add(lName, App.labelConstraints());
        panel.add(name,App.textFieldConstraints());

        lPrice = new JLabel("Price",JLabel.LEFT);
        price = new JTextField("",32);

        panel.add(lPrice,App.labelConstraints());
        panel.add(price,App.textFieldConstraints());


        lWeight = new JLabel("Weight",JLabel.LEFT);
        weight = new JTextField("",10);

        panel.add(lWeight,App.labelConstraints());
        panel.add(weight,App.textFieldConstraints());

        lMan = new JLabel("Manufacturer",JLabel.LEFT);
        man = new JComboBox(Product.getManufacturers());
        man.setEditable(false);

        panel.add(lMan);
        panel.add(man);


        lCategory = new JLabel("Category",JLabel.LEFT);
        String[] names = CategoryRepo.getAllCategoriesNames();
        categoryBox = new JComboBox(Arrays.copyOf(names, names.length));

        panel.add(lCategory,App.labelConstraints());
        panel.add(categoryBox,App.comboBoxConstraints());

        panel.add(new JPanel(), App.tablePaneConstraint());

        add = new JButton("ADD");
        panel.add(add);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                String p = price.getText();
                String w = weight.getText();
                if(p.isEmpty() || w.isEmpty()){
                    p = "0";
                    w = "0";
                }
                String m = (String) man.getSelectedItem();
                String c = (String) categoryBox.getSelectedItem();

                try {
                    ProdRepo.add(new Product(n, Double.parseDouble(p), Double.parseDouble(w), Product.getManufacturer(m), CategoryRepo.getIdByName(c)));
                    JOptionPane.showMessageDialog(panel,
                            "Product was successfully added!");
                    name.setText("");
                    price.setText("");
                    weight.setText("");

                } catch (emptyNameException e1) {
                    JOptionPane.showMessageDialog(panel,
                            e1.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
                    name.setText("");
                } catch (invalidWeightPriceException e1) {
                    JOptionPane.showMessageDialog(panel,
                            e1.getMessage(), "Invalid input", JOptionPane.WARNING_MESSAGE);
                    name.setText("");
                    price.setText("");
                    weight.setText("");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
        panel.setBorder(new TitledBorder("Product"));

        return panel;
    }
}
