package edu.tjut.lzy.util;

import java.io.UnsupportedEncodingException;

public class EncodeString {
	public static String encodeUtf8(String string) {
		try {
			return new String(string.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
}
