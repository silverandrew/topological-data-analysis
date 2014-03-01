#include "number.h"

void Pair::setX(double xpos){
x = xpos;
}

void Pair::setY(double ypos){
y = ypos;
}

double Pair::getX() const{
  return x;
}

double Pair::getY() const{
  return y;
}

Pair& Pair::operator==(Pair& p) const{
  return (x == p.x && y == p.y);
}

Pair& Pair::operator=(Pair& p) const{ //update x and y values to those of p
  x = p.x; 
  y = p.y;
  return this;
}
