package system;

public class ClosedWithItem implements IMicrowave {

	public ClosedWithItem(Microwave mw) {
		mw.getLampElement().lamp_off();
		mw.getHeatingElement().heating_off();
		mw.getTurntableElement().turntable_stop();
		mw.setCooking(false);
		mw.setDoorOpen(false);
		mw.setWithItem(true);
	}

	@Override
	public void door_opened(Microwave mw) {
		mw.setStatus(new OpenWithItem(mw));
	}

	@Override
	public void door_closed(Microwave mw) {
		throw new IllegalStateException("Error: Door already closed");
	}

	@Override
	public void item_placed(Microwave mw) {
		throw new IllegalStateException("Error: Door closed");
	}

	@Override
	public void item_removed(Microwave mw) {
		throw new IllegalStateException("Error: Door closed");
	}

	@Override
	public void power_dec(Microwave mw) {
		if (mw.getPower() > 0) {
			mw.setPower(mw.getPower() - 1);
			mw.getDisplayElement().setDisplay(Integer.toString(mw.getPower()));
		}
	}

	@Override
	public void power_reset(Microwave mw) {
		mw.setPower(0);
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
		if(mw.getTimer() > 0 && mw.getPower() > 0) {
			mw.setStatus(new Cooking(mw));
		} else if (mw.getTimer() > 0) {
			throw new IllegalStateException("Error: Power is 0");
		} else {
			throw new IllegalStateException("Error: Timer is 0");
		}
	}

	@Override
	public void cooking_stop(Microwave mw) {
		throw new IllegalStateException("Error: Microwave was not cooking");
	}
	
	@Override
	public void tick(Microwave mw) {
		throw new IllegalStateException("Error: Microwave not cooking");
	}

}

