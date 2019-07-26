package vn.com.cmcglobal.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import vn.com.cmcglobal.du5.dto.PastTestCaseDTO;
import vn.com.cmcglobal.du5.dto.TestCaseDTO;

@Service
public class DateUtils {
//	@Value("${result.sheet.data}")
//    private  String nameSheetfile;
//	@Value("${colum.header.rtccode}")
//	private  String RTC_CODE;
//	@Value("${colum.header.firsttime}")
//	private  String FIRST_TIME;
//	@Value("${colum.header.lasttime}")
//	private  String LAST_TIME;
//	@Value("${colum.header.remark}")
//	private  String REMARK;

	private static List<PastTestCaseDTO> pastTest = new ArrayList<>();

	private Integer getColumnIndexByName(Row headerRow, String text) {
		Integer index = 0;
		DataFormatter dataFormatter = new DataFormatter();
		for (int cn = 0; cn < headerRow.getLastCellNum(); cn++) {
			Cell c = headerRow.getCell(cn);
			if (text.equals(dataFormatter.formatCellValue(c).split("\\r?\\n")[0])) {
				index = cn;
			}
		}
		return index;
	}

	public List<TestCaseDTO> readFile(String testResulFile) throws IOException {
		List<TestCaseDTO> list = new ArrayList<TestCaseDTO>();
		int listIndexColum[] = new int[32];
		// PN(Internal) PN(QA) PN(equipment) PN(Bug) NT
		InputStream inputStream = new FileInputStream(new File(testResulFile));

		// Get workbook
		Workbook workbook = getWorkbook(inputStream, testResulFile);
		Sheet sheet = workbook.getSheet(NameColumn.NAME_SHEET);
		DataFormatter dataFormatter = new DataFormatter();
		int index = 0;

		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				listIndexColum[0] = this.getColumnIndexByName(row, NameColumn.ID);
				listIndexColum[1] = this.getColumnIndexByName(row, NameColumn.FIRST_TIME);
				listIndexColum[2] = this.getColumnIndexByName(row, NameColumn.LAST_TIME);
				listIndexColum[3] = this.getColumnIndexByName(row, NameColumn.TEST_RESULT);
				listIndexColum[4] = this.getColumnIndexByName(row, NameColumn.REMARKS);
				listIndexColum[5] = this.getColumnIndexByName(row, NameColumn.DESCRIPTION);
				listIndexColum[6] = this.getColumnIndexByName(row, NameColumn.CORRECTION_REQUEST);
				listIndexColum[7] = this.getColumnIndexByName(row, NameColumn.CREATOR_COMPANY);
				listIndexColum[8] = this.getColumnIndexByName(row, NameColumn.TEST_DURATION);
				listIndexColum[9] = this.getColumnIndexByName(row, NameColumn.Evaluation_Software);
				listIndexColum[10] = this.getColumnIndexByName(row, NameColumn.Evaluation_Hard);
				listIndexColum[11] = this.getColumnIndexByName(row, NameColumn.Peripheral_Device_Number);
				listIndexColum[12] = this.getColumnIndexByName(row, NameColumn.CY);
				listIndexColum[13] = this.getColumnIndexByName(row, NameColumn.Previous);
				listIndexColum[14] = this.getColumnIndexByName(row, NameColumn.Test_Angle);
				listIndexColum[15] = this.getColumnIndexByName(row, NameColumn.Test_Section);
				listIndexColum[16] = this.getColumnIndexByName(row, NameColumn.Change_Type_Y);
				listIndexColum[17] = this.getColumnIndexByName(row, NameColumn.Extra2);
				listIndexColum[18] = this.getColumnIndexByName(row, NameColumn.Extra3);
				listIndexColum[19] = this.getColumnIndexByName(row, NameColumn.RTC_CODE);
				// past of 1 ~ 14
				listIndexColum[20] = this.getColumnIndexByName(row, NameColumn.PAST_TEST_RESULT);
				listIndexColum[21] = this.getColumnIndexByName(row, NameColumn.PAST_RTC_CODE);
				listIndexColum[22] = this.getColumnIndexByName(row, NameColumn.PAST_REMARKS);
				listIndexColum[23] = this.getColumnIndexByName(row, NameColumn.PAST_DESCRIPTION);
				listIndexColum[24] = this.getColumnIndexByName(row, NameColumn.PAST_CORRECTION_REQUEST);
				listIndexColum[25] = this.getColumnIndexByName(row, NameColumn.PAST_CREATOR_COMPANY);
				listIndexColum[26] = this.getColumnIndexByName(row, NameColumn.PAST_TEST_DURATION);
				listIndexColum[27] = this.getColumnIndexByName(row, NameColumn.PAST_Evaluation_Software);
				listIndexColum[28] = this.getColumnIndexByName(row, NameColumn.PAST_Evaluation_Hard);
				listIndexColum[29] = this.getColumnIndexByName(row, NameColumn.PAST_Peripheral_Device_Number);

			} else {
				TestCaseDTO testDto = new TestCaseDTO();
				PastTestCaseDTO pastDTO = new PastTestCaseDTO();
				// testDto.setId(row.getCell(listIndexColum[0]).getStringCellValue().substring(row.getCell(listIndexColum[0]).getStringCellValue().length()-5,
				// row.getCell(listIndexColum[0]).getStringCellValue().length()));
				// testDto.setId(dataFormatter.formatCellValue(row.getCell(listIndexColum[0])));
				testDto.setFirstTime(dataFormatter.formatCellValue(row.getCell(listIndexColum[1])));
				testDto.setLastTime(dataFormatter.formatCellValue(row.getCell(listIndexColum[2])));
				testDto.setTestResults(dataFormatter.formatCellValue(row.getCell(listIndexColum[3])));
				testDto.setIncidentNumber(dataFormatter.formatCellValue(row.getCell(listIndexColum[19])));
				testDto.setRemarks(dataFormatter.formatCellValue(row.getCell(listIndexColum[4])));
				testDto.setDescription(dataFormatter.formatCellValue(row.getCell(listIndexColum[5])));
				testDto.setCorrectionRequest(dataFormatter.formatCellValue(row.getCell(listIndexColum[6])));
				testDto.setCreatorCompany(dataFormatter.formatCellValue(row.getCell(listIndexColum[7])));
				testDto.setTestDuration(dataFormatter.formatCellValue(row.getCell(listIndexColum[8])));
				testDto.setEvaluationSoftware(dataFormatter.formatCellValue(row.getCell(listIndexColum[9])));
				testDto.setEvaluationHard(dataFormatter.formatCellValue(row.getCell(listIndexColum[10])));
				testDto.setPeripheralDeviceNumber(dataFormatter.formatCellValue(row.getCell(listIndexColum[11])));
//    		testDto.setEvaluationHardCY(dataFormatter.formatCellValue(row.getCell(listIndexColum[12])));
//    		testDto.setPrevious(dataFormatter.formatCellValue(row.getCell(listIndexColum[13])));
//    		testDto.setTestAngle(dataFormatter.formatCellValue(row.getCell(listIndexColum[14])));
//    		testDto.setTestSection(dataFormatter.formatCellValue(row.getCell(listIndexColum[15])));
//    		testDto.setChangeTypeY(dataFormatter.formatCellValue(row.getCell(listIndexColum[16])));
//    		testDto.setExtra2(dataFormatter.formatCellValue(row.getCell(listIndexColum[17])));
//    		testDto.setExtra3(dataFormatter.formatCellValue(row.getCell(listIndexColum[18])));
				// Past
				pastDTO.setTestResults(dataFormatter.formatCellValue(row.getCell(listIndexColum[20])));
				pastDTO.setIncidentNumber(dataFormatter.formatCellValue(row.getCell(listIndexColum[21])));
				pastDTO.setRemarks(dataFormatter.formatCellValue(row.getCell(listIndexColum[22])));
				pastDTO.setDescription(dataFormatter.formatCellValue(row.getCell(listIndexColum[23])));
				pastDTO.setCorrectionRequest(dataFormatter.formatCellValue(row.getCell(listIndexColum[24])));
				pastDTO.setCreatorCompany(dataFormatter.formatCellValue(row.getCell(listIndexColum[25])));
				pastDTO.setTestDuration(dataFormatter.formatCellValue(row.getCell(listIndexColum[26])));
				pastDTO.setEvaluationSoftware(dataFormatter.formatCellValue(row.getCell(listIndexColum[27])));
				pastDTO.setEvaluationHard(dataFormatter.formatCellValue(row.getCell(listIndexColum[28])));
				pastDTO.setPeripheralDeviceNumber(dataFormatter.formatCellValue(row.getCell(listIndexColum[29])));

				// action update file, fix firstTime<lastTime is one
				if ("2".equals(FunctionAction.checkExecute(testDto.getFirstTime(), testDto.getLastTime()))) {
					XSSFCell cell = (XSSFCell) sheet.getRow(index).getCell(listIndexColum[1]);
					;
					cell.setCellValue(Integer.parseInt(testDto.getLastTime()) + 1);
				}

				// testDto.setMarket(row.getCell(listIndexColum[0]).getStringCellValue());
				pastTest.add(pastDTO);
				list.add(testDto);
			}

