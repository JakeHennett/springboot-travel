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
    //Create an HTTP request that matches the following cURL
    //     curl --location 'https://test.api.amadeus.com/v1/security/oauth2/token' \
    // --header 'Content-Type: application/x-www-form-urlencoded' \
    // --data-urlencode 'grant_type=client_credentials' \
    // --data-urlencode 'client_id=4u3S14ZTnv0G75lnMzRvz9CDNQ0aPGMt' \
    // --data-urlencode 'client_secret=kXzFmTwO4YphklII'
    return accessToken
}