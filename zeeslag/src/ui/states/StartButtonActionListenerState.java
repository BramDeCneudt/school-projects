package ui.states;

import java.awt.Button;

import ui.Controller;
import ui.actionlisteners.StartButtonActionListener;
import ui.view.TargetView;
import ui.view.VlootView;

public interface StartButtonActionListenerState {
	
	public void teDoen(Controller controller, Button startButton, StartButtonActionListener listener, VlootView vlootview, TargetView targetView);
}
