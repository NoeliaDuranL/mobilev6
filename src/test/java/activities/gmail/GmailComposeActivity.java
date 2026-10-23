
package activities.gmail;

import control.Button;
import control.TextBox;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class GmailComposeActivity {

    // Localizador del campo donde se escribe el destinatario.
    public By recipientLocator = By.xpath(
            "//android.view.ViewGroup[@resource-id='com.google.android.gm:id/peoplekit_autocomplete_chip_group']/android.widget.EditText"
    );

    public TextBox recipientTextBox =
            new TextBox(recipientLocator);

    // Campo del asunto.
    public TextBox subjectTextBox =
            new TextBox(By.id("com.google.android.gm:id/subject"));

    // Campo del cuerpo: confirmar en Inspector que sea editable.
    public TextBox bodyTextBox =
            new TextBox(By.id("com.google.android.gm:id/wc_body"));

    // Priorizar Accessibility ID para el botón Enviar.
    public Button sendButton =
            new Button(AppiumBy.accessibilityId("Send"));
}
