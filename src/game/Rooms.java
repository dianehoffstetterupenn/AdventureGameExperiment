package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * MCIT 591 Final Project
 * @author Team 30
 * 3/28/20
 * 
 * This class is responsible for setting up the rooms in the game.
 * 		- Loads the room data from a CSV file
 * 		- Parses the CSV data into the room information
 * 		- Puts the rooms into a HashMap 
 * 		- Puts the room links in a separate HashMap to set up the connections once all the rooms are created.
 * 
 *  rooms : HashMap of the rooms using the unique room id as the key and the room object as the value
 *  roomLinks : HashMap of the links between the rooms - links are created after all the rooms are created
 */
public class Rooms {

	HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
	HashMap<Integer, Integer[]> roomLinks = new HashMap<Integer, Integer[]>();

	/**
	 * Rooms Constructor - takes as an argument the file name of the CSV containing the room data
	 * @param roomFilename - CSV containing the room data
	 */
	public Rooms(String roomFilename) {
		loadRoomCSV(roomFilename);
	}

	/**
	 * Method reads in the data from the CSV files and sends to other methods for processing
	 * @param roomFilename
	 */
	public void loadRoomCSV(String roomFilename) {
		File roomFile = new File(roomFilename);
		try {
			Scanner roomFileScanner = new Scanner(roomFile);
			// Read header
			String input = roomFileScanner.nextLine();
			// Read the data
			while(roomFileScanner.hasNextLine()) {
				input = roomFileScanner.nextLine();
				processFileLine(input); // Creates each room based on the CSV data
			}
			processRoomLinks(); // Creates the link between rooms after all the rooms have been created
			roomFileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file <" + roomFilename + ">");
		}
	}

	/**
	 * Method takes each line in the CSV, parses it, and creates a new room object
	 * @param line - line of data from the input CSV file
	 */
	public void processFileLine(String line) {
		String[] data = line.split(",");
		if (data.length < 10) { return;} // There should be 10 fields in each CSV

		// Set default data for the room object
		int id = -1;
		String name = "";
		int xCor = -1;
		int yCor = -1;
		String shortDesc = "";
		String longDesc = "";
		int n = -1;
		int e = -1;
		int s = -1;
		int w = -1;

		// If data parsing fails, results are left at their default
		// Print statements in the catch block are to help find issues with csv file
		try {
			id = Integer.parseInt(data[0]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad id <" + data[0] + ">");
		}
		name = data[1];
		try {
			xCor = Integer.parseInt(data[2]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad xCor <" + data[2] + ">");
		}
		try {
			yCor = Integer.parseInt(data[3]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad yCor <" + data[3] + ">");
		}
		shortDesc = data[4];
		longDesc = data[5];

		try {
			n = Integer.parseInt(data[6]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad n <" + data[6] + ">");
		}	
		try {
			e = Integer.parseInt(data[7]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad e <" + data[7] + ">");
		}	
		try {
			s = Integer.parseInt(data[8]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad s <" + data[8] + ">");
		}	
		try {
			w = Integer.parseInt(data[9]);
		} catch (NumberFormatException e1) {
			System.out.println("Bad w <" + data[9] + ">");
		}	

		Room room = new Room(id, name, xCor, yCor, shortDesc, longDesc);
		rooms.put(id, room);
		Integer[] adjacentRooms = {n, e, s, w};
		roomLinks.put(id, adjacentRooms);
	}

	/**
	 * This method takes all of the room links and sets them once all of the rooms are created
	 */
	public void processRoomLinks() {
		for(Integer id: roomLinks.keySet()) {
			int n = roomLinks.get(id)[0];
			int e = roomLinks.get(id)[1];
			int s = roomLinks.get(id)[2];
			int w = roomLinks.get(id)[3];

			// Only rooms links with values greater than 0 are valid
			// Make sure that the room that is being linked to exists
			
			if((n > 0) && rooms.containsKey(n)) { // check for valid room Link
				rooms.get(id).setAdjacentRoom("north", rooms.get(n));
			}
			if((e > 0) && rooms.containsKey(e)) { // check for valid room Link
				rooms.get(id).setAdjacentRoom("east", rooms.get(e));
			}
			if((s > 0) && rooms.containsKey(s)) { // check for valid room Link
				rooms.get(id).setAdjacentRoom("south", rooms.get(s));
			}
			if((w > 0) && rooms.containsKey(w)) { // check for valid room Link
				rooms.get(id).setAdjacentRoom("west", rooms.get(w));
			}
		}
	}

	/**
	 * Method returns the room object at the id.  
	 * @param id : id for the room desired
	 * @return : Room object at id.  Note that this could be null and need to account fo rit
	 */
	public Room getRoomAtID(int id) {
		if(rooms.containsKey(id)) {
			return rooms.get(id);
		}
		return null;
	}

	// TODO delete this main method when it is no longer needed
	/*
	 * This is a temporary Main method used for quick testing.
	 */
	public static void main(String[] args) {
		String roomFilename = "rooms.csv";
		Rooms rooms = new Rooms(roomFilename);

		Room currentRoom = rooms.getRoomAtID(1);
		System.out.println("Room: "+ currentRoom.getName());

		Room nextRoom = currentRoom.getRoomAtDirection("north");
		if(nextRoom != null) {
			currentRoom = nextRoom;
		}
		System.out.println("Room: "+ currentRoom.getName());		
	}

}
