package io.branch.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import io.branch.pages.GooglePage;
import io.branch.pages.MainPage;
import io.branch.pages.TeamPage;
import io.branch.utilities.BrowserUtils;


public class BranchTest extends TestBase {

	GooglePage googlePage = new GooglePage();
	MainPage mainPage = new MainPage();
	TeamPage teamPage = new TeamPage();

	List<Map<String, String>> deptTeam;
	List<String> allTeamName = new ArrayList<>();
	Map<String, String> allTeamNameDept;
	Map<String, String> otherSumNameDept;

	
	
	@Test(priority = 0, alwaysRun = true)
	public void verifyAllEmpWithSumOther() {
		extentLogger.info("Verify that number of employees match between All tab and sum of other tabs");
		
		googlePage.searchInput.sendKeys("branch" + Keys.ENTER);
		googlePage.branchSite.click();

		BrowserUtils.scrollDown(mainPage.team);

		mainPage.team.click();
		int allTeam = teamPage.Team.size();

		int otherTeam = 0;
		allTeamNameDept = teamPage.empNameDepartment();
		otherSumNameDept = new HashMap<>();
		deptTeam = new ArrayList<>();

		for (int i = 1; i < teamPage.departments.size(); i++) {
			teamPage.departments.get(i).click();
			Map<String, String> map = new HashMap<>();
			map = teamPage.empNameDepartment();
			otherSumNameDept.putAll(map);
			deptTeam.add(map);
			otherTeam += teamPage.Team.size();
		}

		assertEquals(otherTeam, allTeam, "All tab employees count did not match with sum of other tabs");
	}

	
	
	@Test(priority = 1)
	public void verifyAllEmpNameWithOther() {
		extentLogger.info("Verify that employee names match between All tab and other tabs");
		
		for (Map.Entry<String, String> entry : otherSumNameDept.entrySet()) {
			assertTrue(allTeamNameDept.containsKey(entry.getKey()),
					"Name from all team did not match or not exist in other tabs");
		}
	}

	
	
	@Test(priority = 1)
	public void verifyAllEmpDeptWithOther() {
		extentLogger.info("Verify that employee departments are listed correctly between All tab and Department tabs");
				
		for (Map.Entry<String, String> entry : allTeamNameDept.entrySet()) {
			if (otherSumNameDept.containsKey(entry.getKey())) {
				assertEquals(entry.getValue(), otherSumNameDept.get(entry.getKey()),
						"Department from all team did not match or not exist in other tabs");
			}
		}

	}

	
	
	@Test(priority = 1)
	public void verifyAllTeamSortedByName() {
		extentLogger.info("Verify employyes under all tab sorted by name, except Co-Founders");
		
		teamPage.allDepartment.click();
		
		allTeamName = BrowserUtils.getElementsText(teamPage.allName);
		
		List<String> actualTeamName = new ArrayList<>();
		List<String> expectedTeamName = new ArrayList<>();
		
		for (int i = 4; i < allTeamName.size(); i++) {
			expectedTeamName.add(allTeamName.get(i));
			actualTeamName.add(allTeamName.get(i));
		}

		Collections.sort(expectedTeamName);

		for (int i = 0; i < actualTeamName.size(); i++) {
			assertEquals(actualTeamName.get(i), expectedTeamName.get(i),
					"Team name on all team page is not sorted by name");
		}
	}

	
	
	@Test(priority = 1)
	public void verifyTeamInfoBlockText() {
		extentLogger.info("Verify all employees has a info block when hover");
		
		for (int i = 0; i < teamPage.infoText.size(); i++) {
			BrowserUtils.hover(teamPage.Team.get(i));
			assertTrue(teamPage.infoText.get(i).getText().length() > 0, "Employee don't have hover information");
		}
	}

	
	
	@Test(priority = 1)
	public void verifyDeptName() {
		extentLogger.info("Verify department tabs name and employees department name");
		
		String department = "";
		for (int i = 0; i < deptTeam.size(); i++) {

			switch (i) {
			case 0:
				department = "Data";
				break;
			case 1:
				department = "Engineering";
				break;
			case 2:
				department = "Marketing";
				break;
			case 3:
				department = "Operations";
				break;
			case 4:
				department = "Partner Growth";
				break;
			case 5:
				department = "Product";
				break;
			case 6:
				department = "Recruiting";
				break;
			}

			for (Map.Entry<String, String> entry : deptTeam.get(i).entrySet()) {
				if (!entry.getValue().contains("Co-Founder"))
					assertEquals(entry.getValue(), department, "Employee department name didn't match department tab name");
			}

		}
	}
}
