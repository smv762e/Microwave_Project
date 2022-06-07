package system;

public class Turntable {
	
	private boolean turntableOn = false;

	public void turntable_start() {
		turntableOn = true;
	}

	public void turntable_stop() {
		turntableOn = false;
	}

	public boolean isMoving() {
		return turntableOn;
	}
	
}

