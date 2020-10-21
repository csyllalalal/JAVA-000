package jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class HelloClassLoader extends ClassLoader{

	
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		
		File file = new File("d:/Hello.xlass");
		byte[] bytes = null;
		try {
			bytes = getByte(file);
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = (byte) (255-bytes[i]);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defineClass(name,bytes,0,bytes.length);
	}
	
	
	public static void main(String[] args){
		try{
			Object obj = new HelloClassLoader().findClass("Hello").newInstance();
			obj.getClass().getMethod("hello").invoke(obj, null);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param file
	 * @return   byte[]
	 * @throws Exception
	 */
	public static byte[] getByte(File file) throws Exception
	{
		byte[] bytes = null;
		if(file!=null)
		{
			InputStream is = new FileInputStream(file);
			int length = (int) file.length();
			//当文件的长度超过了int的最大值
			if(length>Integer.MAX_VALUE)   
			{
				System.out.println("this file is max ");
				return null;
			}
			bytes = new byte[length];
			int offset = 0;
			int numRead = 0;
			while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
			{
				offset+=numRead;
			}
			//如果得到的字节长度和file实际的长度不一致就可能出错了
			if(offset<bytes.length)
			{
				System.out.println("file length is error");
				return null;
			}
			is.close();
		}
		return bytes;
	}


}
