package ex0920.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientExam {
	public ClientExam() {
		
		try {
		Socket sk=new Socket("192.168.0.141",8000);
		
		//�������� ������ ������
		PrintWriter pw=	new PrintWriter(sk.getOutputStream(),true); 
		pw.println("â�ƾ� ������");
	
	//������ �������� �����͸� �б�
		BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
		String serverMessage=br.readLine();
		System.out.println("������ ������ ����:"+serverMessage);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
	}
	public static void main(String[] args) {
		new ClientExam();
		
	}

}
