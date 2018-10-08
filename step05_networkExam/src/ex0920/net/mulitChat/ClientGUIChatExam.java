package ex0920.net.mulitChat;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUIChatExam extends JFrame {
	JTextArea textArea = new JTextArea();
	JScrollPane jsp = new JScrollPane(textArea);
	JTextField text = new JTextField();
	
	Socket sk;
	BufferedReader br;
	PrintWriter pw;
	
	
	/**
	 * 화면구성
	 * 
	 */
	
	public ClientGUIChatExam() {
	super("ClientGUIChat!!");
	Container con= super.getContentPane();
	con.add(jsp, "Center");
	con.add(text, "South");
	
	//옵션설정
	textArea.setFocusable(false);
	textArea.setBackground(Color.PINK);
	text.requestFocus();//커서놓기
	
	
	setSize(400,300);//크기 설정
	setLocationRelativeTo(null);//JFrame 정중앙에 위치
	setVisible(true);//JFrame보여줭
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x클릭시 프로세스 종료
	//서버접속시도
	this.connection();
	
	//이벤트 등록: 이벤트 주체.addXxxListener(이벤트구현클래스);
	text.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		System.out.println("하하.....");
			//textField값 읽기
			String inputData = text.getText();
			
			//서버에게 전송
			pw.println(inputData);
			
			//textfield 지우기 
			text.setText("");
				}
	});
	
	
	
	}//생성자끝
	public void connection() {
		try {
		sk =new Socket("192.168.0.111",8000);
		String name =JOptionPane.showInputDialog("대화명을 입력해주세요.");
		br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
		pw = new PrintWriter(sk.getOutputStream(),true);
		
		//대화명 전송하기
		pw.println(name);
		
		//서버가 보내오는 데이터를 받아서 textArea에 추가한다. 익명으로 만들기
		new Thread() {
			public void run() {
				try {
				String data=null;
				while((data =br.readLine())!=null) {
					textArea.append(data+"\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			};
			
		}.start();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		new ClientGUIChatExam();

	}

}
