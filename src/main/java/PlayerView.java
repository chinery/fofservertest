

import java.awt.Font;

import javax.swing.*;
import javax.swing.GroupLayout.*;


public class PlayerView extends JFrame{

	private JScrollPane jScrollPane1;
	private JTextArea textArea;
	private int turnsTaken;

	public PlayerView(String name ) {
		super("Group " + name);
		turnsTaken = 0;
		initComponents();  
	}

	public void updatePlayerView(String points) {
		String top = ("Group No\tGOLD\tCOPPER\tALU\tMAG\tNICKEL\tSILICON\tBonusPoints\tTotal Points\tTurns Taken\n");
		turnsTaken++;
		textArea.setText(top + "Group " + points + "\t\t" + turnsTaken);
	}


	private void initComponents() {

		textArea = new JTextArea();
		textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		textArea.setColumns(20);
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setWrapStyleWord(true);
		textArea.setText(("Group No\tGOLD\tCOPPER\tALU\tMAG\tNICKEL\tSILICON\tBonusPoints\tTotal Points\tTurns Taken\n"));

		jScrollPane1 = new JScrollPane(textArea);

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

		this.setSize(900, 80);

	}    

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				new PlayerView("1").setVisible(true);
			}
		});
	}


}

