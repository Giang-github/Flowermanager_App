/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package flowermanager.controller;

import flowermanager.Converter;
import flowermanager.database.Database;
import flowermanager.entity.Order;
import flowermanager.getData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class OrderController implements Initializable {

    private String PATH = "C:\\Users\\Admin\\Documents\\Order.csv";

    @FXML
    private TableColumn<Order, String> cl_customerId;

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
    private Button purchase_addCart;

    @FXML
    private Label purchase_total;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private TableView<Order> purchase_tableView;

    @FXML
    private TableColumn<Order, String> purchase_col_flowerID;

    @FXML
    private TableColumn<Order, String> purchase_col_flowerName;

    @FXML
    private TableColumn<Order, String> purchase_col_quantity;

    @FXML
    private TableColumn<Order, String> purchase_col_price;

    @FXML
    private TableColumn<Order, Integer> clOrderId;

    @FXML
    private Button btnRemove;

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
            ObservableList listData = FXCollections.observableArrayList();
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
                image = new Image(uri);
                imgFlower.setImage(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpinnerValueFactory<Integer> spinner;
    public void purchaseSpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
    }
    private int qty;
    public void purchaseQuantity() {
        qty = purchase_quantity.getValue();
    }
    private int customerId;
    public void purchaseCustomerId() {
        String sql = "SELECT MAX(customer_id) FROM customer";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                customerId = result.getInt("MAX(customer_id)");
            }
            int countData = 0;
            String checkInfo = "SELECT MAX(customer_id) FROM customer_info";
            prepare = connect.prepareStatement(checkInfo);
            result = prepare.executeQuery();
            if (result.next()) {
                countData = result.getInt("MAX(customer_id)");
            }
            if (customerId == 0) {
                customerId += 1;
            } else if (customerId == countData) {
                customerId = countData + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Order> purchaseListData() {
        purchaseCustomerId();
        ObservableList<Order> listData = FXCollections.observableArrayList();// new ObservableList
        String sql = "SELECT * FROM customer WHERE customer_id = '" + customerId + "'";
        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Order customer;
            while (result.next()) {
                customer = new Order(result.getInt("customer_id"),
                        result.getInt("flower_id"), result.getString("name"),
                        result.getInt("quantity"), result.getDouble("total_price"),
                        result.getDate("date"), result.getInt("id"));
                listData.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    Path path = Paths.get(PATH);
    @FXML
    private Button btnPrintOrder;

    @FXML
    public void printOrder() {
        Alert alert;
        if (purchaseListData().size() == 0) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Printing Error");
            alert.setContentText("Not Flower(s) ");
            alert.showAndWait();
            return;
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write("" + "," + "Name Flower, Quantity , Price($), Purchase Date");
            writer.newLine();
            for (Order order : purchaseListData()) {
                writer.write(Converter.OrderToCsvString(order));
                writer.newLine();

            }
            writer.write("Total($)" + "," + "," + "," + "$ " + totalP);
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Printing Successful");
            alert.setContentText("The order list has been printed successfully.");
            alert.showAndWait();
        } catch (IOException e) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Printing Error");
            alert.setContentText("An error occurred while printing the order list.");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }
//    try (BufferedReader reader = new BufferedReader(new FileReader("path/to/file.csv"))) {
//    String line;
//    while ((line = reader.readLine()) != null) {
//        String[] fields = line.split(",");
//        // xử lý các trường dữ liệu trong mỗi dòng ở đây
//    }
//} catch (IOException e) {
//    e.printStackTrace();
//}

    private ObservableList<Order> purchaseListD;

    public void purchaseShowListData() {
        purchaseListD = purchaseListData();

        purchase_col_flowerID.setCellValueFactory(new PropertyValueFactory<>("flowerId"));
        purchase_col_flowerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        purchase_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purchase_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        cl_customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        clOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        purchase_tableView.setItems(purchaseListD);
    }

    public void purchaseAddToCart() {
        purchaseCustomerId(); // customerId = 1

        String sql = "INSERT INTO customer (customer_id, flower_id, name, quantity, total_price, date) "
                + "VALUES(?,?,?,?,?,?)";

        connect = Database.connectDB();

        try {
            Alert alert;

            if (purchase_flowerID.getSelectionModel().getSelectedItem() == null
                    || qty == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please choose the product and quantity");
                alert.showAndWait();
            } else {
                double priceData = 0;
                double totalPrice;
                int id = purchase_flowerID.getSelectionModel().getSelectedItem();
                String checkPrice = "SELECT name, price FROM flowers WHERE id = ?";

                prepare = connect.prepareStatement(checkPrice);
                prepare.setInt(1, id);
                result = prepare.executeQuery();

                if (result.next()) {
                    priceData = result.getDouble("price");
                }

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(customerId));
                prepare.setInt(2, (Integer) purchase_flowerID.getSelectionModel().getSelectedItem());
                prepare.setString(3, (String) txtFlowerName.getText());
                prepare.setString(4, String.valueOf(qty));

                totalPrice = (priceData * qty);

                prepare.setString(5, String.valueOf(totalPrice));

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(6, String.valueOf(sqlDate));

                prepare.executeUpdate();

                purchaseShowListData();
                purchaseDisplayTotal();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double totalP = 0;

    public void purchaseDisplayTotal() {
        purchaseCustomerId(); 
        String sql = "SELECT SUM(total_price) FROM customer WHERE customer_id = '" + customerId + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(total_price)");
            }

            purchase_total.setText("$" + String.valueOf(totalP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void purchasePay() {

        String sql = "INSERT INTO customer_info (customer_id, total, date) VALUES(?,?,?)";

        connect = Database.connectDB();

        try {
            Alert alert;

            if (totalP == 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Nothing in your cart !");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, String.valueOf(customerId));
                    prepare.setString(2, String.valueOf(totalP));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(3, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successful !! Thanks for purchase.");
                    alert.showAndWait();

                    totalP = 0;
                    clear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void clear() {
        purchase_flowerID.getSelectionModel().clearSelection();
        txtFlowerName.setText("");
        imgFlower.setImage(null);
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        purchase_quantity.setValueFactory(spinner);
        purchase_total.setText("$" + 0.00);
        purchase_tableView.getItems().clear();
        purchase_tableView.refresh();

    }

    public void removeItem() {
        Alert alert;
        Order order = purchase_tableView.getSelectionModel().getSelectedItem();
        if (order == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Not record was choice");
            alert.showAndWait();
        }
        connect = Database.connectDB();
        int id = order.getOrderId();
        String sql = "DELETE FROM customer WHERE id = ? ";
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete flowers was choice ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, id);
                prepare.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();
                purchaseDisplayTotal();
                purchaseShowListData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        purchaseFlowerId();
        purchaseSpinner();
        purchaseDisplayTotal();
        purchaseShowListData();
        image = new Image(new File(getData.imagePath).toURI().toString());
        adminImage.setImage(image);
        lbWelcome.setText("Welcome, " + getData.username);
        txtFlowerName.setEditable(false);
    }

}
