package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.NewFilePage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

public class NewFilePageSteps {

    NewFilePage newFilePage;

    @Step
    public void enter_file_name(String fileName) {
        newFilePage.enter_file_name(fileName);
    }

    @Step
    public void enter_file_content(String content) {
        newFilePage.enter_file_content(content);
    }

    @Step
    public void save_file() {
        newFilePage.click_save_file();
    }

    @Step
    public void back() {
        newFilePage.back();
    }

    @Step
    public void createFile(String fileName, String content) {
        enter_file_name(fileName);
        enter_file_content(content);
        save_file();
        back();
    }

    @Step
    public void should_stay_on_new_file_page() {
        assertTrue(newFilePage.is_save_button_visible());
    }

    @Step
    public void should_see_error_message(String expectedMessage) {
        assertThat(newFilePage.get_error_message(), containsString(expectedMessage));
    }
}
