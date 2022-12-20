public class Result {
    private String finalPath;
    private int totalDistance;

    public Result(String finalPath, int totalDistance) {
        this.finalPath = finalPath;
        this.totalDistance = totalDistance;
    }

    public String getFinalPath() {
        return finalPath;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath = finalPath;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }
    private static void convertDataForPlotting() {
    }
}