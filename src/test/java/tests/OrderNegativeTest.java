package tests;

import org.junit.Test;
import pageobjects.MainPageObject;

import static org.junit.Assert.assertTrue;

public class OrderNegativeTest extends BaseTest {

    public int indexOfOrderButton = 0;
    public String firstName = "Тестик";
    public String lastName = "Тестов";
    public String addressForOrder = "Нужный адрес";
    public String phoneNumber = "+79444444444";

    //написал для поля "Имя" - остальные должны проверяться по аналогии
    @Test
    public void checkErrorWithEmptyFirstNameField() {
        boolean isErrorMessageDisplayedInNameField = new MainPageObject(driver).
                clickOnOrderButtonByIndex(indexOfOrderButton).
                fillSecondNameField(lastName).
                fillAddressField(addressForOrder).
                chooseSubwayStationFromList("3").
                fillPhoneNumberField(phoneNumber).
                pressProceed().
                errorMessageIdDisplayedInInputField();

        assertTrue(isErrorMessageDisplayedInNameField);
    }

    //также написал тест для поля "Адрес" - здесь баг, он не валидируется (как пример теста, который находит ошибку)
    @Test
    public void checkErrorWithEmptyAddressField() {
        boolean isErrorMessageDisplayedInNameField = new MainPageObject(driver).
                clickOnOrderButtonByIndex(indexOfOrderButton).
                fillFirstNameField(firstName).
                fillSecondNameField(lastName).
                chooseSubwayStationFromList("10").
                fillPhoneNumberField(phoneNumber).
                pressProceed().
                errorMessageIdDisplayedInInputField();

        assertTrue(isErrorMessageDisplayedInNameField);
    }


    //и тест на поле выбора станции метро, т.к. здесь другой селектор
    @Test
    public void checkErrorWithEmptySubwayStationField() {
        boolean isErrorMessageDisplayedForMetro = new MainPageObject(driver).
                clickOnOrderButtonByIndex(indexOfOrderButton).
                fillFirstNameField(firstName).
                fillSecondNameField("Тест").
                fillAddressField(addressForOrder).
                fillPhoneNumberField(phoneNumber).
                pressProceed().errorMessageIsDisplayedInMetroChooser();

        assertTrue(isErrorMessageDisplayedForMetro);
    }

}
