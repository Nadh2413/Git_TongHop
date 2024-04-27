package view;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Login_Admin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Admin frame = new Login_Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login_Admin() {
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setTitle("Admin Login");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 100, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 204));
		panel.setBounds(0, 0, 285, 363);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIconLogo = new JLabel("");
		lblIconLogo.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\login_logo.png"));
		lblIconLogo.setBounds(10, 38, 265, 192);
		panel.add(lblIconLogo);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome!");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_3.setBounds(80, 250, 146, 62);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(325, 142, 226, 40);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtUsername.setText("Username");
		txtUsername.setBounds(10, 11, 168, 19);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\login_user.png"));
		lblNewLabel.setBounds(188, 0, 34, 40);
		panel_1.add(lblNewLabel);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(220, 220, 220));
		panel_1_1.setBounds(325, 192, 226, 40);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.ITALIC, 10));
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 11, 168, 19);
		panel_1_1.add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\login_pass.png"));
		lblNewLabel_1.setBounds(188, 0, 34, 40);
		panel_1_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login Form");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 35));
		lblNewLabel_2.setBounds(325, 63, 199, 53);
		contentPane.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\login.png"));
		btnLogin.setBackground(UIManager.getColor("Button.background"));
		btnLogin.setForeground(new Color(0, 128, 0));
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getPassword().toString();
				
				//login
				if (username.contains("Quang Trieu") || password.contains("1234567890")) {
					txtUsername.setText(null);
					txtPassword.setText(null);
					
					//link
					CustomerManagerment.main(null);
				}
				else if (username.contains("Admin") || password.contains("1234567890")){
					txtUsername.setText(null);
					txtPassword.setText(null);
					
					// dong form hien tai
					JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnLogin);
			        currentFrame.dispose();
					
					//link
					CustomerManagerment.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Login Details", "Login Error", JOptionPane.ERROR_MESSAGE);
					txtUsername.setText(null);
					txtPassword.setText(null);
				}
			}
		});
		btnLogin.setBounds(325, 265, 98, 31);
		contentPane.add(btnLogin);
		
		setLocationRelativeTo(null);
		
		this.setVisible(true);
	}		
}
