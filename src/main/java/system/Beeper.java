package system;

public class Beeper {
	
	public void beep(int b) {
		BeeperCounter.transfer(b);
	}
	
}

