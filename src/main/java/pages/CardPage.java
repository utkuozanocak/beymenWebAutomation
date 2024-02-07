package pages;

import baseactions.BaseActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static pageobjects.BasketPageObjects.*;

public class BasketPage extends BaseActions {
    public BasketPage(WebDriver driver) {
        super(driver); // BaseActions sınıfının constructor'ını çağırarak WebDriver'ı ayarla
    }

    public void productDetailAndBasketPriceComparison(ProductDetailPage productDetailPage) {
        double productPrice = productDetailPage.getPrice();
        double basketPrice = Double.parseDouble(getText(BASKET_PRODUCT_PRICE_LBL).split(" ")[0].replace(".", "").replace(",", "."));
        compareValues(productPrice,basketPrice,"Ürün Detaydaki Fiyat İle Sepetteki Fiyat Aynı Değil");
    }
    public void selectQuantity(Integer quantity) {
        assertVisible(BASKET_QUANTITY_SELECT_DRP,"Adet Seçim Alanı Görüntülenemedi",5);
        assertTrue(findElements(BASKET_QUANTITY_SELECT_LST).size() >= quantity,quantity+ " Adet Seçim Yapılacak Sayı Bulunamadı");
        WebElement selectionElement = findElement(BASKET_QUANTITY_SELECT_DRP,5);
        String selectedValue = selectOption(selectionElement,quantity.toString());
        compareValues(selectedValue.split(" ")[0],quantity.toString(),"Seçilen Adet İle Görüntülenen Adet Aynı Değil");
    }
    public void deleteProductFromBasket() {
        clickVisibleElement(BASKET_DELETE_BTN,"Sepette Sil Butonu Görüntülenemedi",5);
    }
}
