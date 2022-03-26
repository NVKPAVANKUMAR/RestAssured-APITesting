package pojo.utilities;

import constants.ApplicationConstants;
import constants.FrameworkConstants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class Api {

    private static RequestSpecification requestSpec = null;
    private static ResponseSpecification responseSpec;

    public static void configReqestSpecification() {
        getRequestSpecification();
    }

    private static RequestSpecification getRequestSpecification() {

        try {
            requestSpec = new RequestSpecBuilder()
                    .setBaseUri(ApplicationConstants.baseURI)
                    .addFilter(RequestLoggingFilter.logRequestTo(new PrintStream(
                            new FileOutputStream(FrameworkConstants.REQUEST_LOG))))
                    .addFilter(ResponseLoggingFilter.logResponseTo(new PrintStream(
                            new FileOutputStream(FrameworkConstants.RESPONSE_LOG))))
                    .setContentType(ContentType.JSON).build();
        } catch (Exception e) {
            System.out.println(" Not able to create the request specification.");
            System.out.println(e.getStackTrace());

        }
        return requestSpec;
    }

    public static Response getAPI(String path) {
        return given()
                .spec(requestSpec)
                .when()
                .get(path);
    }

    public static <T> T getAPI(String path, int statusCode, Class<T> responseClass) {
        return given()
                .spec(requestSpec)
                .when()
                .get(path)
                .then()
                .statusCode(statusCode)
                .extract().as(responseClass);
    }

    public static Response postAPI(String path, Object bodyPayload) {
        return given()
                .spec(requestSpec)
                .body(bodyPayload)
                .when()
                .post(path);
    }

    public static <T> T postAPI(String path, Object bodyPayload, int statusCode, Class<T> responseClass) {
        return given()
                .spec(requestSpec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(statusCode)
                .extract().as(responseClass);
    }

    public static Response putAPI(String path, Object bodyPayload, String token, String contentType) {
        return given().
                spec(requestSpec).
                header("Content-Type", contentType).
                header("Accept", contentType).
                header("Cookie", token).
                body(bodyPayload).log().body().
                when().
                put(path);
    }

    public static Response patchAPI(String path, String bodyPayload, String token, String contentType) {
        return given().
                spec(requestSpec).
                contentType(contentType).
                header("Accept", contentType).
                header("Cookie", token).
                body(bodyPayload).log().body().
                when().
                patch(path);
    }

    public static Response deleteAPI(String path, String token, String contentType) {
        return given().
                spec(requestSpec).
                header("Content-Type", contentType).
                header("Cookie", token).
                when().
                patch(path);
    }

    public void responseSpecification(int statusCode) {
        responseSpec = new ResponseSpecBuilder().expectStatusCode(statusCode).build();
    }

}
