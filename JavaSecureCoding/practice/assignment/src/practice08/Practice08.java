package practice08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Practice08 {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		/* KeyPair ���� */
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();

		PublicKey publicKey = pair.getPublic();
		PrivateKey privateKey = pair.getPrivate();

		/* ������ KeyPair�� ��ü����ȭ�� �̿��Ͽ� ���Ͽ� �����ϰ� �о� ���̱� */
		String publicKeyFile = "PublicKey.txt";
		String privateKeyFile = "PrivateKey.txt";
		
		savePublicKey(publicKey, publicKeyFile);
		PublicKey publicKeyFromFile = restorePublicKey(publicKeyFile);
		savePrivateKey(privateKey, privateKeyFile);
		PrivateKey privateKeyFromFile = restorePrivateKey(privateKeyFile);

		/* ������ KeyPair�� �� KeyPair�� ��ü����ȭ�� �̿��� ���Ͽ� �����ϰ� �ҷ��� KeyPair�� ��ȣȭ�ϰ� ��ȣȭ�� ��� �� */
		String plainTxt = "Software Security of Dongduk";
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			byte[] cipherTxt1 = encrypt(cipher, plainTxt, publicKey);
			byte[] cipherTxt2 = encrypt(cipher, plainTxt, publicKeyFromFile);
			System.out.println("2���� key pair�� ��ȣȭ/��ȣȭ ��� ���� ������?: " 
							+ Arrays.equals(decrypt(cipher, cipherTxt1, privateKey), decrypt(cipher, cipherTxt2, privateKeyFromFile)));
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
	}

	static void savePublicKey(PublicKey publicKey, String filename) {
		try (FileOutputStream fstream = new FileOutputStream(filename)) {
			try (ObjectOutputStream ostream = new ObjectOutputStream(fstream)) {
				ostream.writeObject(publicKey);
			}
		} catch (IOException e) {
			System.out.println("[IOException] in savePublicKey()");
			e.printStackTrace();
		}
	}

	static PublicKey restorePublicKey(String filename) {
		PublicKey publicKey = null;
		try (FileInputStream fis = new FileInputStream(filename)) {
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				Object obj = ois.readObject(); // �о Object�� ��ȯ
				publicKey = (PublicKey) obj; // �ڵ� ����ȯ�� �� ��. ���� ��������ȯ ����� ��.
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return publicKey;

	}

	static void savePrivateKey(PrivateKey privateKey, String filename) {
		try (FileOutputStream fstream = new FileOutputStream(filename)) {
			try (ObjectOutputStream ostream = new ObjectOutputStream(fstream)) {
				ostream.writeObject(privateKey);
			}
		} catch (FileNotFoundException e) {
			System.out.println("[FileNotFoundException] in savePublicKey()");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[IOException] in savePublicKey()");
			e.printStackTrace();
		}
	}

	static PrivateKey restorePrivateKey(String filename) {
		PrivateKey privateKey = null;
		try (FileInputStream fis = new FileInputStream(filename)) {
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				Object obj = ois.readObject(); // �о Object�� ��ȯ
				privateKey = (PrivateKey) obj; // �ڵ� ����ȯ�� �� ��. ���� ��������ȯ ����� ��.
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return privateKey;

	}

	static byte[] encrypt(Cipher cipher, String plainTxt, PublicKey publicKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		byte[] input = plainTxt.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		cipher.update(input);
		byte[] cipherTxt = cipher.doFinal();
		
		return cipherTxt;
	}
	
	static byte[] decrypt(Cipher cipher, byte[] cipherTxt, PrivateKey privateKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decipheredTxt = cipher.doFinal(cipherTxt);
		
		return decipheredTxt;
	}
}
