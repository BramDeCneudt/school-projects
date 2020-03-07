package ui.states;

import java.awt.Button;

import ui.Controller;
import ui.actionlisteners.StartButtonActionListener;
import ui.view.TargetView;
import ui.view.VlootView;

public class ClickToStartState implements StartButtonActionListenerState {

	@Override
	public void teDoen(Controller controller, Button startButton, StartButtonActionListener listener, VlootView vlootview, TargetView targetView) {
		controller.AIPlaatsSchip();
		listener.switchState();
		startButton.setLabel("Reset");
		startButton.setEnabled(true);
		controller.startSpel();
	}
}