package model;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dataAccessLayer.DAO;
import view.Dialog;

/**
 * @author anisz
 */

@Service
public class Summary {

	@Autowired
	private DAO dao;
	
	@Autowired
	private Dialog dialog;

	private static int correctAnswers;

	public void provideSummary() {

		dialog.printSummaryIntro();
		provideDetailedSummary();
		dialog.printNumberOfCorrectAnswers(correctAnswers);
		commentUserScore(); 
	}
	
	public void provideDetailedSummary() {
		List<Map<String, Object>> rows = dao.queryForAnswerList();
		for (Map<String, Object> row : rows) {
			String creatureName = (String) row.get("name");
			String creatureOrigin = (String) row.get("origin");
			String answer = (String) row.get("answer");

			remindUserAnswers(answer, creatureName);
			summariseUserAnswers(answer, creatureOrigin);
		}
	}

	public void remindUserAnswers(String answer, String creatureName) {
		if (answer.equals("p")) {
			dialog.sumUpPokemonAnswer(creatureName);
		} else if (answer.equals("s")) {
			dialog.sumUpStarWarriorAnswer(creatureName);
		} else {
			dialog.sumUpMaleformedAnswer(creatureName, answer);
		}
	}

	public void summariseUserAnswers(String answer, String creatureOrigin) {
		if ((answer.equals("p")) && (creatureOrigin.equals("Pokemon"))) {
			dialog.summarizeCorrectPokemonAnswer();
			correctAnswers++;
		} else if ((answer.equals("s")) && (creatureOrigin.equals("StarWarrior"))) {
			dialog.summarizeCorrectStarWarriorAnswer();
			correctAnswers++;
		} else {
			dialog.summarizeIncorrectAnswer();
		}
		dialog.makeSomeSpace();
	}

	public void commentUserScore() {
		if (correctAnswers <= 5) {
			dialog.commentLowScore();
		} else if ((correctAnswers > 5) && (correctAnswers < 11)) {
			dialog.commentMediumScore();
		} else {
			dialog.commentHighScore();
		}
	}

	public static int getCorrectAnswers() {
		return correctAnswers;
	}
}
