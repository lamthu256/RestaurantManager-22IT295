package Restaurants;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.processing.Messager;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;

import Restaurants.Admin;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Login{
	
	private JFrame frmLogin;
	private JTextField tf_username;
	private JPasswordField tf_password;

	public Login() {
		initialize();
	}

	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/lamthu/Downloads/background-menu-nha-hang_090706772.jpeg"));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 439, 623);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.setLocationRelativeTo(null);//đưa frame vào giữa màn hình
		
		JLabel lb_username = new JLabel("Tài Khoản");
		lb_username.setForeground(new Color(128, 0, 0));
		lb_username.setFont(new Font("Tahoma", Font.BOLD, 13));
		lb_username.setBounds(81, 324, 71, 16);
		frmLogin.getContentPane().add(lb_username);
		
		JLabel ld_password = new JLabel("Mật Khẩu ");
		ld_password.setForeground(new Color(128, 0, 0));
		ld_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		ld_password.setBounds(81, 383, 71, 16);
		frmLogin.getContentPane().add(ld_password);
		
		tf_username = new JTextField();
		tf_username.setBackground(new Color(240, 241, 242));
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tf_username.setBounds(182, 319, 179, 26);
		frmLogin.getContentPane().add(tf_username);
		
		tf_password = new JPasswordField();
		tf_password.setBackground(new Color(240, 241, 242));
		tf_password.setBounds(182, 378, 179, 26);
		frmLogin.getContentPane().add(tf_password);
		
		JButton bt_login = new JButton("Đăng Nhập");
		bt_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectionDB c = new ConnectionDB();
				try {
					User u = c.checkLogin(tf_username.getText(), new String(tf_password.getPassword())); 
					if(u != null)
					{ 
						Admin.main(null);
						frmLogin.dispose();//đóng frame
					}else {
						JOptionPane.showMessageDialog(bt_login, "Tên đăng nhập hay mật khẩu sai");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(bt_login, "Lỗi");
				}
			}
		});
		bt_login.setBackground(new Color(239, 83, 87));
		bt_login.setForeground(new Color(250, 203, 135));
		bt_login.setBorderPainted(false);
		bt_login.setOpaque(true);
		bt_login.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_login.setBounds(73, 460, 116, 36);
		frmLogin.getContentPane().add(bt_login);
		
		JButton bt_exit = new JButton("Thoát");
		bt_exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bt_exit.setBackground(new Color(240, 241, 242));
		bt_exit.setForeground(new Color(245, 175, 125));
		bt_exit.setBorderPainted(false);
		bt_exit.setOpaque(true);
		bt_exit.setFont(new Font("Tahoma", Font.BOLD, 13));
		bt_exit.setBounds(251, 460, 116, 36);
		frmLogin.getContentPane().add(bt_exit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Bản sao Picture1.png")
				.getImage().getScaledInstance(171, 114, Image.SCALE_DEFAULT)));
		lblNewLabel.setBounds(132, 193, 171, 114);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(245, 177, 126));
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon("/Users/lamthu/Downloads/Login.jpeg")
				.getImage().getScaledInstance(444, 602, Image.SCALE_DEFAULT)));
		
		lblNewLabel_1.setBounds(0, 0, 439, 600);
		frmLogin.getContentPane().add(lblNewLabel_1);
		bt_exit.addActionListener(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
