MACHINE TwoQueensSevenKnights
/* A puzzle trying to place two queens and seven knights on a 6x6 chess board */
/* Idea came from: http://cairnarvon.rotahall.org/2010/06/20/processing-constraints-is-easy/ */
DEFINITIONS
 Q1 == 0;
 Q2 == 1;
 K1 == 2;
 K2 == 3;
 K6 == 7;
 K7 == 8;
 less(p1,p2) == (xcoord(p1)<=xcoord(p2) & (xcoord(p1)=xcoord(p2) => ycoord(p1)<ycoord(p2)));
 KMOVES(x,y) == {dx,dy | (dx=x+1 & dy=y+2) or (dx=x+1 & dy=y-2) or
                         (dx=x-1 & dy=y+2) or (dx=x-1 & dy=y-2) or
                         (dx=x+2 & dy=y+1) or (dx=x+2 & dy=y-1) or
                         (dx=x-2 & dy=y+1) or (dx=x-2 & dy=y-1) };
 KnightAttack(x,y,dx,dy) == ((dx=x+1 & dy=y+2) or (dx=x+1 & dy=y-2) or
                             (dx=x-1 & dy=y+2) or (dx=x-1 & dy=y-2) or
                             (dx=x+2 & dy=y+1) or (dx=x+2 & dy=y-1) or
                             (dx=x-2 & dy=y+1) or (dx=x-2 & dy=y-1));
 KnightNoAttackOr(x,y,dx,dy) == ((dx/=x+1 or dy/=y+2) & (dx/=x+1 or dy/=y-2) &
                             (dx/=x-1 or dy/=y+2) & (dx/=x-1 or dy/=y-2) &
                             (dx/=x+2 or dy/=y+1) & (dx/=x+2 or dy/=y-1) &
                             (dx/=x-2 or dy/=y+1) & (dx/=x-2 or dy/=y-1));
 KnightNoAttack(x,y,dx,dy) == ((dx,dy)/=(x+1,y+2) & (dx,dy)/=(x-1,y+2) &
                               (dx,dy)/=(x+1,y-2) & (dx,dy)/=(x-1,y-2) &
                               (dx,dy)/=(x+2,y+1) & (dx,dy)/=(x-2,y+1) &
                               (dx,dy)/=(x+2,y-1) & (dx,dy)/=(x-2,y-1) );
      BWOFFSET(k) == (xcoord(k)+ycoord(k)) mod 2;
      ANIMATION_FUNCTION_DEFAULT == ( {r,c,i|r:1..n & c:1..n & i=(r+c) mod 2 }  );
      ANIMATION_FUNCTION == ( {(xcoord(Q1),ycoord(Q1),2+BWOFFSET(Q1)), 
                               (xcoord(Q2),ycoord(Q2),2+BWOFFSET(Q2))} \/
                              UNION(k).(k:K1..K7| {(xcoord(k),ycoord(k),4+BWOFFSET(k))})   );
      ANIMATION_IMG0 == "images/sm_empty_box.gif";
      ANIMATION_IMG1 == "images/sm_gray_box.gif";
      ANIMATION_IMG2 == "images/sm_queen_white.gif";
      ANIMATION_IMG3 == "images/sm_queen_black.gif";
      ANIMATION_IMG4 == "images/sm_knight_white.gif";
      ANIMATION_IMG5 == "images/sm_knight_black.gif";
 SET_PREF_MININT == 0;
 SET_PREF_MAXINT == 8;
 SET_PREF_TIME_OUT == 3200;
 SET_PREF_CLPFD == TRUE;
 SET_PREF_MAX_INITIALISATIONS == 5
/* Initially did work very well; now takes 0.56 seconds to find first solution with CLP(FD) on; 3.18 seconds without
*/
CONSTANTS n, xcoord, ycoord
PROPERTIES
 n=6 &
 xcoord : Q1..K7 --> 1..n &
 ycoord : Q1..K7 --> 1..n &
 
 /* no other piece i is attacked by a queen */
 !(q,i).(q:Q1..Q2 & i:Q2..K7 & i>q => xcoord(q) /= xcoord(i) &
                 ycoord(q) /= ycoord(i) &
                 xcoord(i)-xcoord(q) /= ycoord(i)-ycoord(q) &
                 xcoord(i)-xcoord(q) /= ycoord(q)-ycoord(i) ) &
                 
  /* all pieces are on different squares: */
 /* This is implied by second ordering constraint below */
/* !(i,j).(i:Q1..K6 & j>i & j<=K7 =>  (xcoord(i),ycoord(i)) /= (xcoord(j),ycoord(j))) &
 */
 
 /* nobody is attacked by a knight k1 */
 !(k1,k2).(k1:K1..K7 & (k2:Q1..Q2 or (k2:K2..K7 & k2>k1))  =>
             KnightNoAttack(xcoord(k1),ycoord(k1),xcoord(k2),ycoord(k2)) )
            /* (xcoord(k2)|->ycoord(k2)) /: KMOVES(xcoord(k1),ycoord(k1)) ) */ &

  /* some symmetry breaking ordering constraints */
  less(Q1,Q2) &
  !(k1,k2).(k1:K1..K6 & k2:K2..K7 & k1<k2 => less(k1,k2))
OPERATIONS
  x,y <-- Queen1 = BEGIN x:= xcoord(Q1) || y:= ycoord(Q1) END;
  x,y <-- Queen2 = BEGIN x:= xcoord(Q2) || y:= ycoord(Q2) END;
  x,y <-- Knight(k) = PRE k:K1..K7 THEN x,y := xcoord(k),ycoord(k) END;
  AttackSq(k,x,y) = PRE k:K1..K7 & (x,y):KMOVES(xcoord(k),ycoord(k)) &
                            x:1..n & y:1..n THEN skip END;
  Attack(k1,p2) = PRE k1:K1..K7 & p2 : Q1..K7 &
                       (xcoord(p2),ycoord(p2)) : KMOVES(xcoord(k1),ycoord(k1)) THEN
    skip
  END
END

