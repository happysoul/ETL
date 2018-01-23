package com.myetl;

public class MDBUtil {
	
	public static native void createMDB(String str);
	static {
		System.loadLibrary("mdbutil_jni");
	}
}
