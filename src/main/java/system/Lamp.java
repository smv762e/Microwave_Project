package system;

public class Lamp {
	
	private boolean lampOn = false;

	public void lamp_on() {
		lampOn = true;
	}

	public void lamp_off() {
		lampOn = false;
	}
	
	public boolean isLampOn() {
		return lampOn;
	}
	
}

