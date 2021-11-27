package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.GhiExcelHoaDon;
import Entity.NhanVien;
import Entity.TaiKhoan;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;

public class GUIChucNang extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnqlnhanvien;
	private JButton btnqlbenhnhan;
	private JButton btnLapHoaDon;
	private JButton btnlapphieukham;
	private JButton btncapnhatphieukham;
	private JButton btndatlichkham,btndangxuat,btnxuathoadon;
	private JButton btndoimatkhau,btnxapxep;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmdangXuat;
	private JMenuItem mntmThoat;
	
	//
	NhanVien mNhanVien;
	TaiKhoan mTaiKhoan;
	
	private JButton btnlapphieudichvu;
	private JLabel lblChucVu;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	public GUIChucNang(TaiKhoan taikhoan,NhanVien nhanvien) {
		this.mTaiKhoan=taikhoan;
		this.mNhanVien=nhanvien;
		intform();
		
	}
	
		

	public void intform() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setTitle("Hệ thống chức năng");
		setBounds(100, 100, 1177, 700);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmdangXuat = new JMenuItem("Đăng Xuất");
		mnFile.add(mntmdangXuat);
		
		mntmThoat = new JMenuItem("Thoát");
		mnFile.add(mntmThoat);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Hệ Thống Chức Năng");
		label.setBounds(450, 11, 328, 49);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 32));
		contentPane.add(label);
		
		JLabel lblten = new JLabel("Tên: ");
		lblten.setBounds(884, 52, 105, 29);
		lblten.setForeground(Color.BLACK);
		lblten.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblten);
		
		JLabel lblloaiNhanVien = new JLabel("Chức Vụ: ");
		lblloaiNhanVien.setBounds(884, 79, 105, 29);
		lblloaiNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblloaiNhanVien);
		
		btnqlnhanvien = new JButton("Quản Lí Nhân Viên");
		btnqlnhanvien.setIcon(new ImageIcon("travel_management.png"));
		btnqlnhanvien.setBounds(67, 128, 262, 66);
		btnqlnhanvien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		contentPane.add(btnqlnhanvien);
		
		btnqlbenhnhan = new JButton("Quản Lí Bệnh Nhân");
		btnqlbenhnhan.setIcon(new ImageIcon("travel_management.png"));
		btnqlbenhnhan.setBounds(406, 128, 245, 66);
		btnqlbenhnhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
		contentPane.add(btnqlbenhnhan);
		
		btnlapphieukham = new JButton("Lập Phiếu Khám");
		btnlapphieukham.setIcon(new ImageIcon("default_document.png"));
		btnlapphieukham.setBounds(406, 257, 245, 66);
		btnlapphieukham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnlapphieukham);
		
		btncapnhatphieukham = new JButton("Cập Nhật Phiếu Khám");
		btncapnhatphieukham.setIcon(new ImageIcon("default_document.png"));
		btncapnhatphieukham.setBounds(67, 257, 262, 66);
		btncapnhatphieukham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btncapnhatphieukham);
		
		btndoimatkhau = new JButton("Đổi Mật Khẩu");
		btndoimatkhau.setIcon(new ImageIcon("advancedsettings.png"));
		btndoimatkhau.setBounds(748, 257, 241, 66);
		btndoimatkhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btndoimatkhau);
		
		JLabel lblTen = new JLabel();
		lblTen.setBounds(973, 53, 144, 29);
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTen.setText(mNhanVien.getTen());
		contentPane.add(lblTen);
		
		
		btnlapphieudichvu = new JButton("Lập Phiếu Dịch Vụ");
		btnlapphieudichvu.setIcon(new ImageIcon("default_document.png"));
		btnlapphieudichvu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnlapphieudichvu.setBounds(748, 128, 245, 66);
		contentPane.add(btnlapphieudichvu);
		
		lblChucVu = new JLabel();
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblChucVu.setBounds(984, 79, 144, 29);
		lblChucVu.setText(mTaiKhoan.getRole().getName());
		contentPane.add(lblChucVu);
		
		btndatlichkham = new JButton("Đặt lịch khám");
		btndatlichkham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndatlichkham.setBounds(67, 398, 262, 66);
		btndatlichkham.setIcon(new ImageIcon("add.png"));
		contentPane.add(btndatlichkham);
		
		btnLapHoaDon = new JButton("Lập hóa đơn");
		btnLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLapHoaDon.setBounds(406, 398, 245, 66);
		btnLapHoaDon.setIcon(new ImageIcon("default_document.png"));
		contentPane.add(btnLapHoaDon);
		
		btnxapxep = new JButton("Sắp xếp ưu tiên");
		btnxapxep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxapxep.setBounds(748, 398, 241, 66);
		btnxapxep.setIcon(new ImageIcon("sua.png"));
		contentPane.add(btnxapxep);
		
		btnxuathoadon = new JButton("Xuất Hóa Đơn");
		btnxuathoadon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxuathoadon.setBounds(67, 531, 262, 66);
		btnxuathoadon.setIcon(new ImageIcon("luu.png"));
		contentPane.add(btnxuathoadon);
		
		btndangxuat = new JButton("Đăng xuất");
		btndangxuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btndangxuat.setBounds(406, 531, 245, 66);
		btndangxuat.setIcon(new ImageIcon("Login-out-icon.png"));
		contentPane.add(btndangxuat);
		
		btndoimatkhau.addActionListener(this);
		btnqlnhanvien.addActionListener(this);
		btncapnhatphieukham.addActionListener(this);
		btnlapphieukham.addActionListener(this);
		btnqlbenhnhan.addActionListener(this);
		btndatlichkham.addActionListener(this);
		btnLapHoaDon.addActionListener(this);
		btnlapphieudichvu.addActionListener(this);
		btnxapxep.addActionListener(this);
		btndangxuat.addActionListener(this);
		btnxuathoadon.addActionListener(this);
		mntmdangXuat.addActionListener(this);
		mntmThoat.addActionListener(this);
		
		if(mNhanVien.getTaiKhoan().getRole().getName().equals("Bác Sỹ")) {
			btnqlnhanvien.setEnabled(false);
			btndatlichkham.setEnabled(false);
			btnLapHoaDon.setEnabled(false);
			btnxapxep.setEnabled(false);
		}else if (mNhanVien.getTaiKhoan().getRole().getName().equals("Quản Lý"))
		{
			btncapnhatphieukham.setEnabled(false);
			btndatlichkham.setEnabled(false);
			btnLapHoaDon.setEnabled(false);
			btnlapphieudichvu.setEnabled(false);
			btnlapphieukham.setEnabled(false);
			btnqlbenhnhan.setEnabled(false);
			btnxapxep.setEnabled(false);
		}else if (mNhanVien.getTaiKhoan().getRole().getName().equals("Nhân Viên Tiếp Tân"))
		{
			btncapnhatphieukham.setEnabled(false);
			btnlapphieudichvu.setEnabled(false);
			btnlapphieukham.setEnabled(false);
			btnqlnhanvien.setEnabled(false);
		}
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		 if(o==btnqlbenhnhan)
		{
			dispose();
			GUIThongTinBenhNhan ft= new GUIThongTinBenhNhan(mTaiKhoan,mNhanVien);
			ft.setVisible(true);
		}
		 if(o==mntmdangXuat) 
		 {
			 dispose();
			 GUIDangNhap dn= new GUIDangNhap();
			 dn.setVisible(true);
		 }
		 if(o==btndoimatkhau) 
		 {
			 dispose();
			 GUIDoiMatKhau dmk= new GUIDoiMatKhau(mTaiKhoan,mNhanVien);
			 dmk.setVisible(true);
		 }
		 if(o==btnqlnhanvien) 
		 {
			 dispose();
			 GUIThongTinNhanVien nv= new GUIThongTinNhanVien(mTaiKhoan,mNhanVien);
			 nv.setVisible(true);
		 }
		 if(o==btnlapphieukham) 
		 {
			 dispose();
			 GUINhanBenhNhan dskb= new GUINhanBenhNhan(mTaiKhoan,mNhanVien);
			 dskb.setVisible(true);
		 }
		 if(o==btncapnhatphieukham) 
		 {
			 dispose();
			 GUICapNhatPhieuKham cnpk= new GUICapNhatPhieuKham(mTaiKhoan,mNhanVien);
			 cnpk.setVisible(true);
		 }
		 if(o==btnlapphieudichvu) 
		 {
			 dispose();
			 GUIPhieuDichVu dv= new GUIPhieuDichVu(mTaiKhoan,mNhanVien);
			 dv.setVisible(true);
		 }
		 if(o==btndatlichkham) 
		 {
			 dispose();
			 GUIDatLichKham dl= new GUIDatLichKham(mTaiKhoan,mNhanVien);
			 dl.setVisible(true);
		 }
		 if(o==btnLapHoaDon) 
		 {
			 dispose();
			 GUIHoaDon hd= new GUIHoaDon(mTaiKhoan,mNhanVien);
			 hd.setVisible(true);
		 }
		 if(o==btnxapxep) {
			 dispose();
			 GUIDanhSachKhamBenh dn = new GUIDanhSachKhamBenh(mTaiKhoan,mNhanVien);
			 dn.setVisible(true);
		 }
		 if(o==btndangxuat) 
		 {
			 dispose();
			 GUIDangNhap dn= new GUIDangNhap();
			 dn.setVisible(true);
		 }
		 if(o==btnxuathoadon) 
		 {
			 GhiExcelHoaDon ghi = new GhiExcelHoaDon();
			 try {
				ghi.main();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 JOptionPane.showMessageDialog(this,"Đã xuất hóa đơn tại : E:/KhoaLuan/ ","Thông báo",JOptionPane.CLOSED_OPTION);
		 }
	}
}
