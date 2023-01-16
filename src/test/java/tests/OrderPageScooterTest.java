package tests;

import pageObject.MainPage;
import model.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObject.OrderDetails;

@RunWith(Parameterized.class)
public class OrderPageScooterTest extends BaseUITest {
    private final Order orderData;

    public OrderPageScooterTest(Order orderData) {
        this.orderData = orderData;
    }

    @Parameterized.Parameters
    public static Object[] getData() {
        return new Object[]{
                new Order("Верхняя кнопка", "Тест", "Юзер", "Тестовый адрес",
                        "Автозаводская", "88975677733", "13.01.2023",
                        "сутки", "grey", "Позвоните, как будете у метро"),

                new Order("Нижняя кнопка", "Марина", "Семенова",
                        "Город Королев", "Комсомольская", "89773452312",
                        "14.01.2023", "четверо суток", "black",
                        "Вопросов нет")
        };
    }

    @Test
    public void orderScooter() {

        boolean isInputDataBlockDisplayed = new OrderDetails(driver)
                .fillOrderDetails(orderData)
                .isOrderTrue();
        Assert.assertTrue("Заказ не был оформлен", isInputDataBlockDisplayed);

    }
}

