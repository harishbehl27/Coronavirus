package io.practise.Coronavirus.models;

public class LocationStats {

    private String state;
    private String country;
    private int latestTotalScore;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalScore() {
        return latestTotalScore;
    }

    public void setLatestTotalScore(int latestTotalScore) {
        this.latestTotalScore = latestTotalScore;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalScore=" + latestTotalScore +
                '}';
    }
}
