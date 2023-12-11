/**
 * The MotorcycleQuizQuestion class extends the QuizQuestion class and
 * represents a set of quiz questions specific to motorcycles.
 * It initializes the questions array with motorcycle-related questions and
 * options.
 */
public class MotorcycleQuizQuestion extends QuizQuestion
{

	/**
	 * Constructs a new instance of MotorcycleQuizQuestion.
	 * Initializes the questions array with motorcycle-related questions and
	 * options.
	 */
	MotorcycleQuizQuestion()
	{
		super(); // Call the constructor of the superclass (QuizQuestion)
		questions = new String[][] {
				{ "What is the purpose of a motorcycle clutch?",
						"engage and disengage the transmission",
						"increase speed", "control suspension", "filter oil" },
				{ "Which part in a motorcycle is responsible for changing gears?",
						"gear shifter", "clutch lever", "throttle",
						"brake pedal" },
				{ "What is the purpose of a motorcycle's carburetor?",
						"mix air and fuel", "cool the engine",
						"control exhaust", "filter oil" },
				{ "What provides the necessary spark for ignition in a motorcycle?",
						"spark plug", "battery", "alternator",
						"starter motor" },
				{ "What is the purpose of a motorcycle's suspension system?",
						"absorb shocks and provide a smooth ride", "store fuel",
						"filter air", "control temperature" } };
	}
}