package java8concepts;
import java.util.ArrayList;
import java.util.List;

public class Java8 {
	

	static class WtmsMessage {
	    private AutoScheduleLoad autoScheduleLoad;

		public AutoScheduleLoad getAutoScheduleLoad() {
			return autoScheduleLoad;
		}

		public void setAutoScheduleLoad(AutoScheduleLoad autoScheduleLoad) {
			this.autoScheduleLoad = autoScheduleLoad;
		}

	    // Other fields, getters, setters, etc.
	}

	static class AutoScheduleLoad {
	    private List<Stop> stops;

		public List<Stop> getStops() {
			return stops;
		}

		public void setStops(List<Stop> stops) {
			this.stops = stops;
		}

	    // Other fields, getters, setters, etc.
	}

	static class Stop {
	    private List<Pos> posList;

		public List<Pos> getPosList() {
			return posList;
		}

		public void setPosList(List<Pos> posList) {
			this.posList = posList;
		}

	    // Other fields, getters, setters, etc.
	}

	static class Pos {
	    private String posId;

	    public String getPosId() {
			return posId;
		}

		public void setPosId(String posId) {
			this.posId = posId;
		}

		public Pos(String posId) {
	        this.posId = posId;
	    }

	    // Other fields, getters, setters, etc.
	}

	    public static void main(String[] args) {
	        // Create a sample wtmsMessage object
	        WtmsMessage wtmsMessage = new WtmsMessage();
	        AutoScheduleLoad autoScheduleLoad = new AutoScheduleLoad();
	        List<Stop> stops = new ArrayList<>();

	        Stop stop1 = new Stop();
	        List<Pos> posList1 = new ArrayList<>();
	        posList1.add(new Pos("pos1"));
	        posList1.add(new Pos("pos2"));
	        posList1.add(new Pos("pos3"));
	        stop1.setPosList(posList1);

	        Stop stop2 = new Stop();
	        List<Pos> posList2 = new ArrayList<>();
	        posList2.add(new Pos("pos2"));
	        posList2.add(new Pos("pos4"));
	        posList2.add(new Pos("pos5"));
	        stop2.setPosList(posList2);

	        stops.add(stop1);
	        stops.add(stop2);

	        autoScheduleLoad.setStops(stops);
	        wtmsMessage.setAutoScheduleLoad(autoScheduleLoad);

	        // Remove certain "pos" objects
	        String posToRemove = "pos2";
	        wtmsMessage.getAutoScheduleLoad().getStops().forEach(stop ->
	            stop.getPosList().removeIf(pos -> posToRemove.equals(pos.getPosId()))
	        );

	        // Print the updated "pos" lists
	        wtmsMessage.getAutoScheduleLoad().getStops().forEach(stop -> {
	            System.out.println("Stop:");
	            stop.getPosList().forEach(pos -> System.out.println("  Pos: " + pos.getPosId()));
	        });
	    }
	

	
}
