package com.t2s.staying.home.T2S.StayingHome.view;

import com.t2s.staying.home.T2S.StayingHome.factory.CommandsFactory;
import com.t2s.staying.home.T2S.StayingHome.factory.TextToSpeechFactory;
import com.t2s.staying.home.T2S.StayingHome.manager.ReplayManager;
import com.t2s.staying.home.T2S.StayingHome.model.Line;
import com.t2s.staying.home.T2S.StayingHome.tts.FreeTTSAdapter;

import javax.swing.*;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.t2s.staying.home.T2S.StayingHome.ApplicationConstants.*;

public class DocumentEditorView extends JFrame {

	private CommandsFactory commandsFactory = new CommandsFactory();
	private TextToSpeechFactory textToSpeechAPIFactory = new TextToSpeechFactory();
	private JPanel mainPane;

	private static final String LOAD_BUTTON_TEXT = "Load";
	private static final String UPDATE_BUTTON_TEXT = "Save";
	/**
	 * Create the frame.
	 */
	private JFrame frame;
	private JTextField authorTextField;
	private JTextField documentTitleTextField;

	private JLabel creationTimestampPlaceholder;
	private JLabel lModifiedTimestampPlaceholder;

	private JTextArea textArea;

	private FreeTTSAdapter t2s = new FreeTTSAdapter();

	private JSlider voiceRateSlider = new JSlider(0, 400);

	private JSlider voicePitchSlider = new JSlider(50, 200);

	private ReplayManager replayManager = new ReplayManager();

	private FloatJSlider voiceVolumeSlider = new FloatJSlider(3, 10, 10, 10);

	public DocumentEditorView() {

		initialize();

		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

	}
	private void initialize(){
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1191, 763);

		mainPane = new JPanel();
		mainPane.setBorder(new LineBorder(Color.BLACK));
		frame.setContentPane(mainPane);
		mainPane.setLayout(null);
		mainPane.setBackground(new Color(226,226,226));

		JPanel closePanel = new JPanel();
		closePanel.setLayout(null);
		closePanel.setBorder(new LineBorder(Color.BLACK));
		closePanel.setBackground(new Color(60, 89, 130));
		closePanel.setBounds(-75, 0, 1266, 21);
		mainPane.add(closePanel);

		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblX.setBounds(1243, 0, 37, 23);
		closePanel.add(lblX);

