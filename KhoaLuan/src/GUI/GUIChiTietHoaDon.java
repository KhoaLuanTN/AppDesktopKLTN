package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.JButton;

public class GUIChiTietHoaDon extends JFrame {

	private JPanel contentPane,Jpanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	private DefaultTableModel datamodel1,datamodel2; 
	private JScrollPane scrollPane1,scrollPane2;
	private JTable table1,table2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIChiTietHoaDon frame = new GUIChiTietHoaDon();
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
	public GUIChiTietHoaDon() {
		setTitle("Chi Tiết Hóa Đơn");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 648);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltitle = new JLabel("Chi Tiết Hóa Đơn");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbltitle.setBounds(249, 11, 354, 48);
		contentPane.add(lbltitle);
		
		Jpanel = new JPanel();
		Jpanel.setBackground(new Color(95, 158, 160));
		Jpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel.setBounds(10, 64, 1076, 538);
		contentPane.add(Jpanel);
		Jpanel.setLayout(null);
		
		JLabel lblhoTen = new JLabel("Họ tên: ");
		lblhoTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblhoTen.setBounds(25, 35, 86, 20);
		Jpanel.add(lblhoTen);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(SystemColor.info);
		textField.setBounds(167, 30, 344, 30);
		Jpanel.add(textField);
		
		JLabel lblsDT = new JLabel("Số điện thoại:");
		lblsDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblsDT.setBounds(548, 30, 86, 31);
		Jpanel.add(lblsDT);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(SystemColor.info);
		textField_1.setBounds(689, 30, 344, 30);
		Jpanel.add(textField_1);
		
		JLabel lblcmnd = new JLabel("Chứng minh nhân dân:");
		lblcmnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblcmnd.setBounds(26, 72, 135, 30);
		Jpanel.add(lblcmnd);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(SystemColor.info);
		textField_2.setBounds(167, 72, 344, 30);
		Jpanel.add(textField_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(10, 113, 1056, 325);
		Jpanel.add(panel_2_1);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new TitledBorder(null, "Chi tiết các khoản thu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2_1.setBackground(new Color(95, 158, 160));

		String[]header = {"Mã","Tên thuốc","Số lượng", "Hướng dẫn sử dụng","Giá tiền"};
		datamodel1 = new DefaultTableModel(header,0);
		panel_2_1.add(scrollPane1= new JScrollPane(table1 = new JTable(datamodel1)));
		scrollPane1.setBounds(10, 26, 1036, 124);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane1.setBackground(SystemColor.scrollbar);
		
		String[]header2 = {"Mã","Ghi chú","Kết luận","Ngày tạo","Tên dịch vu","Giá tiền"};
		datamodel2 = new DefaultTableModel(header2,0);
		panel_2_1.add(scrollPane2= new JScrollPane(table2 = new JTable(datamodel2)));
		
		JButton btnluu = new JButton("Thanh Toán");
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnluu.setBackground(new Color(102, 205, 170));
		btnluu.setBounds(671, 459, 155, 57);
		Jpanel.add(btnluu);
		
		JButton btnhuy = new JButton("Quay Lại");
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnhuy.setBackground(new Color(102, 205, 170));
		btnhuy.setBounds(896, 459, 155, 57);
		Jpanel.add(btnhuy);
		scrollPane2.setBounds(10, 176, 1036, 135);
		table2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane2.setBackground(SystemColor.scrollbar);
		
	
		
	}
}
