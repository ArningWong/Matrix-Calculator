package math1;

import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class WinSquareM extends JFrame {

	private JPanel contentPane;
	private JTextPane textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinSquareM frame = new WinSquareM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WinSquareM() {
		setTitle("行列式的运算");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 200, 280, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(30, 35, 200, 200);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("计算结果");
		btnNewButton.setBounds(133, 245, 97, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("分数输出:");
		lblNewLabel.setBounds(40, 249, 75, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("小数输出:");
		lblNewLabel_1.setBounds(40, 300, 75, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("计算结果");
		btnNewButton_1.setBounds(133, 296, 97, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextPane();
		textField.setEditable(false);
		textField.setBounds(30, 339, 200, 30);
		contentPane.add(textField);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String a = textPane.getText().toString();
				
				//去除矩阵中的空行
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag = true;
				String[] len = a.split("\n");
				//方阵的行数
				int len1 = len.length;
				String[] a2 = a.split("\\s+");
				
				String[] a1 = len[0].split("\\s+");
				//第一行的元素个数
				int len2 = a1.length;
				//判断方阵中是否没有空缺
				for (int i =0; i < len1; i++) {
					if (len2 != len[i].split("\\s+").length) {
						flag = false;
						break;
					}
				}
//				System.out.println(len1);
//				System.out.println(len2);
//				System.out.println(flag);
				
//				String[] len = a.split("\n");
//				String[] a1 = a.split("\\s+");
//				
//				int l1 = len.length;
//				int l2 = a1.length/len.length;
				
				if(flag && len1==len2) {
					DrawPanel2 p = new DrawPanel2(len.length);
					
					p.getSqu(a2);
				
					SquareMatrix ans = null;
				
					textField.setText(p.mSquare(ans));
					}
				else {
					textField.setText("该矩阵不是方阵！请重新输入！");
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				String a = textPane.getText().toString();
				
				//去除矩阵中的空行
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag = true;
				String[] len = a.split("\n");
				//方阵的行数
				int len1 = len.length;
				String []a2 = a.split("\\s+");
				
				String[] a1 = len[0].split("\\s+");
				//第一行的元素个数
				int len2 = a1.length;
				//判断方阵中是否没有空缺
				for (int i =0; i < len1; i++) {
					if (len2 != len[i].split("\\s+").length) {
						flag = false;
						break;
					}
				}
				
				if(flag && len1==len2) {
					DrawPanel2 p = new DrawPanel2(len.length);
					p.getSqu(a2);
					SquareMatrix ans = null;
					String s = p.mSquare(ans);
					
					FractionToDouble fr = null;
					textField.setText(fr.convert(s, 1, 1));
					
					}
				else {
					textField.setText("该矩阵不是方阵！请重新输入！");
				}
				
			}
		});
		
	
	
	}
}

class DrawPanel2 extends JPanel{

	public int row ;
	
	public String [][]m;
	
	DrawPanel2(int row){
		this.row = row;
		m = new String[this.row][this.row];
	}

	public void getSqu(String[] a1) {
		int k = 0;
		for(int i = 0;i < row;i++) {
			for(int j = 0;j < row;j++) {
				m[i][j] = a1[k];
				k++;
			}
		}
	}	
	
	public String mSquare(SquareMatrix s) {
		s = new SquareMatrix(row,m);
		String ans;
		try{
			ans = s.detValue();
		}catch(Exception e){
			return "0";
		}
		return ans;
		
	}
	
}