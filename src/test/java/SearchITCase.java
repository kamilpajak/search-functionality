import common.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobject.LandingPage;
import pageobject.SearchPage;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static common.Sorting.PRICE_DESCENDING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchITCase {

    private final LandingPage landingPage = new LandingPage();

    @Test
    @DisplayName("User displays cars with first registration from a specific year, sorted by price descending")
    void userDisplaysCarsWithFirstRegistrationFromSpecificYearSortedByPriceDescending() {
        SearchPage searchPage = landingPage.goToSearch();

        final int firstRegistrationFrom = 2015;
        List<Vehicle> searchResults = searchPage
                .filterYear(Integer.toString(firstRegistrationFrom))
                .sortResults(PRICE_DESCENDING)
                .getSearchResults();

        searchResults.stream()
                .map(Vehicle::getFirstRegistration)
                .forEach(firstRegistration -> assertTrue(firstRegistration.getYear() >= firstRegistrationFrom));

        List<BigDecimal> returnedPrices = searchResults.stream()
                .map(Vehicle::getPrice)
                .collect(Collectors.toList());
        assertEquals(returnedPrices.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList()),
                returnedPrices);
    }
}
