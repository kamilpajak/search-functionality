package pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.Sorting;
import common.Vehicle;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static java.time.temporal.ChronoField.DAY_OF_MONTH;

public class SearchPage {

    private static final Logger log = LogManager.getLogger(SearchPage.class);

    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("MM/yyyy")
            .parseDefaulting(DAY_OF_MONTH, 1)
            .toFormatter();

    public SearchPage filterYear(String value) {
        $("[data-qa-selector=\"filter-year\"]").click();
        $("[name=\"yearRange.min\"]").selectOption(value);
        $("[data-qa-selector=\"active-filter\"]").shouldBe(Condition.visible);
        return this;
    }

    public SearchPage sortResults(Sorting sorting) {
        switch (sorting) {
            case PRICE_ASCENDING:
                $("[name=\"sort\"]").selectOption(1);
                break;
            case PRICE_DESCENDING:
                $("[name=\"sort\"]").selectOption(2);
                break;
            case MILEAGE_ASCENDING:
                $("[name=\"sort\"]").selectOption(3);
                break;
            case MILEAGE_DESCENDING:
                $("[name=\"sort\"]").selectOption(4);
                break;
            case MANUFACTURER_ASCENDING:
                $("[name=\"sort\"]").selectOption(5);
                break;
            case MANUFACTURER_DESCENDING:
                $("[name=\"sort\"]").selectOption(6);
                break;
        }
        refresh();
        return this;
    }

    public List<Vehicle> getSearchResults() {
        ElementsCollection specificationElements = $$("[data-qa-selector=\"results-found\"] [data-qa-selector=\"ad\"]");
        return specificationElements.stream()
                .map(toVehicle())
                .peek(log::debug)
                .collect(Collectors.toList());
    }

    private Function<SelenideElement, Vehicle> toVehicle() {
        return specification -> {
            String priceText = specification.$("[data-qa-selector=\"price\"]").getText();
            String firstRegistrationText = specification.$("[data-qa-selector=\"spec\"]", 0).getText();
            return Vehicle.builder()
                    .price(new BigDecimal(StringUtils.getDigits(priceText)))
                    .firstRegistration(LocalDate.parse(firstRegistrationText.replaceAll("[^\\d/]", StringUtils.EMPTY), dateTimeFormatter))
                    .build();
        };
    }
}
