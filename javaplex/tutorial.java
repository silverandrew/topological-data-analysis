import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.examples.PointCloudExamples;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.barcodes.AnnotatedBarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.metric.landmark.LandmarkSelector;
import edu.stanford.math.plex4.metric.landmark.MaxMinLandmarkSelector;
import edu.stanford.math.plex4.streams.impl.LazyWitnessStream;
import edu.stanford.math.plex4.autogen.homology.IntAbsoluteHomology;
import edu.stanford.math.primitivelib.algebraic.impl.ModularIntField;
import edu.stanford.math.plex4.homology.chain_basis.SimplexComparator;
import java.util.*;
import edu.stanford.math.plex4.streams.impl.ExplicitSimplexStream;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceBasisAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
//CompTop HW 3
public class tutorial {

  public void Example1(){ //homology of 2-sphere
    int d = 2;
    int prime = 2;
    ExplicitSimplexStream stream = new ExplicitSimplexStream();
    stream.addVertex(0);
    stream.addVertex(1);
    stream.addVertex(2);
    int[][] edges = {{0,1},{0,2},{1,2}};
    for(int i = 0; i != edges.length; ++i){
      stream.addElement(edges[i]);
    }
    stream.finalizeStream();
    System.out.println("Number of simpleces in complex: " + stream.getSize());

    AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getModularSimplicialAlgorithm(d + 1, prime);
    BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
    System.out.println("\nBarcodes for " + d + "-sphere:");
    System.out.println(intervals);

    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals = annotated_algorithm.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for " + d + "-sphere:");
    System.out.println(annotated_intervals);
  }

  public void Example2(){ //homology of 9-sphere based on taking boundary of 10 simplex
    int d = 9;
    int prime = 2;
    int[] cell = {0,1,2,3,4,5,6,7,8,9,10};
    ExplicitSimplexStream stream = new ExplicitSimplexStream();
    stream.addElement(cell); //adds a 10 simplex w/o faces
    stream.ensureAllFaces(); //makes the simplicial complex
    stream.removeElementIfPresent(cell); //removes the 10 simplex, leaving the boundary
    stream.finalizeStream();
    System.out.println("Number of simpleces in complex: " + stream.getSize());

    AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getModularSimplicialAlgorithm(d + 1, prime);
    BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
    System.out.println("\nBarcodes for " + d + "-sphere:");
    System.out.println(intervals);

    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals = annotated_algorithm.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for " + d + "-sphere:");
    System.out.println(annotated_intervals);
  }

  public void r9(){ //R9
    System.out.println("PROBLEM 1");
    int d = 2;
    int prime = 2;

  }

  public void prob1(){ //Vietoris Rips from Figure 8
    System.out.println("PROBLEM 1");
    int d = 2;
    int prime = 2;
    double points[][] = {{-3, 0}, {2, 1}, {-2, -1}, {-1, 0.5}, {-1, -0.5}, {-0.5, 0.25},
      {-0.5, -0.25}, {0.5, 0.25}, {0.5, -0.25}, {1, 0.5}, {1, -0.5}, {2, 1}, {2, -1}, {3, 0}};
    int max_dimension = 3;
    double max_filtration_value = 4;
    int num_divisions = 100;
    VietorisRipsStream stream = Plex4.createVietorisRipsStream(points, max_dimension, max_filtration_value, num_divisions); 
    
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals = annotated_algorithm.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for figure 8");
    System.out.println(annotated_intervals);

  }

