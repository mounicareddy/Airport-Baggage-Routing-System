package airport.baggage.system;

public class Departures {
	String flight_id;
	String flight_gate;
	String destination;
	String flight_time;
	public Departures(String id,String gate,String dest,String time) {
		this.flight_id=id;
		this.flight_gate=gate;
		this.destination=dest;
		this.flight_time=time;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public String getFlight_gate() {
		return flight_gate;
	}
	public void setFlight_gate(String flight_gate) {
		this.flight_gate = flight_gate;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlight_time() {
		return flight_time;
	}
	public void setFlight_time(String flight_time) {
		this.flight_time = flight_time;
	}

}
