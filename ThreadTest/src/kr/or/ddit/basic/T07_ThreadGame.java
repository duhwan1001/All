package kr.or.ddit.basic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
 * 사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 *  === 결과 ===
 *  컴퓨터 : 가위
 *  당   신 : 바위
 *  결   과 : 당신이 이겼습니다
 *  
 * @author PC-21
 *
 */
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		CountDown ct = new CountDown();
		
		ct.start();
		
		bot.

ArrayList<String> rsp = new ArrayList<>();
		
		rsp.add("가위");
		rsp.add("바위");
		rsp.add("보");
		
		int ran = (int)(Math.random() * 3);
		
		comIndex = rsp.get(ran);
		
		System.out.println(rsp.get(ran));
		
		
humIndex = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		T06_ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + humIndex + "입니다.");
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		for(int i=10; i>=1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면 
			// run()메서드를 종료시킨다.. 즉 현재 스레드를 종료시킨다.
			if(T06_ThreadTest.inputCheck == true) {
				return; // run()메서드가 종료되면 쓰레드도 끝난다.
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}