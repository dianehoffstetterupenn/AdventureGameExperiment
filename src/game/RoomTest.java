package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest {
	
	Room room1 = new Room(1, "Room1", 0, 0, "Room 1 Short", "Room 1 Long");
	Room room2 = new Room(2, "Room1", 0, 0, "Room 1 Short", "Room 1 Long");
	
	@BeforeEach
	void setUp() throws Exception {
		room1.setAdjacentRoom("east", room2);
		room2.setAdjacentRoom("west", room1);
	}

	@Test
	void testGetName() {
		assertEquals(room1.getName(), "Room1");
	}

	@Test
	void testGetShortDesc() {
		assertEquals(room1.getShortDesc(), "Room 1 Short");
	}

	@Test
	void testGetLongDesc() {
		assertEquals(room1.getLongDesc(), "Room 1 Long");
	}

	@Test
	void testVisited() {
		assertFalse(room1.wasVisited());
		room1.setVisited();
		assertTrue(room1.wasVisited());		
	}

	@Test
	void testGetRoomAtDirection_R1_E2() {
		assertEquals(room1.getRoomAtDirection("east").getId(),2);
	}
	
	@Test
	void testGetRoomAtDirection_R2_W1() {
		assertEquals(room2.getRoomAtDirection("west").getId(),1);
	}

	@Test
	void testGetRoomAtDirection_R1_Snull() {
		assertEquals(room1.getRoomAtDirection("sourth"),null);
	}
}
