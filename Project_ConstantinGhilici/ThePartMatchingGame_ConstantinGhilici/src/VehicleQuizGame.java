import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * The VehicleQuizGame class represents a simple quiz game about vehicles using
 * a graphical user interface (GUI).
 * It includes functionality to display a main menu, conduct quizzes for cars
 * and motorcycles, and show quiz results.
 * 
 * @author Constantin Ghilici
 * 
 * Reason: to teach users about auto parts
 * 
 * 12/10/2023
 */
public class VehicleQuizGame
{
	private static QuizQuestion question;
	// Single-dimensional array for explanations
	private static String[] explanations = new String[] {
			"The catalytic converter reduces emissions by converting harmful gases into less harmful substances.",
			"The transmission regulates the power generated by the engine and controls the speed of the vehicle.",
			"The clutch engages and disengages the transmission from the engine during gear changes.",
			"The flywheel, connected to the crankshaft, helps smooth out fluctuations in engine speed.",
			"The crankshaft is a mechanical component that transmits torque and rotation from the vehicle's transmission." };; // Single-dimensional
																																// array
																																// for
	// explanations
	private static int currentQuestionIndex;
	private static int correctCarAnswersCount;
	private static int correctMotorcycleAnswersCount;

	/**
	 * The main method of the application, launching the GUI creation on the
	 * Event Dispatch Thread.
	 *
	 * @param args Command-line arguments (not used in this application).
	 */
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	/**
	 * Creates and shows the main menu GUI frame.
	 */
	private static void createAndShowGUI()
	{
		showMainMenu();
	}

	/**
	 * Displays the main menu GUI frame, allowing the user to choose between the
	 * car and motorcycle quizzes.
	 */
	private static void showMainMenu()
	{
		JFrame mainMenuFrame = new JFrame("Vehicle Quiz Game");
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setSize(400, 400);
		mainMenuFrame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton carButton = new JButton("Car Quiz");
		carButton.addActionListener(e -> {
			currentQuestionIndex = 0;
			correctCarAnswersCount = 0;
			mainMenuFrame.dispose();
			question = new CarQuizQuestion();
			showQuestionFrame(question.getQuestions());
		});

		JButton motorcycleButton = new JButton("Motorcycle Quiz");
		motorcycleButton.addActionListener(e -> {
			currentQuestionIndex = 0;
			correctMotorcycleAnswersCount = 0;
			mainMenuFrame.dispose();
			question = new MotorcycleQuizQuestion();
			showQuestionFrame(question.getQuestions());
		});

		panel.add(new JLabel("Welcome to Vehicle Quiz Game!"));
		panel.add(carButton);
		panel.add(motorcycleButton);

		mainMenuFrame.add(panel, BorderLayout.CENTER);
		mainMenuFrame.setLocationRelativeTo(null);
		mainMenuFrame.setVisible(true);
	}

	/**
	 * Displays the quiz question GUI frame for the given set of questions.
	 *
	 * @param questions The array of questions to be displayed.
	 */
	private static void showQuestionFrame(String[][] questions)
	{
		if (currentQuestionIndex >= questions.length)
		{
			showResult(questions);
			return;
		}

		JFrame questionFrame = new JFrame("Vehicle Quiz Game");
		questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questionFrame.setSize(400, 300);
		questionFrame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		String[] currentQuestion = questions[currentQuestionIndex];

		// Display the question text
		panel.add(new JLabel(currentQuestion[0]));

		// Display radio buttons for each option
		ButtonGroup buttonGroup = new ButtonGroup();
		for (int i = 1; i < currentQuestion.length; i++)
		{
			JRadioButton radioButton = new JRadioButton(currentQuestion[i]);
			buttonGroup.add(radioButton);
			panel.add(radioButton);
		}

		// Add spacing between components
		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Add a button to submit the answer
		JButton submitButton = new JButton("Submit Answer");
		submitButton.addActionListener(e -> {
			try
			{
				String userAnswer = getSelectedRadioButton(buttonGroup);

				// Check if the answer is correct
				if (userAnswer != null && userAnswer.equals(currentQuestion[1]))
				{
					JOptionPane.showMessageDialog(questionFrame,
							"Correct! Explanation: "
									+ explanations[currentQuestionIndex]);
					if (questions == question.getQuestions())
					{
						correctCarAnswersCount++; // Increment correct car
													// answers count
					}
					else
					{
						correctMotorcycleAnswersCount++; // Increment correct
															// motorcycle
															// answers count
					}
				}
				else
				{
					throw new InvalidAnswerException("Incorrect answer");
				}

				currentQuestionIndex++;
				questionFrame.dispose();
				showQuestionFrame(questions); // Display the next question
			}
			catch (InvalidAnswerException ex)
			{
				JOptionPane.showMessageDialog(questionFrame,
						"Invalid Answer: " + ex.getMessage());
			}
		});
		panel.add(submitButton);

		questionFrame.add(panel, BorderLayout.CENTER);
		questionFrame.setLocationRelativeTo(null);
		questionFrame.setVisible(true);
	}

	/**
	 * Retrieves the text of the selected radio button from a ButtonGroup.
	 *
	 * @param buttonGroup The ButtonGroup containing the radio buttons.
	 * @return The text of the selected radio button, or null if none is
	 *         selected.
	 */
	private static String getSelectedRadioButton(ButtonGroup buttonGroup)
	{
		for (Enumeration<AbstractButton> buttons = buttonGroup
				.getElements(); buttons.hasMoreElements();)
		{
			AbstractButton button = buttons.nextElement();
			if (button.isSelected())
			{
				return button.getText();
			}
		}
		return null;
	}

	/**
	 * Displays the quiz result GUI frame, showing the number of correct answers
	 * and a congratulatory message if applicable.
	 *
	 * @param questions The array of questions used in the quiz.
	 */
	private static void showResult(String[][] questions)
	{
		JFrame resultFrame = new JFrame("Quiz Result");
		resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultFrame.setSize(300, 300);
		resultFrame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		panel.add(new JLabel("Quiz Completed!"));
		panel.add(new JLabel(
				"Correct Answers: " + getCorrectAnswersCount(questions)));

		if (getCorrectAnswersCount(questions) > 4)
		{
			panel.add(new JLabel(
					"Congratulations! You are a certified mechanic!"));
		}

		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(e -> {
			resultFrame.dispose();
			showMainMenu(); // Go back to the main menu
		});

		panel.add(closeButton);
		// Add the result frame to the center
		resultFrame.add(panel, BorderLayout.CENTER);

		// Set the location of the result frame
		resultFrame.setLocationRelativeTo(null);

		// Make the result frame visible
		resultFrame.setVisible(true);
	}

	/**
	 * Retrieves the total count of correct answers for either car or motorcycle
	 * quizzes.
	 *
	 * @param questions The array of questions used in the quiz.
	 * @return The total count of correct answers.
	 */
	private static int getCorrectAnswersCount(String[][] questions)
	{
		return (questions == question.getQuestions()) ? correctCarAnswersCount
				: correctMotorcycleAnswersCount;
	}
}
