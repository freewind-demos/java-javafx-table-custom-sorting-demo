package demo;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.Callback;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static javafx.collections.FXCollections.observableArrayList;


public class Hello extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");
        VBox root = new VBox() {{
            getChildren().add(createTable());
        }};
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    private final ObservableList<Person> data = observableArrayList(
            new Person("AAA-1", 23, observableArrayList("Apple", "Banana")),
            new Person("BBB-3", 111, observableArrayList("Pear")),
            new Person("CCC-2", 34, observableArrayList("Orange"))
    );

    private TableView<Person> createTable() {
        return new TableView<Person>() {{
            getColumns().add(new TableColumn<Person, String>("Name") {{
                setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
                setComparator(new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        int n1 = Integer.valueOf(StringUtils.substringAfter(o1, "-"));
                        int n2 = Integer.valueOf(StringUtils.substringAfter(o2, "-"));
                        return n1 - n2;
                    }
                });
            }});
            getColumns().add(new TableColumn<Person, Integer>("Number") {{
                setCellValueFactory(new PropertyValueFactory<Person, Integer>("number"));
            }});
            getColumns().add(new TableColumn<Person, List<String>>("Fruits") {{
                setCellValueFactory(new PropertyValueFactory<Person, List<String>>("fruits"));
                setSortable(false);
            }});
            setItems(data);
            setEditable(true);
        }};
    }
}
