package br.com.tdd.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.tdd.config.TestConfigs;
import br.com.tdd.testcontainers.AbstractTesteIntegracao;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DisplayName("Teste JUnit para Swagger")
class SwaggerTesteIntegracao extends AbstractTesteIntegracao {

    @Test
    @DisplayName("Teste JUnit para a página de interface do usuário do Swagger")
    void validacaoParaPaginaDeInterfaceDoSwagger() {
        var content = given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfigs.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();
        assertTrue(content.contains("Swagger UI"));
    }
}