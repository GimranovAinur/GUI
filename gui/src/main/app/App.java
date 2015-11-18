package main.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class App extends JFrame {

    public final static String TITLE = "Swing Form Example";
    private JFrame frame;
    private JPanel mainPanel;
    private JTabbedPane tp;

    public void createGui() {
        frame = new JFrame(App.TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(400,500));
        frame.setSize(400,500);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(new KeyEventDispatcher() {
                    @Override
                    public boolean dispatchKeyEvent(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                            System.exit(0);
                        }
                        return false;
                    }
                });

        mainPanel = new JPanel(new GridBagLayout());
        tp = new JTabbedPane();
        tp.add("products",new ProductForm().getJPanel());
        tp.add("categories", new CategoryForm().getJPanel());
        tp.add("product list", new ProductList().getJPanel());

        mainPanel.add(tp,this.tablePaneConstraint());
        frame.add(mainPanel);
        frame.setVisible(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
    }


    public static GridBagConstraints labelConstraints(){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,3,20,3);
        c.anchor = GridBagConstraints.BASELINE_TRAILING;
        c.weightx = 0.0;
        return c;
    }

    public static GridBagConstraints textFieldConstraints(){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,3,25,3);
        c.anchor = GridBagConstraints.BASELINE;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        return c;
    }

    public static GridBagConstraints comboBoxConstraints(){
        GridBagConstraints c = textFieldConstraints();
        c.anchor = GridBagConstraints.BASELINE;
        return c;
    }

    public static GridBagConstraints tablePaneConstraint(){
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(30,30,30,30);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weighty = 1;
        return c;
    }
}