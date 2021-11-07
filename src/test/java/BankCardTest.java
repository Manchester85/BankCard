import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

class BankCardTest {

    @Test
    void shouldTestName(){

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Petr Vasiliev");
        form.$("[data-test-id=phone] input").setValue("+79162875634");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
        }

        @Test
    void shouldTestNameBlank(){

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79162875634");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $("[class=input__sub]").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestTel(){

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Яша Пупкин");
        form.$("[data-test-id=phone] input").setValue("89992223344");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $$("[class=input__sub]").shouldHave(CollectionCondition.exactTexts("Укажите точно как в паспорте, Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        }

    @Test
    void shouldTestTelBlank(){

        open("http://localhost:9999");
        SelenideElement form = $("[action]");
        form.$("[data-test-id = name] input").setValue("Яша Пупкин");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button]").click();
        $$("[class=input__sub]").shouldHave(CollectionCondition.exactTexts("Укажите точно как в паспорте, Поле обязательно для заполнения"));
    }

}
