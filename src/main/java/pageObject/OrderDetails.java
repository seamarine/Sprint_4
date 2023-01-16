package pageObject;

import model.Order;
import org.openqa.selenium.*;

public class OrderDetails {

    private final WebDriver driver;
    public OrderDetails(WebDriver driver) {
        this.driver = driver;
    }

    // Кнопка Заказать сверху
    private final By buttonOrderInHeader = By.xpath("//button[@class='Button_Button__ra12g']");

    // Кнопка Заказать снизу
    private final By buttonOrderDown = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    // Первая форма заказа
    // Поле Имя
    private final By fieldName = By.xpath(".//input[@placeholder = '* Имя']");

    // Поле Фамилия
    private final By fieldLastName = By.xpath(".//input[@placeholder = '* Фамилия']");

    // Поле Адрес: куда привезти заказ
    private final By fieldAddress = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");

    // Выпадающий список Станция метро
    private final By metroStationList = By.xpath(".//input[@class='select-search__input']");

    // Поле Телефон: на него позвонит курьер
    private final By fieldPhoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");

    // Кнопка Далее
    private final By buttonFurther = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    // Вторая форма заказа
    // Поле с календарем Когда привезти самокат
    private final By fieldOrderDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Выпадающий список Срок аренды
    private final By rentalPeriodField = By.className("Dropdown-placeholder");

    // Динамическая подстановка даты
    private String rentalPeriodDropDown = "//div[@class ='Dropdown-option' and text()='%s']";

    // Чекбоксы Цвет самоката
    private String colourScooter = "//label[@for='%s']";

    // Комментарий для курьера
    private final By fieldCommentForCourier = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка Заказать после заполнения информации о заказе
    private final By buttonSignedOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    // Заказ успешно оформлен
    private final By finalOrder = By.xpath("//div[@class='Order_Text__2broi']");


    public OrderDetails fillOrderDetails(Order order) {
        choiceButton(order.getButton());
        fillFieldName(order.getName());
        fillFieldLastName(order.getLastName());
        fillFieldAddress(order.getAddress());
        fillMetroStationList(order.getMetroStation());
        fillFieldPhoneNumber(order.getPhoneNumber());
        clickButtonFurther();
        fillFieldOrderDate(order.getOrderDate());
        fillRentalPeriodField(order.getRentalPeriod());
        fillScooterColour(order.getScooterColour());
        fillFieldCommentForCourier(order.getCommentForCourier());
        clickButtonSignedOrder();

        return this;
    }


    private OrderDetails finalOrderText() {
        driver.findElement(finalOrder).getText();
        return this;
    }

    private OrderDetails choiceButton(String button) {
        if (button.equals("Верхняя кнопка")) {
            driver.findElement(buttonOrderInHeader).click();
        }
        if (button.equals("Нижняя кнопка")) {
            WebElement element = driver.findElement(buttonOrderDown);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            element.click();
        }
        return new OrderDetails(driver);
    }

    private OrderDetails fillFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
        return this;
    }

    private OrderDetails fillFieldLastName(String lastName) {
        driver.findElement(fieldLastName).sendKeys(lastName);
        return this;
    }

    private OrderDetails fillFieldAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
        return this;
    }

    private OrderDetails fillMetroStationList(String metroStation) {
        driver.findElement(metroStationList).sendKeys(metroStation + Keys.ARROW_DOWN + Keys.ENTER);
        return this;
    }

    private OrderDetails fillFieldPhoneNumber(String phone) {
        driver.findElement(fieldPhoneNumber).sendKeys(phone);
        return this;
    }
    private OrderDetails clickButtonFurther() {
        driver.findElement(buttonFurther).click();
        return this;
    }

    //Выбор даты
    private OrderDetails fillFieldOrderDate(String orderDate) {
        driver.findElement(fieldOrderDate).click();
        driver.findElement(fieldOrderDate).sendKeys(orderDate + Keys.ENTER);
        return this;
    }

    private OrderDetails fillRentalPeriodField(String rentalPeriod) {
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(String.format(rentalPeriodDropDown, rentalPeriod))).click();
        return this;
    }

    private OrderDetails fillScooterColour(String scooterColour) {
        driver.findElement(By.xpath(String.format(colourScooter, scooterColour))).click();
        return this;
    }

    private OrderDetails fillFieldCommentForCourier(String orderComment) {
        driver.findElement(fieldCommentForCourier).click();
        driver.findElement(fieldCommentForCourier).sendKeys(orderComment);
        return this;
    }

    private OrderDetails clickButtonSignedOrder() {
        driver.findElement(buttonSignedOrder).click();
        return this;
    }

    public boolean isOrderTrue() {
        return driver.findElement(finalOrder).isDisplayed();
    }
}