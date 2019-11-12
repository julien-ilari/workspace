package fr.app;

public class Stop {

	public static void main(String[] args) {
		if (args.length == 1) {
			JettyServer.stop(Integer.valueOf(args[0]));
		} else {
			JettyServer.stop();
		}
	}

}
