import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Vector {

    Point firstPoint, secondPoint;
    List<Double> vector;

    public Vector(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.vector = createVector(firstPoint, secondPoint);
    }

    public List<Double> createVector(Point firstPoint, Point secondPoint){
        List<Double> vector = List.of(secondPoint.getX() - firstPoint.getX(),
                secondPoint.getY() - firstPoint.getY(),
                secondPoint.getZ() - firstPoint.getZ());
       return vector;
    }
    public Double determineLength() {

       //sum mathematical vector elements squared
        Double sum = vector.stream().mapToDouble(i -> i*i).sum();

        return sum ;

    }

    @Override
    public String toString() {
        return "( "+ vector.get(0) + " " + vector.get(1) +  " " + vector.get(2) + " )";
    }
}
