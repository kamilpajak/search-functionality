package pageobject;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    public SearchPage filterYear(String value) {
        $("[data-qa-selector=\"filter-year\"]").click();
        $("[name=\"yearRange.min\"]").selectOption(value);
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
        return this;
    }

    public List<Car> getResults() {

        return List.of();
    }

    public enum Sorting {
        PRICE_ASCENDING,
        PRICE_DESCENDING,
        MILEAGE_ASCENDING,
        MILEAGE_DESCENDING,
        MANUFACTURER_ASCENDING,
        MANUFACTURER_DESCENDING
    }
}
