import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class GeometricalForms {

    Vector v1, v2, v3, v4, v1Rev, v2Rev, v3Rev, v4Rev;

    public String determineShape(List<Point> points) {

        try {
            switch (points.size()) {
                case 0:
                    return "None";

                case 1:
                    return "Point";

                case 2:
                  return "Line";

                case 3:
               return "Triangle";

                case 4:
                    return determineRectangleShape(points);

                case 5:
                    return determineThreeDShape(points);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "None";
    }

    private List<List<Double> > separatePointsCoordinates(List<Point> points){

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> zValues = new ArrayList<>();

        points.stream().forEach(point ->{
            xValues.add(point.getY());
            yValues.add(point.getY());
            zValues.add(point.getZ());
        });

        return List.of(xValues, yValues, zValues);
    }



    //This method only checks if 4, 5 points are in one of planes
    //Return -1 if neither of the options


  // Remove all duplicates from coordinates and return only the coordinates
    // with exactly two distinct values
    private List<List<Double>> returnDistinctValues(List<Point> points) {
        List<List<Double>> coordinates = separatePointsCoordinates(points);

        List<Double> distinctX = coordinates.get(0).stream()
                .distinct()
                .collect(Collectors.toList());
        List<Double> distinctY = coordinates.get(1).stream()
                .distinct()
                .collect(Collectors.toList());
        List<Double> distinctZ = coordinates.get(2).stream()
                .distinct()
                .collect(Collectors.toList());


        return List.of(distinctX, distinctY, distinctZ);
    }

    private String determineThreeDShape(List<Point> points) {


        returnDistinctValues(points);

       // System.out.println("distinct values " + returnDistinctValues(points));
        List<List<Point>> pointsInPlane = sortSamePlane(points);

        String baseShape = determineRectangleShape(pointsInPlane.get(0));

        if(!baseShape.equals("Rectangle")) {

            return "3D Shape";
        }
        return "Pyramid";
    }


    //Return points that are in the same plane
    private List<List<Point>> sortSamePlane(List<Point> points) {

        List<List<Point>> filteredPointsList = new ArrayList<>();

        List<Point> filteredPoints = new ArrayList<>();

        List<List<Double>> distinctValues = returnDistinctValues(points);


        List<List<Double>> twoDistinctValue = distinctValues.stream().filter(
                value -> value.size() == 2
        ).collect(Collectors.toList());

        System.out.println("twoDistinctValue " + twoDistinctValue.get(0).size());
        System.out.println("distinctValue " + distinctValues);
        System.out.println("distinctValue " + distinctValues.size());


        for(int j = 1; j < 2; j++){
            int finalJ = j;
            for(int i = 0; i < distinctValues.size(); i++){

                if(i == 0){


                    filteredPoints = points.stream().
                            filter(point -> point.getX().equals(twoDistinctValue.get(0).get(finalJ)))
                                    .collect(Collectors.toList());

                }else  if(i == 1){

                     filteredPoints = points.stream().
                            filter(point -> point.getY().equals(twoDistinctValue.get(0).get(finalJ)))
                            .collect(Collectors.toList());

                }else  if(i == 2) {

                     filteredPoints = points.stream().
                            filter(point -> point.getZ().equals(twoDistinctValue.get(0).get(finalJ)))
                            .collect(Collectors.toList());

                }
            }


            filteredPointsList.add(filteredPoints);

            filteredPointsList.stream().forEach(System.out::println);

        }




/*
        System.out.println(filteredPoints.size());
        System.out.println(filteredPoints.get(1).getZ());
        System.out.println(filteredPoints.get(2).getZ());
        filteredPoints.forEach(System.out::println);
*/

        return filteredPointsList;
    }

    private String determineRectangleShape(List<Point> points) {

        //Create 3D mathematical vectors that are extended between two points
        v1 = new Vector(points.get(0), points.get(1));
        v2 = new Vector(points.get(1), points.get(2));
        v3 = new Vector(points.get(2), points.get(3));
        v4 = new Vector(points.get(3), points.get(0));

        System.out.println(v1.toString());
        System.out.println(v2.toString());
        System.out.println(v3.toString());
        System.out.println(v4.toString());

        v1Rev = new Vector(points.get(1), points.get(0));
        v2Rev = new Vector(points.get(2), points.get(1));
        v3Rev = new Vector(points.get(3), points.get(2));
        v4Rev = new Vector(points.get(3), points.get(0));


        //Returns the lengths of all side to be checked later for equality
        double[] lengths = {v1.determineLength(), v2.determineLength(), v3.determineLength(), v4.determineLength()};

        //Returns the angles between mathematical vectors
        List<Double> angles = List.of(determineAngle(v1,v4), determineAngle(v1Rev,v2), determineAngle(v2Rev, v3), determineAngle(v3Rev, v4Rev));



        //Get the lengths of those vectors to compare for equality
        //Each array contains adjacent vectors
        double[] l12 = new double[] {v1.determineLength(), v2.determineLength()};
        double[] l34 = new double[] {v3.determineLength(), v4.determineLength()};

       // returns true if all elements in lengths are equal
         boolean allLengthsEqual = stream(lengths).distinct().count() == 1;


        if (allLengthsEqual && angles.contains(Math.PI /2)){
            return "Square";
        } else if(allLengthsEqual && !angles.contains(Math.PI /2)){
            return "Rhombus";
        }

        else if (Arrays.equals(l12, l34) && angles.contains(Math.PI /2)){
        return "Rectangle";

        } else if (Arrays.equals(l12,l34) && !angles.contains(Math.PI /2) ){

            return "Parallelogram";
        }
        return "Quadrilateral";
    }

    // The definition of scalar product form linear algebra is used to
    //determine the angle between two mathematical vectors
    private double determineAngle(Vector v1, Vector v2) {
        double absV1 = v1.determineLength();
        double absV2 = v2.determineLength();
        double[] entrySum = new double[3];
        for(int i = 0; i < v1.vector.size(); i++){
            double entry = v1.vector.get(i) * v2.vector.get(i);
            entrySum[i] = entry;
        }
        return arcCos(stream(entrySum).sum()/absV1*absV2);

    }

    // Math.acos returns null for negative value, this custom method
    // handles that exception when the angles are still less than Math.PI
    private double arcCos(Double number) {


        if(number < 0){
           return Math.PI - Math.acos(Math.abs(number));
        } else{

            return Math.acos(number) ;
        }


    }


}

