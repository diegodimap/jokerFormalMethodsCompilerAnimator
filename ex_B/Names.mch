MACHINE           Names

SETS              FORENAME; SURNAME

VARIABLES         ss

INVARIANT         ss : SURNAME

INITIALISATION    ss :: SURNAME

OPERATIONS

  read(nn) = 
  PRE nn : SURNAME
  THEN ss := nn
  END

END