			index++;

		}
		try {
			FileOutputStream fileout = new FileOutputStream(new File(testResulFile));
			workbook.write(fileout);
		} catch (Exception e) {
			// TODO: handle exception
		}
//    for (TestCaseDTO dto : list) {
//    	 System.out.println(dto.getFirstTime()+" :   " + dto.getLastTime());
//	}
//   
		return list;

	}

	private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsm") || excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	public void writeFile(String nameSheet, String nameFile, List<TestCaseDTO> data) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// CreationHelper createHelper = workbook.getCreationHelper();
		File file = new File(nameFile);
		Sheet sheet = workbook.createSheet(nameSheet);

		// color for row 1
//	  CellStyle backgroundStyle = workbook.createCellStyle();
		int index = 0;
		Row row;
		for (TestCaseDTO dto : data) {

			if (index == 0) {
				row = sheet.createRow(index);
				CellStyle style = workbook.createCellStyle();
				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				Font font = workbook.createFont();
				font.setColor(IndexedColors.BLACK.getIndex());
				font.setBold(true);
				style.setFont(font);
				Cell cell1 = row.createCell(0);
				cell1.setCellValue(NameColumn.ID);
				cell1.setCellStyle(style);

				Cell cell2 = row.createCell(1);
				cell2.setCellValue(NameColumn.TEST_RESULT);
				cell2.setCellStyle(style);

				Cell cell3 = row.createCell(2);
				cell3.setCellValue(NameColumn.FIRST_TIME);
				cell3.setCellStyle(style);

				Cell cellRtcCode = row.createCell(3);
				cellRtcCode.setCellValue(NameColumn.RTC_CODE);
				cellRtcCode.setCellStyle(style);

				Cell cellCorrection = row.createCell(4);
				cellCorrection.setCellValue(NameColumn.CORRECTION_REQUEST);
				cellCorrection.setCellStyle(style);

				Cell cellCompany = row.createCell(5);
				cellCompany.setCellValue(NameColumn.CREATOR_COMPANY);
				cellCompany.setCellStyle(style);

				Cell cellDuration = row.createCell(6);
				cellDuration.setCellValue(NameColumn.TEST_DURATION);
				cellDuration.setCellStyle(style);

				Cell cellEvaluationSoftware = row.createCell(7);
				cellEvaluationSoftware.setCellValue(NameColumn.Evaluation_Software);
				cellEvaluationSoftware.setCellStyle(style);

				Cell cellEvaluationHard = row.createCell(8);
				cellEvaluationHard.setCellValue(NameColumn.Evaluation_Hard);
				cellEvaluationHard.setCellStyle(style);

				Cell cellPeripheralDeviceNumber = row.createCell(9);
				cellPeripheralDeviceNumber.setCellValue(NameColumn.Peripheral_Device_Number);
				cellPeripheralDeviceNumber.setCellStyle(style);

				Cell cellColumnCY = row.createCell(10);
				cellColumnCY.setCellValue(NameColumn.COLUMN_CY);
				cellColumnCY.setCellStyle(style);

				Cell cellChangTypeColumnZ = row.createCell(11);
				cellChangTypeColumnZ.setCellValue(NameColumn.CHANGE_TYPE);
				cellChangTypeColumnZ.setCellStyle(style);

				Cell cellTestAngle = row.createCell(12);
				cellTestAngle.setCellValue(NameColumn.Test_Angle);
				cellTestAngle.setCellStyle(style);

				Cell cellTestSection = row.createCell(13);
				cellTestSection.setCellValue(NameColumn.Test_Section);
				cellTestSection.setCellStyle(style);

				Cell cellChangTypeColumnY = row.createCell(14);
				cellChangTypeColumnY.setCellValue(NameColumn.Change_Type_Y);
				cellChangTypeColumnY.setCellStyle(style);

				Cell cellExtra2 = row.createCell(15);
				cellExtra2.setCellValue(NameColumn.Extra2);
				cellExtra2.setCellStyle(style);

				Cell cellExtra3 = row.createCell(16);
				cellExtra3.setCellValue(NameColumn.Extra3);
				cellExtra3.setCellStyle(style);

				// PAST
				Cell pastcell3 = row.createCell(17);
				pastcell3.setCellValue(NameColumn.PAST_TEST_RESULT);
				pastcell3.setCellStyle(style);

				Cell pastcellRtcCode = row.createCell(18);
				pastcellRtcCode.setCellValue(NameColumn.PAST_RTC_CODE);
				pastcellRtcCode.setCellStyle(style);

				Cell pastcellCorrection = row.createCell(19);
				pastcellCorrection.setCellValue(NameColumn.PAST_CORRECTION_REQUEST);
				pastcellCorrection.setCellStyle(style);

				Cell pastcellCompany = row.createCell(20);
				pastcellCompany.setCellValue(NameColumn.PAST_CREATOR_COMPANY);
				pastcellCompany.setCellStyle(style);

				Cell pastcellDuration = row.createCell(21);
				pastcellDuration.setCellValue(NameColumn.PAST_TEST_DURATION);
				pastcellDuration.setCellStyle(style);

				Cell pastcellEvaluationSoftware = row.createCell(22);
				pastcellEvaluationSoftware.setCellValue(NameColumn.PAST_Evaluation_Software);
				pastcellEvaluationSoftware.setCellStyle(style);

				Cell pastcellEvaluationHard = row.createCell(23);
				pastcellEvaluationHard.setCellValue(NameColumn.PAST_Evaluation_Hard);
				pastcellEvaluationHard.setCellStyle(style);

				Cell pastcellPeripheralDeviceNumber = row.createCell(24);
				pastcellPeripheralDeviceNumber.setCellValue(NameColumn.PAST_Peripheral_Device_Number);
				pastcellPeripheralDeviceNumber.setCellStyle(style);

				index++;

			}

			row = sheet.createRow(index);
			CellStyle style = workbook.createCellStyle();

			// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			Font font = workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			style.setFont(font);
			// sum column of file output
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(dto.getId());
			cell1.setCellStyle(style);

			Cell cell2 = row.createCell(1);
			cell2.setCellValue(FunctionAction.checkLatestResult(dto.getTestResults()));
			cell2.setCellStyle(style);
			if (!"2".equals(FunctionAction.checkExecute(dto.getFirstTime(), dto.getLastTime()))) {
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(FunctionAction.checkExecute(dto.getFirstTime(), dto.getLastTime()));
				cell3.setCellStyle(style);
			}
			Cell cellRtcCode = row.createCell(3);
			cellRtcCode.setCellValue(FunctionAction.checkColumnRtcCode(dto.getTestResults(), dto.getIncidentNumber()));
			cellRtcCode.setCellStyle(style);

			Cell cellCorrection = row.createCell(4);
			cellCorrection.setCellValue("");// NameColumn.CORRECTION_REQUEST
			cellCorrection.setCellStyle(style);

			Cell cellCompany = row.createCell(5);
			cellCompany.setCellValue(FunctionAction.creatorCompany(dto.getCreatorCompany()));
			cellCompany.setCellStyle(style);

			Cell cellDuration = row.createCell(6);
			cellDuration.setCellValue(FunctionAction.duration(dto.getTestDuration()));
			cellDuration.setCellStyle(style);

			Cell cellEvaluationSoftware = row.createCell(7);
			cellEvaluationSoftware.setCellValue(FunctionAction.evaluation(dto.getTestResults(), "TYFA"));
			cellEvaluationSoftware.setCellStyle(style);

			Cell cellEvaluationHard = row.createCell(8);
			cellEvaluationHard.setCellValue(FunctionAction.evaluation(dto.getTestResults(), "TYFA"));
			cellEvaluationHard.setCellStyle(style);

			Cell cellPeripheralDeviceNumber = row.createCell(9);
			cellPeripheralDeviceNumber.setCellValue(FunctionAction.evaluation(dto.getTestResults(), "TYFA"));
			cellPeripheralDeviceNumber.setCellStyle(style);

			Cell cellColumnCY = row.createCell(10);
			cellColumnCY.setCellValue("");// FunctionAction.checkColumnCY(dto.getTestResults())
			cellColumnCY.setCellStyle(style);

//                    Cell cellChangTypeColumnZ = row.createCell(11);
//                    cellChangTypeColumnZ.setCellValue(FunctionAction.changeTypeColumnZ(dto.getChangType(), dto.getTestResults()));
//                    cellChangTypeColumnZ.setCellStyle(style);
//                    
//                    Cell cellTestAngle = row.createCell(12);
//                    cellTestAngle.setCellValue(FunctionAction.TestAngle(dto.getTestAngle()));
//                    cellTestAngle.setCellStyle(style);
//                    
//                    Cell cellTestSection = row.createCell(13);
//                    cellTestSection.setCellValue(FunctionAction.testSetion(dto.getTestSection()));
//                    cellTestSection.setCellStyle(style);
//                    
//                    Cell cellChangTypeColumnY = row.createCell(14);
//                    cellChangTypeColumnY.setCellValue(FunctionAction.ChangeTypeColumnY(dto.getChangeTypeY()));
//                    cellChangTypeColumnY.setCellStyle(style);
//                    
//                    Cell cellExtra2 = row.createCell(15);
//                    cellExtra2.setCellValue(FunctionAction.checkExtra2(dto.getExtra2()));
//                    cellExtra2.setCellStyle(style);
//                    
//                    Cell cellExtra3 = row.createCell(16);
//                    cellExtra3.setCellValue(FunctionAction.checkExtra3(dto.getExtra3()));
//                    cellExtra3.setCellStyle(style);
//                    // past 
			Cell pastcell2 = row.createCell(17);
			pastcell2.setCellValue(FunctionAction.checkLatestResult(pastTest.get(index - 1).getTestResults()));
			pastcell2.setCellStyle(style);

			Cell pastcellRtcCode = row.createCell(18);
			pastcellRtcCode.setCellValue(FunctionAction.checkColumnRtcCode(pastTest.get(index - 1).getTestResults(),
					pastTest.get(index - 1).getIncidentNumber()));
			pastcellRtcCode.setCellStyle(style);

			Cell pastcellCorrection = row.createCell(19);
			pastcellCorrection.setCellValue("");// NameColumn.CORRECTION_REQUEST
			pastcellCorrection.setCellStyle(style);

			Cell pastcellCompany = row.createCell(20);
			pastcellCompany.setCellValue(FunctionAction.creatorCompany(pastTest.get(index - 1).getCreatorCompany()));
			pastcellCompany.setCellStyle(style);

			Cell pastcellDuration = row.createCell(21);
			pastcellDuration.setCellValue(FunctionAction.duration(pastTest.get(index - 1).getTestDuration()));
			pastcellDuration.setCellStyle(style);

			Cell pastcellEvaluationSoftware = row.createCell(22);
			pastcellEvaluationSoftware
					.setCellValue(FunctionAction.evaluation(pastTest.get(index - 1).getTestResults(), "TYFA"));
			pastcellEvaluationSoftware.setCellStyle(style);

			Cell pastcellEvaluationHard = row.createCell(23);
			pastcellEvaluationHard
					.setCellValue(FunctionAction.evaluation(pastTest.get(index - 1).getTestResults(), "TYFA"));
			pastcellEvaluationHard.setCellStyle(style);

			Cell pastcellPeripheralDeviceNumber = row.createCell(4);
			pastcellPeripheralDeviceNumber
					.setCellValue(FunctionAction.evaluation(pastTest.get(index - 1).getTestResults(), "TYFA"));
			pastcellPeripheralDeviceNumber.setCellStyle(style);

			index++;

		}
		try {
			FileOutputStream fileout = new FileOutputStream(file);

			workbook.write(fileout);
			fileout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Date date(int year, int month, int date) {
		Calendar working = GregorianCalendar.getInstance();
		working.set(year, month, date, 0, 0, 1);
		return working.getTime();
	}

}
