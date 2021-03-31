package excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class WriteToExcel {
    /**
     * 导出excel文件
     * @param title  表sheet的名字
     * @param headers  表头
     * @param dataList  正文单元格
     * @param out  输出流
     */
    public void exporteExcel(String title,String[] headers,String[][] dataList,OutputStream out){
		/*
		 * OutputStream os = new FileOutputStream("/代理IP地址.xls"); String[] headers =
		 * {"IP","PORT","匿名度","类型","位置","响应速度","最后验证时间"}; String[][] list = {{}};
		 * os.close();
		 */
        HSSFWorkbook workBook = new HSSFWorkbook();
        createSheet(title, headers, dataList, workBook);
        try {
            workBook.write(out);
        }catch (IOException e){
            System.out.println("写入文件失败"+e.getMessage());
        }
    }

    /**
     * 创建sheet
     * @param title  sheet的名字
     * @param headers  表头
     * @param dataList  正文单元格
     */
    private void createSheet(String title, String[] headers, String[][] dataList, HSSFWorkbook workBook) {
        HSSFSheet sheet = workBook.createSheet(title);
//        sheet.setDefaultColumnWidth(15);
        //设置表头和普通单元格的格式
        HSSFCellStyle headStyle = setHeaderStyle(workBook);
        HSSFCellStyle bodyStyle = setBodyStyle(workBook);

        createBody(dataList, sheet, bodyStyle);
        createHeader(headers, sheet, headStyle);
    }

    /**
     * 创建正文单元格
     * @param dataList 数据数组
     * @param sheet 表
     * @param bodyStyle 单元格格式
     */
    private void createBody(String[][] dataList, HSSFSheet sheet, HSSFCellStyle bodyStyle) {
        for (int a=0;a<dataList.length;a++){
            HSSFRow row = sheet.createRow(a+1);
            for(int j=0;j<dataList[a].length;j++){
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(bodyStyle);
                HSSFRichTextString textString = new HSSFRichTextString(dataList[a][j]);
                cell.setCellValue(textString);
            }
        }
    }

    /**
     * 创建表头
     * @param headers  表头
     * @param sheet 表
     * @param headStyle 表头格式
     */
    private void createHeader(String[] headers, HSSFSheet sheet, HSSFCellStyle headStyle) {
        HSSFRow row = sheet.createRow(0);
        for (int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            HSSFRichTextString textString = new HSSFRichTextString(headers[i]);
            cell.setCellValue(textString);
            sheet.autoSizeColumn((short)i);
        }
    }

    /**
     * 设置正文单元格格式
     * @param workBook
     * @return
     */
    private HSSFCellStyle setBodyStyle(HSSFWorkbook workBook) {
        HSSFCellStyle style2 = workBook.createCellStyle();
        style2.setFillForegroundColor((short) 13);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.LEFT);

        HSSFFont font2 = workBook.createFont();
        font2.setFontName("微软雅黑");
        font2.setBold(true);
        style2.setFont(font2);
        return style2;
    }

    /**
     * 设置表头格式
     * @param workBook
     * @return
     */
    private HSSFCellStyle setHeaderStyle(HSSFWorkbook workBook) {
        HSSFCellStyle style = workBook.createCellStyle();
        style.setFillForegroundColor(HSSFColorPredefined.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.LEFT);

        HSSFFont font = workBook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short)12);
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) {
        WriteToExcel transToExcel = new WriteToExcel();
        try {
            OutputStream os = new FileOutputStream("/代理IP地址.xls");
            String[] headers = {"IP","PORT","匿名度","类型","位置","响应速度","最后验证时间"};
            String[][] list = {{}};
			/* transToExcel.exporteExcel("学生表",headers,list,os); */
            os.close();

        }catch (FileNotFoundException e){
            System.out.println("无法找到文件");
        }catch (IOException e){
            System.out.println("写入文件失败");
        }
    }
}
