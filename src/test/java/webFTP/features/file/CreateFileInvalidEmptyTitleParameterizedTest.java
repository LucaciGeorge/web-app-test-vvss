package webFTP.features.file;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import webFTP.steps.serenity.AccountPageSteps;
import webFTP.steps.serenity.LoginPageSteps;
import webFTP.steps.serenity.NewFilePageSteps;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src\\test\\resources\\assignment\\file-invalid-empty-title-data.csv")
public class CreateFileInvalidEmptyTitleParameterizedTest {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public LoginPageSteps user;

    @Steps
    public AccountPageSteps userLoggedIn;

    @Steps
    public NewFilePageSteps userNewFile;

    String server, userName, password, fileName, content, errorMessage;

    @Test
    public void create_file_with_empty_title() {
        webdriver.get("https://vvss:strugure@scs.ubbcluj.ro/vvta/net2ftp/index.php");
        user.click_saveCookies();
        user.login_steps(server, userName, password);
        userLoggedIn.should_be_in_user_directory("/home/" + userName);
        userLoggedIn.newFile();
        userNewFile.enter_file_name(fileName);
        userNewFile.enter_file_content(content);
        userNewFile.save_file();
        userNewFile.should_see_error_message(errorMessage);
    }
}
