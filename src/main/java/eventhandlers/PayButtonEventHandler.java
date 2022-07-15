package eventhandlers;

import dao.CheckDao;
import dao.ChecklineDao;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.Good;
import utils.BucketSummaryGetter;

import java.sql.SQLException;
import java.util.List;

public class PayButtonEventHandler implements EventHandler<MouseEvent> {

    private List<Good> bucket;
    private Button bucketButton;

    public PayButtonEventHandler(List<Good> goodList, Button bucketButton) {
        this.bucket = goodList;
        this.bucketButton = bucketButton;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        try {
            CheckDao.addCheck(BucketSummaryGetter.getSummary(bucket));
            int lastCheckId = CheckDao.getLastCheckId();
            for (Good good : bucket) {
                ChecklineDao.addCheckLine(lastCheckId, good.getGoodId(), good.getGoodCost(), 1);
            }
            bucket.clear();
            bucketButton.setText(BucketSummaryGetter.getSummary(bucket).toString());
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
