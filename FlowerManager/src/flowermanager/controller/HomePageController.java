package flowermanager.controller;

import flowermanager.database.Database;
import flowermanager.getData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class HomePageController implements Initializable {

    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;

    @FXML
    private Button btnLogout;

    @FXML
    private BarChart<String, Double> home_chart;

    @FXML
    private Label lbFlower;

    @FXML
    private Label lbRevenu;

    @FXML
    private Label lbCustomer;
    @FXML
    private Button btnFlower;
    @FXML
    private Button btnPurchase;

    @FXML
    private ImageView adminImage;

    @FXML
    private Label lbWelcome;

    @FXML
    public void logout() {
        try {
            btnLogout.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/flowermanager/view/Login.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void moveFlowerForm() {
        try {
            btnFlower.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/FlowerManager.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Flower Managerment");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private Button returnFlower;

    @FXML
    public void moveReturnForm() {
        try {
            returnFlower.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/ReturnForm.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Flowers Return");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void movePurchaseForm() {
        try {
            btnPurchase.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/Order.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Flower Managerment");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int homeAvailableFlower() {

        String sql = "SELECT COUNT(id) FROM flowers WHERE status = 'Available'";

        connect = Database.connectDB();
        int countF = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countF = result.getInt("COUNT(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countF;
    }

    public double homeRevenu() {
    String customerSql = "SELECT SUM(total) FROM customer_info";
    String returnSql = "SELECT SUM(amount) FROM return_flower";
    double customerTotal = 0;
    double returnTotal = 0;
    
    try (Connection conn = Database.connectDB();
         PreparedStatement customerStmt = conn.prepareStatement(customerSql);
         PreparedStatement returnStmt = conn.prepareStatement(returnSql);
         ResultSet customerRs = customerStmt.executeQuery();
         ResultSet returnRs = returnStmt.executeQuery()) {
        
        if (customerRs.next()) {
            customerTotal = customerRs.getDouble(1);
        }
        
        if (returnRs.next()) {
            returnTotal = returnRs.getDouble(1);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return customerTotal - returnTotal;
}


    public int homeCustomer() {

        String sql = "SELECT COUNT(customer_id) FROM customer_info";

        connect = Database.connectDB();
        int countF = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countF = result.getInt("COUNT(customer_id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countF;
    }

    public void homeChart() {

        home_chart.getData().clear();

        String sql = "SELECT date, SUM(total) FROM customer_info GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                String date = result.getString("date");
                Double total = result.getDouble("SUM(total)");
                //XYChart.Series charte = new XYChart.Series(date,total);  
                XYChart.Data data = new XYChart.Data(date, total);

                chart.getData().add(data);
            }

            home_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeChart();
        Image image = new Image(new File(getData.imagePath).toURI().toString());
        adminImage.setImage(image);
        lbWelcome.setText("Welcome, " + getData.username);
        lbFlower.setText("Total : " + String.valueOf(homeAvailableFlower()) + " type(s)");
        lbRevenu.setText("Total : " + "$" + homeRevenu());
        lbCustomer.setText("Total : " + homeCustomer() + " customer(s)");
    }

}
