package pages;

import baseactions.BaseActions;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pageobjects.SearchResultPageObjects.*;
import static stringvariables.CommonStrings.*;
import static stringvariables.SearchResultPageStrings.*;

public class SearchResultPage extends BaseActions {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
    ProductDetailPage productDetailPage = new ProductDetailPage(driver);
    public void selectRandomProduct() {
        assertVisible(SEARCH_RESULT_PRODUCT_LST,SEARCH_RESULT_PRODUCT_LST_VISIBLE_ERR_MSG,DEFAULT_WAIT);
        clickElementByIndex(SEARCH_RESULT_PRODUCT_LST,generateRandomNumber(0,findElements(SEARCH_RESULT_PRODUCT_LST).size()-1));
        productDetailPage.writeToFileProductInfo();
    }
    public void fillInSearchBox(String searchText) {
        fillInVisibleElement(SEARCH_RESULT_SEARCH_TXT,MAINPAGE_SEARCHBOX_VISIBLE_ERR_MSG,searchText,DEFAULT_WAIT);
    }
    public void clearSearchBox() {
        clearVisibleElement(SEARCH_RESULT_SEARCH_TXT,MAINPAGE_SEARCHBOX_VISIBLE_ERR_MSG,DEFAULT_WAIT);
        assertEquals(getText(SEARCH_RESULT_SEARCH_TXT),"",SEARCH_RESULT_SEARCHBOX_EMPTY_ERR_MSG);
    }
}
