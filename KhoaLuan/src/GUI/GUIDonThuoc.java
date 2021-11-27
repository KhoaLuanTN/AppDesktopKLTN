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

import DAO.ChiTietDonThuocDAO;
import DAO.DonThuocDAO;
import DAO.HoaDonDAO;
import DAO.LichHenDAO;
import DAO.PhieuDichVuDAO;
import DAO.PhieuKhamDAO;
import DAO.ThuocDAO;
import Entity.ChiTietDonThuoc;
import Entity.DonThuoc;
import Entity.HoaDon;
import Entity.LichHen;
import Entity.NhanVien;
import Entity.PhieuKhambenh;
import Entity.TaiKhoan;
import Entity.Thuoc;

public class GUIDonThuoc extends JFrame implements ActionListener,MouseListener{

	private JPanel contentPane,panel_1;
	private JLabel lbldonvi;
	private JComboBox comboBox;
	private JTextField txtsl;
	private JButton btnhuy,btnluu,btnthem;
	private JTextArea tatghichu;
	
	private DefaultTableModel datamodel; 
	private JScrollPane scrollPane;
	private JTable table;
	
	private PhieuKhambenh mPhieuKhamBenh;
	private Thuoc mThuoc;
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private LichHen mLichHen;
	
	private ThuocDAO thuocService;
	private ChiTietDonThuocDAO chiTietDonThuocService;
	private DonThuocDAO donThuocService;
	private PhieuKhamDAO phieuKhamService;
	private HoaDonDAO hoaDonService;
	private LichHenDAO lichHenService;
	private PhieuDichVuDAO phieuDichVuService;
	
	private DonThuoc dt,dtpost;
	
	private List<Thuoc> listthuoc;
	

