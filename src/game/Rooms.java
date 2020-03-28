package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Rooms {

	HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
	HashMap<Integer, Integer[]> roomLinks = new HashMap<Integer, Integer[]>();

	public void loadRoomCSV(String roomFilename) {
		File roomFile = new File(roomFilename);
		try {
			Scanner roomFileScanner = new Scanner(roomFile);
			// Read header
			String input = roomFileScanner.nextLine();
			while(roomFileScanner.hasNextLine()) {
				input = roomFileScanner.nextLine();
				processFileLine(input);
			}
			processRoomLinks();
			roomFileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file <" + roomFilename + ">");
			e.printStackTrace();
		}
	}

	public void processFileLine(String line) {
		String[] data = line.split(",");
		if (data.length < 10) { return;}

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

	public void processRoomLinks() {
		for(Integer id: roomLinks.keySet()) {
			int n = roomLinks.get(id)[0];
			int e = roomLinks.get(id)[1];
			int s = roomLinks.get(id)[2];
			int w = roomLinks.get(id)[3];
			
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
	
	public Room getRoomAtID(int id) {
		return rooms.get(id);
	}
	
	public static void main(String[] args) {
		String roomFilename = "rooms.csv";
		Rooms rooms = new Rooms();
		rooms.loadRoomCSV(roomFilename);
		
		Room currentRoom = rooms.getRoomAtID(1);
		System.out.println("Room: "+ currentRoom.getName());
		
		Room nextRoom = currentRoom.getRoomAtDirection("north");
		if(nextRoom != null) {
			currentRoom = nextRoom;
		}
		System.out.println("Room: "+ currentRoom.getName());		
	}

}
