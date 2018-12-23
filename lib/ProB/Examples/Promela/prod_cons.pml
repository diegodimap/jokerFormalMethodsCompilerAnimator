mtype = { P, C };
mtype turn = P;

active proctype producer() 
{ 
        do 
        :: (turn == P) -> 
               printf("Produce"); 
               turn = C 
        od 
} 

active proctype consumer() 
{ 
        do 
        :: (turn == C) -> 
               printf("Consume"); 
               turn = P 
        od 
}