	/**
	 * Create the frame.
	 */
	public GUIDonThuoc(PhieuKhambenh phieukhambenh,TaiKhoan taikhoan,NhanVien nhanvien,LichHen lichhen) {
		this.mPhieuKhamBenh=phieukhambenh;
		this.mNhanVien=nhanvien;
		this.mTaiKhoan=taikhoan;
		this.mLichHen=lichhen;
		this.thuocService=new ThuocDAO();
		this.lichHenService=new LichHenDAO();
		this.chiTietDonThuocService=new ChiTietDonThuocDAO();
		this.donThuocService=new DonThuocDAO();
		this.phieuKhamService= new PhieuKhamDAO();
		this.hoaDonService=new HoaDonDAO();
		this.phieuDichVuService=new PhieuDichVuDAO();
		
		this.dt=new DonThuoc();
		this.dtpost=new DonThuoc();
		dtpost.setNgayLapDon(java.util.Calendar.getInstance().getTime());
		
		try {
			dt=donThuocService.POSTDonThuocReturnPK(dtpost);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setTitle("Đơn thuốc");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("Lập đơn thuốc");
		lblHpngBn.setBounds(371, 0, 491, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		panel_1 = new JPanel();
		panel_1.setBounds(28, 59, 1104, 244);
		contentPane.add(panel_1);
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin thuốc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lbltenthuoc = new JLabel("Tên thuốc :");
		lbltenthuoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltenthuoc.setBounds(33, 30, 86, 20);
		panel_1.add(lbltenthuoc);
		
		try {
			listthuoc=thuocService.GetAllThuoc();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comboBox = new JComboBox();
		
		
		comboBox.setBounds(152, 30, 268, 20);
		panel_1.add(comboBox);
		
		for(int i=0;i<listthuoc.size();i++)
			comboBox.addItem(listthuoc.get(i).getTenThuoc());
		comboBox.setSelectedItem(null);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				for(int i=0;i<listthuoc.size();i++)
					    if(listthuoc.get(i).getTenThuoc().equals(comboBox.getSelectedItem()))
					    {
					    	try {
								mThuoc=thuocService.GetOneThuoc(listthuoc.get(i).getId());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }

				System.out.println(mThuoc.getTenThuoc());
			}
		});
		
		JLabel lblNewLabel = new JLabel("Số lượng :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(33, 70, 88, 20);
		panel_1.add(lblNewLabel);
		
		txtsl = new JTextField();
		txtsl.setBounds(152, 70, 96, 20);
		panel_1.add(txtsl);
		txtsl.setColumns(10);
		
		JLabel lblsl = new JLabel();
		lblsl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblsl.setBounds(290, 70, 86, 20);
		panel_1.add(lblsl);
		
		JLabel lblghiChu = new JLabel("Ghi chú :");
		lblghiChu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblghiChu.setBounds(33, 115, 86, 20);
		panel_1.add(lblghiChu);
		
		tatghichu = new JTextArea();
		tatghichu.setBounds(149, 114, 918, 105);
		panel_1.add(tatghichu);
		
		String[]headers = {"Mã","Tên thuốc","Số lượng", "Ghi chú","Giá tiền"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(51, 351, 1060, 142);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(28, 323, 1104, 192);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(null, "Danh sách chi tiết thuốc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panel_2);
		
		btnhuy = new JButton("Quay Lại");
		btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnhuy.setBounds(977, 564, 155, 57);
		contentPane.add(btnhuy);
		
		btnluu = new JButton("Lưu đơn thuốc");
		btnluu.setIcon(new ImageIcon("luu.png"));
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnluu.setBounds(694, 564, 228, 57);
		contentPane.add(btnluu);
		
		btnthem = new JButton("Thêm");
		btnthem.setIcon(new ImageIcon("sua.png"));
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBounds(53, 564, 155, 57);
		contentPane.add(btnthem);
		
		btnhuy.addActionListener(this);
		btnthem.addActionListener(this);
		btnluu.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int ketquapostHD=0;
		
		int ketqua=0;
		if(o==btnhuy)
		{
			dispose();
			GUIChucNang cn=new GUIChucNang(mTaiKhoan, mNhanVien);
			cn.setVisible(true);
		}else if(o==btnthem)
		{
			ChiTietDonThuoc ctdt=new ChiTietDonThuoc();
			ketqua=0;
			
			ctdt.setGhiChu(tatghichu.getText());
			ctdt.setGiaTien((float) (mThuoc.getDonGia()*(Float.parseFloat(txtsl.getText()))));
			ctdt.setSoLuong(Integer.parseInt(txtsl.getText()));
			ctdt.setThuoc(mThuoc);
			ctdt.setDonthuoc(dt);
			try {
				ketqua=chiTietDonThuocService.POSTChiTietDonThuoc(ctdt);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketqua==200)
			{
				JOptionPane.showMessageDialog(this,"Bạn vừa thêm thuốc thành công !","Chú ý",JOptionPane.CLOSED_OPTION);
				XoaRong();
				removeTable();
				updateTableData();
			}
		}else if(o==btnluu) {
			mPhieuKhamBenh.setDonthuoc(dt);
			try {
				ketqua=phieuKhamService.PUTPhieuKhamBenh(mPhieuKhamBenh);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketqua==200) {
				Float giaTien=(float) 0.0;
				HoaDon hd=new HoaDon();
				hd.setNgayTao(java.util.Calendar.getInstance().getTime());
				try {
					hd.setPhieukhambenh(phieuKhamService.GetOnePhieuKham(mPhieuKhamBenh.getId()));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				hd.setTrangThai(false);
				try {
					if(chiTietDonThuocService.GetAllChiTietDonThuocByDonThuoc(dt.getId()).size()!=0) {
						for(int i=0;i<chiTietDonThuocService.GetAllChiTietDonThuocByDonThuoc(dt.getId()).size();i++)
							giaTien+=chiTietDonThuocService.GetAllChiTietDonThuocByDonThuoc(dt.getId()).get(i).getGiaTien();
					}
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					if(phieuDichVuService.GetAllDichVuByPhieuKham(mPhieuKhamBenh.getId()).size()!=0) {
						for(int i=0;i<phieuDichVuService.GetAllDichVuByPhieuKham(mPhieuKhamBenh.getId()).size();i++)
							giaTien+=(float) phieuDichVuService.GetAllDichVuByPhieuKham(mPhieuKhamBenh.getId()).get(i).getGiaTienDV();
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				hd.setTongTien(mPhieuKhamBenh.getTienKham()+giaTien);
				try {
					ketquapostHD=hoaDonService.POSTHoaDon(hd);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(ketquapostHD==200) {
					mLichHen.setTrangThai("2");
					try {
						int PUT = lichHenService.PUTLichHen(mLichHen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this,"Bạn vừa lưu phiếu khám bệnh thành công !","Chú ý",JOptionPane.CLOSED_OPTION);
					dispose();
					GUIChucNang cn=new GUIChucNang(mTaiKhoan,mNhanVien);
					cn.setVisible(true);
				}
				
			}
		}
		
	}
	public void XoaRong() {
		comboBox.setSelectedItem(null);
		txtsl.setText("");
		tatghichu.setText("");
	}
	public void updateTableData() 
	{
		// TODO Auto-generated method stub
		ArrayList<ChiTietDonThuoc>list=new ArrayList<>();
		try {
			list.addAll(chiTietDonThuocService.GetAllChiTietDonThuocByDonThuoc(dt.getId()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()!=0) {
			for (ChiTietDonThuoc pk : list) {
				
				String[] rowdata = { String.valueOf(pk.getId()),pk.getThuoc().getTenThuoc(),String.valueOf(pk.getSoLuong()),pk.getGhiChu(),String.valueOf(pk.getGiaTien())};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
