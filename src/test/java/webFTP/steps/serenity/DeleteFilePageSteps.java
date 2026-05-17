package webFTP.steps.serenity;

import net.thucydides.core.annotations.Step;
import webFTP.pages.DeleteFilePage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class DeleteFilePageSteps {

    DeleteFilePage deleteFilePage;

    @Step
    public void delete_file() {
        deleteFilePage.click_delete_file();
    }

    @Step
    public void back() {
        deleteFilePage.back();
    }

    @Step
    public void delete_file(String file) {
        should_be_able_to_see_message("File " + file);
        delete_file();
       // should_be_able_to_see_message("Processing file /home/vvta1/" + file);
        back();
    }

    @Step
    public void should_be_able_to_see_message(String message) {
        assertThat(deleteFilePage.getContent(), hasItem(containsString(message)));
    }
}
