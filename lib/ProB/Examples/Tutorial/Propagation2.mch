MACHINE Propagation2
CONSTANTS low,up,f,fi,invert
PROPERTIES
 f:low..up --> 0..1 &
 invert: 0..1 --> 0..1 &
 fi = (f ; invert) &
 invert = {0|->1,1|->0} &
 low = 0 & up = 100
VARIABLES xx
INVARIANT
 xx:low..up
INITIALISATION xx:=low
OPERATIONS
  val <-- Sense = BEGIN val := f(xx) END;
  Inc = IF xx<up THEN xx := xx+1 ELSE xx := low END
END

