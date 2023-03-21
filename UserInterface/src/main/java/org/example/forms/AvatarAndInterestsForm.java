package org.example.forms;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.example.utils.RandomUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class AvatarAndInterestsForm extends Form {
    private String selectAllCheckboxIndexValue = "/selectAllCheckboxIndex";
    private String unselectAllCheckboxIndexValue = "/unselectAllCheckboxIndex";
    private static final By pageUniqueLocator = By.xpath("//div[@class='avatar-and-interests']");
    private static final String name = "Avatar and interests";
    private IButton uploadButton = getElementFactory().getButton(By.xpath("//a[contains(@class, 'upload-button')]"), "Upload button");
    private ITextBox uploadedImage = getElementFactory().getTextBox(By.xpath("//div[contains(@class, 'avatar-image')]"), "Uploaded image");
    private IButton nextButton = getElementFactory().getButton(By.xpath("//button[text()='Next']"),"Next button");
    private By checkboxLocator = new By.ByXPath("//span[ contains ( @class, 'checkbox__box' ) ]");
    private List<Integer> checkboxIndexesToAvoid = new ArrayList<>();
    public AvatarAndInterestsForm() {
        super(pageUniqueLocator, name);
    }

    public void clickOnUploadButton() {
        uploadButton.click();
    }

    public void waitForUploadedImageToAppear() {
        uploadedImage.state().waitForDisplayed();
    }

    public void checkRandomInterests(int numberOfInterests) {
        ISettingsFile config = new JsonSettingsFile("config.json");
        int unselectAllCheckboxIndex = Integer.parseInt(config.getValue(unselectAllCheckboxIndexValue).toString());
        int selectAllCheckboxIndex = Integer.parseInt(config.getValue(selectAllCheckboxIndexValue).toString());
        List<ICheckBox> checkBoxes = getElementFactory().findElements( checkboxLocator, ElementType.CHECKBOX );
        checkBoxes.get( unselectAllCheckboxIndex ).check();
        checkboxIndexesToAvoid.add(selectAllCheckboxIndex);
        checkboxIndexesToAvoid.add(unselectAllCheckboxIndex);
        for (int i = 0; i < numberOfInterests; i++) {
            int index = getRandomCheckBoxIndex( checkBoxes.size() );
            checkBoxes.get( index ).check();
            checkboxIndexesToAvoid.add(index);
        }
    }

    private Integer getRandomCheckBoxIndex(int end) {
        int number = RandomUtils.getNumber(end);
        while ( checkboxIndexesToAvoid.contains(number) ) {
            number = RandomUtils.getNumber(end);
        }
        return number;
    }

    public void clickNextButton() {
        nextButton.click();
    }

}
