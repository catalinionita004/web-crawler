package com.example.webcrawler.utils;

import com.example.webcrawler.data.CrawlerConfiguration;
import com.example.webcrawler.data.Pair;
import com.example.webcrawler.data.ProductSpecification;
import com.example.webcrawler.exceptions.ClickException;
import com.example.webcrawler.exceptions.Error;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.webcrawler.utils.Constans.*;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String getStoreNameFromUrl(String url) {
        String storeName = url.split("\\.")[1];

        return storeName;
    }

    public static String getXPathSpecs(String xPath) {
        String oldSuffix = "[1]";
        String newSuffix = "[%d]";
        if (xPath.endsWith(oldSuffix))
            return xPath.substring(0, xPath.length() - oldSuffix.length()) + newSuffix;
        else
            return xPath + "[%d]";

    }

    public static void loadPage(WebDriver driver, float delay, int numberOfIterations) {
        logger.info("loadPage(): ENTER ->");

        try {
            ThreadSleeper.sleep(delay);

            // Obține înălțimea totală a paginii
            JavascriptExecutor js = (JavascriptExecutor) driver;
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

            // Rulează un script JavaScript pentru a face scrol treptat în jos
            while (true) {
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                ThreadSleeper.sleep(delay);

                // Obține înălțimea paginii după scrol
                long newHeight = (long) js.executeScript("return document.body.scrollHeight");

                // Verifică dacă s-a atins sfârșitul paginii
                if (newHeight == lastHeight) {
                    break;
                }

                // Actualizează înălțimea anterioară
                lastHeight = newHeight;
            }


            for (int i = 0; i <= numberOfIterations; i++) {
                js.executeScript("window.scrollTo(0, (document.body.scrollHeight*" + i + ")/" + numberOfIterations + ");");
                ThreadSleeper.sleep(delay);

            }
            js.executeScript("window.scrollTo(0, 0);");
            ThreadSleeper.sleep(delay);
        } catch (Exception exception) {
            logger.error("loadPage(): exception while loading page= {}", exception.getMessage());
        }


        logger.info("loadPage(): EXIT ->");
    }


    public static String concatenate(String header, String name, String value) {
        String separator = "//";
        return header.concat(separator).concat(name).concat(separator).concat(value);
    }

    public static ProductSpecification split(String input) {
        String separator = "//";
        List<String> splittedList = Arrays.stream(input.split(separator)).toList();
        return new ProductSpecification(splittedList.get(0), splittedList.get(1), splittedList.get(2));
    }

    public static String getBaseItemUrl(String url) {
        return url.split("\\?")[0];
    }


    public static String createTable(String project, String dataset, String tableName) {
        return project.concat(".")
                .concat(dataset).concat(".")
                .concat(tableName);
    }

    public static List<String> createMultipleXPaths(String retailerName, String configName, CrawlerConfiguration crawlerConfiguration) {
        if (retailerName.equals("bofrost") && configName.equals(PRODUCT_COOKIES_BUTTON)) {
            String xPathCookiesButton = crawlerConfiguration.getProductCookiesButtonPaths().getFirst();
            List<String> multipleXPathCookiesButton = new ArrayList<>();

            return multipleXPathCookiesButton;
        }
        return new ArrayList<>();
    }

    public static boolean existErrorWithConfigNameAndUrl(List<Error> errors, String configName, String url) {
        return errors.stream()
                .anyMatch(error -> error.fieldName().equals(mapFromString(configName).toString()) && error.otherDetails().equals(url));
    }

    public static String extractArticleNumber(String input) throws Exception {
        Pattern pattern = Pattern.compile("\\b\\w*\\d+\\w*\\b");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group();
        } else throw new Exception("article number invalid");
    }

    public static void clickButtonOnPage(WebDriver driver, WebElement webElement, float delaySeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int numberOfIterations = ((int) ((double) ((Double) js.executeScript("return 2*document.documentElement.scrollHeight/window.innerHeight"))));

        int i;
        boolean pressed = false;

        Pair<Integer, Boolean> pair = iterateClick(numberOfIterations, pressed, webElement, js, numberOfIterations);
        i = pair.getFirst();
        pressed = pair.getSecond();

        if(!pressed)
            i=0;

        for (; i <= numberOfIterations; i++) {
            pair = iterateClick(i, pressed, webElement, js, numberOfIterations);
            i = pair.getFirst();
            pressed = pair.getSecond();
            ThreadSleeper.sleep(delaySeconds);
        }
        if (!pressed) {
            logger.error("clickButtonOnPage(): the button can't be clicked");
            throw new ClickException("invalid button");
        }
    }

    private static Pair<Integer, Boolean> iterateClick(int i, boolean pressed, WebElement webElement, JavascriptExecutor js, int numberOfIterations) {
        js.executeScript("window.scrollTo(0, (document.body.scrollHeight*" + numberOfIterations + ")/" + numberOfIterations + ");");
        try {
            webElement.click();
            i = numberOfIterations + 1;
            pressed = true;
        } catch (Exception exception) {

        }

        return new Pair<>(i, pressed);
    }

    public static FieldType mapFromString(String input) {
        if (input.equals(PRODUCT_COOKIES_BUTTON))
            return FieldType.productCookiesButtonPath;
        else if (input.equals(PRODUCT_OTHER_BUTTON))
            return FieldType.productOtherButtonPath;
        else if (input.equals(PRODUCT_NAME))
            return FieldType.productNamePath;
        else if (input.equals(PRODUCT_IMAGE))
            return FieldType.productImagePath;
        else if (input.equals(PRODUCT_PRICE))
            return FieldType.productPricePath;
        else if (input.equals(PRODUCT_BRAND))
            return FieldType.productBrandPath;
        else if (input.equals(PRODUCT_DESCRIPTION))
            return FieldType.productDescriptionPath;
        else if (input.equals(PRODUCT_DESCRIPTION_BUTTON))
            return FieldType.productDescriptionButtonPath;
        else if (input.equals(PRODUCT_SPECIFICATIONS))
            return FieldType.productSpecsPath;
        else if (input.equals(PRODUCT_SPECIFICATIONS_BUTTON))
            return FieldType.productSpecsButtonPath;
        else if (input.equals(PRODUCT_CODE))
            return FieldType.productCodePath;
        else return FieldType.UNKNOWN;
    }


}
