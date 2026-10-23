package tests;

import activities.gmail.GmailComposeActivity;
import activities.gmail.GmailMainActivity;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sesion.Sesion;

import java.time.Duration;

public class SendEmailTest {

    GmailMainActivity gmailMainActivity = new GmailMainActivity();
    GmailComposeActivity gmailComposeActivity = new GmailComposeActivity();

    @BeforeEach
    public void setup() {
        Sesion.getSesion("gmail");
    }

    @AfterEach
    public void tearDown() {
        Sesion.getSesion().closeSesion();
    }

    @Test
    public void sendEmail() {

        AppiumDriver driver = Sesion.getSesion().getDevice();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(30)
        );

        // 1. Cerrar la bienvenida si aparece.
        clickIfPresent(
                driver,
                By.id("com.google.android.gm:id/welcome_tour_got_it"),
                5
        );

        // 2. Continuar a Gmail si aparece la pantalla de bienvenida.
        By takeMeToGmail =
                By.id("com.google.android.gm:id/action_done");

        clickIfPresent(driver, takeMeToGmail, 5);

        // 3. Rechazar el permiso si aparece.
        clickIfPresent(
                driver,
                By.id("com.android.permissioncontroller:id/permission_deny_button"),
                5
        );

        // 4. Cerrar el panel de Google Meet si aparece.
        clickIfPresent(
                driver,
                AppiumBy.accessibilityId("Close"),
                5
        );

        // 5. Abrir la pantalla de redacción.
        By composeButton = AppiumBy.accessibilityId("Compose");

        wait.until(
                ExpectedConditions.elementToBeClickable(composeButton)
        ).click();

        // 6. Escribir el destinatario.
        WebElement recipient = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        gmailComposeActivity.recipientLocator
                )
        );

        gmailComposeActivity.recipientTextBox.setText(
                "noeliapaoladuranllaveta@gmail.com"
        );

        System.out.println(
                "Correo escrito. Esperando la sugerencia de Gmail."
        );

        // Seleccionar la sugerencia del destinatario.
        By recipientSuggestion = By.id(
                "com.google.android.gm:id/peoplekit_listview_contact_method"
        );

        WebElement suggestion = wait.until(
                ExpectedConditions.elementToBeClickable(
                        recipientSuggestion
                )
        );

        suggestion.click();

        System.out.println("Sugerencia del destinatario seleccionada.");

        // 7. Esperar el campo del asunto.
        By subjectLocator =
                By.id("com.google.android.gm:id/subject");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(subjectLocator)
        );

        System.out.println("Campo Subject disponible.");

        // Escribir el asunto.
        gmailComposeActivity.subjectTextBox.setText(
                "Prueba de automatizacion Gmail"
        );

        // 8. Activar el editor del cuerpo del correo.
        By composeEmailLocator = By.xpath(
                "//*[contains(@text, 'Compose email')]"
        );

        WebElement composeEmail = wait.until(
                ExpectedConditions.elementToBeClickable(composeEmailLocator)
        );

        composeEmail.click();

        // Escribir el cuerpo del correo.
        driver.switchTo().activeElement().sendKeys(
                "Este correo fue enviado mediante una prueba de Appium y JUnit."
        );

        System.out.println("Se intentó escribir el cuerpo del correo.");

        // 9. Enviar el correo.
        By sendButton = AppiumBy.accessibilityId("Send");

        wait.until(
                ExpectedConditions.elementToBeClickable(sendButton)
        ).click();

        // 10. Verificar la notificación de envío.
        // El texto "Sent" está en el TextView hijo description_text,
        // no directamente en el contenedor snackbar_main_content.
        By sentMessageLocator =
                By.id("com.google.android.gm:id/description_text");

        WebElement sentMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        sentMessageLocator
                )
        );

        String actualMessage = sentMessage.getText();

        Assertions.assertEquals(
                "Sent",
                actualMessage,
                "Gmail no mostró la confirmación de envío esperada."
        );

        System.out.println(
                "Confirmación de Gmail: " + actualMessage
        );
    }

    // Intentar hacer clic en elementos que pueden no aparecer.
    private void clickIfPresent(
            AppiumDriver driver,
            By locator,
            int seconds
    ) {
        try {
            WebDriverWait optionalWait = new WebDriverWait(
                    driver, Duration.ofSeconds(seconds)
            );

            optionalWait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            ).click();

        } catch (TimeoutException e) {
            // Si el elemento no aparece, continuar.
        }
    }
}