package system;

public class Microwave {
	
	private boolean doorOpen;
	private int power;
	private int timer;
	private boolean cooking;
	private boolean withItem;
	private IMicrowave status;
	private Heating heatingElement = new Heating();
	private Lamp lampElement = new Lamp();
	private Turntable turnableElement = new Turntable();
	private Beeper beeperElement = new Beeper();
	private Display displayElement = new Display();

	public Microwave() {
		doorOpen = false;
		power = 0;
		timer = 0;
		cooking = false;
		withItem = false;
		status = new ClosedWithNoItem(this);
	}

	public void door_opened() {
		status.door_opened(this);
	}

	public void door_closed() {
		status.door_closed(this);
	}

	public void item_placed() {
		status.item_placed(this);
	}

	public void item_removed() {
		status.item_removed(this);
	}

	public void power_inc() {
		power++;
		displayElement.setDisplay(Integer.toString(power));
	}

	public void power_dec() {
		status.power_dec(this);
	}

	public void power_reset() {
		status.power_reset(this);
		displayElement.setDisplay(Integer.toString(power));
	}

	public void timer_inc() {
		timer++;
		displayElement.setDisplay(Integer.toString(timer));
	}

	public void timer_dec() {
		status.timer_dec(this);
	}

	public void timer_reset() {
		status.timer_reset(this);
		displayElement.setDisplay(Integer.toString(timer));
	}

	public void cooking_start() {
		status.cooking_start(this);
	}

	public void cooking_stop() {
		status.cooking_stop(this);
	}

	public void tick() {
		status.tick(this);
	}

	// EXTRA METHODS
	
	public boolean isDoorOpen() {
		return doorOpen;
	}

	public void setDoorOpen(boolean doorOpen) {
		this.doorOpen = doorOpen;
	}
	
	public boolean isWithItem() {
		return withItem;
	}

	public void setWithItem(boolean withItem) {
		this.withItem = withItem;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public boolean isCooking() {
		return cooking;
	}

	public void setCooking(boolean cooking) {
		this.cooking = cooking;
	}

	public IMicrowave getStatus() {
		return status;
	}

	public void setStatus(IMicrowave status) {
		this.status = status;
	}

	public Heating getHeatingElement() {
		return heatingElement;
	}

	public Lamp getLampElement() {
		return lampElement;
	}

	public Turntable getTurntableElement() {
		return turnableElement;
	}

	public Beeper getBeeperElement() {
		return beeperElement;
	}

	public Display getDisplayElement() {
		return displayElement;
	}

}
