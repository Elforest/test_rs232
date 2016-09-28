package com.qqq.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class SerialBean {
	private static String portName;
	private int rate;
	private int dataBits;
	private int stopBits;
	private int parity;
	private static OutputStream out;
	private static InputStream in;

	private CommPortIdentifier portId;
	private SerialPort serialPort;
	private SerialBuffer SB;
	private ReadSerial RT;

	public SerialBean(int portID) {
		portName = "COM" + portID;
	}

	public SerialBean(int portID, int rate, int dataBits, int stopBits,
			int parity) {
		portName = "COM" + portID;
		this.rate = rate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
	}

	public int Initialize() {
		int InitSuccess = 1;
		int InitFail = -1;
		try {
			portId = CommPortIdentifier.getPortIdentifier(portName);
			try {
				serialPort = (SerialPort) portId.open("Serial_Communication",
						2000);
			} catch (PortInUseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return InitFail;
			}
			try {
				in = serialPort.getInputStream();
				out = serialPort.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return InitFail;
			}
			try {
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return InitFail;
			}
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return InitFail;
		}
		SB = new SerialBuffer();
		RT = new ReadSerial(SB, in);
		RT.start();
		return InitSuccess;
	}

	public String ReadPort(int Length) {
		String Msg;
		Msg = SB.GetMsg(Length);
		return Msg;
	}

	public void WritePort(String Msg) {
		try {
			for (int i = 0; i < Msg.length(); i++) {
				out.write(Msg.charAt(i));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ClosePort() {
		// RT.stop();
		serialPort.close();
	}
}
