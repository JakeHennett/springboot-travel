package com.snhu.travel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTravelController {
    
    @RequestMapping("/test")
    public String test() {
        return "test string output";
    }

    @GetMapping(value = "/callclienthello")
    private String getHelloClient() {
        String uri = "http://localhost:8080/test";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping(value = "/scryfall")
    private String getScryfallRandom() {
        String uri = "https://api.scryfall.com/cards/random";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    //http://localhost:8080/weather/points/31/-82
    @GetMapping(value = "/weather/points/{latitude}/{longitude}")
    private String getPointsByCoordinates(@PathVariable Long latitude, @PathVariable Long longitude) {
        String uri = "https://api.weather.gov/points/" + latitude + "," + longitude;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }
    
    //http://localhost:8080/weather/forecast/31/-82
    @GetMapping(value = "/weather/forecast/{latitude}/{longitude}")
    private String getForecastByCoordinates(@PathVariable Long latitude, @PathVariable Long longitude) {
        RestTemplate restTemplate = new RestTemplate();

        //points - https://api.weather.gov/points/35,-82
        String pointsUri = "https://api.weather.gov/points/" + latitude + "," + longitude;
        String pointsStringRaw = restTemplate.getForObject(pointsUri, String.class);
        String pointsStringResult = (pointsStringRaw == null) ? "" : pointsStringRaw;   //catch a potential null on failed http request

        // "forecast": "https://api.weather.gov/gridpoints/JAX/50,93/forecast"
        // Use indexOf and substring to isolate the forecast url for the provided GPS coordinates.
        int urlLocationStart = pointsStringResult.indexOf("https://api.weather.gov/gridpoints");
        int urlLocationEnd = pointsStringResult.indexOf("forecast", urlLocationStart);
        String extractedURL = pointsStringResult.substring(urlLocationStart, urlLocationEnd+8);
        String forecastResult = restTemplate.getForObject(extractedURL, String.class);
        //http://localhost:8080/weather/forecast/35/-82 = https://api.weather.gov/gridpoints/GSP/78,51/forecast
        //http://localhost:8080/weather/forecast/31/-82 = https://api.weather.gov/gridpoints/JAX/50,93/forecast
        //http://localhost:8080/weather/forecast/30/-85 = https://api.weather.gov/gridpoints/TAE/58,65/forecast

        // This would be dynamic and more reliable, but we aren't going to bother with JSON manipulation right now.
        // //forecast - https://api.weather.gov/gridpoints/{office}/{grid X},{grid Y}/forecast
        // String office = "";
        // //pointsResult.office;
        // String gridX = "";
        // String gridY = "";
        // String forecastUri = "https://api.weather.gov/gridpoints/" + office + "/" + gridX + "," + gridY + "/forecast";
        // Forecast forecastResult = restTemplate.getForObject(forecastUri, Forecast.class);

        String result = forecastResult;
        return result;
    }
}
