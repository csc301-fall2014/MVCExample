package csc301.mvcExample.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class GUI extends JFrame implements StockUpdateEventListener, ActionListener{

	private static final long serialVersionUID = 1L;

	private static final String PAUSE_LABEL = "Pause";
	private static final String RESUME_LABEL = "Resume";

	//-------------------------------------------------------------------------
	
	// Other components who will be listening to GUI event (e.g. Button clicked)
	Set<GUIEventListener> listeners;
	
	private JTextArea textArea;
	private JScrollPane textAreaScrollPane;
	private JButton pauseResumeButton;


	public GUI() {
		listeners = new HashSet<GUIEventListener>();
		initializeGUIComponents();
	}


	
	private void initializeGUIComponents(){
		setSize(320, 480);

		textArea = new JTextArea();
		textArea.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 16));
		textAreaScrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);

		pauseResumeButton = new JButton(PAUSE_LABEL);
		pauseResumeButton.addActionListener(this);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(pauseResumeButton, BorderLayout.NORTH);
		panel.add(textAreaScrollPane, BorderLayout.CENTER);
		this.add(panel);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	

	//-------------------------------------------------------------------------
	// Generate "outgoing" events (pause-clicked, resume-clicked)
	
	public void addEventListener(GUIEventListener listener){
		listeners.add(listener);
	}

	public void removeEventListener(GUIEventListener listener){
		listeners.remove(listener);
	}

	
	private void notifyPauseClicked(){
		for(GUIEventListener listener : listeners){
			listener.onPauseClicked();
		}
	}

	private void notifyResumeClicked(){
		for(GUIEventListener listener : listeners){
			listener.onResumeClicked();
		}
	}
	
	
	//-------------------------------------------------------------------------
	// Handle "incoming" GUI-action events, such as button-click.
	
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource().equals(pauseResumeButton)){
			handlePauseResumeButtonClicked();
		}
	}	


	private void handlePauseResumeButtonClicked(){
		if(pauseResumeButton.getText().equals(PAUSE_LABEL)){
			notifyPauseClicked();
			pauseResumeButton.setText(RESUME_LABEL);
		} else {
			notifyResumeClicked();
			pauseResumeButton.setText(PAUSE_LABEL);
		}
	}

	
	//-------------------------------------------------------------------------
	// Handle "incoming" stock-update events.
	
	@Override
	public void onStockUpdate(String stockId, BigDecimal currentPrice, boolean hasGoneUp, BigDecimal percentChanged) {
		String text = String.format("%s %s (%s%s%%)", stockId, currentPrice.toString(), 
				(hasGoneUp ? "+" : "-"), percentChanged.toString());
		this.textArea.insert(text + "\n", 0);
	}


}
