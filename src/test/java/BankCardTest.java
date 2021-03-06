import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.*;


class BankCardTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestName() {

        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Petr Vasiliev");
        form.$("[data-test-id=phone] input").setValue("+79162875634");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestNameBlank() {

        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79162875634");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestTel() {

        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Яша Пупкин");
        form.$("[data-test-id=phone] input").setValue("89992223344");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }


    @Test
    void shouldTestTelBlank() {

        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Яша Пупкин");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestCheckbox() {

        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Яша Пупкин");
        form.$("[data-test-id=phone] input").setValue("+79867775544");
        form.$("[data-test-id=agreement] .checkbox__box");
        form.$("[role=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(Condition.exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
