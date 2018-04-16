package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import FAtoRE.DSL;

/*
 * LoadFrame cria a interface se o parser da DSL for sucedido.
 * Faz display do grafo, demonstra a expressão regular resultante
 * e mostra os passos necessários para atingir essa expressão regular (se aplicável).
 */
public class LoadFrame extends JFrame {
	private static final long serialVersionUID = -9139346187976980289L;
	private DSL dsl;
	private Box box;
	private JButton loadButton, exitButton;
	private JPanel downLine, firstPanel, secondPanel, rePanel, stepsPanel;
	private JTextPane firstPane, secondPane, reTextPane, stepsPane;;

	private class MainContainer extends JPanel {
		private static final long serialVersionUID = 6915709672157377950L;
		private final int widthExcess = 12, heightExcess = 3;
		private Image graphImage;
		private Dimension size;

		public MainContainer(FlowLayout f) {
			super(f);
			graphImage = new ImageIcon(dsl.getGraphImageName()).getImage();

			size = new Dimension(graphImage.getWidth(null) - widthExcess,
					graphImage.getHeight(null) - heightExcess);

			this.setPreferredSize(size);
			this.setBackground(Color.WHITE);
			this.setFocusable(true);
		}

		public void paint(Graphics g) {
			g.drawImage(graphImage, 0, 0, null);
		}
	}

	public LoadFrame(DSL dsl) {
		this.dsl = dsl;

		loadButton = new JButton("Parse another DSL");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StartFrame();
			}
		});

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		box = Box.createVerticalBox();

		firstPane = new JTextPane();
		firstPane.setText("The automaton read from the .dot file:");
		firstPane.setEditable(false);
		firstPane.setFont(new Font("Myanmar Text", Font.BOLD, 15));

		firstPanel = new JPanel(new FlowLayout());
		firstPanel.setBackground(new Color(255, 255, 255));
		firstPanel.add(firstPane);

		box.add(firstPanel);

		MainContainer c = new MainContainer(new FlowLayout());
		c.setBackground(new Color(255, 255, 255));

		box.add(c);

		secondPane = new JTextPane();
		secondPane.setText("The regular expression after state elimination:");
		secondPane.setEditable(false);
		secondPane.setFont(new Font("Myanmar Text", Font.BOLD, 15));

		secondPanel = new JPanel(new FlowLayout());
		secondPanel.setBackground(new Color(255, 255, 255));
		secondPanel.add(secondPane);

		box.add(secondPanel);

		reTextPane = new JTextPane();
		reTextPane.setText(dsl.getRegularExpression());
		reTextPane.setEditable(false);
		reTextPane.setFont(new Font("Myanmar Text", Font.BOLD, 20));

		rePanel = new JPanel(new FlowLayout());
		rePanel.setBackground(new Color(255, 255, 255));
		rePanel.add(reTextPane);

		box.add(rePanel);

		if (dsl.getMinimizedExpression() != dsl.getRegularExpression()) {
			secondPane = new JTextPane();
			secondPane.setText("After simplification and parentheses removal:");
			secondPane.setEditable(false);
			secondPane.setFont(new Font("Myanmar Text", Font.BOLD, 15));

			secondPanel = new JPanel(new FlowLayout());
			secondPanel.setBackground(new Color(255, 255, 255));
			secondPanel.add(secondPane);

			box.add(secondPanel);

			reTextPane = new JTextPane();
			reTextPane.setText(dsl.getMinimizedExpression());
			reTextPane.setEditable(false);
			reTextPane.setFont(new Font("Myanmar Text", Font.BOLD, 20));

			rePanel = new JPanel(new FlowLayout());
			rePanel.setBackground(new Color(255, 255, 255));
			rePanel.add(reTextPane);

			box.add(rePanel);
		}

		if (dsl.getAST().getMode().get(0).equals("steps")) {
			stepsPane = new JTextPane();
			stepsPane.setText(dsl.getRegularExpression());
			stepsPane.setEditable(false);
			stepsPane.setFont(new Font("Myanmar Text", Font.PLAIN, 12));

			stepsPanel = new JPanel(new FlowLayout());
			stepsPanel.setBackground(new Color(255, 255, 255));
			stepsPanel.add(stepsPane);

			stepsPane.setText(dsl.getConversor().getSteps());

			box.add(stepsPanel);
		}

		downLine = new JPanel(new FlowLayout());
		downLine.setBackground(Color.WHITE);
		downLine.add(loadButton);
		downLine.add(exitButton);

		box.add(downLine);

		this.setContentPane(box);
		this.setTitle("DSL Result");
		this.setVisible(true);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}