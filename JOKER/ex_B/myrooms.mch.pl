spec_trans(root,'$initialise_machine',5).
spec_trans(root,'$initialise_machine',7).
spec_trans(root,'$initialise_machine',6).
spec_trans(root,'$initialise_machine',4).
spec_trans(3,'$initialise_machine',4).
spec_trans(0,'$initialise_machine',5).
spec_trans(4,'sizequery(ROOM1)-->big',4).
spec_trans(4,'sizequery(ROOM2)-->little',4).
spec_trans(4,'pricequery(ROOM1)-->1',4).
spec_trans(4,'pricequery(ROOM2)-->1',4).
spec_trans(2,'$initialise_machine',6).
spec_trans(6,'sizequery(ROOM1)-->little',6).
spec_trans(6,'sizequery(ROOM2)-->big',6).
spec_trans(6,'pricequery(ROOM1)-->1',6).
spec_trans(6,'pricequery(ROOM2)-->1',6).
spec_trans(1,'$initialise_machine',7).
spec_trans(5,'sizequery(ROOM1)-->big',5).
spec_trans(5,'sizequery(ROOM2)-->big',5).
spec_trans(5,'pricequery(ROOM1)-->1',5).
spec_trans(5,'pricequery(ROOM2)-->1',5).
spec_trans(7,'sizequery(ROOM1)-->little',7).
spec_trans(7,'sizequery(ROOM2)-->little',7).
spec_trans(7,'pricequery(ROOM1)-->1',7).
spec_trans(7,'pricequery(ROOM2)-->1',7).
spec_max_reached_for_node(root).
spec_not_all_transitions_added(_) :-
        fail.
spec_completely_explored :-
        fail.
