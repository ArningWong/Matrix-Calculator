package math1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

public class WinMat extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinMat frame = new WinMat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WinMat() {
		setResizable(false);
		setTitle("矩阵的运算");
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(150, 150, 515, 500);

		// 面板容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblM = new JLabel("矩阵1");
		lblM.setBounds(10, 25, 58, 15);
		contentPane.add(lblM);

		// 文本框1
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 50, 200, 150);
		contentPane.add(textPane);

		JLabel lblNewLabel = new JLabel("矩阵2");
		lblNewLabel.setBounds(10, 235, 58, 15);
		contentPane.add(lblNewLabel);

		// 文本框2
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(10, 260, 200, 150);
		contentPane.add(textPane_1);

		// 输出結果的文本框
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(301, 260, 200, 150);
		textPane_2.setEditable(false);
		contentPane.add(textPane_2);
		

		JPanel panel = new JPanel();
		panel.setBounds(301, 50, 200, 150);
		contentPane.add(panel);
		panel.setLayout(null);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("矩阵相加");
		rdbtnNewRadioButton.setBounds(24, 5, 86, 23);
		panel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("矩阵相减");
		rdbtnNewRadioButton_1.setBounds(24, 30, 86, 23);
		panel.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("矩阵相乘");
		rdbtnNewRadioButton_2.setBounds(24, 55, 86, 23);
		panel.add(rdbtnNewRadioButton_2);

		JButton btnNewButton = new JButton("立即运算");
		btnNewButton.setBounds(93, 96, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("立即运算");
		btnNewButton_1.setBounds(93, 127, 97, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("分数输出:");
		lblNewLabel_1.setBounds(10, 100, 75, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("小数输出:");
		lblNewLabel_3.setBounds(10, 131, 75, 15);
		panel.add(lblNewLabel_3);

		// 三个按钮只能选择其中一个
		ButtonGroup button = new ButtonGroup();
		button.add(rdbtnNewRadioButton);
		button.add(rdbtnNewRadioButton_1);
		button.add(rdbtnNewRadioButton_2);

		rdbtnNewRadioButton.setSelected(true);
		getContentPane().add(panel);


		JLabel lblNewLabel_2 = new JLabel("输出结果：");
		lblNewLabel_2.setBounds(301, 235, 83, 15);
		contentPane.add(lblNewLabel_2);
		
		// 运行结果
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String a = textPane.getText().toString();
				String b = textPane_1.getText().toString();
				
				//去除矩阵中的空行
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				b = b.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag1 = true;
				boolean flag2 = true;
				//计算a矩阵的行数
				String[] lena = a.split("\n+");
				int la1 = lena.length;
				//计算a矩阵的列数
				String[] a1 = lena[0].split("\\s+");
				int la2 = a1.length;
				int temp1 = la2;
				//判断a是否为矩阵, 每行的元素个数是否相等
				for (int i=0; i<a1.length; i++) {
					if (temp1 != lena[i].split("\\s+").length) {
						flag1 = false;
						break;
					}
				}
				
				//计算b矩阵的行数
				String[] lenb = b.split("\n+");
				int lb1 = lenb.length;
				//计算b矩阵的列数
				String[] b1 = lenb[0].split("\\s+");
				//矩阵第一行的个数
				int lb2 = b1.length;
				int temp2 = lb2;
				//判断b是否为矩阵, 每行的元素个数是否相等
				for (int i=0; i<b1.length; i++) {
					//计算每一行的个数
					if (temp2 != lenb[i].split("\\s+").length) {
						flag2 = false;
						break;
					}
				}
				
				//将矩阵转化成一个字符串数组
				String[] m = a.split("\\s+");
				String[] n = b.split("\\s+");

				
//				String[] a1 = a.split("\\s+");
//				String[] b1 = b.split("\\s+");
//
//				// 获取行数和列数
//				String[] lena = a.split("\n");
//				int la1 = lena.length;
//				int la2 = a1.length / lena.length;
//
//				String[] lenb = b.split("\n");
//				int lb1 = lenb.length;
//				int lb2 = b1.length / lenb.length;

				if (rdbtnNewRadioButton.isSelected() && la1 == lb1 && la2 == lb2 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2);
					p.getMat(m, n);
					Matrix ans = null;
					textPane_2.setText(p.mAdd(ans));

				} else if (rdbtnNewRadioButton_1.isSelected() && la1 == lb1 && la2 == lb2 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2);
					p.getMat(m, n);
					Matrix ans = null;
					textPane_2.setText(p.mSub(ans));

				} else if (rdbtnNewRadioButton_2.isSelected() && la2 == lb1 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2, lb2);
					p.getMat(m, n);
					MulMatrix ans = null;
					textPane_2.setText(p.mMul(ans));

				} else {
					textPane_2.setText("输入出现错误！请检查！");

				}
			}

		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				String a = textPane.getText().toString();
				String b = textPane_1.getText().toString();

				//去除矩阵中的空行
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				b = b.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag1 = true;
				boolean flag2 = true;
				//计算a矩阵的行数
				String[] lena = a.split("\n+");
				int la1 = lena.length;
				//计算a矩阵的列数
				String[] a1 = lena[0].split("\\s+");
				int la2 = a1.length;
				int temp1 = la2;
				//判断a是否为矩阵, 每行的元素个数是否相等
				for (int i=0; i<a1.length; i++) {
					if (temp1 != lena[i].split("\\s+").length) {
						flag1 = false;
						break;
					}
				}
				
				//计算b矩阵的行数
				String[] lenb = b.split("\n+");
				int lb1 = lenb.length;
				//计算a矩阵的列数
				String[] b1 = lenb[0].split("\\s+");
				//矩阵第一行的个数
				int lb2 = b1.length;
				int temp2 = lb2;
				//判断a是否为矩阵, 每行的元素个数是否相等
				for (int i=0; i<b1.length; i++) {
					//计算每一行的个数
					if (temp2 != lenb[i].split("\\s+").length) {
						flag2 = false;
						break;
					}
				}
				
				//将矩阵转化成一个字符串数组
				String[] m = a.split("\\s+");
				String[] n = b.split("\\s+");

				if (rdbtnNewRadioButton.isSelected() && la1 == lb1 && la2 == lb2 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2);
					p.getMat(m, n);
					Matrix ans = null;
					String s = p.mAdd(ans);
					
					FractionToDouble fr = null;
					textPane_2.setText(fr.convert(s, la1, la2));

				} else if (rdbtnNewRadioButton_1.isSelected() && la1 == lb1 && la2 == lb2 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2);
					p.getMat(m, n);
					Matrix ans = null;
					String s = p.mSub(ans);
					
					FractionToDouble fr = null;
					textPane_2.setText(fr.convert(s, la1, la2));

				} else if (rdbtnNewRadioButton_2.isSelected() && la2 == lb1 && flag1 && flag2) {
					DrawPanel p = new DrawPanel(la1, la2, lb2);
					p.getMat(m, n);
					MulMatrix ans = null;
					String s = p.mMul(ans);
					
					FractionToDouble fr = null;
					textPane_2.setText(fr.convert(s, la1, lb2));

				} else {
					textPane_2.setText("输入出现错误！请检查！");

				}
				
			}
		});
		
		
	}
}

