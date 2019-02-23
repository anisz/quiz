package controller;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import dataAccessLayer.DAO;
import view.Dialog;
/**
 * @author anisz
 */

@Controller
public class AnswerController {
	
	@Autowired
	DAO dao;
	
	@Autowired
	Dialog dialog;
	
	public void getAnswer(String creatureName) {
				
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();

		if (s.equals("p")) {
			dao.saveAnswers("p", creatureName);
		} else if (s.equals("s")) {
			dao.saveAnswers("s", creatureName);
		} else {
			dialog.informAboutIncorrectAnswer();  
			dao.saveAnswers(s, creatureName);
		}
	}	
}
