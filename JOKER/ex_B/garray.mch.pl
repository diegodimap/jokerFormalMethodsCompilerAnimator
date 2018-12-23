spec_trans(root,'$initialise_machine',0).
spec_trans(0,'set(ITEM1,ITEM1)',0).
spec_trans(0,'set(ITEM1,ITEM2)',1).
spec_trans(0,'set(ITEM2,ITEM1)',2).
spec_trans(0,'set(ITEM2,ITEM2)',0).
spec_trans(0,'get(ITEM1)-->ITEM1',0).
spec_trans(0,'get(ITEM2)-->ITEM2',0).
spec_trans(1,'set(ITEM1,ITEM1)',0).
spec_trans(1,'set(ITEM1,ITEM2)',1).
spec_trans(1,'set(ITEM2,ITEM1)',3).
spec_trans(1,'set(ITEM2,ITEM2)',1).
spec_trans(1,'get(ITEM1)-->ITEM2',1).
spec_trans(1,'get(ITEM2)-->ITEM2',1).
spec_trans(2,'set(ITEM1,ITEM1)',2).
spec_trans(2,'set(ITEM1,ITEM2)',3).
spec_trans(2,'set(ITEM2,ITEM1)',2).
spec_trans(2,'set(ITEM2,ITEM2)',0).
spec_trans(2,'get(ITEM1)-->ITEM1',2).
spec_trans(2,'get(ITEM2)-->ITEM1',2).
spec_trans(3,'set(ITEM1,ITEM1)',2).
spec_trans(3,'set(ITEM1,ITEM2)',3).
spec_trans(3,'set(ITEM2,ITEM1)',3).
spec_trans(3,'set(ITEM2,ITEM2)',1).
spec_trans(3,'get(ITEM1)-->ITEM2',3).
spec_trans(3,'get(ITEM2)-->ITEM1',3).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.