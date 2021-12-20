package DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import Entity.BenhNhan;
import Entity.ChiTietDonThuoc;
import Entity.HoaDon;
import Entity.PhieuDichVu;

public class GhiExcelHoaDon {
	
	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
	}
	public static void main() throws IOException {

		HoaDonDAO hoadonDao = new HoaDonDAO();
		ChiTietDonThuocDAO chitietdonthuocDao = new ChiTietDonThuocDAO();
		PhieuDichVuDAO phieudichvuDao = new PhieuDichVuDAO();
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Hoa Don");
        
        
        List<HoaDon> list = hoadonDao.GetAllHoaDonDaThanhToan();

        int rownum = 0;
        
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        
       
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue(" ID ");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("    Tên nhân viên    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("    Tên bệnh nhân    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("    Chuẩn đoán bệnh    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("  Tiền khám bệnh  ");
        cell.setCellStyle(style);
        
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("  Tiền thuốc  ");
        cell.setCellStyle(style);
        
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("   Tiền dịch vụ    ");
        cell.setCellStyle(style);
      
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("  Tổng tiền  ");
        cell.setCellStyle(style);

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Data
        for (HoaDon hd : list) {
            rownum++;
            row = sheet.createRow(rownum);
            
            Float tienkham= (float) 0.0;
            Float tiendichvu= (float) 0.0;
            Float tienthuoc= (float) 0.0;

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(hd.getId());
   
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(hd.getPhieukhambenh().getNhanvien().getTen());
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(hd.getPhieukhambenh().getBenhnhan().getTen());
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(hd.getPhieukhambenh().getChanDoan());
            
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(hd.getPhieukhambenh().getTienKham());
            
            cell = row.createCell(5, CellType.STRING);
            List<ChiTietDonThuoc>listthuoc= chitietdonthuocDao.GetAllChiTietDonThuocByDonThuoc(hd.getPhieukhambenh().getDonthuoc().getId());
            for(ChiTietDonThuoc ct : listthuoc)
            {
            	tienthuoc+=ct.getGiaTien();
            }
            cell.setCellValue(tienthuoc);
            
            cell = row.createCell(6, CellType.STRING);
            List<PhieuDichVu>listdichvu= phieudichvuDao.GetAllDichVuByPhieuKham(hd.getPhieukhambenh().getId());
            for(PhieuDichVu dv : listdichvu)
            {
            	tiendichvu+=dv.getGiaTienDV();
            }
            cell.setCellValue(tiendichvu);
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(hd.getTongTien());
            
            row = sheet.createRow(list.size()+1);
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tổng thu thập : ");
            
            cell = row.createCell(7, CellType.FORMULA);
            cell.setCellFormula("SUM(H2:H"+( list.size()+1)+")");
           
        }
        File file = new File("E:/KhoaLuan/HoaDon.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}

