package csc301.mvcExample.view;

/**
 * Classes that implement this interface can be attached as listeners to
 * our GUI, and act upon GUI events.
 */
public interface GUIEventListener {

	public void onPauseClicked();
	public void onResumeClicked();
	
}
