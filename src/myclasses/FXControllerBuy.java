package myclasses;

import entity.Buyer;
import entity.History;
import entity.Sneaker;
import facade.BuyerFacade;
import facade.HistoryFacade;
import facade.SneakerFacade;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXControllerBuy {
    
    SneakerFacade sneakerFacade;
    BuyerFacade buyerFacade;
    HistoryFacade historyFacade;

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
    void AddUser(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/myclasses/sampleUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private Button btnAddPurchase;

    @FXML
    private Button btnReload;

    @FXML
    private AnchorPane dropDownEmpty;

    @FXML
    private ListView<Sneaker> listSneaker;

    @FXML
    private ListView<Buyer> listUser;

    @FXML
    private Text txtFieldInfo;

    public FXControllerBuy(){
        sneakerFacade = new SneakerFacade(Sneaker.class);
        buyerFacade = new BuyerFacade(Buyer.class);
        historyFacade = new HistoryFacade(History.class);
    }
    
    @FXML
    void initialize() {
       btnReload.setOnAction((event) -> {
           txtFieldInfo.setText("");
           btnReload.setVisible(false);
           listSneaker.getItems().clear();
           List<Sneaker> sneakers = sneakerFacade.findAll();
           for (int i = 0; i < sneakers.size(); i++) {
               listSneaker.getItems().addAll(sneakers.get(i));
           }
           listUser.getItems().clear();
           List<Buyer> buyers = buyerFacade.findAll();
           for (int i = 0; i < buyers.size(); i++) {
               listUser.getItems().addAll(buyers.get(i));
           }
       });
           btnAddPurchase.setOnAction((event) -> {
               try {   
                Long sneakerNum = listSneaker.getSelectionModel().getSelectedItem().getId();
                Long buyerNum = listUser.getSelectionModel().getSelectedItem().getId();
                    History history= new History();
                    history.setSneaker(sneakerFacade.find((long) sneakerNum));
                    history.setBuyer(buyerFacade.find((long)buyerNum));
                    Calendar c = new GregorianCalendar();
                    history.setGivenSneaker(c.getTime());
                    if(history.getBuyer().getBuyerMoney()>=history.getSneaker().getSneakerPrice() && history.getSneaker().getSneakerQuantity()!=0){
                    txtFieldInfo.setText("Вы добавили покупку в базу!");
                    history.getBuyer().setBuyerMoney(history.getBuyer().getBuyerMoney()-history.getSneaker().getSneakerPrice());
                    history.getSneaker().setSneakerQuantity(history.getSneaker().getSneakerQuantity()-1);
                    sneakerFacade.edit(history.getSneaker());
                    buyerFacade.edit(history.getBuyer());
                    historyFacade.create(history);
                    btnReload.setVisible(true);
                    }else if(history.getBuyer().getBuyerMoney()<history.getSneaker().getSneakerPrice()){
                        txtFieldInfo.setText("Не хватает денег!");
                    }else if(history.getSneaker().getSneakerQuantity()==0){
                        txtFieldInfo.setText("Этого товара нет в наличии!");
                    }  
               } catch (Exception e) {
                   txtFieldInfo.setText("Выберите покупателя и обувь!");
               }
           });
    }

}
