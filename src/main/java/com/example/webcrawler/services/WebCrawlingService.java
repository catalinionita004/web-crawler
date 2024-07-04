package com.example.webcrawler.services;

import com.example.webcrawler.data.CrawlerConfiguration;
import com.example.webcrawler.data.Pair;
import com.example.webcrawler.data.Product;
import com.example.webcrawler.data.ProductSpecification;
import com.example.webcrawler.exceptions.Error;
import com.example.webcrawler.exceptions.NotFoundException;
import com.example.webcrawler.exceptions.*;
import com.example.webcrawler.utils.ErrorType;
import com.example.webcrawler.utils.FieldType;
import com.example.webcrawler.utils.ThreadSleeper;
import com.example.webcrawler.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.webcrawler.utils.Constans.*;

@Service
public class WebCrawlingService {

    private static final Logger logger = LoggerFactory.getLogger(WebCrawlingService.class);

    @Value("${webdriver.headless}")
    private boolean headless;


    @Value("${spring.profiles.active}")
    private String profile;


    public List<Product> searchOnDetailView(CrawlerConfiguration crawlerConfiguration, String category, List<String> urls) {
        logger.info("searchOnDetailView(): ENTER -> retailerUuid = {}, retailerName={}, category={},  urls={}", crawlerConfiguration.getRetailerUuid(), crawlerConfiguration.getRetailerName(), category, urls);
        List<Product> products = new ArrayList<>();
        WebDriver driver = getWebDriver();
        boolean isFirstCookies = true;
        boolean isFirstOther = true;
        List<Error> errorList = new ArrayList<>();
        driver.get(crawlerConfiguration.getMainPageUrl());
        isFirstCookies = pressButtonsOnPage(driver, crawlerConfiguration, isFirstCookies, crawlerConfiguration.getProductCookiesButtonPaths(), crawlerConfiguration.getMainPageUrl(), PRODUCT_COOKIES_BUTTON, false, 1, 10, 500);
        for (String url : urls) {
            logger.info("searchOnDetailView(): url={}", url);

            Pair<Product, Pair<Boolean, Boolean>> pair = searchItemOnDetailView(crawlerConfiguration.getRetailerUuid(), crawlerConfiguration.getRetailerName(), category, driver, url, isFirstCookies, isFirstOther, crawlerConfiguration, errorList, 3, 500);

            isFirstCookies = pair.getSecond().getFirst();
            isFirstOther = pair.getSecond().getSecond();
            Product product = pair.getFirst();


            products.add(product);
        }

        driver.quit();
        if (!errorList.isEmpty()) {
            errorList.forEach(error -> logger.error("ERROR  ={}", error));
            throw new NotFoundException("", errorList, ErrorType.INVALID_FIELD);
        } else
            logger.info("searchOnDetailView(): EXIT -> the crawler worked successfully");


        return products;
    }


    public void searchOnDetailViewTest(CrawlerConfiguration crawlerConfiguration, List<String> urls) {
        WebDriver driver = getWebDriver();
        boolean isFirstCookies = true;
        boolean isFirstOther = true;
        List<Error> errorList = new ArrayList<>();
        driver.get(crawlerConfiguration.getMainPageUrl());
        isFirstCookies = pressButtonsOnPage(driver, crawlerConfiguration, isFirstCookies, crawlerConfiguration.getProductCookiesButtonPaths(), crawlerConfiguration.getMainPageUrl(), PRODUCT_COOKIES_BUTTON, false, 1, 10, 500);

        for (String url : urls) {
            Pair<Product, Pair<Boolean, Boolean>> pair = searchItemOnDetailView("retailerUuid", crawlerConfiguration.getRetailerName(), "category", driver, url, isFirstCookies, isFirstOther, crawlerConfiguration, errorList, 3, 500);
            isFirstCookies = pair.getSecond().getFirst();
            isFirstOther = pair.getSecond().getSecond();
        }

        driver.quit();
        if (!errorList.isEmpty()) {
            errorList.forEach(error -> logger.error("ERROR  ={}", error));
            throw new NotFoundException("", errorList, ErrorType.INVALID_FIELD);
        } else
            logger.info("searchOnDetailViewTest(): EXIT -> the crawler worked successfully");

    }

