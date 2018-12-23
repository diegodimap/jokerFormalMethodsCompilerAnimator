MACHINE           Marriage
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
USES              Life

VARIABLES         marriage

INVARIANT         marriage : male >+> female

INITIALISATION    marriage := {}

OPERATIONS

  wed(mm,ff) =
  PRE mm : male & mm /: dom(marriage) & ff : female & ff /: ran(marriage)
  THEN marriage(mm) := ff
  END;

  part(mm,ff) =
  PRE mm : male & ff : female & mm |->ff : marriage
  THEN marriage := marriage - {mm |-> ff}
  END;

pp <-- partner(nn) =
  PRE nn: PERSON & nn : dom(marriage) \/ ran(marriage)
  THEN 
    IF nn : dom(marriage)
    THEN pp := marriage(nn)
    ELSE pp := marriage~(nn)
    END
  END

END
