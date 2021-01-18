package digital_signing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class Practice07 {

	final String keyAlgorithm = "RSA";
	final static String signAlgorithm = "SHA1withRSA";
	static String filename = "C:\\Temporary Workspace\\sample_text_signing.txt";
	
	public static void main(String[] args) throws Exception {
		
		/* File �о�� byte[]�� ���� */
		Path path = (new File(filename)).toPath();
		byte[] data = Files.readAllBytes(path);	
		
		/* key �߱� */
		MyKeyPair keyPair = MyKeyPair.getInstance(1024);
		keyPair.createKeys();
		
		/* ����Ű �̿��� ���� ���� ����*/
		byte[] signature = sign(data, keyPair.getPrivateKey());
		
		/* ����Ű�� ����� ������ ���� */
		System.out.println(verify(data, signature, keyPair.getPublicKey()));
	}
	
	
	public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
		Signature sig = Signature.getInstance(signAlgorithm);
		sig.initSign(privateKey);
		sig.update(data);
		return sig.sign(); 
	}
	
	
	private static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws Exception {
		Signature verify = Signature.getInstance(signAlgorithm);
		verify.initVerify(publicKey);
		verify.update(data);
		return verify.verify(signature);
	}

}
