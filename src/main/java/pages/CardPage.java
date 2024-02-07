package pages;

import baseactions.BaseActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pageobjects.CardPageObjects.*;
import static stringvariables.CardPageStrings.*;

public class CardPage extends BaseActions {
    public CardPage(WebDriver driver) {
        super(driver);
    }
    public void productDetailAndBasketPriceComparison(ProductDetailPage productDetailPage) {
        double productPrice = productDetailPage.getPrice();
        double basketPrice = Double.parseDouble(getText(CARD_PRODUCT_PRICE_LBL).split(" ")[0].replace(".", "").replace(",", "."));
        compareValues(productPrice,basketPrice,CARDPAGE_PRICE_COMPARE_ERR_MSG);
    }
    public void selectQuantityAndSelectedControl(Integer quantity) {
        assertVisible(CARD_QUANTITY_SELECT_DRP,CARDPAGE_SELECT_QUANTITY_VISIBLE_ERR_MSG,DEFAULT_WAIT);
        assertTrue(findElements(CARD_QUANTITY_SELECT_LST).size() >= quantity,quantity+ CARDPAGE_SELECT_QUANTITY_SIZE_ERR_MSG);
        WebElement selectionElement = findElement(CARD_QUANTITY_SELECT_DRP,DEFAULT_WAIT);
        String selectedValue = selectOption(selectionElement,quantity.toString());
        compareValues(selectedValue.split(" ")[0],quantity.toString(),CARDPAGE_SELECT_QUANTITY_COMPARE_ERR_MSG);
    }
    public void deleteProductAndEmptyControlFromBasket() {
        clickVisibleElement(CARD_DELETE_BTN,CARDPAGE_DELETE_BTN_VISIBLE_ERR_MSG,DEFAULT_WAIT);
        assertVisible(CARD_EMPTY_OBJ, CARDPAGE_EMPTY_VISIBLE_ERR_MSG,DEFAULT_WAIT);
        compareValues(getText(CARD_EMPTY_OBJ),CARDPAGE_EMPTY_MSG,CARDPAGE_EMPTY_MSG_ERR_MSG);
    }
}
