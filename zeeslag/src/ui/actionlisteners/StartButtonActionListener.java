package ui.actionlisteners;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.Controller;
import ui.states.ClickToResetState;
import ui.states.ClickToStartState;
import ui.states.StartButtonActionListenerState;
import ui.view.TargetView;
import ui.view.VlootView;

public class StartButtonActionListener implements ActionListener{

	private Controller controller;
	private TargetView targetView;
	private VlootView vlootview;
	private Button startButton;
	
	//states
	private StartButtonActionListenerState clickToStartState;
	private StartButtonActionListenerState clickToResetState;
	private StartButtonActionListenerState currentstate;
	
	 public StartButtonActionListener(Controller controller,TargetView targetView, VlootView vlootview, Button startButton) {
		 this.controller = controller;
		 this.targetView = targetView;
		 this.vlootview = vlootview;
		 this.clickToStartState = new ClickToStartState();
		 this.clickToResetState = new ClickToResetState();
		 this.currentstate = clickToStartState;
		 this.startButton = startButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		this.targetView.setEnabled(true);
		this.vlootview.setEnabled(false);
		this.currentstate.teDoen(controller, startButton, this, vlootview, targetView);
	}

	public void switchState(){
		if (this.currentstate == this.clickToStartState){
			this.currentstate = this.clickToResetState;
		}else {
			this.currentstate = this.clickToStartState;
		}
	}
}
