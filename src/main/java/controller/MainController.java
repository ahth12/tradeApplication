package controller;

import eventhandlers.AddButtonEventHandler;
import eventhandlers.DeleteButtonEventHandler;
import eventhandlers.PayButtonEventHandler;
import cellfactory.GoodCellFactory;
import dao.GoodDao;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Good;
import utils.BucketSummaryGetter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button bucketButton;

    @FXML
    private Button payButton;

    @FXML
    private Button payButtonForPopup = new Button("Pay");

    @FXML
    private Button deleteButtonForPopup = new Button("Delete");

    @FXML
    private ListView<Good> listOfGoods;

    @FXML
    private  TextField searchBar = new TextField();

    @FXML
    private Scene primaryScene;

    @FXML
    private Text text = new Text("In the bucket:");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Good> bucket = new ArrayList<>();
        FilteredList<Good> filteredList = null;
        GoodCellFactory goodCellFactory = new GoodCellFactory();
        PayButtonEventHandler payButtonEventHandler = new PayButtonEventHandler(bucket, bucketButton);
        DeleteButtonEventHandler deleteButtonEventHandler = new DeleteButtonEventHandler(listOfGoods, bucket, bucketButton);
        payButton.setOnMouseClicked(payButtonEventHandler);
        payButtonForPopup.setOnMouseClicked(payButtonEventHandler);
        deleteButton.setOnMouseClicked(deleteButtonEventHandler);
        deleteButtonForPopup.setOnMouseClicked(deleteButtonEventHandler);
        addButton.setOnMouseClicked(new AddButtonEventHandler(bucket, listOfGoods, bucketButton));
        listOfGoods.setCellFactory(goodCellFactory);

        try {
            filteredList = new FilteredList(GoodDao.searchGoods(), p -> true);
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        listOfGoods.setItems(filteredList);
        bucketButton.setText(BucketSummaryGetter.getSummary(bucket).toString());
        FilteredList<Good> finalFilteredList = filteredList;

        searchBar.textProperty().addListener(((observable, oldValue, newValue) -> finalFilteredList
                .setPredicate(data -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseSearch = newValue.toLowerCase();
                    return data.getGoodName().toLowerCase().contains(lowerCaseSearch);
                })));

        bucketButton.setOnAction(
                event -> {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(primaryScene.getWindow());
                    VBox dialogVbox = new VBox(20);
                    ListView<Good> popupListview = new ListView<>();
                    popupListview.setCellFactory(goodCellFactory);
                    popupListview.setEditable(true);
                    bucket.forEach(e-> popupListview.getItems().add(e));
                    dialogVbox.getChildren().add(popupListview);
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialogVbox.getChildren().add(payButtonForPopup);
                    dialogVbox.getChildren().add(deleteButtonForPopup);
                    dialog.setScene(dialogScene);
                    dialog.setTitle("Bucket");
                    dialog.show();
                });

    }
}
