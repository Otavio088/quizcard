package com.quizcard;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class QuizCardPlayer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextArea textQuestion;
	private JButton btnShow;
	private JRadioButton radioAnswer_1;
	private JRadioButton radioAnswer_2;
	private JRadioButton radioAnswer_3;
	
	private ArrayList<QuizCard> cardList = new ArrayList<QuizCard>();
	private int currentCardIndex;
	private QuizCard currentCard;
	private final ButtonGroup buttonGroup;
	private JLabel lblResponse;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizCardPlayer frame = new QuizCardPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuizCardPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(455, 500);
		setTitle("Player Quiz Card");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 231, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Arquivo");
		menuBar.add(fileMenu);
		JMenuItem loadMenuItem = new JMenuItem("Carregar Conjunto");
		loadMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileOpen = new JFileChooser();
				fileOpen.showOpenDialog(QuizCardPlayer.this);
				loadFile(fileOpen.getSelectedFile());
			}
		});
		fileMenu.add(loadMenuItem);
		
		JScrollPane scrollPane = new JScrollPane();
		textQuestion = new JTextArea();
		textQuestion.setBackground(new Color(255, 242, 236));
		textQuestion.setLineWrap(true);
		textQuestion.setWrapStyleWord(true);
		textQuestion.setEditable(false);
		textQuestion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		scrollPane.setViewportView(textQuestion);
		
		btnShow = new JButton("Carregue um Conjunto");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				clear();
				if ((btnShow.getText().equals("Próxima Questão")) && (!(currentCardIndex == cardList.size()))) {
					showNextCard();
					btnShow.setText("Responda");
					btnShow.setEnabled(false);
				} else {
					textQuestion.setText("Não há mais cartões!");
					btnShow.setText("Carregue um Conjunto");
					btnShow.setBackground(new Color(255, 154, 53));
					btnShow.setEnabled(false);
				}
			}
		});
		btnShow.setForeground(new Color(0, 0, 0));
		btnShow.setBackground(new Color(255, 154, 53));
		btnShow.setEnabled(false);
		btnShow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShow.setFocusPainted(false);
		btnShow.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		JButton btnBack = new JButton("Voltar");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				dispose();
				
				Menu x = new Menu();
				x.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(255, 154, 53));
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		buttonGroup = new ButtonGroup();
		
		radioAnswer_1 = new JRadioButton("");
		buttonGroup.add(radioAnswer_1);
		radioAnswer_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		radioAnswer_1.setBackground(new Color(255, 231, 206));
		radioAnswer_1.setSize(200, 200);
		radioAnswer_1.setEnabled(false);
		radioAnswer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				compareAnswer(radioAnswer_1.getText());
			}
		});
		
		radioAnswer_2 = new JRadioButton("");
		buttonGroup.add(radioAnswer_2);
		radioAnswer_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		radioAnswer_2.setBackground(new Color(255, 231, 206));
		radioAnswer_2.setEnabled(false);
		radioAnswer_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				compareAnswer(radioAnswer_2.getText());
			}
		});
		
		radioAnswer_3 = new JRadioButton("");
		buttonGroup.add(radioAnswer_3);
		radioAnswer_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		radioAnswer_3.setBackground(new Color(255, 231, 206));
		radioAnswer_3.setEnabled(false);
		radioAnswer_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				compareAnswer(radioAnswer_3.getText());
			}
		});
		
		lblResponse = new JLabel("");
		lblResponse.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(radioAnswer_3)
								.addComponent(radioAnswer_2)
								.addComponent(radioAnswer_1))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblResponse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addComponent(btnBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addComponent(btnShow, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
							.addGap(23))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(btnShow)
					.addGap(18)
					.addComponent(btnBack)
					.addGap(18)
					.addComponent(radioAnswer_1)
					.addGap(18)
					.addComponent(radioAnswer_2)
					.addGap(18)
					.addComponent(radioAnswer_3)
					.addGap(18)
					.addComponent(lblResponse, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	private void loadFile(File file) {
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
		
		showNextCard();
	}

	private void makeCard(String line) {
		String[] result = line.split("/");
		QuizCard card = new QuizCard(result[0], result[1], result[2], result[3]);
		cardList.add(card);
	}
	
	private void showNextCard() {
		currentCard = cardList.get(currentCardIndex);
		textQuestion.setText(currentCard.getQuestion());
		showAnswers();
		currentCardIndex++;
	}
	
	private void showAnswers() {
		int rand = ((int) (Math.random() * 3));
		
		if (rand == 0) {
			radioAnswer_1.setText(currentCard.getAnswer());
			radioAnswer_2.setText(currentCard.getFalseAnswer_1());
			radioAnswer_3.setText(currentCard.getFalseAnswer_2());
		}
		if (rand == 1) {
			radioAnswer_1.setText(currentCard.getFalseAnswer_1());
			radioAnswer_2.setText(currentCard.getAnswer());
			radioAnswer_3.setText(currentCard.getFalseAnswer_2());
		}
		if (rand == 2) {
			radioAnswer_1.setText(currentCard.getFalseAnswer_2());
			radioAnswer_2.setText(currentCard.getFalseAnswer_1());
			radioAnswer_3.setText(currentCard.getAnswer());
		}
		
		btnShow.setText("Responda");
		radioAnswer_1.setEnabled(true);
		radioAnswer_2.setEnabled(true);
		radioAnswer_3.setEnabled(true);
	}
	
	private void compareAnswer(String userAnswer) {
		if (userAnswer.equals(currentCard.getAnswer())) {
			lblResponse.setText("ACERTOU!");
			lblResponse.setForeground(Color.GREEN);
		} else {
			lblResponse.setText("ERROU!");
			lblResponse.setForeground(Color.RED);
		}
		
		btnShow.setEnabled(true);
		radioAnswer_1.setEnabled(false);
		radioAnswer_2.setEnabled(false);
		radioAnswer_3.setEnabled(false);
		btnShow.setText("Próxima Questão");
	}
	
	private void clear() {
		buttonGroup.clearSelection();
		lblResponse.setText("");
	}
	
}
