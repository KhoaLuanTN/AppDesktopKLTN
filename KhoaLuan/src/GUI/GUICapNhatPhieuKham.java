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
import java.text.ParseException;
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
import DAO.LichHenDAO;
import DAO.PhieuKhamDAO;
import Entity.BenhNhan;
import Entity.LichHen;
import Entity.NhanVien;
import Entity.PhieuKhambenh;
import Entity.TaiKhoan;

public class GUICapNhatPhieuKham extends JFrame implements ActionListener,MouseListener{
	private JPanel contentPane;

	 
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private BenhNhan mBenhNhan;
	private LichHen mLichHen;
	
	private LichHenDAO lichHenService;
	private PhieuKhamDAO phieuKhamService;
	private BenhNhanDAO benhnhanservice;
	
	private JPanel Jpanel_1;
	private JLabel lbldiaChiBN;
	private JTextField txtdiaChiBN;
	
	private JTextField txthoTenBN;
	private JRadioButton radnamBN;
	private JRadioButton radnuBN;
	private JRadioButton rdbhoanthanh;
	private JRadioButton rdbchuahoanthanh;
	private JTextField txtemailBN;
	private JLabel lblmaBN;
	private JButton btnhuy,btnthem;
	private JButton btnluu;
	private JComboBox comboBox;
	
	private DefaultTableModel datamodel; 
	private JScrollPane scrollPane;
	private JTable table;

	private List<String> listBN;
	private List<PhieuKhambenh> listpk;
	private PhieuKhambenh pkb,pkketqua;
 
	private JPanel panel;
	private JTextField textField;
	private JTextArea tatTrieuChung,tatChanDoan;
	/**
	 * Create the frame.
	 */
	public GUICapNhatPhieuKham(TaiKhoan taikhoan,NhanVien nhanvien) {
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		this.benhnhanservice=new BenhNhanDAO();
		this.phieuKhamService=new PhieuKhamDAO();
		this.lichHenService=new LichHenDAO();
		
		this.pkb=null;
		this.pkketqua=new PhieuKhambenh();
		
		setTitle("Phiếu khám bệnh");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("Cập Nhật Phiếu Khám Bệnh");
		lblHpngBn.setBounds(371, 0, 491, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		
		
		Jpanel_1 = new JPanel();
		Jpanel_1.setBounds(28, 59, 1104, 125);
		contentPane.add(Jpanel_1);
		Jpanel_1.setBackground(SystemColor.inactiveCaptionBorder);
		Jpanel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin bệnh nhân", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel_1.setLayout(null);
		
				JLabel lblEmailBN = new JLabel("Email :");
		
				lblEmailBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblEmailBN.setBounds(623, 30, 86, 20);
				Jpanel_1.add(lblEmailBN);
				
				txtemailBN = new JTextField();
				txtemailBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtemailBN.setColumns(10);
				txtemailBN.setUI(new HintTextFieldUI("					Nhập email . VD: duyvien159@gmail.com", true, Color.GRAY));
				txtemailBN.setBounds(742, 30, 268, 20);
				txtemailBN.setEnabled(false);
				Jpanel_1.add(txtemailBN);
				
				JLabel lblgioiTinhBN = new JLabel("Giới tính:");
				lblgioiTinhBN.setBounds(33, 90, 86, 20);
				Jpanel_1.add(lblgioiTinhBN);
				lblgioiTinhBN.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
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
				
				
				radnamBN = new JRadioButton("Nam");
				radnamBN.setBounds(152, 90, 59, 23);
				radnamBN.setEnabled(false);
				Jpanel_1.add(radnamBN);
				radnamBN.setBackground(SystemColor.inactiveCaptionBorder);
				radnamBN.setFont(new Font("Tahoma", Font.PLAIN, 11));
				radnamBN.setSelected(true);
				
				
				
				radnuBN = new JRadioButton("Nữ");
				radnuBN.setBounds(228, 90, 70, 23);
				radnuBN.setEnabled(false);
				Jpanel_1.add(radnuBN);
				radnuBN.setBackground(SystemColor.inactiveCaptionBorder);
				
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
				lblsdT.setBounds(623, 90, 86, 20);
				Jpanel_1.add(lblsdT);
				
				textField = new JTextField();
				textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
				textField.setEnabled(false);
				textField.setColumns(10);
				textField.setBounds(742, 91, 268, 20);
				Jpanel_1.add(textField);
				
				
				
				
				String[]headers = {"Mã","Triệu chứng","Ngày khám", "Người khám","Trạng thái"};
				datamodel = new DefaultTableModel(headers,0);
				contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
				scrollPane.setBounds(51, 215, 1060, 109);
				table.setFont(new Font("Tahoma", Font.PLAIN, 12));
				scrollPane.setBackground(SystemColor.scrollbar);
				
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(null, "Kết quả khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBackground(SystemColor.inactiveCaptionBorder);
				panel_1.setBounds(26, 351, 1106, 202);
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
							txtemailBN.setText(mBenhNhan.getEmail());
							removeTable();
							updateTableData();
				}
			});
				
				
			JLabel lblNewLabel_1 = new JLabel("Chẩn đoán  :");
			lblNewLabel_1.setBounds(33, 30, 109, 19);
			panel_1.add(lblNewLabel_1);
			
