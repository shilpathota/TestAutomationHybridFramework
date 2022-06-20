package com.mbrdi.gsp.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mbrdi.gsp.test.dto.ControlDTO;
import com.mbrdi.gsp.test.dto.TestCaseDTO;
import com.mbrdi.gsp.test.dto.TestCaseDefinitionDTO;
import com.mbrdi.gsp.test.dto.TestDataDTO;


public class ExcelUtils {

	private static final String FILE_NAME = "C:\\Users\\SHAPRIY\\workspace\\gsp-automation\\src\\main\\java\\com\\mbrdi\\gsp\\testinput\\TestCaseDefination.xlsx";
			

	private static String getCV(Row row, int cell) {
		if (row != null && row.getCell(cell) != null) {
			return row.getCell(cell).getStringCellValue();
		}
		return null;
	}


	public static List<TestCaseDefinitionDTO> getTestCaseDefinitions(boolean onlyEnabled) throws Exception {
		List<TestCaseDefinitionDTO> testCaseDefinitionsDTOs = new ArrayList<TestCaseDefinitionDTO>();
		Workbook workbook = null;

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				TestCaseDefinitionDTO testCaseDefinitionsDTO = new TestCaseDefinitionDTO();
				Row currentRow = rowIterator.next();

				testCaseDefinitionsDTO.setTestCaseId(getCV(currentRow, 0));
				testCaseDefinitionsDTO.setPathToTestCase(getCV(currentRow, 1));
				testCaseDefinitionsDTO.setEnabled(StringUtils.equalsIgnoreCase("Y", getCV(currentRow, 2)));
				if (onlyEnabled) {
					if (testCaseDefinitionsDTO.isEnabled()) {
						testCaseDefinitionsDTOs.add(testCaseDefinitionsDTO);
					}
				} else {
					testCaseDefinitionsDTOs.add(testCaseDefinitionsDTO);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist: " + FILE_NAME);
			throw e;
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}

		return testCaseDefinitionsDTOs;
	}

	public static List<TestCaseDTO> getTestCases(String filePath, boolean reusableData) throws Exception {
		List<TestCaseDTO> testCaseDTOs = new ArrayList<TestCaseDTO>();
		Workbook workbook = null;

		try {

			FileInputStream excelFile = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(reusableData ? 1 : 0);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				TestCaseDTO testCaseDTO = new TestCaseDTO();
				Row currentRow = rowIterator.next();

				if (reusableData) {
					testCaseDTO.setReusableName(getCV(currentRow, 0));
				} else {
					testCaseDTO.setTestCaseId(getCV(currentRow, 0));
				}

				testCaseDTO.setStepNumber(Long.valueOf(getCV(currentRow, 1)));
				testCaseDTO.setScreen(getCV(currentRow, 2));
				testCaseDTO.setControlName(getCV(currentRow, 3));
				testCaseDTO.setAction(getCV(currentRow, 4));
				testCaseDTO.setData(getCV(currentRow, 5));
				testCaseDTO.setOptional(StringUtils.equalsIgnoreCase("Y", getCV(currentRow, 6)));
				testCaseDTO.setEnabled(StringUtils.equalsIgnoreCase("Y", getCV(currentRow, 7)));
				testCaseDTOs.add(testCaseDTO);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist: " + filePath);
			throw e;
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}
		return testCaseDTOs.stream().sorted(Comparator.comparing(TestCaseDTO::getStepNumber))
				.collect(Collectors.toList());
	}

	public static List<ControlDTO> getControls(String filePath) throws Exception {
		List<ControlDTO> mappingsDTOs = new ArrayList<ControlDTO>();
		Workbook workbook = null;

		try {

			FileInputStream excelFile = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(2);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				ControlDTO mappingsDTO = new ControlDTO();
				Row currentRow = rowIterator.next();

				mappingsDTO.setId(getCV(currentRow, 0));
				mappingsDTO.setScreen(getCV(currentRow, 1));
				mappingsDTO.setControlName(getCV(currentRow, 2));
				mappingsDTO.setControlTag(getCV(currentRow, 3));
				mappingsDTO.setControlDetails(getCV(currentRow, 4));
				mappingsDTO.setParentDetails(getCV(currentRow, 5));
				mappingsDTO.setWindowTag(getCV(currentRow, 6));
				mappingsDTOs.add(mappingsDTO);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist: " + filePath);
			throw e;
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}

		return mappingsDTOs;
	}

	public static List<TestDataDTO> getTestData(String filePath) throws Exception {
		List<TestDataDTO> testDataDTOs = new ArrayList<TestDataDTO>();
		Workbook workbook = null;

		try {

			FileInputStream excelFile = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(3);
			Iterator<Row> rowIterator = datatypeSheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				TestDataDTO testDataDTO = new TestDataDTO();
				Row currentRow = rowIterator.next();

				testDataDTO.setDataReference(getCV(currentRow, 0));
				testDataDTO.setData(getCV(currentRow, 1));
				testDataDTOs.add(testDataDTO);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist: " + filePath);
			throw e;
		} finally {
			if (workbook != null) {
				workbook.close();
			}
		}

		return testDataDTOs;
	}
}
