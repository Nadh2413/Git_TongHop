package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ds_Admin extends JFrame {

	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ds_Admin frame = new Ds_Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ds_Admin() {
		setTitle("Danh sách Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 478);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(274, 10, 101, 108);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\ds_admin.png"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(145, 70, 353, 341);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Huỳnh Quang Triệu - N19DCVT069");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(63, 112, 246, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Võ Hoài Thanh - N19DCVT060");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(63, 147, 246, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Lê Nguyễn Đức Duy - N19DCVT006");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(63, 182, 246, 29);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Chu Đình Huấn - N19DCVT013");
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(63, 215, 246, 29);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Nguyễn Duy Phúc - N19DCVT044");
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(63, 249, 246, 29);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_2 = new JLabel("Nhóm 3:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(142, 58, 88, 30);
		panel.add(lblNewLabel_2);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(0, 128, 0));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerManagerment.main(null);
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnBack);
		        currentFrame.dispose();
			}
		});
		btnBack.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\back.png"));
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnBack.setBounds(0, 0, 87, 25);
		contentPane.add(btnBack);
		
		setLocationRelativeTo(null);
	}
}
