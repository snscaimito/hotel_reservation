package net.caimito.reservations;

public class FakeFrontDeskStrategyTest extends ReservationTest {

	public FakeFrontDeskStrategyTest() {
		this.strategyBuilder = new FakeFrontDeskStrategyBuilder() ;
	}
}
