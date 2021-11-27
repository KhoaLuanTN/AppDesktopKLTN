package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.TaiKhoanDAO;
import Entity.NhanVien;
import Entity.TaiKhoan;


public class GUIDoiMatKhau extends JFrame implements ActionListener{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblmatKhauMoi;
	private JLabel lblXcNhnMt;
	private JButton btnxacNhan;
	private JButton btnhuy;
	private JLabel label;
	private JPasswordField txtmatKhauCu;
	private JPasswordField txtmatKhauMoi;
	private JPasswordField txtxacNhan;
	
	TaiKhoan mtaikhoan;
	NhanVien mnhanvien;
	/**
	 * Launch the application.
	 */
	
	public GUIDoiMatKhau(TaiKhoan taikhoan,NhanVien nhanvien) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		this.mtaikhoan=taikhoan;
		this.mnhanvien=nhanvien;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
		setTitle("Đổi mật khẩu");
		setBackground(Color.LIGHT_GRAY);
		
		JLabel lblmatKhauCu = new JLabel("Mật khẩu cũ: ");
		lblmatKhauCu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmatKhauCu.setBounds(42, 67, 133, 27);
		contentPane.add(lblmatKhauCu);
		
		lblmatKhauMoi = new JLabel("Mật khẩu mới: ");
		lblmatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmatKhauMoi.setBounds(42, 121, 133, 27);
		contentPane.add(lblmatKhauMoi);
		
		lblXcNhnMt = new JLabel("Xác nhận mật khẩu: ");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXcNhnMt.setBounds(42, 173, 133, 27);
		contentPane.add(lblXcNhnMt);
		
		btnxacNhan = new JButton("Xác Nhận");
		btnxacNhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnxacNhan.setBounds(68, 227, 110, 23);
		contentPane.add(btnxacNhan);
		
		btnhuy = new JButton("Hủy");
		
		
		btnhuy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnhuy.setBounds(248, 227, 110, 23);
		contentPane.add(btnhuy);
		
		label = new JLabel("Đổi Mật Khẩu");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(136, 11, 130, 44);
		contentPane.add(label);
		
		txtmatKhauCu = new JPasswordField();
		txtmatKhauCu.setBounds(194, 67, 182, 24);
		contentPane.add(txtmatKhauCu);
		
		txtmatKhauMoi = new JPasswordField();
		txtmatKhauMoi.setBounds(194, 121, 182, 24);
		contentPane.add(txtmatKhauMoi);
		
		txtxacNhan = new JPasswordField();
		txtxacNhan.setBounds(194, 173, 182, 24);
		contentPane.add(txtxacNhan);
		btnhuy.addActionListener(this);
		btnxacNhan.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		TaiKhoanDAO control = new TaiKhoanDAO();
		char[] pass1 = txtmatKhauCu.getPassword();
		String password1 = String.copyValueOf(pass1);
		char[] pass2 = txtmatKhauMoi.getPassword();
		String password2 = String.copyValueOf(pass2);
		char[] pass3 = txtxacNhan.getPassword();
		String password3 = String.copyValueOf(pass3);
		
		if(o==btnhuy) {
			dispose();
			GUIChucNang cn= new GUIChucNang(mtaikhoan, mnhanvien);
			cn.setVisible(true);
			
		}else if (o.equals(btnxacNhan)) {
			
			if (mtaikhoan.getPassword().equals(password1))
			{
				if(password2.equals(password3))
				{
					TaiKhoan tk=new TaiKhoan();
					tk.setUsername(mtaikhoan.getUsername());
					tk.setPassword(password2);
					tk.setRole(mtaikhoan.getRole());
					
						if (control.UpdateTK(tk))
						{
							int ret=JOptionPane.showConfirmDialog(this, "Bạn đã đổi mật khẩu thành công  !!","Chú ý",JOptionPane.YES_OPTION);
							if(ret==JOptionPane.YES_OPTION)
							dispose();
							GUIChucNang ft= new GUIChucNang(mtaikhoan,mnhanvien);
							ft.setVisible(true);
						}
						else JOptionPane.showConfirmDialog(this, "Bạn đã đổi mật khẩu thất bại","Chú ý",JOptionPane.YES_OPTION);
					
					
					
				}else 
				{
					JOptionPane.showConfirmDialog(this, "Xác nhận mật khẩu không giống với mật khẩu !","Chú ý",JOptionPane.YES_OPTION);
					txtxacNhan.requestFocus();
				}
				
						
			}else 
			{
				JOptionPane.showConfirmDialog(this, "Sai mật khẩu cũ !!!","Chú ý",JOptionPane.YES_OPTION);
				txtmatKhauCu.requestFocus();
			}
		}	
	}
}