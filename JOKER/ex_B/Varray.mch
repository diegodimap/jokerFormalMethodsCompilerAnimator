MACHINE           Varray(cap, VALUE)

CONSTRAINTS       cap : NAT1

VARIABLES         array

INVARIANT         array : 1..cap --> VALUE

INITIALISATION    array :: 1..cap --> VALUE

OPERATIONS

  set(ii,vv) =
  PRE ii : 1..cap & vv : VALUE
  THEN array(ii) := vv
  END;

vv <-- get(ii) =
  PRE ii : 1..cap
  THEN vv := array(ii)
  END

END
