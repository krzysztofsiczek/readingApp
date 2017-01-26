package readingProject.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hashing {

	private String passwordToDeHashed;
	private String hashedPassword;

	public SHA256Hashing(String passwordToDeHashed) {
		this.passwordToDeHashed = passwordToDeHashed;
	}

	public String encrypt() throws NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(passwordToDeHashed.getBytes());
		
		byte byteData[] = messageDigest.digest();
		
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i = 0; i < byteData.length; i++){
			stringBuffer.append(Integer.toString((byteData[i & 0xff]) + 0x100, 16).substring(1));
		}
		
		hashedPassword = stringBuffer.toString();
		
		return hashedPassword;
	}
}
