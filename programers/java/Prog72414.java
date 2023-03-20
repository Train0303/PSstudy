import java.util.*;

public class Prog72414 {
    class TimeLog{
        int start;
        int end;
        public TimeLog(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    long[] timeArray = new long[360000];
    public int convertToSec(String time){
        int result = 0;
        String[] timeSplit = time.split(":");
        result = Integer.parseInt(timeSplit[0])*3600 +
                Integer.parseInt(timeSplit[1])*60 +
                Integer.parseInt(timeSplit[2]);

        return result;
    }
    public List<TimeLog> convertToSecLogs(String[] logs){
        List<TimeLog> result = new ArrayList<>();
        for(String log: logs){
            String[] startEnd = log.split("-");
            int startTime = convertToSec(startEnd[0]);
            int endTime = convertToSec(startEnd[1]);
            result.add(new TimeLog(startTime, endTime));
        }
        return result;
    }
    public String convertSecToTime(int time){
        return String.format("%02d:%02d:%02d",
                time/3600, (time/60)%60, time%60);
    }
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeSec = convertToSec(play_time);
        int advTimeSec = convertToSec(adv_time);
        List<TimeLog> timeLogs = convertToSecLogs(logs);
        for(TimeLog t: timeLogs){
            timeArray[t.start] += 1;
            timeArray[t.end] += -1;
        }
        for(int i=1; i<timeArray.length; i++) timeArray[i] += timeArray[i-1];
        for(int i=1; i<timeArray.length; i++) timeArray[i] += timeArray[i-1];

        long maxTime = timeArray[advTimeSec];
        int answer = 0;
        for(int i=1; i<playTimeSec; i++){
            if(i + advTimeSec > playTimeSec) break;

            long totalTime = timeArray[i+advTimeSec-1] - timeArray[i-1];
            if(maxTime < totalTime){
                answer = i;
                // System.out.println(i);
                maxTime = totalTime;
            }
        }

        return convertSecToTime(answer);
    }
}