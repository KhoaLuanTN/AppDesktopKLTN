package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.BenhNhanDAO;
import DAO.PhieuKhamDAO;
import Entity.BenhNhan;
import Entity.DonThuoc;
import Entity.LichHen;
import Entity.NhanVien;
import Entity.PhieuKhambenh;
import Entity.TaiKhoan;

import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class GUIPhieuKhamBenh extends JFrame implements MouseListener,ActionListener{

	private JPanel contentPane;
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnHelp;
	
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private BenhNhan mBenhNhan;
	private LichHen mLichHen;
	 
	private PhieuKhamDAO phieuKhamService;
	private BenhNhanDAO benhnhanservice;
	
	private JPanel Jpanel_1;
	private JLabel lbldiaChiBN;
	private JTextField txtdiaChiBN;
	
	private DefaultTableModel datamodel; 
	private JScrollPane scrollPane;
	private JTable table;
	
	private JTextField txthoTenBN;
	private JRadioButton rdbhoanthanh;
	private JRadioButton rdbchuahoanthanh;
	private JLabel lblmaBN;
//	private JButton btnhuy;
	private JButton btnluu;
	private JComboBox comboBox;

	private List<String> listBN;
 
	private JPanel panel;
	private JTextField textField;
	private JTextArea tatTrieuChung,tatChanDoan;
	/**
	 * Create the frame.
	 */
	public GUIPhieuKhamBenh(TaiKhoan taikhoan,NhanVien nhanvien, BenhNhan benhnhan,LichHen lichhen) {
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		this.mBenhNhan=benhnhan;
		this.mLichHen=lichhen;
		this.benhnhanservice=new BenhNhanDAO();
		this.phieuKhamService=new PhieuKhamDAO();
		
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("Phiếu Khám Bệnh");
		lblHpngBn.setBounds(371, 0, 325, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		
		
		Jpanel_1 = new JPanel();
		Jpanel_1.setBounds(28, 59, 1104, 95);
		contentPane.add(Jpanel_1);
		Jpanel_1.setBackground(SystemColor.inactiveCaptionBorder);
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
				txtdiaChiBN.setBounds(740, 31, 268, 20);
				Jpanel_1.add(txtdiaChiBN);
				txtdiaChiBN.setUI(new HintTextFieldUI("					Nhập địa chỉ khách hàng. VD: Bình Thuận", true, Color.GRAY));
				txtdiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtdiaChiBN.setColumns(10);
				txtdiaChiBN.setEnabled(false);
				
				lbldiaChiBN = new JLabel("Địa chỉ:");
				lbldiaChiBN.setBounds(621, 30, 86, 20);
				Jpanel_1.add(lbldiaChiBN);
				lbldiaChiBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				lblmaBN = new JLabel("Mã bệnh nhân :");
				lblmaBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblmaBN.setBounds(33, 30, 86, 20);
				Jpanel_1.add(lblmaBN);
				
				
				
				
				 comboBox = new JComboBox();
				
				
				comboBox.setSelectedItem(null);
				comboBox.setBounds(152, 30, 268, 20);
				Jpanel_1.add(comboBox);
				comboBox.setEditable(true);
				
				JLabel lblsdT = new JLabel("Số điện thoại:");
				lblsdT.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblsdT.setBounds(621, 60, 86, 20);
				Jpanel_1.add(lblsdT);
				
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
				textField.setEnabled(false);
				textField.setColumns(10);
				textField.setBounds(740, 61, 268, 20);
				Jpanel_1.add(textField);
				
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
				
				panel = new JPanel();
				panel.setBackground(SystemColor.inactiveCaptionBorder);
				panel.setBorder(new TitledBorder(null, "Thông tin khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(28, 165, 1106, 95);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Triệu Chứng :");
				lblNewLabel.setBounds(38, 30, 91, 19);
				panel.add(lblNewLabel);
				
				tatTrieuChung = new JTextArea();
				tatTrieuChung.setBounds(154, 27, 888, 50);
				panel.add(tatTrieuChung);
				
				String[]headers = {"Mã","Triệu chứng","Ngày khám", "Người khám","Chẩn đoán","Trạng thái"};
				datamodel = new DefaultTableModel(headers,0);
				contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
				scrollPane.setBounds(51, 291, 1060, 79);
				table.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane.setBackground(SystemColor.scrollbar);
				
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(28, 272, 1104, 113);
				panel_2.setLayout(null);
				panel_2.setBorder(new TitledBorder(null, "Danh Sách Phiếu khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_2.setBackground(SystemColor.inactiveCaptionBorder);
				contentPane.add(panel_2);
				
				
				
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(null, "Kết quả khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBackground(SystemColor.inactiveCaptionBorder);
				panel_1.setBounds(26, 396, 1106, 153);
				contentPane.add(panel_1);
				
				JLabel lblNewLabel_1 = new JLabel("Chẩn đoán  :");
				lblNewLabel_1.setBounds(34, 30, 109, 19);
				panel_1.add(lblNewLabel_1);
				
				tatChanDoan = new JTextArea();
				tatChanDoan.setBounds(153, 27, 889, 65);
				panel_1.add(tatChanDoan);
				
				JLabel lblNewLabel_2 = new JLabel("Trạng Thái :");
				lblNewLabel_2.setBounds(32, 114, 91, 19);
				panel_1.add(lblNewLabel_2);
				
				 rdbhoanthanh = new JRadioButton("Đã hoàn thành");
				rdbhoanthanh.setBounds(148, 114, 128, 23);
				panel_1.add(rdbhoanthanh);
				
				 rdbchuahoanthanh = new JRadioButton("Chưa hoàn thành");
				rdbchuahoanthanh.setBounds(332, 114, 143, 23);
				panel_1.add(rdbchuahoanthanh);
				rdbchuahoanthanh.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (rdbchuahoanthanh.isSelected())
							rdbhoanthanh.setSelected(false);
						else
							rdbhoanthanh.setSelected(true);
					}
				});
				rdbchuahoanthanh.setSelected(true);
				
				rdbhoanthanh.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (rdbhoanthanh.isSelected())
							rdbchuahoanthanh.setSelected(false);
						else
							rdbchuahoanthanh.setSelected(true);
					}
				});
				
//				btnhuy = new JButton("Quay Lại");
//				btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
//				btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
//				btnhuy.setBounds(977, 564, 155, 57);
//				contentPane.add(btnhuy);
				
				btnluu = new JButton("Lưu");
				btnluu.setIcon(new ImageIcon("luu.png"));
				btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnluu.setBounds(767, 564, 155, 57);
				contentPane.add(btnluu);
				
			comboBox.setEnabled(false);
			
				
//			btnhuy.addActionListener(this);
			btnluu.addActionListener(this);
			comboBox.setSelectedItem(mBenhNhan.getId());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		int ketquaPost=0;
//		if(o==btnhuy)
//		{
//			dispose();
//			GUIChucNang ft= new GUIChucNang(mTaiKhoan,mNhanVien);
//			ft.setVisible(true);
//		}
//		else 
			if(o==btnluu) 
		{
			PhieuKhambenh pk= new PhieuKhambenh();
			PhieuKhambenh pkketqua= new PhieuKhambenh();
			try {
				pk.setBenhnhan(benhnhanservice.GetOneBenhNhan(Long.parseLong(comboBox.getSelectedItem().toString())));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pk.setNhanvien(mNhanVien);
			pk.setNgayLapPhieu(java.util.Calendar.getInstance().getTime());
			pk.setTienKham((float) 40000);
			pk.setChanDoan(tatChanDoan.getText());
			pk.setTrieuChung(tatTrieuChung.getText());
			System.out.println(pk.getBenhnhan().getTen());
			System.out.println(pk.getNhanvien().getTen());
			if(rdbhoanthanh.isSelected())
			{
				pk.setTrangThai(true);
				try {
					pkketqua= phieuKhamService.POSTPhieuKhamBenhReturnPK(pk);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				GUIDonThuoc ft= new GUIDonThuoc(pkketqua,mTaiKhoan,mNhanVien,mLichHen);
				ft.setVisible(true);
				
			}
			else 			
			{
				pk.setTrangThai(false);
				try {
					ketquaPost= phieuKhamService.POSTPhieuKhamBenh(pk);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(ketquaPost==200) 
				{
					JOptionPane.showMessageDialog(this,"Bạn vừa lưu 1 phiếu khám bệnh !","Chú ý",JOptionPane.CLOSED_OPTION);
					
					dispose();
					GUIChucNang cn= new GUIChucNang(mTaiKhoan, mNhanVien);
					cn.setVisible(true);
				}	
			}	
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
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
