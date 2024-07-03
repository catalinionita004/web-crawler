//package com.example.webcrawler;
//
//import com.example.webcrawler.data.CategoryManualsUrls;
//import com.example.webcrawler.data.CrawlerConfiguration;
//import com.example.webcrawler.data.ManualUrls;
//import com.example.webcrawler.data.Product;
//import com.example.webcrawler.exceptions.NotFoundException;
//import com.example.webcrawler.services.CrawlerConfigurationService;
//import com.example.webcrawler.services.WebCrawlingService;
//import com.example.webcrawler.utils.Utils;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.List;
//
//import static com.example.webcrawler.utils.Constans.*;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class WebCrawlingServiceTest {
//
//    @Autowired
//    private WebCrawlingService webCrawlingService;
//
//    @MockBean
//    private CrawlerConfigurationService crawlerConfigurationService;
//
//    @Test
//    public void testALLSearchOnDetailViewMediamarktHEADLESS() {
//        // all good
//        testSearchOnDetailViewAllGoodMediamarktHEADLESS();
//        // invalid cookies button
//        testSearchOnDetailViewInvalidXPathCookiesMediamarktHEADLESS();
//        // all empty
//        testSearchOnDetailViewEmptyXPathsMediamarktHEADLESS();
//        // all invalid
//        testSearchOnDetailViewInvalidXPathsMediamarktHEADLESS();
//        // valid mandatory fields
//        testSearchOnDetailViewValidMandatoryFieldsMediamarktHEADLESS();
//    }
//
//    @Test
//    public void testALLSearchOnDetailViewBofrostHEADLESS() {
//        //all good
//        testSearchOnDetailViewAllGoodBofrostHEADLESS();
//        //all good 2
//        testSearchOnDetailViewAllGoodBofrost2HEADLESS();
//        //invalid cookies button
//        testSearchOnDetailViewInvalidXPathCookiesBofrostHEADLESS();
//        // all empty
//        testSearchOnDetailViewEmptyXPathsBofrostHEADLESS();
//        // all invalid
//        testSearchOnDetailViewInvalidXPathsBofrostHEADLESS();
//        // valid mandatory fields
//        testSearchOnDetailViewValidMandatoryFieldsBofrostHEADLESS();
//    }
//
//    @Test
//    public void testALLSearchOnDetailViewSwarovskiHEADLESS() {
//        // all good
//        testSearchOnDetailViewAllGoodSwarovskiHEADLESS();
//        //invalid cookies button
//        testSearchOnDetailViewInvalidXPathCookiesSwarovskiHEADLESS();
//        // all empty
//        testSearchOnDetailViewEmptyXPathsSwarovskiHEADLESS();
//        // all invalid
//        testSearchOnDetailViewInvalidXPathsSwarovskiHEADLESS();
//        // valid mandatory fields
//        testSearchOnDetailViewValidMandatoryFieldsSwarovskiHEADLESS();
//
//    }
//
//
//    /*
//     * -------------------------------------------- MEDIAMARKT TESTS ---------------------------------------------------
//     */
//
//    @Test
//    public void testSearchOnDetailViewAllGoodMediamarktHEADLESS() {
//        testSearchOnDetailViewAllGoodMediamarkt(true);
//
//    }
//
//    @Test
//    public void testSearchOnDetailViewAllGoodMediamarktNotHEADLESS() {
//        testSearchOnDetailViewAllGoodMediamarkt(false);
//
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesMediamarktHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesMediamarkt(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesMediamarktNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesMediamarkt(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsMediamarktHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsMediamarkt(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsMediamarktNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsMediamarkt(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewEmptyXPathsMediamarktHEADLESS() {
//        testSearchOnDetailViewEmptyXPathsMediamarkt(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewEmptyXPathsMediamarktNotHEADLESS() {
//        testSearchOnDetailViewEmptyXPathsMediamarkt(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsMediamarktHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsMediamarkt(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsMediamarktNotHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsMediamarkt(false);
//    }
//
//
//
//    /*
//     * -------------------------------------------  BOFROST TESTS ------------------------------------------------------
//     */
//
//    @Test
//    public void testSearchOnDetailViewAllGoodBofrostHEADLESS() {
//        testSearchOnDetailViewAllGoodBofrost(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewAllGoodBofrostNotHEADLESS() {
//        testSearchOnDetailViewAllGoodBofrost(false);
//    }
//
//
//    @Test
//    public void testSearchOnDetailViewAllGoodBofrost2HEADLESS() {
//
//        testSearchOnDetailViewAllGoodBofrost2(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewAllGoodBofrost2NotHEADLESS() {
//        testSearchOnDetailViewAllGoodBofrost2(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesBofrostHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesBofrost(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesBofrostNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesBofrost(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewEmptyXPathsBofrostHEADLESS() {
//        testSearchOnDetailViewEmptyXPathsBofrost(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsBofrostHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsBofrost(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsBofrostNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsBofrost(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsBofrostHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsBofrost(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsBofrostNotHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsBofrost(false);
//    }
//
//
//    /*
//     * -------------------------------------------  SWAROVSKI TESTS ----------------------------------------------------
//     */
//
//
//    @Test
//    public void testSearchOnDetailViewAllGoodSwarovskiHEADLESS() {
//        testSearchOnDetailViewAllGoodSwarovski(true);
//
//    }
//
//    @Test
//    public void testSearchOnDetailViewAllGoodSwarovskiNotHEADLESS() {
//        testSearchOnDetailViewAllGoodSwarovski(false);
//
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesSwarovskiHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesSwarovski(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathCookiesSwarovskiNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathCookiesSwarovski(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewEmptyXPathsSwarovskiHEADLESS() {
//        testSearchOnDetailViewEmptyXPathsSwarovski(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewEmptyXPathsSwarovskiNotHEADLESS() {
//        testSearchOnDetailViewEmptyXPathsSwarovski(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsSwarovskiHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsSwarovski(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewInvalidXPathsSwarovskiNotHEADLESS() {
//        testSearchOnDetailViewInvalidXPathsSwarovski(false);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsSwarovskiHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsSwarovski(true);
//    }
//
//    @Test
//    public void testSearchOnDetailViewValidMandatoryFieldsSwarovskiNotHEADLESS() {
//        testSearchOnDetailViewValidMandatoryFieldsSwarovski(false);
//    }
//
//
//    private void testSearchOnDetailViewAllGoodMediamarkt(boolean headless) {
//        String category = "smartphone";
//        String retailerName = "mediamarkt";
//        String retailerUuid = "retailerUuid1";
//        String userManualUuid = "userManualUuid6";
//        List<String> urls = List.of("https://www.mediamarkt.de/de/product/_xiaomi-redmi-a2-32-gb-light-blue-dual-sim-2859031.html", "https://www.mediamarkt.de/de/product/_samsung-galaxy-a14-5g-64-gb-black-dual-sim-2862704.html");
//        String mainPageUrl = "https://www.mediamarkt.de";
//        List<String> productNamePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[2]/div/h1", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div/h1");
//        List<String> productImagePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[3]/div/div[1]/div[2]/div/div/ul/li[1]/div/div/div/picture/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/ul/li[1]/div/div/div/picture/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/ul/li[1]/div/div/div/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div/div/ul/li[1]/div/div/div/img");
//        //List<String> productPricePaths = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[4]/div[1]/div[1]/div/div/div/div[2]", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div/div/div/div[2]","/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[3]/div[4]/div[1]/div/div/div/div[2]");
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[2]/div/div[1]/div/a[2]", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div/div[1]/div/a[2]");
//        List<String> productSpecsPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/div");
//        List<String> productDescriptionPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[2]/div[1]/div/div/div/div[2]/div[1]/div/div[1]");
//        List<String> productSpecsButtonPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/div/div/button");
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[2]/div/div[2]/div/form/div[2]/button[3]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div/div[1]/div/p[2]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//
//            Product product1 = products.getFirst();
//            Product product2 = products.getLast();
//            Assertions.assertEquals(products.size(), 2);
//            System.out.println(products);
//
//            Assertions.assertEquals("XIAOMI Redmi A2 32 GB Light Blue Dual SIM", product1.getName());
//            Assertions.assertEquals("https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_104880642?x=536&y=402&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=536&ey=402&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=536&cdy=402", product1.getImageUrl());
//            Assertions.assertEquals(0, product1.getPriceAmount());
//            Assertions.assertEquals("", product1.getPriceCurrency());
//            Assertions.assertEquals("smartphone", product1.getCategory());
//            Assertions.assertEquals("XIAOMI", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Was sind die Kamera-Spezifikationen des XIAOMI Redmi A2"));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Batterie-/ Akkutyp")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Li-Polymer")));
//
//
//            Assertions.assertEquals("SAMSUNG Galaxy A14 5G 64 GB Black Dual SIM", product2.getName());
//            Assertions.assertEquals("https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_103519718?x=536&y=402&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=536&ey=402&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=536&cdy=402", product2.getImageUrl());
//            Assertions.assertEquals(0, product2.getPriceAmount());
//            Assertions.assertEquals("", product2.getPriceCurrency());
//            Assertions.assertEquals("smartphone", product2.getCategory());
//            Assertions.assertEquals("SAMSUNG", product2.getBrand());
//            Assertions.assertTrue(product2.getDescription().contains("Smarter Einstieg für deinen mobilen Alltag"));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Prozessor-Taktfrequenz")));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Octa-Core Prozessortaktung 2x 2.2 GHz, 6x 1.8 GHz")));
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//    }
//
//    private void testSearchOnDetailViewAllGoodBofrost(boolean headless) {
//        String category = "food";
//        String retailerName = "bofrost";
//        String retailerUuid = "retailerUuid2";
//        String userManualUuid = "userManualUuid2";
//        List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/eiskonfekt-bourbon-vanille.html?position=1&clicked=");
//        String mainPageUrl = "https://www.bofrost.de";
//        List<String> productNamePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[1]");
//        List<String> productImagePath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[1]/div[1]/div[3]/div/div/div[2]/picture/img");
//        List<String> productPricePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[3]/div/div[1]/div[1]/span");
//        List<String> productBrandPath = List.of("");
//        List<String> productSpecsPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[3]");
//        List<String> productDescriptionPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[1]");
//        List<String> productSpecsButtonPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/label[3]");
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[4]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[5]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[2]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//
//            Product product1 = products.getFirst();
//            Assertions.assertEquals(products.size(), 1);
//
//            Assertions.assertEquals("Eiskonfekt Bourbon-Vanille", product1.getName());
//            Assertions.assertEquals("https://www.bofrost.de/medias/00068-DE-eiskonfekt-bourbon-vanille-pic1.jpg-W720xH450R1.6?context=bWFzdGVyfHByb2R1Y3QtaW1hZ2VzfDE0Njc0NHxpbWFnZS9qcGVnfGFHWXlMMmd3TlM4NU5EQTVNamMzT1RVeU1ETXdMekF3TURZNFgwUkZYMlZwYzJ0dmJtWmxhM1F0WW05MWNtSnZiaTEyWVc1cGJHeGxYM0JwWXpFdWFuQm5YMWMzTWpCNFNEUTFNRkl4TGpZfDY0NzJjYWNkODk2NDQ2MjExM2Y1M2VlOTEwZWE4MjQ4YjQ3ZmYwOTRiYmZiOWFiYTNhNzM5NWI3NGVhMjQ2Yjk", product1.getImageUrl());
//            Assertions.assertEquals(9.99, product1.getPriceAmount());
//            Assertions.assertEquals("€", product1.getPriceCurrency());
//            Assertions.assertEquals("food", product1.getCategory());
//            Assertions.assertEquals("bofrost", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Bestes Bourbon-Vanille-Eis, um"));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Ingredients")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("entrahmte Milch, kakaohaltige Fettglasur 36% (pflanzliche Fette (Kokos, Shea), Zucker, fettarmer Kakao 3,6%, Magermilchpulver, Sonnenblumenöl, Emulgator (Lecithine)), Zucker, Kokosfett, Glukosesirup, Schlagsahne 3%, Magermilchpulver, Emulgator (Monound Diglyceride von Speisefettsäuren), Stabilisatoren (Johannisbrotkernmehl, Guarkernmehl), färbendes Karottenkonzentrat, natürliches Bourbon-Vanille-Aroma, extrahierte gemahlene Bourbon-Vanilleschoten. Kann enthalten: Gluten, Nüsse, Weizen, Eier, Erdnüsse, Soja.")));
//
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//
//
//    }
//
//
//        private void testSearchOnDetailViewAllGoodBofrost2(boolean headless) {
//            String category = "food";
//            String retailerName = "bofrost";
//            String retailerUuid = "retailerUuid2";
//            String userManualUuid = "userManualUuid2";
//            List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/spaghetti-eis-tradizionale.html","https://www.bofrost.de/shop/vegetarisch_5587/desserts-backwaren/baeckerbroetchen_10464.html","https://www.bofrost.de/shop/vegetarisch_5587/gemuesezubereitungen_5591/gemuese-staebchen.html");
//            String mainPageUrl = "https://www.bofrost.de";
//            List<String> productNamePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[1]");
//            List<String> productImagePath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[1]/div[1]/div[3]/div/div/div[2]/picture/img", "/html/body/section/div[2]/div/div[1]/div[1]/div[1]/div[1]/div[3]/div/div/div/picture/img");
//            List<String> productPricePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[3]/div/div[1]/div[1]/span");
//            List<String> productBrandPath = List.of("");
//            List<String> productSpecsPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[3]", "/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[1]/div/div[3]");
//            List<String> productDescriptionPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[1]", "/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[1]/div/div[1]");
//            List<String> productSpecsButtonPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/label[3]", "/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[1]/div/label[3]");
//            List<String> productDescriptionButtonPath = List.of();
//            List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[5]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[4]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]");
//            List<String> productOtherButtonPath = List.of();
//            List<String> productCodePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[2]");
//            CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//            crawlerConfiguration.setRetailerName(retailerName);
//            crawlerConfiguration.setMainPageUrl(mainPageUrl);
//            crawlerConfiguration.setRetailerUuid(retailerUuid);
//            Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//            List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//            List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//            try {
//                List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//
//                Assertions.assertEquals(products.size(), 3);
//
//                Assertions.assertEquals("Spaghetti-Eis tradizionale", products.get(0).getName());
//                Assertions.assertEquals("https://www.bofrost.de/medias/00069-DE-spaghetti-eis-tradizionale-pic1.jpg-W720xH450R1.6?context=bWFzdGVyfHByb2R1Y3QtaW1hZ2VzfDE1OTcxMXxpbWFnZS9qcGVnfGNISnZaSFZqZEMxcGJXRm5aWE12YUdKa0wyZzFOaTg1TURBMk5qQTNNelk0TWpJeUxtcHdad3wxNjE2MGFiY2U4NjliMmM3ZWZhY2MwNGNhYzVjYTc1NDU1YWQyYzVhZTFiMjVkYzk2ZTQyNjA2OTg4Y2JkZDU3", products.get(0).getImageUrl());
//                Assertions.assertEquals(10.99, products.get(0).getPriceAmount());
//                Assertions.assertEquals("€", products.get(0).getPriceCurrency());
//                Assertions.assertEquals("food", products.get(0).getCategory());
//                Assertions.assertEquals("bofrost", products.get(0).getBrand());
//                Assertions.assertTrue(products.get(0).getDescription().contains("Auf die italienische Art. Mit Sahne verfeinertes Bou"));
//
//
//                Assertions.assertTrue(products.get(0).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//                Assertions.assertTrue(products.get(0).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Ingredients")));
//                Assertions.assertTrue(products.get(0).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("entrahmte Milch, Zucker, Erdbeerpüree 11%, eingedickte entrahmte Milch, Glukose-Fruktose-Sirup, Kokosfett, Glukosesirup, Invertzuckersirup, Schlagsahne 2%, Emulgatoren (Mono- und Diglyceride von Speisefettsäuren, Lecithine (Soja)), Stabilisatoren (Johannisbrotkernmehl, Guarkernmehl, Pektine), Vollmilchpulver, Kakaobutter, Säuerungsmittel (Citronensäure), färbendes Karottenkonzentrat, extrahierte gemahlene Bourbon-Vanilleschoten, Bourbon-Vanilleextrakt, Vanilleextrakt. Kann enthalten: Gluten, Nüsse, Weizen, Eier, Erdnüsse.")));
//                Assertions.assertEquals("00069",products.get(0).getCode());
//
//                Assertions.assertEquals("Bäckerbrötchen", products.get(1).getName());
//                Assertions.assertEquals("https://www.bofrost.de/medias/W720xH450R1.6-10464-DE-baeckerbroetchen-pic1.jpg?context=bWFzdGVyfHByb2R1Y3QtaW1hZ2VzfDE4MDkzMHxpbWFnZS9qcGVnfGFHSmlMMmc0WkM4NU9UQXlOemsxTVRZeE5qTXdMMWMzTWpCNFNEUTFNRkl4TGpaZk1UQTBOalJmUkVWZlltRmxZMnRsY21KeWIyVjBZMmhsYmw5d2FXTXhMbXB3Wnd8NzhhNjRmZGY5ZWE2NjM5NDRkYjYyOTdiOTk2NTZkMTMyZDE0ZTcyYTljMDVlNTYwYzM4NWQ0YTE3Y2E5MTJkMA", products.get(1).getImageUrl());
//                Assertions.assertEquals(3.99, products.get(1).getPriceAmount());
//                Assertions.assertEquals("€", products.get(1).getPriceCurrency());
//                Assertions.assertEquals("food", products.get(1).getCategory());
//                Assertions.assertEquals("bofrost", products.get(1).getBrand());
//                Assertions.assertTrue(products.get(1).getDescription().contains("Nach alter, traditioneller Rezeptur mit Hefe und Sauerteig ge"));
//
//                Assertions.assertTrue(products.get(1).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//                Assertions.assertTrue(products.get(1).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Ingredients")));
//                Assertions.assertTrue(products.get(1).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Weizenmehl, Wasser, Hefe, Salz, Rapsöl, Zucker, Roggenmehl, Weizenmalzmehl. Kann enthalten: Soja, Milch, Senf, Sesam.")));
//                Assertions.assertEquals("10464",products.get(1).getCode());
//
//
//                Assertions.assertEquals("Gemüse-Stäbchen", products.get(2).getName());
//                Assertions.assertEquals("https://www.bofrost.de/medias/00167-DE-gemuese-staebchen-pic1.jpg-W720xH450R1.6?context=bWFzdGVyfHByb2R1Y3QtaW1hZ2VzfDIwMDkyNHxpbWFnZS9qcGVnfGNISnZaSFZqZEMxcGJXRm5aWE12YURrekwyZzNOeTg1TURBMk5qQTRPVFF4TURnMkxtcHdad3wyMWMyNjE5MWYzMWE0NzZiNDU1MTg0OGE4Y2IyZGIyMTJiNWRiMGY2OTA4N2UxYTczNzU3NGQzOGYyZWVkNjY0",  products.get(2).getImageUrl());
//                Assertions.assertEquals(8.49,  products.get(2).getPriceAmount());
//                Assertions.assertEquals("€",  products.get(2).getPriceCurrency());
//                Assertions.assertEquals("food",  products.get(2).getCategory());
//                Assertions.assertEquals("bofrost",  products.get(2).getBrand());
//                Assertions.assertTrue( products.get(2).getDescription().contains("Gemüse mal ganz anders. Appetitliche Gemüse-Stäbch"));
//                Assertions.assertTrue(products.get(2).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//                Assertions.assertTrue(products.get(2).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Ingredients")));
//                Assertions.assertTrue(products.get(2).getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Karotten 27%, Weizenmehl, Mais 15%, Broccoli 13%, Rapsöl, Magermilchpulver, Wasser, Butter, modifizierte Maisstärke, Edamer 2% (Milch, Salz, Käsereikulturen, mikrobielles Lab), Salz, Zucker, Petersilie, Hefe, Gewürze (Kurkuma, schwarzer Pfeffer, Muskat, Paprika, Zwiebelpulver, Liebstöckel, Pastinakenpulver, Nelken, Lorbeer), Maltodextrin, Steinpilzpulver. Kann enthalten: Krebstiere, Eier, Fisch, Soja, Sellerie, Senf.")));
//                Assertions.assertEquals("00167",products.get(2).getCode());
//
//
//            } catch (NotFoundException notFoundException) {
//                notFoundException.getErrorList().forEach(System.out::println);
//                Assertions.fail("unexpected notFoundException occurred");
//            }
//
//
//    }
//
//    private void testSearchOnDetailViewAllGoodSwarovski(boolean headless) {
//        String category = "jewelry";
//        String retailerName = "swarovski";
//        String retailerUuid = "retailerUuid2";
//        List<String> urls = List.of("https://www.swarovski.com/ro-RO/p-M5639134/Cercei-stud-Stilla-Taietura-octogonala-Albastri-Placat-cu-rodiu/?variantID=5639134");
//        String mainPageUrl = "https://www.swarovski.com";
//
//        List<String> productNamePath = List.of("/html/body/main/section/section[1]/section[1]/section[3]/div[1]/h1/span[1]");
//        List<String> productImagePath = List.of("/html/body/main/section/section[1]/section[1]/section[2]/div[1]/div/div/div/div[3]/a/picture/img");
//        List<String> productPricePath = List.of("/html/body/main/section/section[1]/section[1]/section[3]/div[1]/div[2]");
//        List<String> productBrandPath = List.of("");
//        List<String> productSpecsPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]");
//        List<String> productDescriptionPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[1]");
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of();
//        List<String> productOtherButtonPath = List.of("/html/body/div[1]/div/div[2]/div/div[3]/button[2]", "/html/body/div[2]/div/div[2]/div/div[3]/button[2]");
//        List<String> productCodePath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]/ul/li[1]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(null, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,"",urls, headless);
//
//            Product product1 = products.getFirst();
//            Assertions.assertEquals(products.size(), 1);
//
//            //System.out.println(product1);
//            Assertions.assertEquals("Cercei stud Stilla", product1.getName());
//            Assertions.assertEquals("https://asset.swarovski.com/images/$size_1450/t_swa002/c_scale,dpr_1.0,f_auto,w_375/5639134_ms1/cercei-stud-stilla--t%C4%83ietur%C4%83-octogonal%C4%83--alba%C8%99tri--placat-cu-rodiu-swarovski-5639134.jpg", product1.getImageUrl());
//            Assertions.assertEquals(349.0, product1.getPriceAmount());
//            Assertions.assertEquals("RON", product1.getPriceCurrency());
//            Assertions.assertEquals("jewelry", product1.getCategory());
//            Assertions.assertEquals("swarovski", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Concepuți pentru a fi asortați cu alte piese din familia Stilla, acești cercei cu șurub pot fi purtați în nenumărate combinații. Cu pietre albastre cu tăietură octogonală, în monturi placate cu rodiu, modul în care alegeți să-i purtați este doar începutul distracției."));
//            Assertions.assertEquals("Specifications", product1.getSpecifications().get(1).getHeader());
//            Assertions.assertEquals("Colecție", product1.getSpecifications().get(1).getName());
//            Assertions.assertEquals("  Stilla", product1.getSpecifications().get(1).getValue());
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//    }
//
//    private void testSearchOnDetailViewInvalidXPathCookiesMediamarkt(boolean headless) {
//        String category = "smartphone";
//        String retailerName = "mediamarkt";
//        String retailerUuid = "retailerUuid1";
//        String userManualUuid = "userManualUuid6";
//        List<String> urls = List.of("https://www.mediamarkt.de/de/product/_xiaomi-redmi-a2-32-gb-light-blue-dual-sim-2859031.html", "https://www.mediamarkt.de/de/product/_samsung-galaxy-a14-5g-64-gb-black-dual-sim-2862704.html");
//        String mainPageUrl = "https://www.mediamarkt.de";
//        List<String> productNamePath = List.of("xPath");
//        List<String> productImagePath = List.of("xPath");
//        List<String> productPricePath = List.of("xPath");
//        List<String> productBrandPath = List.of("xPath");
//        List<String> productSpecsPath = List.of("xPath");
//        List<String> productDescriptionPath = List.of("xPath");
//        List<String> productSpecsButtonPath = List.of("xPath");
//        List<String> productDescriptionButtonPath = List.of("xPath");
//        List<String> productCookiesButtonPath = List.of("xPath");
//        List<String> productOtherButtonPath = List.of("xPath");
//        List<String> productCodePath = List.of("xPath");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(1, notFoundException.getErrorList().size());
//        }
//
//    }
//
//
//    private void testSearchOnDetailViewInvalidXPathsMediamarkt(boolean headless) {
//        String category = "smartphone";
//        String retailerName = "mediamarkt";
//        String retailerUuid = "retailerUuid1";
//        String userManualUuid = "userManualUuid6";
//        List<String> urls = List.of("https://www.mediamarkt.de/de/product/_xiaomi-redmi-a2-32-gb-light-blue-dual-sim-2859031.html", "https://www.mediamarkt.de/de/product/_samsung-galaxy-a14-5g-64-gb-black-dual-sim-2862704.html");
//        String mainPageUrl = "https://www.mediamarkt.de";
//        List<String> productNamePath = List.of("xPath");
//        List<String> productImagePath = List.of("xPath");
//        List<String> productPricePath = List.of("xPath");
//        List<String> productBrandPath = List.of("xPath");
//        List<String> productSpecsPath = List.of("xPath");
//        List<String> productDescriptionPath = List.of("xPath");
//        List<String> productSpecsButtonPath = List.of("xPath");
//        List<String> productDescriptionButtonPath = List.of("xPath");
//        List<String> productCookiesButtonPath = List.of("/html/body/div[2]/div/div[2]/div/form/div[2]/button[3]");
//        List<String> productOtherButtonPath = List.of("xPath");
//        List<String> productCodePath = List.of("xPath");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(20, notFoundException.getErrorList().size());
//        }
//
//
//    }
//
//
//    private void testSearchOnDetailViewEmptyXPathsMediamarkt(boolean headless) {
//        String category = "smartphone";
//        String retailerName = "mediamarkt";
//        String retailerUuid = "retailerUuid1";
//        String userManualUuid = "userManualUuid6";
//        List<String> urls = List.of("https://www.mediamarkt.de/de/product/_xiaomi-redmi-a2-32-gb-light-blue-dual-sim-2859031.html", "https://www.mediamarkt.de/de/product/_samsung-galaxy-a14-5g-64-gb-black-dual-sim-2862704.html");
//        String mainPageUrl = "https://www.mediamarkt.de";
//        List<String> productNamePath = List.of();
//        List<String> productImagePath = List.of();
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of();
//        List<String> productDescriptionPath = List.of();
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[2]/div/div[2]/div/form/div[2]/button[3]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of();
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//
//            Assertions.assertEquals(10, notFoundException.getErrorList().size());
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_IMAGE).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_NAME).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_DESCRIPTION).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_CODE).toString())));
//        }
//
//
//    }
//
//
//    private void testSearchOnDetailViewValidMandatoryFieldsMediamarkt(boolean headless) {
//        String category = "smartphone";
//        String retailerName = "mediamarkt";
//        String retailerUuid = "retailerUuid1";
//        String userManualUuid = "userManualUuid6";
//        List<String> urls = List.of("https://www.mediamarkt.de/de/product/_xiaomi-redmi-a2-32-gb-light-blue-dual-sim-2859031.html", "https://www.mediamarkt.de/de/product/_samsung-galaxy-a14-5g-64-gb-black-dual-sim-2862704.html");
//        String mainPageUrl = "https://www.mediamarkt.de";
//        List<String> productNamePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[2]/div/h1", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div/h1");
//        List<String> productImagePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div/div[3]/div/div[1]/div[2]/div/div/ul/li[1]/div/div/div/picture/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[2]/div/div[1]/div[2]/div/div/ul/li[1]/div/div/div/picture/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div/div/ul/li[1]/div/div/div/img", "/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div/div/ul/li[1]/div/div/div/img");
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[2]/div[1]/div/div/div/div[2]/div[2]/div/div");
//        List<String> productDescriptionPath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[2]/div[1]/div/div/div/div[2]/div[1]/div/div[1]");
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[2]/div/div[2]/div/form/div[2]/button[3]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of("/html/body/div[1]/div[3]/main/div[1]/div[1]/div[2]/div[1]/div/div[1]/div/p[2]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//
//            Product product1 = products.getFirst();
//            Product product2 = products.getLast();
//            Assertions.assertEquals(products.size(), 2);
//
//            System.out.println(products);
//
//            Assertions.assertEquals("XIAOMI Redmi A2 32 GB Light Blue Dual SIM", product1.getName());
//            Assertions.assertEquals("https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_104880642?x=536&y=402&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=536&ey=402&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=536&cdy=402", product1.getImageUrl());
//            Assertions.assertEquals(0, product1.getPriceAmount());
//            Assertions.assertEquals("", product1.getPriceCurrency());
//            Assertions.assertEquals("smartphone", product1.getCategory());
//            Assertions.assertEquals("mediamarkt", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Was sind die Kamera-Spezifikationen des XIAOMI Redmi A2"));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Prozessor-Taktfrequenz")));
//            Assertions.assertTrue(product1.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Octa-Core-Prozessor mit bis zu 2.2 GHz")));
//
//
//            Assertions.assertEquals("SAMSUNG Galaxy A14 5G 64 GB Black Dual SIM", product2.getName());
//            Assertions.assertEquals("https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_103519718?x=536&y=402&format=jpg&quality=80&sp=yes&strip=yes&trim&ex=536&ey=402&align=center&resizesource&unsharp=1.5x1+0.7+0.02&cox=0&coy=0&cdx=536&cdy=402", product2.getImageUrl());
//            Assertions.assertEquals(0, product2.getPriceAmount());
//            Assertions.assertEquals("", product2.getPriceCurrency());
//            Assertions.assertEquals("smartphone", product2.getCategory());
//            Assertions.assertEquals("mediamarkt", product2.getBrand());
//            Assertions.assertTrue(product2.getDescription().contains("Smarter Einstieg für deinen mobilen Alltag"));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getHeader().equals("Specifications")));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getName().equals("Prozessor-Taktfrequenz")));
//            Assertions.assertTrue(product2.getSpecifications().stream().anyMatch(productSpecification -> productSpecification.getValue().equals("Octa-Core Prozessortaktung 2x 2.2 GHz, 6x 1.8 GHz")));
//
//
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//
//    }
//
//
//    private void testSearchOnDetailViewInvalidXPathCookiesBofrost(boolean headless) {
//        String category = "food";
//        String retailerName = "bofrost";
//        String retailerUuid = "retailerUuid2";
//        String userManualUuid = "userManualUuid2";
//        List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/eiskonfekt-bourbon-vanille.html?position=1&clicked=", "https://www.bofrost.de/shop/vegetarisch_5587/eis_5592/spaghetti-eis-tradizionale.html?position=3&clicked=search");
//        String mainPageUrl = "https://www.bofrost.de";
//        List<String> productNamePath = List.of("");
//        List<String> productImagePath = List.of("");
//        List<String> productPricePath = List.of("");
//        List<String> productBrandPath = List.of("");
//        List<String> productSpecsPath = List.of("");
//        List<String> productDescriptionPath = List.of("");
//        List<String> productSpecsButtonPath = List.of("");
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]/blabla");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of("");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(1, notFoundException.getErrorList().size());
//        }
//
//
//    }
//
//
//    private void testSearchOnDetailViewEmptyXPathsBofrost(boolean headless) {
//        String category = "food";
//        String retailerName = "bofrost";
//        String retailerUuid = "retailerUuid2";
//        String userManualUuid = "userManualUuid2";
//        List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/eiskonfekt-bourbon-vanille.html?position=1&clicked=");
//        String mainPageUrl = "https://www.bofrost.de";
//        List<String> productNamePath = List.of();
//        List<String> productImagePath = List.of();
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of();
//        List<String> productDescriptionPath = List.of();
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[5]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[4]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of();
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("unexpected notFoundException was thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//
//            Assertions.assertEquals(5, notFoundException.getErrorList().size());
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_IMAGE).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_NAME).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_DESCRIPTION).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_CODE).toString())));
//        }
//    }
//
//
//    private void testSearchOnDetailViewInvalidXPathsBofrost(boolean headless) {
//        String category = "food";
//        String retailerName = "bofrost";
//        String retailerUuid = "retailerUuid2";
//        String userManualUuid = "userManualUuid2";
//        List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/eiskonfekt-bourbon-vanille.html?position=1&clicked=");
//        String mainPageUrl = "https://www.bofrost.de";
//
//        List<String> productNamePath = List.of("xPath");
//        List<String> productImagePath = List.of("xPath");
//        List<String> productPricePath = List.of("xPath");
//        List<String> productBrandPath = List.of("xPath");
//        List<String> productSpecsPath = List.of("xPath");
//        List<String> productDescriptionPath = List.of("xPath");
//        List<String> productSpecsButtonPath = List.of("xPath");
//        List<String> productDescriptionButtonPath = List.of("xPath");
//        List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[5]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[4]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]");
//        List<String> productOtherButtonPath = List.of("xPath");
//        List<String> productCodePath = List.of("xPath");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(10, notFoundException.getErrorList().size());
//        }
//
//    }
//
//
//    private void testSearchOnDetailViewValidMandatoryFieldsBofrost(boolean headless) {
//        String category = "food";
//        String retailerName = "bofrost";
//        String retailerUuid = "retailerUuid2";
//        String userManualUuid = "userManualUuid2";
//        List<String> urls = List.of("https://www.bofrost.de/shop/eis_5500/eisdesserts_5502/eiskonfekt-bourbon-vanille.html?position=1&clicked=");
//        String mainPageUrl = "https://www.bofrost.de";
//        List<String> productNamePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[1]");
//        List<String> productImagePath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[1]/div[1]/div[3]/div/div/div[2]/picture/img");
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[3]");
//        List<String> productDescriptionPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/div[1]");
//        List<String> productSpecsButtonPath = List.of("/html/body/section/div[2]/div/div[1]/div[1]/div[3]/div[2]/div/label[3]");
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("/html/body/div[9]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[5]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]", "/html/body/div[4]/div[3]/div/div[2]/div/div/div[2]/div[1]/button[2]");
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of("/html/body/section/div[2]/div/div[1]/div[2]/div/div[2]/div[2]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(userManualUuid, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,userManualUuid,urls, headless);
//
//            Product product1 = products.getFirst();
//            Assertions.assertEquals(products.size(), 1);
//
//            Assertions.assertEquals("Eiskonfekt Bourbon-Vanille", product1.getName());
//            Assertions.assertEquals("https://www.bofrost.de/medias/00068-DE-eiskonfekt-bourbon-vanille-pic1.jpg-W720xH450R1.6?context=bWFzdGVyfHByb2R1Y3QtaW1hZ2VzfDE0Njc0NHxpbWFnZS9qcGVnfGFHWXlMMmd3TlM4NU5EQTVNamMzT1RVeU1ETXdMekF3TURZNFgwUkZYMlZwYzJ0dmJtWmxhM1F0WW05MWNtSnZiaTEyWVc1cGJHeGxYM0JwWXpFdWFuQm5YMWMzTWpCNFNEUTFNRkl4TGpZfDY0NzJjYWNkODk2NDQ2MjExM2Y1M2VlOTEwZWE4MjQ4YjQ3ZmYwOTRiYmZiOWFiYTNhNzM5NWI3NGVhMjQ2Yjk", product1.getImageUrl());
//            Assertions.assertEquals(0, product1.getPriceAmount());
//            Assertions.assertEquals("", product1.getPriceCurrency());
//            Assertions.assertEquals("food", product1.getCategory());
//            Assertions.assertEquals("bofrost", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Bestes Bourbon-Vanille-Eis, um"));
//            Assertions.assertEquals("Specifications", product1.getSpecifications().get(0).getHeader());
//            Assertions.assertEquals("Ingredients", product1.getSpecifications().get(0).getName());
//            Assertions.assertEquals("entrahmte Milch, kakaohaltige Fettglasur 36% (pflanzliche Fette (Kokos, Shea), Zucker, fettarmer Kakao 3,6%, Magermilchpulver, Sonnenblumenöl, Emulgator (Lecithine)), Zucker, Kokosfett, Glukosesirup, Schlagsahne 3%, Magermilchpulver, Emulgator (Monound Diglyceride von Speisefettsäuren), Stabilisatoren (Johannisbrotkernmehl, Guarkernmehl), färbendes Karottenkonzentrat, natürliches Bourbon-Vanille-Aroma, extrahierte gemahlene Bourbon-Vanilleschoten. Kann enthalten: Gluten, Nüsse, Weizen, Eier, Erdnüsse, Soja.", product1.getSpecifications().get(0).getValue());
//
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//    }
//
//
//    private void testSearchOnDetailViewInvalidXPathCookiesSwarovski(boolean headless) {
//        String category = "jewelry";
//        String retailerName = "swarovski";
//        String retailerUuid = "retailerUuid2";
//        List<String> urls = List.of("https://www.swarovski.com/ro-RO/p-M5639134/Cercei-stud-Stilla-Taietura-octogonala-Albastri-Placat-cu-rodiu/?variantID=5639134");
//        String mainPageUrl = "https://www.swarovski.com";
//
//        List<String> productNamePath = List.of("/html/body/main/section/section[1]/section[1]/section[3]/div[1]/h1/span[1]");
//        List<String> productImagePath = List.of("/html/body/main/section/section[1]/section[1]/section[2]/div[1]/div/div/div/div[3]/a/picture/img");
//        List<String> productPricePath = List.of("/html/body/main/section/section[1]/section[1]/section[3]/div[1]/div[2]");
//        List<String> productBrandPath = List.of("");
//        List<String> productSpecsPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]");
//        List<String> productDescriptionPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[1]");
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of("blabla");
//        List<String> productOtherButtonPath = List.of("/html/body/div[1]/div/div[2]/div/div[3]/button[2]", "/html/body/div[2]/div/div[2]/div/div[3]/button[2]");
//        List<String> productCodePath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]/ul/li[1]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(null, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,"",urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(1, notFoundException.getErrorList().size());
//        }
//
//    }
//
//
//    private void testSearchOnDetailViewEmptyXPathsSwarovski(boolean headless) {
//        String category = "jewelry";
//        String retailerName = "swarovski";
//        String retailerUuid = "retailerUuid2";
//        List<String> urls = List.of("https://www.swarovski.com/ro-RO/p-M5639134/Cercei-stud-Stilla-Taietura-octogonala-Albastri-Placat-cu-rodiu/?variantID=5639134");
//        String mainPageUrl = "https://www.swarovski.com";
//
//        List<String> productNamePath = List.of();
//        List<String> productImagePath = List.of();
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of();
//        List<String> productDescriptionPath = List.of();
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of();
//        List<String> productOtherButtonPath = List.of();
//        List<String> productCodePath = List.of();
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(null, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,"",urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(5, notFoundException.getErrorList().size());
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_IMAGE).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_NAME).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_DESCRIPTION).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_CODE).toString())));
//        }
//
//
//    }
//
//    private void testSearchOnDetailViewInvalidXPathsSwarovski(boolean headless) {
//        String category = "jewelry";
//        String retailerName = "swarovski";
//        String retailerUuid = "retailerUuid2";
//        List<String> urls = List.of("https://www.swarovski.com/ro-RO/p-M5639134/Cercei-stud-Stilla-Taietura-octogonala-Albastri-Placat-cu-rodiu/?variantID=5639134");
//        String mainPageUrl = "https://www.swarovski.com";
//
//        List<String> productNamePath = List.of("xPath");
//        List<String> productImagePath = List.of("xPath");
//        List<String> productPricePath = List.of("xPath");
//        List<String> productBrandPath = List.of("xPath");
//        List<String> productSpecsPath = List.of("xPath");
//        List<String> productDescriptionPath = List.of("xPath");
//        List<String> productSpecsButtonPath = List.of("xPath");
//        List<String> productDescriptionButtonPath = List.of("xPath");
//        List<String> productCookiesButtonPath = List.of();
//        List<String> productOtherButtonPath = List.of("/html/body/div[1]/div/div[2]/div/div[3]/button[2]", "/html/body/div[2]/div/div[2]/div/div[3]/button[2]");
//        List<String> productCodePath = List.of("xPath");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(null, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,"",urls, headless);
//            Assertions.fail("notFoundException was not thrown");
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.assertEquals(9, notFoundException.getErrorList().size());
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_IMAGE).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_NAME).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_DESCRIPTION).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_SPECIFICATIONS).toString())));
//            Assertions.assertTrue(notFoundException.getErrorList().stream().anyMatch(error -> error.fieldName().equals(Utils.mapFromString(PRODUCT_CODE).toString())));
//        }
//
//    }
//
//    private void testSearchOnDetailViewValidMandatoryFieldsSwarovski(boolean headless) {
//        String category = "jewelry";
//        String retailerName = "swarovski";
//        String retailerUuid = "retailerUuid2";
//        List<String> urls = List.of("https://www.swarovski.com/ro-RO/p-M5639134/Cercei-stud-Stilla-Taietura-octogonala-Albastri-Placat-cu-rodiu/?variantID=5639134");
//        String mainPageUrl = "https://www.swarovski.com";
//
//        List<String> productNamePath = List.of("/html/body/main/section/section[1]/section[1]/section[3]/div[1]/h1/span[1]");
//        List<String> productImagePath = List.of("/html/body/main/section/section[1]/section[1]/section[2]/div[1]/div/div/div/div[3]/a/picture/img");
//        List<String> productPricePath = List.of();
//        List<String> productBrandPath = List.of();
//        List<String> productSpecsPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]");
//        List<String> productDescriptionPath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[1]");
//        List<String> productSpecsButtonPath = List.of();
//        List<String> productDescriptionButtonPath = List.of();
//        List<String> productCookiesButtonPath = List.of();
//        List<String> productOtherButtonPath = List.of("/html/body/div[1]/div/div[2]/div/div[3]/button[2]", "/html/body/div[2]/div/div[2]/div/div[3]/button[2]");
//        List<String> productCodePath = List.of("/html/body/main/section/section[1]/section[2]/div/div/div/div[1]/div[2]/ul/li[1]");
//        CrawlerConfiguration crawlerConfiguration = new CrawlerConfiguration(productNamePath, productImagePath, productPricePath, productBrandPath, productSpecsPath, productDescriptionPath, productSpecsButtonPath, productDescriptionButtonPath, productCookiesButtonPath, productOtherButtonPath, productCodePath);
//        crawlerConfiguration.setMainPageUrl(mainPageUrl);
//        crawlerConfiguration.setRetailerName(retailerName);
//        crawlerConfiguration.setRetailerUuid(retailerUuid);
//        Mockito.when(crawlerConfigurationService.getConfiguration(retailerName)).thenReturn(crawlerConfiguration);
//
//        List<ManualUrls> manualUrls = List.of(new ManualUrls(null, urls));
//        List<CategoryManualsUrls> categoryManualsUrlsList = List.of(new CategoryManualsUrls(category, manualUrls));
//
//        try {
//            List<Product> products = webCrawlingService.searchOnDetailView(crawlerConfiguration,category,"",urls, headless);
//
//            Product product1 = products.getFirst();
//            Assertions.assertEquals(products.size(), 1);
//
//            //System.out.println(product1);
//            Assertions.assertEquals("Cercei stud Stilla", product1.getName());
//            Assertions.assertEquals("https://asset.swarovski.com/images/$size_1450/t_swa002/c_scale,dpr_1.0,f_auto,w_375/5639134_ms1/cercei-stud-stilla--t%C4%83ietur%C4%83-octogonal%C4%83--alba%C8%99tri--placat-cu-rodiu-swarovski-5639134.jpg", product1.getImageUrl());
//            Assertions.assertEquals(0, product1.getPriceAmount());
//            Assertions.assertEquals("", product1.getPriceCurrency());
//            Assertions.assertEquals("jewelry", product1.getCategory());
//            Assertions.assertEquals("swarovski", product1.getBrand());
//            Assertions.assertTrue(product1.getDescription().contains("Concepuți pentru a fi asortați cu alte piese din familia Stilla, acești cercei cu șurub pot fi purtați în nenumărate combinații. Cu pietre albastre cu tăietură octogonală, în monturi placate cu rodiu, modul în care alegeți să-i purtați este doar începutul distracției."));
//            Assertions.assertEquals("Specifications", product1.getSpecifications().get(1).getHeader());
//            Assertions.assertEquals("Colecție", product1.getSpecifications().get(1).getName());
//            Assertions.assertEquals("  Stilla", product1.getSpecifications().get(1).getValue());
//        } catch (NotFoundException notFoundException) {
//            notFoundException.getErrorList().forEach(System.out::println);
//            Assertions.fail("unexpected notFoundException occurred");
//        }
//    }
//
//
//    @Test
//    public void manyTestsHeadless() {
//
//        for (int i = 0; i <= 10; i++) {
//            System.out.println("i=" + i);
//            testSearchOnDetailViewAllGoodMediamarktHEADLESS();
//
//        }
//    }
//
//
//}
