package ex0920.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExam {
		public ServerExam() {
			try {
				ServerSocket server= new ServerSocket(8000);
				while(true) {
					System.out.println("client접속을 기다립니다.");
					Socket sk = server.accept();//클라이언트 접속 대기중;
					System.out.println(sk.getInetAddress()+"님 접속되었습니다...");
					
					//클라이언트가 보내온느 데이터 읽기
					BufferedReader br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
					String clientMessage = br.readLine();
					System.out.println("client보내온 내용:"+clientMessage);
					
					//클라이언트에게 데이터 전송하기(보내기)
					sk.getOutputStream();
					//OutputStreamWriter->Bufferedwirter 한번에 쓰는 PrintWriter
					PrintWriter pw = new PrintWriter(sk.getOutputStream(),true);
					pw.println("보내드립니다");
					sk.close();
					
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	public static void main(String[] args) {
			new ServerExam();

	}

}
