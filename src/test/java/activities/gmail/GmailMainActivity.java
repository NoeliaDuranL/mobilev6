
package activities.gmail;

import control.Button;
import control.Label;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class GmailMainActivity {

    // Botón para empezar a redactar un correo.
    public Button composeButton =
            new Button(AppiumBy.accessibilityId("Compose"));

    // Botón que cierra el aviso de Google Meet, si aparece.
    public Button dismissMeetButton =
            new Button(By.id("com.google.android.gm:id/dismiss_button"));

    // Elemento de confirmación que aparece después del envío.
    public Label sentNotification =
            new Label(By.id("com.google.android.gm:id/snackbar_main_content"));
}
