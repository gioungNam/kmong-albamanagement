package data;

public class Schedule {
    private String userId;
    private int startTime;
    private int endTime;


    public Schedule(String userId, int startTime, int endTime) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public int getStartTime() {
        return startTime;
    }
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    public int getEndTime() {
        return endTime;
    }
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "Schedule [userId=" + userId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
    

    
    
}
