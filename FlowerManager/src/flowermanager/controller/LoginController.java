/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package flowermanager.controller;

import flowermanager.database.Database;
import flowermanager.getData;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare; // tạo ra một truy vấn có tham số
    private ResultSet result; // trả về tập hợp các kết quả được trả về từ cơ sở dữ liệu sau khi truy vấn được thực thi

    @FXML
    private AnchorPane loginForm;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;
    @FXML
    private CheckBox rbShowpassWord;

    @FXML
    public void handleCheckBox(ActionEvent event) {

        if (rbShowpassWord.isSelected()) {
            password.setText(password.getText());
            password.setPromptText("Enter Password");
        } else {
            password.setPromptText("");
            password.setText(password.getPromptText());
        }
    }

    @FXML
    private void login() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());//Thiết lập giá trị cho tham số thứ nhất
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                
                if (result.next()) {
                    getData.imagePath = result.getString("image");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    getData.username = username.getText();

                    loginBtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("/flowermanager/view/HomePage.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("HomePage");
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.closeConnection(connect);
        }

    }

    public void close() {
        System.exit(0);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        password.setPromptText("Enter password");
        username.setPromptText("Enter username");
    }

    public Parent getPane() {
        return loginForm;
    }

}
