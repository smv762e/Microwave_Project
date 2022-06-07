package microwave;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import system.BeeperCounter;
import system.Microwave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {
	
	private Microwave mw;

	// GIVEN
	
	@Given("A closed microwave with no item")
	public void closedWithNoItem() {
		mw = new Microwave();
	}

	@Given("An opened microwave with no item")
	public void openWithNoItem() {
		closedWithNoItem();
		mw.door_opened();
	}

	@Given("An opened microwave with item")
	public void openWithItem() {
		openWithNoItem();
		mw.item_placed();

	}

	@Given("A closed microwave with item")
	public void closedWithItem() {
		openWithItem();
		mw.door_closed();
	}

	@Given("A cooking microwave with {int} power and {int} timer")
	public void cooking(Integer power, Integer time) {
		closedWithItem();
		setTimer(time);
		setPower(power);
		mw.cooking_start();
	}

	// WHEN
	
	@When("Open the door")
	public void openDoor() {
		mw.door_opened();
	}
	
	@When("Close the door")
	public void closeDoor() {
		mw.door_closed();
	}
	
	@When("Place an item")
	public void placeItem() {
		mw.item_placed();
	}

	@When("Remove an item")
	public void removeItem() {
		mw.item_removed();
	}

	@When("Set the power with {int} W")
	public void setPower(Integer power) {
		mw.power_reset();
		for (int i = 0; i < power; i++) {
			mw.power_inc();
		}
	}
	
	@When("Increase the power")
	public void incPower() {
		mw.power_inc();
	}
	
	@When("Decrease the power")
	public void decPower() {
		mw.power_dec();
	}
	
	@When("Reset the power")
	public void resetPower() {
		mw.power_reset();
	}
	
	@When("Set the timer with {int} seconds")
	public void setTimer(Integer times) {
		mw.timer_reset();
		for (int i = 0; i < times; i++) {
			mw.timer_inc();
		}
	}
	
	@When("Increase the timer")
	public void incTimer() {
		mw.timer_inc();
	}
	
	@When("Decrease the timer")
	public void decTimer() {
		mw.timer_dec();
	}
	
	@When("Reset the timer")
	public void resetTimer() {
		mw.timer_reset();
	}
	
	@When("Timer goes down for {int} seconds")
	public void timerGoesDown(Integer t) {
		timer_works(t);
	}

	@When("Set microwave to start cooking")
	public void setStartCooking() {
		try {
			mw.cooking_start();
		} catch (IllegalStateException e) {
			microwaveNotStartCooking();
		}
	}

	// THEN
	
	@Then("Door opens")
	public void doorOpens() {
		assertTrue(mw.isDoorOpen());
	}
	
	@Then("Door closes")
	public void doorCloses() {
		assertFalse(mw.isDoorOpen());
	}
	
	@Then("Microwave has an item")
	public void hasItem() {
		assertTrue(mw.isWithItem());
	}
	
	@Then("Microwave has no item")
	public void noItem() {
		assertFalse(mw.isWithItem());
	}
	
	@Then("Power goes to zero")
	public void powerGoesZero() {
		assertEquals("0", mw.getDisplayElement().getDisplay());
	}
	
	@Then("Timer goes to zero")
	public void timerGoesZero() {
		assertEquals("0", mw.getDisplayElement().getDisplay());
	}
	
	@Then("Heating heats")
	public void heatingHeat() {
		assertTrue(mw.getHeatingElement().isHeating());
	}
	
	@Then("Heating doesn't heats")
	public void heatingNotHeat() {
		assertFalse(mw.getHeatingElement().isHeating());
	}

	@Then("Lamp turns on")
	public void lampTurnsOn() {
		assertTrue(mw.getLampElement().isLampOn());
	}
	
	@Then("Lamp turns off")
	public void lampTurnsOff() {
		assertFalse(mw.getLampElement().isLampOn());
	}
	
	@Then("Turntable turns")
	public void turntableTurns() {
		assertTrue(mw.getTurntableElement().isMoving());
	}
	
	@Then("Turntable doesn't turns")
	public void turntableNotTurns() {
		assertFalse(mw.getTurntableElement().isMoving());
	}
	
	@Then("Beeper sounds {int} times")
	public void beeperSounds(Integer t) {
		assertTrue(BeeperCounter.beeperSound(t));
	}
	
	@Then("Display shows {string}")
	public void displayWorks(String i) {
		assertEquals(i, mw.getDisplayElement().getDisplay());
	}
	
	@Then("Microwave start cooking")
	public void microwaveStartCooking() {
		assertTrue(mw.isCooking());
	}

	@Then("Microwave doesn't start cooking")
	public void microwaveNotStartCooking() {
		assertThrows(IllegalStateException.class, () -> mw.cooking_start());
	}
	
	@Then("Microwave not cooking")
	public void microwaveNotCooking() {
		assertFalse(mw.isCooking());
	}

	@Then("Timer has {int} seconds")
	public void timerHas(Integer time) {
		timer_works(time);
		assertEquals("Item ready", mw.getDisplayElement().getDisplay());
	}

	// EXTRA METHOD

	private void timer_works(int t) {
		for (int i = 0; i < t; i++) {
			mw.tick();
		}
	}
}
