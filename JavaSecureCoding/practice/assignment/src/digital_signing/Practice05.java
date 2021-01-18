package digital_signing;

import java.security.Key;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Practice05 {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = {'T', 'h', 'e', 'B', 'e', 's', 't', 
											'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y'};
	
	/**
	 * Generate a new encryption key.
	 */
	public static Key generateKey() throws Exception {
		return (new SecretKeySpec(keyValue, ALGO)); //return value: secretkey class
	}
	
	public static byte[] encrypt(String data, Key skey) throws Exception {
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, skey);
		byte[] encVal = c.doFinal(data.getBytes());
		return encVal;
	}
	
	public static String decrypt(byte[] encryptedData, Key skey) throws Exception {
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, skey);
		byte[] decValue = c.doFinal(encryptedData);
		return (new String(decValue));
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Key secretKey = generateKey();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("메시지를 입력하세요: ");
		String msg = scan.nextLine();
		
		byte[] encResult = encrypt(msg, secretKey);
		System.out.print("암호화 결과 : ");
		for (byte bytes : encResult) {
			System.out.print(String.format("%02x", bytes) + "-");
		}
		
		String decResult = decrypt(encResult, secretKey);
		System.out.println("\n복호화 결과 : " + decResult);
	}

}