			tatChanDoan = new JTextArea();
			tatChanDoan.setBounds(152, 27, 889, 80);
			panel_1.add(tatChanDoan);
				
			JLabel lblNewLabel_2 = new JLabel("Trạng Thái :");
			lblNewLabel_2.setBounds(33, 132, 91, 19);
			panel_1.add(lblNewLabel_2);
				
			rdbhoanthanh = new JRadioButton("Đã hoàn thành");
			rdbhoanthanh.setBounds(149, 132, 128, 23);
			panel_1.add(rdbhoanthanh);
				
			rdbchuahoanthanh = new JRadioButton("Chưa hoàn thành");
			rdbchuahoanthanh.setBounds(333, 132, 143, 23);
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
				
			btnhuy = new JButton("Quay Lại");
			btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
			btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnhuy.setBounds(977, 564, 155, 57);
			contentPane.add(btnhuy);
				
			btnluu = new JButton("Cập nhật");
			btnluu.setIcon(new ImageIcon("luu.png"));
			btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnluu.setBounds(767, 564, 155, 57);
			contentPane.add(btnluu);
				
			btnthem = new JButton("Sửa");
			btnthem.setIcon(new ImageIcon("sua.png"));
			btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnthem.setBounds(53, 564, 155, 57);
			contentPane.add(btnthem);
				
			comboBox.setEnabled(false);
			tatChanDoan.setEnabled(false);
			rdbhoanthanh.setEnabled(false);
			rdbchuahoanthanh.setEnabled(false);
			btnluu.setEnabled(false);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(28, 195, 1104, 147);
			panel_2.setLayout(null);
			panel_2.setBorder(new TitledBorder(null, "Danh Sách Phiếu khám bệnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBackground(SystemColor.inactiveCaptionBorder);
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
		int ketquaPut=0;
		if(o==btnhuy)
		{
			dispose();
			GUIChucNang ft= new GUIChucNang(mTaiKhoan,mNhanVien);
			ft.setVisible(true);
		}else if(o==btnthem)
		{
			if(btnthem.getText().equals("Sửa"))
			{
				comboBox.setEnabled(true);
				tatChanDoan.setEnabled(true);
				rdbhoanthanh.setEnabled(true);
				rdbchuahoanthanh.setEnabled(true);
				
				btnthem.setText("Hủy");
			}
			else 
			{
				comboBox.setEnabled(false);
				tatChanDoan.setEnabled(false);
				rdbhoanthanh.setEnabled(false);
				rdbchuahoanthanh.setEnabled(false);
				
				btnthem.setText("Sửa");
				btnluu.setEnabled(false);
			}
		}
		else if(o==btnluu)
		{
			if(pkb!=null)
			{
				pkb.setChanDoan(tatChanDoan.getText());
				if(rdbhoanthanh.isSelected())
					{
						pkb.setTrangThai(true);
						try {
							pkketqua= phieuKhamService.POSTPhieuKhamBenhReturnPK(pkb);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							mLichHen=lichHenService.GetLichHenBenhNhan(benhnhanservice.doichuoitungay(java.util.Calendar.getInstance().getTime()), mBenhNhan.getId());
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
					pkb.setTrangThai(false);
					try {
						ketquaPut=phieuKhamService.PUTPhieuKhamBenh(pkb);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(ketquaPut==200) {
						JOptionPane.showMessageDialog(this,"Bạn vừa cập nhật 1 phiếu khám bệnh !","Chú ý",JOptionPane.CLOSED_OPTION);
						
						comboBox.setEnabled(false);
						tatChanDoan.setEnabled(false);
						rdbhoanthanh.setEnabled(false);
						rdbchuahoanthanh.setEnabled(false);
						
						btnthem.setText("Sửa");
						btnluu.setEnabled(false);
						
						removeTable();
						updateTableData();
					}	
				}
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
				String[] rowdata = { String.valueOf(pk.getId()),pk.getTrieuChung(),benhnhanservice.doichuoitungay(pk.getNgayLapPhieu()),pk.getNhanvien().getTen(),trangthai};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
