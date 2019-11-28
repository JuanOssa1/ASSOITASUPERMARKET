package threads;

import controllerWindow.WindowController;
import model.SuperMarketApp;

public class MusicThread extends Thread{
	private WindowController win;
	public MusicThread(WindowController win) {
		this.win = win;
	}
	public void run() {
		win.startMusic();
	}
}
