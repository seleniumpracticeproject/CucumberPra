package Library;

import org.apache.commons.codec.binary.Base64;

public class En_Decoding {
	// method to encode password
	public String encode(String stringToEncode) {
		byte[] bytesEncoded = Base64.encodeBase64(stringToEncode.getBytes());
		return new String(bytesEncoded);
	}

	// method to decode password
	public String decode(String stringToDecode) {
		byte[] bytesDecoded = Base64.decodeBase64(stringToDecode.getBytes());
		return new String(bytesDecoded);
	}
}
