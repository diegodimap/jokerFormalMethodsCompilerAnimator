spec_trans(root,'$initialise_machine',0).
spec_trans(0,'unlockdoor(DOOR1)',1).
spec_trans(0,'unlockdoor(DOOR2)',2).
spec_trans(0,'lockdoor(DOOR1)',0).
spec_trans(0,'lockdoor(DOOR2)',0).
spec_trans(0,'closedoor(DOOR1)',0).
spec_trans(0,'closedoor(DOOR2)',0).
spec_trans(1,'opendoor(DOOR1)',3).
spec_trans(1,'unlockdoor(DOOR1)',1).
spec_trans(1,'unlockdoor(DOOR2)',4).
spec_trans(1,'lockdoor(DOOR1)',0).
spec_trans(1,'lockdoor(DOOR2)',1).
spec_trans(1,'closedoor(DOOR1)',1).
spec_trans(1,'closedoor(DOOR2)',1).
spec_trans(2,'opendoor(DOOR2)',5).
spec_trans(2,'unlockdoor(DOOR1)',4).
spec_trans(2,'unlockdoor(DOOR2)',2).
spec_trans(2,'lockdoor(DOOR1)',2).
spec_trans(2,'lockdoor(DOOR2)',0).
spec_trans(2,'closedoor(DOOR1)',2).
spec_trans(2,'closedoor(DOOR2)',2).
spec_trans(5,'opendoor(DOOR2)',5).
spec_trans(5,'unlockdoor(DOOR1)',6).
spec_trans(5,'unlockdoor(DOOR2)',5).
spec_trans(5,'lockdoor(DOOR1)',5).
spec_trans(5,'closedoor(DOOR1)',5).
spec_trans(5,'closedoor(DOOR2)',2).
spec_trans(3,'opendoor(DOOR1)',3).
spec_trans(3,'unlockdoor(DOOR1)',3).
spec_trans(3,'unlockdoor(DOOR2)',7).
spec_trans(3,'lockdoor(DOOR2)',3).
spec_trans(3,'closedoor(DOOR1)',1).
spec_trans(3,'closedoor(DOOR2)',3).
spec_trans(6,'opendoor(DOOR1)',8).
spec_trans(6,'opendoor(DOOR2)',6).
spec_trans(6,'unlockdoor(DOOR1)',6).
spec_trans(6,'unlockdoor(DOOR2)',6).
spec_trans(6,'lockdoor(DOOR1)',5).
spec_trans(6,'closedoor(DOOR1)',6).
spec_trans(6,'closedoor(DOOR2)',4).
spec_trans(4,'opendoor(DOOR1)',7).
spec_trans(4,'opendoor(DOOR2)',6).
spec_trans(4,'unlockdoor(DOOR1)',4).
spec_trans(4,'unlockdoor(DOOR2)',4).
spec_trans(4,'lockdoor(DOOR1)',2).
spec_trans(4,'lockdoor(DOOR2)',1).
spec_trans(4,'closedoor(DOOR1)',4).
spec_trans(4,'closedoor(DOOR2)',4).
spec_trans(8,'opendoor(DOOR1)',8).
spec_trans(8,'opendoor(DOOR2)',8).
spec_trans(8,'unlockdoor(DOOR1)',8).
spec_trans(8,'unlockdoor(DOOR2)',8).
spec_trans(8,'closedoor(DOOR1)',6).
spec_trans(8,'closedoor(DOOR2)',7).
spec_trans(7,'opendoor(DOOR1)',7).
spec_trans(7,'opendoor(DOOR2)',8).
spec_trans(7,'unlockdoor(DOOR1)',7).
spec_trans(7,'unlockdoor(DOOR2)',7).
spec_trans(7,'lockdoor(DOOR2)',3).
spec_trans(7,'closedoor(DOOR1)',4).
spec_trans(7,'closedoor(DOOR2)',7).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
