package cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Practice03A {

	public static void main(String[] args) throws Exception {

		// Creating KeyPair generator object
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		// Getting the public key from the key pair
		PublicKey publicKey = pair.getPublic();

		// Creating a Cipher object
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		// Initializing a Cipher object
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		// Adding data to the cipher
		String plainTxt = "Software Security of Dongduk";
		byte[] input = plainTxt.getBytes();
		cipher.update(input);	//String이든 int든 byte형 배열로 바꿔준 다음에 update() 호출
		// Encrypting the data
		byte[] cipherTxt = cipher.doFinal();

		// Get the private key
		PrivateKey privateKey = pair.getPrivate();
		//Initializing the same cipher for decryption
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		//Decrypting the text
		byte[] decipheredTxt = cipher.doFinal(cipherTxt);

		System.out.println("Length of Input: " + input.length);
		System.out.println("Length of Encrypted Data: " + cipherTxt.length);
		System.out.println("[Encrypted Data]");
		for (int i = 0; i < cipherTxt.length; i++) {
			System.out.print(String.format("%02x", cipherTxt[i]) + "\t");
			if (i % 15 == 14)
				System.out.println();
		}
		System.out.println("\n[Decrypted Data]");
		System.out.println(new String(decipheredTxt));

	}

}
