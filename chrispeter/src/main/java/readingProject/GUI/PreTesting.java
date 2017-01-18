package readingProject.GUI;

import readingProject.SessionFactoryInstance;

public class PreTesting {

	private static final int ONE_SECOND = 1000;
	
	public static void main(String[] args) throws InterruptedException {

		BasicFrame basicFrame = new BasicFrame();
		Thread.sleep(ONE_SECOND);

		Thread loginThread = new Thread(new LoginPanel(basicFrame));
		loginThread.start();
	}
}
