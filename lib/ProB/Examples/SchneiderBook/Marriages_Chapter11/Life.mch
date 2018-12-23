MACHINE           Life
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
SETS              PERSON; SEX = {boy, girl}

VARIABLES         male, female

INVARIANT         male <: PERSON & female <: PERSON & male /\ female = {}

INITIALISATION    male := {} || female := {}

OPERATIONS

  born(nn,ss) =
  PRE nn : PERSON & nn /: (male \/ female) & ss : SEX
  THEN
    IF ss = boy
    THEN male := male \/ {nn}
    ELSE female := female \/ {nn}
    END
  END;

  die(nn) =
  PRE nn : PERSON & nn : male \/ female
  THEN 
    IF nn : male
    THEN male := male - {nn}
    ELSE female := female - {nn}
    END
  END

END
