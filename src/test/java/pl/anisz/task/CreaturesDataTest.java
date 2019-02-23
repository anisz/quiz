package pl.anisz.task;

import static org.junit.Assert.*;

import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import model.CreaturesData;

public class CreaturesDataTest {

	CreaturesData creaturesData = new CreaturesData();

	@Test
	public void testGetWholeApiResponseNotNull() {
		try {
			assertNotNull(creaturesData.getWholeApiResponse("https://swapi.co/api/species/?page=1&format=json"));
			assertNotNull(creaturesData.getWholeApiResponse("https://pokeapi.co/api/v2/pokemon/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetWholeApiResponseNotContainingBrackets() {
		String openingBracket = "<";
		String closingBracket = ">";
		try {
			JsonNode response1 = creaturesData.getWholeApiResponse("https://swapi.co/api/species/?page=1&format=json");
			JsonNode response2 = creaturesData.getWholeApiResponse("https://pokeapi.co/api/v2/pokemon/");
			String stringResponse1 = response1.toString();
			String stringResponse2 = response2.toString();
			assertFalse(stringResponse1.contains(openingBracket));
			assertFalse(stringResponse1.contains(closingBracket));
			assertFalse(stringResponse2.contains(openingBracket));
			assertFalse(stringResponse2.contains(closingBracket));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}