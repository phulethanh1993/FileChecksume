package phult.view;

import java.awt.Color;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import phult.process.Calculator;

public class View extends JFrame {
	private static final long serialVersionUID = -2823890875948920485L;
	private Calculator calculator;
	private JButton browse;
	private JTextField directory;
	private JButton calculate;
	private JLabel md5Label;
	private TextField md5Value;
	private JLabel sha1Label;
	private TextField sha1Value;

	public View() {
		initPanel();
		initComponents();

	}

	public void initComponents() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.WHITE);
		this.add(jPanel);
		calculator = new Calculator(this);
		browse = new JButton("Choose");
		browse.setLocation(10, 20);
		browse.setSize(80, 25);
		browse.setActionCommand("browse");
		browse.addActionListener(calculator);
		jPanel.add(browse);

		directory = new JTextField();
		directory.setLocation(105, 20);
		directory.setSize(280, 25);
		jPanel.add(directory);

		calculate = new JButton("Calculate");
		calculate.setActionCommand("calculate");
		calculate.setLocation(150, 75);
		calculate.setSize(100, 30);
		calculate.addActionListener(calculator);
		jPanel.add(calculate);

		md5Label = new JLabel("MD5 :");
		md5Label.setBounds(10, 120, 40, 30);
		jPanel.add(md5Label);

		sha1Label = new JLabel("SHA1 :");
		sha1Label.setBounds(10, 160, 40, 30);
		jPanel.add(sha1Label);

		md5Value = new TextField();
		md5Value.setBounds(60, 120, 325, 26);
		md5Value.setEditable(false);
		jPanel.add(md5Value);

		sha1Value = new TextField();
		sha1Value.setBounds(60, 160, 325, 26);
		sha1Value.setEditable(false);
		jPanel.add(sha1Value);
	}

	public void initPanel() {
		setBackground(Color.WHITE);
		setTitle("FileChecksum");
		setSize(400, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public String getPathFile() {
		JFileChooser jFileChooser = new JFileChooser();
		int rVal = jFileChooser.showOpenDialog(this);
		String path = "";

		if (rVal == JFileChooser.APPROVE_OPTION) {
			path = jFileChooser.getCurrentDirectory().toString() + "\\" + jFileChooser.getSelectedFile().getName();
		}
		return path;
	}

	public void setText(int value, String text) {
		switch (value) {
		case 1:
			this.directory.setText(text);
			break;
		case 2:
			this.md5Value.setText(text);
			break;
		case 3:
			this.sha1Value.setText(text);
			break;
		default:
			break;
		}
	}

	public String getText() {
		return this.directory.getText();
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

}
