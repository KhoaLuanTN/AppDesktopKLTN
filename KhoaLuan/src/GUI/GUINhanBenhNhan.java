package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import DAO.LichHenDAO;
import DAO.NhanBenhNhan;
import Entity.BenhNhan;
import Entity.LichHen;
import Entity.NhanVien;
import Entity.TaiKhoan;

import javax.swing.JTextArea;

public class GUINhanBenhNhan extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JRadioButton radnam;
	private JRadioButton radnu;
	private JLabel lblsDT;
	private JTextField txtsDT;
	private JLabel lbldiaChi;
	private JTextField txtdiaChi;
	private JPanel Jpanel;
	private JLabel lblngaySinh;
	private JDateChooser txtngaySinh;
	private JTextField txtemail;
	private JTextField textField;
	private JTextField txthoTen;
	private JTextField txtcmnd;
	private JTextField textField_1;
	private JTextArea textArea ;
	private JButton btnnhan,btnkham;
	private TaiKhoan mTaiKhoan;
	private NhanVien mNhanVien;
	private LichHen bn = new LichHen();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public GUINhanBenhNhan(TaiKhoan taikhoan,NhanVien nhanvien) {
		
		this.mNhanVien=nhanvien;
		this.mTaiKhoan=taikhoan;
		setTitle("Nhận bênh nhân khám bệnh");
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 649);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltitle = new JLabel("Nhận Bệnh Nhân");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		lbltitle.setBounds(249, 11, 354, 48);
		contentPane.add(lbltitle);
		
		Jpanel = new JPanel();
		Jpanel.setBackground(SystemColor.inactiveCaptionBorder);
		Jpanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thông tin bệnh nhân", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Jpanel.setBounds(10, 64, 815, 453);
		contentPane.add(Jpanel);
		Jpanel.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email :");

		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(29, 144, 86, 30);
		Jpanel.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtemail.setColumns(10);
		txtemail.setBounds(170, 143, 433, 30);
		Jpanel.add(txtemail);
		
	
		
		lblsDT = new JLabel("Số điện thoại:");
		lblsDT.setBounds(29, 102, 86, 31);
		Jpanel.add(lblsDT);
		lblsDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtsDT = new JTextField();
		txtsDT.setBounds(170, 102, 319, 30);
		Jpanel.add(txtsDT);
		txtsDT.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtsDT.setColumns(10);
		
		JLabel lblgioiTinh = new JLabel("Giới tính:");
		lblgioiTinh.setBounds(29, 63, 86, 30);
		Jpanel.add(lblgioiTinh);
		lblgioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblhoTen = new JLabel("Họ tên: ");
		lblhoTen.setBounds(29, 28, 86, 20);
		Jpanel.add(lblhoTen);
		lblhoTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txthoTen = new JTextField();
		txthoTen.setBounds(170, 28, 282, 30);
		Jpanel.add(txthoTen);
		txthoTen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txthoTen.setColumns(10);
		
		
		radnam = new JRadioButton("Nam");
		radnam.setBounds(170, 64, 59, 31);
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
		radnam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radnam.setSelected(true);
		
		
		
		radnu = new JRadioButton("Nữ");
		radnu.setBounds(246, 64, 70, 31);
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
		radnu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblcmnd = new JLabel("Chứng minh nhân dân:");
		lblcmnd.setBounds(29, 185, 135, 30);
		Jpanel.add(lblcmnd);
		lblcmnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblngaySinh = new JLabel("Ngày sinh:");
		lblngaySinh.setBounds(29, 273, 86, 30);
		Jpanel.add(lblngaySinh);
		lblngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtngaySinh = new JDateChooser();
		txtngaySinh.setBounds(170, 273, 157, 30);
		Jpanel.add(txtngaySinh);
		txtngaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtngaySinh.setDateFormatString("yyyy-MM-dd");
		
		txtdiaChi = new JTextField();
		txtdiaChi.setBounds(170, 227, 405, 35);
		Jpanel.add(txtdiaChi);
		txtdiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtdiaChi.setColumns(10);
		
		txtcmnd = new JTextField();
		txtcmnd.setBounds(170, 185, 344, 33);
		Jpanel.add(txtcmnd);
		txtcmnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtcmnd.setColumns(10);
		
		lbldiaChi = new JLabel("Địa chỉ:");
		lbldiaChi.setBounds(29, 226, 86, 30);
		Jpanel.add(lbldiaChi);
		lbldiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lbldiaChi_1 = new JLabel("Ghi Chú:");
		lbldiaChi_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbldiaChi_1.setBounds(29, 355, 86, 30);
		Jpanel.add(lbldiaChi_1);
		
		JLabel lblcmnd_1 = new JLabel("Triệu Chứng:");
		lblcmnd_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblcmnd_1.setBounds(29, 314, 135, 30);
		Jpanel.add(lblcmnd_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(170, 314, 622, 33);
		Jpanel.add(textField_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setBounds(170, 359, 622, 73);
		textArea.setColumns(10);
		Jpanel.add(textArea);
		
		btnnhan = new JButton("Nhận Bệnh Nhân");
		btnnhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnnhan.setBounds(261, 547, 156, 44);
		contentPane.add(btnnhan);
		
		btnkham = new JButton("Khám Bệnh Nhân");
		btnkham.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnkham.setBounds(471, 547, 156, 44);
		contentPane.add(btnkham);
		
		txthoTen.setEditable(false);
		radnam.setEnabled(false);
		radnu.setEnabled(false);
		txtsDT.setEditable(false);
		txtcmnd.setEditable(false);
		txtdiaChi.setEditable(false);
		txtngaySinh.setEnabled(false);
		txtemail.setEnabled(false);
		textField_1.setEnabled(false);
		textArea.setEnabled(false);
		
		txthoTen.setBackground(SystemColor.info);
		txtcmnd.setBackground(SystemColor.info);
		txtsDT.setBackground(SystemColor.info);
		txtdiaChi.setBackground(SystemColor.info);
		txtemail.setBackground(SystemColor.info);
		textField_1.setBackground(SystemColor.info);
		textArea.setBackground(SystemColor.info);
		
		radnu.addActionListener(this);
		radnam.addActionListener(this);
		btnkham.addActionListener(this);
		btnnhan.addActionListener(this);
		btnkham.setEnabled(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		NhanBenhNhan nhan= new NhanBenhNhan();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date="";
        date = formatter.format(java.util.Calendar.getInstance().getTime());
		BenhNhanDAO control = new BenhNhanDAO();
		if(o==btnnhan)
		{
			try {
			bn=	nhan.receiveMessage(date);
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			if (bn.getBenhNhan().getTen()!=null)
			{
				txthoTen.setText(bn.getBenhNhan().getTen());
				txtcmnd.setText(bn.getBenhNhan().getCmnd());
				txtdiaChi.setText(bn.getBenhNhan().getDiaChi());
				txtemail.setText(bn.getBenhNhan().getEmail());
				txtngaySinh.setDate(bn.getBenhNhan().getNgaySinh());
				txtsDT.setText(bn.getBenhNhan().getSoDienThoai());
				textArea.setText(bn.getGhiChu());
				textField_1.setText(bn.getTrieuChung());
				btnkham.setEnabled(true);
				btnnhan.setEnabled(false);
			}
		}else if(o==btnkham)
		{
			dispose();
			GUIPhieuKhamBenh pkb= new GUIPhieuKhamBenh(mTaiKhoan, mNhanVien,bn.getBenhNhan(),bn);
			pkb.setVisible(true);
		}
	}
}
