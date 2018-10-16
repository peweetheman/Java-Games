import java.util.*;
import java.io.*;

public class ScoreManager {
	
	private ArrayList<Score> scores;
	public ScoreManager() {
		scores = new ArrayList<Score>();
	}
	
	ObjectOutputStream out = null;
	ObjectInputStream inputStream = null;

	public ArrayList<Score> getScores() {
		getFile();
		compare();
		return scores;
	}
	public void compare() {
		Compare comp = new Compare();
		Collections.sort(scores, comp);
	}
	public void addScore(String name, int score) {
		getFile();
		scores.add(new Score(name, score));
		update();
	}
	
	public String highscores() {
		String highscoreString = "";
		int max = 5;

		ArrayList<Score> scores;
		scores = getScores();

		int i = 0;
		int x = scores.size();
		if (x > max) {
			x = max;
		}
		while (i < x) {
			highscoreString += (i + 1) + ".\t " + scores.get(i).getname() + "\t\t " + scores.get(i).getScore() + " \n";
			i++;
		}
		return highscoreString;
	}

	public void getFile() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream("scores.txt"));
			scores = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public void update() {
		try {
			out = new ObjectOutputStream(new FileOutputStream("scores.txt"));
			out.writeObject(scores);
		} catch (FileNotFoundException e) {
			System.out.println("NO FILE");
		} catch (IOException e) {
			System.out.println("IO EXCEPTION 1");
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				System.out.println("IO EXCEPTION");
			}
		}}
}
