package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import model.QuizCard;

public class QuizCardPlayerController {
	
	private ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
	private int currentCardIndex;
	private QuizCard currentCard;

	public ArrayList<QuizCard> getCardList() {
		return cardList;
	}
	public QuizCard getCurrentCard() {
		return currentCard;
	}
	public void setCurrentCard(QuizCard currentCard) {
		this.currentCard = currentCard;
	}
	public int getCurrentCardIndex() {
		return currentCardIndex;
	}
	public void setCurrentCardIndex(int currentCardIndex) {
		this.currentCardIndex = currentCardIndex;
	}
	
	public void loadFile(File file) {
		if (!cardList.isEmpty()) {
			cardList.removeAll(cardList);
			currentCard = null;
			currentCardIndex = 0;
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while((line = reader.readLine()) != null) {
				makeCard(line);
			}
			
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void makeCard(String line) {
		String[] result = line.split("/");
		QuizCard card = new QuizCard(result[0], result[1], result[2], result[3]);
		cardList.add(card);
	}
	
	public int showAnswers() {
		return ((int) (Math.random() * 3));
	}
	
	public boolean compareAnswer(String userAnswer) {
		if (userAnswer.equals(currentCard.getAnswer())) {
			return true;
		} else {
			return false;
		}
	}
}