    public void validateCrawlerConfiguration(CrawlerConfiguration crawlerConfiguration, String category, String userManualUuid) {
        List<Error> errorList = new ArrayList<>();

        if (category == null || category.isEmpty())
            errorList.add(new Error(CATEGORY, category, "invalid", "url"));

        if (crawlerConfiguration.getRetailerUuid() == null || crawlerConfiguration.getRetailerUuid().isEmpty())
            errorList.add(new Error(RETAILER_ID, crawlerConfiguration.getRetailerUuid(), "invalid", ""));

        if (crawlerConfiguration.getRetailerName() == null || crawlerConfiguration.getRetailerName().isEmpty())
            errorList.add(new Error(RETAILER_NAME, crawlerConfiguration.getRetailerName(), "invalid", ""));

        if (crawlerConfiguration.getMainPageUrl() == null || crawlerConfiguration.getMainPageUrl().isEmpty())
            errorList.add(new Error(MAIN_PAGE_URL, crawlerConfiguration.getMainPageUrl(), "invalid", ""));

        validateXPath(crawlerConfiguration.getProductNamePaths(), FieldType.productNamePath, errorList);
        validateXPath(crawlerConfiguration.getProductImagePaths(), FieldType.productImagePath, errorList);
        validateXPath(crawlerConfiguration.getProductDescriptionPaths(), FieldType.productDescriptionPath, errorList);
        validateXPath(crawlerConfiguration.getProductSpecsPaths(), FieldType.productSpecsPath, errorList);
        validateXPath(crawlerConfiguration.getProductCodePaths(), FieldType.productCodePath, errorList);

        if (!errorList.isEmpty()) {
            errorList.forEach(error -> logger.error("ERROR  ={}", error));
            throw new NotFoundException("", errorList, ErrorType.MISSING_FIELD);
        }

    }

    private void validateXPath(List<String> xPaths, FieldType fieldType, List<Error> errorList) {
        if (xPaths == null || xPaths.isEmpty() || xPaths.getFirst().isEmpty())
            errorList.add(new Error(fieldType.toString(), xPaths != null ? xPaths.toString() : null, "invalid", ""));
    }


    private Pair<Product, Pair<Boolean, Boolean>> searchItemOnDetailView(String retailerUuid, String retailerName, String category, WebDriver driver, String url, Boolean isFirstCookies, Boolean isFirstOther, CrawlerConfiguration crawlerConfiguration, List<Error> errorList, int maxIterations, int timeoutMilis) {
        Product product = new Product();

        product.setRetailerUuid(retailerUuid);
        product.setRetailerName(retailerName);
        product.setCategory(category);
        product.setPriceCurrency("");
        product.setPriceAmount(0.0f);
        product.setSpecifications(new ArrayList<>());
        product.setDescription("");

        boolean ok = false;
        int iteration = 0;
        while (!ok) {
            try {
                if (iteration == maxIterations) break;
                iteration++;
                driver.get(url);
                isFirstCookies = pressButtonsOnPage(driver, crawlerConfiguration, isFirstCookies, crawlerConfiguration.getProductCookiesButtonPaths(), url, PRODUCT_COOKIES_BUTTON, true, 3, 20, timeoutMilis);

                try {
                    isFirstOther = pressButtonsOnPage(driver, crawlerConfiguration, isFirstOther, crawlerConfiguration.getProductOtherButtonPaths(), url, PRODUCT_OTHER_BUTTON, false, 2, 10, 500);
                } catch (NotFoundException notFoundException) {
                    errorList.add(new Error(FieldType.productOtherButtonPath.toString(), crawlerConfiguration.getProductOtherButtonPaths().toString(), NOT_FOUND_MESSAGE, url));
                }


                searchItem(product, driver, crawlerConfiguration, url, errorList, maxIterations, timeoutMilis);
                ok = true;
            } catch (ReloadException reloadException) {
                logger.warn("reloadException = {}", reloadException.getMessage());
            } catch (WebDriverException webDriverException) {
                logger.warn("webDriverException = {}, stackTrace={}", webDriverException.getMessage(), webDriverException.getStackTrace());
            }
        }

        if (!ok)
            errorList.add(new Error(FieldType.UNKNOWN.name(), "", "product could not be crawled", ""));


        return new Pair<>(product, new Pair<>(isFirstCookies, isFirstOther));

    }


