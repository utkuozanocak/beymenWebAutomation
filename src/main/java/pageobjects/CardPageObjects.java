package pageobjects;

import org.openqa.selenium.By;
public class CardPageObjects {
    private CardPageObjects() {
    }
    public static final By CARD_PRODUCT_PRICE_LBL = By.cssSelector(".m-productPrice__salePrice");
    public static final By CARD_QUANTITY_SELECT_DRP = By.cssSelector(".m-select.-small select.a-selectControl.-small");
    public static final By CARD_QUANTITY_SELECT_LST = By.cssSelector(".m-select.-small select.a-selectControl.-small > option");
    public static final By CARD_DELETE_BTN = By.cssSelector(".m-basket__remove");
    public static final By CARD_EMPTY_OBJ = By.cssSelector("#emtyCart");
}
