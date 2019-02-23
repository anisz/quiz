package api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UriMapping {
	
	private static final String URI_POKEMONS = "https://pokeapi.co/api/v2/pokemon/";
	private static final String URI_STARWARS1 = "https://swapi.co/api/species/?page=1&format=json";
	private static final String POKEMON = "Pokemon";
	private static final String STAR_WARRIOR = "StarWarrior";
	
	Map<String, String> creaturesUriMap = new HashMap<String, String>();
	
	public Map<String, String> mapCreaturesUri() {

		creaturesUriMap.put(URI_STARWARS1, STAR_WARRIOR);
		creaturesUriMap.put(URI_POKEMONS, POKEMON);
		return creaturesUriMap;
	} 
}
