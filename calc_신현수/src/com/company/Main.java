package com.company;

import java.util.Scanner;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Main implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        String s = Calc.label.getText();
        double result = Calculator(s);
        Calc.label.setFont(new Font("맑은 고딕", Font.BOLD, 40));

        if(result < 0) {
            Calc.info.setText("양수 범위 계산 가능");
            Calc.label.setText(Double.toString(result));
            Calc.pos_neg = 1;
        }
        else if (result >= 10000000) {
            Calc.info.setText("10,000,000 미만의 범위만 계산가능");
            Calc.label.setText("0.0");
        }
        else {
            Calc.label.setText(Double.toString(result));
        }

    }

    public double Calculator(String ss) {
        int i;
        double ans;
        Calc.check = 0;
        ArrayList<Double> v = new ArrayList<Double>();
        ArrayList<String> op = new ArrayList<String>();
        op.add(null);
        String str = new String("");
        for (i = 0; i < ss.length(); i++) {
            Character c = ss.charAt(i);
            String s = Character.toString(c);

            if(Character.isDigit(c)) {
                str += Character.toString(c);
                if(i == ss.length()-1) {
                    v.add(Double.parseDouble(str));
                }
            }
            else if (s.equals(".")) str += s;
            else {
                v.add(Double.parseDouble(str));
                op.add(Character.toString(c));
                str = "";
            }
        }

        for(i = 0; i < v.size(); i++) {
            if(v.get(i) >= 1000000){
                Calc.check =1;
                Calc.info.setText("10000000 미만의 숫자끼지만 계산 가능");
                break;
            }
        }

        if(Calc.check == 0) {
            for(i = 1; i< v.size(); i++) {
                String s = op.get(i);
                double tmp;

                if(s.equals("x")) {
                    tmp = v.get(i-1) * v.get(i);
                    op.remove(i);
                    v.remove(i);
                    v.remove(i-1);
                    v.add(i-1, tmp);
                    i--;
                }
                else if(s.equals("/")) {
                    tmp = v.get(i-1) / v.get(i);
                    op.remove(i);
                    v.remove(i);
                    v.remove(i-1);
                    v.add(i-1, tmp);
                    i--;
                }
            }
            ans = v.get(0);
            for(i = 1; i < v.size(); i++){
                String s = op.get(i);
                double n = v.get(i);

                if(s.compareTo("+") == 0) {
                    ans = ans + n;
                }
                else if(s.compareTo("-") == 0) {
                    ans = ans - n;
                }
            }
            return ans;
        }
        return 0;
    }

    public static void main(String[] args) {
        new Calc();
    }

}