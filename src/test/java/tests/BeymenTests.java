package tests;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CardPage;
import pages.MainPage;
import pages.ProductDetailPage;
import pages.SearchResultPage;

public class BeymenTests extends BaseTest {
    @Test
    @DisplayName("TS0001 : Arama ve Sepet Kontrol")
    public void searchProductAndBasketControlTest() {
        MainPage mainPage = new MainPage(getDriver());
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
        CardPage cardPage = new CardPage(getDriver());
        mainPage.mainPageIsOpenControl();
        mainPage.acceptAllCookies();
        mainPage.clickGenderButton("erkek");
        mainPage.clickInfoPopUpAllowOrNotAllowButton("notallow");
        mainPage.fillInSearchBox(mainPage.getExcelDataWithRowAndColumn(0,0));
        searchResultPage.clearSearchBox();
        searchResultPage.fillInSearchBox(mainPage.getExcelDataWithRowAndColumn(0,1));
        mainPage.pressEnterFromSearchBox();
        searchResultPage.selectRandomProduct();
        productDetailPage.addToBasket();
        //mainPage.goToBasketFromMainPage();
        productDetailPage.goToBasketWithPopUp();
        cardPage.productDetailAndBasketPriceComparison(productDetailPage);
        cardPage.selectQuantityAndSelectedControl(2);
        cardPage.deleteProductAndEmptyControlFromBasket();
    }
}
