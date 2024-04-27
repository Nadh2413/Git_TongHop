package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.security.auth.Refreshable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import model.Customer;
import controller.CustomerModify;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.event.MouseAdapter;

public class CustomerManagerment extends JFrame {
	private Customer model;
	private JPanel contentPane;
	private JLabel lblClock;
	private JTextField emailTxt;
	private JTextField fullnameTxt;
	private JTextField phoneTxt;
	private JTextField birthdayTxt;
	private JTextField addressTxt;
	private JTable customerTable;
	
	//SQL
	DefaultTableModel tableModel;
	List<Customer> dataList;
	int currentPos = -1;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerManagerment frame = new CustomerManagerment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// set clock
	public void clock() {
		Thread clock = new Thread() {
			public void run() {
				try {
					for(;;) {
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH) + 1;
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute = cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					
					lblClock.setText("Time " + hour + ":" + minute + ":" + second + "  | " + " Date " + year + "/" + month + "/" + day);
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
	}

	public CustomerManagerment() {
		this.model = new Customer(currentPos, getName(), getName(), getTitle(), getWarningString(), getName());
		initComponents();
		clock();
		
		//SQL
		tableModel = (DefaultTableModel) customerTable.getModel();
		dataList = CustomerModify.getCustomerList(null);
		
		showData();
		
		customerTable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				
			}
			public void mousePressed(MouseEvent e) {
			
			}
			public void mouseExited(MouseEvent e) {
			
			}
			public void mouseEntered(MouseEvent e) {
			
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				currentPos = customerTable.getSelectedRow();
				
				fullnameTxt.setText(dataList.get(currentPos).getFullname());
				emailTxt.setText(dataList.get(currentPos).getEmail());
				phoneTxt.setText(dataList.get(currentPos).getPhoneNumber());
				birthdayTxt.setText(dataList.get(currentPos).getBirthday());
				addressTxt.setText(dataList.get(currentPos).getAddress());
				
			}
		});
	}
	
	//ham hien thi du lieu len bang
	public void showData() {
		tableModel.setRowCount(0);
		for (Customer customer : dataList) {
			tableModel.addRow(new Object[] {
				tableModel.getRowCount() + 1,
				customer.getFullname(),
				customer.getEmail(),
				customer.getPhoneNumber(),
				customer.getBirthday(),
				customer.getAddress()
				
			});
		}
	}
	
	public void initComponents( ) {
		setTitle("Chức năng quản lý khách hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 569);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\back.png"));
		menuBar.add(BackBtn);
		BackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Đóng form hiện tại
		        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(BackBtn);
		        currentFrame.dispose();

