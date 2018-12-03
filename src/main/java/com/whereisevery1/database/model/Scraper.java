package com.whereisevery1.database.model;

//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.whereisevery1.database.model.Building;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

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
//	private final static String file = "catalog.txt";
	private final static String course_url = "https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx";
	private final static String courseListXPath = "//*[@id=\"ctl00_pageContent_courseList\"]";
	private final static String courseLevelXPath = "//*[@id=\"ctl00_pageContent_dropDownCourseLevels\"]";
	private final static String searchButtonXPath = "//*[@id=\"ctl00_pageContent_searchButton\"]";
	private final static String courseTableXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr";
	private final static String locationXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[9]";
	private final static String daysXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[7]";
	private final static String timesXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[8]";
	private final static String allCourseLevels = "All";
//	private final static String nonRooms = "T B A";
	// END - IMMUTABLE ATTRIBUTES

	public Scraper() {
//		driver = new HtmlUnitDriver();
//		driver.setJavascriptEnabled(true);
//		driver.get(course_url);
//		buildings = new HashMap<String, Building>();
//		load_times_rooms_days(driver, get_subjectArea(driver));
//
//		//writeJSON(this);
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
		 * move on to all subject areas and finally to all course levels add sleep()
		 * between courses
		 */

		// VARIABLE - DELIMITER TO PARSE DAY STRING, RANDOM DELAY FOR SCRAPING
		String delimiter = "[a-zA-z]{0,}[0-9]{0,}\\s*[0-9]{0,}";
		String splitter = "\\s+";
		double r = (Math.random() * ((5000))) + 4000;

		for (String c : courses) {
			// for (int m = 0; m <= 1; m++) {
			// String c = courses.get(m);
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
				if (location.matches(delimiter)) {
					String[] location_room = location.split(splitter);

					if (!buildings.containsKey(location_room[0]))
						buildings.put(location_room[0], new Building(location_room[0]));

					buildings.get(location_room[0]).addToRoom(
							(location_room.length == 1) ? 0 : Integer.parseInt(location_room[1]),
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
