spec_trans(root,'$initialise_machine',0).
spec_trans(0,'buy(0)',0).
spec_trans(0,'board(0)',0).
spec_trans(0,'board(1)',1).
spec_trans(0,'board(2)',2).
spec_trans(0,'board(3)',3).
spec_trans(0,dble,0).
spec_trans(1,'buy(0)',1).
spec_trans(1,'buy(1)',4).
spec_trans(1,'board(0)',1).
spec_trans(1,'board(1)',2).
spec_trans(1,'board(2)',3).
spec_trans(1,'board(3)',5).
spec_trans(1,dble,2).
spec_trans(3,'buy(0)',3).
spec_trans(3,'buy(1)',6).
spec_trans(3,'buy(2)',7).
spec_trans(3,'buy(3)',8).
spec_trans(3,'board(0)',3).
spec_trans(3,'board(1)',5).
spec_trans(3,'board(2)',9).
spec_trans(3,'board(3)',10).
spec_trans(3,dble,10).
spec_trans(10,'buy(0)',10).
spec_trans(10,'buy(1)',11).
spec_trans(10,'buy(2)',12).
spec_trans(10,'buy(3)',13).
spec_trans(10,'board(0)',10).
spec_trans(10,'board(1)',14).
spec_trans(10,'board(2)',15).
spec_trans(10,'board(3)',16).
spec_trans(10,dble,17).
spec_not_all_transitions_added(17).
spec_not_all_transitions_added(16).
spec_not_all_transitions_added(15).
spec_not_all_transitions_added(14).
spec_not_all_transitions_added(13).
spec_not_all_transitions_added(12).
spec_not_all_transitions_added(11).
spec_not_all_transitions_added(9).
spec_not_all_transitions_added(8).
spec_not_all_transitions_added(7).
spec_not_all_transitions_added(6).
spec_not_all_transitions_added(2).
spec_not_all_transitions_added(4).
spec_not_all_transitions_added(5).
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored :-
        fail.