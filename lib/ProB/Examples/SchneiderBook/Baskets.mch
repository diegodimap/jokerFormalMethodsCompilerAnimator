MACHINE           Baskets(CUSTOMER, GOODS)
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
VARIABLES         baskets

CONSTANTS         price

PROPERTIES        price : GOODS --> NAT1

INVARIANT         baskets : CUSTOMER +-> POW(GOODS)

INITIALISATION    baskets := {}

OPERATIONS

  enter(cu) =
  PRE cu : CUSTOMER & cu /: dom(baskets)
  THEN baskets(cu) := {}
  END;

  add(cu, gg) =
  PRE cu : dom(baskets) & gg : GOODS
  THEN baskets(cu) := baskets(cu) \/ {gg}
  END;
  
nn <-- checkout(cu) =
  PRE cu : dom(baskets)
  THEN nn := SIGMA (gg) . (gg : GOODS & gg: baskets(cu) | price(gg))
       || baskets := {cu} <<| baskets
  END

END