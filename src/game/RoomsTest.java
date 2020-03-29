package game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoomsTest {
	
	Rooms rooms = new Rooms("roomsTest.csv");
	
	@Test 
	void testProcessFileLine01() {
		rooms.processFileLine("5,Room5,5,5,Room 5 Short,Room 5 Long,1,2,3,0");
		assertEquals(rooms.getRoomAtID(5).getName(), "Room5");
		assertEquals(rooms.getRoomAtID(5).getShortDesc(), "Room 5 Short");
		assertEquals(rooms.getRoomAtID(5).getLongDesc(), "Room 5 Long");
		rooms.processRoomLinks();
		assertEquals(rooms.getRoomAtID(5).getRoomAtDirection("north").getId(), 1);
		assertEquals(rooms.getRoomAtID(5).getRoomAtDirection("east").getId(), 2);
		assertEquals(rooms.getRoomAtID(5).getRoomAtDirection("south").getId(), 3);
		assertEquals(rooms.getRoomAtID(5).getRoomAtDirection("west"), null);
	}
	
}
