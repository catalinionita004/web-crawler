# 🌐 Web Crawler Backend Project

This is a backend project for a web crawler that collects data using Chrome Driver. The project can be accessed at the following link: [Swagger UI](https://web-crawler-s7qlbu7unq-ez.a.run.app/swagger-ui/index.html#).

## 🚀 Features

- Collects data from retailer websites (e.g., eMAG) using Chrome Driver.
- Swagger UI interface for testing and accessing the API.

## 📽️ Demo Video

Curious to see how it all works? Watch our demo video to see the application in action! 🍿

[![IMAGE ALT TEXT HERE](https://camo.githubusercontent.com/414ef4e0ce20d5c28416c3d6419611ca27ebc3e4fd85895054fa129c1f2637c8/68747470733a2f2f692e626c6f67732e65732f3962313961642f796f75747562652f3435305f313030302e77656270)](https://www.youtube.com/watch?v=pLioIlQ2klsa)

## 🛠️ System Requirements

- Google Cloud Platform account.
- Bigquery dataset
- ```crawler_configuration``` and ```product``` tables in that dataset

## ⚙️ Local Setup

1. **Set Environment Variables:**
   - Ensure you have the `GOOGLE_APPLICATION_CREDENTIALS` environment variable set to the path of the JSON file containing your Google Cloud project key.

   ```
    GOOGLE_APPLICATION_CREDENTIALS="C:\path\to\your-file.json" 
   ```
   - Ensure you have the `BIGQUERY_DATASET`  environment variable set to the name of your bigquery dataset
    ```
    BIGQUERY_DATASET="my_bigquery_dataset" 
   ```
   - Ensure you have the `BIGQUERY_PROJECT`
    ```
    BIGQUERY_PROJECT="my_bigquery_project" 
   ```
   
2. **Download Chrome Driver:**

   - Download Chrome Driver from [Chrome for Testing](https://googlechromelabs.github.io/chrome-for-testing/) and place it in local C (C:/chromedriver.exe).






# API Endpoints
## Example request:

- ### `/api/v1/product/retailers` - for getting retailers


- ### `api/v1/product/save/test` - to test and to save a configuration for a retailer

```json
{
   "crawlerConfigurationDTO": {
      "retailerName": "emag",
      "retailerUuid": "emag1234",
      "mainPageUrl": "https://www.emag.ro/",
      "productNamePaths": [
         "/html/body/div[3]/div[2]/div/section[2]/div/div[1]/h1"
      ],
      "productImagePaths": [
         "/html/body/div[3]/div[2]/div/section[3]/div/div[1]/div[1]/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/a/img"
      ],
      "productPricePaths": [
         "/html/body/div[3]/div[2]/div/section[3]/div/div[1]/div[2]/div/div[2]/div[2]/form/div/div[1]/div[1]/div/div/div/p[2]"
      ],
      "productBrandPaths": [
         "/html/body/div[3]/div[2]/div/section[9]/div/div/div[1]/div[2]/p[1]/a"
      ],
      "productSpecsPaths": [
         "/html/body/div[3]/div[2]/div/section[9]/div/div"
      ],
      "productDescriptionPaths": [
         "/html/body/div[3]/div[2]/div/section[8]/div/div/div"
      ],
      "productSpecsButtonPaths": [
         "/html/body/div[3]/div[2]/div/section[9]/div/div/div[1]/button"
      ],
      "productDescriptionButtonPaths": [
         "/html/body/div[3]/div[2]/div/section[8]/div/div/div/div[1]/div[3]/a"
      ],
      "productCookiesButtonPaths": [

      ],
      "productOtherButtonPaths": [

      ],
      "productCodePaths": [
         "/html/body/div[3]/div[2]/div/section[1]/div/div/span"
      ]
   },
   "urls": [
      "https://www.emag.ro/telefon-mobil-samsung-galaxy-s24-ultra-dual-sim-12gb-ram-512gb-5g-titanium-gray-sm-s928bztheue/pd/DS6L7KYBM/"
   ]
}

```

- ### `api/v1/product/save` - to save one or more products

```json

{
   "retailerUuid": "emag1234",
   "category": "pool",
   "urls": [
      "https://www.emag.ro/telefon-mobil-samsung-galaxy-s24-ultra-dual-sim-12gb-ram-512gb-5g-titanium-gray-sm-s928bztheue/pd/DS6L7KYBM/"
   ]
}

```

- ### `api/v1/product/filterBy' - 








