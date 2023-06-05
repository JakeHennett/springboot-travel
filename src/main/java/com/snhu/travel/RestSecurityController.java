package com.snhu.travel;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestSecurityController {

    private static final String FILENAME = "c:\\test\\users.xml";

    @RequestMapping("/security")
    public String securityTest() {
        return "test security endpoints";
    }

    // http://localhost:8080/security/allusers?continue
    @GetMapping(value = "/security/allusers")
    private List<User> getAllUsers() {
        String result = "";
        List<User> userList = new ArrayList();

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("user");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();

                    result += "\nCurrent Element :" + node.getNodeName();
                    result += "\nUsername : " + username;
                    result += "\nPassword : " + password;

                    User newUser = new User(username, password);
                    userList.add(newUser);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        return userList;
    }

    //http://localhost:8080/security/validate?uname=default&pass=password
    @GetMapping(value = "/security/validate")
    private boolean validateUser(@RequestParam String uname, @RequestParam String pass) {
        boolean response = false;
        List<User> userList = getAllUsers();
        User validate = new User(uname, pass);
        // boolean response = userList.contains(validate);
        System.out.println(validate.getUsername());
        System.out.println(validate.getPassword());

        for (User checkUser : userList)
        {
            System.out.println(checkUser.getUsername());
            System.out.println(checkUser.getPassword());

            if(validate.getUsername().equals(checkUser.getUsername()))
            {
                if(validate.getPassword().equals(checkUser.getPassword()))
                {
                    response = true;
                }
            }
        }


        return response;
    }
    // @GetMapping(value = "/callclienthello")
    // private String getHelloClient() {
    // String uri = "http://localhost:8080/test";
    // RestTemplate restTemplate = new RestTemplate();
    // String result = restTemplate.getForObject(uri, String.class);
    // return result;
    // }

    // @GetMapping(value = "/scryfall")
    // private String getScryfallRandom() {
    // String uri = "https://api.scryfall.com/cards/random";
    // RestTemplate restTemplate = new RestTemplate();
    // String result = restTemplate.getForObject(uri, String.class);
    // return result;
    // }

    // //http://localhost:8080/weather/points/31/-82
    // @GetMapping(value = "/weather/points/{latitude}/{longitude}")
    // private String getPointsByCoordinates(@PathVariable Long latitude,
    // @PathVariable Long longitude) {
    // String uri = "https://api.weather.gov/points/" + latitude + "," + longitude;
    // RestTemplate restTemplate = new RestTemplate();
    // String result = restTemplate.getForObject(uri, String.class);
    // return result;
    // }

    // //http://localhost:8080/weather/forecast/31/-82
    // @GetMapping(value = "/weather/forecast/{latitude}/{longitude}")
    // private String getForecastByCoordinates(@PathVariable Long latitude,
    // @PathVariable Long longitude) {
    // RestTemplate restTemplate = new RestTemplate();

    // //points - https://api.weather.gov/points/35,-82
    // String pointsUri = "https://api.weather.gov/points/" + latitude + "," +
    // longitude;
    // String pointsStringResult = restTemplate.getForObject(pointsUri,
    // String.class);

    // // "forecast": "https://api.weather.gov/gridpoints/JAX/50,93/forecast"
    // // Use indexOf and substring to isolate the forecast url for the provided GPS
    // coordinates.
    // int urlLocationStart =
    // pointsStringResult.indexOf("https://api.weather.gov/gridpoints");
    // int urlLocationEnd = pointsStringResult.indexOf("forecast",
    // urlLocationStart);
    // String extractedURL = pointsStringResult.substring(urlLocationStart,
    // urlLocationEnd+8);
    // String forecastResult = restTemplate.getForObject(extractedURL,
    // String.class);
    // //http://localhost:8080/weather/forecast/35/-82 =
    // https://api.weather.gov/gridpoints/GSP/78,51/forecast
    // //http://localhost:8080/weather/forecast/31/-82 =
    // https://api.weather.gov/gridpoints/JAX/50,93/forecast
    // //http://localhost:8080/weather/forecast/30/-85 =
    // https://api.weather.gov/gridpoints/TAE/58,65/forecast

    // // This would be dynamic and more reliable, but we aren't going to bother
    // with JSON manipulation right now.
    // // //forecast - https://api.weather.gov/gridpoints/{office}/{grid X},{grid
    // Y}/forecast
    // // String office = "";
    // // //pointsResult.office;
    // // String gridX = "";
    // // String gridY = "";
    // // String forecastUri = "https://api.weather.gov/gridpoints/" + office + "/"
    // + gridX + "," + gridY + "/forecast";
    // // Forecast forecastResult = restTemplate.getForObject(forecastUri,
    // Forecast.class);

    // String result = forecastResult;
    // return result;
    // }
}