class DrawPanel extends JPanel {

	public int row;
	public int column;
	public int row2;
	public int column2;

	public String[][] m1;
	public String[][] m2;

	DrawPanel(int row, int column) {
		this.row = row;
		this.column = column;
		this.row2 = row;
		this.column2 = column;
		m1 = new String[this.row][this.column];
		m2 = new String[this.row2][this.column2];
	}

	DrawPanel(int row, int column, int column2) {
		this.row = row;
		this.column = column;
		this.row2 = column;
		this.column2 = column2;
		m1 = new String[this.row][this.column];
		m2 = new String[this.row2][this.column2];
	}

	public void getMat(String[] a1, String[] b1) {
		int k = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				m1[i][j] = a1[k];
				k++;
			}
		}

		k = 0;
		for (int i = 0; i < row2; i++) {
			for (int j = 0; j < column2; j++) {
				m2[i][j] = b1[k];
				k++;
			}
		}

	}

	public String mAdd(Matrix a) {
		a = new Matrix(row, column, m1, m2);
		return a.matrixAdd();

	}

	public String mSub(Matrix a) {
		a = new Matrix(row, column, m1, m2);
		return a.matrixSubstract();

	}

	public String mMul(MulMatrix a) {
		a = new MulMatrix(row, column, column2, m1, m2);
		return a.matrixMultiply();

	}

}


