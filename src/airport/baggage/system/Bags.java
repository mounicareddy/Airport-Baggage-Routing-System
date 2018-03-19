package airport.baggage.system;

public class Bags {
	String bag_Number;
	String entry_point;
	String flight_id;
	public Bags(String bagNum,String entry,String flightid) {
		this.bag_Number=bagNum;
		this.entry_point=entry;
		this.flight_id=flightid;
	}
	public String getBag_Number() {
		return bag_Number;
	}
	public void setBag_Number(String bag_Number) {
		this.bag_Number = bag_Number;
	}
	public String getEntry_point() {
		return entry_point;
	}
	public void setEntry_point(String entry_point) {
		this.entry_point = entry_point;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}

}
