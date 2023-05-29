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

async function getFlightOffers() {
    let accessToken = getAccessToken();

    alert(accessToken);
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