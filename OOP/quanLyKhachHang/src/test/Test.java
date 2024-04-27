package test;

import javax.swing.UIManager;
import view.Login_Admin;

public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Login_Admin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
