import java.util.*;

public class prog_42579 {
    static class Music{
        String name;
        int play;
        int index;
        public Music(String name, int play, int index){
            this.name = name;
            this.play = play;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }

        public int getPlay() {
            return play;
        }
    }
    public static int[] solution(String[] genres, int[] plays){
        List<Integer> answer = new ArrayList<>();
        List<Music> playList = new ArrayList<>();
        for(int i=0;i<plays.length;i++) {
            playList.add(new Music(genres[i],plays[i],i));
        }

        playList.sort(Comparator.comparing(Music::getPlay)
                .reversed()
                .thenComparing(Music::getIndex)
        );

        Map<String, Integer> genresSum = new HashMap<>();
        Map<String, ArrayList<Integer>> genresPlays = new HashMap<>();
        for (Music m:playList){
            String name = m.getName();
            int index = m.getIndex();
            if (genresPlays.get(name) == null) genresPlays.put(name,new ArrayList<>());
            genresPlays.get(name).add(index);
            genresSum.put(name,genresSum.getOrDefault(name,0)+plays[index]);
        }

        List<String> keys = new ArrayList<>(genresSum.keySet());
        keys.sort(Comparator.comparingInt(genresSum::get)
                .reversed());

        for (String key: keys){
            int count = 0;
            for (int idx: genresPlays.get(key)){
                if (count > 1) break;
                answer.add(idx);
                count++;
            }
        }

        return answer.stream()
                .mapToInt(i->i)
                .toArray();
    }

    public static void main(String[] args) {
        String[] genres = {"classic","pop","classic","classic","pop"};
        int[] plays = {500,600,150,800,2500};
        int []answer = solution(genres,plays);
        for(int ans:answer){
            System.out.print(ans +" ");
        }
    }
}
