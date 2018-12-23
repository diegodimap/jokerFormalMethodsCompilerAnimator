MACHINE           Player
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
SETS              ANSWER = {in, out}; PLAYER

DEFINITIONS
                  scope_PLAYER == 1..12  /* a pragma to indicate to ProB to use a set of size 12 */

PROPERTIES        card(PLAYER) > 11

VARIABLES         team

INVARIANT         team <: PLAYER & card(team) = 11

INITIALISATION    ANY tt WHERE tt <: PLAYER & card(tt) = 11 THEN team := tt END

OPERATIONS

  substitute(pp,rr) =
  PRE pp : team & rr : PLAYER & rr /: team
  THEN team := (team \/ {rr}) - {pp}
  END;

aa <-- query(pp) =
  PRE pp : PLAYER
  THEN IF pp : team 
       THEN aa := in
       ELSE aa := out
       END
  END

END
