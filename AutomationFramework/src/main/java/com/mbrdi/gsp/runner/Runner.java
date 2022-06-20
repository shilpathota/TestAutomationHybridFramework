package com.mbrdi.gsp.runner;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.mbrdi.gsp.test.dto.TestCaseDTO;
import com.mbrdi.gsp.test.dto.TestCaseDefinitionDTO;
import com.mbrdi.gsp.test.util.AutomationUtils;
import com.mbrdi.gsp.test.util.ExcelUtils;

public class Runner {
	/******
	 * 
	 * @param args
	 * @throws Exception
	 * Test Automation Framework:
		- Driver folder to src>main>resources
		- TestCase ID in TestCaseDefinitions and test file shuld have same id -> if not matches throw error
		- Events
		- logic for runtest

	 */

	public static void main(String[] args) throws Exception {
		List<TestCaseDefinitionDTO> testCaseDefinitions = ExcelUtils.getTestCaseDefinitions(true);
		if(CollectionUtils.isNotEmpty(testCaseDefinitions)) {
			for(TestCaseDefinitionDTO testCaseDefinitionDTO : testCaseDefinitions) {
				List<TestCaseDTO> testCases = AutomationUtils.getTestCases(testCaseDefinitionDTO.getPathToTestCase());
				
				for(TestCaseDTO testCaseDTO : testCases) {
					runTest(testCaseDTO);
				}
			}
		} else {
			System.out.println("No test definitions found.");
		}
	}

	private static void runTest(TestCaseDTO testCaseDTO) {
		System.out.println("<<<<<<<<<<<<<<<");
		System.out.println("Test Case Id: " + testCaseDTO.getTestCaseId());
		System.out.println("Step Number: " + testCaseDTO.getStepNumber());
		System.out.println("Action: " + testCaseDTO.getAction());
		System.out.println("Data: " + testCaseDTO.getData());
		System.out.println("Control: " + testCaseDTO.getControlDTO());
		System.out.println(">>>>>>>>>>>>>>>\n\n");
		System.out.println();
		/* Validate if the TestCaseID matches with the TestCaseDefinitions ID*/
		
		/*Create Driver instance with the respective browser*/
		
		/*put the testcase steps in datatable dt*/
		
		/*Check if Control name has >> and extract the steps in reusable test data and insert it in the above datatable2 - dt2*/
		
		/*read the control column in the datatable and extract the mapping output i.e., element*/
		
		/*read the action and perform it*/
		
		/*if action requires an input then get data from data column and extract input from data sheet and input them*/
		
		/*mark the step as pass*/
		
		/*if all the steps are pass then return the pass result to testcasedefinitions file/remarks if failure*/
		
		
	}
}
