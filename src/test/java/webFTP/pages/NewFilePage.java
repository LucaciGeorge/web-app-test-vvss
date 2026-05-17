package webFTP.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class NewFilePage extends PageObject {
    @FindBy(name="entry")
    private WebElementFacade fileName;

    @FindBy(name="text")
    private WebElementFacade textArea;

    @FindBy(xpath="//*[@id=\"EditForm\"]/table/tbody/tr[1]/td[1]/a[2]/img")
    private WebElementFacade saveButton;

    @FindBy(xpath="//*[@id=\"EditForm\"]/table/tbody/tr[1]/td[1]/a[1]/img")
    private WebElementFacade backButtonBtn;

    @FindBy(css = "p.error-box")
    private WebElementFacade errorBox;

    public void enter_file_name(String file) {
        fileName.type(file);
    }

    public void enter_file_content(String content) {
        textArea.clear();
        textArea.type(content);
    }

    public void click_save_file() {
        saveButton.click();
    }

    public void back() {
        backButtonBtn.click();
    }

    public boolean is_save_button_visible() {
        return saveButton.isVisible();
    }

    public String get_error_message() {
        return errorBox.getText();
    }


    public List<String> getContent() {
        WebElementFacade definitionList = find(By.tagName("div"));
        return definitionList.findElements(By.tagName("form")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }
}
