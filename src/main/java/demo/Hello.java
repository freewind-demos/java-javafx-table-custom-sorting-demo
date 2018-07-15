package demo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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
                setCellValueFactory(new PropertyValueFactory<>("name"));
                setComparator((o1, o2) -> {
                    int n1 = Integer.valueOf(StringUtils.substringAfter(o1, "-"));
                    int n2 = Integer.valueOf(StringUtils.substringAfter(o2, "-"));
                    return n1 - n2;
                });
            }});
            getColumns().add(new TableColumn<Person, Integer>("Number") {{
                setCellValueFactory(new PropertyValueFactory<>("number"));
            }});
            getColumns().add(new TableColumn<Person, List<String>>("Fruits") {{
                setCellValueFactory(new PropertyValueFactory<>("fruits"));
                setSortable(false);
            }});
            setItems(data);
            setEditable(true);
        }};
    }
}
