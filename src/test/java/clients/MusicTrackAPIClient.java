package clients;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public final class MusicTrackAPIClient {

    public static RequestSpecification Request;

    public MusicTrackAPIClient() {

        RequestSpecBuilder builder = new RequestSpecBuilder();

        //Enable Test to run in the selected environment
        String environment = System.getProperty("environment", "test");
        if (environment.equalsIgnoreCase("test")) {
            builder.setBaseUri("https://testapi.io/api/ottplatform/");
        } else {
            throw new RuntimeException("Please provide valid environment : " + environment);
        }

        builder.setContentType(ContentType.JSON);
        RequestSpecification requestSpec = builder.build();

        try {
            RestAssured.useRelaxedHTTPSValidation();
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }

        Request = RestAssured.given().spec(requestSpec);
    }

    public Response getMusicTracks(String url) {
        Request.log().all();
        return Request.get(url);
    }
}


