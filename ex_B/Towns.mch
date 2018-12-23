MACHINE           Towns(TOWN)
SETS              ANSWER = {connected, notconnected}
VARIABLES         road
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
  IF tt1|->tt2 : closure(roads \/ roads~) or (tt1 = tt2)
  THEN ans := connected
  ELSE ans := notconnected
  END
  END
END
