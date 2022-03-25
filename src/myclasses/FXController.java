package myclasses;

import entity.Brand;
import entity.Sneaker;
import facade.BrandFacade;
import facade.SneakerFacade;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXController {
    
    private SneakerFacade sneakerFacade;
    private BrandFacade brandFacade;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void AddUser(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/myclasses/sampleUser.fxml"));
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
    private Button btnAddSneaker;

    @FXML
    private AnchorPane dropDownEmpty;

    @FXML
    private Text txtFieldAddSneakerInfo;

    @FXML
    private TextField txtFieldFirm;

    @FXML
    private TextField txtFieldModel;

    @FXML
    private TextField txtFieldPrice;

    @FXML
    private TextField txtFieldQuantity;

    @FXML
    private TextField txtFieldSize;
    
    public FXController(){
        sneakerFacade = new SneakerFacade(Sneaker.class);
        brandFacade = new BrandFacade(Brand.class);
    }
    
    @FXML
    void initialize() {
        btnAddSneaker.setOnAction((event) -> {
            try { 
            Sneaker sneaker= new Sneaker();
            Brand brand = new Brand();
            sneaker.setSneakerQuantity(Integer.parseInt(txtFieldQuantity.getText())); 
            brand.setBrand(txtFieldFirm.getText());
            sneaker.setSneakerFirm(brand);       
            sneaker.setSneakerModel(txtFieldModel.getText());
            sneaker.setSneakerSize(Double.parseDouble(txtFieldSize.getText()));
            sneaker.setSneakerPrice(Double.parseDouble(txtFieldPrice.getText()));
            txtFieldAddSneakerInfo.setText("Вы добавили обувь в базу!");
            brandFacade.create(brand);
            sneakerFacade.create(sneaker);
            } catch (Exception e) {
                txtFieldAddSneakerInfo.setText("Ошибка!");
            }
        });
    }

}
