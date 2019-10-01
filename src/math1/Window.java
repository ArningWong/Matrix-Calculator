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
	
	//制作三个按钮
	JButton btnNewButton = new JButton("矩阵的运算");
	JButton btnNewButton_1 = new JButton("求方阵的行列式");
	JButton btnNewButton_2 = new JButton("求方阵的逆矩阵");
	
	//制作GUI
	public Window() {
		
		setTitle("《线性代数》课程学习辅助软件");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 245);
		
		//面板容器
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		//按钮字体设置
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(btnNewButton);
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(btnNewButton_1);
		
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2);
		
		//设置一个导语
		JLabel label = new JLabel("请选择一个功能");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label, BorderLayout.NORTH);
		getContentPane().add(panel_1);
		
		//监听鼠标点击哪个按钮
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == btnNewButton){               
        	new WinMat();                //创建一个新界面:矩阵的运算
             
        }
        else if(e.getSource() == btnNewButton_1){
        	new WinSquareM();              //创建一个新界面:求方阵的行列式
             
        }
        else if(e.getSource() == btnNewButton_2){
        	new WinTrans();              //创建一个新界面:求方阵的逆矩阵
             
        }
        
	}
	
}


