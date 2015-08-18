package com.push11.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authorization {

	private static final String hashKey = "hash_key";
	private String type;
	private String hash;
	private String random;

    public static Authorization createEmptyAuthorization(){
        return new Authorization();
    }

	public static Authorization generateFromString(String data) {

		if (data == null) {
			return null;
		}
        Authorization authorization = null;
        String[] dataList = data.split(",");
		if (dataList.length == 3) {
			authorization = createEmptyAuthorization();
			authorization.type = dataList[0];
			authorization.hash = dataList[1];
			authorization.random = dataList[2];
		}
		return authorization;
	}

	public boolean isAuthorizationValid() {
		return hash.equals(getMD5(random));
	}

    public boolean isNotAuthorizationValid() {
		return !isAuthorizationValid();
	}

	private static String getMD5(String random) {

		String data = hashKey;

		try {
			byte[] output = MessageDigest.getInstance("MD5").digest(data.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder();
			for (byte b : output) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {

		}
		return "";
	}

}
