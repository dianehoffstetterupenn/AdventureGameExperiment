package game;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * MCIT 591 Final Project
 * @author Team 30
 * 3/28/20
 * 
 * This class models a room in the Adventure game.  
 * id: Unique ID for each room.
 * name: Name of the room.
 * xCor: x coordinate of the room in a 2D grid
 * yCor: y coordinate of the room in a 2D grid
 * shortDesc: Short description of the room
 * longDesc: Long (detailed) description of the room
 * visited: Default false until player enters the room
 * adjacentRooms: maps directions (north, east, south, west) to the unique ID of the room in that direction
 * 		Not all rooms will have adjacent rooms in each direction
 */
public class Room {
	private int id;
	private String name;
	private int xCor;
	private int yCor;
	private String shortDesc;
	private String longDesc;
	private boolean visited = false;
	private HashMap<String, Room> adjacentRooms = new HashMap<String, Room>();
//	private ArrayList<String> treasures = new ArrayList<String>(); // Change to objects
//	private ArrayList<String> characters = new ArrayList<String>();
	
	/**
	 * Constructor
	 * @param id : Unique ID for each room
	 * @param name : Name of the room
	 * @param xCor : x coordinate of the room in a 2D grid
	 * @param yCor : y coordinate of the room in a 2D grid
	 * @param shortDesc : Short description of the room
	 * @param longDesc : Long (detailed) description of the room
	 */
	public Room(int id, String name, int xCor, int yCor, String shortDesc, String longDesc) {
		this.id = id;
		this.name = name;
		this.xCor = xCor;
		this.yCor = yCor;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
	}

	/**
	 * Room name getter
	 * @return name of the room
	 */
	public String getName() {
		return name;
	}

	/**
	 * Room short description getter
	 * @return short description of the room
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * Room long (detailed) description getter
	 * @return long (detailed) description of the room
	 */
	public String getLongDesc() {
		return longDesc;
	}
	
	/**
	 * Room ID getter
	 * @return room id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Method to return if room was visited
	 * @return if room was visited before
	 */
	public boolean wasVisited() {
		return visited;
	}

	/**
	 * Method to set that the room was visited
	 */
	public void setVisited() {
		this.visited = true;
	}

	/**
	 * Method to set up the adjacent rooms.  Note that all rooms must be created
	 * before you can set up the room connections
	 * @param direction : direction out of the room
	 * @param room : room in the direction specified
	 */
	public void setAdjacentRoom(String direction, Room room) {
		adjacentRooms.put(direction, room);
	}
	
	/**
	 * Method to return the room in the direction specified
	 * Note that the room can be null and the caller must handle that case.
	 * @param direction : direction from the room
	 * @return : Room object in that direction, can be null
	 */
	public Room getRoomAtDirection(String direction) {
		Room room = null;
		
		if(adjacentRooms.containsKey(direction) && adjacentRooms.get(direction) != null) {
			room = adjacentRooms.get(direction);
		}
		return room;
	}
}
