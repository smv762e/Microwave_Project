package system;

public class Heating {
	
	private boolean heating = false;
	private int power = 0;

	public void heating_on() {
		heating = true;
	}

	public void heating_off() {
		heating = false;
	}

	public void setPower(int power) {
		if (power >= 0) {
			this.power = power;
		}
	}

	public boolean isHeating() {
		return heating;
	}
	
	public int getPower() {
		return power;
	}

}


