package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import api.UriMapping;
import dataAccessLayer.DAO;
import view.Dialog;

/**
 * @author anisz
 */

@Service
public class CreaturesData {
	
	private JsonNode rootNode;
	
	private JsonNode jsonResultNode;
	
	private JsonNode jsonNextNode;
	
	private String tempURL;
	
	private String tempSpicies;
	
	List<String> creatureNameList = new ArrayList<>();
	
	List<String> upperCaseCreatures = new ArrayList<>();

	@Autowired
	private DAO dao;

	@Autowired
	private UriMapping uriMapping;
	
	@Autowired
	private Dialog dialog; 
	
	public void tackleQuizDataDownloads() throws Exception { 
		dialog.downloadingData();
		dao.clearDatabase();
		getDataFromAPIs();
	}
	
	public void getDataFromAPIs() throws Exception {
		Map<String, String> uriMap = uriMapping.mapCreaturesUri();
		for (Map.Entry<String, String> entry : uriMap.entrySet()) {
			this.getDataFromApi(entry.getKey(), entry.getValue());
		}
	}

	public void getDataFromApi(String url, String spicies) throws Exception {
		setTempData(url, spicies);
		getWholeApiResponse();
		downloadCreatureNames();
		performNextStep();
	}
	
	public void performNextStep() throws Exception {
		jsonNextNode = rootNode.path("next");
		tempURL = jsonNextNode.asText();
		if (!jsonNextNode.isNull()) {
			getDataFromApi(tempURL, tempSpicies);
		}
		else {
			performFinalSteps();
		}
	}
	
	public void performFinalSteps() {
		changeCreatureNamesListToUpperCase();
		saveCreatures(upperCaseCreatures, tempSpicies);
		clearLists();	
	}

	public void downloadCreatureNames() throws Exception {
		getJsonResultNode();
		createCreatureNameList();
	}
	
	public void getJsonResultNode() throws Exception {
		jsonResultNode = rootNode.path("results");
	} 
	
	public void getWholeApiResponse() throws Exception {
		System.setProperty("http.agent", "Mozilla/5.0");
		rootNode = new ObjectMapper().readTree(new URL(tempURL));
	}
		
	public void createCreatureNameList() {
		jsonResultNode.forEach(result ->creatureNameList.add(result.path("name").asText()));
	}
		
	public void changeCreatureNamesListToUpperCase() {
		creatureNameList.forEach(creatureName -> upperCaseCreatures.add(creatureName.toUpperCase()));
	}
	
	public void saveCreatures(List<String> upperCaseCreatures, String species) {
		upperCaseCreatures.forEach(upperCaseCreatureName -> dao.saveObjects(upperCaseCreatureName, species));
	}
	
	public void setTempData(String tempURL, String tempSpicies) {
		this.tempURL = tempURL;
		this.tempSpicies = tempSpicies;
	}
		
	public void clearLists() {
		creatureNameList.clear();			
		upperCaseCreatures.clear();
	}	
}
