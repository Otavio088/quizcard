package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(442, 300);
		setTitle("Menu");
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 231, 206));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTitle = new JLabel("QUIZ CARD GAME");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));

		JButton btnCardBuilder = new JButton("Criar Cartões");
		btnCardBuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				dispose();

				QuizCardBuilder x = new QuizCardBuilder();
				x.setVisible(true);
			}
		});
		btnCardBuilder.setForeground(new Color(0, 0, 0));
		btnCardBuilder.setBackground(new Color(255, 154, 53));
		btnCardBuilder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCardBuilder.setFocusPainted(false);
		btnCardBuilder.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JButton btnCardPlayer = new JButton("Jogar");
		btnCardPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

				QuizCardPlayer x = new QuizCardPlayer();
				x.setVisible(true);
			}
		});
		btnCardPlayer.setForeground(new Color(0, 0, 0));
		btnCardPlayer.setBackground(new Color(255, 154, 53));
		btnCardPlayer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCardPlayer.setFocusPainted(false);
		btnCardPlayer.setFont(new Font("Segoe UI", Font.BOLD, 12));

		ImageIcon icon = new ImageIcon("images/card_img.png");
		icon.setImage(icon.getImage().getScaledInstance(120, 70, 100));
		JLabel lblImg = new JLabel(icon);
		lblImg.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnCardPlayer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCardBuilder, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(195, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(103)
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(280))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(71)
					.addComponent(lblImg, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addGap(252))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
					.addGap(26)
					.addComponent(lblImg, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(34)
					.addComponent(btnCardBuilder)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCardPlayer)
					.addContainerGap())
		);

		contentPane.setLayout(gl_contentPane);
	}
}