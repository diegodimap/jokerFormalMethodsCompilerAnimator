MACHINE           Archive(ELEM)

VARIABLES         read, entries

INVARIANT         read : NAT & entries : seq(ELEM)

INITIALISATION    read := 0 || entries := {}

OPERATIONS

ee <-- lookup =
  PRE read : NAT1 & read <= size(entries)
  THEN ee := entries(read) 
  END;

  resetread = read := 0;

  incread = read := read + 1;

  enter(ee) =
  PRE ee : ELEM
  THEN entries := entries <- ee
  END

END
