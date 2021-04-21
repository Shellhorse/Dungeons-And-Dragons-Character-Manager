package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of characters. This is used for saving the
 * list of characters to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "characters")
public class CharacterListWrapper {

	private List<Character> characters;

	@XmlElement(name = "character")
	public List<Character> getPersons() {
		return characters;
	}

	public void setPersons(List<Character> characters) {
		this.characters = characters;
	}
}