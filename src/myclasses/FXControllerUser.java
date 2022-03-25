package myclasses;

import entity.Buyer;
import facade.BuyerFacade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXControllerUser {
    
    private BuyerFacade buyerFacade;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void AddSneaker(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/myclasses/sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void EditSneaker(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/myclasses/sampleBuy.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private Button btnAddUser;

    @FXML
    private Text txtFieldAddUserInfo;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldLast;

    @FXML
    private TextField txtFieldPhone;

    @FXML
    private TextField txtFieldMoney;

    public FXControllerUser(){
        buyerFacade = new BuyerFacade(Buyer.class);
    }
    
    @FXML
    void initialize() {
       btnAddUser.setOnAction((event) -> {
           try {  
            Buyer buyer= new Buyer();
            buyer.setBuyerFirstName(txtFieldName.getText());
            buyer.setBuyerLastName(txtFieldLast.getText());
            buyer.setBuyerPhone(txtFieldPhone.getText());
            buyer.setBuyerMoney(Double.parseDouble(txtFieldMoney.getText()));
            txtFieldAddUserInfo.setText("Вы добавили покупателя в базу");
            buyerFacade.create(buyer);
           } catch (Exception e) {
               txtFieldAddUserInfo.setText("Ошибка!");
           }
       });
    }

}
