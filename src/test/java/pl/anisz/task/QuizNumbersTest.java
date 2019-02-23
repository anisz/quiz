package pl.anisz.task;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Quiz;

public class QuizNumbersTest {

	Quiz quiz = new Quiz();

	@Test(expected = Exception.class)
	public void testCheckIfEnoughtCreatures() throws Exception {
		quiz.setMinCreatureNumber(6);
		quiz.checkIfEnoughtCreatures();
	}
	
	@Test(expected = Test.None.class)
	public void testCheckIfEnoughtCreatures2() throws Exception {
		quiz.setMinCreatureNumber(9);
		quiz.checkIfEnoughtCreatures();
	}
}