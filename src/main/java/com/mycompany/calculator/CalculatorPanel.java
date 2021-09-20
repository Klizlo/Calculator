/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


/**
 *
 * @author Klizlo
 * 
 * @version 1.0
 * 
 */
public class CalculatorPanel extends JPanel implements ActionListener{
    
    //create two text fields that contain mathematical operations and results
    JTextField mathOperation;
    JTextField result;
    
    //panel containing results
    JPanel resultPanel;
    //panel containing buttons
    JPanel buttonsPanel;
    
    //create operators and operands
    String operand1, operand2, operator;
    //variables check that equal button was clicked(isEqualButtonPressed)
    //or percent/squared/squareRoot/reciprocal button was cklicked(isAdvancedOperatorPressed)
    static boolean isEqualButtonPressed, isAdvancedOperatorPressed = false;
    
    CalculatorPanel(){
        //assigne initial values to operators
        operand1 = "0";
        operand2 = operator = "";
        
        //set layout for whole calculator panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        //create panel for results and its look
        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setMinimumSize(new Dimension(320, 110));
        resultPanel.setMaximumSize(new Dimension(380, 110));
        resultPanel.setPreferredSize(new Dimension(340, 110));
        //init text field 
        setTextFields();
        //add mathoperation and result to resultPanel
        resultPanel.add(mathOperation, BorderLayout.PAGE_START);
        resultPanel.add(result, BorderLayout.CENTER);
        
        //create panel contains buttons and its look
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(6, 4, 0, 0));
        //set min, max and prefered size of panel
        buttonsPanel.setMinimumSize(new Dimension(320, 440));
        buttonsPanel.setMaximumSize(new Dimension(380, 540));
        buttonsPanel.setPreferredSize(new Dimension(340, 490));
        //init buttonsPanel
        initbuttons();
        
