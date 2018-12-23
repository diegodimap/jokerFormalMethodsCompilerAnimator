MACHINE           Priorityqueue
/* This B Machine is taken from the book: The B-Method: An Introduction
   (Editor Palgrave, ISBN 0-33-79284-X, Year 2002) by Steve Schneider.
   This machine is distributed with kind permission by Steve Schneider.
*/
VARIABLES         queue

INVARIANT         queue : seq(NAT) 
                  & !xx . (xx : 1..size(queue)-1 => (queue(xx) <= queue(xx+1)))

INITIALISATION    queue := <>

OPERATIONS

  insert(nn) =
  PRE nn : NAT
  THEN 
    SELECT queue = <> THEN queue := [nn]
    WHEN queue /= <> & nn <= min(ran(queue)) THEN queue := nn -> queue
    WHEN queue /= <> & nn >= max(ran(queue)) THEN queue := queue <- nn
    ELSE ANY xx
         WHERE xx : 1..size(queue)-1 & queue(xx) <= nn & nn <= queue(xx+1)
         THEN queue := (queue /|\ xx) ^ [nn] ^ (queue \|/ xx)
         END
    END
  END;

nn <-- extract =
  PRE queue /= <>
  THEN nn := first(queue) || queue := tail(queue)
  END

END
