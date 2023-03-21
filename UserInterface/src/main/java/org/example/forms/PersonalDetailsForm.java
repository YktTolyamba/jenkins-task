package org.example.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PersonalDetailsForm extends Form {
    private static final By pageUniqueLocator = By.xpath("//div[@class='personal-details']");
    private static final String name = "Personal details";

    public PersonalDetailsForm() {
        super(pageUniqueLocator, name);
    }


}
