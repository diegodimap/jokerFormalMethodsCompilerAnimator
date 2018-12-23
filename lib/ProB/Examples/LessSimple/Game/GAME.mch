MACHINE	GAME 
/* Model of an electronic raffle; by Michael Butler */
USES
		PRIZE,
		TICKET
PROMOTES
       PRIZEcreate,TICKETcreate
SETS
		GAMESET

VARIABLES
		GAMEinstances
		, Tickets
		, Winners
		, Claimed
		, Drawn
		, Sold
		, Prizes

INVARIANT
		GAMEinstances  <: GAMESET
		& Tickets : GAMEinstances --> POW(TICKETinstances)
		& Winners : GAMEinstances --> POW(TICKETinstances)
		& Claimed : GAMEinstances --> POW(TICKETinstances)
		& Drawn : GAMEinstances --> BOOL
		& Sold : GAMEinstances --> NAT
		& Prizes : GAMEinstances --> POW(PRIZEinstances)
		& !(self).(self:GAMEinstances => card(Tickets(self)) = Sold(self))
		& !(self).(self:GAMEinstances => Winners(self) <: Tickets(self))
		& !(self).(self:GAMEinstances => Claimed(self) <: Winners(self))
		& !(self).(self:GAMEinstances => !(gg). (gg:GAMEinstances-{self} => Tickets(self) /\ Tickets(gg) = {}))
		
INITIALISATION
		GAMEinstances := {}
		|| Tickets := {}
		|| Winners := {}
		|| Claimed := {}
		|| Drawn := {}
		|| Sold := {}
		|| Prizes := {}

OPERATIONS
res <-- UnionTest = BEGIN res := UNION(gg).(gg:GAMEinstances | Tickets(gg)) END;

result  <-- GAMEcreate =
	PRE
		GAMEinstances  /= GAMESET	
	THEN
		ANY new
		WHERE
			new : GAMESET - GAMEinstances
		THEN
			GAMEinstances := GAMEinstances \/ {new }
			|| Tickets := Tickets <+ {new |-> {}}
			|| Winners := Winners <+ {new |-> {}}
			|| Claimed := Claimed <+ {new |-> {}}
			|| Drawn := Drawn <+ {new |-> FALSE}
			|| Sold := Sold <+ {new |-> 0}
			|| Prizes := Prizes <+ {new |-> {}}
			|| result := new
		END
	END

;
/*" Initialise the Prizes attribute with a set of Prizes "*/
 setprizes (self, pp) =
	PRE
		self  :  GAMEinstances
		& pp : POW(PRIZEinstances) 
		& Prizes(self) = {}
	THEN
		Prizes(self) := pp
	END
;
/*" If the game has had its Prizes set and has not been drawn then a ticket is added to the "*/
/*"  Tickets attribute and returned "*/
/*" operation return type should be TICKET "*/
result <--  buy (self) =
	PRE
		self  :  GAMEinstances 
		& Prizes(self) /= {}
		& Winners(self) = {}
		& Tickets(self) /= TICKETinstances

	THEN
		ANY tt WHERE 
			tt: TICKETinstances - UNION(gg).( gg:GAMEinstances | Tickets(gg) )
			/* chnaged by MJB, was tt: TICKETinstances - Tickets(self)   */
		THEN
		        Tickets(self) := Tickets(self) \/ {tt} ||
		        Sold(self) := Sold(self) +1 ||
		        result := tt
		END

	END
;
/*" If the game has been set up and not been drawn already and enough tickets have been sold "*/
/*"  to provide a winner for each prize then the game is drawn by selecting a subset of the "*/
/*"  tickets sold as winners of the prizes and true is returned. If  the game has been set "*/
/*"  up and not been drawn already but not enough tickets have been sold, false is returned "*/
/*" operation return type should be BOOL "*/
result <--  draw (self) =
	PRE
		self  :  GAMEinstances 
		& Prizes(self) /= {}
		& Winners(self) = {}

	THEN
		IF  card (Prizes(self)) < card (Tickets(self))
		THEN
		        ANY ww WHERE
		                ww : POW (Tickets(self)) &
		                card (ww) = card (Prizes(self))
		        THEN
		                Winners(self) := ww
		        END ||
		        Drawn(self) := TRUE ||
		        result := TRUE
		ELSE
		        result := FALSE
		END
	END
;
/*" If tt is in the set of winners but not in the set of claimed true is returned, otherwise "*/
/*"  false is returned "*/
/*" operation return type should be BOOL "*/
result <--  check (self, tt) =
	PRE
		self  :  GAMEinstances
		& tt : TICKETinstances 

	THEN
		IF tt : Winners(self) - Claimed(self)
		THEN
		        result := TRUE
		ELSE
		        result := FALSE
		END
	END
;
/*" If tt is in Winners but not in Claimed one of the prizes in Prizes is returned and is "*/
/*"  removed from Prizes and the ticket is added to claimed "*/
/*" operation return type should be PRIZE "*/
result <--  claim (self, tt) =
	PRE
		self  :  GAMEinstances
		& tt : TICKETinstances 
		& tt : Winners(self) - Claimed(self)
	THEN
		ANY pp WHERE pp :Prizes(self)
		THEN
		        Claimed(self) := Claimed(self) \/ {tt} ||
		        Prizes(self) := Prizes(self) - {pp} ||
		        result := pp
		END
	END

END







