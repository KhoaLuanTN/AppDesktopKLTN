package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import Entity.NhanVien;
import Entity.TaiKhoan;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class GUIDangNhap extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField txttaiKhoan;
	private JPasswordField txtmatKhau;
	private JButton btnThoat;
	private JButton btndangNhap;
	private TaiKhoanDAO taiKhoanService;
	private NhanVienDAO nhanVienService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDangNhap frame = new GUIDangNhap();
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
	public GUIDangNhap() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Quản Lý Phòng Khám");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.PNG"));
		setBounds(100, 100, 548, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 250, 205));
		
		panel = new JPanel();
		panel.setBounds(0, 0, 532, 311);
		contentPane.add(panel);
		panel.setLayout(null);

		
		JLabel label = new JLabel("Đăng Nhập Hệ Thống");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 32));
		label.setBounds(96, 42, 369, 32);
		panel.add(label);
		
		JLabel lbltaiKhoan = new JLabel("Tài Khoản:");
		lbltaiKhoan.setHorizontalAlignment(SwingConstants.CENTER);
		lbltaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltaiKhoan.setBounds(52, 103, 135, 32);
		
		panel.add(lbltaiKhoan);
		
		txttaiKhoan = new JTextField();
		txttaiKhoan.setColumns(10);
		txttaiKhoan.setToolTipText("Nhập tài khoản");
		txttaiKhoan.setBounds(166, 103, 293, 32);
		panel.add(txttaiKhoan);
		
		txtmatKhau = new JPasswordField();
		txtmatKhau.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == e.VK_ENTER){
						Dangnhap();
					}
			
			}
		});
		txtmatKhau.setToolTipText("Nhập mật khẩu");
		txtmatKhau.setBounds(166, 159, 293, 32);
		panel.add(txtmatKhau);
		
		JLabel lblmatKhau = new JLabel("Mật Khẩu:");
		lblmatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblmatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmatKhau.setBounds(52, 159, 135, 32);

		panel.add(lblmatKhau);
		
		btndangNhap = new JButton("Đăng Nhập");
		btndangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dangnhap();
			}
		});
		btndangNhap.setIcon(new ImageIcon("Login-in-icon.png"));
		btndangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndangNhap.setBounds(84, 215, 164, 41);
		panel.add(btndangNhap);
		
		btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnThoat.setIcon(new ImageIcon("Login-out-icon.png"));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(317, 215, 164, 41);
		panel.add(btnThoat);
		
		taiKhoanService=new TaiKhoanDAO();
		nhanVienService=new NhanVienDAO();
	}
	@SuppressWarnings("unlikely-arg-type")
	private void Dangnhap() {
		TaiKhoan taiKhoan=new TaiKhoan();
		NhanVien nhanvien=new NhanVien();
		
		if((txttaiKhoan.getText().equals("")))
			JOptionPane.showConfirmDialog(this,	 "Tài khoản không được trống !!","Chú ý",JOptionPane.YES_OPTION);
		else if(txtmatKhau.equals(""))
			JOptionPane.showConfirmDialog(this,	 "Mật khẩu không được trống !!","Chú ý",JOptionPane.YES_OPTION);
		else if(Check())
		{
			int i=0;
			char[] pass = txtmatKhau.getPassword();
			String password = String.copyValueOf(pass);
			List<TaiKhoan>list=new ArrayList<>();
			
			try {
				list.addAll(taiKhoanService.GetAllTaiKhoan());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(TaiKhoan tk : list)
			{
				if (tk.getUsername().equalsIgnoreCase(txttaiKhoan.getText()))
					if(tk.getPassword().equals(password))
				{
					i++;
					try {
						taiKhoan =taiKhoanService.GetOneTaiKhoan(tk.getUsername());
						nhanvien=nhanVienService.GetOneNhanVien(tk.getUsername());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(i==0)
			{
				JOptionPane.showConfirmDialog(this	,	 "Sai tài khoản mật khẩu","Chú ý",JOptionPane.CLOSED_OPTION);
				txttaiKhoan.requestFocus();
			}
			else if(taiKhoan.getRole().getName().equals("Bệnh Nhân"))
			{
				JOptionPane.showConfirmDialog(this	,	 "Bạn không phải là nhân viên phòng khám. Vui lòng đăng nhập lại !","Chú ý",JOptionPane.CLOSED_OPTION);
				txttaiKhoan.requestFocus();
			}else
			{
				dispose();
				GUIChucNang ft= new GUIChucNang(taiKhoan,nhanvien);
				ft.setVisible(true);
			}
		}
	}

	boolean checkmk()
	{
		char[] pass = txtmatKhau.getPassword();
		String password = String.copyValueOf(pass);
		Pattern passwordCheck = Pattern.compile("[0-9]+");
		if(!passwordCheck.matcher(password).matches())
			return false;
		
		return true;
		
	}
	
	
	
	boolean Check()
	{	
			if(!checkmk()){
				JOptionPane.showMessageDialog(this,"Mật khẩu phải là số  !!","Chú ý",JOptionPane.CLOSED_OPTION);
				txtmatKhau.requestFocus();
				txtmatKhau.selectAll();
				return false;}

		return true;
	}
		
	
	
		
}
