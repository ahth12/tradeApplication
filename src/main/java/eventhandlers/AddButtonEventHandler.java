package eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Good;
import utils.BucketSummaryGetter;

import java.util.List;

public class AddButtonEventHandler implements EventHandler<MouseEvent> {

    private List<Good> bucket;
    private ListView<Good> listOfGoods;
    private Button bucketButton;

    public AddButtonEventHandler(List<Good> bucket, ListView<Good> listOfGoods, Button bucketButton) {
        this.bucket = bucket;
        this.listOfGoods = listOfGoods;
        this.bucketButton = bucketButton;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        bucket.add(listOfGoods.getSelectionModel().getSelectedItem());
        bucketButton.setText(BucketSummaryGetter.getSummary(bucket).toString());
        System.out.println("element added : " + listOfGoods.getSelectionModel().getSelectedItem());
        System.out.println(bucket);
    }
}
