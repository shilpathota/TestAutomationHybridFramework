package com.mbrdi.gsp.test.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.mbrdi.gsp.test.dto.ControlDTO;
import com.mbrdi.gsp.test.dto.TestCaseDTO;
import com.mbrdi.gsp.test.dto.TestDataDTO;


public class AutomationUtils {

	private static List<TestCaseDTO> getReusableTestCases(List<TestCaseDTO> reusableTestCases, TestCaseDTO testCaseDTO)
			throws Exception {
		if (CollectionUtils.isNotEmpty(reusableTestCases)) {
			List<TestCaseDTO> testCaseDTOs = reusableTestCases.stream()
					.filter(each -> (StringUtils.equalsIgnoreCase(each.getScreen(), testCaseDTO.getScreen())
							&& StringUtils.equalsIgnoreCase(each.getReusableName(), testCaseDTO.getAction()) && each.isEnabled()))
					.sorted(Comparator.comparing(TestCaseDTO::getStepNumber)).collect(Collectors.toList());
			if (CollectionUtils.isEmpty(testCaseDTOs)) {
				throw new Exception("Reusable test cases missing for action: " + testCaseDTO.getAction() + ", screen: "
						+ testCaseDTO.getScreen());
			}
			return testCaseDTOs;
		}
		throw new Exception("Reusable test cases missing for action: " + testCaseDTO.getAction() + ", screen: "
				+ testCaseDTO.getScreen());
	}

	public static List<TestCaseDTO> getTestCases(String filePath) throws Exception {

		List<TestCaseDTO> testCases = ExcelUtils.getTestCases(filePath, false);
		List<TestCaseDTO> reusableTestCases = ExcelUtils.getTestCases(filePath, true);

		List<TestCaseDTO> finalTestCases = new ArrayList<TestCaseDTO>();

		if (CollectionUtils.isNotEmpty(testCases)) {
			for (TestCaseDTO testCaseDTO : testCases) {
				if(testCaseDTO.isEnabled()) {
					if (StringUtils.contains(testCaseDTO.getAction(), "<<")) {
						List<TestCaseDTO> testCaseDTOs = getReusableTestCases(reusableTestCases, testCaseDTO);
						if (CollectionUtils.isNotEmpty(testCaseDTOs)) {
							testCaseDTOs.stream().forEach(each -> each.setTestCaseId(testCaseDTO.getTestCaseId()));
							finalTestCases.addAll(testCaseDTOs);
						}
					} else {
						finalTestCases.add(testCaseDTO);
					}
				}
			}
		}

		List<ControlDTO> mappingsDTOs = ExcelUtils.getControls(filePath);
		List<TestDataDTO> testDataDTOs = ExcelUtils.getTestData(filePath);

		for (int i = 0; i < finalTestCases.size(); i++) {
			TestCaseDTO testCaseDTO = finalTestCases.get(i);

			testCaseDTO.setStepNumber(i + 1);

			Optional<ControlDTO> optionalControlDTO = mappingsDTOs.stream()
					.filter(mapping -> (StringUtils.equalsIgnoreCase(mapping.getScreen(), testCaseDTO.getScreen())
							&& StringUtils.equalsIgnoreCase(mapping.getControlName(), testCaseDTO.getControlName())))
					.findFirst();
			if (optionalControlDTO.isPresent()) {
				testCaseDTO.setControlDTO(optionalControlDTO.get());
			} else {
				throw new Exception("Missing control name: " + testCaseDTO.getControlName() + ", screen: "
						+ testCaseDTO.getScreen());
			}

			if (StringUtils.contains(testCaseDTO.getData(), "<<")) {
				Optional<TestDataDTO> optionalTestDataDTO = testDataDTOs.stream()
						.filter(each -> StringUtils.equalsIgnoreCase(each.getDataReference(), testCaseDTO.getData()))
						.findFirst();
				if (optionalTestDataDTO.isPresent()) {
					testCaseDTO.setData(optionalTestDataDTO.get().getData());
				} else {
					throw new Exception("Missing test data: " + testCaseDTO.getData());
				}
			}
		}

		return finalTestCases;
	}


}
