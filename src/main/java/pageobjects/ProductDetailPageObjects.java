package pageobjects;

import org.openqa.selenium.By;
public class ProductDetailPageObjects {
    private ProductDetailPageObjects() {
    }
    public static final By PRODUCT_DETAIL_DESCRIPTION = By.cssSelector(".o-productDetail__description");
    public static final By PRODUCT_DETAIL_PRICE = By.cssSelector("#priceNew");
    public static final By PRODUCT_DETAIL_SIZE_LST = By.xpath("//div[@class='m-variation']//span[not(contains(@class, '-disabled'))]");
    public static final By PRODUCT_DETAIL_ADD_BASKET_BTN = By.cssSelector("#addBasket");
    public static final By PRODUCT_DETAIL_GOTO_BASKET_BTN = By.cssSelector(".m-notification__button.btn");
}
