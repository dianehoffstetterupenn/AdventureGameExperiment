import java.util.ArrayList;
import java.util.HashMap;

public class Room {
	private int id;
	private String name;
	private int xCor;
	private int yCor;
	private String shortDesc;
	private String longDesc;
	private HashMap<String, Room> adjacentRooms = new HashMap<String, Room>();
	private ArrayList<String> treasures = new ArrayList<String>(); // Change to objects
	private ArrayList<String> characters = new ArrayList<String>();
			
	public Room(int id, String name, int xCor, int yCor, String shortDesc, String longDesc) {
		this.id = id;
		this.name = name;
		this.xCor = xCor;
		this.yCor = yCor;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
	}

	public String getName() {
		return name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}
	
	public int getId() {
		return id;
	}

	public void setAdjacentRoom(String direction, Room room) {
		adjacentRooms.put(direction, room);
	}
	
	public Room getRoomAtDirection(String direction) {
		Room room = null;
		
		if(adjacentRooms.containsKey(direction) && adjacentRooms.get(direction) != null) {
			room = adjacentRooms.get(direction);
		}
		return room;
	}
}
