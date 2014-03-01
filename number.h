#ifndef _PAIR_N_
#define _PAIR_N_

#include <iostream>
#include <math.h>
#include <string>

class Pair{ //ordered pair (a,b)

  double x;
  double y;
  public :
    Pair () : x(0), y(0){} //point at origin, default
  Pair(double x, double y) : x(x), y(y){}
  double getX() const;
  void setX(double xpos);
  void setY(double ypos);
  double getY() const;
};
#endif
