package GUI;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.toedter.calendar.JDateChooser;

import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import Entity.NhanVien;
import Entity.Role;
import Entity.TaiKhoan;


public class GUIThongTinNhanVien extends JFrame implements MouseListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComponent menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnHelp;
	private JTextField txthoTen;
	private JTextField txtcmnd;
	private JButton btnthem;
	private JButton btnsua;
	private JButton btnluu;
	private JButton btnhuy;
	private JTextField txttim;
	private JPanel Jpanel_2;
	private JRadioButton radnam;
	private JRadioButton radnu;
	private JLabel lblsDT;
	private JTextField txtsDT;
	private JLabel lbldiaChi;
	private JTextField txtdiaChi;
	private JPanel Jpanel;
	private JLabel lblngaySinh;
	private JDateChooser txtngaySinh;
	private DefaultTableModel datamodel; 
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JTextField txtemail;
	
	private JTextField textField;
	private JComboBox comboBox,comboBoxRole;
	private JTextField txtTaiThoan;
	private List<Role> list;
	private NhanVien mNhanVien;
	private TaiKhoan mTaiKhoan;
	private int idRole;
	private NhanVienDAO control;
	private TaiKhoanDAO taikhoanController;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GUIThongTinNhanVien(TaiKhoan taikhoan,NhanVien nhanvien) {
		control=new NhanVienDAO();
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		setTitle("Quản lí thông tin nhân viên");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		
		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.menu);
		setJMenuBar((JMenuBar) menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit ");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltitle = new JLabel("Thông Tin Nhân Viên");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lbltitle.setBounds(366, 11, 365, 45);
		contentPane.add(lbltitle);
		
		Jpanel_2 = new JPanel();
		Jpanel_2.setBackground(new Color(95, 158, 160));
		Jpanel_2.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		Jpanel_2.setBounds(10, 438, 1104, 163);
		contentPane.add(Jpanel_2);
		Jpanel_2.setLayout(null);
		
		btnhuy = new JButton("Quay Lại");
		btnhuy.setBackground(new Color(102, 205, 170));
		btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
		btnhuy.setBounds(924, 95, 155, 57);
		Jpanel_2.add(btnhuy);
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Họ tên", "Chứng minh", "Số điện thoại"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(705, 95, 155, 57);
		Jpanel_2.add(comboBox);
		
		txttim = new JTextField();
		txttim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String selection=(String)comboBox.getSelectedItem();
				if(selection.trim().equals("Họ tên"))
				{
					removeTable();
					String gioitinh="";
					List<NhanVien> listnv= control.searchName(txttim.getText().toString());
					for(NhanVien nv:listnv)
						if(String.valueOf(nv.getId())!=null)
					{
						if(nv.isGioiTinh()){
							gioitinh="Nam";
						}
						else
							gioitinh="Nữ";
						
						String[] rowdata = { String.valueOf(nv.getId()),nv.getTen(),gioitinh,nv.getSoDienThoai(),nv.getCmnd(),nv.getDiaChi(),control.doichuoitungay(nv.getNgaySinh()),nv.getEmail(),nv.getTaiKhoan().getRole().getName()};
						datamodel.addRow(rowdata);
					}	
				}
				if(selection.trim().equals("Chứng minh"))
					{
					removeTable();
					String gioitinh="";
					List<NhanVien> listnv= control.searchCMND(txttim.getText());
					for(NhanVien nv:listnv)
					if(String.valueOf(nv.getId())!=null)
					{
						if(nv.isGioiTinh()){
							gioitinh="Nam";
						}
						else
							gioitinh="Nữ";
						
						String[] rowdata = { String.valueOf(nv.getId()),nv.getTen(),gioitinh,nv.getSoDienThoai(),nv.getCmnd(),nv.getDiaChi(),control.doichuoitungay(nv.getNgaySinh()),nv.getEmail(),nv.getTaiKhoan().getRole().getName()};
						datamodel.addRow(rowdata);
					}
					}
				if(selection.trim().equals("Số điện thoại"))
					{
					removeTable();
					String gioitinh="";
					List<NhanVien> listnv= control.searchSDT(txttim.getText());
					for(NhanVien nv:listnv)
					if(String.valueOf(nv.getId()).toString()!=null)
					{
						if(nv.isGioiTinh()){
							gioitinh="Nam";
						}
						else
							gioitinh="Nữ";
						
						String[] rowdata = { String.valueOf(nv.getId()),nv.getTen(),gioitinh,nv.getSoDienThoai(),nv.getCmnd(),nv.getDiaChi(),control.doichuoitungay(nv.getNgaySinh()),nv.getEmail(),nv.getTaiKhoan().getRole().getName()};
						datamodel.addRow(rowdata);
					}
					}
			
			}
		});
		
				
		txttim.setBounds(20, 108, 646, 34);
		Jpanel_2.add(txttim);
		txttim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttim.setColumns(10);
				
		btnluu = new JButton("Lưu");
		btnluu.setBackground(new Color(102, 205, 170));
		btnluu.setIcon(new ImageIcon("luu.png"));
		btnluu.setBounds(380, 23, 155, 57);
		Jpanel_2.add(btnluu);
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
		btnsua = new JButton("Sửa");
		btnsua.setBackground(new Color(102, 205, 170));
		btnsua.setIcon(new ImageIcon("sua.png"));
		btnsua.setBounds(200, 23, 155, 57);
		Jpanel_2.add(btnsua);
		btnsua.setFont(new Font("Tahoma", Font.PLAIN, 14));
				
	
				
		btnthem = new JButton("Thêm");
		btnthem.setBackground(new Color(102, 205, 170));
		btnthem.setIcon(new ImageIcon("add.png"));
		btnthem.setBounds(20, 23, 155, 57);
		Jpanel_2.add(btnthem);
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		String[]headers = {"Mã","Họ tên","Giới tính",  "Số điện thoại", "Số chứng minh",  "Địa chỉ" ,"Ngày sinh","Email","Chức vụ"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(28, 291, 1060, 118);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		Jpanel = new JPanel();
		Jpanel.setBackground(new Color(95, 158, 160));
		Jpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel.setBounds(10, 51, 1104, 204);
		contentPane.add(Jpanel);
		Jpanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email :");

		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(33, 126, 86, 20);
		Jpanel.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtemail.setColumns(10);
		txtemail.setUI(new HintTextFieldUI("					Nhập email . VD: duyvien159@gmail.com", true, Color.GRAY));
		txtemail.setBounds(152, 126, 268, 20);
		Jpanel.add(txtemail);
		
	
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setColumns(10);
		textField.setVisible(false);
		textField.setBounds(152, 157, 268, 20);
		Jpanel.add(textField);
		
		lblsDT = new JLabel("Số điện thoại:");
		lblsDT.setBounds(33, 95, 86, 20);
		Jpanel.add(lblsDT);
		lblsDT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtsDT = new JTextField();
		txtsDT.setBounds(152, 95, 268, 20);
		Jpanel.add(txtsDT);
		txtsDT.setUI(new HintTextFieldUI("					Nhập số điện thoại khách hàng. VD: 0399972888", true, Color.GRAY));
		txtsDT.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtsDT.setColumns(10);
		
		JLabel lblgioiTinh = new JLabel("Giới tính:");
		lblgioiTinh.setBounds(33, 61, 86, 20);
		Jpanel.add(lblgioiTinh);
		lblgioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblhoTen = new JLabel("Họ tên: ");
		lblhoTen.setBounds(33, 31, 86, 20);
		Jpanel.add(lblhoTen);
		lblhoTen.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txthoTen = new JTextField();
		txthoTen.setBounds(152, 32, 268, 20);
		Jpanel.add(txthoTen);
		txthoTen.setUI(new HintTextFieldUI("					Nhập họ tên khách hàng. VD: Nguyễn Văn B", true, Color.GRAY));
		txthoTen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txthoTen.setColumns(10);
		
		
		radnam = new JRadioButton("Nam");
		radnam.setBounds(152, 61, 59, 23);
		Jpanel.add(radnam);
		radnam.setBackground(SystemColor.inactiveCaptionBorder);
		radnam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radnam.isSelected())
				{
					radnu.setSelected(false);
				}
				
			}
		});
		radnam.setFont(new Font("Tahoma", Font.PLAIN, 11));
		radnam.setSelected(true);
		
		
		
		radnu = new JRadioButton("Nữ");
		radnu.setBounds(228, 61, 70, 23);
		Jpanel.add(radnu);
		radnu.setBackground(SystemColor.inactiveCaptionBorder);
		radnu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radnu.isSelected())
				{
					radnam.setSelected(false);
				}
				
			}
		});
		radnu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblcmnd = new JLabel("Chứng minh nhân dân:");
		lblcmnd.setBounds(621, 35, 135, 16);
		Jpanel.add(lblcmnd);
		lblcmnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblngaySinh = new JLabel("Ngày sinh:");
		lblngaySinh.setBounds(621, 95, 86, 20);
		Jpanel.add(lblngaySinh);
		lblngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtngaySinh = new JDateChooser();
		txtngaySinh.setBounds(768, 95, 268, 20);
		Jpanel.add(txtngaySinh);
		txtngaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaySinh.setDateFormatString("yyyy-MM-dd");
		
		txtdiaChi = new JTextField();
		txtdiaChi.setBounds(768, 65, 268, 20);
		Jpanel.add(txtdiaChi);
		txtdiaChi.setUI(new HintTextFieldUI("					Nhập địa chỉ khách hàng. VD: Bình Thuận", true, Color.GRAY));
		txtdiaChi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtdiaChi.setColumns(10);
		
		txtcmnd = new JTextField();
		txtcmnd.setBounds(768, 35, 268, 20);
		Jpanel.add(txtcmnd);
		txtcmnd.setUI(new HintTextFieldUI("					Nhập số chứng minh nhân dân. VD: 261464277", true, Color.GRAY));
		txtcmnd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtcmnd.setColumns(10);
		
		lbldiaChi = new JLabel("Địa chỉ:");
		lbldiaChi.setBounds(621, 65, 86, 20);
		Jpanel.add(lbldiaChi);
		lbldiaChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblcmnd_1 = new JLabel("Tài Khoản :");
		lblcmnd_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcmnd_1.setBounds(621, 126, 135, 16);
		Jpanel.add(lblcmnd_1);
		
		txtTaiThoan = new JTextField();
		txtTaiThoan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTaiThoan.setColumns(10);
		txtTaiThoan.setBounds(768, 126, 268, 20);
		Jpanel.add(txtTaiThoan);
		
		JLabel lblrole = new JLabel("Quyền :");
		lblrole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblrole.setBounds(621, 157, 135, 16);
		Jpanel.add(lblrole);
		
		 comboBoxRole = new JComboBox();
		
		
		TaiKhoanDAO control=new TaiKhoanDAO();
		list= new ArrayList<Role>();
		try {
			list=control.GetAllRole();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel();
	    for(int i=0;i<list.size()-1;i++)
	    	model.addElement(list.get(i).getName());
	    comboBoxRole.setModel(model);
	    
		comboBoxRole.setBounds(768, 157, 268, 20);
		Jpanel.add(comboBoxRole);
		
		radnu.addActionListener(this);
		radnam.addActionListener(this);
		
		panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBorder(new TitledBorder(null, "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 266, 1106, 161);
		contentPane.add(panel);
		panel.setLayout(null);
		table.addMouseListener(this);
		
		btnsua.addActionListener(this);
		btnluu.addActionListener(this);
		btnthem.addActionListener(this);
		btnhuy.addActionListener(this);
		
		SetEnableEditText(false);
		
		btnluu.setEnabled(false);
		btnsua.setEnabled(false);
		comboBoxRole.setEnabled(false);
		
		updateTableData();
		
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		NhanVienDAO nhanVienController=new NhanVienDAO();
		Object o=e.getSource();
		if(o==btnhuy)
		{
			dispose();
			GUIChucNang ft= new GUIChucNang(mTaiKhoan,mNhanVien);
			ft.setVisible(true);
		}
		else if (o==btnthem)
		{
			btnluu.setEnabled(true);
			if(btnthem.getText().equals("Thêm"))
			{
				comboBoxRole.setEnabled(true);
				SetEnableEditText(true);
				btnsua.setEnabled(false);
				btnthem.setIcon(new ImageIcon("xoa.png"));
				btnthem.setText("Huỷ");
			}
		else
				
			{
				SetEnableEditText(false);
				comboBoxRole.setEnabled(false);
				btnluu.setEnabled(false);
				xoarong();
				btnthem.setIcon(new ImageIcon("add.png"));
				btnthem.setText("Thêm");				
			}
			if(btnsua.getText().equals("Xong"))
			{
				btnsua.setText("Sửa");
				btnsua.setEnabled(false);
			}
			
		}
		else if (o.equals(btnluu))
			
		{
			if(CheckThongTin())
			{
				int responseCode=0;	
				NhanVien nv=new NhanVien();
				AddNhanVien(nv);
				TaiKhoan tk=new TaiKhoan();
				tk.setUsername(txtTaiThoan.getText());
				tk.setPassword("123456");
				taikhoanController=new TaiKhoanDAO();
				
				tk.setRole(list.get((comboBoxRole.getSelectedIndex())));
					
				if(!(control.KiemTraTaiKhoan(tk)))
				{
					try {
						responseCode=taikhoanController.POSTTaiKhoan(tk);
						if(responseCode==200)
						{
							try {
								nv.setTaiKhoan(tk);
								responseCode=nhanVienController.POSTNhanVien(nv);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(responseCode==200)
							{
								
								SetEnableEditText(false);
								
								btnthem.setEnabled(true);
								btnthem.setIcon(new ImageIcon("add.png"));
								btnthem.setText("Thêm");
								btnluu.setEnabled(false);
								
								removeTable();
								updateTableData();
								
								JOptionPane.showMessageDialog(table,"Bạn vừa thêm mới thông tin 1 nhân viên !","Chú ý",JOptionPane.CLOSED_OPTION);
								
							}
							else
							{
								try {
									int ketqua=taikhoanController.DeleteTaiKhoan(txtTaiThoan.getText());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(table,"Bạn vừa thêm thất bại nhân viên !","Chú ý",JOptionPane.CLOSED_OPTION);
								
							}
						}
						else 
							JOptionPane.showMessageDialog(table,"Bạn vừa thêm thất bại tài khoản !","Chú ý",JOptionPane.CLOSED_OPTION);
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(table,"Tài khoản bạn tạo bị trùng !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtTaiThoan.requestFocus();
				}
				
			}
			
			
		}
		
		else if(o.equals(btnsua))
		{
			if	(btnsua.getText().equals("Sửa"))
			{	
				comboBoxRole.setEnabled(false);
				SetEnableEditText(true);
				btnsua.setText("Xong");
				txtTaiThoan.setEnabled(false);
				btnthem.setEnabled(true);
				btnthem.setText("Hủy");
				btnthem.setIcon(new ImageIcon("xoa.png"));
				btnluu.setEnabled(false);
			}
			else
			{
					if(CheckThongTin()) {
						int code=0;
						
					
						boolean gioitinh = true;
						if (radnu.isSelected()) gioitinh= false;
						NhanVien nv=new NhanVien();
						AddNhanVien(nv);
						int row = table.getSelectedRow();
						nv.setId(Long.parseLong(datamodel.getValueAt(row, 0).toString()));
						if(row>=0){
							try {
								code = nhanVienController.PUTNhanVien(nv);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(code==200)
						{
							btnsua.setText("Sửa");
							SetEnableEditText(false);
							
							comboBoxRole.setEnabled(false);
							btnsua.setEnabled(false);
							btnthem.setEnabled(true);
							btnthem.setIcon(new ImageIcon("add.png"));
							btnthem.setText("Thêm");
							xoarong();
							
							removeTable();
							updateTableData();
							
							JOptionPane.showMessageDialog(table,"Bạn vừa cập nhật thông tin 1 nhân viên !","Chú ý",JOptionPane.CLOSED_OPTION);
							
						}
					}
					
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		btnsua.setEnabled(true);
		comboBoxRole.setEnabled(false);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		NhanVienDAO control=new NhanVienDAO();
		
		try {
			date = df.parse(table.getValueAt(row, 6).toString());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textField.setText(table.getValueAt(row, 0).toString());
		txthoTen.setText(table.getValueAt(row, 1).toString());
		if(table.getValueAt(row, 2).toString().equals("Nam"))
		{
			radnam.setSelected(true);
			radnu.setSelected(false);
		}
		else
		{
			radnam.setSelected(false);
			radnu.setSelected(true);
		}
		
		txtsDT.setText(table.getValueAt(row, 3).toString());
		txtcmnd.setText(table.getValueAt(row, 4).toString());
		txtdiaChi.setText(table.getValueAt(row, 5).toString());
		txtngaySinh.setDate(date );
		txtemail.setText(table.getValueAt(row, 7).toString());
		if(table.getValueAt(row, 8).toString().equalsIgnoreCase("Bác Sỹ"))
			comboBoxRole.setSelectedIndex(1);
		if(table.getValueAt(row, 8).toString().equalsIgnoreCase("Quản Lý"))
			comboBoxRole.setSelectedIndex(0);
		if(table.getValueAt(row, 8).toString().equalsIgnoreCase("Nhân Viên Tiếp Tân"))
			comboBoxRole.setSelectedIndex(2);
		if(table.getValueAt(row, 8).toString().equalsIgnoreCase("Nhân Viên Quầy Thuốc"))
			comboBoxRole.setSelectedIndex(3);
		if(table.getValueAt(row, 8).toString().equalsIgnoreCase("Bệnh Nhân"))
			comboBoxRole.setSelectedIndex(4);
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateTableData() 
	{
		ArrayList<NhanVien>list=new ArrayList<>();
		try {
			list.addAll(control.GetAllNhanVien());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()!=0)
		{
			for (NhanVien nv : list) {
				
				String gioitinh="";
				if(nv.isGioiTinh()){
					gioitinh="Nam";
				}
				else
					gioitinh="Nữ";
				String[] rowdata = {String.valueOf(nv.getId()).toString(),nv.getTen(),gioitinh,nv.getSoDienThoai(),nv.getCmnd(),nv.getDiaChi(),control.doichuoitungay(nv.getNgaySinh()),nv.getEmail(),nv.getTaiKhoan().getRole().getName()};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}

	private void SetEnableEditText(boolean trangthai) {
		
		if(!trangthai)
		{
			
			
			txthoTen.setEditable(false);
			radnam.setEnabled(false);
			radnu.setEnabled(false);
			txtsDT.setEditable(false);
			txtcmnd.setEditable(false);
			txtdiaChi.setEditable(false);
			txtngaySinh.setEnabled(false);
			txtemail.setEnabled(false);
			txtTaiThoan.setEnabled(false);
			
			txthoTen.setBackground(SystemColor.info);
			txtcmnd.setBackground(SystemColor.info);
			txtsDT.setBackground(SystemColor.info);
			txtdiaChi.setBackground(SystemColor.info);
			txtemail.setBackground(SystemColor.info);
			txtTaiThoan.setBackground(SystemColor.info);
			
		}
		else
		{
			txthoTen.setEditable(true);
			radnam.setEnabled(true);
			radnu.setEnabled(true);
			txtsDT.setEditable(true);
			txtcmnd.setEditable(true);
			txtdiaChi.setEditable(true);
			txtngaySinh.setEnabled(true);
			txtemail.setEnabled(true);
			txtTaiThoan.setEnabled(true);
			
			txthoTen.setBackground(SystemColor.text);
			txtcmnd.setBackground(SystemColor.text);
			txtsDT.setBackground(SystemColor.text);
			txtdiaChi.setBackground(SystemColor.text);
			txtemail.setBackground(SystemColor.text);
			txtTaiThoan.setBackground(SystemColor.text);
		}
		
	}
	public void xoarong() {
		txthoTen.setText("");
		radnam.setSelected(true);
		radnu.setSelected(false);
		txtsDT.setText("");
		txtcmnd.setText("");
		txtdiaChi.setText("");
		txtngaySinh.setDate(null);
		txtemail.setText("");
		txtTaiThoan.setText("");
	}
	
	public boolean CheckThongTin() {
		if(txthoTen.getText().equals("")) 
			{
				JOptionPane.showConfirmDialog(this, "Họ tên bạn nhập vào đã trống. Mời nhập lại !","Chú ý",JOptionPane.CLOSED_OPTION);
				txthoTen.requestFocus();
				return false;
			}
		if(!radnam.isSelected()&&!radnu.isSelected())
		{
			JOptionPane.showConfirmDialog(this, "Bạn chưa chọn giới tính !","Chú ý",JOptionPane.CLOSED_OPTION);
			txthoTen.requestFocus();
			return false;
		}
			
		if(txtsDT.getText().equals("")) 
			{
				JOptionPane.showConfirmDialog(this, "Bạn chưa nhập số điện thoại !","Chú ý",JOptionPane.CLOSED_OPTION);
				txtsDT.requestFocus();
				return false;
			}
		if(txtcmnd.getText().equals("")) 
		{
			JOptionPane.showConfirmDialog(this, "Bạn chưa nhập số chứng minh nhan dân !","Chú ý",JOptionPane.CLOSED_OPTION);
			txtcmnd.requestFocus();
			return false;
		}
		if(txtdiaChi.getText().equals("")) {
			JOptionPane.showConfirmDialog(this, "Bạn chưa nhập địa chỉ !","Chú ý",JOptionPane.CLOSED_OPTION);
			txtdiaChi.requestFocus();
			return false;
		}
		if(txtngaySinh.getDate()==null) {
			JOptionPane.showConfirmDialog(this, "Bạn chưa chọn ngày sinh !","Chú ý",JOptionPane.CLOSED_OPTION);
			txtngaySinh.requestFocus();
			return false;
		
		}
		// thời gian hiện hành
		Date date1 = new Date();
		if(txtngaySinh.getDate().compareTo(date1)>0) {
			JOptionPane.showConfirmDialog(this, "Ngày sinh của bạn nhập đã lớn hơn ngày hiện hành. Xin chọn lại ngày sinh !","Chú ý",JOptionPane.CLOSED_OPTION);
			txtngaySinh.requestFocus();
			return false;
		
		}
		
		
			try 
			{	
				if(Long.parseLong(txtsDT.getText())<0){
					JOptionPane.showMessageDialog(table,"Số điện thoại bạn vừa nhập là số âm. Vui lòng nhập lại !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtsDT.requestFocus();
					txtsDT.selectAll();
					return false;}
				if(!control.Checksdt(txtsDT.getText())) {
					JOptionPane.showConfirmDialog(this, "Số điện thoại bạn vừa nhập có ký tự không phải là số hoặc không phải 10 chữ số ! ","Chú ý",JOptionPane.CLOSED_OPTION);
					txtsDT.requestFocus();
					txtsDT.selectAll();
				return false;
			}
			} catch (Exception e) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(table,"Số điện thoại bạn nhập không hợp lệ !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtsDT.requestFocus();
					txtsDT.selectAll();
					return false;
				}
			try 
			{	
				if(Long.parseLong(txtcmnd.getText())<0){
					JOptionPane.showMessageDialog(table,"Số Chứng minh không được âm. Vui lòng nhập lại !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtcmnd.requestFocus();
					txtcmnd.selectAll();
					return false;}
				if(!control.Checkcmnd(txtcmnd.getText())) {
					JOptionPane.showConfirmDialog(this, "Số Chứng minh nhập vào có ký tự không phải là số hoặc không phải 9 chữ số. Vui lòng nhập lại số chứng minh ! ","Chú ý",JOptionPane.CLOSED_OPTION);
					txtcmnd.requestFocus();
					txtcmnd.selectAll();
				return false;
			}
			} catch (Exception e) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(table,"Số Chứng minh bạn nhập không hợp lệ !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtcmnd.requestFocus();
					txtcmnd.selectAll();
					return false;
				}
			try 
			{	
				if(!control.Checkemail(txtemail.getText())) {
					JOptionPane.showConfirmDialog(this, "Bạn vừa nhập email không hợp lệ. Email có dạng : anystring@anystring.anystring ! ","Chú ý",JOptionPane.CLOSED_OPTION);
					txtcmnd.requestFocus();
					txtcmnd.selectAll();
				return false;
			}
			} catch (Exception e) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(table,"email bạn nhập không hợp lệ !","Chú ý",JOptionPane.CLOSED_OPTION);
					txtemail.requestFocus();
					txtemail.selectAll();
					return false;
				}
		return true;	
		
	}
	
	
	public void AddNhanVien(NhanVien nv) {
				
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(txtngaySinh.getDate());
		boolean gioitinh =true ;
		if (radnam.isSelected()) gioitinh = true;
		if (radnu.isSelected()) gioitinh= false;
		
		nv.setTen(txthoTen.getText());
		nv.setDiaChi(txtdiaChi.getText());
		nv.setGioiTinh(gioitinh);
		nv.setNgaySinh(control.doingaytuchuoi(strDate));
		nv.setSoDienThoai(txtsDT.getText());
		nv.setEmail(txtemail.getText());
		nv.setCmnd(txtcmnd.getText());
	}
	
	
}
