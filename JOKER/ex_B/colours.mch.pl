spec_trans(root,'$initialise_machine',1).
spec_trans(root,'$initialise_machine',2).
spec_trans(root,'$initialise_machine',3).
spec_trans(root,'$initialise_machine',4).
spec_trans(4,'add(red)',4).
spec_trans(4,'add(green)',4).
spec_trans(4,'add(blue)',5).
spec_trans(4,'query-->red',4).
spec_trans(4,'query-->green',4).
spec_trans(4,change,1).
spec_trans(4,change,3).
spec_trans(4,change,6).
spec_trans(4,change,2).
spec_trans(4,change,5).
spec_trans(4,change,7).
spec_trans(4,change,8).
spec_trans(1,'add(red)',3).
spec_trans(1,'add(green)',2).
spec_trans(1,'add(blue)',8).
spec_trans(1,change,3).
spec_trans(1,change,4).
spec_trans(1,change,6).
spec_trans(1,change,2).
spec_trans(1,change,5).
spec_trans(1,change,7).
spec_trans(1,change,8).
spec_trans(2,'add(red)',4).
spec_trans(2,'add(green)',2).
spec_trans(2,'add(blue)',7).
spec_trans(2,'query-->green',2).
spec_trans(2,change,1).
spec_trans(2,change,3).
spec_trans(2,change,4).
spec_trans(2,change,6).
spec_trans(2,change,5).
spec_trans(2,change,7).
spec_trans(2,change,8).
spec_trans(8,'add(red)',6).
spec_trans(8,'add(green)',7).
spec_trans(8,'add(blue)',8).
spec_trans(8,'query-->blue',8).
spec_trans(8,change,1).
spec_trans(8,change,3).
spec_trans(8,change,4).
spec_trans(8,change,6).
spec_trans(8,change,2).
spec_trans(8,change,5).
spec_trans(8,change,7).
spec_trans(7,'add(red)',5).
spec_trans(7,'add(green)',7).
spec_trans(7,'add(blue)',7).
spec_trans(7,'query-->green',7).
spec_trans(7,'query-->blue',7).
spec_trans(7,change,1).
spec_trans(7,change,3).
spec_trans(7,change,4).
spec_trans(7,change,6).
spec_trans(7,change,2).
spec_trans(7,change,5).
spec_trans(7,change,8).
spec_trans(6,'add(red)',6).
spec_trans(6,'add(green)',5).
spec_trans(6,'add(blue)',6).
spec_trans(6,'query-->red',6).
spec_trans(6,'query-->blue',6).
spec_trans(6,change,1).
spec_trans(6,change,3).
spec_trans(6,change,4).
spec_trans(6,change,2).
spec_trans(6,change,5).
spec_trans(6,change,7).
spec_trans(6,change,8).
spec_trans(3,'add(red)',3).
spec_trans(3,'add(green)',4).
spec_trans(3,'add(blue)',6).
spec_trans(3,'query-->red',3).
spec_trans(3,change,1).
spec_trans(3,change,4).
spec_trans(3,change,6).
spec_trans(3,change,2).
spec_trans(3,change,5).
spec_trans(3,change,7).
spec_trans(3,change,8).
spec_trans(5,'add(red)',5).
spec_trans(5,'add(green)',5).
spec_trans(5,'add(blue)',5).
spec_trans(5,'query-->red',5).
spec_trans(5,'query-->green',5).
spec_trans(5,'query-->blue',5).
spec_trans(5,change,1).
spec_trans(5,change,3).
spec_trans(5,change,4).
spec_trans(5,change,6).
spec_trans(5,change,2).
spec_trans(5,change,7).
spec_trans(5,change,8).
spec_max_reached_for_node(root).
spec_not_all_transitions_added(_) :-
        fail.
spec_completely_explored :-
        fail.