package ex0920.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.net.URL;

public class URLconnectionExam {
	public URLconnectionExam() {
		try {
		URL url = new URL("https://www.daum.net");
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		BufferedOutputStream bos =new BufferedOutputStream(new FileOutputStream("src/ex0920/net/daum.txt"));
		/*byte b[] = new byte[bis.available()];
		bis.read(b);

		System.out.println(new String(b));
		
		bos.write(b);*/
		int i=0;;
		while((i=bis.read())!=-1) {
			
			bos.write(i);
		}
		bis.close();
		bis.close();
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	new URLconnectionExam();

	}

}
