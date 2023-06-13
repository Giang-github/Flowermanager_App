/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package flowermanager.controller;

import flowermanager.database.Database;
import flowermanager.entity.Flower;
import flowermanager.getData;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FlowerManagerController implements Initializable {

    @FXML
    private Button btnPurchase;

    @FXML
    private AnchorPane availableFlowers_form;

    @FXML
    private ImageView availableFlowers_imageView;

    @FXML
    private Button availableFlowers_importBtn;

    @FXML
    public TextField availableFlowers_flowerName = new TextField();

    @FXML
    public ComboBox<String> availableFlowers_status = new ComboBox<>();

    @FXML
    public TextField availableFlowers_price = new TextField();
    @FXML
    public TableView<Flower> availableFlowers_tableView = new TableView<>();

    ObservableList<String> items;

    @FXML
    private Button availableFlowers_addBtn;

    @FXML
    private Button availableFlowers_updateBtn;

    @FXML
    private Button availableFlowers_clearBtn;

    @FXML
    private Button availableFlowers_deleteBtn;

    @FXML
    private TextField availableFlowers_search;


    @FXML
    private TableColumn<Flower, String> availableFlowers_col_flowerID;

    @FXML
    private TableColumn<Flower, String> availableFlowers_col_flowerName;

    @FXML
    private TableColumn<Flower, String> availableFlowers_col_status;

    @FXML
    private TableColumn<Flower, String> availableFlowers_col_price;

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button btnHomepage;
    @FXML
    private ImageView adminImage;
    @FXML
    private Label lbWelcome;
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    public void moveHomepage() {
        try {
            btnHomepage.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/HomePage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Homepage");
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

    public void availableFlowersInsertImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            Path imagePath = Paths.get("src", "flowermanager", "image");
            if (!Files.exists(imagePath)) {
                try {
                    Files.createDirectories(imagePath);
                } catch (IOException ex) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Unable to create image directory");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                    return;
                }
            }
            String imageName = file.getName();
            String destinationPath = "src/flowermanager/image/" + imageName;
            Path destination = Paths.get(destinationPath);
            try {
                Files.copy(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                getData.path = destination.toString();
                Image image = new Image(destination.toUri().toString());
                availableFlowers_imageView.setImage(image);
            } catch (IOException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Unable to save image");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

//    public ObservableList<Flower> availableFlowersListData() {
//        ObservableList<Flower> listData = FXCollections.observableArrayList();
//        String sql = "SELECT * FROM flowers";
//        connect = Database.connectDB();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            result = prepare.executeQuery();
//            Flower flower;
//
//            while (result.next()) {
//                flower = new Flower(result.getInt("id"),
//                        result.getString("name"), result.getString("status"),
//                        result.getDouble("price"), result.getString("image"),
//                        result.getDate("date"));
//                listData.add(flower);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (connect != null) {
//                Database.closeConnection(connect);
//            }
//            try {
//                if (result != null) {
//                    result.close();
//                }
//                if (prepare != null) {
//                    prepare.close();
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return listData;
//    }
    private ObservableList<Flower> flowersList = FXCollections.observableArrayList();
//
//// Phương thức này được gọi để khởi tạo TableView và hiển thị dữ liệu

    public void availableFlowersInitialize() {
        //...
        availableFlowers_col_flowerID.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
        availableFlowers_col_flowerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        availableFlowers_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        availableFlowers_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        availableFlowers_tableView.setItems(flowersList);
        availableFlowersShowListData();
        //...
    }
//

    public void availableFlowersShowListData() {
        String sql = "SELECT * FROM flowers";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Flower flower;
            while (result.next()) {
                flower = new Flower(result.getInt("id"),
                        result.getString("name"), result.getString("status"),
                        result.getDouble("price"), result.getString("image"),
                        result.getDate("date"));
                flowersList.add(flower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void availableFlowersAdd() {
        String sql = "INSERT INTO flowers (name, status, price, image, date) "
                + "VALUES(?,?,?,?,?)";
        connect = Database.connectDB();
        try {
            Alert alert;
            if (availableFlowers_flowerName.getText().isEmpty()
                    || availableFlowers_status.getSelectionModel().getSelectedItem() == null
                    || availableFlowers_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, availableFlowers_flowerName.getText());
                prepare.setString(2, availableFlowers_status.getSelectionModel().getSelectedItem());
                prepare.setString(3, availableFlowers_price.getText());
                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");
                prepare.setString(4, uri);
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                prepare.setString(5, String.valueOf(sqlDate));
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                availableFlowers_tableView.getItems().clear();
                availableFlowersShowListData();
                availableFlowersClear();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection(connect);
        }
    }

    @FXML
    public void availableFlowersUpdate() {
        Alert alert;
        if (availableFlowers_tableView.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Not record was choice");
            alert.showAndWait();
        }
        String uri = getData.path;
        if (!(uri == null || uri == "")) {
            uri = uri.replace("\\", "\\\\");
        }
        int id = availableFlowers_tableView.getSelectionModel().getSelectedItem().getFlowerId();
        String sql = "UPDATE flowers SET name = ?, status = ?, price = ?, image = ? WHERE id = ?";
        connect = Database.connectDB();

        try {
            if (availableFlowers_flowerName.getText().isEmpty()
                    || availableFlowers_status.getSelectionModel().getSelectedItem() == null
                    || availableFlowers_price.getText().isEmpty()
                    || uri == null || uri == "" || getData.path == null || getData.path == "") {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, availableFlowers_flowerName.getText());
                    prepare.setString(2, availableFlowers_status.getSelectionModel().getSelectedItem());
                    prepare.setString(3, availableFlowers_price.getText());
                    prepare.setString(4, uri);
                    prepare.setInt(5, id);
                    prepare.executeUpdate();
                    Flower updatedFlower = availableFlowers_tableView.getSelectionModel().getSelectedItem();
                    updatedFlower.setName(availableFlowers_flowerName.getText());
                    updatedFlower.setStatus(availableFlowers_status.getSelectionModel().getSelectedItem());
                    updatedFlower.setPrice(Double.valueOf(availableFlowers_price.getText()));
                    updatedFlower.setImage(uri);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    availableFlowers_tableView.refresh();
                    availableFlowersClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableFlowersDelete() {
        Alert alert;
        if (availableFlowers_tableView.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Not record was choice");
            alert.showAndWait();
        }
        List<Flower> flowersSelected = availableFlowers_tableView.getSelectionModel().getSelectedItems();
        List<Integer> idSelected = new ArrayList<>();
        for (Flower flower : flowersSelected) {
            idSelected.add(flower.getFlowerId());
        }
        StringBuilder sb = new StringBuilder();
        for (int id : idSelected) {
            sb.append(id).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        String sql = "DELETE FROM flowers WHERE id IN (" + sb.toString() + ")";

        connect = Database.connectDB();

        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete flowers was choice ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.executeUpdate();
                Flower updatedFlower = availableFlowers_tableView.getSelectionModel().getSelectedItem();
                flowersList.remove(updatedFlower);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                availableFlowers_tableView.refresh();
                availableFlowersClear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableFlowersClear() {
        availableFlowers_flowerName.setText("");
        availableFlowers_status.getSelectionModel().clearSelection();
        availableFlowers_price.setText("");
        getData.path = "";

        availableFlowers_imageView.setImage(null);
        availableFlowers_tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void availableFlowersSearch() {
        String searchKey = availableFlowers_search.getText().toLowerCase();
        FilteredList<Flower> filteredData = new FilteredList<>(flowersList, flower
                -> flower.getFlowerId().toString().contains(searchKey)
                || flower.getName().toLowerCase().contains(searchKey)
                || flower.getStatus().toLowerCase().contains(searchKey)
                || flower.getPrice().toString().contains(searchKey)
        );

        availableFlowers_tableView.setItems(filteredData);
    }

    @FXML
    public void availableFlowersSelect() {

        Flower flower = availableFlowers_tableView.getSelectionModel().getSelectedItem();
        int num = availableFlowers_tableView.getSelectionModel().getSelectedIndex();

        if (num < 0) {
            return;
        }

        availableFlowers_flowerName.setText(flower.getName());
        availableFlowers_price.setText(String.valueOf(flower.getPrice()));
        getData.path = flower.getImage();
        String uri = "file:" + flower.getImage();
        image = new Image(uri, 116, 139, false, true);
        availableFlowers_imageView.setImage(image);
        String status = flower.getStatus();
        if (items.contains(status)) {
            availableFlowers_status.setValue(status);
        } else {
            availableFlowers_status.setValue("");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = FXCollections.observableArrayList("Available", "Not Available");
        availableFlowers_status.setItems(items);
        image = new Image(new File(getData.imagePath).toURI().toString());
        adminImage.setImage(image);
        lbWelcome.setText("Welcome, " + getData.username);
        availableFlowersInitialize();
    }

}
