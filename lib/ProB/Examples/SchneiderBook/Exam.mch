MACHINE           Exam
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
SETS              CANDIDATE

VARIABLES         marks

INVARIANT         marks : CANDIDATE +-> 0..100

INITIALISATION    marks := {}

OPERATIONS

  enter(cc,nn) =
  PRE cc : CANDIDATE & cc /: dom(marks) & nn : 0..100
  THEN marks(cc) := nn
  END;

aa <-- average =
  PRE marks /= {}
  THEN aa := (SIGMA zz . (zz : dom(marks) | marks(zz))) / card(dom(marks)) 
  END;

nn <-- number =
  nn := card(dom(marks))

END
