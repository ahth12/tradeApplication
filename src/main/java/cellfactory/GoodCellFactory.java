package cellfactory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Good;

public class GoodCellFactory implements Callback<ListView<Good>, ListCell<Good>> {
    @Override
    public ListCell<Good> call(ListView<Good> param) {
        return new ListCell<Good>(){
            @Override
            public void updateItem(Good good, boolean empty) {
                super.updateItem(good, empty);
                if (empty || good == null) {
                    setText(null);
                } else {
                    setText(good.getGoodName() + " " + good.getGoodCost());
                }
            }
        };
    }
}
