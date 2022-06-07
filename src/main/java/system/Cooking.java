package system;

public class Cooking implements IMicrowave{
	
	public Cooking(Microwave mw) {
		mw.getLampElement().lamp_on();
		mw.getHeatingElement().setPower(mw.getPower());
		mw.getHeatingElement().heating_on();
		mw.getTurntableElement().turntable_start();
		mw.setCooking(true);
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
		} if(mw.getPower() == 0) {
			cooking_stop(mw);
		}
	}
	
	@Override
	public void power_reset(Microwave mw) {
		mw.setStatus(new ClosedWithItem(mw));
		mw.setPower(0);
	}
	
	@Override
	public void timer_dec(Microwave mw) {
		if (mw.getTimer() > 0) {
			mw.setTimer(mw.getTimer() - 1);
			mw.getDisplayElement().setDisplay(Integer.toString(mw.getTimer()));
		} if (mw.getTimer() == 0) {
			mw.getBeeperElement().beep(3);
			mw.getDisplayElement().setDisplay("Item ready");
			cooking_stop(mw);
		}
	}

	@Override
	public void timer_reset(Microwave mw) {
		mw.setStatus(new ClosedWithItem(mw));
		mw.setTimer(0);
	}

	@Override
	public void cooking_start(Microwave mw) {
		throw new IllegalStateException("Error: Microwave already cooking");
	}

	@Override
	public void cooking_stop(Microwave mw) {
		mw.setStatus(new ClosedWithItem(mw));
	}
	
	@Override
	public void tick(Microwave mw) {
		if (mw.getTimer() > 1) {
			mw.timer_dec();
		} else {
			mw.timer_dec();
			mw.getBeeperElement().beep(3);
			mw.getDisplayElement().setDisplay("Item ready");
			cooking_stop(mw);
		}
	}

}


