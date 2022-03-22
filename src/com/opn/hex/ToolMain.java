package com.opn.hex;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//JFrame은 GUI가 가능하게 하고, ActionListener는 Override 되는 method를 통해 이벤트를 걸 수 있다.
public class ToolMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 12835729385723L;
	
	//메소드마다 전부 사용할 수 있도록 버튼을 전역 변수로 설정
	JButton btnRndHex;
	
	//SingleTone Pattern
	static private RandomHex rndHex;
	
	//Dimension frameSize;
	
	//constructor
	ToolMain() {
		
		//frameSize = size();
		
		//////////////////타이틀 설정
		setTitle("YAMAETOOL FROM FURIUM");
		
		//////////////////위치와 크기를 설정
		//setBounds(x, y, width, height)
		setBounds(100, 100, 300, 80);
		
		//창 크기 고정
		setResizable(false);
		
		//////////////////위에 X버튼(닫기)을 눌렀을 때 어떻게 할지 설정
		//operation에 EXIT_ON_CLOSE하면 현재 실행된 모든 창이 꺼짐. 완전 종료
		//operation에 DISPOSE_ON_CLOSE하면 해당 창만 꺼짐.
		//setDefaultCloseOperation(operation)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//////////////////컨테이너. 헤더 밑에 바디 전체임. html의 <body> 생각하자
		Container contentPane = this.getContentPane();
		
		
		//////////////////패널. body안에 <div> 같이 패널 생각하자
		JPanel pane = new JPanel();
		//pane.setBackground(Color.BLACK);
		pane.setBackground(Color.decode("#0A174E"));
		
		//////////////////버튼. JButton("버튼안에 글자들어갈거 여기 쓰자");
		btnRndHex = new JButton("시작");
		btnRndHex.setBackground(Color.decode("#F5D042"));
		//버튼안에 문자열 색상 설정
		btnRndHex.setForeground(Color.decode("#0A174E"));
		
		//////////////////버튼 클릭 시 밑에 ActionListener 상속받아서 override된 actionPerformed 이용해서 이벤트 줄 수 있음.
		//addActionListener(this) 주면 this는 BtnRndHex가 됨.
		//동작은 
		btnRndHex.addActionListener(this);
		
		//////////////////단축키 설정
		//setMnemonic(character)
		btnRndHex.setMnemonic('R');	//이렇게 하면 알트 + r 누르면 실행됨.
		
		//////////////////버튼 색상 설정 방법
		//버튼 색상 기본 설정 방법
		//btnRndHex.setBackground(Color.BLUE);
		
		//버튼 색상 헥사코드 설정 방법
		//btnRndHex.setBackground(Color.decode("#FFFF00"));
		//////////////////
		
		//////////////////패널안에 버튼 올리기. div안에 Button 넣었다고 생각하자
		pane.add(btnRndHex);
		/*JLabel jdkLabel = new JLabel("JDK 1.8.0_192", SwingConstants.CENTER);
		pane.add(jdkLabel);
		jdkLabel.setForeground(Color.decode("#F5D042"));*/
		
		
		//////////////////컨테이너 안에 패널 올리기. body안에 div 넣었다고 생각하자
		contentPane.add(pane);
		
		//////////////////버튼 색상 기본 설정 방법지금 위에 짜놓은 컨테이너 보일지 말지 결정
		//setVisible(boolean)
		setVisible(true);
		
	}
	
	public void removeRndHex(){
		rndHex = null;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		////////////////ActionEvent 구분
		//버튼 안에 텍스트가 뭐가 들어가 있느냐로 비교할 수도 있고
		//if((e.getActionCommand().equals("RANDOM HEX")){
		
		//btnRndHex는 전역변수 이므로 getSource를 통해서 이렇게 객체 자체를 비교할 수도 있음
		if(e.getSource()==btnRndHex){
			if(rndHex == null){
				rndHex = new RandomHex(this);
			}else{
				System.out.println("이미 있음");
			}
		}
	}
}