		JLabel lblMin = new JLabel("-");
		lblMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(frame.ICONIFIED);setState(JFrame.ICONIFIED);
			}
		});
		lblMin.setForeground(Color.WHITE);
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMin.setBounds(1219, -1, 37, 23);
		closePanel.add(lblMin);

		JPanel textAreaPanel = new JPanel();
		textAreaPanel.setLayout(null);
		textAreaPanel.setBorder(new LineBorder(Color.BLACK));
		textAreaPanel.setBackground(new Color(98, 134, 183));
		textAreaPanel.setBounds(309, 128, 572, 597);
		mainPane.add(textAreaPanel);

		authorTextField = new JTextField();
		authorTextField.setColumns(10);
		authorTextField.setBounds(131, 41, 133, 19);
		textAreaPanel.add(authorTextField);


		documentTitleTextField = new JTextField();
		documentTitleTextField.setColumns(10);
		documentTitleTextField.setBounds(340, 41, 133, 19);
		textAreaPanel.add(documentTitleTextField);

		JLabel authorLabel = new JLabel("Author's Name :");
		authorLabel.setForeground(Color.WHITE);
		authorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		authorLabel.setBounds(131, 18, 124, 13);
		textAreaPanel.add(authorLabel);

		JLabel documentTitleLabel = new JLabel("File's Title :");
		documentTitleLabel.setForeground(Color.WHITE);
		documentTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		documentTitleLabel.setBounds(340, 18, 124, 13);
		textAreaPanel.add(documentTitleLabel);

		textArea = new JTextArea();
		textArea.setBounds(104, 94, 379, 444);
		textAreaPanel.add(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton replayBtn = new JButton(" ℛeplay");
		ActionListener replayActionListener = commandsFactory.createCommand(REPLAY_COMMAND, this);
		replayBtn.addActionListener(replayActionListener);
		replayBtn.setForeground(Color.WHITE);
		replayBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		replayBtn.setBackground(new Color(60, 89, 130));
		replayBtn.setBounds(247, 560, 96, 37);
		textAreaPanel.add(replayBtn);

		JPanel settingsPanel = new JPanel();
		settingsPanel.setBorder(new LineBorder(Color.BLACK));
		settingsPanel.setBackground(new Color(98, 134, 183));
		settingsPanel.setBounds(470, 31, 251, 55);
		mainPane.add(settingsPanel);
		settingsPanel.setLayout(null);


		JLabel filelbl = new JLabel("File Settings");
		filelbl.setForeground(Color.WHITE);
		filelbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		filelbl.setBounds(90, 0, 106, 23);

		settingsPanel.add(filelbl);

		JButton loadButton = new JButton(LOAD_BUTTON_TEXT);
		ActionListener loadDocumentActionListener = commandsFactory.createCommand(OPEN_DOCUMENT_COMMAND, this);
		loadButton.addActionListener(loadDocumentActionListener);
		loadButton.setBounds(20, 22, 106, 23);
		loadButton.setBackground(new Color(226,226,226));
		settingsPanel.add(loadButton);


		JButton saveButton = new JButton(UPDATE_BUTTON_TEXT);
		ActionListener saveDocumentActionListener = commandsFactory.createCommand(SAVE_DOCUMENT_COMMAND, this);
		saveButton.addActionListener(saveDocumentActionListener);
		saveButton.setBackground(new Color(226,226,226));
		saveButton.setBounds(135, 22, 106, 23);
		settingsPanel.add(saveButton);

		JPanel atBashPanel = new JPanel();
		atBashPanel.setBorder(new LineBorder(Color.BLACK));
		atBashPanel.setBounds(955, 164, 178, 197);
		mainPane.add(atBashPanel);
		atBashPanel.setLayout(null);

		JButton encodeSelectedButtonAtbash = new JButton("Line");
		ActionListener  encodeLineAtbashActionListener = commandsFactory.createCommand(ENCODE_LINE_ATBASH, this);
		encodeSelectedButtonAtbash.addActionListener(encodeLineAtbashActionListener);
		encodeSelectedButtonAtbash.setVerticalAlignment(SwingConstants.TOP);
		encodeSelectedButtonAtbash.setForeground(Color.WHITE);
		encodeSelectedButtonAtbash.setFont(new Font("Tahoma", Font.BOLD, 17));
		encodeSelectedButtonAtbash.setBackground(new Color(98, 134, 183));
		encodeSelectedButtonAtbash.setBounds(31, 131, 115, 31);
		atBashPanel.add(encodeSelectedButtonAtbash);

		JLabel lblAtbash = new JLabel("Atbash");
		lblAtbash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAtbash.setBounds(53, 24, 115, 20);
		atBashPanel.add(lblAtbash);

		JButton encodeAllButtonAtbash = new JButton("All");
		ActionListener  encodeAllAtbashActionListener = commandsFactory.createCommand(ENCODE_ALL_ATBASH, this);
		encodeAllButtonAtbash.addActionListener(encodeAllAtbashActionListener);
		encodeAllButtonAtbash.setForeground(Color.WHITE);
		encodeAllButtonAtbash.setFont(new Font("Tahoma", Font.BOLD, 17));
		encodeAllButtonAtbash.setBackground(new Color(98, 134, 183));
		encodeAllButtonAtbash.setBounds(31, 65, 115, 31);
		atBashPanel.add(encodeAllButtonAtbash);

		JPanel rot13Panel = new JPanel();
		rot13Panel.setBorder(new LineBorder(Color.BLACK));
		rot13Panel.setBounds(955, 443, 178, 197);
		mainPane.add(rot13Panel);
		rot13Panel.setLayout(null);

		JButton encodingLineButtonROT13 = new JButton("Line");
		ActionListener  encodeLineRot13ActionListener = commandsFactory.createCommand(ENCODE_LINE_ROT13, this);
		encodingLineButtonROT13.addActionListener(encodeLineRot13ActionListener);
		encodingLineButtonROT13.setVerticalAlignment(SwingConstants.TOP);
		encodingLineButtonROT13.setForeground(Color.WHITE);
		encodingLineButtonROT13.setFont(new Font("Tahoma", Font.BOLD, 17));
		encodingLineButtonROT13.setBackground(new Color(98, 134, 183));
		encodingLineButtonROT13.setBounds(29, 125, 115, 31);
		rot13Panel.add(encodingLineButtonROT13);

		JButton encodingAllButtonROT13 = new JButton("All");
		ActionListener  encodeAllRot13ActionListener = commandsFactory.createCommand(ENCODE_ALL_ROT13, this);
		encodingAllButtonROT13.addActionListener(encodeAllRot13ActionListener);
		encodingAllButtonROT13.setForeground(Color.WHITE);
		encodingAllButtonROT13.setFont(new Font("Tahoma", Font.BOLD, 17));
		encodingAllButtonROT13.setBackground(new Color(98, 134, 183));
		encodingAllButtonROT13.setBounds(29, 63, 115, 31);
		rot13Panel.add(encodingAllButtonROT13);

		JLabel lblRot = new JLabel("Rot13");
		lblRot.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRot.setBounds(53, 21, 115, 20);
		rot13Panel.add(lblRot);

		JPanel t2sPanel = new JPanel();
		t2sPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		t2sPanel.setBounds(49, 164, 178, 197);
		mainPane.add(t2sPanel);
		t2sPanel.setLayout(null);

		JButton ttsSelectedButton = new JButton("Line");
		ActionListener lineToSpeechActionListener = commandsFactory.createCommand(LINE_TO_SPEECH, this);
		ttsSelectedButton.addActionListener(lineToSpeechActionListener);
		ttsSelectedButton.setForeground(Color.WHITE);
		ttsSelectedButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		ttsSelectedButton.setBackground(new Color(98, 134, 183));
		ttsSelectedButton.setBounds(34, 126, 115, 31);
		t2sPanel.add(ttsSelectedButton);

		JButton ttsAllButton = new JButton("All");
		ActionListener doc2SpeechActionListener = commandsFactory.createCommand(DOC_TO_SPEECH_COMMAND, this);
		ttsAllButton.addActionListener(doc2SpeechActionListener);
		ttsAllButton.setForeground(Color.WHITE);
		ttsAllButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		ttsAllButton.setBackground(new Color(98, 134, 183));
		ttsAllButton.setBounds(34, 61, 115, 31);
		t2sPanel.add(ttsAllButton);

		JLabel lblt2s = new JLabel("TextToSpeech");
		lblt2s.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblt2s.setBounds(34, 20, 115, 20);
		t2sPanel.add(lblt2s);

		JPanel reversePanel = new JPanel();
		reversePanel.setBorder(new LineBorder(Color.BLACK));
		reversePanel.setBounds(49, 443, 178, 197);
		mainPane.add(reversePanel);
		reversePanel.setLayout(null);

		JButton reverseLineButton_1 = new JButton("Line");
		ActionListener reverseLineActionListener = commandsFactory.createCommand(REVERSE_LINE_COMMAND, this);
		reverseLineButton_1.addActionListener(reverseLineActionListener);
		reverseLineButton_1.setForeground(Color.WHITE);
		reverseLineButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		reverseLineButton_1.setBackground(new Color(98, 134, 183));
		reverseLineButton_1.setBounds(34, 124, 115, 31);
		reversePanel.add(reverseLineButton_1);

		JButton reverseAllButton = new JButton("All");
		ActionListener reverseAllActionListener = commandsFactory.createCommand(REVERSE_ALL_COMMAND, this);
		reverseAllButton.addActionListener(reverseAllActionListener);
		reverseAllButton.setForeground(Color.WHITE);
		reverseAllButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		reverseAllButton.setBackground(new Color(98, 134, 183));
		reverseAllButton.setBounds(34, 66, 115, 31);
		reversePanel.add(reverseAllButton);

		JLabel lblReversed = new JLabel("Reversed");
		lblReversed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReversed.setBounds(53, 22, 115, 20);
		reversePanel.add(lblReversed);

		JButton backButton = new JButton("◄");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu();
				frame.setVisible(false);
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		backButton.setBackground(new Color(60, 89, 130));
		backButton.setBounds(49, 31, 74, 37);
		mainPane.add(backButton);

		JLabel creationTimestampLabel = new JLabel("Creation Date :");
		creationTimestampLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		creationTimestampLabel.setBounds(932, 666, 100, 13);
		mainPane.add(creationTimestampLabel);

		JLabel lModifiedTimestampLabel = new JLabel("Last Modified Date :");
		lModifiedTimestampLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lModifiedTimestampLabel.setBounds(926, 712, 135, 13);
		mainPane.add(lModifiedTimestampLabel);

		creationTimestampPlaceholder = new JLabel("-");
		creationTimestampPlaceholder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		creationTimestampPlaceholder.setBounds(1073, 666, 146, 14);
		mainPane.add(creationTimestampPlaceholder);

		lModifiedTimestampPlaceholder = new JLabel("-");
		lModifiedTimestampPlaceholder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lModifiedTimestampPlaceholder.setBounds(1073, 712, 146, 14);
		mainPane.add(lModifiedTimestampPlaceholder);

		JPanel panel = new JPanel();
		panel.setBounds(941, 55, 219, 87);
		mainPane.add(panel);
		panel.setLayout(null);

		ChangeListener volumeChangeListener = textToSpeechAPIFactory.createChangeListener(TUNE_VOLUME_COMMAND, this);
		voiceVolumeSlider.addChangeListener(volumeChangeListener);
		//JSlider sliderVolume = new JSlider();
		voiceVolumeSlider.setBounds(10, 10, 200, 22);
		panel.add(voiceVolumeSlider);

		ChangeListener pitchChangeListener = textToSpeechAPIFactory.createChangeListener(TUNE_PITCH_COMMAND, this);
		voicePitchSlider.addChangeListener(pitchChangeListener);
		//JSlider sliderPitch = new JSlider();
		voicePitchSlider.setBounds(10, 34, 200, 22);
		panel.add(voicePitchSlider);

		ChangeListener rateChangeListener = textToSpeechAPIFactory.createChangeListener(TUNE_RATE_COMMAND, this);
		voiceRateSlider.addChangeListener(rateChangeListener);
		//JSlider sliderRate = new JSlider();
		voiceRateSlider.setBounds(10, 61, 200, 22);
		panel.add(voiceRateSlider);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(609, 46, 45, 13);
		mainPane.add(lblNewLabel_3);

		JLabel lblVolume = new JLabel("Volume :");
		lblVolume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVolume.setBounds(876, 70, 65, 13);
		mainPane.add(lblVolume);

		JLabel lblPitch = new JLabel("Pitch :");
		lblPitch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPitch.setBounds(891, 94, 56, 13);
		mainPane.add(lblPitch);

		JLabel lblRate = new JLabel("Rate :");
		lblRate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRate.setBounds(891, 117, 56, 13);
		mainPane.add(lblRate);

		JLabel lblSound = new JLabel("Sound Settings");
		lblSound.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSound.setBounds(991, 31, 127, 21);
		mainPane.add(lblSound);
	}
	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public void updateView(String docTitle, String docAuthor, String docCreationTime, String docLModifiedTime, List<Line> lines) {

		this.documentTitleTextField.setText(docTitle);
		this.authorTextField.setText(docAuthor);
		this.creationTimestampPlaceholder.setText(docCreationTime);
		this.lModifiedTimestampPlaceholder.setText(docLModifiedTime);
		this.textArea.setText("");
		for (Line line : lines) {
			for (String word : line.getWords()) {
				this.textArea.append(word);
				this.textArea.append(" ");
			}
			this.textArea.append("\n");

		}

	}

	public void goToMainView() {
		new MainMenu();
		frame.setVisible(false);
	}

	public int getLineNumber(){
		int caretPos = textArea.getCaretPosition();
		int lineNumber = 0;
		try {
			lineNumber = textArea.getLineOfOffset(caretPos);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return lineNumber;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public String getAuthorTextField() {
		return authorTextField.getText();
	}

	public String getDocumentTitleTextField() {
		return documentTitleTextField.getText();
	}

	public JSlider getVoicePitchSlider() {
		return voicePitchSlider;
	}

	public JSlider getVoiceRateSlider() {
		return voiceRateSlider;
	}

	public FloatJSlider getVoiceVolumeSlider() {
		return voiceVolumeSlider;
	}

	public class FloatJSlider extends JSlider {
		final int scale;

		public FloatJSlider(int min, int max, int value, int scale) {
			super(min, max, value);
			this.scale = scale;
		}

		public float getScaledValue() {
			return ((float)super.getValue()) / this.scale;
		}
	}
	public ReplayManager getReplayManager() {
		return replayManager;
	}

	// standard getters setters
	public void setAuthorTextField(JTextField authorTextField) {
		this.authorTextField = authorTextField;
	}

	public void setDocumentTitleTextField(JTextField documentTitleTextField) {
		this.documentTitleTextField = documentTitleTextField;
	}

	public void setCreationTimestampPlaceholder(JLabel creationTimestampPlaceholder) {
		this.creationTimestampPlaceholder = creationTimestampPlaceholder;
	}

	public void setlModifiedTimestampPlaceholder(JLabel lModifiedTimestampPlaceholder) {
		this.lModifiedTimestampPlaceholder = lModifiedTimestampPlaceholder;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JLabel getCreationTimestampPlaceholder() {
		return creationTimestampPlaceholder;
	}

	public JLabel getlModifiedTimestampPlaceholder() {
		return lModifiedTimestampPlaceholder;
	}

	public JTextField getAuthorJTextField() {
		return authorTextField;
	}

	public JTextField getTitleJTextField() {
		return documentTitleTextField;
	}
}
