spec_trans(root,'$initialise_machine',4).
spec_trans(root,'$initialise_machine',11).
spec_trans(root,'$initialise_machine',3).
spec_trans(2,'$initialise_machine',3).
spec_trans(0,'$initialise_machine',4).
spec_trans(3,'add(ELEM1)-->ok',5).
spec_trans(3,'add(ELEM2)-->ok',6).
spec_trans(3,'remove-->failed,ELEM1',3).
spec_trans(3,'remove-->failed,ELEM2',3).
spec_trans(3,'number-->0',3).
spec_trans(6,'add(ELEM1)-->ok',7).
spec_trans(6,'add(ELEM2)-->ok',8).
spec_trans(6,'remove-->ok,ELEM2',3).
spec_trans(6,'number-->1',6).
spec_trans(8,'add(ELEM1)-->ok',9).
spec_trans(8,'add(ELEM2)-->ok',10).
spec_trans(8,'remove-->ok,ELEM2',6).
spec_trans(8,'number-->2',8).
spec_trans(1,'$initialise_machine',11).
spec_trans(10,'add(ELEM1)-->failed',10).
spec_trans(10,'add(ELEM2)-->failed',10).
spec_trans(10,'remove-->ok,ELEM2',8).
spec_trans(10,'number-->3',10).
spec_trans(4,'add(ELEM1)-->ok',12).
spec_trans(4,'add(ELEM2)-->ok',13).
spec_trans(4,'remove-->failed,ELEM1',4).
spec_trans(4,'remove-->failed,ELEM2',4).
spec_trans(4,'number-->0',4).
spec_trans(9,'add(ELEM1)-->failed',9).
spec_trans(9,'add(ELEM2)-->failed',9).
spec_trans(9,'remove-->ok,ELEM2',7).
spec_trans(9,'number-->3',9).
spec_trans(5,'add(ELEM1)-->ok',14).
spec_trans(5,'add(ELEM2)-->ok',15).
spec_trans(5,'remove-->ok,ELEM1',3).
spec_trans(5,'number-->1',5).
spec_trans(7,'add(ELEM1)-->ok',16).
spec_trans(7,'add(ELEM2)-->ok',17).
spec_trans(7,'remove-->ok,ELEM2',5).
spec_trans(7,'number-->2',7).
spec_trans(11,'add(ELEM1)-->ok',18).
spec_trans(11,'add(ELEM2)-->ok',19).
spec_trans(11,'remove-->failed,ELEM1',11).
spec_trans(11,'remove-->failed,ELEM2',11).
spec_trans(11,'number-->0',11).
spec_trans(14,'add(ELEM1)-->ok',20).
spec_trans(14,'add(ELEM2)-->ok',21).
spec_trans(14,'remove-->ok,ELEM1',5).
spec_trans(14,'number-->2',14).
spec_trans(12,'add(ELEM1)-->failed',12).
spec_trans(12,'add(ELEM2)-->failed',12).
spec_trans(12,'remove-->ok,ELEM1',4).
spec_trans(12,'number-->1',12).
spec_trans(13,'add(ELEM1)-->failed',13).
spec_trans(13,'add(ELEM2)-->failed',13).
spec_trans(13,'remove-->ok,ELEM2',4).
spec_trans(13,'number-->1',13).
spec_trans(15,'add(ELEM1)-->ok',22).
spec_trans(15,'add(ELEM2)-->ok',23).
spec_trans(15,'remove-->ok,ELEM1',6).
spec_trans(15,'number-->2',15).
spec_trans(16,'add(ELEM1)-->failed',16).
spec_trans(16,'add(ELEM2)-->failed',16).
spec_trans(16,'remove-->ok,ELEM2',14).
spec_trans(16,'number-->3',16).
spec_trans(21,'add(ELEM1)-->failed',21).
spec_trans(21,'add(ELEM2)-->failed',21).
spec_trans(21,'remove-->ok,ELEM1',15).
spec_trans(21,'number-->3',21).
spec_trans(20,'add(ELEM1)-->failed',20).
spec_trans(20,'add(ELEM2)-->failed',20).
spec_trans(20,'remove-->ok,ELEM1',14).
spec_trans(20,'number-->3',20).
spec_trans(17,'add(ELEM1)-->failed',17).
spec_trans(17,'add(ELEM2)-->failed',17).
spec_trans(17,'remove-->ok,ELEM2',15).
spec_trans(17,'number-->3',17).
spec_trans(18,'add(ELEM1)-->ok',24).
spec_trans(18,'add(ELEM2)-->ok',25).
spec_trans(18,'remove-->ok,ELEM1',11).
spec_trans(18,'number-->1',18).
spec_trans(25,'add(ELEM1)-->failed',25).
spec_trans(25,'add(ELEM2)-->failed',25).
spec_trans(25,'remove-->ok,ELEM1',19).
spec_trans(25,'number-->2',25).
spec_trans(19,'add(ELEM1)-->ok',26).
spec_trans(19,'add(ELEM2)-->ok',27).
spec_trans(19,'remove-->ok,ELEM2',11).
spec_trans(19,'number-->1',19).
spec_trans(22,'add(ELEM1)-->failed',22).
spec_trans(22,'add(ELEM2)-->failed',22).
spec_trans(22,'remove-->ok,ELEM1',7).
spec_trans(22,'number-->3',22).
spec_trans(26,'add(ELEM1)-->failed',26).
spec_trans(26,'add(ELEM2)-->failed',26).
spec_trans(26,'remove-->ok,ELEM2',18).
spec_trans(26,'number-->2',26).
spec_trans(23,'add(ELEM1)-->failed',23).
spec_trans(23,'add(ELEM2)-->failed',23).
spec_trans(23,'remove-->ok,ELEM1',8).
spec_trans(23,'number-->3',23).
spec_trans(24,'add(ELEM1)-->failed',24).
spec_trans(24,'add(ELEM2)-->failed',24).
spec_trans(24,'remove-->ok,ELEM1',18).
spec_trans(24,'number-->2',24).
spec_trans(27,'add(ELEM1)-->failed',27).
spec_trans(27,'add(ELEM2)-->failed',27).
spec_trans(27,'remove-->ok,ELEM2',19).
spec_trans(27,'number-->2',27).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.