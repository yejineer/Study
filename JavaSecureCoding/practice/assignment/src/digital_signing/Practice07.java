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
		
		/* File 읽어와 byte[]에 저장 */
		Path path = (new File(filename)).toPath();
		byte[] data = Files.readAllBytes(path);	
		
		/* key 발급 */
		MyKeyPair keyPair = MyKeyPair.getInstance(1024);
		keyPair.createKeys();
		
		/* 개인키 이용해 전자 서명 생성*/
		byte[] signature = sign(data, keyPair.getPrivateKey());
		
		/* 공개키로 서명된 데이터 검증 */
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
