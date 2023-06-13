/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ReturnFormController implements Initializable {

    @FXML
    private AnchorPane form;

    @FXML
    private ImageView imgFlower;

    private Connection connect;
    private PreparedStatement prepare;

    @FXML
    private TextField txtFlowerName;

    private ResultSet result;
    @FXML
    private Button btnFlower;
    @FXML
    private Button btnHomepage;

    @FXML
    private ImageView adminImage;

    @FXML
    private Label lbWelcome;

    Image image;

    @FXML
    private AnchorPane purchase_form;

    @FXML
    private ComboBox<Integer> purchase_flowerID;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private Button btnReturn;

    @FXML
    private Label purchase_total;

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
    public void moveHomepageForm() {
        try {
            btnHomepage.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/HomePage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Flower Managerment");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void purchaseFlowerId() {
        String sql = "SELECT status, id FROM flowers WHERE status = 'Available'";
        connect = Database.connectDB();
        try {
            ObservableList listData = FXCollections.observableArrayList();// Khởi tạo một đối tượng ObservableList
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                listData.add(result.getInt("id"));
            }
            purchase_flowerID.setItems(listData);
            purchaseFlowerNameAndImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void purchaseFlowerNameAndImage() {

        String sql = "SELECT id, name, image FROM flowers WHERE id = '"
                + purchase_flowerID.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()) {
                txtFlowerName.setText(result.getString("name"));
                String uri = "file:" + result.getString("image");
                //Việc thêm "file:" vào đường dẫn tệp ảnh là để chỉ định rằng đường dẫn này là đường dẫn tới
                //một tệp cục bộ trên hệ thống tệp của máy tính,
                //chứ không phải là đường dẫn tới một tài nguyên trên mạng
                image = new Image(uri);
                imgFlower.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpinnerValueFactory<Integer> spinner;

    public void purchaseSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);//tạo ra một SpinnerValueFactory<Integer> để chứa giá trị Integer, 
        //có giá trị tối thiểu là 0 và tối đa là 10, và giá trị mặc định ban đầu là 0.
        // IntegerSpinnerValueFactory(min,max,default)

        purchase_quantity.setValueFactory(spinner);
    }

    @FXML
    public void chooseFlower() {
        double priceData = 0;
        double totalPrice;
        int id = purchase_flowerID.getSelectionModel().getSelectedItem();
        String checkPrice = "SELECT name, price FROM flowers WHERE id = ?";
        try {
            prepare = connect.prepareStatement(checkPrice);
            prepare.setInt(1, id);
            result = prepare.executeQuery();

            if (result.next()) {
                priceData = result.getDouble("price");
            }
            totalPrice = priceData * purchase_quantity.getValue();
            purchase_total.setText("$" + String.valueOf(totalPrice));
        } catch (SQLException ex) {
            Logger.getLogger(ReturnFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void chooseQuantity() {
        double priceData = 0;
        double totalPrice;
        int id = purchase_flowerID.getSelectionModel().getSelectedItem();
        String checkPrice = "SELECT name, price FROM flowers WHERE id = ?";
        try {
            prepare = connect.prepareStatement(checkPrice);
            prepare.setInt(1, id);
            result = prepare.executeQuery();

            if (result.next()) {
                priceData = result.getDouble("price");
            }
            totalPrice = priceData * purchase_quantity.getValue();
            purchase_total.setText("$" + String.valueOf(totalPrice));
        } catch (SQLException ex) {
            Logger.getLogger(ReturnFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void returnFlower() {
        Alert alert;
        String sql = "INSERT INTO return_flower (amount) VALUES (?)";
        String str = purchase_total.getText();
        String replace = str.replace("$", "0");
        double amount = Double.parseDouble(replace);
        if (amount == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please choose flower(s)");
            alert.show();
            return;
        }
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                connect = Database.connectDB();
                prepare = connect.prepareStatement(sql);
                prepare.setDouble(1, amount);
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successful");
                alert.showAndWait();
                clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReturnFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clear() {
        purchase_flowerID.getSelectionModel().clearSelection();
        txtFlowerName.setText("");
        imgFlower.setImage(null);
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
        purchase_total.setText("$" + 0.00);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        purchaseFlowerId();
        purchaseSpinner();
        image = new Image(new File(getData.imagePath).toURI().toString());
        txtFlowerName.setEditable(false);
    }

}
