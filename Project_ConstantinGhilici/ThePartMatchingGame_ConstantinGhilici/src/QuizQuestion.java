
/**
 * The QuizQuestion class serves as a base class for quiz questions in the
 * Vehicle Quiz Game.
 * It provides a structure to hold an array of questions and their corresponding
 * options.
 */
public class QuizQuestion
{

	/** A two-dimensional array to store quiz questions and their options. */
	protected String[][] questions;

	/**
	 * Gets the array of quiz questions and options.
	 *
	 * @return The array containing quiz questions and options.
	 */
	public String[][] getQuestions()
	{
		return questions;
	}
}