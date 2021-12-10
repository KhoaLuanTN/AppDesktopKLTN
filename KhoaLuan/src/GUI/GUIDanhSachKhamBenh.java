package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.BenhNhanDAO;
import DAO.GuiBenhNhan;
import DAO.LichHenDAO;
import DAO.NhanVienDAO;
import Entity.BenhNhan;
import Entity.LichHen;
import Entity.NhanVien;
import Entity.TaiKhoan;

import javax.jms.JMSException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class GUIDanhSachKhamBenh extends JFrame implements MouseListener, ActionListener{

	private JPanel contentPane,Jpanel_1;
	
	private DefaultTableModel datamodel; 
	private JScrollPane scrollPane;
	private JTable table;
	
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private BenhNhan mBenhNhan;
	private LichHen mLichHen;
	private LichHenDAO lichhenservice;
	private JButton btnhuy,btnChuyen;
	private JButton btncapnhat;

	
	
	/**
	 * Create the frame.
	 */
	public GUIDanhSachKhamBenh(TaiKhoan taikhoan, NhanVien nhanvien) {
		
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		this.mLichHen=new LichHen();
		this.lichhenservice=new LichHenDAO();
		
		setTitle("Sắp xếp ưu tiên");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHpngBn = new JLabel("Sắp Xếp Ưu Tiên");
		lblHpngBn.setBounds(304, 0, 436, 48);
		lblHpngBn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHpngBn.setFont(new Font("Tahoma", Font.PLAIN, 32));
		contentPane.add(lblHpngBn);
		
		String[]headers = {"Mã","Ghi chú","Tên bệnh nhân", "Hình thức","Trạng thái"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(51, 100, 1060, 370);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		Jpanel_1 = new JPanel();
		Jpanel_1.setBounds(28, 59, 1104, 446);
		contentPane.add(Jpanel_1);
		Jpanel_1.setBackground(new Color(95, 158, 160));
		Jpanel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Danh sách bệnh nhân", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel_1.setLayout(null);
		
		btnhuy = new JButton("Quay Lại");
		btnhuy.setBackground(new Color(102, 205, 170));
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnhuy.setBounds(976, 545, 155, 57);
		btnhuy.setIcon(new ImageIcon("Login-out-icon.png"));
		contentPane.add(btnhuy);
		
		btncapnhat = new JButton("Cập nhật danh sách");
		btncapnhat.setBackground(new Color(102, 205, 170));
		btncapnhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncapnhat.setBounds(695, 545, 226, 57);
		contentPane.add(btncapnhat);
		
		btnChuyen = new JButton("Chuyển lên hàng đợi");
		btnChuyen.setBackground(new Color(102, 205, 170));
		btnChuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnChuyen.setBounds(405, 545, 239, 57);
		contentPane.add(btnChuyen);
		
		table.addMouseListener(this);
		btncapnhat.addActionListener(this);
		btnhuy.addActionListener(this);
		btnChuyen.addActionListener(this);
		
		btnChuyen.setEnabled(false);
		
		removeTable();
		updateTableData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o= e.getSource();
		GuiBenhNhan gui=new GuiBenhNhan();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date="";
        date = formatter.format(java.util.Calendar.getInstance().getTime());
		if(o==btnhuy) {
			dispose();
			GUIChucNang cn=new GUIChucNang(mTaiKhoan, mNhanVien);
			cn.setVisible(true);
		} else if (o==btncapnhat) {
			btnChuyen.setEnabled(false);
			removeTable();
			updateTableData();
		}else if(o==btnChuyen) {
			if(mLichHen!=null && mLichHen.getTrangThai().equals("1")) {
				try {
					gui.sendMessage(mLichHen, date);
				} catch (JMSException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mLichHen.setTrangThai("1");
				try {
					lichhenservice.PUTLichHen(mLichHen);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				
			}
			btnChuyen.setEnabled(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		try {
			mLichHen=lichhenservice.GetOneLichHen(Long.parseLong(table.getValueAt(row, 0).toString()));
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		mBenhNhan=mLichHen.getBenhNhan();
		if(!table.getValueAt(row, 4).toString().equals("Đã khám")||!table.getValueAt(row, 4).toString().equals("Đang trong hàng đợi")||!table.getValueAt(row, 4).toString().equals("Vắng mặt")) 
		{
			btnChuyen.setEnabled(true);
		}
		else 
			
		{
			btnChuyen.setEnabled(false);
			JOptionPane.showMessageDialog(this,"Bệnh nhân này đã khám rồi !","Chú ý",JOptionPane.CLOSED_OPTION);
			
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
		ArrayList<LichHen>list=new ArrayList<>();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String date="";
	        date = formatter.format(java.util.Calendar.getInstance().getTime());
	        System.out.println(mNhanVien.getId());
			list.addAll(lichhenservice.GetAllLichHenByDate(date));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()!=0)
		{
			
			for (LichHen pk : list) {
				String hinhThuc=null;
				String trangthai=null;
				if(pk.isHinhThuc())
					hinhThuc="Đặt lịch";
				else
					hinhThuc="Khám trực tiếp";
				
				if(pk.getTrangThai().equals("1"))
					trangthai="Đang chờ khám";
				else if(pk.getTrangThai().equals("3"))
					trangthai="Vắng mặt";
				else if(pk.getTrangThai().equals("2"))
					trangthai="Đã khám";
				else if(pk.getTrangThai().equals("4"))
					trangthai="Đang trong hàng đợi";
				String[] rowdata = { String.valueOf(pk.getMaLichHen()),pk.getGhiChu(),pk.getBenhNhan().getTen(),hinhThuc,trangthai};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
