import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UndergroundSystem {
    Map<Integer, String> customerTripMap;
    Map<String, List<Integer>> tripsMap;

    public UndergroundSystem() {
        customerTripMap = new HashMap<Integer, String>();
        tripsMap = new HashMap<String, List<Integer>>();
    }

    public static void main(String[] args) {

        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge"));
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
        undergroundSystem.checkOut(10, "Waterloo", 38);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));
    }

    public void checkIn(int id, String stationName, int t) {
        String value = stationName + ", " + t;
        customerTripMap.put(id, value);
    }

    public void checkOut(int id, String stationName, int t) {
        String prevValue = customerTripMap.get(id);
        String[] array = prevValue.split(", ");
        String startStation = array[0];
        int startTime = Integer.parseInt(array[1]);
        String trip = startStation + ", " + stationName;
        int duration = t - startTime;
        List<Integer> list = tripsMap.getOrDefault(trip, new ArrayList<Integer>());
        list.add(duration);
        tripsMap.put(trip, list);
        customerTripMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String trip = startStation + ", " + endStation;
        List<Integer> list = tripsMap.getOrDefault(trip, new ArrayList<Integer>());
        if (list.size() == 0)
            return 0;
        int size = list.size();
        double sum = 0;
        for (int time : list)
            sum += time;
        return sum / size;
    }
}

