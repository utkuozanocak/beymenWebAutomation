package pageobjects;

import org.openqa.selenium.By;
public class SearchResultPageObjects {
    private SearchResultPageObjects() {
    }
    public static final By SEARCH_RESULT_PRODUCT_LST = By.cssSelector(".o-productList__item");
    public static final By SEARCH_RESULT_SEARCH_TXT = By.cssSelector("#o-searchSuggestion__input");
}
