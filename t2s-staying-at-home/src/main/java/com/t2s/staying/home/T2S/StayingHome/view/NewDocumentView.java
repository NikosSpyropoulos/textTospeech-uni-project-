package com.t2s.staying.home.T2S.StayingHome.view;

import com.t2s.staying.home.T2S.StayingHome.factory.CommandsFactory;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static com.t2s.staying.home.T2S.StayingHome.ApplicationConstants.NEW_DOCUMENT_COMMAND;

public class NewDocumentView extends JFrame {

	private JPanel contentPane;

	private CommandsFactory commandsFactory = new CommandsFactory();
	private JFrame frame;
	private JTextField authorTextField;
	private JTextField documentTitleTextField;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public NewDocumentView() {

		intialize();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	private void intialize(){
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(null);
		bluePanel.setBorder(new LineBorder(Color.BLACK));
		bluePanel.setBackground(new Color(98, 134, 183));
		bluePanel.setBounds(0, -13, 450, 127);
		contentPane.add(bluePanel);

		JLabel welcomeLabel = new JLabel("Create New File");
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		welcomeLabel.setBounds(125, 45, 225, 32);
		bluePanel.add(welcomeLabel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(null);
		buttonsPanel.setBorder(new MatteBorder(1, 1, 0, 1, (Color) Color.BLACK));
		buttonsPanel.setBounds(114, 95, 210, 32);
		bluePanel.add(buttonsPanel);

		JLabel lblNewLabel = new JLabel("Author's Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(49, 10, 107, 13);
		buttonsPanel.add(lblNewLabel);

		JPanel closePanel = new JPanel();
		closePanel.setBounds(0, 10, 450, 25);
		bluePanel.add(closePanel);
		closePanel.setLayout(null);
		closePanel.setBorder(new LineBorder(Color.BLACK));
		closePanel.setBackground(new Color(60, 89, 130));

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

		JButton backButton = new JButton("◄");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				frame.setVisible(false);
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(new Color(60, 89, 130));
		backButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		backButton.setBounds(10, 45, 53, 32);
		bluePanel.add(backButton);

		JPanel grayPanel = new JPanel();
		grayPanel.setLayout(null);
		grayPanel.setBorder(new LineBorder(Color.BLACK));
		grayPanel.setBackground(new Color(226, 226, 226));
		grayPanel.setBounds(0, 109, 450, 191);
		contentPane.add(grayPanel);

		JPanel buttonsPanel_1 = new JPanel();
		buttonsPanel_1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.BLACK));
		buttonsPanel_1.setBounds(114, 0, 210, 126);
		grayPanel.add(buttonsPanel_1);
		buttonsPanel_1.setLayout(null);

		authorTextField  = new JTextField();
		authorTextField .setBounds(50, 10, 110, 29);
		buttonsPanel_1.add(authorTextField );
		authorTextField .setColumns(10);

		documentTitleTextField  = new JTextField();
		documentTitleTextField .setColumns(10);
		documentTitleTextField .setBounds(50, 76, 110, 29);
		buttonsPanel_1.add(documentTitleTextField );

		JLabel lblFilesTitle = new JLabel("File's Title");
		lblFilesTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFilesTitle.setBounds(53, 53, 107, 13);
		buttonsPanel_1.add(lblFilesTitle);

		JButton btnSaveFile = new JButton("Save File");
		ActionListener saveDocumentActionListener = commandsFactory.createCommand(NEW_DOCUMENT_COMMAND, this);
		btnSaveFile.addActionListener(saveDocumentActionListener);

		btnSaveFile.setForeground(Color.WHITE);
		btnSaveFile.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSaveFile.setBackground(new Color(98, 134, 183));

		btnSaveFile.setBounds(159, 137, 115, 31);
		grayPanel.add(btnSaveFile);
	}

	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public void goToMainView() {
		new MainMenu();
		frame.setVisible(false);
	}

	// getters
	public String getAuthorTextField() {
		return authorTextField.getText();
	}

	public String getDocumentTitleTextField() {
		return documentTitleTextField.getText();
	}

	public void setAuthorTextField(JTextField authorTextField) {
		this.authorTextField = authorTextField;
	}

	public void setDocumentTitleTextField(JTextField documentTitleTextField) {
		this.documentTitleTextField = documentTitleTextField;
	}

	public JTextField getAuthorJTextField() {
		return authorTextField;
	}

	public JTextField getDocumentTitleJTextField() {
		return documentTitleTextField;
	}
}

