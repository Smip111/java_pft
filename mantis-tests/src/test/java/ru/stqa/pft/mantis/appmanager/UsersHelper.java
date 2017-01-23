package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by admin on 21.01.2017.
 */
public class UsersHelper extends HelperBase {

    public UsersHelper(ApplicationManager app) {
        super(app);
    }

    public void manageUser(String userName){
        type(By.name("username"),userName);
        click(By.cssSelector("input[value='Manage User']"));
    }

    public void startResetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }



}
