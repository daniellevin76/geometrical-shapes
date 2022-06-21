
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
public class GeometricalFormsTest {

    GeometricalForms geometricalForms;

    @BeforeEach
    void setup(){
        geometricalForms = new GeometricalForms();

    }

    @Test
    void should_returnNone_when_AnEmptyListIsGiven(){


        List<Point> points = List.of();

        assertEquals("None", geometricalForms.determineShape(points));
    }

    @Test
    void should_returnPoint_when_onePointIsGiven(){

        Point p1 = new Point(2.0,10.0,7.0);


        List<Point> points = List.of(p1);

        assertEquals("Point", geometricalForms.determineShape(points));
    }


    @Test
    void should_returnLine_when_twoPointsAreGiven(){


        Point p1 = new Point(2.0,10.0,7.0);
        Point p2 = new Point(1.0,15.0,4.0);

        List<Point> points = List.of(p1, p2);

        assertEquals("Line", geometricalForms.determineShape(points));
    }



    @Test
    void should_returnATriangle_when_threeRandomPointsAreGiven(){

        Point p1 = new Point(1.0,2.0,3.0);
        Point p2 = new Point(2.0,5.0,3.0);
        Point p3 = new Point(2.0,5.0,5.0);
         List<Point>  points = List.of(p1, p2, p3);

        assertEquals("Triangle", geometricalForms.determineShape(points));
    }

    @Test
    void should_returnQuadrilateral_when_FourPointsAreGiven(){

        Point p1 = new Point(0.0,0.0,1.0);
        Point p2 = new Point(0.0,1.0,0.0);
        Point p3 = new Point(0.0,0.0,0.0);
        Point p4 = new Point(5.0,4.0,1.0);

        List<Point> points = List.of(p1, p2, p3, p4);

        assertEquals("Quadrilateral", geometricalForms.determineShape(points));
    }


    @Test
    void should_returnASquare_when_FourEquidistantPointsAreGiven(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(0.0,1.0,0.0);
        Point p3 = new Point(1.0,1.0,0.0);
        Point p4 = new Point(1.0,0.0,0.0);

        List<Point> points = List.of(p1, p2, p3, p4);

        assertEquals("Square", geometricalForms.determineShape(points));
    }

    @Test
    void should_returnAParallelogram_when_FourPointsThatAreGivenTwoIdenticalAngles(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(1.0,1.0,0.0);
        Point p3 = new Point(3.0,1.0,0.0);
        Point p4 = new Point(2.0,0.0,0.0);

        List<Point> points = List.of(p1, p2, p3, p4);

        assertEquals("Parallelogram", geometricalForms.determineShape(points));
    }

    @Test
    void should_return2DShape_when_FivePointsInTheSamePlaneThatAreGiven(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(1.0,1.0,0.0);
        Point p3 = new Point(1.0,5.0,0.0);
        Point p4 = new Point(1.0,0.0,0.0);
        Point p5 = new Point(2.0,1.0,0.0);

        List<Point> points = List.of(p1, p2, p3, p4, p5);

        assertEquals("2D Shape", geometricalForms.determineShape(points));
    }

    @Test
    void should_return3DShape_when_FivePointsNotAllInTheSamePlaneThatAreGiven(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(1.0,1.0,0.0);
        Point p3 = new Point(1.0,5.0,1.0);
        Point p4 = new Point(1.0,0.0,0.0);
        Point p5 = new Point(2.0,1.0,5.0);

        List<Point> points = List.of(p1, p2, p3, p4, p5);

        assertEquals("3D Shape", geometricalForms.determineShape(points));
    }

    @Test
    void should_return3DShape_when_FivePointsAreGivenFourPointsInTheSamePlane(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,2.0);
        Point p2 = new Point(1.0,1.0,5.0);
        Point p3 = new Point(1.0,5.0,5.0);
        Point p4 = new Point(1.0,0.0,5.0);
        Point p5 = new Point(2.0,1.0,5.0);

        List<Point> points = List.of(p1, p2, p3, p4, p5);

        assertEquals("3D Shape", geometricalForms.determineShape(points));
    }

    @Test
    void should_returnPyramid_when_FivePointsAreGivenFourPointsInTheSamePlaneShapeNotRandom(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(1.0,1.0,0.0);
        Point p3 = new Point(3.0,1.0,0.0);
        Point p4 = new Point(2.0,0.0,0.0);
        Point p5 = new Point(2.0,4.0,5.0);

        List<Point> points = List.of(p1, p2, p3, p4, p5);

        assertEquals("Pyramid", geometricalForms.determineShape(points));
    }

    @Test
    void should_return3D_when_EightRandomPointsAreGiven(){

        //For simplicity the points are assumed to given in such an order that
        // no two consecutive points will connect diagonally
        Point p1 = new Point(0.0,0.0,0.0);
        Point p2 = new Point(1.0,1.0,0.0);
        Point p3 = new Point(3.0,1.0,0.0);
        Point p4 = new Point(2.0,0.0,0.0);
        Point p5 = new Point(2.0,4.0,5.0);
        Point p6 = new Point(1.0,1.0,0.0);
        Point p7 = new Point(3.0,1.0,0.0);
        Point p8 = new Point(2.0,0.0,0.0);

        List<Point> points = List.of(p1, p2, p3, p4, p5, p6, p7, p8);

        assertEquals("Pyramid", geometricalForms.determineShape(points));
    }

}

