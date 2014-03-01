//locate landmarks for a witness complex in R2 using the maxmin method, and generate distance matrix from points to landmarks 
//for visually creating the witness complex

#include "number.h"
#include <vector>

double distance(Pair& p1, Pair& p2){
  return sqrt((p2.getX() - p1.getX())*(p2.getX() - p1.getX()) +(p2.getY() - p1.getY())*(p2.getY() - p1.getY())); 
}      

//return index of landmark a postd::size_t is closest to
int closestLandmark(std::vector<Pair>& landmarks, Pair& point){ 
  std::size_t idx = 0; //assume maximum at 0
  for(std::size_t i = 0; i != landmarks.size(); ++i){
    if(distance(landmarks[i], point) < distance(landmarks[idx], point)){
      idx = i; //update idx to reflect new closer landmark
    }
  }
  return idx;
}

void makeLandmark(std::vector<Pair> & landmarks, std::vector<Pair>&points){ //make landmark set
  std::vector<std::vector<Pair>> sections; //idx corresponds to landmark index of pairs that are closest
  sections.resize(landmarks.size()); //resize to size of landmarks, this is how many sections
  for(std::size_t i = 0; i != points.size(); ++i){
    std::size_t idx_landmark = closestLandmark(landmarks, points[i]);
    sections[idx_landmark].push_back(points[i]); //store the points closest to each landmark in sections
  }
  int maxDist = distance(sections[0][0], landmarks[0]); //assume max at point 0
  Pair newLandmark(sections[0][0].getX(),sections[0][0].getY()); //assume new landmark is at start
  for(std::size_t i = 0; i != sections.size(); ++i){ //find the farthest point away for landmark
    for(int j = 0; j != sections[i].size(); ++j){
      if(distance(sections[i][j], landmarks[i]) > maxDist){
        newLandmark.setX(sections[i][j].getX()); //update potential landmark's location in data
        newLandmark.setY(sections[i][j].getY());
      }
    }
  }
  landmarks.push_back(newLandmark); //add new landmark to landmark set
  int pointIdx;
  for(std::size_t i = 0; i != points.size(); ++i){ //find the index of the point that became a landmark
    if(points[i].getX() == newLandmark.getX() && points[i].getY() == newLandmark.getY()){
      pointIdx = i;
    }
  }
  points.erase(points.begin() + pointIdx); //delete point that became a landmark from point vector, idx 0 + idx of point
}

void printDist(std::vector<Pair>& landmarks, std::vector<Pair>& points){ //print a matrix of distances between landmarks and points remaining, spot check for witness complex
  for(std::size_t i = 0; i != landmarks.size(); ++i){ //print first row
    std::cout << "       " << i << " ";
  }
  std::cout << "" << std::endl;
  for(std::size_t i = 0; i!= points.size(); ++i){ //rows are points
    std::cout << "(" << points[i].getX() << ", " << points[i].getY() << ") ";
    for(int j = 0; j != landmarks.size(); ++j){ //cols are landmarks
    std::cout << distance(points[i], landmarks[j]) << " ";
  }
    std::cout << "" << std::endl;
}
}

int main(){
  std::vector<Pair> landmarks;
  std::vector<Pair> orig_points;
  double x; 
  double y;
  unsigned int npoints = 13;
  unsigned int nlandmarks = 5; //nlandmarks = totalnumberoflandmarks - 1 (-1 for the seed)
  for(std::size_t i = 1; i != npoints + 1; ++i){  //13 points total, not counting landmark
  //  std::cout << "Enter point " << i << ".x" << std::endl;
    std::cin >> x;
  //  std::cout << "Enter point " << i << ".y" << std::endl;
    std::cin >> y;
    Pair p(x,y); //construct point
    orig_points.push_back(p); //store the postd::size_t in the vector of points
  }
  std::cout << "Enter seed" << std::endl;
//  std::cout << "Enter point.x" << std::endl;
  std::cin >> x;
 // std::cout << "Enter point.y" << std::endl;
  std::cin >> y;
  Pair p(x,y); //construct seed point
  landmarks.push_back(p); //store seed
  std::vector<Pair> freePoints = orig_points; //freePoints will be modified later
  for(std::size_t i = 0; i != nlandmarks; ++i){ //construct landmark set
    makeLandmark(landmarks,freePoints);
  }
  std::cout << "Landmark points:" << std::endl;
  for(std::size_t i = 0; i != landmarks.size(); ++i){
    std::cout << "B" << i << ": (" << landmarks[i].getX() << ", " << landmarks[i].getY() << ")" << std::endl;
  }
  printDist(landmarks,freePoints); //print distance matrix between landmarks and remaining points
  return 0;
}
