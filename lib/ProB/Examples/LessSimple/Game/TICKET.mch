MACHINE	TICKET 

SETS
		TICKETSET

VARIABLES
		TICKETinstances

INVARIANT
		TICKETinstances  <: TICKETSET

		
INITIALISATION
		TICKETinstances := {}

OPERATIONS
result  <-- TICKETcreate =
	PRE
		TICKETinstances  /= TICKETSET	
	THEN
		ANY new
		WHERE
			new : TICKETSET - TICKETinstances
		THEN
			TICKETinstances := TICKETinstances \/ {new }
			|| result := new
		END
	END


END


