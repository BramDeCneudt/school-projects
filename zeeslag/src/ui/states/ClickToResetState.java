package ui.states;

import java.awt.Button;

import ui.Controller;
import ui.actionlisteners.StartButtonActionListener;
import ui.view.TargetView;
import ui.view.VlootView;

public class ClickToResetState implements StartButtonActionListenerState {

	@Override
	public void teDoen(Controller controller, Button startButton, StartButtonActionListener listener, VlootView vlootview, TargetView targetView) {
		if (controller.isSpelGestopt()) {
				controller.setNieuwSpel();
				listener.switchState();
				startButton.setLabel("Start");
				startButton.setEnabled(true);
		}

	}

}