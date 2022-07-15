package eventhandlers;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Good;
import utils.BucketSummaryGetter;

import java.util.List;

public class DeleteButtonEventHandler implements EventHandler<MouseEvent> {

    private ListView<Good> listOfGoods;
    private List<Good> bucket;
    private Button bucketButton;

    public DeleteButtonEventHandler(ListView<Good> listOfGoods, List<Good> bucket, Button bucketButton) {
        this.listOfGoods = listOfGoods;
        this.bucket = bucket;
        this.bucketButton = bucketButton;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        bucket.remove(listOfGoods.getSelectionModel().getSelectedItem());
        bucketButton.setText(BucketSummaryGetter.getSummary(bucket).toString());
        System.out.println("element removed" + listOfGoods.getSelectionModel().getSelectedItem());
        System.out.println(bucket);
    }
}
