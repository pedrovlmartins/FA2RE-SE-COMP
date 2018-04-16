package Interface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import FAtoRE.DSL;
import Parser.SELexer;
import Parser.SEParser;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Cria a interface inicial para carregamento da DSL e o lançamento de erros do parser.
 */
public class StartFrame extends JFrame {
	private static final long serialVersionUID = -4929696559457296676L;
	private Box box;
	private JPanel downLine;
	private JButton loadButton, runButton, exitButton;
	private JTextArea textArea, errorArea;
	private JLabel errorReporting;
	private JPanel panel, panel1, panel2, panel3, panel4;
	private JTextPane firstPane, secondPane;
	private File selectedFile = null;
	private PrintStream con = null;

	public StartFrame() {
		box = Box.createVerticalBox();

		this.setContentPane(box);

		panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		box.add(panel1);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		firstPane = new JTextPane();
		firstPane
				.setText("Please load the DSL with the rules defined for state elimination. \r\nThe DSL shall be in the following format:");
		firstPane.setEditable(false);
		firstPane.setFont(new Font("Myanmar Text", Font.BOLD, 14));

		StyledDocument doc = firstPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, 70, center, false);

		panel1.add(firstPane);

		panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		box.add(panel2);

		secondPane = new JTextPane();
		secondPane
				.setText("StateElimination {\r\n    FileName: <name>;  // string with at least one char, must end with .dot.\r\n    Mode: <mode>; // either \"default\" or \"steps\".\r\n    Type: <type>; // either \"automatic\" or \"manual\".\r\n    Ordering: <st1>, <st2>, <st3>...; // only if type is \"manual\".\r\n}");
		secondPane.setBackground(new Color(255, 255, 255));
		secondPane.setEditable(false);
		panel2.add(secondPane);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		box.add(panel);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setColumns(50);
		textArea.setRows(7);
		panel.add(textArea);

		loadButton = new JButton("Parse DSL");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userDir = System.getProperty("user.home");
				JFileChooser fileChooser = new JFileChooser(userDir
						+ "/Desktop");
				int returnValue = fileChooser.showOpenDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();

					FileInputStream fstream = null;
					try {
						fstream = new FileInputStream(selectedFile
								.getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					BufferedReader br = new BufferedReader(
							new InputStreamReader(fstream));

					String strLine, result = "";

					try {
						while ((strLine = br.readLine()) != null) {
							result += strLine + "\n";
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					try {
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					textArea.setText(null);
					errorArea.setText(null);

					con = new PrintStream(new TextAreaOutputStream(errorArea));

					// System.setOut(con);
					System.setErr(con);

					CharStream in = new ANTLRInputStream(result);
					SELexer lexer = new SELexer(in);
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					SEParser parser = new SEParser(tokens);
					parser.start();

					if (TextAreaOutputStream.flag == 0) {
						panel.setBorder(BorderFactory.createLineBorder(
								Color.GREEN, 1, true));
						errorArea.setVisible(false);
						panel4.setVisible(false);
						errorReporting
								.setText("The file has been successfully parsed.");
						loadButton.setText("Parse another DSL");
						panel3.setVisible(true);
						runButton.setEnabled(true);
						pack();
					} else {
						panel.setBorder(BorderFactory.createLineBorder(
								Color.RED, 1, true));
						errorArea.setVisible(true);
						panel4.setVisible(true);
						errorReporting
								.setText("The parser has encountered some errors. Please fix them:");
						loadButton.setText("Parse DSL");
						panel3.setVisible(true);
						runButton.setEnabled(false);
						pack();
						TextAreaOutputStream.flag = 0;
					}

					textArea.setText(result);
				}
			}
		});

		runButton = new JButton("Run DSL");
		runButton.setEnabled(false);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DSL dsl = new DSL(textArea.getText());

				if (dsl.evaluate() == true) {
					dispose();
					new LoadFrame(dsl);
				}
			}
		});

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		panel3.setVisible(false);
		box.add(panel3);

		errorReporting = new JLabel();
		panel3.add(errorReporting);

		panel4 = new JPanel();
		panel4.setBackground(Color.WHITE);
		panel4.setVisible(false);
		box.add(panel4);

		errorArea = new JTextArea();
		errorArea.setEditable(false);
		errorArea.setVisible(false);
		errorArea.setColumns(50);
		errorArea.setRows(7);
		panel4.add(errorArea);

		downLine = new JPanel(new FlowLayout());
		downLine.setBackground(Color.WHITE);
		downLine.add(loadButton);
		downLine.add(runButton);
		downLine.add(exitButton);

		box.add(downLine);

		this.setTitle("Finite Automata -> Regular Expression @ COMP'16");
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new StartFrame();
	}
}
