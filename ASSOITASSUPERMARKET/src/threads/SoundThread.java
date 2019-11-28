package threads;

import controllerWindow.WindowController;

public class SoundThread extends Thread{
	private WindowController win;
	public SoundThread(WindowController win) {
		this.win = win;
	}
	public void run() {
		win.startExit();
	}
}