        //add resultPanel to calculator panel
        add(resultPanel);
        //add buttonsPanel panel to calculator panel
        add(buttonsPanel);
        
    }
    
    private void setTextFields(){
        //method of intializing text fields and their look
        mathOperation = new JTextField("");
        //set text alingment to right in text fields
        mathOperation.setHorizontalAlignment(SwingConstants.RIGHT);
        //set text field to non editable
        mathOperation.setEditable(false);
        //set font size
        mathOperation.setFont(new Font(mathOperation.getFont().getName(), mathOperation.getFont().getStyle(), 15));
        result = new JTextField("0");
        //set the second text field
        result.setHorizontalAlignment(SwingConstants.RIGHT);
        result.setEditable(false);
        result.setFont(new Font(result.getFont().getName(), result.getFont().getStyle(), 26));
    }
    
    private void initbuttons(){
        //method of intializing buttons and their look
        //create all buttons
        JButton[][] buttons = new JButton[6][4];
        
        buttons[0][0] = new JButton("%");
        buttons[0][1] = new JButton("1/x");
        buttons[0][2] = new JButton("x²");
        buttons[0][3] = new JButton("√");
        buttons[1][0] = new JButton("C");
        buttons[1][1] = new JButton("CE");
        buttons[1][2] = new JButton("⌫");
        buttons[1][3] = new JButton("÷");
        buttons[2][0] = new JButton("7");
        buttons[2][1] = new JButton("8");
        buttons[2][2] = new JButton("9");
        buttons[2][3] = new JButton("×");
        buttons[3][0] = new JButton("4");
        buttons[3][1] = new JButton("5");
        buttons[3][2] = new JButton("6");
        buttons[3][3] = new JButton("+");
        buttons[4][0] = new JButton("1");
        buttons[4][1] = new JButton("2");
        buttons[4][2] = new JButton("3");
        buttons[4][3] = new JButton("-");
        buttons[5][0] = new JButton("±");
        buttons[5][1] = new JButton("0");
        buttons[5][2] = new JButton(".");
        buttons[5][3] = new JButton("=");
        
        for(int i=0; i<6; i++)
            for(int j=0; j<4; j++){
                //set buttons' font style 
                buttons[i][j].setFont(new Font(result.getFont().getName(), result.getFont().getStyle(), 18));
                //add buttons to panel
                buttonsPanel.add(buttons[i][j]);
                //add listener to each buttons
                buttons[i][j].addActionListener(this);
            }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        
        //0-9 & "." buttons operation
        if(s.length() == 1 && s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.equals(".") || s.equals(",")){
            
            if(isEqualButtonPressed){
                // if isEqualButtonPressed (it's set after pressing the "=" button) is true then all class' string and labels are reset
                operator = "";
                operand1 = "0";
                operand2 = "";
                mathOperation.setText("");
                isEqualButtonPressed = isAdvancedOperatorPressed = false;
                if(result.getText().equals("Dividing by zero!"))
                    buttonsPanelUnblocked();
            }
            if(isAdvancedOperatorPressed){
                //if isAdvancedOperatorPressed is true, the number resulting from clicking this button is reset
                operand1 = "0";
                if(!operand2.equals(""))
                    mathOperation.setText(operand2+operator);
                else
                    mathOperation.setText("");
                isAdvancedOperatorPressed = false;
            }
            if(operand1.length() < 16){
            //the entered number can only contain 16 digits
                if(s.equals(","))
                    //in some countries it is used , instead of . in number as in Poland
                    s = ".";
                if(operand1.equals("0") && s.charAt(0) >= '1' && s.charAt(0) <= '9')
                    //set first digit that isn't 0
                    operand1 = s;
                else if(operand1.equals("0") && s.equals("0"));
                //prohibit setting more zeros than 1 or 0 at the beginning
                else if(s.equals(".") && operand1.contains(".") || s.equals(",") && operand1.contains("."));
                //prohibit add more dots than 1 in number
                else
                    operand1 += s;

                result.setText(operand1);
            }
            
        }
        else if(s.equals("C")){
            //the button C operation
            //this button removes all operations that were entered
            operand1 = "0";
            operand2 = operator = "";
            
            result.setText(operand1);
            mathOperation.setText("");
        }
        else if(s.equals("CE")){
            //the buton CE operation
            //this button removes whole number that is entered
            operand1 = "0";
            
            result.setText(operand1);
        }
        else if(s.equals("⌫")){
            //the button backspace operation
            //this button removes the last digit from number that is entered
            if(isAdvancedOperatorPressed);
            //when percent/squared/squareRoot/reciprocal button was cklicked, do nothing
            else{
                operand1 = operand1.substring(0, operand1.length()-1);
            
                if(operand1.equals(""))
                    operand1 = "0";
                
                result.setText(operand1);
            }
        }
        else if(s.equals("±")){
            //the button plus or minus operation
            if(!operand1.equals("0")){
                if(!operand1.startsWith("-"))
                    operand1 = "-" + operand1;
                else
                    operand1 = operand1.substring(1);
            
                result.setText(operand1);
            }
        }
        else if(s.equals("+") || s.equals("-") || s.equals("×")|| s.equals("*")  || s.equals("÷")  || s.equals("/")){
            //the simple math operator buttons operation
            if(isEqualButtonPressed){
                operator = "";
                isEqualButtonPressed = isAdvancedOperatorPressed = false;
            }
            if(isAdvancedOperatorPressed)
                isAdvancedOperatorPressed = false;
            if(operand2.equals("")){
                operand2 = operand1;
            }
            if(s.equals("*"))
                s = "×";
            if(s.equals("/"))
                s = "÷";
            else if(!operator.equals(""))
                operand2 = mathOperations();
            
            if(!operand2.equals("Infinity")){
                operator = s;
                operand1 = "0";

                mathOperation.setText(operand2 + operator);
                result.setText("0");
            }else{
                mathOperation.setText(mathOperation.getText()+operand1+"=");
                buttonsPanelBlocked();
            }
        }
        else if(s.equals("√")){
            //the square root button operation
            if(isEqualButtonPressed){
                //if isEqualButtonPressed is True then result of square root is calculated from the obtained result
                operand1 = operand2.equals("")?operand1:operand2;
                operand2 = operator = "";
                isEqualButtonPressed = false;
            }
            if(Double.parseDouble(operand1) < 0){
                //this checks that entered number isn't less than 0
                //if it's true then there is displayed message
                operand1 = "0";
                result.setText("Invalid input");
            }
            else{ 
                if(!operand2.equals(""))
                    mathOperation.setText(operand2+operator+"√("+operand1+")");
                else
                    mathOperation.setText("√("+operand1+")");
                
                operand1 = Double.toString(sqrt(Double.parseDouble(operand1)));
                result.setText(operand1.endsWith(".0")?operand1.substring(0,operand1.length()-2):operand1);
                
                isAdvancedOperatorPressed = true;
            }
            
        }
        else if(s.equals("x²")){
            //the square button operation
            if(isEqualButtonPressed){
                //if isEqualButtonPressed is True then result of square is calculated from the obtained result
                operand1 = operand2.equals("")?operand1:operand2;
                operand2 = operator = "";
                operand2 = "";
                isEqualButtonPressed = false;
            }
            if(!operand2.equals(""))
                mathOperation.setText(operand2+operator+"("+operand1+")²");
            else
                mathOperation.setText("("+operand1+")²");

            operand1 = Double.toString(pow(Double.parseDouble(operand1),2));
            result.setText(operand1.endsWith(".0")?operand1.substring(0,operand1.length()-2):operand1);
            isAdvancedOperatorPressed = true;
            
        }
        else if(s.equals("1/x")){
            //the reciprocal button operation
            if(isEqualButtonPressed){
                //if isEqualButtonPressed is True then result of reciprocal is calculated from the obtained result
                operand1 = operand2.equals("")?operand1:operand2;
                operand2 = "";
                isEqualButtonPressed = false;
            }
            if(!operand2.equals(""))
                mathOperation.setText(operand2+operator+"1/("+operand1+")");
            else
                mathOperation.setText("1/("+operand1+")");

            operand1 = Double.toString(1/Double.parseDouble(operand1));
            if(operand1.equals("Infinity")){
                buttonsPanelBlocked();
            }else{
                result.setText(operand1.endsWith(".0")?operand1.substring(0,operand1.length()-2):operand1);
                isAdvancedOperatorPressed = true;
            }
            
        }
        else if(s.equals("%")){
            //the percent button operation
            if(isEqualButtonPressed){
                //if isEqualButtonPressed is True then result of percent is calculated from the obtained result
                operand1 = operand2.equals("")?operand1:operand2;
                operand2 = "";
                isEqualButtonPressed = false;
            }
            if(!operand2.equals(""))
                mathOperation.setText(operand2+operator+operand1+"%");
            else
                mathOperation.setText(operand1+"%");

            operand1 = Double.toString(Double.parseDouble(operand1)/100);
            result.setText(operand1.endsWith(".0")?operand1.substring(0,operand1.length()-2):operand1);
            isAdvancedOperatorPressed = true;
            
        }
        else if(s.equals("=")){
            //the "=" button operation
            operand1 = operand1.endsWith(".0")?operand1.substring(0,operand1.length()-2):operand1;
            //remove ".0" from number if it's integer
            mathOperation.setText(operand2+operator+(operand1.contains("-")?"("+operand1+")":operand1)+"=");
            
            if(!operand2.equals(""))
                operand2 = mathOperations();
            
            if(!operand2.equals("Infinity")){
                result.setText(operand2.equals("")?operand1:operand2);
                isEqualButtonPressed = true;
            }
            else{
                buttonsPanelBlocked();
            }
            
        }
            
    }
    
    private String mathOperations(){
        //the function where is calculated simple math operations
        //the function returns calculated result in string form
        if(operand1.equals("")){
            return operand2;
        }
        
        Double localResult = 0.0;
        switch(operator){
            case "+" -> localResult = Double.parseDouble(operand2) + Double.parseDouble(operand1);
            case "-" -> localResult = Double.parseDouble(operand2) - Double.parseDouble(operand1);
            case "×" -> localResult = Double.parseDouble(operand2) * Double.parseDouble(operand1);
            case "÷" -> localResult = Double.parseDouble(operand2) / Double.parseDouble(operand1);
                            
        }
        return Double.toString(localResult).endsWith(".0")?Double.toString(localResult).substring(0,Double.toString(localResult).length()-2):Double.toString(localResult);
        
    }
    
    private void buttonsPanelBlocked(){
        //block non numeric buttons when result is infinity
        result.setText("Dividing by zero!");
        //display message about dividing by zero
        isEqualButtonPressed = true;
        operand2 = "";
        
        //block non numeric buttons
        buttonsPanel.getComponent(0).setEnabled(false);  //percent button
        buttonsPanel.getComponent(1).setEnabled(false);  //reciprocal button
        buttonsPanel.getComponent(2).setEnabled(false);  //squared button
        buttonsPanel.getComponent(3).setEnabled(false);  //square root button
        buttonsPanel.getComponent(4).setEnabled(false);  //button C
        buttonsPanel.getComponent(5).setEnabled(false);  //button CE
        buttonsPanel.getComponent(6).setEnabled(false);  //backspace button
        buttonsPanel.getComponent(7).setEnabled(false);  //division button
        buttonsPanel.getComponent(11).setEnabled(false); //multiplication button
        buttonsPanel.getComponent(15).setEnabled(false); //plus button
        buttonsPanel.getComponent(19).setEnabled(false); //minus button
        buttonsPanel.getComponent(20).setEnabled(false); //plus/minus button
        buttonsPanel.getComponent(23).setEnabled(false); //equal button
    }
    
    private void buttonsPanelUnblocked(){
        //unblock non numeric buttons
        
        buttonsPanel.getComponent(0).setEnabled(true);
        buttonsPanel.getComponent(1).setEnabled(true);
        buttonsPanel.getComponent(2).setEnabled(true);
        buttonsPanel.getComponent(3).setEnabled(true);
        buttonsPanel.getComponent(4).setEnabled(true);
        buttonsPanel.getComponent(5).setEnabled(true);
        buttonsPanel.getComponent(6).setEnabled(true);
        buttonsPanel.getComponent(7).setEnabled(true);
        buttonsPanel.getComponent(11).setEnabled(true);
        buttonsPanel.getComponent(15).setEnabled(true);
        buttonsPanel.getComponent(19).setEnabled(true);
        buttonsPanel.getComponent(20).setEnabled(true);
        buttonsPanel.getComponent(23).setEnabled(true);
    }
}
