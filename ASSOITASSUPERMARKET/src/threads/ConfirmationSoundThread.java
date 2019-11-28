package threads;

import controllerWindow.WindowController;

public class ConfirmationSoundThread extends Thread{
	private WindowController win;
	public ConfirmationSoundThread(WindowController win) {
		this.win = win;
	}
	public void run() {
		win.startNotification();
	}

}
