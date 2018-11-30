package com.whereisevery1.database.model;

import com.whereisevery1.database.model.Building;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Math;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;


/*
 * TODO: - Write the loading into database method
 * 		 - Parse time, days, rooms/buildings
 * 		 - Use the building class and room
 */
public class Scraping {
	
	// START -  MUTABLE ATTRIBUTES
	private static HtmlUnitDriver driver;
	private static HashMap<String,Building> buildings;
	// END - MUTABLE ATTRIBUTES	


	// START - IMMUTABLE ATTRIBUTES
	private static String course_url = "https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx";
	private static String courseListXPath = "//*[@id=\"ctl00_pageContent_courseList\"]";
	private static String courseLevelXPath ="//*[@id=\"ctl00_pageContent_dropDownCourseLevels\"]";
       	private static String searchButtonXPath = "//*[@id=\"ctl00_pageContent_searchButton\"]";
	private static String courseTableXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr";
	private static String locationXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[9]";
	private static String daysXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[7]"; 
	private static timesXPath = "//*[@id=\"aspnetForm\"]/table/tbody/tr[3]/td/div/center/table/tbody/tr[%d]/td[8]";
	private static String allCourseLevels = "All";	
	private static String nonRooms = "T B A";
	// END - IMMUTABLE ATTRIBUTES
	
	public Scraping() {
		driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get(course_url);

		load_times_rooms_days(driver, get_subjectArea(driver));
	}

	/*
	*	parameters:
	*		driver	-- HtmlUnitDriver object for scraping
	*/
	public static ArrayList<String> get_subjectArea(HtmlUnitDriver driver){
		Select s = new Select(driver.findElementByXPath(courseListXPath));
		ArrayList<String> temp = new ArrayList<String>();

		for(WebElement e : s.getOptions())
			temp.add(e.getText());

		return temp;
	}

	/*
	*	parameters:
	*		driver	-- HtmlUnitDriver object for scraping
	*		courses	-- ArrayList<String> of courses already
	*/
	public static void load_times_rooms_days(HtmlUnitDriver driver, ArrayList<String> courses){

		/* TODO: Loop through each course and level
		 * Start with the one subject area
		 * Then move on to all subject areas
		 * and finally to all course levels
		 * add sleep() between courses
		*/

		// VARIABLE - DELIMITER TO PARSE DAY STRING, RANDOM DELAY FOR SCRAPING
		String delimiter = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
		double r = (Math.random() * ((60000 - 4000) + 1)) + 4000;
		
		for(String c : courses) {
			//	grabs element id
			Select course_editbox = new Select(driver.findElementByXPath(courseListXPath));
			course_editbox.selectByVisibleText(c);

			//	grabs course level and changes to ALL
			Select lvl_editbox = new Select(driver.findElementByXPath());
			lvl_editbox.selectByVisibleText(allCourseLevels);

			//	grabs element id and clicks
			WebElement search_button = driver.findElementByXPath(searchButtonXPath);
			search_button.click();

			for(int i = 1; i <= driver.findElements(By.xpath(courseTableXPath)).size(); i++){
				String location = driver.findElement(By.xpath(String.format(locationXPath,i)).getText();

				String[] location_room = location.split(delimiter);

				if(location_room[0].compareTo(nonRooms) != 0) {
					if(!buildings.containsKey(location_room[0]))
						buildings.put(location, new Building(location_room[0]));

					buildings.get(location_room[0]).addToRoom((location_room.length == 1)? 0 : Integer.parseInt(location_room[1]),
							driver.findElement(By.xpath(String.format(daysXPath, i))).toString(),
							driver.findElement(By.xpath(String.format(timesXPath, i)).toString());
				}
			}

			// random delay
			Thread.sleep((long) r);
		}
	}
}
