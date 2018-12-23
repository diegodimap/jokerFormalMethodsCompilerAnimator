MACHINE           Club

     /* In orde to relate the parameter capacity to the set NAME in the properties claus

     we move the parameter and the set to a separate machine */

     /* (capacity) */

 

SEES              NAME_ctx, CAPACITY_ctx

 

SETS              REPORT = {yes, no}

     /* ;NAME */

 

CONSTANTS         total

 

PROPERTIES        card(NAME) > capacity  & total : NAT1 & total < 4096

 

VARIABLES         member, waiting

 

INVARIANT         member <: NAME & waiting <: NAME

                  & member /\ waiting = {}

                  & card(member) <= 4096

                  & card(waiting) <= total

 

INITIALISATION    member := {} || waiting := {}

 

OPERATIONS

 

  join(nn) =

  PRE nn : waiting & card(member) < capacity

  THEN member := member \/ {nn} || waiting := waiting - {nn}

  END;

 

  join_queue(nn) =

  PRE nn : NAME & nn /: member & nn /: waiting

      & card(waiting) < total

  THEN waiting := waiting \/ {nn}

  END;

 

  remove(nn) =

  PRE nn : member

  THEN member := member - {nn}

  END;

 

  semi_reset = member, waiting := {}, member;

 

ans <-- query_membership(nn) =

  PRE nn : NAME

  THEN

    IF nn : member

    THEN ans := yes

    ELSE ans := no

    END

  END

 

END