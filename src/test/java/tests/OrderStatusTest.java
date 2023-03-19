package tests;

import org.junit.Test;
import pageobjects.MainPageObject;

import static org.junit.Assert.assertTrue;

public class OrderStatusTest extends BaseTest {

    @Test
    public void checkErrorInCaseOfNonexistentOrder(){
        boolean isOrderNotFoundVisible = new MainPageObject(driver).
                getHeader().
                clickCheckOrderStatusButton().
                setOrderNumberInField("абракадабра").
                clickGoToOrderButton().
                isOrderNotFoundErrorVisible();

        assertTrue(isOrderNotFoundVisible);
    }
}
