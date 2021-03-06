spec_trans(root,'$initialise_machine',1).
spec_trans(0,'$initialise_machine',1).
spec_trans(1,'guestcheckin(ROOM1,NAME2)',2).
spec_trans(1,'guestcheckin(ROOM2,NAME2)',3).
spec_trans(1,'guestcheckout(ROOM1)',1).
spec_trans(1,'guestcheckout(ROOM2)',1).
spec_trans(1,'guestquery(ROOM1)-->empty',1).
spec_trans(1,'guestquery(ROOM2)-->empty',1).
spec_trans(1,'presentquery(NAME2)-->notpresent',1).
spec_trans(1,'guestswap(ROOM1,ROOM1)',1).
spec_trans(1,'guestswap(ROOM1,ROOM2)',1).
spec_trans(1,'guestswap(ROOM2,ROOM1)',1).
spec_trans(1,'guestswap(ROOM2,ROOM2)',1).
spec_trans(2,'guestcheckin(ROOM1,NAME2)',2).
spec_trans(2,'guestcheckin(ROOM2,NAME2)',4).
spec_trans(2,'guestcheckout(ROOM1)',1).
spec_trans(2,'guestcheckout(ROOM2)',2).
spec_trans(2,'guestquery(ROOM1)-->NAME2',2).
spec_trans(2,'guestquery(ROOM2)-->empty',2).
spec_trans(2,'presentquery(NAME2)-->present',2).
spec_trans(2,'guestswap(ROOM1,ROOM1)',2).
spec_trans(2,'guestswap(ROOM1,ROOM2)',3).
spec_trans(2,'guestswap(ROOM2,ROOM1)',3).
spec_trans(2,'guestswap(ROOM2,ROOM2)',2).
spec_trans(4,'guestcheckin(ROOM1,NAME2)',4).
spec_trans(4,'guestcheckin(ROOM2,NAME2)',4).
spec_trans(4,'guestcheckout(ROOM1)',3).
spec_trans(4,'guestcheckout(ROOM2)',2).
spec_trans(4,'guestquery(ROOM1)-->NAME2',4).
spec_trans(4,'guestquery(ROOM2)-->NAME2',4).
spec_trans(4,'presentquery(NAME2)-->present',4).
spec_trans(4,'guestswap(ROOM1,ROOM1)',4).
spec_trans(4,'guestswap(ROOM1,ROOM2)',4).
spec_trans(4,'guestswap(ROOM2,ROOM1)',4).
spec_trans(4,'guestswap(ROOM2,ROOM2)',4).
spec_trans(3,'guestcheckin(ROOM1,NAME2)',4).
spec_trans(3,'guestcheckin(ROOM2,NAME2)',3).
spec_trans(3,'guestcheckout(ROOM1)',3).
spec_trans(3,'guestcheckout(ROOM2)',1).
spec_trans(3,'guestquery(ROOM1)-->empty',3).
spec_trans(3,'guestquery(ROOM2)-->NAME2',3).
spec_trans(3,'presentquery(NAME2)-->present',3).
spec_trans(3,'guestswap(ROOM1,ROOM1)',3).
spec_trans(3,'guestswap(ROOM1,ROOM2)',2).
spec_trans(3,'guestswap(ROOM2,ROOM1)',2).
spec_trans(3,'guestswap(ROOM2,ROOM2)',3).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
