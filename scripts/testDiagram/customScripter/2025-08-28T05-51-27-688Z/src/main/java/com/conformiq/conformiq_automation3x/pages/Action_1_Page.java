package com.conformiq.conformiq_automation3x.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.conformiq.conformiq_automation3x.utilities.PageObjectBase;
import com.conformiq.conformiq_automation3x.utilities.WebController;
public class Action_1_Page extends PageObjectBase {
    @FindBy(how = How.ID, using = "fill_text_area__associated_data_username_1")
    private WebElement fill_text_area__associated_data_username_1;
    public void enter___fill_text_area__associated_data_username_1(String text) {
        fill_text_area__associated_data_username_1.clear();
        fill_text_area__associated_data_username_1.sendKeys(text);
    }

    @FindBy(how = How.ID, using = "fill_text_area__associated_data_password_2")
    private WebElement fill_text_area__associated_data_password_2;
    public void enter___fill_text_area__associated_data_password_2(String text) {
        fill_text_area__associated_data_password_2.clear();
        fill_text_area__associated_data_password_2.sendKeys(text);
    }

}