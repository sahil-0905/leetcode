class TimeMap {

    HashMap<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {

        if (!map.containsKey(key)) {
            map.put(key, new TreeMap<>());
        }

        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {

        if (!map.containsKey(key)) {
            return "";
        }

        TreeMap<Integer, String> timeMap = map.get(key);

        Integer time = timeMap.floorKey(timestamp);

        if (time == null) {
            return "";
        }

        return timeMap.get(time);
    }
}