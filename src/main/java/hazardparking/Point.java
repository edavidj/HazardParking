package hazardparking;

/**
 * @author Ethan Johnston
 * @brief this class is an object for representing points in the heatmap
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
    public double[] getHeatPoint(){
        double [] out = {this.longitude, this.latitude, this.intensity};
        return out;
    }
}
