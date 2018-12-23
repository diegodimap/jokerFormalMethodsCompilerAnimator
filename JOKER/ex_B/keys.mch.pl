spec_trans(root,'$initialise_machine',0).
spec_trans(0,'insertkey(KEY1)',1).
spec_trans(0,'insertkey(KEY2)',2).
spec_trans(0,'removekey(KEY1)',0).
spec_trans(0,'removekey(KEY2)',0).
spec_trans(1,'insertkey(KEY1)',1).
spec_trans(1,'insertkey(KEY2)',3).
spec_trans(1,'removekey(KEY1)',0).
spec_trans(1,'removekey(KEY2)',1).
spec_trans(2,'insertkey(KEY1)',3).
spec_trans(2,'insertkey(KEY2)',2).
spec_trans(2,'removekey(KEY1)',2).
spec_trans(2,'removekey(KEY2)',0).
spec_trans(3,'insertkey(KEY1)',3).
spec_trans(3,'insertkey(KEY2)',3).
spec_trans(3,'removekey(KEY1)',2).
spec_trans(3,'removekey(KEY2)',1).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
