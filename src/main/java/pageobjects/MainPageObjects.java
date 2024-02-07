package pageobjects;

import org.openqa.selenium.By;
public class MainPageObjects {
    private MainPageObjects() {
    }
    public static final By MAINPAGE_ACCEPT_ALL_COOKIE_BTN = By.cssSelector("button#onetrust-accept-btn-handler");
    public static final By MAINPAGE_GENDER_MAN_BTN = By.cssSelector("#genderManButton");
    public static final By MAINPAGE_GENDER_WOMAN_BTN = By.cssSelector("#genderWomanButton");
    public static final By MAINPAGE_INFO_POPUP_NOTALLOW_BTN = By.cssSelector(".dn-slide-buttons.horizontal > .dn-slide-deny-btn");
    public static final By MAINPAGE_INFO_POPUP_ALLOW_BTN = By.cssSelector(".dn-slide-buttons.horizontal > .dn-slide-accept-btn");
    public static final By MAINPAGE_BANNER = By.cssSelector(".o-header a[title='Beymen']");
    public static final By MAINPAGE_SEARCH_TXT = By.cssSelector(".o-header__search--input");
    public static final By MAINPAGE_GOTO_BASKET_BTN = By.cssSelector("a[title='Sepetim'] > .o-header__userInfo--text");
}
