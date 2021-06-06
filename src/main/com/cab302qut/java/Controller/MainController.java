package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.util.AssetPriceHistoryObj;
import com.cab302qut.java.util.AssetQuantityObj;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    ChoiceBox<String> pendingTrades;
    @FXML
    private TableView<AssetQuantityObj> table_assets;
    @FXML
    private TableColumn<AssetQuantityObj, String> col_assetType;
    @FXML
    private TableColumn<AssetQuantityObj,Integer> col_quantity;

    private ObservableList<AssetQuantityObj> existingOrgAssets;
    @FXML
    private Text orgLabel;
    @FXML
    Label currentCredits;

    @FXML
    private Text userLabel;

    @FXML
    private Button logoutButton;
    @FXML
    private Button tradeButton;

    private String chosenOfferTradeID;

    Asset[] assetList = new Asset[5];

    private String previousScene;

    ObservableList<String> pendingTradesList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // organisationLabel = new TextFlow(new Text("Testing"));
        this.col_assetType.setCellValueFactory(new PropertyValueFactory<AssetQuantityObj,String>("assetType"));
        this.col_quantity.setCellValueFactory(new PropertyValueFactory<AssetQuantityObj,Integer>("quantity"));

        table_assets.setItems(existingOrgAssets);
        initAssetChoiceBox();

    }

    public void initAssetChoiceBox() {
        try {
            pendingTrades.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    chosenOfferTradeID = StaticVariables.pendingTradesData.get(newValue.intValue()).get(0);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void RemoveOffer() {
        if (chosenOfferTradeID != null) {
            ArrayList<String> tradeIDPlusOrg = new ArrayList<>();
            tradeIDPlusOrg.add(chosenOfferTradeID);
            tradeIDPlusOrg.add(StaticVariables.user.getOrganisation().getName());
            Message msg = new Message("RemoveOffer",tradeIDPlusOrg);
            StaticVariables.pendingTradesData = null;
            CAB302Assignment.tradeClient.sendMessage(msg);
            while (StaticVariables.pendingTradesData == null) {
                System.out.println("Waiting for delete offer update");
            }
            pendingTradesList.clear();//FXCollections.observableArrayList();
            if (StaticVariables.pendingTradesData.size() > 0) {
                String rowEntry = "";
                for(ArrayList<String> row : StaticVariables.pendingTradesData) {
                    rowEntry = "ID*" + row.get(0) + "*ASSET*" + row.get(1) + "*QTY*" + row.get(2) + "*PRICE_P/UNIT*"
                            + row.get(3) + "*ORDERTYPE*"+ row.get(4) + "*DATE*" + row.get(5);
                    pendingTradesList.add(rowEntry);
                }
            }
        }
    }

    public void setPendingTrades() {
        try {
            Message msg = new Message("GetOrgPendingTrades",StaticVariables.user.getOrganisation().getName());
            CAB302Assignment.tradeClient.sendMessage(msg);

            while (StaticVariables.pendingTradesData == null) {
                System.out.println("Waiting for orgs assets");
            }

            pendingTradesList = FXCollections.observableArrayList();
            String rowEntry = "";
            for(ArrayList<String> row : StaticVariables.pendingTradesData) {
                rowEntry = "ID*" + row.get(0) + "*ASSET*" + row.get(1) + "*QTY*" + row.get(2) + "*PRICE_P/UNIT*"
                        + row.get(3) + "*ORDERTYPE*"+ row.get(4) + "*DATE*" + row.get(5);
                pendingTradesList.add(rowEntry);
            }
            pendingTrades.setItems(FXCollections.observableArrayList(pendingTradesList));

//            existingOrgAssets = FXCollections.observableArrayList();
//            for (ArrayList<String> row: StaticVariables.orgsAssets) {
//                existingOrgAssets.add(row.get(0)); // get orgName
//            }
//            orgAssets.setItems(FXCollections.observableArrayList(existingOrgAssets));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void SetCurrentAssets(String previousScene) {
        this.previousScene = previousScene;
        GetCurrentAssetsData();
        setPendingTrades();
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //
    public void GetCurrentAssetsData(){
        try {
            //StaticVariables.selectedAssetPriceHistory = null; // reset for the current asset
            Message msg = new Message("GetOrgsAsset",orgLabel.getText());
            CAB302Assignment.tradeClient.sendMessage(msg);
            while (StaticVariables.orgsAssets == null || StaticVariables.orgsAssets.isEmpty()) {
                System.out.println("Waiting for current assets");
            }
            existingOrgAssets = FXCollections.observableArrayList();
            ArrayList<ArrayList<String>> currentAssets = StaticVariables.orgsAssets;
            ArrayList<AssetQuantityObj> orgsAssets = new ArrayList<>();
            for (ArrayList<String> asset : currentAssets) {
                orgsAssets.add(new AssetQuantityObj(asset.get(0),Integer.parseInt(asset.get(1))));
            }
            for (AssetQuantityObj assetDetails: orgsAssets) {
                existingOrgAssets.add(assetDetails);
            }
            table_assets.setItems(existingOrgAssets);

            Message newMsg = new Message("GetOrgsList");
            CAB302Assignment.tradeClient.sendMessage(newMsg);
            while (StaticVariables.organisationList == null) {
                System.out.println("Waiting for orgs credits");
            }
            // get the credits of the org selected
            for (ArrayList<String> row: StaticVariables.organisationList) {
                if (row.get(0).equals(orgLabel.getText())) {
                    currentCredits.setText(row.get(1));
                    break;
                }
            }

//            ArrayList<String> updatedOrgCredits = StaticVariables.orgCreditsUpdateMsg;
//
//            // update the organisations list in static variables class with updated org credits
//            for (ArrayList<String> row: StaticVariables.organisationList) {
//                if (row.get(0).equals(updatedOrgCredits.get(0))) {
//                    // update the array list object of the org to reflect new credit value
//                    row.set(1,updatedOrgCredits.get(1));
//                    // show updated credits in the scene
//                    currentCredits.setText(updatedOrgCredits.get(1));
//                    break;
//                }
//            }

            //Refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTrade(ActionEvent actionEvent) throws IOException {
        try {
            Message msg = new Message("CreateTrade");
            CAB302Assignment.tradeClient.sendMessage(msg);
            while(!StaticVariables.assetRefresh){

            }
            for ( int i = 0; i < StaticVariables.assets.length-1;i++) {
                assetList[i] = StaticVariables.assets[i];
            }
//            assetList[0] = new Asset("test1",1);
//            assetList[1] = new Asset("test2",2);
//            assetList[2] = new Asset("test3",3);
//            assetList[3] = new Asset("test4",4);
//            assetList[4] = new Asset("test5",5);

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Trade.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            TradeController tradeController = loader.getController();
            tradeController.setChoiceBox(assetList);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrganisationLabel(String organisationText){
        orgLabel.setText(organisationText);
    }
    public void setUserLabel(String userText){
        userLabel.setText(userText);
    }
}
