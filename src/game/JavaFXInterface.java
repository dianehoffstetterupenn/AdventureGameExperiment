package game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class JavaFXInterface extends Application {

	Room currentRoom;
	Label description = new Label();
	boolean foundTreasure = false;

	public void initializeGame() {

		String roomFilename = "rooms9.csv";
		Rooms rooms = new Rooms(roomFilename);

		this.currentRoom = rooms.getRoomAtID(1);
	}

	@Override
	public void start(Stage primaryStage) {

		initializeGame();

		primaryStage.setTitle("Javenture");

		GridPane grid = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints(100);
		ColumnConstraints column2 = new ColumnConstraints(200);
		ColumnConstraints column3 = new ColumnConstraints(100);
		grid.getColumnConstraints().addAll(column1, column2, column3);
		RowConstraints row1 = new RowConstraints(100);
		RowConstraints row2 = new RowConstraints(100);
		RowConstraints row3 = new RowConstraints(100);
		grid.getRowConstraints().addAll(row1, row2, row3);

		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid);

		Button north = new Button("North");
		north.setOnAction(event -> {
			northButtonPushed();
		});
		Button east = new Button("East");
		east.setOnAction(event -> {
			eastButtonPushed();
		});
		Button south = new Button("South");
		south.setOnAction(event -> {
			southButtonPushed();
		});
		Button west = new Button("West");
		west.setOnAction(event -> {
			westButtonPushed();
		});

		description.setWrapText(true);
		description.setText(currentRoom.getLongDesc());
		grid.add(north, 1, 0);
		grid.add(east, 2, 1);
		grid.add(south, 1, 2);
		grid.add(west, 0, 1);
		grid.add(description, 1, 1);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void northButtonPushed() {
		Room nextRoom = currentRoom.getRoomAtDirection("north");
		processNextRoom(nextRoom);
	}

	public void eastButtonPushed() {
		Room nextRoom = currentRoom.getRoomAtDirection("east");
		processNextRoom(nextRoom);
	}

	public void southButtonPushed() {
		Room nextRoom = currentRoom.getRoomAtDirection("south");
		processNextRoom(nextRoom);
	}

	public void westButtonPushed() {
		Room nextRoom = currentRoom.getRoomAtDirection("west");
		processNextRoom(nextRoom);
	}

	public void processNextRoom(Room nextRoom) {
		System.out.println("Current Room: "+ currentRoom.getName());
		
		if(nextRoom != null) {
			currentRoom = nextRoom;
			if (currentRoom.wasVisited()) {
				description.setText(currentRoom.getShortDesc());
			}
			else {
				description.setText(currentRoom.getLongDesc());
				currentRoom.setVisited();
			}
			checkoutLocation();
		}
		System.out.println("Next Room: "+ currentRoom.getName());		
	}

	public void checkoutLocation() {
		if (currentRoom.getId() == 3) {
			foundTreasure = true;
		}
		if ((currentRoom.getId() == 1) && (foundTreasure == true)) {
			description.setText("You Won!");
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
