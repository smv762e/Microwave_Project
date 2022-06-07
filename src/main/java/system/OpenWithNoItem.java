package system;

public class OpenWithNoItem implements IMicrowave {

	public OpenWithNoItem(Microwave mw) {
		mw.getLampElement().lamp_on();
		mw.getHeatingElement().heating_off();
		mw.getTurntableElement().turntable_stop();
		mw.setCooking(false);
		mw.setWithItem(false);
		mw.setDoorOpen(true);
	}

	@Override
	public void door_opened(Microwave mw) {
		throw new IllegalStateException("Error: Door already opened");
	}

	@Override
	public void door_closed(Microwave mw) {
		mw.setStatus(new ClosedWithNoItem(mw));
	}

	@Override
	public void item_placed(Microwave mw) {
		mw.setStatus(new OpenWithItem(mw));
	}

	@Override
	public void item_removed(Microwave mw) {
		throw new IllegalStateException("Error: No item there before");
	}

	@Override
	public void power_dec(Microwave mw) {
		if (mw.getPower() > 0) {
			mw.setPower(mw.getPower() - 1);
			mw.getDisplayElement().setDisplay(Integer.toString(mw.getPower()));
		}
	}
	
	@Override
	public void power_reset(Microwave m) {
		m.setPower(0);
	}
	
	@Override
	public void timer_dec(Microwave mw) {
		if (mw.getTimer() > 0) {
			mw.setTimer(mw.getTimer() - 1);
			mw.getDisplayElement().setDisplay(Integer.toString(mw.getTimer()));
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
	public void tick(Microwave mw) {
		throw new IllegalStateException("Error: Microwave not cooking");
	}

}

