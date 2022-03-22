package com.opn.hex;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.opn.hex.cmmnTool.ClipboardTool;

// ActionListener는 button만 되는 것 같다.
// 지금은 panel과 label에도 이벤트를 줘야하므로 mouseListener impl 받음
public class RandomHex extends JFrame implements MouseListener, ActionListener{
	
	private static final long serialVersionUID = 194683409869L;
	
	// 컨테이너. 전체 창.
	Container hexContainer;
	
	//toolMain을 전역변수로.
	ToolMain toolMain;
	
	ClipboardTool clipboardTool = new ClipboardTool();
	
	//RandomHex Constructor //////////////////////////////////////
	RandomHex(ToolMain main){
		toolMain = main;
		
		//초기화 동작
		initialization();
		
	}
	//RandomHex Constructor End //////////////////////////////////////
	
	
	
	//initialization //////////////////////////////////////
	//전체적인 실행
	public void initialization(){
		//List<String> hexCodes = releaseHexCode();
		
		setTitle("Release Random Hex");
		
		//setBounds(110 , 110, 500, 800);	이번엔 이렇게 안하고
		
		//창 크기 설정. setSize(가로, 세로)
		int widthSize = 500;
		int heightSize = 800;
		setSize(widthSize, heightSize);
		
		// x, y 위치를 모니터 가운데로
		//툴킷
		Toolkit tk = Toolkit.getDefaultToolkit();
		//현재 모니터 사이즈 가져옴
		Dimension screenSize = tk.getScreenSize();
		//x,y 위치 설정. setLocation(x, y)
		// x축 위치 : 화면 너비 / 2 - Frame 너비 / 2
        // y축 위치 : 화면 높이 / 2 - Frame 높이 / 2
		setLocation(screenSize.width / 2 - widthSize / 2, screenSize.height/2 - heightSize / 2);
		
		//윈도우 위에 X 표시 누르면 이 창만 닫힘
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//window창에 뭔가 변화가 일어날때 마다 이벤트를 일어나게 할 거임
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// 윈도우가 처음 생성됐을 때 발생
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// 윈도우가 최소화 됐을 때 발생
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// 윈도우가 최소화에서 최대화 됐을 때 발생
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// 윈도우가 비활성화 됐을 때 발생
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// 윈도우의 시스템 메뉴의 닫기를 시도할 때 발생
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				//윈도우가 닫힐 때 발생
				
				// 싱글톤 유지를 위해 값 가지고 있던 rndHex 값을 null로 만들어줘서
				// 이제 꺼졌으니깐 다시 켜질 수 있도록 한다.
				toolMain.removeRndHex();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				//윈도우가 활성화 될 때 발생
				
			}
		});
		
		
		// 창 만들고
		hexContainer = this.getContentPane();
		
		// 색상 패널들, 새로고침 버튼을 포함하고 있는 기본 패널 생성하고 리턴
		JPanel basePane = createLayerPanel();
		
		//기본 패널을 컨테이너에 올림
		hexContainer.add(basePane);
		
		//보여지게
		setVisible(true);
	}
	
	//initialization End //////////////////////////////////////
	//실제 안쪽 컨텐츠들 생성하는 부분
	public JPanel createLayerPanel(){
		
		//기본 패널 생성
		JPanel basePanel = new JPanel();
		
		//새로고침 버튼.
		JButton jBtn = new JButton("다시");
		jBtn.setBackground(Color.decode("#101820"));
		jBtn.setForeground(Color.decode("#FEE715"));
		//버튼 눌렀을 때 이벤트 걸리게 추가.
		jBtn.addActionListener(this);
		//단축키 추가
		jBtn.setMnemonic('R');
		//기본 패널에 버튼 추가.
		basePanel.add(jBtn);
		
		//레이어 20개 생성할거임
		int layerCnt = 20;
		Random random = new Random();
		
		for(int i = 0; i < layerCnt; i++){
			
			//이렇게하면 헥사코드 만들어짐
			int ranNum = random.nextInt(0xffffff + 1);
			String hexCode = String.format("%06x", ranNum);
			
			//패널 생성
			JPanel panel = new JPanel();
			//패널의 색상을 위에 만든 헥사 코드로 설정 
			panel.setBackground(Color.decode("#" + hexCode));
			//이 패널의 id값을 #헥사코드 문자열로 설정
			panel.putClientProperty("id", "#" + hexCode);
			//이 패널에 마우스 이벤트 걸리게 설정
			panel.addMouseListener(this);
			
			//헥사코드 문자열을 보여주는 라벨 생성
			JLabel label = new JLabel(hexCode);
			//이 라벨의 id 값을 헥사코드 문자열로 설정
			label.putClientProperty("id", hexCode);
			//이 라벨에 마우스 이벤트 걸리게 설정
			label.addMouseListener(this);
			
			// 라벨을 패널 위에 올리고
			panel.add(label);
			// 패널을 기본 패널 위에 올림
			basePanel.add(panel);
		}
		
		/////////////위에 반복문을 통해서 패널 안에 자식 패널들을 넣어둔 상태인데
		//자식 요소들을 어떻게 배치할 것인지
		//자세한건 개인매뉴얼 참고
		basePanel.setLayout(new GridLayout(layerCnt + 1, 1));
		
		return basePanel;
	}
	
	public void refreshRandomHex(){
		//컨테이너 안에 기본 패널부터 해서 쭉 들어가있는 상태인데 그것들 다 날려버림
		//component들만 없애주고 나머지 설정(예를 들면 setDefaultCloseOperation) 같은건 유지됨.
		hexContainer.removeAll();
		initialization();
	}
	
	//MouseListener Override//////////////////////////////////
	@Override
	public void mouseClicked(MouseEvent e) {
		// 마우스 버튼을 클릭했을 때의 동작
		
		// 마우스 클릭한게 패널 타입이라면
		if(e.getSource() instanceof JPanel){
			JPanel panel = (JPanel)e.getSource();
			String msg = (String)panel.getClientProperty("id");
			clipboardTool.copyToClipboard(msg);
			
		// 마우스 클릭한게 라벨 타입이라면
		}else if(e.getSource() instanceof JLabel){
			JLabel label = (JLabel)e.getSource();
			String msg = (String)label.getClientProperty("id");
			clipboardTool.copyToClipboard(msg);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 마우스 버튼을 누르고 있을 때의 동작 정의
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 마우스 버튼을 눌렀다가 떼었을 때 동작
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 마우스 포인터가 컨테이너/컴포넌트 영역 안에 있을 때의 동작
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 마우스 포인터가 컨테이너/컴포넌트 영역 밖에 있을 때의 동작
		
	}
	//MouseListener Override End//////////////////////////////////
	
	//ActionListner Override//////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("다시")){
			refreshRandomHex();
		}
	}
	//ActionListner Override End//////////////////////////////////
	
}
