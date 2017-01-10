package readingProject;

public class PreTesting {

	public static void main(String[] args) throws InterruptedException {
		
		BasicFrame basicFrame = new BasicFrame();
		Thread.sleep(1500);

		Thread loginThread = new Thread(new LoginPanel(basicFrame));
		loginThread.start();
	}
}
