package system;

public class BeeperCounter {
	
	private static int beeps = 0;
	
	public static void transfer(int tb) {
		beeps = tb;
	}

	// Reset the counter
	public static boolean beeperSound(int t) {
		int bp = beeps;
		beeps = 0;
		return (bp == t);
	}

}

