package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import model.Quiz;
import view.Dialog;

/**
 * @author anisz
 */

@Controller
public class QuestionController {
		
	@Autowired
	private Dialog dialog;
	
	@Autowired
	private Quiz quiz;

	@Autowired
	private AnswerController answers; 

	public void ask() throws Exception {
		dialog.printInstructions();
		performQuiz();
		dialog.markQuizEnd();
	}
	
	public void performQuiz() {
		List<String> quizQuestions = quiz.getQuizQuestions();
		for (String creatureName : quizQuestions) {
			dialog.askQuestion(quizQuestions.indexOf(creatureName)+1, creatureName);
			answers.getAnswer(creatureName);
		}
	}
}
