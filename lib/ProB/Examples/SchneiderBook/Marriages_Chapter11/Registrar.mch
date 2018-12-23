MACHINE           Registrar
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
EXTENDS           Marriage

INCLUDES          Life

PROMOTES          born

OPERATIONS

  dies(nn) =
  PRE nn: PERSON & nn : male \/ female
  THEN
    die(nn) ||
    IF nn : dom(marriage)
    THEN part(nn,marriage(nn))
    ELSIF nn : ran(marriage)
    THEN part(marriage~(nn),nn)
    END
  END

END

