package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private static final long serialVersionUID = 9071498822176117437L;

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	private JPanel panel;
	private JLabel userNameLabel, comp1Label, comp2Label, comp3Label;
	private JTextField userName, comp1Name, comp2Name, comp3Name;
	private JButton play;
	private JPanel GraphicsPanel;

	public GUI() {
		layoutPreGame();
	}

	public void layoutPreGame() {
		this.setTitle("Hearts knockoff");
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		panel = new JPanel();
		panel.setVisible(true);
		panel.setBounds(0, 0, 400, 300);
		panel.setLayout(null);
		userNameLabel = new JLabel("Enter Your Name: ");
		userNameLabel.setBounds(25, 0, 200, 25);
		userName = new JTextField();
		userName.setBounds(25, 25, 200, 25);
		userName.setText("Human Player");
		comp1Label = new JLabel("Enter Computer 1 Name: ");
		comp1Label.setBounds(25, 50, 200, 25);
		comp1Name = new JTextField();
		comp1Name.setBounds(25, 75, 200, 25);
		comp1Name.setText("Player 2");
		comp2Label = new JLabel("Enter Computer 2 Name: ");
		comp2Label.setBounds(25, 100, 200, 25);
		comp2Name = new JTextField();
		comp2Name.setBounds(25, 125, 200, 25);
		comp2Name.setText("Player 3");
		comp3Label = new JLabel("Enter Computer 3 Name: ");
		comp3Label.setBounds(25, 150, 200, 25);
		comp3Name = new JTextField();
		comp3Name.setBounds(25, 175, 200, 25);
		comp3Name.setText("Player 4");
		play = new JButton("Play");
		play.setBounds(25, 215, 100, 25);
		play.addActionListener(new ButtonLitsener());
		panel.add(userNameLabel);
		panel.add(userName);
		panel.add(comp1Label);
		panel.add(comp1Name);
		panel.add(comp2Label);
		panel.add(comp2Name);
		panel.add(comp3Label);
		panel.add(comp3Name);
		panel.add(play);

		add(panel);

	}

	public class ButtonLitsener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == play) {
				// Check that all entries are not nothing, and that they are all different:
				if (userName.getText().equals("")|| comp1Name.getText().equals("") || comp2Name.getText().equals("") || comp3Name.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter more information ");
				}
				else if (userName.getText().equals(comp1Name.getText()) || userName.getText().equals(comp2Name.getText()) || userName.getText().equals(comp1Name.getText()) || userName.getText().equals(comp3Name.getText()) || comp1Name.getText().equals(comp2Name.getText()) || comp1Name.getText().equals(comp3Name.getText()) || comp3Name.getText().equals(comp2Name.getText())) {
					JOptionPane.showMessageDialog(null, "Enter unique names ");
				}
				else {
					
					GraphicsPanel = new GraphicsPanel(userName.getText(), comp1Name.getText(), comp2Name.getText(), comp3Name.getText());
					
					layoutThisJFrame();

				}
			}
		}
	}

	public void layoutThisJFrame() {
		this.remove(panel);
		this.setTitle("Hearts knockoff");
		this.setSize(1100, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		GraphicsPanel.setVisible(true);
		GraphicsPanel.setLayout(null);
		GraphicsPanel.setBackground(Color.GREEN);
		GraphicsPanel.setBounds(0, 0, 1100, 900);
		add(GraphicsPanel);

	}

}
