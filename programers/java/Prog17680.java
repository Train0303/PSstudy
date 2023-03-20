import java.util.*;

public class Prog17680 {

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        String[] lowerCities = Arrays.stream(cities)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        Set<String> citySet = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();

        for(String city: lowerCities){
            if(cacheSize == 0){
                answer += 5;
                continue;
            }

            if(citySet.contains(city)){
                q.remove(city);
                q.add(city);
                answer++;
            }else if(citySet.size() == cacheSize){
                String s = q.poll();
                citySet.remove(s);
                q.add(city);
                citySet.add(city);
                answer += 5;
            }else{
                q.add(city);
                citySet.add(city);
                answer += 5;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize, cities));
    }
}
