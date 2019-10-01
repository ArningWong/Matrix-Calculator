package math1;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class WinTrans extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinTrans frame = new WinTrans();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public WinTrans() {
		setTitle("�����������");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(250, 250, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(30, 35, 200, 200);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(347, 35, 200, 200);
		contentPane.add(textPane_1);
		
		JButton btnNewButton = new JButton("������");
		btnNewButton.setBounds(240, 98, 97, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("�������:");
		lblNewLabel.setBounds(240, 73, 75, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("С�����:");
		lblNewLabel_1.setBounds(240, 143, 75, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("������");
		btnNewButton_1.setBounds(240, 168, 97, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				String a = textPane.getText().toString();
				
				//ȥ�������еĿ���
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag = true;
				String[] len = a.split("\n");
				//���������
				int len1 = len.length;
				String []a2 = a.split("\\s+");
				
				String[] a1 = len[0].split("\\s+");
				//��һ�е�Ԫ�ظ���
				int len2 = a1.length;
				//�жϷ������Ƿ�û�п�ȱ
				for (int i =0; i < len1; i++) {
					if (len2 != len[i].split("\\s+").length) {
						flag = false;
						break;
					}
				}
				
				if(flag && len1==len2) {
					DrawPanel3 p = new DrawPanel3(len.length);
					p.getTrans(a2);
					
					SquareMatrix ans = null;
					textPane_1.setText(p.mTrans(ans));
				}
				else {
					textPane_1.setText("�þ����Ƿ������������룡");
				}
				
				
				

				
				
				
//				String a = textPane.getText().toString();
//				
//				String[] len = a.split("\n");
//				int l = len.length;
//				
//				DrawPanel3 p = new DrawPanel3(l);
//				
//				String[] a1 = a.split("\\s+");
//				
//				p.getTrans(a1);
//				
//				SquareMatrix ans = null;
//				
//				textPane_1.setText(p.mTrans(ans));
				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				String a = textPane.getText().toString();
				
				//ȥ�������еĿ���
				a = a.replaceAll("(?m)^\\s*$"+System.lineSeparator(), "");
				
				boolean flag = true;
				String[] len = a.split("\n");
				//���������
				int len1 = len.length;
				String []a2 = a.split("\\s+");
				
				String[] a1 = len[0].split("\\s+");
				//��һ�е�Ԫ�ظ���
				int len2 = a1.length;
				//�жϷ������Ƿ�û�п�ȱ
				for (int i =0; i < len1; i++) {
					if (len2 != len[i].split("\\s+").length) {
						flag = false;
						break;
					}
				}
				
				if(flag && len1==len2) {
					try {
						DrawPanel3 p = new DrawPanel3(len.length);
						p.getTrans(a2);
						SquareMatrix ans = null;
						String s = p.mTrans(ans);
						
						FractionToDouble fr = null;
						textPane_1.setText(fr.convert(s, len1, len2));
						
					}catch(Exception ex) {
						textPane_1.setText("����ʽΪ0���÷����������");
					}
				}
				
				else {
					textPane_1.setText("�þ����Ƿ������������룡");
				}
				
			}
		});
		
	}

}

class DrawPanel3 extends JPanel{

	public int row ;
	
	public String [][]m;
	
	DrawPanel3(int row){
		this.row = row;
		m = new String[this.row][this.row];
	}
	
	public void getTrans(String[] a1) {
		int k = 0;
		for(int i = 0;i < row;i++) {
			for(int j = 0;j < row;j++) {
				m[i][j] = a1[k];
				k++;
			}
		}
	}	
	
	public String mTrans(SquareMatrix t) {
		t = new SquareMatrix(row,m);
		try {
			t.getInverse();
		}catch(Exception e) {
			return "����ʽΪ0���÷����������";
		}
		
		return t.getInverse();
		
	}
	
	
}


