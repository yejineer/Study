package hash_function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Practice02 {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String f1 = "C:\\Temporary Workspace\\copy_ver_1.unknown";
		String f2 = "C:\\Temporary Workspace\\copy_ver_2.unknown"; 
		String f3 = "C:\\Temporary Workspace\\copy_ver_3.unknown"; 
		
		System.out.println(checkIdentity(f1, f2)); // false
		System.out.println(checkIdentity(f1, f3)); // true
		System.out.println(checkIdentity(f2, f3)); // false
	}
	
	public static boolean checkIdentity(String fname1, String fname2) throws IOException, NoSuchAlgorithmException {
		/* ���� ������ byte[] ���·� �б� */
		byte[] b1 = fileToByte(fname1);
		byte[] b2 = fileToByte(fname2);
		
		/* ���� ������ ������ Ȯ�� */
		boolean same = true;
		if(b1.length != b2.length) 
			same = false;
		else {
			for(int i = 0; i < b1.length; i++) {
				if(b1[i] != b2[i])
					same = false;
			}	
		}
//		/* ���� ���� ������ �ٸ��� ��� */
//		if (same == true) { // �� ���� ��� null�� ��쵵 true (���� ������ ����) 
//			System.out.println(fname1 + "�� " + fname2 + "�� ���� ������ �����Դϴ�.\n");	
//		} else {
//			System.out.println(fname1 + "�� " + fname2 + "�� �ٸ� ������ �����Դϴ�.\n");
//		}
		
		/* �� ������ �ؽ� �� �� */
		if (compareMessageDigest(b1, b2)) {
//			System.out.println("\n" + fname1 + "�� " + fname2 + "�� �ؽ� ���� �����ϴ�.");
			return true;
		} else { 
//			System.out.println("\n" + fname1 + "�� " + fname2 + "�� �ؽ� ���� �ٸ��ϴ�.");
			return false;
		}
	}
	
	private static boolean compareMessageDigest(byte[] data1, byte[] data2) throws NoSuchAlgorithmException {
		byte[] b1 = digestFile(data1);
		byte[] b2 = digestFile(data2);
		
		if (b1.length != b2.length) return false;
		for(int i = 0; i < b1.length; i++) {
			if(b1[i] != b2[i])
				return false;
		}
		return true;
	}
	
	private static byte[] digestFile(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(data);
		byte[] messageDigestMD5 = messageDigest.digest(); //�ؽð�
		
		/* ���� ����� �ؽ� �� ��� */
//		System.out.println("data: " + data);
//		System.out.println("digestMD5(hex): ");
//		for (byte bytes : messageDigestMD5) {
//			System.out.print(String.format("%02x", bytes) + "\t");
//		}
//		System.out.println();
		return messageDigestMD5;
	}
	
	private static byte[] fileToByte(String filename) throws IOException {
		File file = new File(filename);
		FileInputStream in;
		
		try {
			in = new FileInputStream(file);	
			
			long byteLength = file.length();
			byte[] data = new byte[(int)byteLength];
			
			in.read(data, 0, (int)byteLength);
			
			if (in != null) {
				in.close();
			}
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(filename + " ������ ã�� �� �����ϴ�!");
			return null;
		}
	}

}
