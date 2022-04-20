package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static listeners.CustomAllureListener.withCustomTemplates;
import static org.hamcrest.Matchers.is;

public class reqresApiTests {

    @Test
    @DisplayName("Получение одного пользователя")
    void singleUser() {
        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("Создание пользователя")
    void userCreate() {
        String requestBody = "{\"name\": \"Bob\", \"job\": \"Developer\"}";

        given()
                .filter(withCustomTemplates())
                .body(requestBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("Bob"))
                .body("job", is("Developer"));
    }
        @Test
        @DisplayName("Редактирование пользователя")
        void userUpdate() {
            String requestBody = "{\"name\": \"Bob\", \"job\": \"Web-Developer\"}";

            given()
                    .filter(withCustomTemplates())
                    .body(requestBody)
                    .contentType(JSON)
                    .when()
                    .put("https://reqres.in/api/users/2")
                    .then()
                    .statusCode(200)
                    .body("name", is("Bob"))
                    .body("job", is("Web-Developer"));
        }

        @Test
        @DisplayName("Удаление пользователя")
        void userDelete() {
            given()
                    .filter(withCustomTemplates())
                    .contentType(JSON)
                    .when()
                    .delete("https://reqres.in/api/users/2")
                    .then()
                    .statusCode(204);
        }
}
