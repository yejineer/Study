package hash_function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Practice01 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String data = "This is a message to be digested using MD5!";
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data.getBytes());
		byte[] messageDigestMD5 = messageDigest.digest(); //ÇØ½Ã°ª
		
		System.out.println("length of digest (bytes): " + messageDigestMD5.length);
		
		System.out.println("data: " + data);
		System.out.println("digestMD5(hex): ");
		for (byte bytes : messageDigestMD5) {
			System.out.print(String.format("%02x", bytes) + "\t");
		}
	}

}
