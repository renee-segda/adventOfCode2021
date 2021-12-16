package day13;

public class Intercept {
 
    private int intercept;
    private InterceptAxis intersectAxis;

    public Intercept(int intercept, InterceptAxis axis) {
        setIntercept(intercept);
        setIntersectAxis(axis);
    }

    public void setIntercept(int intercept) {
        this.intercept = intercept;
    }

    public int getIntercept() {
        return intercept;
    }

    public void setIntersectAxis(InterceptAxis intersectAxis) {
        this.intersectAxis = intersectAxis;
    }

    public InterceptAxis getIntersectAxis() {
        return intersectAxis;
    }
}
