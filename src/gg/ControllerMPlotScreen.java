package gg;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerMPlotScreen {
    @FXML
    private Button backButton;
    @FXML
    private TextField inputField;
    @FXML
    private  Button nextButton;
    @FXML
    private Text resultText;
    @FXML
    private LineChart plot;


    /**
     * Funkcja obsługująca przycisk powrotu do ekranu początkowego
     */
    @FXML
    void actionBack() {
        Main.scenesManager.setScene("Start");
    }

    /**
     * Funkcja obsługująca przycisk wyświetlający wykres.
     */
    @FXML
    void actionNext() {

        String[] res = inputField.getCharacters().toString().split(", *");
        System.out.println(res[0]);
        float[] values = Ow_api.INSTANCE.getDataForPlot(res[0], res[1]);
        if (values == null) {
            resultText.setText("Error");
        }
        else {
            resultText.setText("");
            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("argument");
            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("value");

            XYChart.Series dataSeries1 = new XYChart.Series();
            dataSeries1.setName("series");
            for (int i = 0; i < values.length; ++i)
                dataSeries1.getData().add(new XYChart.Data(Integer.toString(i), values[i]));

            plot.getData().add(dataSeries1);
        }

    }

}
