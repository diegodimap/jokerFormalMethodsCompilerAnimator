spec_trans(root,'$initialise_machine',0).
spec_trans(0,'finished(RUNNER1)',1).
spec_trans(0,'finished(RUNNER2)',2).
spec_trans(0,'medals-->{}',0).
spec_trans(1,'finished(RUNNER2)',3).
spec_trans(1,'query(1)-->RUNNER1',1).
spec_trans(1,'disqualify(1)',0).
spec_trans(1,'medals-->{(1|->RUNNER1)}',1).
spec_trans(2,'finished(RUNNER1)',4).
spec_trans(2,'query(1)-->RUNNER2',2).
spec_trans(2,'disqualify(1)',0).
spec_trans(2,'medals-->{(1|->RUNNER2)}',2).
spec_trans(4,'query(1)-->RUNNER2',4).
spec_trans(4,'query(2)-->RUNNER1',4).
spec_trans(4,'disqualify(1)',1).
spec_trans(4,'disqualify(2)',2).
spec_trans(4,'medals-->[RUNNER2,RUNNER1]',4).
spec_trans(3,'query(1)-->RUNNER1',3).
spec_trans(3,'query(2)-->RUNNER2',3).
spec_trans(3,'disqualify(1)',2).
spec_trans(3,'disqualify(2)',1).
spec_trans(3,'medals-->[RUNNER1,RUNNER2]',3).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
