package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.AccountPageSteps;
import webFTP.steps.serenity.DeleteFilePageSteps;
import webFTP.steps.serenity.LoginPageSteps;
import webFTP.steps.serenity.LogoutPageSteps;
import webFTP.steps.serenity.NewFilePageSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\assignment\\file-valid-data.csv")
public class CreateFileValidParameterizedTest {

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

    String server, userName, password, fileName, content, logoutMessage;

    @Test
    public void create_file_with_valid_data() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        user.click_saveCookies();
        user.login_steps(server, userName, password);
        userLoggedIn.should_be_in_user_directory("/home/" + userName);
        userLoggedIn.newFile();
        userNewFile.createFile(fileName, content);
        userLoggedIn.should_be_able_to_see_new_file(fileName);
        userLoggedIn.select_file_to_delete(fileName);
        userLoggedIn.delete_selected_file();
        userDeleteFile.delete_file(fileName);
        userLoggedIn.should_not_be_able_to_see_new_file(fileName);
        userLoggedIn.logout();
        userLoggedOut.should_see_logout_message(logoutMessage);
    }
}
