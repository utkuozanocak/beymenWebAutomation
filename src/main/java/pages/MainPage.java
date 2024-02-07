package pages;

import baseactions.BaseActions;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.fail;
import static pageobjects.MainPageObjects.*;
import static pageobjects.SearchResultPageObjects.*;
import static stringvariables.CommonStrings.*;
import static stringvariables.MainPageStrings.*;

public class MainPage extends BaseActions {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public void acceptAllCookies() {
        clickSeleniumElement(MAINPAGE_ACCEPT_ALL_COOKIE_BTN);
    }
    public void clickGenderButton(String gender) {
        switch (gender) {
            case MAINPAGE_WOMAN_STR:
                ifElementIsVisibleClick(MAINPAGE_GENDER_WOMAN_BTN);
                break;
            case MAINPAGE_MAN_STR:
                ifElementIsVisibleClick(MAINPAGE_GENDER_MAN_BTN);
                break;
            default:
                fail(ERROR_INPUT_MSG+gender);
        }
    }
    public void clickInfoPopUpAllowOrNotAllowButton(String button) {
        switch (button) {
            case MAINPAGE_ALLOW_STR:
                ifElementIsVisibleClick(MAINPAGE_INFO_POPUP_ALLOW_BTN);
                break;
            case MAINPAGE_NOTALLOW_STR:
                ifElementIsVisibleClick(MAINPAGE_INFO_POPUP_NOTALLOW_BTN);
                break;
            default:
                fail(ERROR_INPUT_MSG+button);
        }
    }
    public void mainPageIsOpenControl() {
        assertVisible(MAINPAGE_BANNER,MAINPAGE_VISIBLE_ERR_MSG,DEFAULT_WAIT);
    }
    public void fillInSearchBox(String searchText) {
        fillInVisibleElement(MAINPAGE_SEARCH_TXT,MAINPAGE_SEARCHBOX_VISIBLE_ERR_MSG,searchText,DEFAULT_WAIT);
    }
    public void pressEnterFromSearchBox() {
        pressEnterVisibleElement(SEARCH_RESULT_SEARCH_TXT,MAINPAGE_SEARCHBOX_VISIBLE_ERR_MSG,DEFAULT_WAIT);
    }
    public void goToBasketFromMainPage() {
        clickVisibleElement(MAINPAGE_GOTO_BASKET_BTN,MAINPAGE_MYCARD_BTN_VISIBLE_ERR_MSG,DEFAULT_WAIT);
    }
}
