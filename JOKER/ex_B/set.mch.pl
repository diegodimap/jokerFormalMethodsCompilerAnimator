spec_trans(root,'$initialise_machine',0).
spec_trans(0,'add(ELEM1)',1).
spec_trans(0,'add(ELEM2)',2).
spec_trans(1,'add(ELEM1)',1).
spec_trans(1,'add(ELEM2)',3).
spec_trans(1,'out-->ELEM1',0).
spec_trans(2,'add(ELEM1)',3).
spec_trans(2,'add(ELEM2)',2).
spec_trans(2,'out-->ELEM2',0).
spec_trans(3,'add(ELEM1)',3).
spec_trans(3,'add(ELEM2)',3).
spec_trans(3,'out-->ELEM1',2).
spec_trans(3,'out-->ELEM2',1).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
