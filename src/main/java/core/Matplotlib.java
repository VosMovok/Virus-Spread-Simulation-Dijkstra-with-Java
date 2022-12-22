package core;

import com.github.sh0nk.matplotlib4j.*;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


public class Matplotlib {
    public static void dataToPlot(TreeMap<Double, Integer> data) throws PythonExecutionException, IOException {
        List<Double> keys = new ArrayList<>(data.keySet());
        List<Integer> values = new ArrayList<>();
        for (Double key : keys) {
            values.add(data.get(key));
        }
        Plot plt = Plot.create();
        plt.figure("Graph");
        plt.plot()
                .add(keys, values)
                .label("Days to Infected")
                .color("r");
        plt.xlabel("Days");
        plt.ylabel("Infected");
        plt.title("Line Graph of Days Infected");
        plt.legend();
        plt.show();
    }
}
