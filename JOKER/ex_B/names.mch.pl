spec_trans(root,'$initialise_machine',0).
spec_trans(root,'$initialise_machine',1).
spec_trans(1,'read(SURNAME1)',0).
spec_trans(1,'read(SURNAME2)',1).
spec_trans(0,'read(SURNAME1)',0).
spec_trans(0,'read(SURNAME2)',1).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
