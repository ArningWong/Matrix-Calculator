package math1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//����������ť
	JButton btnNewButton = new JButton("���������");
	JButton btnNewButton_1 = new JButton("���������ʽ");
	JButton btnNewButton_2 = new JButton("����������");
	
	//����GUI
	public Window() {
		
		setTitle("�����Դ������γ�ѧϰ�������");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 245);
		
		//�������
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		//��ť��������
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		panel_1.add(btnNewButton);
		
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 20));
		panel_1.add(btnNewButton_1);
		
		btnNewButton_2.setFont(new Font("����", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2);
		
		//����һ������
		JLabel label = new JLabel("��ѡ��һ������");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("����", Font.PLAIN, 20));
		contentPane.add(label, BorderLayout.NORTH);
		getContentPane().add(panel_1);
		
		//����������ĸ���ť
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == btnNewButton){               
        	new WinMat();                //����һ���½���:���������
             
        }
        else if(e.getSource() == btnNewButton_1){
        	new WinSquareM();              //����һ���½���:���������ʽ
             
        }
        else if(e.getSource() == btnNewButton_2){
        	new WinTrans();              //����һ���½���:����������
             
        }
        
	}
	
}


