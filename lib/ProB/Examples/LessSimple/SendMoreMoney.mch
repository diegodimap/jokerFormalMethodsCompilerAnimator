MACHINE SendMoreMoney
/* An example showcasing the new CLP(FD) capabilities of ProB */
CONSTANTS S,E,N,D, M,O,R, Y
PROPERTIES
   S : 1..9 & E : 0..9 & N : 0..9 &
   D : 0..9 & M : 1..9 & O : 0..9 & 
   R : 0..9 & Y : 0..9 &
   S >0 & M >0 & 
   card({S,E,N,D, M,O,R, Y}) = 8 & 
   S*1000 + E*100 + N*10 + D +
   M*1000 + O*100 + R*10 + E =
   M*10000 + O*1000 + N*100 + E*10 + Y
DEFINITIONS SET_PREF_CLPFD == TRUE
OPERATIONS
  r <-- GetS = BEGIN r := S END;

  s,e,n,d, m,o,r, y <-- GetSol = BEGIN s,e,n,d, m,o,r, y := S,E,N,D, M,O,R, Y END
END