    public boolean pressButtonsOnPage(WebDriver driver, CrawlerConfiguration crawlerConfiguration, boolean isFirst, List<String> xPathButton, String url, String configNameButton, boolean mandatory, int maxIterations, int maxAttempts, int timeoutMilis) {
        if (isFirst && !xPathButton.isEmpty() && !xPathButton.getFirst().isEmpty()) {
            logger.info("pressButtonsOnProductPage(): isFirst product that press other button on url = " + url);
            boolean pressed = false;
            int iterations = 0;

            while (!pressed) {
                try {
                    int attempts = 0;
                    while (!isFound(xPathButton, driver, false) && attempts < maxAttempts) {
                        logger.info("pressButtonsOnMainPage(): enter in while");
                        try {
                            logger.info("pressButtonsOnMainPage(): try enter wait");
                            waitForMultipleXPaths(driver, Duration.ofMillis(timeoutMilis), xPathButton);
                            logger.info("pressButtonsOnMainPage(): try exit wait");
                        } catch (TimeoutException timeoutException) {
                            logger.error("timeout exception={} , for xPath={}", timeoutException.getMessage(), xPathButton);
                        }
                        attempts++;
                    }
                    if (attempts == maxAttempts)
                        throw new ReloadException(configNameButton + " not found on url= " + url);

                    clickButton(crawlerConfiguration, driver, configNameButton, mandatory, url);
                    pressed = true;
                    isFirst = false;
                    logger.info("pressButtonsOnMainPage(): EXIT -> url={}", url);
                } catch (ReloadException reloadException) {
                    logger.error("pressButtonsOnPage(): reloadException = {}", reloadException.getMessage());
                } catch (ClickException clickException) {
                    logger.error("pressButtonsOnPage(): clickException = {}", clickException.getMessage());
                }

                iterations++;
                if (iterations == maxIterations && !pressed) {
                    logger.error("pressButtonsOnPage(): " + configNameButton + " not found on url= " + url + " , retailerName={}", crawlerConfiguration.getRetailerName());
                    if (mandatory)
                        driver.quit();
                    throw new com.example.webcrawler.exceptions.NotFoundException("", List.of(new Error(Utils.mapFromString(configNameButton).toString(), xPathButton.toString(), NOT_FOUND_MESSAGE, url)), ErrorType.INVALID_FIELD);
                }
                if (!pressed)
                    driver.get(url);
            }

        } else if (mandatory && isFirst && !xPathButton.isEmpty() && !xPathButton.getFirst().isEmpty()) {
            logger.error("pressButtonsOnPage(): " + configNameButton + "not found, retailerName={}", crawlerConfiguration.getRetailerName());
            driver.quit();
            throw new com.example.webcrawler.exceptions.NotFoundException("", List.of(new Error(Utils.mapFromString(configNameButton).toString(), xPathButton.toString(), NOT_FOUND_MESSAGE, url)), ErrorType.INVALID_FIELD);
        }
        return isFirst;
    }


    private void searchItem(Product product, WebDriver driver, CrawlerConfiguration crawlerConfiguration, String url, List<Error> errorList, int maxIterations, int timeoutMilis) {
        logger.info("searchItem(): driver currentURL={}", driver.getCurrentUrl());

        Utils.loadPage(driver, 0.1f, 50);
        try {
            setAttributesFromDetailView(crawlerConfiguration, driver, product, url, errorList, maxIterations, timeoutMilis);
        } catch (Exception e) {
            driver.quit();
            logger.error("searchItem(): Product could not be saved " + e.getClass().getName());
            throw new NotSavedException("Product could not be saved , url = " + url);
        }

    }

    public WebDriver getWebDriver() {
        logger.info("getWebDriver(): -> ENTER");

        ChromeOptions options = new ChromeOptions();

        if (!Objects.equals(profile, "prod"))
            System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        if (headless) options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        options.addArguments("accept-language=en-US,en;q=0.9");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
        // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //launching the specified URL
        logger.info("getWebDriver(): -> EXIT");
        return driver;
    }

