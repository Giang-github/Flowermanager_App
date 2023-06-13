package flowermanager.controller;

import flowermanager.getData;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class AvailableFlowersAddTest {
    @BeforeClass
    public static void initJFX() {
        new JFXPanel();
    }
    @Test
    public void testAvailableFlowersAdd_Success() {

        Platform.runLater(() -> {
            FlowerManagerController controller = new FlowerManagerController();
            controller.availableFlowers_flowerName.setText("Rose");
            controller.availableFlowers_status.getSelectionModel().select("Available");
            controller.availableFlowers_price.setText("10.00");
            getData.path = "C:\\Users\\User\\Pictures\\rose.jpg";
            controller.availableFlowersAdd();
            int actualSize = controller.availableFlowers_tableView.getItems().size();
            System.out.println(actualSize);
            assertEquals(1, actualSize);
        });
    }
    @Test
    public void testAvailableFlowersAdd_False() {

        Platform.runLater(() -> {
            FlowerManagerController controller = new FlowerManagerController();
            controller.availableFlowersAdd();
            int actualSize = controller.availableFlowers_tableView.getItems().size();
            System.out.println(actualSize);
            assertEquals(0,actualSize);
        });
    }

}
