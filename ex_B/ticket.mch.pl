spec_trans(root,'$initialise_machine',0).
spec_trans(0,'take_ticket-->0',1).
spec_trans(1,'serve_next-->1',2).
spec_trans(1,'take_ticket-->1',3).
spec_trans(2,'take_ticket-->1',4).
spec_trans(4,'serve_next-->2',5).
spec_trans(4,'take_ticket-->2',6).
spec_trans(3,'serve_next-->1',4).
spec_trans(3,'take_ticket-->2',7).
spec_trans(6,'serve_next-->2',8).
spec_trans(6,'take_ticket-->3',9).
spec_trans(5,'take_ticket-->2',8).
spec_trans(9,'serve_next-->2',10).
spec_trans(9,'take_ticket-->4',11).
spec_not_all_transitions_added(11).
spec_not_all_transitions_added(10).
spec_not_all_transitions_added(8).
spec_not_all_transitions_added(7).
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored :-
        fail.
