package ui;

import ui.view.MainView;

public class Launcher {

	public static void main(String[] args) {
		
		//new Menu().showMenu();
		MainView view = new MainView();
		view.setVisible(true);
	}

}
