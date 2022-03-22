package com.opn.hex.cmmnTool;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardTool {
	
	//클립보드에 임시 복사해주는 로직
	//스트링만 저렇게 넣어서 해주면 클립보드에 복사 된다
	public void copyToClipboard(String msg){
		StringSelection data = new StringSelection(msg);
		Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipBoard.setContents(data, data);
	}
}
