package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.BenhNhanDAO;
import DAO.PhieuDichVuDAO;
import DAO.PhieuKhamDAO;
import Entity.BenhNhan;
import Entity.DichVu;
import Entity.NhanVien;
import Entity.PhieuDichVu;
import Entity.PhieuKhambenh;
import Entity.TaiKhoan;

public class GUIPhieuDichVu extends JFrame implements MouseListener,ActionListener{

	private JPanel contentPane;
	private JComponent menuBar;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnHelp;
	 
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private BenhNhan mBenhNhan;
	private PhieuKhamDAO phieuKhamService;
	private PhieuDichVuDAO phieudichvuService;
	private BenhNhanDAO benhnhanservice;
	
	private JPanel Jpanel_1;
	private JLabel lbldiaChiBN;
	private JTextField txtdiaChiBN;
	
	private JTextField txthoTenBN;
	private JLabel lblmaBN;
	private JButton btnhuy,btnthem;
	private JButton btnluu;
	private JComboBox comboBox,comboBox_1;
	
	private DefaultTableModel datamodel; 
	private JScrollPane scrollPane;
	private JTable table;

	private List<String> listBN;
	private List<DichVu> listDV;
	private List<PhieuKhambenh> listpk;
	private PhieuKhambenh pkb;
 
	private JPanel panel;
	private JTextField textField;
	private JTextArea tatketqua,tatghichu;
	/**
	 * Create the frame.
	 */
	public GUIPhieuDichVu(TaiKhoan taikhoan,NhanVien nhanvien) {
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		this.benhnhanservice=new BenhNhanDAO();
		this.phieuKhamService=new PhieuKhamDAO();
		this.phieudichvuService=new PhieuDichVuDAO();
		this.pkb=null;
		
		setTitle("Phiếu khám bệnh");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmngXut = new JMenuItem("Đăng Xuất");
		mnFile.add(mntmngXut);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("Phiếu Dịch Vụ");
		lblHpngBn.setBounds(304, 0, 436, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		
		
		Jpanel_1 = new JPanel();
		Jpanel_1.setBounds(28, 59, 1104, 93);
		contentPane.add(Jpanel_1);
		Jpanel_1.setBackground(new Color(95, 158, 160));
		Jpanel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin bệnh nhân", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel_1.setLayout(null);
				
				JLabel lblhoTenBN = new JLabel("Họ tên: ");
				lblhoTenBN.setBounds(33, 60, 86, 20);
				Jpanel_1.add(lblhoTenBN);
				lblhoTenBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				txthoTenBN = new JTextField();
				txthoTenBN.setBounds(152, 61, 268, 20);
				txthoTenBN.setEnabled(false);
				Jpanel_1.add(txthoTenBN);
				txthoTenBN.setUI(new HintTextFieldUI("					Nhập họ tên bệnh nhân. VD: Nguyễn Văn B", true, Color.GRAY));
				txthoTenBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txthoTenBN.setColumns(10);
				
				txtdiaChiBN = new JTextField();
				txtdiaChiBN.setBounds(742, 61, 268, 20);
				Jpanel_1.add(txtdiaChiBN);
				txtdiaChiBN.setUI(new HintTextFieldUI("					Nhập địa chỉ khách hàng. VD: Bình Thuận", true, Color.GRAY));
				txtdiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtdiaChiBN.setColumns(10);
				txtdiaChiBN.setEnabled(false);
				
				lbldiaChiBN = new JLabel("Địa chỉ:");
				lbldiaChiBN.setBounds(623, 60, 86, 20);
				Jpanel_1.add(lbldiaChiBN);
				lbldiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				lblmaBN = new JLabel("Mã bệnh nhân :");
				lblmaBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblmaBN.setBounds(33, 30, 86, 20);
				Jpanel_1.add(lblmaBN);
				
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date today = Calendar.getInstance().getTime();       
				String todayAsString = df.format(today);
				
				try {
					 listBN= benhnhanservice.GetBenhNhanByPKChuaHoanThanh(todayAsString);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 comboBox = new JComboBox();
				
				for(int i=0;i<listBN.size();i++)
					comboBox.addItem(listBN.get(i));
				comboBox.setSelectedItem(null);
				comboBox.setBounds(152, 30, 268, 20);
				Jpanel_1.add(comboBox);
				
				JLabel lblsdT = new JLabel("Số điện thoại:");
				lblsdT.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblsdT.setBounds(623, 30, 86, 20);
				Jpanel_1.add(lblsdT);
				
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
				textField.setEnabled(false);
				textField.setColumns(10);
				textField.setBounds(742, 31, 268, 20);
				Jpanel_1.add(textField);
				
				
				
				
				String[]headers = {"Mã","Triệu chứng","Ngày khám", "Người khám","Chẩn Đoán","Trạng thái"};
				datamodel = new DefaultTableModel(headers,0);
				contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
				scrollPane.setBounds(51, 185, 1060, 109);
				table.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane.setBackground(SystemColor.scrollbar);
				
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(null, "Phiếu dịch vụ ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBackground(new Color(95, 158, 160));
				panel_1.setBounds(28, 316, 1106, 131);
				contentPane.add(panel_1);
				
				listpk=new ArrayList<>();
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						try {
							mBenhNhan=benhnhanservice.GetOneBenhNhan(Long.parseLong( comboBox.getSelectedItem().toString()));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
								txthoTenBN.setText(mBenhNhan.getTen());
								txtdiaChiBN.setText(mBenhNhan.getDiaChi());
								textField.setText(mBenhNhan.getSoDienThoai());
								removeTable();
								updateTableData();
					}
				});
				
				
				JLabel lblNewLabel_1 = new JLabel("Kết quả  :");
				lblNewLabel_1.setBounds(37, 76, 109, 19);
				panel_1.add(lblNewLabel_1);
				
				tatketqua = new JTextArea();
				tatketqua.setBounds(213, 73, 837, 46);
				panel_1.add(tatketqua);
				
				btnhuy = new JButton("Quay Lại");
				btnhuy.setBackground(new Color(102, 205, 170));
				btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
				btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnhuy.setBounds(977, 564, 155, 57);
				contentPane.add(btnhuy);
				
				btnluu = new JButton("Lưu");
				btnluu.setBackground(new Color(102, 205, 170));
				btnluu.setIcon(new ImageIcon("luu.png"));
				btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnluu.setBounds(767, 564, 155, 57);
				contentPane.add(btnluu);
				
				btnthem = new JButton("Thêm");
				btnthem.setBackground(new Color(102, 205, 170));
				btnthem.setIcon(new ImageIcon("sua.png"));
				btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnthem.setBounds(53, 564, 155, 57);
				contentPane.add(btnthem);
				
			comboBox.setEnabled(false);
			tatketqua.setEnabled(false);
			
			JLabel lblmaBN_1 = new JLabel("Chọn kiểu dịch vụ :");
			lblmaBN_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblmaBN_1.setBounds(35, 24, 143, 20);
			panel_1.add(lblmaBN_1);
			
			comboBox_1 = new JComboBox();
			listDV=new ArrayList<DichVu>();
			try {
				listDV=phieudichvuService.GetAllDichVu();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0;i<listDV.size();i++)
				comboBox_1.addItem(listDV.get(i).getGhiChu());
		
			comboBox_1.setEnabled(false);
			comboBox_1.setBounds(211, 24, 179, 20);
			panel_1.add(comboBox_1);
			
			tatghichu = new JTextArea();
			tatghichu.setEnabled(false);
			tatghichu.setBounds(529, 24, 536, 38);
			panel_1.add(tatghichu);
			
			JLabel lblNewLabel_1_1 = new JLabel("Ghi chú  :");
			lblNewLabel_1_1.setBounds(440, 26, 81, 19);
			panel_1.add(lblNewLabel_1_1);
			btnluu.setEnabled(false);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(28, 163, 1104, 147);
			panel_2.setLayout(null);
			panel_2.setBorder(new TitledBorder(null, "Danh Sách Phiếu khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBackground(new Color(95, 158, 160));
			contentPane.add(panel_2);
				
			btnhuy.addActionListener(this);
			btnluu.addActionListener(this);
			btnthem.addActionListener(this);
			table.addMouseListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		int ketquaPOST=0;
		if(o==btnhuy)
		{
			dispose();
			GUIChucNang ft= new GUIChucNang(mTaiKhoan,mNhanVien);
			ft.setVisible(true);
		}else if(o==btnthem)
		{
			if(btnthem.getText().equals("Thêm"))
			{
				comboBox.setEnabled(true);
				comboBox_1.setEnabled(true);
				tatketqua.setEnabled(true);
				tatghichu.setEnabled(true);
				tatketqua.setEnabled(true);
				btnthem.setText("Hủy");
			}
			else 
			{
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				tatketqua.setEnabled(false);
				tatghichu.setEnabled(false);
	
				btnthem.setText("Thêm");
				btnluu.setEnabled(false);
			}
		}
		else if(o==btnluu) {
			PhieuDichVu pdv = new PhieuDichVu();
			DichVu dv = new DichVu();
			pdv.setGhiChu(tatghichu.getText());
			pdv.setKetLuan(tatketqua.getText());
			pdv.setNgayTaoPhieu(java.util.Calendar.getInstance().getTime());
			
			for(int i=0;i<listDV.size();i++)
			{
				if(comboBox_1.getSelectedItem().toString().equals(listDV.get(i).getGhiChu()))
				{
					try {
						dv=phieudichvuService.GetOneDichVu(listDV.get(i).getId());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
				
			pdv.setDichVu(dv);
			pdv.setGiaTienDV((float) dv.getDonGia());
			try {
				pdv.setPhieukhambenh(phieuKhamService.GetOnePhieuKham(pkb.getId()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(!pdv.getPhieukhambenh().isTrangThai()) {
				try {
					ketquaPOST= phieudichvuService.POSTPhieuDichVu(pdv);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(ketquaPOST==200)
				{
					JOptionPane.showMessageDialog(this,"Bạn vừa lưu 1 phiếu dịch vụ !","Chú ý",JOptionPane.CLOSED_OPTION);
					
					comboBox.setEnabled(false);
					tatghichu.setEnabled(false);
					tatketqua.setEnabled(false);
					comboBox_1.setEnabled(false);
					
					btnthem.setText("Thêm");
					btnluu.setEnabled(false);
					
					removeTable();
					updateTableData();
				}
			}else
			{
				JOptionPane.showMessageDialog(this,"Phiếu khám bạn chọn đã hoàn thành. Xin chọn lại !","Chú ý",JOptionPane.CLOSED_OPTION);
				btnluu.setEnabled(false);
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		btnluu.setEnabled(true);
		
		try {
			pkb=phieuKhamService.GetOnePhieuKham(Long.parseLong(table.getValueAt(row, 0).toString()));
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		// TODO Auto-generated method stub
		ArrayList<PhieuKhambenh>list=new ArrayList<>();
		if(list!=null)
		{
			try {
				list.addAll(phieuKhamService.GetAllPhieuKhamByBenhNhanIDANDDate(Long.parseLong(comboBox.getSelectedItem().toString())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(list.size()!=0) {
				for (PhieuKhambenh pk : list) {
					String trangthai=null;
					if(pk.isTrangThai())
						trangthai="Hoàn thành";
					else
						trangthai="Chưa hoàn thành";
					String[] rowdata = { String.valueOf(pk.getId()),pk.getTrieuChung(),benhnhanservice.doichuoitungay(pk.getNgayLapPhieu()),pk.getNhanvien().getTen(),pk.getChanDoan(),trangthai};
					datamodel.addRow(rowdata);
				}
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
