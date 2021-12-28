package com.company;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Calc extends JFrame{
    static JLabel label;
    static JLabel info;
    static int pos_neg = 0; //음수 양수
    static int check = 0; // 숫자 체크

    public Calc() {
        setTitle("Java 간단 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout(5,5));
        c.setBackground(Color.BLACK);

        NorthPanel NP = new NorthPanel();
        c.add(NP, BorderLayout.EAST);

        CenterPanel CP = new CenterPanel();
        c.add(CP, BorderLayout.SOUTH);

        setSize(500, 600);
        setVisible(true);
    }

    class NorthPanel extends JFrame {
        public NorthPanel() {
            setLayout(new GridLayout(3,1));
            setBackground(Color.BLACK);
            info = new JLabel("수를 입력하세요");
            label = new JLabel("");

            info.setFont(new Font("맑은 고딕", 0, 20));
            info.setBackground(Color.BLACK);
            info.setForeground(Color.WHITE);
            info.setHorizontalAlignment((SwingConstants.RIGHT));

            label.setFont(new Font("맑은 고딕", 0, 40));
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            info.setHorizontalAlignment((SwingConstants.RIGHT));

            add(info);
            add(label);
            label.addMouseListener(new MyMouse());
        }
    }
    class MyMouse extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2) {
                pos_neg = 0;
                label.setText("");
                info.setText("수식을 입력하세요 ");
            }
        }
    }

    class CenterPanel extends JPanel{
        public CenterPanel() {
            JButton[] bt = new JButton[16];
            setBackground(Color.BLACK);
            setLayout(new GridLayout(4,4,5,5));

            bt[0] = new JButton("7");
            bt[1] = new JButton("8");
            bt[2] = new JButton("9");
            bt[3] = new JButton("×");

            bt[4] = new JButton("4");
            bt[5] = new JButton("5");
            bt[6] = new JButton("6");
            bt[7] = new JButton("/");

            bt[8] = new JButton("1");
            bt[9] = new JButton("2");
            bt[10] = new JButton("3");
            bt[11] = new JButton("+");

            bt[12] = new JButton("C");
            bt[13] = new JButton("0");
            bt[14] = new JButton("=");
            bt[15] = new JButton("-");

            for(int i = 0; i <= 15; i++) {
                if(i%4 != 3 && i < 11){
                    bt[i].setBackground(Color.GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);
                    bt[i].addActionListener(new ActionListener () {
                        public void actionPerformed(ActionEvent e) {
                            if (pos_neg == 0) {
                                JButton b = (JButton)e.getSource();
                                String oldtext = label.getText();
                                String text = b.getText();
                                String newtext = oldtext + text;

                                int n = newtext.length();
                                if (n <= 10) label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
                                else if (n > 10) label.setFont(new Font("맑은 고딕", Font.BOLD, 30));

                                if (n <= 25) {
                                    label.setText(newtext);
                                    info.setText("수식을 계산 중입니다 ");
                                }
                                else if (n > 25) info.setText("입력 가능한 범위를 초과하였습니다 ");
                            }
                        }
                    });
                }
                else if(i == 12 || i == 14) {
                    bt[i].setFont(new Font("맑은 고딕", Font.BOLD, 30));
                    bt[i].setBackground(Color.LIGHT_GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);

                    bt[12].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int n = label.getText().length()-1;
                            if(n == 0){
                                label.setText("");
                                info.setText("수식을 입력해주세요 ");
                                pos_neg = 0;
                            }
                            else if(n > 0 && n <= 10) {
                                label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
                                label.setText(label.getText().substring(0,n));
                                info.setText("수식을 지우는 중");
                            }
                            else {
                                label.setFont(new Font("맑은 고딕", Font.BOLD, 35));
                                label.setText(label.getText().substring(0,n));
                                info.setText("수식을 지우는 중");
                            }
                        }
                    });
                    bt[14].addActionListener(new Main());
                }
                else if(i==3){
                    bt[i].setFont(new Font("맑은 고딕", 0, 30));
                    bt[i].setBackground(Color.GRAY);
                    bt[i].setForeground(Color.WHITE);
                    add(bt[i]);
                    bt[i].addActionListener(new MyListener());
                }
            }
        }
    }
    private class MyListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            int n = label.getText().length();
            Character c = label.getText().charAt(n-1);
            if (pos_neg == 0 && c != '+' && c != '-' && c != '×' && c != '÷') {
                String oldtext = label.getText();
                String text = b.getText();
                String newtext = oldtext + text;
                label.setText(newtext);
                info.setText("수식을 계산 중입니다 ");
            }
        }
    }
}
