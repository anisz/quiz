package dataAccessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DAOJdbcTemplate implements DAO {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate; 
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final static String SQL_INSERT = "INSERT INTO quiz (name, origin, drawSet, asked, answer) "
			+ " VALUES (:name, :origin, :drawSet, :asked, :answer)";
	
	private final static String SQL_DELETE = "DELETE FROM quiz";	
	
	private final static String SQL_SELECT_QUIZ_QUESTIONS = "SELECT name FROM quiz WHERE drawSet = true ORDER BY RAND() LIMIT 15";
		
	private final static String SQL_UPDATE_ANSWER = "UPDATE quiz SET answer = :answer WHERE name = :name";
	
	private final static String SQL_UPDATE_ASKED = "UPDATE quiz SET asked = true WHERE name = ?";	
	
	private final static String SQL_COUNT_SPICIES_NAMES = "SELECT COUNT(name) FROM quiz WHERE origin = ?";
	
	private static final String SQL_PREPARE_LARGER_DRAW_SET = "SELECT name FROM QUIZ WHERE origin = ? LIMIT ?";
	
	private final static String SQL_UPDATE_DRAW_SET_BY_ORIGIN = "UPDATE quiz SET drawSet = true WHERE origin = ?";
	
	private static final String SQL_UPDATE_DRAW_SET_BY_NAME = "UPDATE quiz SET drawSet = true WHERE name = :name";
	
	private static final String SQL_SELECT_QUIZ_ANSWERS = "SELECT name, origin, answer FROM QUIZ WHERE asked = TRUE";
		
	private static final String SQL_SELECT_DRAW_SET = "SELECT name FROM quiz WHERE drawSet = true";

	public Map<String, String> mapObjectsForQuery(String creatureName, String spicies) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", creatureName);
		paramMap.put("origin", spicies);
		paramMap.put("drawSet", "false");
		paramMap.put("asked", "false");
		paramMap.put("answer", "null");
		return paramMap; 
	}
	
	public void saveObjects(String name, String spicies) {
		Map<String, String> paramMap = mapObjectsForQuery(name, spicies);
		namedParameterJdbcTemplate.update(SQL_INSERT, paramMap); 
	}
	
	public List<Map<String, Object>> queryForQuizQuestionsList(){
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_QUIZ_QUESTIONS);
		return rows;
	}
		
	public List<Map<String, Object>> queryForAnswerList(){
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_QUIZ_ANSWERS);
		return rows;
	}
	
	public void markQuizCreaturesInDatabase(List<String> quizQuestions) {
		for (String creatureName : quizQuestions) {
			jdbcTemplate.update(SQL_UPDATE_ASKED, creatureName);
		}
	}
	
	public void saveAnswers(String answer, String creatureName){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("answer", answer).addValue("name", creatureName);
		namedParameterJdbcTemplate.update(SQL_UPDATE_ANSWER, namedParameters);
	}

	@Override
	public int countCreatures(String species) {
		int spiciesNumber = jdbcTemplate.queryForObject(SQL_COUNT_SPICIES_NAMES,  new Object[] {species}, Integer.class);
		return spiciesNumber;
	}

	@Override
	public void updateSmallerDrawSet(String origin) {	
		jdbcTemplate.update(SQL_UPDATE_DRAW_SET_BY_ORIGIN, origin);
	}
	
	public void updateLargerDrawSet(String name){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("name", name);
		namedParameterJdbcTemplate.update(SQL_UPDATE_DRAW_SET_BY_NAME, namedParameters);
	}
		
	public List<Map<String, Object>> drawFromLargerDrawSet(int minCreatureNumber, String name){
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_PREPARE_LARGER_DRAW_SET, name, minCreatureNumber);
		return rows;
	}
	
	public void clearDatabase() {
		jdbcTemplate.update(SQL_DELETE);
	}
	
	public List<String> getDrawSet() {
		List<String> drawSetList = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_SELECT_DRAW_SET);
		for (Map<String, Object> row : rows) {
			String name = (String) row.get("name");
			drawSetList.add(name);
		}
		return drawSetList;
	}
}
