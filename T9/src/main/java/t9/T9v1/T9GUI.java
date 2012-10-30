package t9.T9v1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

/**
 * @author sandeeprv provides GUI for T9
 */
@SuppressWarnings("serial")
public class T9GUI extends JFrame {

	T9 T9Communicator;
	// Declaring components required i.e Label,TextArea and Buttons
	private JLabel textLabel;
	private JTextArea smsText;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	private JButton button9;
	private JButton button0;
	private JButton button_;
	private JButton _button;

	// Digit Sequence is the sequence of digits pressed by user
	StringBuilder digitSequence = new StringBuilder("");
	int preferenceCount = 1;
	String currentString = "";
	String response = "";

	/**
	 * constructor to initialize components of GUI
	 */
	public T9GUI() {

		T9Communicator = new T9();

		/*
		 * setting up the text area
		 */
		createTextArea();

		/*
		 * setting up the buttons
		 */

		initializeButtons();

		// setting a panel having all the 12 buttons

		JPanel panelButtons = addButtonsToPanel();

		// Adding action listener to respond to button clicks
		setActionListener();

		/*
		 * setting up the frame
		 */
		setLayout(new BorderLayout());
		setSize(250, 250);
		setResizable(false);
		setTitle("SMS");

		this.add(panelButtons, BorderLayout.SOUTH);
		JFrame.setDefaultLookAndFeelDecorated(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void initializeButtons() {
		button1 = new JButton("1 ");
		button2 = new JButton("2 abc");
		button3 = new JButton("3 def");
		button4 = new JButton("4 ihg");
		button5 = new JButton("5 kjl");
		button6 = new JButton("6 onm");
		button7 = new JButton("7 qrps");
		button8 = new JButton("8 tvu");
		button9 = new JButton("9 wyxz");
		button0 = new JButton("0");
		button_ = new JButton("#");
		_button = new JButton("*");
	}

	private void setActionListener() {
		EventHandler handler = new EventHandler();
		button1.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		button4.addActionListener(handler);
		button5.addActionListener(handler);
		button6.addActionListener(handler);
		button7.addActionListener(handler);
		button8.addActionListener(handler);
		button9.addActionListener(handler);
		button0.addActionListener(handler);
		_button.addActionListener(handler);
		button_.addActionListener(handler);
	}

	private void createTextArea() {

		initializeTextArea();

		setUpTextArea();
	}

	private void setUpTextArea() {
		JScrollPane scrollPane = new JScrollPane(smsText);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		// Create a parallel group for the horizontal axis
		ParallelGroup hGroup = layout
				.createParallelGroup(GroupLayout.Alignment.LEADING);
		// Create a sequential and a parallel groups
		SequentialGroup h1 = layout.createSequentialGroup();
		ParallelGroup h2 = layout
				.createParallelGroup(GroupLayout.Alignment.TRAILING);
		// Add a scroll panel and a label to the parallel group h2
		h2.addComponent(scrollPane, GroupLayout.Alignment.LEADING,
				GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE);
		h2.addComponent(textLabel, GroupLayout.Alignment.LEADING,
				GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE);

		// Add a container gap to the sequential group h1
		h1.addContainerGap();
		// Add the group h2 to the group h1
		h1.addGroup(h2);
		h1.addContainerGap();
		// Add the group h1 to hGroup
		hGroup.addGroup(Alignment.TRAILING, h1);
		// Create the horizontal group
		layout.setHorizontalGroup(hGroup);

		// Create a parallel group for the vertical axis
		ParallelGroup vGroup = layout
				.createParallelGroup(GroupLayout.Alignment.LEADING);
		// Create a sequential group
		SequentialGroup v1 = layout.createSequentialGroup();
		// Add a container gap to the sequential group v1
		v1.addContainerGap();
		// Add a label to the sequential group v1
		v1.addComponent(textLabel);
		v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		// Add scroll panel to the sequential group v1
		v1.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 80,
				Short.MAX_VALUE);
		v1.addContainerGap();
		// Add the group v1 to vGroup
		vGroup.addGroup(v1);
		// Create the vertical group
		layout.setVerticalGroup(vGroup);
		pack();
	}

	private void initializeTextArea() {
		textLabel = new JLabel("Text Here");
		smsText = new JTextArea("");
		smsText.setVisible(true);
		smsText.setColumns(20);
		smsText.setLineWrap(true);
		smsText.setRows(5);
		smsText.setWrapStyleWord(true);

		smsText.setEditable(false);
	}

	private JPanel addButtonsToPanel() {
		JPanel panelButtons = new JPanel(new GridLayout(4, 3));
		panelButtons.add(button1);
		panelButtons.add(button2);
		panelButtons.add(button3);
		panelButtons.add(button4);
		panelButtons.add(button5);
		panelButtons.add(button6);
		panelButtons.add(button7);
		panelButtons.add(button8);
		panelButtons.add(button9);
		panelButtons.add(_button);
		panelButtons.add(button0);
		panelButtons.add(button_);
		return panelButtons;
	}

	/*
	 * event handlers for buttons
	 */
	private class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			JButton clickedButton = (JButton) e.getSource();
			if (clickedButton == button1) {
				digitSequence.append("1");
			} else if (clickedButton == button2) {
				digitSequence.append("2");
			} else if (clickedButton == button3) {
				digitSequence.append("3");
			} else if (clickedButton == button4) {
				digitSequence.append("4");
			} else if (clickedButton == button5) {
				digitSequence.append("5");
			} else if (clickedButton == button6) {
				digitSequence.append("6");
			} else if (clickedButton == button7) {
				digitSequence.append("7");
			} else if (clickedButton == button8) {
				digitSequence.append("8");
			} else if (clickedButton == button9) {
				digitSequence.append("9");
			} else if (clickedButton == button0) {
				pressedZero();
				return;
			} else if (clickedButton == _button) {
				pressedStar();
				return;
			} else if (clickedButton == button_) {
//				[TODO] handle capital cases when pressed #
			}
			response = T9.getResponse(digitSequence.toString());
			showText();

		}

		private void pressedStar() {
			preferenceCount++;
			try {
				response = T9.notThisWord(digitSequence.toString(),
						preferenceCount);
			} catch (SequenceNotFoundException e1) {
				response = e1.getMessage();
			}
			showText();
		}

		private void pressedZero() {
			response = T9.getResponse(digitSequence.toString());
			if (currentString.isEmpty())
				currentString = response;
			else
				currentString = currentString + " " + response;
			smsText.setText(currentString + " ");
			digitSequence.delete(0, digitSequence.length());
			smsText.getCaret().setVisible(true);
			preferenceCount = 1;
		}

		private void showText() {
			smsText.setText(currentString + " " + response);
			System.out.println(digitSequence + " => " + response);
			smsText.getCaret().setVisible(true);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		T9GUI t9Gui = new T9GUI();
	}

}
