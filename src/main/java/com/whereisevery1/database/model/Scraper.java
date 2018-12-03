package com.whereisevery1.database.model;

import com.whereisevery1.database.model.Building;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

/*
 * TODO: - Write the loading into database method
 * 		 - Parse time, days, rooms/buildings
 * 		 - Use the building class and room
 */
public class Scraper {

	// START - MUTABLE ATTRIBUTES
	private static HtmlUnitDriver driver;
	private static HashMap<String, Building> buildings;
	// END - MUTABLE ATTRIBUTES

	// START - IMMUTABLE ATTRIBUTES
	private final static String course_url = "https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx";
	private final static String courseListXPath = "//*[@id=\"ctl00_pageContent_courseList\"]";
	private final static String courseLevelXPath = "//*[@id=\"ctl00_pageContent_dropDownCourseLevels\"]";
	private final static String searchButtonXPath = "//*[@id=\"ctl00_pageContent_searchButton\"]";
	private final static String courseTableXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr";
	private final static String locationXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[9]";
	private final static String daysXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[7]";
	private final static String timesXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[8]";
	private final static String allCourseLevels = "All";
	// END - IMMUTABLE ATTRIBUTES

	public Scraper() {
	}

	public static HashMap<String, Building> scrapeUCSB() {
		driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get(course_url);
		buildings = new HashMap<String, Building>();
		return Scraper.load_times_rooms_days(driver, get_subjectArea(driver));
	}

	/*
	 * parameters: driver -- HtmlUnitDriver object for scraping
	 */
	public static ArrayList<String> get_subjectArea(HtmlUnitDriver driver) {
		Select s = new Select(driver.findElementByXPath(courseListXPath));
		ArrayList<String> temp = new ArrayList<String>();

		for (WebElement e : s.getOptions())
			temp.add(e.getText());

		return temp;
	}

	/*
	 * parameters: driver -- HtmlUnitDriver object for scraping courses --
	 * ArrayList<String> of courses already
	 */
	public static HashMap<String, Building> load_times_rooms_days(HtmlUnitDriver driver, ArrayList<String> courses) {

		/*
		 * TODO: Loop through each course and level Start with the one subject area Then


		 * between courses
		 */

		// VARIABLE - DELIMITER TO PARSE DAY STRING, RANDOM DELAY FOR SCRAPING
		List<String> illegalRoomNames = Arrays.asList(new String [] {"ONLINE", "ON LINE", "T B A","NO ROOM", "TBA"});
		String delimiterGC = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
		String delimiterSC = "[a-zA-Z]+\\s[0-9]+.|[0-9]+\\s[0-9]+";
		String splitter = "\\s+";
		String engrCase = "ENGR";

		for (String c : courses) {
			double r = (Math.random() * ((3000))) + 3000;
			// grabs element id
			Select course_editbox = new Select(driver.findElementByXPath(courseListXPath));
			course_editbox.selectByVisibleText(c);

			// grabs course level and changes to ALL
			Select lvl_editbox = new Select(driver.findElementByXPath(courseLevelXPath));
			lvl_editbox.selectByVisibleText(allCourseLevels);

			// grabs element id and clicks
			WebElement search_button = driver.findElementByXPath(searchButtonXPath);
			search_button.click();

			for (int i = 1; i <= driver.findElements(By.xpath(courseTableXPath)).size(); i++) {
				String location = driver.findElement(By.xpath(String.format(locationXPath, i))).getText();
				if (!illegalRoomNames.contains(location)) {
					String[] location_room;
					
					if(location.toUpperCase().contains(engrCase))
						location_room = new String [] {"ENGR2",location.substring(location.length() - 4)};
					else if(location.matches(delimiterSC))
						location_room = location.split(splitter);
					else
						location_room = location.split(delimiterGC);


					if (!buildings.containsKey(location_room[0]))
						buildings.put(location_room[0], new Building(location_room[0]));

					buildings.get(location_room[0]).addToRoom(
							(location_room.length == 1) ? "N/A" : location_room[1],
							driver.findElement(By.xpath(String.format(daysXPath, i))).getText(),
							driver.findElement(By.xpath(String.format(timesXPath, i))).getText());
				}
			}

			// random delay
			try {
				Thread.sleep((long) r);
			} catch (Exception e) {
				// pass
			}
		}
		// Pass back the building hashmap to be manipulated and serialized.
		return buildings;
	}
}
