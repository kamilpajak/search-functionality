# Auto Hero - QA challenge
*Solution of task: create automation test for search functionality*

* Open https://www.autohero.com/de/search/
* Filter for First registration (Erstzulassung). Filter for FROM 2015
* Apply Filter
* Sort cars by Price Descending (Höchster Preis)
* Verify all cars are filtered by first registration (2015+)
* Verify all cars are sorted by price descending

## Prerequisites

You can change `application.properties` file to match your needs:

```
# ===================================================================
# AUTO HERO - QA CHALLENGE
# ===================================================================
autohero.environment=production
autohero.language=de
# Optional ----------------------------------------------------------
selenium.grid=
selenide.timeout=5
```