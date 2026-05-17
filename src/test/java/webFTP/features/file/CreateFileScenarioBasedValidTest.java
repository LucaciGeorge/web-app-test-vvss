package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.AccountPageSteps;
import webFTP.steps.serenity.DeleteFilePageSteps;
import webFTP.steps.serenity.LoginPageSteps;
import webFTP.steps.serenity.LogoutPageSteps;
import webFTP.steps.serenity.NewFilePageSteps;

@RunWith(SerenityRunner.class)
public class CreateFileScenarioBasedValidTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    @Steps
    public DeleteFilePageSteps userDeleteFile;

    @Steps
    public LogoutPageSteps userLoggedOut;

    @Test
    public void create_file_scenario_with_optional_repeat() {
        String server = "localhost";
        String userName = "vvta1";
        String password = "vvta1";
        String firstFile = "scenario_file_1.txt";
        String secondFile = "scenario_file_2.txt";

        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        user.click_saveCookies();
        user.login_steps(server, userName, password);
        userLoggedIn.should_be_in_user_directory("/home/" + userName);

        userLoggedIn.newFile();
        userNewFile.should_stay_on_new_file_page();
        userNewFile.createFile(firstFile, "scenario content 1");
        userLoggedIn.should_be_able_to_see_new_file(firstFile);

        userLoggedIn.newFile();
        userNewFile.should_stay_on_new_file_page();
        userNewFile.createFile(secondFile, "scenario content 2");
        userLoggedIn.should_be_able_to_see_new_file(secondFile);

        userLoggedIn.select_file_to_delete(firstFile);
        userLoggedIn.delete_selected_file();
        userDeleteFile.delete_file(firstFile);
        userLoggedIn.should_not_be_able_to_see_new_file(firstFile);

        userLoggedIn.select_file_to_delete(secondFile);
        userLoggedIn.delete_selected_file();
        userDeleteFile.delete_file(secondFile);
        userLoggedIn.should_not_be_able_to_see_new_file(secondFile);

        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message("You have logged out from the FTP server.");
    }
}
