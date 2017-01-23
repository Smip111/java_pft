package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by admin on 21.01.2017.
 */
public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        Users before=app.db().users();
        UserData modifiedUser=before.iterator().next();
        String userName=modifiedUser.getUserName();
        String newPassword="root1";
        String email = modifiedUser.getEmail();
        app.session();
        app.goTo().manageUsersPage();
        app.users().manageUser(userName);
        app.users().startResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink,newPassword);

        HttpSession session=app.newSession();
        assertTrue(session.login(userName,newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex=VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