    private void setAttributesFromDetailView(CrawlerConfiguration crawlerConfiguration, WebDriver driver, Product product, String url, List<Error> errorList, int maxIterations, int timeoutMilis) {

        try {
            product.setName(getElementText(crawlerConfiguration, driver, PRODUCT_NAME, true, "", true).replaceAll("\'", ""));
        } catch (Exception e) {
            if (!Utils.existErrorWithConfigNameAndUrl(errorList, PRODUCT_NAME, url)) {
                errorList.add(new Error(Utils.mapFromString(PRODUCT_NAME).toString(), crawlerConfiguration.getProductNamePaths().toString(), NOT_FOUND_MESSAGE, url));
            }
        }


        try {
            product.setImageUrl(getImageUrlFromDetailView(crawlerConfiguration, driver, PRODUCT_IMAGE, true));
        } catch (Exception e) {
            if (!Utils.existErrorWithConfigNameAndUrl(errorList, PRODUCT_IMAGE, url)) {
                errorList.add(new Error(Utils.mapFromString(PRODUCT_IMAGE).toString(), crawlerConfiguration.getProductImagePaths().toString(), NOT_FOUND_MESSAGE, url));
            }
        }

        if (!crawlerConfiguration.getProductPricePaths().isEmpty() && !crawlerConfiguration.getProductPricePaths().getFirst().isEmpty()) {
            try {
                setPrice(crawlerConfiguration, driver, product, false);
            } catch (Exception e) {
                if (!Utils.existErrorWithConfigNameAndUrl(errorList, PRODUCT_PRICE, url)) {
                    errorList.add(new Error(Utils.mapFromString(PRODUCT_PRICE).toString(), crawlerConfiguration.getProductPricePaths().toString(), NOT_FOUND_MESSAGE, url));
                }
            }
        }


        String brand = new String();

        if (!crawlerConfiguration.getProductBrandPaths().isEmpty() && !crawlerConfiguration.getProductBrandPaths().getFirst().isEmpty()) {
            try {
                brand = getElementText(crawlerConfiguration, driver, PRODUCT_BRAND, false, "alt", false);
            } catch (Exception e) {
                if (!Utils.existErrorWithConfigNameAndUrl(errorList, PRODUCT_BRAND, url)) {
                    errorList.add(new Error(Utils.mapFromString(PRODUCT_BRAND).toString(), crawlerConfiguration.getProductBrandPaths().toString(), NOT_FOUND_MESSAGE, url));
                }
            }
        } else {
            brand = Utils.getStoreNameFromUrl(url);
        }
        product.setBrand((brand != null && !brand.isEmpty()) ? brand : Utils.getStoreNameFromUrl(url));


        try {
            loadElement(crawlerConfiguration, driver, PRODUCT_DESCRIPTION_BUTTON, maxIterations, timeoutMilis);
        } catch (Exception e) {
            errorList.add(new Error(Utils.mapFromString(PRODUCT_DESCRIPTION_BUTTON).toString(), crawlerConfiguration.getProductDescriptionButtonPaths().toString(), NOT_FOUND_MESSAGE, url));
        }

        try {
            loadElementWithClick(crawlerConfiguration, driver, PRODUCT_DESCRIPTION, PRODUCT_DESCRIPTION_BUTTON, url, maxIterations, timeoutMilis);
            product.setDescription(getElementTextAdvanced(crawlerConfiguration, driver, PRODUCT_DESCRIPTION, false));
        } catch (StaleElementReferenceException e) {
            try {
                product.setDescription(getElementTextAdvanced(crawlerConfiguration, driver, PRODUCT_DESCRIPTION, false));
            } catch (Exception ex) {
                errorList.add(new Error(Utils.mapFromString(PRODUCT_DESCRIPTION).toString(), crawlerConfiguration.getProductDescriptionPaths().toString(), NOT_FOUND_MESSAGE, url));
            }
        } catch (Exception e) {
            errorList.add(new Error(Utils.mapFromString(PRODUCT_DESCRIPTION).toString(), crawlerConfiguration.getProductDescriptionPaths().toString(), NOT_FOUND_MESSAGE, url));
        }

        try {
            product.setCode(Utils.extractArticleNumber(getElementText(crawlerConfiguration, driver, PRODUCT_CODE, true, "", true)));
        } catch (Exception e) {
            if (!Utils.existErrorWithConfigNameAndUrl(errorList, PRODUCT_CODE, url)) {
                errorList.add(new Error(Utils.mapFromString(PRODUCT_CODE).toString(), crawlerConfiguration.getProductCodePaths().toString(), NOT_FOUND_MESSAGE, url));
            }
        }


        try {
            loadElement(crawlerConfiguration, driver, PRODUCT_SPECIFICATIONS_BUTTON, maxIterations, timeoutMilis);
        } catch (Exception e) {
            errorList.add(new Error(Utils.mapFromString(PRODUCT_SPECIFICATIONS_BUTTON).toString(), crawlerConfiguration.getProductSpecsButtonPaths().toString(), NOT_FOUND_MESSAGE, url));
        }

        try {
            loadElementWithClick(crawlerConfiguration, driver, PRODUCT_SPECIFICATIONS, PRODUCT_SPECIFICATIONS_BUTTON, url, maxIterations, timeoutMilis);
            product.setSpecifications(getProductSpecifications(crawlerConfiguration, driver));
        } catch (StaleElementReferenceException e) {
            try {
                product.setSpecifications(getProductSpecifications(crawlerConfiguration, driver));
            } catch (Exception ex) {
                errorList.add(new Error(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString(), crawlerConfiguration.getProductSpecsPaths().toString(), NOT_FOUND_MESSAGE, url));
            }
        } catch (Exception e) {
            errorList.add(new Error(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString(), crawlerConfiguration.getProductSpecsPaths().toString(), NOT_FOUND_MESSAGE, url));
        }
    }

