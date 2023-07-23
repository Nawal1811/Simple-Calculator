import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private double num1, num2, result;
    private char operator;

    public calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        displayField = new JTextField(15);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.BOLD, 30));
        displayField.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setPreferredSize(new Dimension(300, 300));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.setBackground(Color.LIGHT_GRAY);
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        char ch = command.charAt(0);

        if (Character.isDigit(ch) || ch == '.') {
            displayField.setText(displayField.getText() + command);
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(displayField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            displayField.setText(String.valueOf(result));
        } else if (command.equals("C")) {
            displayField.setText("");
        } else {
            num1 = Double.parseDouble(displayField.getText());
            operator = ch;
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                calculator calculator = new calculator();
                calculator.setLookAndFeel();
                calculator.setVisible(true);
            }
        });
    }
}
