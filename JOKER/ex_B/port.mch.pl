spec_trans(root,'$initialise_machine',0).
spec_trans(0,'arrive(SHIP1)',1).
spec_trans(0,'arrive(SHIP2)',2).
spec_trans(0,'numberwaiting-->0',0).
spec_trans(1,'arrive(SHIP2)',3).
spec_trans(1,'dock(QUAY1)',4).
spec_trans(1,'dock(QUAY2)',5).
spec_trans(1,'numberwaiting-->1',1).
spec_trans(2,'arrive(SHIP1)',6).
spec_trans(2,'dock(QUAY1)',7).
spec_trans(2,'dock(QUAY2)',8).
spec_trans(2,'numberwaiting-->1',2).
spec_trans(3,'dock(QUAY1)',9).
spec_trans(3,'dock(QUAY2)',10).
spec_trans(3,'numberwaiting-->2',3).
spec_trans(4,'arrive(SHIP2)',9).
spec_trans(4,'leave(SHIP1)-->QUAY1',0).
spec_trans(4,'numberwaiting-->0',4).
spec_trans(8,'arrive(SHIP1)',11).
spec_trans(8,'leave(SHIP2)-->QUAY2',0).
spec_trans(8,'numberwaiting-->0',8).
spec_trans(5,'arrive(SHIP2)',10).
spec_trans(5,'leave(SHIP1)-->QUAY2',0).
spec_trans(5,'numberwaiting-->0',5).
spec_trans(11,'dock(QUAY1)',12).
spec_trans(11,'leave(SHIP2)-->QUAY2',1).
spec_trans(11,'numberwaiting-->1',11).
spec_trans(6,'dock(QUAY1)',13).
spec_trans(6,'dock(QUAY2)',11).
spec_trans(6,'numberwaiting-->2',6).
spec_trans(13,'dock(QUAY2)',14).
spec_trans(13,'leave(SHIP2)-->QUAY1',1).
spec_trans(13,'numberwaiting-->1',13).
spec_trans(7,'arrive(SHIP1)',13).
spec_trans(7,'leave(SHIP2)-->QUAY1',0).
spec_trans(7,'numberwaiting-->0',7).
spec_trans(14,'leave(SHIP1)-->QUAY2',7).
spec_trans(14,'leave(SHIP2)-->QUAY1',5).
spec_trans(14,'numberwaiting-->0',14).
spec_trans(12,'leave(SHIP1)-->QUAY1',8).
spec_trans(12,'leave(SHIP2)-->QUAY2',4).
spec_trans(12,'numberwaiting-->0',12).
spec_trans(9,'dock(QUAY2)',12).
spec_trans(9,'leave(SHIP1)-->QUAY1',2).
spec_trans(9,'numberwaiting-->1',9).
spec_trans(10,'dock(QUAY1)',14).
spec_trans(10,'leave(SHIP1)-->QUAY2',2).
spec_trans(10,'numberwaiting-->1',10).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.