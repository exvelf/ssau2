package functions;

public class TabulatedFunction {
    private FunctionsPoint[] points;
    private int pointCount;

    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        points = new FunctionsPoint[pointsCount];
        pointCount = pointsCount;
        double step=(rightX-leftX)/(pointsCount-1);
        for (int i=0; i<pointsCount; i++){
            double x=leftX+i+step;
            points [i] = new FunctionsPoint(x,0);
        }

    }

    public TabulatedFunction(double leftX, double rightX, double[] values) {
        points = new FunctionsPoint[values.length];
        pointCount = values.length;
        double step=(rightX-leftX)/(values.length-1);
        for (int i=0; i< values.length; i++){
            double x=leftX+i+step;
            points [i] = new FunctionsPoint(x,values[i]);
        }
    }
    public double getLeftDomainBorder(){
        return points[0].getX();
    }
    public double getRightDomainBorder(){
        return points[pointCount-1].getX();
    }

    public double getFunctionValue(double x){
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()){
            return Double.NaN;
        }
        for (int i = 1; i < pointCount; i++){
            if (x <= points[i].getX()){
                FunctionsPoint point1 = points[i-1];
                FunctionsPoint point2 = points[i];
                double key = (point2.getY() - point1.getY())/(point2.getX()-point1.getY());
                return point1.getY() + key * (x - point1.getX());
            }
        }
        return points[pointCount-1].getY();
    }
    public int getPointCount(){
        return pointCount;
    }
    private void IndeOutOfBoundsTest(int index){
        if (index < 0 || index >= pointCount){
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
    }

    public FunctionsPoint getPoint(int index){
        IndeOutOfBoundsTest(index);
        return points[index];
    }

    public void setPoint(int index, FunctionsPoint point){
        IndeOutOfBoundsTest(index);
        if ((index > 0 && point.getX() <= points[index-1].getX())
                || (index < pointCount-1 && point.getX() >= points[index+1].getX())){
            return;
        }
        points[index] = new FunctionsPoint(point);
    }

    public void setPointX(int index, double x){
        IndeOutOfBoundsTest(index);
        if ((index > 0 && x <= points[index-1].getX())
                || (index < pointCount-1 && x >= points[index+1].getX())){
            return;
        }
        points[index].setX(x);
    }
    public void setPointY(int index, double y){
        IndeOutOfBoundsTest(index);
        if ((index > 0 && y <= points[index-1].getY())
                || (index < pointCount-1 && y >= points[index+1].getY())){
            return;
        }
        points[index].setY(y);
    }
    public void deletePoint(int index){
        IndeOutOfBoundsTest(index);
        System.arraycopy(points,index+1,points,index,pointCount-index-1);
        pointCount--;
    }
    public void addPoint(FunctionsPoint point){
        if (pointCount == points.length){
            FunctionsPoint[] newPoints = new FunctionsPoint[points.length+1];
            System.arraycopy(points,0,newPoints,0,points.length);
            points=newPoints;
        }
        int insertIndex = 0;
        while (insertIndex < pointCount && points[insertIndex].getX() < point.getX()){
            insertIndex++;
        }
        if (insertIndex > 0 && insertIndex<pointCount && points[insertIndex-1].getX() >= point.getX()){
            return;
        }
        System.arraycopy(points,insertIndex,points,insertIndex+1,pointCount-insertIndex);
        points[insertIndex] = new FunctionsPoint(point);
        pointCount++;
    }
    public void displayPoint(){
        System.out.print("точки функции: ");
        for (int i=0; i<pointCount; i++){
            System.out.print(points[i] + (i < pointCount-1 ? ", ": ""));
        }
        System.out.println();
    }
}
