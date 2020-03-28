package game;
import java.util.ArrayList;

public class Characters {
	ArrayList<Character> characters = new ArrayList<Character>();
	
	
	public Characters() {
		
	}
	
	public void addCharacter(String name, String power, String question) {
		Character newChar = new Character(name, power, question);
		characters.add(newChar);
	}
}
