spec_trans(root,'$initialise_machine',4).
spec_trans(root,'$initialise_machine',11).
spec_trans(root,'$initialise_machine',3).
spec_trans(2,'$initialise_machine',3).
spec_trans(0,'$initialise_machine',4).
spec_trans(3,'input(ELEM1)',5).
spec_trans(3,'input(ELEM2)',6).
spec_trans(6,'input(ELEM1)',7).
spec_trans(6,'input(ELEM2)',8).
spec_trans(6,'output-->ELEM2',3).
spec_trans(8,'input(ELEM1)',9).
spec_trans(8,'input(ELEM2)',10).
spec_trans(8,'output-->ELEM2',6).
spec_trans(1,'$initialise_machine',11).
spec_trans(10,'output-->ELEM2',8).
spec_trans(4,'input(ELEM1)',12).
spec_trans(4,'input(ELEM2)',13).
spec_trans(9,'output-->ELEM2',7).
spec_trans(5,'input(ELEM1)',14).
spec_trans(5,'input(ELEM2)',15).
spec_trans(5,'output-->ELEM1',3).
spec_trans(7,'input(ELEM1)',16).
spec_trans(7,'input(ELEM2)',17).
spec_trans(7,'output-->ELEM2',5).
spec_trans(11,'input(ELEM1)',18).
spec_trans(11,'input(ELEM2)',19).
spec_trans(14,'input(ELEM1)',20).
spec_trans(14,'input(ELEM2)',21).
spec_trans(14,'output-->ELEM1',5).
spec_trans(12,'output-->ELEM1',4).
spec_trans(13,'output-->ELEM2',4).
spec_trans(15,'input(ELEM1)',22).
spec_trans(15,'input(ELEM2)',23).
spec_trans(15,'output-->ELEM1',6).
spec_trans(16,'output-->ELEM2',14).
spec_trans(21,'output-->ELEM1',15).
spec_trans(20,'output-->ELEM1',14).
spec_trans(17,'output-->ELEM2',15).
spec_trans(18,'input(ELEM1)',24).
spec_trans(18,'input(ELEM2)',25).
spec_trans(18,'output-->ELEM1',11).
spec_trans(25,'output-->ELEM1',19).
spec_trans(19,'input(ELEM1)',26).
spec_trans(19,'input(ELEM2)',27).
spec_trans(19,'output-->ELEM2',11).
spec_trans(22,'output-->ELEM1',7).
spec_trans(26,'output-->ELEM2',18).
spec_trans(23,'output-->ELEM1',8).
spec_trans(24,'output-->ELEM1',18).
spec_trans(27,'output-->ELEM2',19).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.