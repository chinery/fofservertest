

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class GameLogicView extends JFrame{

	private JScrollPane jScrollPane1;
	private JTextPane textPane;

	private Color[] colours = {Color.WHITE, Color.RED, Color.CYAN, Color.GREEN, Color.ORANGE};
	private Style[] styles = new Style[colours.length];

	private boolean badGlobalBoolean = true;
	
	public GameLogicView() {
		super("Game View");
		initComponents();  
	}

	public void updateMap(String[][] map, int currentPlayer) {
		StyledDocument doc = new DefaultStyledDocument(); //textPane.getStyledDocument();

		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j] == null) {
					insertTextColour(doc, 0, ".");
				}
				else if(map[i][j].matches("[1-4]")) {
					int player = Integer.parseInt(map[i][j]);
					if(player == currentPlayer) {
						Color bg = StyleConstants.getBackground(styles[player]);
						if(badGlobalBoolean = !badGlobalBoolean) {
							StyleConstants.setBackground(styles[player], Color.WHITE);
						}else {
							StyleConstants.setBackground(styles[player], Color.DARK_GRAY);
						}
					}
					insertTextColour(doc, player, map[i][j]);
					StyleConstants.setBackground(styles[player], Color.BLACK);
				}
				else {
					insertTextColour(doc, 0, map[i][j]);
				}
			}
			insertTextColour(doc, 0, "\n");
		}
		textPane.setDocument(doc);
	}

	private void insertTextColour(StyledDocument doc, int colour, String text) {
		try { doc.insertString(doc.getLength(), text, styles[colour]); }
		catch (BadLocationException e){}
	}


	private void initComponents() {
		textPane = new JTextPane();
		textPane.setFont(new Font("monospaced", Font.PLAIN, 10));
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		for(int i = 0; i < colours.length; i++) {
			styles[i] = textPane.addStyle("style"+i, null);
			StyleConstants.setForeground(styles[i], colours[i]);
			StyleConstants.setBackground(styles[i], Color.BLACK);
			if(i > 0) {
				StyleConstants.setBold(styles[i], true);
//				StyleConstants.setBackground(styles[i], Color.LIGHT_GRAY);
			}
		}

		jScrollPane1 = new JScrollPane(textPane);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		//Create a parallel group for the horizontal axis
		ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		//Create a sequential and a parallel groups
		SequentialGroup h1 = layout.createSequentialGroup();
		ParallelGroup h2 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
		//Add a scroll panel and a label to the parallel group h2
		h2.addComponent(jScrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);

		//Add a container gap to the sequential group h1
		h1.addContainerGap();
		// Add the group h2 to the group h1
		h1.addGroup(h2);
		h1.addContainerGap();
		//Add the group h1 to hGroup
		hGroup.addGroup(Alignment.TRAILING,h1);
		//Create the horizontal group
		layout.setHorizontalGroup(hGroup);

		//Create a parallel group for the vertical axis
		ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		//Create a sequential group
		SequentialGroup v1 = layout.createSequentialGroup();
		//Add a container gap to the sequential group v1
		v1.addContainerGap();
		v1.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED);
		//Add scroll panel to the sequential group v1
		v1.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
		v1.addContainerGap();
		//Add the group v1 to vGroup
		vGroup.addGroup(v1);
		//Create the vertical group
		layout.setVerticalGroup(vGroup);
		pack();

		this.setSize(1600, 900);

	}    

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				new GameLogicView().setVisible(true);
			}
		});
	}


}
