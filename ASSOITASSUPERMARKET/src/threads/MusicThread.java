package threads;

import model.SuperMarketApp;

public class MusicThread extends Thread{
	private SuperMarketApp superMarket;
	public MusicThread(SuperMarketApp superMarket) {
		this.superMarket = superMarket;
	}
	public void run() {
		superMarket.playCrazyFrog();
	}
}
