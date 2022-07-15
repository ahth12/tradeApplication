package utils;

import model.Good;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BucketSummaryGetter {
    public static Double getSummary(List<Good> goodList){
        return goodList.stream().mapToDouble(Good::getGoodCost).sum();
    }
}
