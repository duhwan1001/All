package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	public static final int DEFAULT_BUFFER_SIZE = 10000;
	
	public static void main(String[] args) throws IOException {
		int port = 8888;
		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		
		int readByte = 0;
		
		System.out.println("파일 수신 대기중...");
		
		DatagramSocket ds = new DatagramSocket(port);		
		FileOutputStream fos = null;
		fos = new FileOutputStream("d:/C_Lib/aaa.jpg");
		
		DatagramPacket dp =
				new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) {
			// Sender에서 전송을 시작한 경우..
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp);
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			double startTime = System.currentTimeMillis();
			
			while(true) {
				ds.receive(dp);
				str = new String(dp.getData()).trim();
				readByte = dp.getLength();
				fos.write(dp.getData(), 0, readByte);
				totalReadBytes += readByte;
				
				System.out.println("In prograss : " 
						+ totalReadBytes + "/"
						+ fileSize + " Bytes("
						+ (totalReadBytes * 100 / fileSize)
						+ "%)" );
				
				if(totalReadBytes >= fileSize) {
					break;
				}
			}
			
				double endTime = System.currentTimeMillis();
				double diffTime = (endTime - startTime) / 1000;
				double transferSpeed = (fileSize/1000)/diffTime;
				
				System.out.println("걸린 시간 : " + diffTime + "초");
				System.out.println("평균전송속도 : " + transferSpeed 
								+ " KB/s");
				
				System.out.println("수신 처리 완료..");
				
				fos.close();
				ds.close();
				
				}else {
				System.out.println("수신 처리 불가");
				fos.close();
				ds.close();
				
			
			
		}
	}
}






















