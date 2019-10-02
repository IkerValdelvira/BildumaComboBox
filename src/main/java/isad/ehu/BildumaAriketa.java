package isad.ehu;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BildumaAriketa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Bilduma Ariketa");

        ComboBox comboBox = new ComboBox();
        ImageView imageView = new ImageView();

        List<String> bildumak = List.of("Kotxeak", "Paisaiak", "Frutak");
        ObservableList<Bilduma> bildumaList = FXCollections.observableArrayList();
        bildumak.forEach((elem) -> {
            bildumaList.add(new Bilduma(elem));
        });

        comboBox.setItems(bildumaList);

        Map<String, List<Argazkia>> bildumaMap = new HashMap<>();

        bildumaMap.put("Kotxeak", List.of(
                new Argazkia("Seat", "Seat.jpg"),
                new Argazkia("Bugatti", "Bugatti.jpg"),
                new Argazkia("Ferrari", "Ferrari.jpg")
        ));

        bildumaMap.put("Paisaiak", List.of(
                new Argazkia("Hiria", "Hiria.jpg"),
                new Argazkia("Hondartza", "Hondartza.jpg"),
                new Argazkia("Mendia", "Mendia.jpg")
        ));

        bildumaMap.put("Frutak", List.of(
                new Argazkia("Laranja", "Laranja.jpg"),
                new Argazkia("Sagarra", "Sagarra.jpg"),
                new Argazkia("Platanoa", "Platanoa.jpg")
        ));

        comboBox.getSelectionModel().selectFirst();
        ObservableList<Argazkia> argazkiList = FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get("Kotxeak"));
        ListView<Argazkia> listViewOfArgazki = new ListView<>(argazkiList);
        comboBox.setOnAction(e -> {
            listViewOfArgazki.getItems().clear();
            argazkiList.addAll(bildumaMap.get(comboBox.getValue().toString()));
        });

        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;

            String fitx = observable.getValue().getFitx();

            try {
                imageView.setImage(lortuIrudia(fitx));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        VBox vbox = new VBox(comboBox, listViewOfArgazki, imageView);
        Scene scene = new Scene(vbox, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Image lortuIrudia(String location) throws IOException {
        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);

    }

}
