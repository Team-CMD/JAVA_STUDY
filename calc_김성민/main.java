import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math.*;

public class Calculator extends JFrame implements ActionListener{
	ArrayList<String> op = new ArrayList<>();
	ArrayList<String> num = new ArrayList<>();
	ArrayList<Double> num2 = new ArrayList<>();
	JTextField jf = new JTextField("0");
	String[] msg = {"1","2","3","+","4","5","6","-","7","8","9","*","0",".","√","/"};
	JButton[] j = new JButton[msg.length];
	JButton[] jj = new JButton[2];
	double result;
	
	Calculator(){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JPanel pp = new JPanel();
		pp.setLayout(new GridLayout(4,4,10,10));
		
		
		for(int i = 0; i < msg.length; i ++) {
			j[i] = new JButton(msg[i]);
			j[i].setBackground(Color.lightGray);
			if(msg[i].equals("+")||msg[i].equals("-")||msg[i].equals("*")||msg[i].equals("/")) {
				j[i].setBackground(Color.white);
			}
		}
		
		setTitle("인혜의 계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jj[0] = new JButton("Clear");
		jj[1] = new JButton("Enter");
		jj[0].setBackground(Color.WHITE);
		jj[1].setBackground(Color.white);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(jf,BorderLayout.NORTH);
		c.add(jj[1],BorderLayout.SOUTH);;
		c.add(p,BorderLayout.CENTER);
		p.add(jj[0],BorderLayout.NORTH);
		
		p.add(pp,BorderLayout.CENTER);
		
		for(int i = 0; i < msg.length; i ++) {
			pp.add(j[i]);
			j[i].addActionListener(this);
		}
		
		jj[0].addActionListener(this);
		jj[1].addActionListener(this);
		
		
		setSize(400,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Calculator c = new Calculator();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String op = btn.getText();
		String str;
		
		boolean n = btn.getText().equals("+") || btn.getText().equals("-");
		boolean n2 = btn.getText().equals("/") || btn.getText().equals("*");
		
		if(op.equals("Enter")) {
			str = "";
			for(int i = 0;i < num.size();i++) {
				str += num.get(i);
			}
			cal(this.op.get(0),Double.parseDouble(str));
			jf.setText(Double.toString(result));
			reset();
		}else if(op.equals("Clear")) {
			reset();
			jf.setText("0");
		}else if(op.equals("√")) {
			
			try {
				str = "";
				for(int i = 0;i < num.size();i++) {
					str += num.get(i);
				}
				this.result = Math.sqrt(Double.parseDouble(str));
				System.out.println(result);
				jf.setText(Double.toString(result));
				num2.add(result);
				
			}catch(Exception e5) {
				jf.setText("숫자를 입력해주세요.");
			}
		
		}
		else {
	
		if(n == false && n2 == false) {
			num.add(op);
			str = "";
			for(int i = 0;i < num.size();i++) {
				str += num.get(i);
			}
			jf.setText(str);
		}else if(n == true || n2 == true) {
			this.op.add(op);
			
			str = "";
			try {
			for(int i = 0;i < num.size();i++) {
				str += num.get(i);
			}
			   num2.add(Double.parseDouble(str));
			
			while(num.size() != 0) {
				int i;
				i = num.size();
				num.remove(i-1);
				i--;
			}
			}catch(NumberFormatException e1) {
				jf.setText("숫자를 입력해주세요.");
			}
			
			if(num2.size()==2) {
				cal(this.op.get(0),num2.get(1));
				
				while(num2.size() != 0) {
					int i;
					i = num2.size();
					num2.remove(i-1);
					i--;
				}
				
				this.op.remove(0);
				num2.add(result);

				}
			
			
			}
		}
		
		
		
	}
	
	private void cal(String op,double a) {
		
		if(op.equals("+")) {
			this.result =  num2.get(0) + a;
		}else if(op.equals("-")) {
			this.result = num2.get(0) - a;
		}else if(op.equals("/")) {
			this.result = num2.get(0)/a;
		}else if(op.equals("*")) {
			this.result = num2.get(0)*a;
		}
		


	}
	
	private void reset() {
		while(num.size() != 0) {
			int i;
			i = num.size();
			num.remove(i-1);
			i--;
		}
		
		while(num2.size() != 0) {
			int i;
			i = num2.size();
			num2.remove(i-1);
			i--;
		}
		
		while(this.op.size() != 0) {
			int i;
			i = op.size();
			op.remove(i-1);
			i--;
		}
		
	}
}