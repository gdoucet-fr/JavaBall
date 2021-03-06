package views;

import components.MatchesTableView;
import components.ResultsTableView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Match;
import model.MatchesList;

public class MatchesPane extends VBox {

  public static MatchesPane centerPane = new MatchesPane();
  private TableView<Match> matchesTable;

  private MatchesPane() {
    super();

    // Section title
    Label title = new Label("Matches");
    title.setFont(Font.font("Gill Sans", FontWeight.BOLD, 14));

    matchesTable = MatchesTableView.getInstance();

    // Import results and display button
    Button importResults = new Button("Import results");
    HBox actionButtons = new HBox();
    actionButtons.getChildren().addAll(importResults);
    actionButtons.setSpacing(5);
    actionButtons.setAlignment(Pos.TOP_CENTER);

    importResults.addEventHandler(MouseEvent.MOUSE_RELEASED,
        new EventHandler<MouseEvent>() {
          public void handle(MouseEvent e) {
            // Update model
            MatchesList.readMatchesScore();
            MatchesList.processResults();

            // Update View
            EditionPane.updateChoices();
            MatchesTableView.getInstance().updateTable();
            ResultsTableView.getInstance().updateTable();
            EditionPane.disableWithdrawal(true);
            EditionPane.disableScoreEdition(false);
            importResults.setDisable(true);
          }
        });

    this.getChildren().addAll(title, matchesTable, actionButtons);
    this.setPadding(new Insets(10));
    this.setSpacing(5);
    this.setAlignment(Pos.TOP_CENTER);
  }

  public static MatchesPane getInstance() {
    return centerPane;
  }
}
