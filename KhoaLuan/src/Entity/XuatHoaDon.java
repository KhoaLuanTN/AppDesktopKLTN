package Entity;

import java.util.List;

public class XuatHoaDon {

	private String tenBN;
	private String cmnd;
	private String chandoan;
	private String tenBS;
	private String tongTien;
	private List<ChiTietDonThuoc> dsthuoc;
	private List<PhieuDichVu> dsdichvu;
	public String getTenBN() {
		return tenBN;
	}
	public void setTenBN(String tenBN) {
		this.tenBN = tenBN;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getChandoan() {
		return chandoan;
	}
	public void setChandoan(String chandoan) {
		this.chandoan = chandoan;
	}
	public String getTenBS() {
		return tenBS;
	}
	public void setTenBS(String tenBS) {
		this.tenBS = tenBS;
	}
	public List<ChiTietDonThuoc> getDsthuoc() {
		return dsthuoc;
	}
	public void setDsthuoc(List<ChiTietDonThuoc> dsthuoc) {
		this.dsthuoc = dsthuoc;
	}
	public List<PhieuDichVu> getDsdichvu() {
		return dsdichvu;
	}
	public void setDsdichvu(List<PhieuDichVu> dsdichvu) {
		this.dsdichvu = dsdichvu;
	}
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	public XuatHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
