package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 27.11.2016.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUsersPage(){
        click(By.linkText("Manage Users"));
    }


}
