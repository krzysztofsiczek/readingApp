package readingProject;

import java.awt.EventQueue;

public class PreTesting {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BasicFrame();
			}
		});
	}
}
