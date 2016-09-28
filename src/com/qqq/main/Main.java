package com.qqq.main;

public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SerialBean SB = new SerialBean(3);
		String Msg = "";
		//System.out.println(SB.Initialize());
		SB.Initialize();
		// for (int i = 5; i <= 10; i++) {
		// Msg = SB.ReadPort(i);
		// System.out.println(Msg);
		// //SB.WritePort("Reply:" + Msg);
		// }
		SB.WritePort("abc");
		while (true) {
			Msg = SB.ReadPort(14);
			System.out.println(Msg);
			for (int i = 0; i < Msg.length(); i++) {
				System.out.print((int)Msg.charAt(i));
				System.out.print(" ");
			}
			//SB.ClosePort();
			// if (Msg.length() == 59) {
			// String sys = Msg.substring(41, 44);
			// System.out.println("SYS:" + sys);
			// String dia = Msg.substring(49, 52);
			// System.out.println("DIA:" + dia);
			// String pr = Msg.substring(53, 56);
			// System.out.println("PR:" + pr);
			// }
		}
	}

}
