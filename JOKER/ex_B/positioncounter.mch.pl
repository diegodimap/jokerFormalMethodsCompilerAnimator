spec_trans(root,'$initialise_machine',4).
spec_trans(root,'$initialise_machine',7).
spec_trans(root,'$initialise_machine',3).
spec_trans(2,'$initialise_machine',3).
spec_trans(0,'$initialise_machine',4).
spec_trans(3,posinc,5).
spec_trans(3,'posget-->1',3).
spec_trans(5,posinc,6).
spec_trans(5,'posget-->2',5).
spec_trans(6,posinc,3).
spec_trans(6,'posget-->3',6).
spec_trans(1,'$initialise_machine',7).
spec_trans(4,posinc,4).
spec_trans(4,'posget-->1',4).
spec_trans(7,posinc,8).
spec_trans(7,'posget-->1',7).
spec_trans(8,posinc,7).
spec_trans(8,'posget-->2',8).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
