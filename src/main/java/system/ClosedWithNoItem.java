package system;

public class ClosedWithNoItem implements IMicrowave {
	
	public ClosedWithNoItem(Microwave m) {
		m.getHeatingElement().heating_off();
		m.getLampElement().lamp_off();
		m.getTurntableElement().turntable_stop();
		m.getDisplayElement().clearDisplay();
		m.setCooking(false); 
		m.setWithItem(false);
		m.setDoorOpen(false);
	}

	@Override
	public void door_opened(Microwave m) {
		m.setStatus(new OpenWithNoItem(m));
	}

	@Override
	public void door_closed(Microwave m) {
		throw new IllegalStateException("Error: Door already closed");
	}

	@Override
	public void item_placed(Microwave m) {
		throw new IllegalStateException("Error: Door closed");
	}

	@Override
	public void item_removed(Microwave m) {
		throw new IllegalStateException("Error: Door closed");
	}
	
	@Override
	public void power_dec(Microwave m) {
		if (m.getPower() > 0) {
			m.setPower(m.getPower() - 1);
			m.getDisplayElement().setDisplay(Integer.toString(m.getPower()));
		}
	}

	@Override
	public void power_reset(Microwave m) {
		m.setPower(0);
	}
	
	@Override
	public void timer_dec(Microwave m) {
		if (m.getTimer() > 0) {
			m.setTimer(m.getTimer() - 1);
			m.getDisplayElement().setDisplay(Integer.toString(m.getTimer()));
		}
	}
	
	@Override
	public void timer_reset(Microwave m) {
		m.setTimer(0);
	}
	
	@Override
	public void cooking_start(Microwave m) {
		throw new IllegalStateException("Error: Microwave with no item");
	}

	@Override
	public void cooking_stop(Microwave m) {
		throw new IllegalStateException("Error: Microwave was not cooking");
	}

	@Override
	public void tick(Microwave m) {
		throw new IllegalStateException("Error: Microwave not cooking");
	}
	
}
