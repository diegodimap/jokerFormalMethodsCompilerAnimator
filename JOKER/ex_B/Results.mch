MACHINE           Results

SETS              RUNNER

VARIABLES         finish

INVARIANT         finish : iseq(RUNNER)

INITIALISATION    finish := <>

OPERATIONS

finished(rr) =
  PRE rr : RUNNER & rr /: ran(finish)
  THEN finish := finish <- rr
  END;

rr <-- query(pp) =
  PRE pp : NAT1 & pp <= size(finish)
  THEN rr := finish(pp)
  END;

disqualify(pp) =
  PRE pp : NAT1 & pp <= size(finish)
  THEN finish := (finish /|\ pp-1) ^ (finish \|/ pp)
  END;

ss <-- medals =
  ss := finish /|\ 3

END
