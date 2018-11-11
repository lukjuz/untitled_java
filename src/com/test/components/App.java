package com.test.components;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public JPanel mainPanel;
    private JButton setButton;
    private JTextField columnsTextField;
    private JTextField rowsTextField;
    private GridsCanvas gridCanvas;
    private JPanel optionPanel;
    private JButton toggleStateButton;
    private JTextField linesTextField;
    private JButton checkButton;

    public App() {
        setButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gridCanvas.generate(Integer.parseInt(columnsTextField.getText()),
                            Integer.parseInt(rowsTextField.getText()),
                            Integer.parseInt(linesTextField.getText()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ErrorBox", JOptionPane.ERROR_MESSAGE);
                }
        }
        });
        toggleStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridCanvas.toggleState();
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gridCanvas.checkSolution())
                    JOptionPane.showMessageDialog(null, "Correct", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Try again", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
