spec_trans(root,'$initialise_machine',0).
spec_trans(root,'$initialise_machine',1).
spec_trans(1,'input(TIME1)',1).
spec_trans(1,'input(TIME2)',1).
spec_trans(1,'output-->TIME2',1).
spec_trans(0,'input(TIME1)',0).
spec_trans(0,'input(TIME2)',0).
spec_trans(0,'output-->TIME1',0).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
