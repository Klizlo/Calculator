package com.mycompany.calculator;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Klizlo
 * 
 * @version 1.0
 * 
 */
public class CalculatorFrame {    
    //method of displaying calculator frame
    public static void main(String[] args) {
        // TODO code application logic here
        
        //creating the frame
        JFrame calculatorFrame = new JFrame("Calculator");
        //exit from program using close button
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set look and feel
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception exc){
            
        }
        //set starting, minimum and maximum size of calculator frame
        calculatorFrame.setMinimumSize(new Dimension(320, 550));
        calculatorFrame.setMaximumSize(new Dimension(380, 650));
        calculatorFrame.setPreferredSize(new Dimension(340, 600));
        
        //add calculator panel to frame
        calculatorFrame.getContentPane().add(new CalculatorPanel());

        //fit frame to starting size
        calculatorFrame.pack();
        //show frame
        calculatorFrame.setVisible(true);
    }
    
}