    private void loadElement(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, int maxIterations, int timeoutMilis) {
        logger.info("loadElement(): configName={}", configName);
        List<String> xPathsElement = getXPathsByConfigName(crawlerConfiguration, configName);

        int iteration = 0;
        if (!xPathsElement.isEmpty() && !xPathsElement.getFirst().isEmpty()) {
            while (!isFound(xPathsElement, driver, true) && iteration < maxIterations) {
//                Utils.loadPage(driver, 0.1f, 5);
                try {
                    logger.info("enter wait");
                    waitForMultipleXPaths(driver, Duration.ofMillis(timeoutMilis), xPathsElement);
                    logger.info("exit wait");
                } catch (TimeoutException timeoutException) {
                    logger.error("timeout exception={}, xPathsElement={}", timeoutException.getMessage(), xPathsElement);
                }
                iteration++;
            }
            if (!isFound(xPathsElement, driver, true)) throw new NotFoundException(configName + "not found");
        }

    }

    private void loadElementWithClick(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, String confingNameClick, String url, int maxIterations, int timeoutMilis) {
        logger.info("loadElementWithClick(): configName={}", configName);
        List<String> xPathsElement = getXPathsByConfigName(crawlerConfiguration, configName);

        int iterations = 0;
//        Utils.loadPage(driver, 0.1f, 50);
        clickButtonExpand(crawlerConfiguration, driver, confingNameClick, false, url);
        while (!isFound(xPathsElement, driver, true) && iterations < maxIterations) {
            clickButtonExpand(crawlerConfiguration, driver, confingNameClick, false, url);

            //Utils.loadPage(driver, 0.1f, 50);
            try {
                logger.info("enter wait");
                waitForMultipleXPaths(driver, Duration.ofMillis(timeoutMilis), xPathsElement);
                logger.info("exit wait");
            } catch (TimeoutException timeoutException) {
                logger.error("timeout exception={}, xPathsElement={}", timeoutException.getMessage(), xPathsElement);
            }

            iterations++;
        }
        if (!isFound(xPathsElement, driver, true))
            loadAdvancedElementWithClick(driver, searchElementInDetailView(crawlerConfiguration, driver, configName, false));

        if (!isFound(xPathsElement, driver, true)) {
            throw new NotFoundException(configName + "not found");
        }
    }

    private void loadAdvancedElementWithClick(WebDriver driver, WebElement webElement) {
        Utils.clickButtonOnPage(driver, webElement, 0.1f);
    }


