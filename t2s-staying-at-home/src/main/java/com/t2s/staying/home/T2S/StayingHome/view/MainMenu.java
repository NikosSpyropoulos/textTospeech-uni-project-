package com.t2s.staying.home.T2S.StayingHome.view;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class MainMenu extends JFrame {

	private JFrame frame;

	private JPanel contentPane;
	private static final String WELCOME_TO_T2S_LABEL_CONTENTS = "Welcome To T2S";
	private static final String CREATE_EMPTY_DOCUMENT_BUTTON_TEXT = "New File";
	private static final String OPEN_DOCUMENT_EDITOR_BUTTON_TEXT = "Edit File";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {

		initialize();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	public void initialize(){
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.setAlwaysOnTop(true);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel bluePanel = new JPanel();
		bluePanel.setBorder(new LineBorder(Color.BLACK));
		bluePanel.setBackground(new Color(98, 134, 183 ));
		bluePanel.setBounds(0, 20, 450, 114);
		contentPane.add(bluePanel);
		bluePanel.setLayout(null);

		JLabel welcomeLabel = new JLabel(WELCOME_TO_T2S_LABEL_CONTENTS);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		welcomeLabel.setBounds(122, 29, 225, 32);
		bluePanel.add(welcomeLabel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) Color.BLACK));
		buttonsPanel.setBounds(114, 91, 210, 23);
		bluePanel.add(buttonsPanel);
		buttonsPanel.setLayout(null);

		JPanel grayPanel = new JPanel();
		grayPanel.setBorder(new LineBorder(Color.BLACK));
		grayPanel.setBackground(new Color(226, 226, 226 ));
		grayPanel.setBounds(0, 130, 450, 170);
		contentPane.add(grayPanel);
		grayPanel.setLayout(null);

		JPanel buttonsPanel_1 = new JPanel();
		buttonsPanel_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		buttonsPanel_1.setBounds(114, 0, 210, 126);
		grayPanel.add(buttonsPanel_1);
		buttonsPanel_1.setLayout(null);

		JButton btnNewFile = new JButton(CREATE_EMPTY_DOCUMENT_BUTTON_TEXT);
		btnNewFile.setForeground(Color.WHITE);
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NewDocumentView();
				frame.setVisible(false);
			}
		});
		btnNewFile.setBackground(new Color(98, 134, 183 ));
		btnNewFile.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewFile.setBounds(47, 0, 119, 43);
		buttonsPanel_1.add(btnNewFile);

		JButton btnEditFile = new JButton(OPEN_DOCUMENT_EDITOR_BUTTON_TEXT);
		btnEditFile.setForeground(Color.WHITE);
		btnEditFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DocumentEditorView();
				frame.setVisible(false);
			}
		});
		btnEditFile.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnEditFile.setBackground(new Color(98, 134, 183));
		btnEditFile.setBounds(47, 53, 119, 43);
		buttonsPanel_1.add(btnEditFile);

		JPanel closePanel = new JPanel();
		closePanel.setBorder(new LineBorder(Color.BLACK));
		closePanel.setBackground(new Color(60, 89, 130 ));
		closePanel.setBounds(0, 0, 450, 21);
		contentPane.add(closePanel);
		closePanel.setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblX.setBounds(433, 0, 37, 23);
		closePanel.add(lblX);

		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}

		});
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMin.setBounds(413, -1, 37, 23);
		closePanel.add(lblMin);


	}
}
