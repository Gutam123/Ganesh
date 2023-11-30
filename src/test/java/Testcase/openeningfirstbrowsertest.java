package Testcase;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Utilities.baseClass;

public class openeningfirstbrowsertest {

	WebDriver driver;
    @Test
    public void test() {
        String title = baseClass.getDriver().getTitle();
        System.out.println(title);
        
        String name1 = baseClass.getProperty("Name");
        System.out.println(name1);
        String name2 = baseClass.getProperty("name");
        System.out.println(name2);
    }
}
