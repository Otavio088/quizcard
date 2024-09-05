package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.QuizCard;

public class QuizCardBuilderController {
	
	private ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
	
	public void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + "/" + card.getAnswer() + "/" + card.getFalseAnswer_1() + "/" + card.getFalseAnswer_2() + "\n");
			}
			
			writer.close();
			cardList.clear();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	public void addCard(QuizCard card) {
		cardList.add(card);
	}
	
}