MACHINE           Map(INDEX, ITEM)

VARIABLES         fun

INVARIANT         fun : INDEX +-> ITEM

INITIALISATION    fun := {}

OPERATIONS

  insert(ss1, ss2) =
  PRE ss1 : INDEX & ss2 : ITEM
  THEN fun(ss1) := ss2
  END;

  remove(ss1) =
  PRE ss1 : INDEX
  THEN fun := {ss1} <<| fun
  END;

ss2 <-- query(ss1) =
  PRE ss1 : dom(fun)
  THEN ss2 := fun(ss1)
  END

END