    private static void waitForMultipleXPaths(WebDriver driver, Duration timeout, List<String> xPaths) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                for (String xpath : xPaths) {
                    try {
                        WebElement webElement = driver.findElement(By.xpath(xpath));
                        if (webElement != null) {
                            return true; // Așteptarea se încheie când cel puțin unul dintre XPath-uri devine adevărat
                        }
                    } catch (NoSuchElementException noSuchElementException) {

                    } catch (WebDriverException webDriverException) {

                    }

                }
                return false;
            }
        });
    }

    private Boolean isFound(List<String> xPaths, WebDriver driver, boolean isText) {
        for (String xpath : xPaths) {
            WebElement webElement = findElementByXpath(driver, xpath, isText);
            if (webElement != null) {
                return true; // Așteptarea se încheie când cel puțin unul dintre XPath-uri devine adevărat
            }
        }
        return false;
    }

    private WebElement findElementByXpath(WebDriver driver, String xPath, boolean isText) {
        WebElement webElement;
        try {
            webElement = driver.findElement(new By.ByXPath(xPath));

            if (webElement != null && !(webElement.getText().isEmpty() && isText)) return webElement;
            else {
                if (webElement == null) {
                    logger.warn("WebElement null or empty: " + "xPath" + " " + xPath + " webElement.getText()=null");
                } else {
                    logger.warn("WebElement null or empty: " + "xPath" + " " + xPath + " webElement.getText()=" + webElement.getText());
                }

            }


        } catch (Exception e) {
            logger.warn(e.getClass().getName() + " " + "xPath" + " " + xPath);
        }
        return null;
    }


    private WebElement findElementByTag(WebElement rootElement, String tag, boolean isText) {
        WebElement webElement;
        try {
            webElement = rootElement.findElement(new By.ByTagName(tag));

            if (webElement != null && !(webElement.getText().isEmpty() && isText)) return webElement;
            else logger.warn("WebElement null or empty: " + "tag" + " " + tag);

        } catch (Exception e) {
            logger.warn(e.getClass().getName() + " " + "tag" + " " + tag);
        }
        return null;
    }

    private WebElement searchElementInDetailView(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean isText) {
//        searching for web element in case of multiple xpath

        List<String> xPathList = getXPathsByConfigName(crawlerConfiguration, configName);
        for (String xPath : xPathList) {
            WebElement webElement = findElementByXpath(driver, xPath, isText);
            if (webElement != null) return webElement;
        }
        logger.warn("searchElementInDetailView(): None of the xPaths was working, retailerName={}, configName={}, xPathList={} ", crawlerConfiguration.getRetailerName(), configName, xPathList);
        return null;
    }

    private WebElement searchElementInDetailViewAdvanced(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean isText) {
//        searching for web element in case of multiple xpath

        List<String> xPathList = getXPathsByConfigName(crawlerConfiguration, configName);
        for (String xPath : xPathList) {
            WebElement webElement = findElementByXpath(driver, xPath, isText);
            if (webElement != null) return webElement;
        }
        logger.warn("searchElementInDetailViewAdvanced(): None of the xPaths was working, retailerName={}, configName={}, xPathList={} ", crawlerConfiguration.getRetailerName(), configName, xPathList);
        return null;

    }


    private String getElementText(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean mandatory, String attribute, boolean isText) {
        logger.info("getElementText(): -> ENTER retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
        try {
            WebElement webElement = searchElementInDetailView(crawlerConfiguration, driver, configName, isText);
            if (webElement == null) {
                throw new RuntimeException(configName + " not found");
            }
            if (webElement.getText().isEmpty() && !attribute.isEmpty()) {
                logger.info("getElementText(): -> EXIT retailerName={}, configName={} , attribute = {}", crawlerConfiguration.getRetailerName(), configName, attribute);
                return webElement.getAttribute(attribute);
            }

            logger.info("getElementText(): -> EXIT retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
            return webElement.getText();
        } catch (Exception e) {
            logger.error("getElementText(): configName={} not found", configName);
            throw e;
        }
    }

    private String getElementTextAdvanced(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean mandatory) {
        logger.info("getElementTextAdvanced(): -> ENTER retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);

        try {
            WebElement webElement = searchElementInDetailView(crawlerConfiguration, driver, configName, true);
            if (webElement == null) {
                throw new RuntimeException(configName + " not found");
            }
            logger.info("getElementTextAdvanced(): -> EXIT retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
            return webElement.getText();
        } catch (Exception e) {
            logger.error("getElementText(): configName={} not found", configName);
            throw e;
        }

    }


    private String getImageUrlFromDetailView(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean mandatory) {
        logger.info("getImageUrlFromDetailView(): -> ENTER retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
        try {
            List<String> xPathList = getXPathsByConfigName(crawlerConfiguration, configName);
            for (String xPath : xPathList) {
                WebElement webElement = findElementByXpath(driver, xPath, false);
                if (webElement != null && webElement.getAttribute("src") != null) {
                    logger.info("getImageUrlFromDetailView(): -> EXIT retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
                    return webElement.getAttribute("src");
                }
            }

            if (mandatory) throw new RuntimeException(configName + " not found");
            else return null;
        } catch (Exception e) {
            logger.warn(configName + " not found; " + e.getClass().getName());
            if (mandatory) throw e;
            else return null;
        }
    }


    private void clickButton(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean mandatory, String url) {
        logger.info("clickButton(): -> ENTER retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
        WebElement webElement = searchElementInDetailView(crawlerConfiguration, driver, configName, false);
        if (webElement != null) {
            try {
                //ThreadSleeper.sleep(3f);
                logger.info("clicked button={}", webElement.getText());

                try {
                    webElement.click();
                } catch (Exception exception) {
                    logger.error("clickButton(): exception while pressing button  errorMessage={}", exception.getMessage());
                    Utils.clickButtonOnPage(driver, webElement, 0.1f);
                }

                if (!Utils.getBaseItemUrl(driver.getCurrentUrl()).contains(Utils.getBaseItemUrl(url)))
                    driver.navigate().back();


                //System.out.println(Utils.getBaseItemUrl(driver.getCurrentUrl()));
                ThreadSleeper.sleep(3f);
                logger.info("clickButton(): -> EXIT retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
            } catch (Exception e) {
                logger.warn("Error pressing " + configName + " " + e.getMessage() + " " + webElement.getText());
                if (mandatory) throw new ClickException(e.getMessage());
            }

        } else if (mandatory) throw new ClickException("Error getting " + configName);
    }

    private void clickButtonExpand(CrawlerConfiguration crawlerConfiguration, WebDriver driver, String configName, boolean mandatory, String url) {
        logger.info("clickButton(): -> ENTER retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
        WebElement webElement = searchElementInDetailView(crawlerConfiguration, driver, configName, false);
        if (webElement != null) {
            try {
                //ThreadSleeper.sleep(3f);
                logger.info("clicked button={}", webElement.getText());

                try {
                    webElement.click();
                } catch (Exception exception) {
                    logger.error("clickButton(): exception while pressing button  errorMessage={}", exception.getMessage());

                    clickButton(crawlerConfiguration, driver, PRODUCT_OTHER_BUTTON, false, url);

                }

                if (!Utils.getBaseItemUrl(driver.getCurrentUrl()).contains(Utils.getBaseItemUrl(url)))
                    driver.navigate().back();


                //System.out.println(Utils.getBaseItemUrl(driver.getCurrentUrl()));
                ThreadSleeper.sleep(3f);
                logger.info("clickButton(): -> EXIT retailerName={}, configName={}", crawlerConfiguration.getRetailerName(), configName);
            } catch (Exception e) {
                logger.warn("Error pressing " + configName + " " + e.getMessage() + " " + webElement.getText());
                if (mandatory) throw new ClickException(e.getMessage());
            }

        } else if (mandatory) throw new ClickException("Error getting " + configName);

    }


    private void setPrice(CrawlerConfiguration crawlerConfiguration, WebDriver driver, Product product, boolean mandatory) {
        logger.info("setPrice(): -> ENTER retailerName={}", crawlerConfiguration.getRetailerName());
        String input = getElementText(crawlerConfiguration, driver, PRODUCT_PRICE, mandatory, "", true);
        if (input != null && !input.isEmpty()) {
            input = input.replaceAll("\\.", "");
            input = input.replaceAll("\n", ".");
            input = input.replaceAll(" ", "");
            input = input.replaceAll(",", ".");

            String regexPriceAndCurrency1 = "\\d+(\\.\\d+)?(" + CURRENCY + ")";
            String regexPriceAndCurrency2 = "(" + CURRENCY + ")\\d+(\\.\\d+)?";
            String regexPrice = "[0-9]+[,.]?[0-9]*";
            String regexCurrency = "(" + CURRENCY + ")";

            String priceAndCurrency1 = findByRegex(input, regexPriceAndCurrency1);
            String priceAndCurrency2 = findByRegex(input, regexPriceAndCurrency2);
            String priceAndCurrency = priceAndCurrency1 != null ? priceAndCurrency1 : priceAndCurrency2;

            String priceString = findByRegex(priceAndCurrency, regexPrice);
            String currencyString = findByRegex(priceAndCurrency, regexCurrency);

            try {
                product.setPriceAmount(Double.parseDouble(priceString));
            } catch (NumberFormatException numberFormatException) {
                logger.error("error={}", numberFormatException.getMessage());
            }
            product.setPriceCurrency(currencyString);
            logger.info("setPrice(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
        }
    }


    private String findByRegex(String input, String regex) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            matcher.find();
            return matcher.group();
        } catch (IllegalStateException illegalStateException) {
            logger.error("error when trying to find with errorMessage={}", illegalStateException.getMessage());
        }
        return null;
    }


    private List<ProductSpecification> getProductSpecifications(CrawlerConfiguration crawlerConfiguration, WebDriver driver) {
        logger.info("getProductSpecifications(): -> ENTER retailerName={}", crawlerConfiguration.getRetailerName());
        List<String> xPathSpecificationsList = getXPathsByConfigName(crawlerConfiguration, PRODUCT_SPECIFICATIONS);
        List<ProductSpecification> productSpecifications = new ArrayList<>();


        for (String xPathSpecifications : xPathSpecificationsList) {
            try {
                WebElement specificationElement = findElementByXpath(driver, xPathSpecifications, true);

                if (specificationElement != null) {
                    // table
//                    if (findElementByTag(specificationElement, "table", true) != null || specificationElement.getTagName().equals("table")) {
//                        logger.info("table specifications");
//
//                        List<WebElement> headerWebElements = getHeaderWebElements(specificationElement);
//                        List<WebElement> tableWebElements = specificationElement.findElements(By.tagName("table"));
//
//                        int iterator = 0;
//                        for (WebElement tableElement : tableWebElements) {
//                            WebElement tableBodyElement = tableElement.findElement(By.tagName("tbody"));
//                            List<WebElement> rows = tableBodyElement.findElements(By.tagName("tr"));
//                            for (WebElement row : rows) {
//                                List<String> specificationNameValueList = Arrays.stream(row.getText().split("\n")).toList();
//                                productSpecifications.add(new ProductSpecification(
//                                        (iterator < headerWebElements.size()) ? headerWebElements.get(iterator).getText() : "Specifications",
//                                        specificationNameValueList.getFirst(),
//                                        specificationNameValueList.getLast())
//                                );
//                            }
//                            iterator++;
//                        }
//                        logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
//                        if (!productSpecifications.isEmpty()) return productSpecifications;
//                        else throw new NotFoundException(specificationElement.getText());
//                    }
                    if (findElementByTag(specificationElement, "tr", true) != null || specificationElement.getTagName().equals("tr")) {
                        logger.info("table specifications");
                        List<WebElement> webElements = specificationElement.findElements(By.tagName("tr"));
                        String productSpecificationHeader = "Specifications";
                        for (WebElement dl : webElements) {
                            List<String> specificationNameValueList = Arrays.stream(dl.getText().split("\n")).toList();
                            productSpecifications.add(new ProductSpecification(productSpecificationHeader, specificationNameValueList.getFirst(), specificationNameValueList.getLast()));
                        }
                        logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
                        if (!productSpecifications.isEmpty()) return productSpecifications;
                        else throw new NotFoundException(specificationElement.getText());
                    } else if (findElementByTag(specificationElement, "dl", true) != null || specificationElement.getTagName().equals("dl")) {
                        logger.info("list specifications dl");
                        List<WebElement> webElements = specificationElement.findElements(By.tagName("dl"));
                        String productSpecificationHeader = "Specifications";
                        for (WebElement dl : webElements) {
                            List<String> specificationNameValueList = Arrays.stream(dl.getText().split("\n")).toList();
                            productSpecifications.add(new ProductSpecification(productSpecificationHeader, specificationNameValueList.getFirst(), specificationNameValueList.getLast()));
                        }
                        logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
                        if (!productSpecifications.isEmpty()) return productSpecifications;
                        else throw new NotFoundException(specificationElement.getText());
                    }
                    // list
                    else if (findElementByTag(specificationElement, "ul", true) != null || specificationElement.getTagName().equals("ul")) {
                        logger.info("list specifications ul");
                        List<WebElement> listElement = specificationElement.findElements(By.tagName("li"));
                        String productSpecificationHeader = "Specifications";
                        for (WebElement li : listElement) {
                            List<String> specificationNameValueList = Arrays.stream(li.getText().split(":")).toList();
                            productSpecifications.add(new ProductSpecification(productSpecificationHeader, specificationNameValueList.getFirst(), specificationNameValueList.getLast()));
                        }
                        logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
                        if (!productSpecifications.isEmpty()) return productSpecifications;
                        else throw new NotFoundException(specificationElement.getText());
                    }
                    // text
                    else {
                        logger.info("text specifications");
                        String productSpecificationHeader = "Specifications";
                        productSpecifications.add(new ProductSpecification(productSpecificationHeader, "Specifications", specificationElement.getText()));
                        logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
                        return productSpecifications;
                    }
                }

            } catch (NotFoundException notFoundException) {
                logger.info("text specifications");
                String productSpecificationHeader = "Specifications";
                productSpecifications.add(new ProductSpecification(productSpecificationHeader, "Specifications", notFoundException.getMessage()));
                logger.info("getProductSpecifications(): -> EXIT retailerName={}", crawlerConfiguration.getRetailerName());
                return productSpecifications;
            } catch (Exception e) {
                logger.warn("Product specification reading error " + e.getClass().getName());

            }
        }
        return null;
    }


    private List<String> getXPathsByConfigName(CrawlerConfiguration crawlerConfiguration, String configName) {

        return switch (configName) {
            case PRODUCT_NAME -> crawlerConfiguration.getProductNamePaths();
            case PRODUCT_IMAGE -> crawlerConfiguration.getProductImagePaths();
            case PRODUCT_PRICE -> crawlerConfiguration.getProductPricePaths();
            case PRODUCT_BRAND -> crawlerConfiguration.getProductBrandPaths();
            case PRODUCT_SPECIFICATIONS -> crawlerConfiguration.getProductSpecsPaths();
            case PRODUCT_SPECIFICATIONS_BUTTON -> crawlerConfiguration.getProductSpecsButtonPaths();
            case PRODUCT_DESCRIPTION -> crawlerConfiguration.getProductDescriptionPaths();
            case PRODUCT_DESCRIPTION_BUTTON -> crawlerConfiguration.getProductDescriptionButtonPaths();
            case PRODUCT_COOKIES_BUTTON -> crawlerConfiguration.getProductCookiesButtonPaths();
            case PRODUCT_OTHER_BUTTON -> crawlerConfiguration.getProductOtherButtonPaths();
            case PRODUCT_CODE -> crawlerConfiguration.getProductCodePaths();
            default -> new ArrayList<>();
        };
    }

    private List<WebElement> getHeaderWebElements(WebElement webElement) {
        List<WebElement> headerElements = new ArrayList<>();

        try {
            headerElements.addAll(webElement.findElements(By.tagName("p")));
        } catch (NoSuchElementException ignored) {
            // No <p> elements found
        }

        try {
            headerElements.addAll(webElement.findElements(By.tagName("h3")));
        } catch (NoSuchElementException ignored) {
            // No <h3> elements found
        }

        try {
            headerElements.addAll(webElement.findElements(By.tagName("thead")));
        } catch (NoSuchElementException ignored) {
            // No <thead> elements found
        }

        return headerElements;
    }

}
