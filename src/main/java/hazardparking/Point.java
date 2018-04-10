package hazardparking;

/**
 *  This class was for formalizing the heatmap points, although conversion to heatPoints notation is required before
 *  sending back to the front end ([double longitude, double latitude, double intensity] )
 * @author Ethan Johnston
 */
public class Point {
    private double latitude;
    private double longitude;
    private double intensity;
    public Point(double longitude, double latitude, double intensity){
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensity = intensity;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public boolean equals(Point other){
        return this.latitude == other.getLatitude() && this.longitude == other.getLongitude();
    }
    public double[] getHeatPoint(){
        double [] out = {this.longitude, this.latitude, this.intensity};
        return out;
    }
}
