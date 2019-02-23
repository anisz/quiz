package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import configuration.SpringJdbcConfig;
import controller.QuestionController;
import model.CreaturesData;
import model.Quiz;
import model.Summary;
import view.Dialog;

/**
 * @author anisz
 */

@Component
public class Task {

	@Autowired
	private CreaturesData creaturesData;

	@Autowired
	private QuestionController questions;

	@Autowired
	private Summary summary;

	@Autowired
	private Dialog dialog;
	
	@Autowired
	private Quiz quiz;

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
		Task main = context.getBean(Task.class);
		main.start(args);
		((AbstractApplicationContext) context).close();
	}

	private void start(String[] args) {
		try {
			creaturesData.tackleQuizDataDownloads();
			quiz.prepareQuiz();
			questions.ask();
			summary.provideSummary();
		} catch (Exception e) {
			dialog.printExceptionMessage();
			e.printStackTrace();
		}
	}
}