		        // Mở form mới
		        Login_Admin.main(null);
			}
		});
		BackBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		BackBtn.setForeground(new Color(0, 128, 0));
		BackBtn.setBackground(UIManager.getColor("Button.background"));
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		menuOpen.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\Open.png"));
		menuFile.add(menuOpen);
		menuOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thucHienOpenFile();
			}
		});
		
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		menuSave.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\Save.png"));
		menuFile.add(menuSave);
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thucHienSaveFile();
			}
		});
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		menuExit.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\Exit.png"));
		menuFile.add(menuExit);
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thoatKhoiPhanMem();
			}
		});
		
		JMenu menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		
		JMenuItem menuAboutMe = new JMenuItem("About me");
		menuAboutMe.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\About.png"));
		menuAbout.add(menuAboutMe);
		menuAboutMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hienThiAbout();
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 454, 1036, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblClock = new JLabel("Clock");
		lblClock.setForeground(new Color(0, 128, 0));
		lblClock.setBackground(Color.WHITE);
		lblClock.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblClock.setBounds(759, 10, 267, 35);
		panel.add(lblClock);
		
		JLabel lblTraiDat = new JLabel("");
		lblTraiDat.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\clock.png"));
		lblTraiDat.setBounds(701, 10, 48, 35);
		panel.add(lblTraiDat);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 204));
		panel_1.setBounds(0, 10, 159, 440);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\login_logo.png"));
		lblNewLabel_1.setBounds(0, 10, 159, 420);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CUSTOMER MANAGERMENT");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_2.setBounds(4, 50, 152, 36);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("SOFTWARE");
		lblNewLabel_2_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(50, 75, 67, 36);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblGroupAdmin = new JLabel("");
		lblGroupAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Ds_Admin.main(null);
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(lblGroupAdmin);
		        frame.setVisible(false);
			}
		});
		lblGroupAdmin.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\group_admin.png"));
		lblGroupAdmin.setBounds(55, 380, 48, 54);
		panel_1.add(lblGroupAdmin);
		
		
		JLabel lblName = new JLabel("Tên KH:");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblName.setBounds(169, 17, 75, 29);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEmail.setBounds(170, 72, 75, 29);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("SĐT:");
		lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPhone.setBounds(170, 126, 75, 29);
		contentPane.add(lblPhone);
		
		JLabel lblBirth = new JLabel("Ngày sinh:");
		lblBirth.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBirth.setBounds(170, 178, 75, 29);
		contentPane.add(lblBirth);
		
		JLabel lblAddress = new JLabel("Địa chỉ:");
		lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblAddress.setBounds(170, 230, 75, 29);
		contentPane.add(lblAddress);
		
		emailTxt = new JTextField();
		emailTxt.setFont(new Font("Arial", Font.PLAIN, 12));
		emailTxt.setColumns(10);
		emailTxt.setBounds(255, 72, 245, 30);
		contentPane.add(emailTxt);
		
		fullnameTxt = new JTextField();
		fullnameTxt.setFont(new Font("Arial", Font.PLAIN, 12));
		fullnameTxt.setColumns(10);
		fullnameTxt.setBounds(254, 17, 245, 30);
		contentPane.add(fullnameTxt);
		
		phoneTxt = new JTextField();
		phoneTxt.setFont(new Font("Arial", Font.PLAIN, 12));
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(255, 126, 245, 30);
		contentPane.add(phoneTxt);
		
		birthdayTxt = new JTextField();
		birthdayTxt.setFont(new Font("Arial", Font.PLAIN, 12));
		birthdayTxt.setColumns(10);
		birthdayTxt.setBounds(255, 178, 245, 30);
		contentPane.add(birthdayTxt);
		
		addressTxt = new JTextField();
		addressTxt.setFont(new Font("Arial", Font.PLAIN, 12));
		addressTxt.setColumns(10);
		addressTxt.setBounds(255, 230, 245, 30);
		contentPane.add(addressTxt);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(164, 274, 342, 2);
		contentPane.add(separator_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 128, 0));
		tabbedPane.setBackground(new Color(192, 192, 192));
		tabbedPane.setBounds(510, 14, 4, 431);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(523, 17, 503, 427);
		contentPane.add(scrollPane);
		
		customerTable = new JTable();
		customerTable.setBackground(new Color(175, 238, 238));
		customerTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		customerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"STT", "Họ tên", "Email", "SĐT", "Ngày sinh", "Địa chỉ"
			}
		));
		scrollPane.setViewportView(customerTable);
		
		JButton saveBtn = new JButton("Lưu");
		saveBtn.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\Add.png"));
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveBtnActionPerformed(e);
			}
		});
		saveBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveBtn.setBounds(182, 298, 84, 29);
		contentPane.add(saveBtn);
		
		JButton delBtn = new JButton("Xoá");
		delBtn.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\dele.png"));
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delBtnActionPerformed(e);
			}
		});
		delBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		delBtn.setBounds(286, 298, 84, 29);
		contentPane.add(delBtn);
		
		JButton searchBtn = new JButton("Tìm kiếm");
		searchBtn.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\search.png"));
		searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBtnActionPerformed(e);
			}
		});
		searchBtn.setBounds(394, 298, 106, 29);
		contentPane.add(searchBtn);
		JButton cannelBtn = new JButton("Trở lại");
		cannelBtn.setIcon(new ImageIcon("D:\\Nam_4\\HK2\\LTrinhHDT\\my_icons\\go_back.png"));
		cannelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thucHienTaiLaiDuLieu(e);
			}
		});
		cannelBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cannelBtn.setBounds(394, 348, 106, 29);
		contentPane.add(cannelBtn);
		
		JLabel lblNewLabel = new JLabel("(yyyy-MM-dd)");
		lblNewLabel.setFont(new Font("Segoe UI", Font.ITALIC, 10));
		lblNewLabel.setBounds(434, 163, 65, 13);
		contentPane.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(160, 10, 4, 440);
		contentPane.add(panel_2);
		setLocationRelativeTo(null);
	}
	
	public void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentPos >= 0) {
            dataList.get(currentPos).setFullname(fullnameTxt.getText());
            dataList.get(currentPos).setEmail(emailTxt.getText());
            dataList.get(currentPos).setPhoneNumber(phoneTxt.getText());
            dataList.get(currentPos).setBirthday(birthdayTxt.getText());
            dataList.get(currentPos).setAddress(addressTxt.getText());
            
            CustomerModify.update(dataList.get(currentPos));
            currentPos = -1;
        } else {
            Customer customer = new Customer(
                    0,
                    fullnameTxt.getText(),
                    emailTxt.getText(),
                    phoneTxt.getText(),
                    birthdayTxt.getText(),
                    addressTxt.getText()
          
            );
            CustomerModify.insert(customer);
            dataList = CustomerModify.getCustomerList(null);
        }
        showData();

        fullnameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        birthdayTxt.setText("");
        addressTxt.setText("");
    }

    public void delBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(currentPos == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng cần xoá, vui lòng kiểm tra lại.");
            return;
        }
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc muốn xoá khách hàng này không?");
        if(option == 0) {
            CustomerModify.delete(dataList.get(currentPos).getId());
            dataList.remove(currentPos);
            currentPos = -1;
            showData();
        }

        fullnameTxt.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        birthdayTxt.setText("");
        addressTxt.setText("");
    }

    public void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String s = JOptionPane.showInputDialog("Nhập tên khách hàng cần tìm kiếm.");
        if(!s.isEmpty()) {
            s = "%"+s+"%";
        }
        dataList = CustomerModify.getCustomerList(s);
        showData();
        
    }
    
    public void thucHienTaiLaiDuLieu(java.awt.event.ActionEvent evt) {
    	dataList = CustomerModify.getCustomerList("");
        showData();
        JOptionPane.showMessageDialog(this, "Đã Loading!");
    }
 
    public void hienThiAbout() {
    	JOptionPane.showMessageDialog(this, "Phần mềm quản lý khách hàng của Nhóm 3.");
    	showData();
    }
    
    public void thoatKhoiPhanMem() {
    	int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn muốn thoát khỏi phần mềm quản lý khách hàng không?");
        if(option == 0) {
            System.exit(0);
            showData();
        }
    }
    
    public void saveFile(String path) {
        try {
            this.model.setTenFile(path);
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer customer : dataList) {
                StringBuilder builder = new StringBuilder();
                builder.append(customer.getFullname()).append(",");
                builder.append(customer.getEmail()).append(",");
                builder.append(customer.getPhoneNumber()).append(",");
                builder.append(customer.getBirthday()).append(",");
                builder.append(customer.getAddress());
                bufferedWriter.write(builder.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadFile(String path) {
        try {
            dataList.clear();
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                Customer customer = new Customer(currentPos, fields[0], fields[1], fields[2], fields[3], fields[4]);
                dataList.add(customer);
                currentPos++;
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void thucHienSaveFile() {
        if (this.model.getTenFile().length() > 0) {
        	String path = this.model.getTenFile() + ".txt";
            saveFile(path);
        } else {
            JFileChooser fs = new JFileChooser(new File("d:\\"));
            fs.setDialogTitle("Save a File");
            int result = fs.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fi = fs.getSelectedFile();
                String path = fi.getAbsolutePath() + ".txt";
                saveFile(path);
            }
        }
    }
    
    public void openFile(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj;
            List<Customer> customers = new ArrayList<Customer>();
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Customer) {
                    Customer customer = (Customer) obj;
                    customers.add(customer);
                }
            }
            ois.close();
            showCustomerTable(customers);
        } catch (EOFException e) {
            // End of file reached
        } catch (StreamCorruptedException e) {
            JOptionPane.showMessageDialog(this, "File không hợp lệ hoặc bị hỏng", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCustomerTable(List<Customer> customers) {
        JTable customerTable = new JTable();
        customerTable.setBackground(new Color(175, 238, 238));
        customerTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        customerTable.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"STT", "Họ tên", "Email", "SĐT", "Ngày sinh", "Địa chỉ"}
        ));
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        int i = 1;
        for (Customer customer : customers) {
            model.addRow(new Object[] {i, customer.getFullname(), customer.getEmail(), 
            		customer.getPhoneNumber(), customer.getBirthday(), customer.getAddress()});
            i++;
        }
        JScrollPane scrollPane = new JScrollPane(customerTable);
        JFrame frame = new JFrame("Danh sách khách hàng");
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
    }
    
    public void thucHienOpenFile() {
        JFileChooser fs = new JFileChooser(new File("d:\\"));
        fs.setDialogTitle("Open a File");
        int result = fs.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fi = fs.getSelectedFile();
            loadFile(fi.getAbsolutePath());
            showCustomerTable(dataList);
        }
    }
}
