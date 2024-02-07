package pages;

import baseactions.BaseActions;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.fail;
import static pageobjects.ProductDetailPageObjects.*;
import static stringvariables.ProductDetailPageStrings.*;

public class ProductDetailPage extends BaseActions {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }
    private double price;
    public void writeToFileProductInfo() {
        writeToFile(PRODUCT_DETAIL_PRODUCT_STR+getText(PRODUCT_DETAIL_DESCRIPTION));
        writeToFile(PRODUCT_DETAIL_PRICE_STR+getText(PRODUCT_DETAIL_PRICE));
    }
    public void addToBasket() {
        if (isElementVisible(PRODUCT_DETAIL_SIZE_LST,DEFAULT_WAIT) && !findElements(PRODUCT_DETAIL_SIZE_LST).isEmpty()) {
            clickElementByIndex(PRODUCT_DETAIL_SIZE_LST,generateRandomNumber(0,findElements(PRODUCT_DETAIL_SIZE_LST).size()-1));
            clickVisibleElement(PRODUCT_DETAIL_ADD_BASKET_BTN,PRODUCT_DETAIL_ADD_TO_CARD_BTN_ERR_MSG,DEFAULT_WAIT);
            setPrice(Double.parseDouble(getText(PRODUCT_DETAIL_PRICE).split(" ")[0].replace(".","")));
        } else {
            fail(PRODUCT_DETAIL_SIZE_LST_ERR_MSG);
        }
    }
    public void goToBasketWithPopUp() {
        clickVisibleElement(PRODUCT_DETAIL_GOTO_BASKET_BTN,PRODUCT_DETAIL_GO_TO_CARD_BTN_ERR_MSG,DEFAULT_WAIT);
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
