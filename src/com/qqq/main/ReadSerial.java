package com.qqq.main;

import java.io.IOException;
import java.io.InputStream;

public class ReadSerial extends Thread {
	private SerialBuffer ComBuffer;
	private InputStream ComPort;

	public ReadSerial(SerialBuffer SB, InputStream port) {
		ComBuffer = SB;
		ComPort = port;
	}

	public void run() {
		int c;
		try {
			while (true) {
				c = ComPort.read();
				ComBuffer.PutChar(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
