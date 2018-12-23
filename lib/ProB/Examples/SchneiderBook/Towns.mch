MACHINE           Towns(TOWN)
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
SETS              ANSWER = {connected, notconnected}

VARIABLES         roads

INVARIANT         roads : TOWN <-> TOWN 

INITIALISATION    roads := {}

OPERATIONS

  link(tt1 , tt2) = 
  PRE tt1 : TOWN & tt2 : TOWN
  THEN roads := roads \/ {tt1 |-> tt2}
  END;

ans <-- connectedquery(tt1 , tt2) = 
  PRE tt1 : TOWN & tt2 : TOWN
  THEN
  IF tt1|->tt2 : closure1(roads \/ roads~) or (tt1 = tt2)
  THEN ans := connected
  ELSE ans := notconnected
  END
  END

END
