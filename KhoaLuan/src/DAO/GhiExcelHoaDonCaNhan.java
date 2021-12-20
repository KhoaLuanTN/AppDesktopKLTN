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
import Entity.DichVu;
import Entity.HoaDon;
import Entity.PhieuDichVu;
import Entity.XuatHoaDon;

public class GhiExcelHoaDonCaNhan {
	
	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
	}
	public static void main(XuatHoaDon xhd) throws IOException {

		HoaDonDAO hoadonDao = new HoaDonDAO();
		ChiTietDonThuocDAO chitietdonthuocDao = new ChiTietDonThuocDAO();
		PhieuDichVuDAO phieudichvuDao = new PhieuDichVuDAO();
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Xuat Hoa Don Ca Nhan");
        
        

        int rownum = 0;
        
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        
       
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("		 Tên bệnh nhân			 ");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("  Số chứng minh thư    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("    Tên bác sỹ    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("    Chuẩn đoán bệnh    ");
        cell.setCellStyle(style);
        
        rownum++;
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("     "+xhd.getTenBN());

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("     "+xhd.getCmnd());
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("     "+xhd.getTenBS());
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("     "+xhd.getChandoan());
        
        rownum+=2;
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("		 Đơn thuốc		 ");
        cell.setCellStyle(style);
        
        rownum++;
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("		 Tên thuốc		 ");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("  Số lượng    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("    Hướng dẫn sử dụng    ");
        cell.setCellStyle(style);
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("    Giá tiền    ");
        cell.setCellStyle(style);
        
        
        
        
        
        // Data
        for (ChiTietDonThuoc chitiet : xhd.getDsthuoc()) {
            rownum++;
            row = sheet.createRow(rownum);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("     "+chitiet.getThuoc().getTenThuoc());
   
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("     "+chitiet.getSoLuong());
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("     "+chitiet.getGhiChu());
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("     "+chitiet.getGiaTien());
            
        }	
        
        if(xhd.getDsdichvu().size()!=0) {
        	rownum+=2;
            row = sheet.createRow(rownum);
            
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("		 Tên dịch vụ		 ");
            cell.setCellStyle(style);
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("  Giá tiền   ");
            cell.setCellStyle(style);
            
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("    Kết luận    ");
            cell.setCellStyle(style);
            
            for (PhieuDichVu dv : xhd.getDsdichvu()) {
                rownum++;
                row = sheet.createRow(rownum);
                
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("     "+dv.getDichVu().getGhiChu());
       
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("     "+dv.getGiaTienDV());
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("     "+dv.getKetLuan());
                
            }
        }
        
        rownum+=2;
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("		 Tên khám bệnh :		 ");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("  40000   ");
        cell.setCellStyle(style);
        
        rownum+=2;
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("	Tổng tiền thanh toán : ");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue(xhd.getTongTien());
        
        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }
        
        File file = new File("E:/KhoaLuan/HoaDon"+xhd.getCmnd()+".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

}

