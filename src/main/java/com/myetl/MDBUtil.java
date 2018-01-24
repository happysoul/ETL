package com.myetl;

//使用jackcess创建mdb文件
//使用ucanaccess通过jdbc方式访问access数据

@Deprecated
public class MDBUtil {
	
	public static native void createMDB(String str);
	static {
		System.loadLibrary("mdbutil_jni");
	}
}