  public void prob2(){ //klein bottle, edges/triangles generated by sage to avoid user error
    System.out.println("PROBLEM 2");
    int d = 2;
    int prime = 2;
    ExplicitSimplexStream stream = new ExplicitSimplexStream();

    stream.addVertex(1);
    stream.addVertex(2);
    stream.addVertex(3);
    stream.addVertex(4);
    stream.addVertex(5);
    stream.addVertex(6);
    stream.addVertex(7);
    stream.addVertex(8);
    stream.addVertex(9);
    int[][] edges = 
    {{3, 7},
      {4, 9},
      {1, 9},
      {3, 6},
      {4, 8},
      {5, 6},
      {2, 6},
      {6, 7},
      {2, 8},
      {6, 9},
      {8, 9},
      {5, 8},
      {1, 7},
      {4, 7},
      {1, 3},
      {2, 7},
      {1, 4},
      {3, 9},
      {7, 8},
      {5, 9},
      {2, 3},
      {4, 6},
      {2, 5},
      {3, 8},
      {1, 2},
      {1, 5},
      {4, 5}};
    int[][] triangles =
    {{1, 3, 7},
      {5, 6, 9},
      {2, 3, 6},
      {2, 5, 6},
      {4, 6, 7},
      {1, 4, 9},
      {2, 7, 8},
      {1, 2, 5},
      {4, 5, 8},
      {1, 2, 7},
      {3, 6, 7},
      {4, 6, 9},
      {5, 8, 9},
      {1, 4, 5},
      {3, 8, 9},
      {4, 7, 8},
      {2, 3, 8},
      {1, 3, 9}};

    for(int i = 0; i != edges.length; ++i){
      stream.addElement(edges[i]);
    }
    for(int i = 0; i != triangles.length; ++i){
      stream.addElement(triangles[i]);
    }
    stream.finalizeStream();
    System.out.println("Number of simpleces in klein complex: " + stream.getSize());
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod2 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod2 = annotated_algorithm_mod2.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for klein mod" + prime);
    System.out.println(annotated_intervals_mod2);
    prime += 1; //switch to mod 3
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod3 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod3 = annotated_algorithm_mod3.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for klein mod" + prime);
    System.out.println(annotated_intervals_mod3);
  }
  public void prob3(){ //RP2 unfiltered, edges/triangles generated by sage to avoid user error
    System.out.println("PROBLEM 3");
    int d = 2;
    int prime = 2;
    ExplicitSimplexStream stream = new ExplicitSimplexStream();
    stream.addVertex(1);
    stream.addVertex(2);
    stream.addVertex(3);
    stream.addVertex(4);
    stream.addVertex(5);
    stream.addVertex(6);
    int[][] edges = 
    {{2, 4},
      {3, 6},
      {5, 6},
      {1, 5},
      {1, 4},
      {3, 5},
      {3, 4},
      {2, 3},
      {4, 6},
      {2, 5},
      {1, 2},
      {1, 3},
      {1, 6},
      {2, 6},
      {4, 5}};
    int[][] triangles =
    {{1, 3, 5},
      {2, 5, 6},
      {3, 4, 5},
      {1, 3, 6},
      {2, 3, 4},
      {1, 2, 5},
      {1, 2, 4},
      {2, 3, 6},
      {1, 4, 6},
      {4, 5, 6}};

    for(int i = 0; i != edges.length; ++i){
      stream.addElement(edges[i]);
    }
    for(int i = 0; i != triangles.length; ++i){
      stream.addElement(triangles[i]);
    }
    stream.finalizeStream();
    System.out.println("Number of simpleces in RP2 complex: " + stream.getSize());
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod2 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod2 = annotated_algorithm_mod2.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for RP2 mod" + prime);
    System.out.println(annotated_intervals_mod2);
    prime += 1; //switch to mod 3
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod3 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod3 = annotated_algorithm_mod3.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for RP2 mod" + prime);
    System.out.println(annotated_intervals_mod3);
  }

  public void prob4(){ //RP2 filtered, edges/triangles generated by sage to avoid user error
    System.out.println("PROBLEM 4");
    int d = 2;
    int prime = 2;
    ExplicitSimplexStream stream = new ExplicitSimplexStream();
    stream.addVertex(1,0);
    stream.addVertex(2,0);
    stream.addVertex(3,0);
    stream.addVertex(4,0);
    stream.addVertex(5,0);
    stream.addVertex(6,0);
    int[][] edges = 
    {{1, 2},
      {1, 3},
      {2, 3},
      {4, 5},
      {4, 6},
      {5, 6},
      {2, 4},
      {3, 6},
      {1, 5},
      {1, 4},
      {3, 5},
      {3, 4},
      {2, 5},
      {1, 6},
      {2, 6}};
    int[][] triangles =
    {{4, 5, 6},
      {2, 5, 6},
      {1, 4, 6},
      {3, 4, 5},
      {1, 3, 5},
      {1, 3, 6},
      {2, 3, 4},
      {1, 2, 5},
      {1, 2, 4},
      {2, 3, 6}};

    stream.addElement(edges[0],1);
    stream.addElement(edges[1],1);
    stream.addElement(edges[2],1);
    stream.addElement(edges[3],2);
    stream.addElement(edges[4],2);
    stream.addElement(edges[5],2);
    for(int i = 6; i != edges.length; ++i){
      stream.addElement(edges[i],3);
    }
    stream.addElement(triangles[0],4);
    stream.addElement(triangles[1],4);
    stream.addElement(triangles[2],4);
    stream.addElement(triangles[3],4);
    for(int i = 4; i != triangles.length; ++i){
      stream.addElement(triangles[i],5);
    }

    stream.finalizeStream();
    System.out.println("Number of simpleces in RP2 complex: " + stream.getSize());
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod2 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod2 = annotated_algorithm_mod2.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for filtered RP2 mod" + prime);
    System.out.println(annotated_intervals_mod2);
    prime += 1; //switch to mod 3
    AbstractPersistenceBasisAlgorithm<Simplex, int[]> annotated_algorithm_mod3 = new IntAbsoluteHomology(ModularIntField.getInstance(prime), SimplexComparator.getInstance(), 0, d+1);
    AnnotatedBarcodeCollection<Double, int[]> annotated_intervals_mod3 = annotated_algorithm_mod3.computeAnnotatedIntervals(stream);
    System.out.println("\nAnnotated Barcodes for filtered RP2 mod" + prime);
    System.out.println(annotated_intervals_mod3);
  }

  //not optimized to minimize duplicated code for clarity
  public static void main(String[] args) {
    tutorial t = new tutorial();
    t.prob1();
    t.prob2();
    t.prob3();
    t.prob4();
  }
}

