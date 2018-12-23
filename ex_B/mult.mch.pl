spec_trans(root,'$initialise_machine',0).
spec_trans(0,'value-->1',0).
spec_trans(0,'mult(1)',0).
spec_trans(0,'mult(2)',1).
spec_trans(0,'mult(3)',2).
spec_trans(1,'value-->2',1).
spec_trans(1,'mult(1)',1).
spec_trans(1,'mult(2)',3).
spec_trans(1,'mult(3)',4).
spec_trans(2,'value-->3',2).
spec_trans(2,'mult(1)',2).
spec_trans(2,'mult(2)',4).
spec_trans(2,'mult(3)',5).
spec_trans(5,'value-->9',5).
spec_trans(5,'mult(1)',5).
spec_trans(5,'mult(2)',6).
spec_trans(5,'mult(3)',7).
spec_not_all_transitions_added(7).
spec_not_all_transitions_added(6).
spec_not_all_transitions_added(3).
spec_not_all_transitions_added(4).
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored :-
        fail.
