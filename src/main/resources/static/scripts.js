function submit() {
    var response =
        "Requested " + document.getElementById("party-size").value
        + " ticket(s) on " + document.getElementById("travel-date").value
        + " from " + document.getElementById("departure").value
        + " to " + document.getElementById("arrival").value
        + "."
    alert(response);
}

async function getAccessToken() {
    let accessToken = ""
    let url = "https://test.api.amadeus.com/v1/security/oauth2/token"

    var details = {
        'client_id': '4u3S14ZTnv0G75lnMzRvz9CDNQ0aPGMt',
        'client_secret': 'kXzFmTwO4YphklII',
        'grant_type': 'client_credentials'
    };

    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");

    try {
        const config = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body: formBody
        }
        const response = await fetch(url, config)
        if (response.ok) {
            let okta = await response.json();
            accessToken = okta.access_token;
        } else {
            //
        }
    } catch (error) {

    }

    return accessToken
}

async function getFlightOffers(origin = "GSP", destination = "LON", departureDate = "2023-06-11", adults = 2, max = 5) {
    let accessToken = await getAccessToken();
    let auth = 'Bearer ' + accessToken;
    let url = "https://test.api.amadeus.com/v2/shopping/flight-offers"
    let queryParams = "" +
        "originLocationCode=" + document.getElementById("departure").value +
        "&destinationLocationCode=" + document.getElementById("arrival").value +
        "&departureDate=" + document.getElementById("travel-date").value +
        "&adults=" + document.getElementById("party-size").value +
        "&max=" + max;

    //TODO: Edit queryParams to read from input params rather than html elements
    // "originLocationCode="+ origin +
    // "&destinationLocationCode="+ destination +
    // "&departureDate="+ departureDate +
    // "&adults="+ adults +
    // "&max=" + max;

    // "Requested " + document.getElementById("party-size").value
    // + " ticket(s) on " + document.getElementById("travel-date").value
    // + " from " + document.getElementById("departure").value
    // + " to " + document.getElementById("arrival").value

    let endpoint = url + "?" + queryParams;

    try {
        const config = {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Authorization': auth
            }
        }
        const response = await fetch(endpoint, config)
        if (response.ok) {
            let responseJSON = await response.json();
            let responseSummary = "Your ticket will cost " + responseJSON.data[0].price.total
            alert(responseSummary);
            return responseSummary;
        } else {
            //
        }
    } catch (error) {

    }
}

//Example HTTP request function
async function getForecast(latitude, longitude) {
    let forecastRequestString = "http://localhost:8080/weather/forecast/" + latitude + "/" + longitude;

    let headers = new Headers([
        ['Content-Type', 'application/json'],
        ['Accept', 'application/json']
    ]);

    let request = new Request(forecastRequestString, {
        method: 'GET',
        headers: headers
    });

    let result = await fetch(request);
    let response = await result.json();
    let forecast = response.properties.periods[0].detailedForecast;
    alert(forecast);
}