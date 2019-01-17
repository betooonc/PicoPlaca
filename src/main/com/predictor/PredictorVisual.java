package com.predictor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredictorVisual extends JFrame{
    private JLabel lblTitle;
    private JLabel lblPlateNum;
    private JLabel lblDate;
    private JLabel lblTime;
    private JTextField txtPlateNum;
    private JTextField txtDate;
    private JTextField txtTime;
    private JButton btnCheck;
    private JPanel container;
    Controller objController = new Controller();

    public PredictorVisual(){
        add(container);
        setTitle("Predictor");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objController.setParameters(txtPlateNum.getText(), txtDate.getText(), txtTime.getText());
            }
        });
    }
}
