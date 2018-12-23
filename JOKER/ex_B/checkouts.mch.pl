spec_trans(root,'$initialise_machine',0).
spec_trans(0,'open(COUNTER1)',1).
spec_trans(0,'open(COUNTER2)',2).
spec_not_all_transitions_added(2).
spec_not_all_transitions_added(1).
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored :-
        fail.
