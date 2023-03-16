package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.MainPageObject;
import pageobjects.OrderPageObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPositiveTest extends BaseTest {

    @Parameterized.Parameter(value = 0)
    public int indexOfOrderButton;

    @Parameterized.Parameter(value = 1)
    public String firstName;

    @Parameterized.Parameter(value = 2)
    public String lastName;

    @Parameterized.Parameter(value = 3)
    public String addressForOrder;

    @Parameterized.Parameter(value = 4)
    public String phoneNumber;

    @Parameterized.Parameter(value = 5)
    public String subwayStationNumber;

    @Parameterized.Parameter(value = 6)
    public String dateOfOrder;

    @Parameterized.Parameter(value = 7)
    public String timeOfArend;

    @Parameterized.Parameter(value = 8)
    public String color;

    @Parameterized.Parameter(value = 9)
    public String comment;

    @Parameterized.Parameters(name = "{index}: выбираем кнопку с индексом {0} и заказываем на {1} {2}")
    public static Object[][] doOrderWithAllCorrectData() {
        return new Object[][] {
                {0, "Имя", "Фамилия", "Какой-то адрес", "89039055111", "5", "22.05.2023", "сутки", "black", "Привезите вовремя"}
        };
    }


    //можно сделать assert, что все тестовые данные, введенные в поля, соответствуют данным в заказе
    @Test
    public void createOrderTest() {
        MainPageObject mainPage = new MainPageObject(driver);

        OrderPageObject orderPage = mainPage.
                clickOnOrderButtonByIndex(indexOfOrderButton).
                fillFirstNameField(firstName).
                fillSecondNameField(lastName).
                fillAddressField(addressForOrder).
                chooseSubwayStationFromList(subwayStationNumber).
                fillPhoneNumberField(phoneNumber).
                pressProceed().
                chooseTimeOfArend(timeOfArend).
                chooseColor(color).
                fillDateOfOrderField(dateOfOrder).
                fillCommentField(comment).
                pressDoOrder().pressOK();

        assertTrue(orderPage.orderIsCreated());
/*
        String numberOfOrderCreated = orderPage.getOrderNumberFromPage();
        String numberOfOrder = orderPage.goToSeeYourOrder().getNumberOfOrderFromField();

        assertEquals("Номер созданного заказа и номер просмотренного не соответствуют",
                numberOfOrderCreated, numberOfOrder);
*/
    }


}
