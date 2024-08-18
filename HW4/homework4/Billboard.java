package homework4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;


/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
public class Billboard extends JFrame implements ActionListener {

	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;

    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    private static final int DELAY = 2000;

	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, strategyMenu, helpMenu;
	private JMenuItem newItem, exitItem,
						AscendStrategyItem, ColStrategyItem, OddEvenStrategyItem, RandomStrategyItem, 
                        aboutItem;
	private JPanel mainPanel;

	// shapes that have been added to this
	private ColorGenerator generator = ColorGenerator.getInstance();

	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation
	 * 			of all shapes in this 25 times per second while animation
	 * 			checkbox is selected.
	 */
	public Billboard() {
		super("Billboard");

		// create main panel and menubar
		mainPanel = (JPanel)createMainPanel();
		getContentPane().add(mainPanel);
		menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);
        
        mainPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

        for(int i = 0; i < NUM_ROWS; i++) {
            for(int j = 0; j < NUM_COLS; j++) {
                Panel curPanel = new Panel();
                generator.addObserver(curPanel);
                mainPanel.add(curPanel);
            }
        }
        setVisible(true);

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                generator.updateColor();
            }
        });
        timer.start();
	}


	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
    	JPanel mainPanel = new JPanel();
    	mainPanel.setPreferredSize(
    			new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    	mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
    	mainPanel.setBackground(Color.WHITE);

    	return mainPanel;
	}


	/**
	 * @return main GUI menubar.
	 */
	private JMenuBar createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();

    	fileMenu = new JMenu("File");
    	exitItem = new JMenuItem("Exit");
    	exitItem.addActionListener(this);
    	fileMenu.add(exitItem);
    	menuBar.add(fileMenu);

    	strategyMenu = new JMenu("Strategy");
    	AscendStrategyItem = new JMenuItem("Ascending");
    	AscendStrategyItem.addActionListener(this);
    	strategyMenu.add(AscendStrategyItem);
    	ColStrategyItem = new JMenuItem("Column");
    	ColStrategyItem.addActionListener(this);
    	strategyMenu.add(ColStrategyItem);
    	OddEvenStrategyItem = new JMenuItem("Odd Even");
    	OddEvenStrategyItem.addActionListener(this);
    	strategyMenu.add(OddEvenStrategyItem);
    	RandomStrategyItem = new JMenuItem("Random");
    	RandomStrategyItem.addActionListener(this);
    	strategyMenu.add(RandomStrategyItem);
    	menuBar.add(strategyMenu);

    	helpMenu = new JMenu("Help");
    	aboutItem = new JMenuItem("About");
    	aboutItem.addActionListener(this);
    	helpMenu.add(aboutItem);
    	menuBar.add(helpMenu);

    	return menuBar;
	}


	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menubar
	 * 			and performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		// File->Exit: close application
		if (source.equals(exitItem)) {
        	dispose();
        }

        else if(source.equals(AscendStrategyItem)){
            generator.setStrategy(new ColorAscendStrategy(NUM_ROWS, NUM_COLS));
        }
        else if(source.equals(OddEvenStrategyItem)){
            generator.setStrategy(new ColorOddEvenStrategy(NUM_ROWS, NUM_COLS));
        }
        else if(source.equals(ColStrategyItem)){
            generator.setStrategy(new ColorColAscendStrategy(NUM_ROWS, NUM_COLS));
        }
        else if(source.equals(RandomStrategyItem)){
            generator.setStrategy(new ColorRandomStrategy(NUM_ROWS, NUM_COLS));
        }

		// Help->About : show about message dialog
		else if (source.equals(aboutItem)){
			JOptionPane.showMessageDialog(
					this,
					"Animator - 1st" +
					" homework assignment",
					"About",
					JOptionPane.INFORMATION_MESSAGE);
		}
    }
    


	/**
	 * @effects Animator application.
	 */
	public static void main(String[] args) {
        Billboard application = new Billboard();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
	}
}
