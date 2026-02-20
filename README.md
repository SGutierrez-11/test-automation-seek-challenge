# Test Automation Seek Challenge

Test Automation Framework for the Seek QA Challenge. Built with **Java**, **Selenium**, and **Cucumber** using a hybrid Page Object Model (POM) / Screenplay architecture. Allure Reporting is integrated for detailed test result visualization.

## Tech Stack

- Java 17
- Selenium 4
- Cucumber 7
- TestNG
- Allure Report
- Maven

## Running Tests Locally

```bash
mvn clean test
```

To generate and open the Allure report locally:

```bash
mvn allure:serve
```

## CI/CD Pipeline

The GitHub Actions pipeline (`.github/workflows/pipeline.yml`) runs automatically on every push to `main` and can also be triggered manually via `workflow_dispatch`.

### Pipeline Steps

1. **Checkout** – checks out the repository.
2. **Set up JDK 17** – configures the Java environment.
3. **Setup Chrome** – installs Chrome 120 for Selenium.
4. **Run Tests** – executes `mvn clean test` via `xvfb-run`. The step uses `continue-on-error: true` so the pipeline continues even when tests fail.
5. **Load test report history** – checks out the `gh-pages` branch to preserve historical Allure trend data.
6. **Generate Allure Report** – uses [simple-elf/allure-report-action](https://github.com/simple-elf/allure-report-action) to build the HTML report with history.
7. **Upload allure-results artifact** – uploads raw Allure results for debugging (retained 7 days).
8. **Upload allure-report artifact** – uploads the generated HTML report as a CI artifact (retained 7 days).
9. **Deploy Report to GitHub Pages** – pushes the `allure-history/` directory to the `gh-pages` branch using [peaceiris/actions-gh-pages](https://github.com/peaceiris/actions-gh-pages).

All report-related steps run with `if: always()` to ensure the report is generated and published even when one or more tests fail.

## Allure Report (GitHub Pages)

The latest Allure report is published to GitHub Pages and is available at:

```
https://sgutierrez-11.github.io/test-automation-seek-challenge/
```

The report is updated on every CI run (including runs with failing tests). Each run appends a new entry to the historical trend so you can track quality over time.
