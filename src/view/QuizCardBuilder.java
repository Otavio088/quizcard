package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QuizCard;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Cursor;

public class QuizCardBuilder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private ArrayList<QuizCard> cardList;
	private JTextArea txtQuestion;
	private JTextArea textTrueAnswer, textFalseAnswer,  textFalseAnswer_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizCardBuilder frame = new QuizCardBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuizCardBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(455, 500);
		setTitle("Criador de Cards");
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 231, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		cardList = new ArrayList<QuizCard>();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("Arquivo");
		menuBar.add(fileMenu);

		JMenuItem saveMenuItem = new JMenuItem("Salvar Conjunto");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fileSave = new JFileChooser();
				fileSave.showSaveDialog(QuizCardBuilder.this);
				saveFile(fileSave.getSelectedFile());
			}
		});
		fileMenu.add(saveMenuItem);

		JLabel lblQuestion = new JLabel("Pergunta");
		lblQuestion.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scrollPaneQuestion = new JScrollPane();
		txtQuestion = new JTextArea();
		txtQuestion.setBackground(new Color(255, 242, 236));
		txtQuestion.setFont(new Font("Segoe UI", Font.BOLD, 18));
		txtQuestion.setLineWrap(true);
		txtQuestion.setWrapStyleWord(true);
		scrollPaneQuestion.setViewportView(txtQuestion);

		JLabel lblTrueAnswer = new JLabel("Resposta Correta");
		lblTrueAnswer.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scrollPaneTrueAnswer = new JScrollPane();
		textTrueAnswer = new JTextArea();
		textTrueAnswer.setBackground(new Color(255, 242, 236));
		textTrueAnswer.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textTrueAnswer.setLineWrap(true);
		textTrueAnswer.setWrapStyleWord(true);
		scrollPaneTrueAnswer.setViewportView(textTrueAnswer);

		JLabel lblFalseAnswer = new JLabel("Resposta Errada");
		lblFalseAnswer.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scrollPaneFalseAnswer = new JScrollPane();
		textFalseAnswer = new JTextArea();
		textFalseAnswer.setBackground(new Color(255, 242, 236));;
		textFalseAnswer.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFalseAnswer.setLineWrap(true);
		textFalseAnswer.setWrapStyleWord(true);
		scrollPaneFalseAnswer.setViewportView(textFalseAnswer);

		JLabel lblFalseAnswer_1 = new JLabel("Resposta Errada");
		lblFalseAnswer_1.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JScrollPane scrollPaneFalseAnswer_1 = new JScrollPane();
		textFalseAnswer_1 = new JTextArea();
		textFalseAnswer_1.setBackground(new Color(255, 242, 236));
		textFalseAnswer_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		textFalseAnswer_1.setLineWrap(true);
		textFalseAnswer_1.setWrapStyleWord(true);
		scrollPaneFalseAnswer_1.setViewportView(textFalseAnswer_1);

		JButton btnNextCard = new JButton("Salvar Carta");
		btnNextCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if ( (!(textTrueAnswer.getText().isEmpty())) && (!(txtQuestion.getText().isEmpty())) 
						&& (!textFalseAnswer.getText().isEmpty()) && (!textFalseAnswer_1.getText().isEmpty()) ) {
					QuizCard card = new QuizCard(txtQuestion.getText(), textTrueAnswer.getText(), textFalseAnswer.getText(), textFalseAnswer_1.getText());
					cardList.add(card);
					clearCard();
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNextCard.setForeground(new Color(0, 0, 0));
		btnNextCard.setBackground(new Color(255, 154, 53));
		btnNextCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNextCard.setFocusPainted(false);
		btnNextCard.setFont(new Font("Segoe UI", Font.BOLD, 12));

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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblQuestion))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneQuestion, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTrueAnswer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneTrueAnswer, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFalseAnswer, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneFalseAnswer, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblFalseAnswer_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPaneFalseAnswer_1, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBack, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
								.addComponent(btnNextCard, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQuestion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneQuestion, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTrueAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTrueAnswer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblFalseAnswer, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneFalseAnswer, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFalseAnswer_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneFalseAnswer_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(btnNextCard)
					.addGap(18)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);

		contentPane.setLayout(gl_contentPane);
	}

	private void saveFile(File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + "/" + card.getAnswer() + "/" + card.getFalseAnswer_1() + "/" + card.getFalseAnswer_2() + "\n");
			}

			writer.close();
			cardList.clear();
			clearCard();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	private void clearCard() {
		txtQuestion.setText(null);
		textTrueAnswer.setText(null);
		textFalseAnswer.setText(null);
		textFalseAnswer_1.setText(null);
		txtQuestion.requestFocus();
	}
}