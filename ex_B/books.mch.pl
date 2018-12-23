spec_trans(root,'$initialise_machine',0).
spec_trans(0,'newbook-->BOOK1',1).
spec_trans(0,'newbook-->BOOK2',2).
spec_trans(1,'newbook-->BOOK2',4).
spec_trans(2,'newbook-->BOOK1',4).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
