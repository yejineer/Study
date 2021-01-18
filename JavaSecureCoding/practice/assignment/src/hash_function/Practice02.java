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
		/* 파일 내용을 byte[] 형태로 읽기 */
		byte[] b1 = fileToByte(fname1);
		byte[] b2 = fileToByte(fname2);
		
		/* 파일 내용이 같은지 확인 */
		boolean same = true;
		if(b1.length != b2.length) 
			same = false;
		else {
			for(int i = 0; i < b1.length; i++) {
				if(b1[i] != b2[i])
					same = false;
			}	
		}
//		/* 파일 내용 같은지 다른지 출력 */
//		if (same == true) { // 두 인자 모두 null인 경우도 true (같은 내용의 파일) 
//			System.out.println(fname1 + "과 " + fname2 + "는 같은 내용의 파일입니다.\n");	
//		} else {
//			System.out.println(fname1 + "과 " + fname2 + "는 다른 내용의 파일입니다.\n");
//		}
		
		/* 각 파일의 해쉬 값 비교 */
		if (compareMessageDigest(b1, b2)) {
//			System.out.println("\n" + fname1 + "과 " + fname2 + "의 해시 값은 같습니다.");
			return true;
		} else { 
//			System.out.println("\n" + fname1 + "과 " + fname2 + "의 해시 값은 다릅니다.");
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
		byte[] messageDigestMD5 = messageDigest.digest(); //해시값
		
		/* 파일 내용과 해시 값 출력 */
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
			System.out.println(filename + " 파일을 찾을 수 없습니다!");
			return null;
		}
	}

}
