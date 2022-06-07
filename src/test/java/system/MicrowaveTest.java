package system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MicrowaveTest {

	private Microwave mw = new Microwave();

	// Test for Heating element
	@Test
	public void heatingTest() {
		Heating h = new Heating();

		// Initial status
		assertEquals(0, h.getPower());
		assertFalse(h.isHeating());

		// Correct power input
		h.setPower(180);
		assertEquals(180, h.getPower());
		h.setPower(0);
		assertEquals(0, h.getPower());

		// Sequence of power on and power off
		h.heating_on();
		assertTrue(h.isHeating());
		h.heating_off();
		assertFalse(h.isHeating());

	}
	
	// Test for Lamp element
	@Test
	public void lampTest() {
		Lamp p = new Lamp();
		
		// Initial status
		assertFalse(p.isLampOn());

		// Sequence of lamp on and lamp off
		assertFalse(p.isLampOn());
		p.lamp_on();
		assertTrue(p.isLampOn());
		p.lamp_off();
		assertFalse(p.isLampOn());
	}

	// Test for Turntable element
	@Test
	public void turntableTest() {
		Turntable t = new Turntable();
		
		// Initial status
		assertFalse(t.isMoving());
		
		// Turn sequence
		assertFalse(t.isMoving());
		t.turntable_start();
		assertTrue(t.isMoving());
		t.turntable_stop();
		assertFalse(t.isMoving());
	}

	// Test for Beeper element
	@Test
	public void beeperTest() {
		Beeper p = new Beeper();
		
		// Correct beeper input
		p.beep(5);
		assertTrue(BeeperCounter.beeperSound(5));

		// After 1 iteration, beeper resets
		assertTrue(BeeperCounter.beeperSound(0));
	}

	// Test for Display element
	@Test
	public void displayTest() {
		Display d = new Display();

		// Initial status
		assertNull(d.getDisplay());

		// Set and clear display
		d.setDisplay("Message");
		assertEquals("Message", d.getDisplay());
		d.clearDisplay();
		assertNull(d.getDisplay());
	}
	
	// Methods to increase and decrease the power and timer
	private void power_incPlus(int p) {
		for (int i = 0; i < p; i++) {
			mw.power_inc();
		}
	}

	private void power_decPlus(int p) {
		for (int i = 0; i < p; i++) {
			mw.power_dec();
		}
	}
	
	private void timer_incPlus(int t) {
		for (int i = 0; i < t; i++) {
			mw.timer_inc();
		}
	}

	private void timer_decPlus(int t) {
		for (int i = 0; i < t; i++) {
			mw.timer_dec();
		}
	}

	private void timer_works(int t) {
		for (int i = 0; i < t; i++) {
			mw.tick();
		}
	}

	// Test for timer and power from Microwave
	@Test
	public void setupTest() {
		
		mw.timer_reset();
		mw.power_reset();
		mw.timer_dec();
		mw.power_dec();
		
		// Setup cycle for power
		assertEquals(0, mw.getPower());
		power_incPlus(200);
		assertEquals(200, mw.getPower());
		assertEquals("200", mw.getDisplayElement().getDisplay());
		power_decPlus(100);
		assertEquals(100, mw.getPower());
		assertEquals("100", mw.getDisplayElement().getDisplay());
		mw.power_reset();
		assertEquals(0, mw.getPower());
		assertEquals("0", mw.getDisplayElement().getDisplay());		
		
		// Setup cycle for timer
		assertEquals(0, mw.getTimer());
		timer_incPlus(60);
		assertEquals(60, mw.getTimer());
		assertEquals("60", mw.getDisplayElement().getDisplay());
		timer_decPlus(30);
		assertEquals(30, mw.getTimer());
		assertEquals("30", mw.getDisplayElement().getDisplay());
		mw.timer_reset();
		assertEquals(0, mw.getTimer());
		assertEquals("0", mw.getDisplayElement().getDisplay());

	}

	// Phase 1: Test for a ClosedWithNoItem situation
	@Test
	public void closedWithNoItemTest() {
		
		// Exceptions check
		assertThrows(IllegalStateException.class, () -> mw.door_closed());
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		assertThrows(IllegalStateException.class, () -> mw.cooking_stop());
		assertThrows(IllegalStateException.class, () -> mw.item_placed());
		assertThrows(IllegalStateException.class, () -> mw.item_removed());
		assertThrows(IllegalStateException.class, () -> mw.tick());

		// Inner status check
		assertFalse(mw.isCooking());
		assertFalse(mw.isWithItem());
		assertFalse(mw.isDoorOpen());
		assertFalse(mw.getHeatingElement().isHeating());
		assertFalse(mw.getLampElement().isLampOn());
		assertFalse(mw.getTurntableElement().isMoving());
		assertTrue(mw.getStatus() instanceof ClosedWithNoItem);

	}

	// Phase 2: Test for an OpenWithNoItem situation
	@Test
	public void openWithNoItemTest() {
		if (mw.getStatus() instanceof ClosedWithNoItem) {
			mw.door_opened();
		}

		// Exceptions check
		assertThrows(IllegalStateException.class, () -> mw.door_opened());
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		assertThrows(IllegalStateException.class, () -> mw.cooking_stop());
		assertThrows(IllegalStateException.class, () -> mw.item_removed());
		assertThrows(IllegalStateException.class, () -> mw.tick());

		// Inner status check
		assertFalse(mw.isCooking());
		assertFalse(mw.isWithItem());
		assertTrue(mw.isDoorOpen());
		assertFalse(mw.getHeatingElement().isHeating());
		assertTrue(mw.getLampElement().isLampOn());
		assertFalse(mw.getTurntableElement().isMoving());
		assertTrue(mw.getStatus() instanceof OpenWithNoItem);

		// Timer and power check
		setupTest();

		// Test works when closing door
		mw.door_closed();
		closedWithNoItemTest();
	}

	// Phase 3: Test for an OpenWithItem situation
	@Test
	public void openWithItemTest() {
		if (mw.getStatus() instanceof ClosedWithNoItem) {
			mw.door_opened();
			mw.item_placed();
		}

		// Exceptions check
		assertThrows(IllegalStateException.class, () -> mw.door_opened());
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		assertThrows(IllegalStateException.class, () -> mw.cooking_stop());
		assertThrows(IllegalStateException.class, () -> mw.item_placed());
		assertThrows(IllegalStateException.class, () -> mw.tick());

		// Inner status check
		assertFalse(mw.isCooking());
		assertTrue(mw.isWithItem());
		assertTrue(mw.isDoorOpen());
		assertFalse(mw.getHeatingElement().isHeating());
		assertTrue(mw.getLampElement().isLampOn());
		assertFalse(mw.getTurntableElement().isMoving());
		assertTrue(mw.getStatus() instanceof OpenWithItem);

		// Timer and power check
		setupTest();

		// Test works when removing item
		mw.item_removed();
		assertTrue(mw.getStatus() instanceof OpenWithNoItem);
		openWithNoItemTest();
	}

	// Phase 4: Test for a ClosedWithItem situation
	@Test
	public void closedWithItemTest() {
		if (mw.getStatus() instanceof ClosedWithNoItem) {
			mw.door_opened();
			mw.item_placed();
			mw.door_closed();
		}

		// Exceptions check
		assertThrows(IllegalStateException.class, () -> mw.door_closed());
		assertThrows(IllegalStateException.class, () -> mw.cooking_stop());
		assertThrows(IllegalStateException.class, () -> mw.item_placed());
		assertThrows(IllegalStateException.class, () -> mw.item_removed());
		assertThrows(IllegalStateException.class, () -> mw.tick());

		// Inner status check
		assertFalse(mw.isCooking());
		assertTrue(mw.isWithItem());
		assertFalse(mw.isDoorOpen());
		assertFalse(mw.getHeatingElement().isHeating());
		assertFalse(mw.getLampElement().isLampOn());
		assertFalse(mw.getTurntableElement().isMoving());
		assertTrue(mw.getStatus() instanceof ClosedWithItem);

		// Timer and power check
		setupTest();

		// Test works when opening door
		mw.door_opened();
		assertTrue(mw.getStatus() instanceof OpenWithItem);
		openWithItemTest();
	}

	// Phase 5: Test for a Cooking situation
	@Test
	public void cookingTest() {
		if (mw.getStatus() instanceof ClosedWithNoItem) {
			mw.door_opened();
			mw.item_placed();
			mw.door_closed();
		}
		
		// Can't start cooking with wrong inputs
		mw.timer_reset();
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		timer_incPlus(15);
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		mw.timer_reset();
		power_incPlus(50);
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		mw.power_reset();

		// Start cooking
		timer_incPlus(20);
		power_incPlus(100);
		mw.cooking_start();

		// Exceptions check
		assertThrows(IllegalStateException.class, () -> mw.door_closed());
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
		assertThrows(IllegalStateException.class, () -> mw.item_placed());
		assertThrows(IllegalStateException.class, () -> mw.item_removed());

		// Inner status check
		assertTrue(mw.isCooking());
		assertTrue(mw.isWithItem());
		assertFalse(mw.isDoorOpen());
		assertTrue(mw.getHeatingElement().isHeating());
		assertTrue(mw.getLampElement().isLampOn());
		assertTrue(mw.getTurntableElement().isMoving());
		assertTrue(mw.getStatus() instanceof Cooking);

		// Microwave stops when opening door
		mw.door_opened();
		assertTrue(mw.getStatus() instanceof OpenWithItem);
		assertFalse(BeeperCounter.beeperSound(3));
		openWithItemTest();

		// Restart process
		mw.door_opened();
		mw.item_placed();
		mw.door_closed();
		timer_incPlus(10);
		power_incPlus(100);
		mw.cooking_start();

		// Microwave stop when timer ends
		assertEquals(10, mw.getTimer());
		timer_works(10);
		assertEquals(0, mw.getTimer());
		assertTrue(mw.getStatus() instanceof ClosedWithItem);
		assertEquals("Item ready", mw.getDisplayElement().getDisplay());
		assertTrue(BeeperCounter.beeperSound(3));

		// Test works when ending cooking
		closedWithItemTest();
	}

}


