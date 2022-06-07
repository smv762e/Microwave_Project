package system;

public class OpenWithItem implements IMicrowave {

	public OpenWithItem(Microwave mw) {
		mw.getLampElement().lamp_on();
		mw.getHeatingElement().heating_off();
		mw.getTurntableElement().turntable_stop();
		mw.setCooking(false);
		mw.setWithItem(true);
		mw.setDoorOpen(true);
	}

	@Override
	public void door_opened(Microwave mw) {
		throw new IllegalStateException("Error: Door already opened");
	}

	@Override
	public void door_closed(Microwave mw) {
		mw.setStatus(new ClosedWithItem(mw));
	}

	@Override
	public void item_placed(Microwave mw) {
		throw new IllegalStateException("Error: Microwave is full");
	}

	@Override
	public void item_removed(Microwave mw) {
		mw.setStatus(new OpenWithNoItem(mw));
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
	public void timer_reset(Microwave mw) {
		mw.setTimer(0);
	}
	
	@Override
	public void cooking_start(Microwave mw) {
		throw new IllegalStateException("Error: Can't star with door opened");
	}

	@Override
	public void cooking_stop(Microwave mw) {
		throw new IllegalStateException("Error: Microwave not cooking");
	}

	@Override
	public void tick(Microwave m) {
		throw new IllegalStateException("Error: Microwave not cooking");
	}

}


