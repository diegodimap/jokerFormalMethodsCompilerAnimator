spec_trans(root,'$initialise_machine',0).
spec_trans(0,'opening(DOOR1)',1).
spec_trans(0,'opening(DOOR2)',2).
spec_trans(0,'closedoor(DOOR1)',0).
spec_trans(0,'closedoor(DOOR2)',0).
spec_trans(1,'opening(DOOR1)',1).
spec_trans(1,'opening(DOOR2)',3).
spec_trans(1,'closedoor(DOOR1)',0).
spec_trans(1,'closedoor(DOOR2)',1).
spec_trans(2,'opening(DOOR1)',3).
spec_trans(2,'opening(DOOR2)',2).
spec_trans(2,'closedoor(DOOR1)',2).
spec_trans(2,'closedoor(DOOR2)',0).
spec_trans(3,'opening(DOOR1)',3).
spec_trans(3,'opening(DOOR2)',3).
spec_trans(3,'closedoor(DOOR1)',2).
spec_trans(3,'closedoor(DOOR2)',1).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
