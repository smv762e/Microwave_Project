package system;

public interface IMicrowave {
	
	public void door_opened(Microwave mw);

	public void door_closed(Microwave mw);

	public void item_placed(Microwave mw);

	public void item_removed(Microwave mw);
	
	public void power_dec(Microwave mw);
	
	public void power_reset(Microwave mw);
	
	public void timer_dec(Microwave mw);
	
	public void timer_reset(Microwave mw);
	
	public void cooking_start(Microwave mw);

	public void cooking_stop(Microwave mw);
	
	public void tick(Microwave mw);
	
}

