spec_trans(root,'$initialise_machine',1).
spec_trans(0,'$initialise_machine',1).
spec_trans(1,'insert(KEY1,KEY1)',2).
spec_trans(1,'insert(KEY2,KEY2)',3).
spec_trans(1,'extract(KEY1,KEY1)',1).
spec_trans(1,'extract(KEY2,KEY2)',1).
spec_trans(1,'quicklock(KEY1)',1).
spec_trans(1,'quicklock(KEY2)',1).
spec_trans(1,'lockdoor(KEY1)',1).
spec_trans(1,'lockdoor(KEY2)',1).
spec_trans(1,'closedoor(KEY1)',1).
spec_trans(1,'closedoor(KEY2)',1).
spec_trans(2,'insert(KEY1,KEY1)',2).
spec_trans(2,'insert(KEY2,KEY2)',4).
spec_trans(2,'extract(KEY1,KEY1)',1).
spec_trans(2,'extract(KEY2,KEY2)',2).
spec_trans(2,'unlock(KEY1)',5).
spec_trans(2,'quicklock(KEY1)',1).
spec_trans(2,'quicklock(KEY2)',2).
spec_trans(2,'lockdoor(KEY1)',2).
spec_trans(2,'lockdoor(KEY2)',2).
spec_trans(2,'closedoor(KEY1)',2).
spec_trans(2,'closedoor(KEY2)',2).
spec_trans(5,'insert(KEY1,KEY1)',5).
spec_trans(5,'insert(KEY2,KEY2)',6).
spec_trans(5,'extract(KEY2,KEY2)',5).
spec_trans(5,'unlock(KEY1)',5).
spec_trans(5,'quicklock(KEY1)',1).
spec_trans(5,'quicklock(KEY2)',5).
spec_trans(5,'opendoor(KEY1)',7).
spec_trans(5,'lockdoor(KEY1)',2).
spec_trans(5,'lockdoor(KEY2)',5).
spec_trans(5,'closedoor(KEY1)',5).
spec_trans(5,'closedoor(KEY2)',5).
spec_trans(3,'insert(KEY1,KEY1)',4).
spec_trans(3,'insert(KEY2,KEY2)',3).
spec_trans(3,'extract(KEY1,KEY1)',3).
spec_trans(3,'extract(KEY2,KEY2)',1).
spec_trans(3,'unlock(KEY2)',8).
spec_trans(3,'quicklock(KEY1)',3).
spec_trans(3,'quicklock(KEY2)',1).
spec_trans(3,'lockdoor(KEY1)',3).
spec_trans(3,'lockdoor(KEY2)',3).
spec_trans(3,'closedoor(KEY1)',3).
spec_trans(3,'closedoor(KEY2)',3).
spec_trans(7,'insert(KEY1,KEY1)',7).
spec_trans(7,'insert(KEY2,KEY2)',9).
spec_trans(7,'extract(KEY2,KEY2)',7).
spec_trans(7,'unlock(KEY1)',7).
spec_trans(7,'quicklock(KEY2)',7).
spec_trans(7,'opendoor(KEY1)',7).
spec_trans(7,'lockdoor(KEY2)',7).
spec_trans(7,'closedoor(KEY1)',5).
spec_trans(7,'closedoor(KEY2)',7).
spec_trans(4,'insert(KEY1,KEY1)',4).
spec_trans(4,'insert(KEY2,KEY2)',4).
spec_trans(4,'extract(KEY1,KEY1)',3).
spec_trans(4,'extract(KEY2,KEY2)',2).
spec_trans(4,'unlock(KEY1)',6).
spec_trans(4,'unlock(KEY2)',10).
spec_trans(4,'quicklock(KEY1)',3).
spec_trans(4,'quicklock(KEY2)',2).
spec_trans(4,'lockdoor(KEY1)',4).
spec_trans(4,'lockdoor(KEY2)',4).
spec_trans(4,'closedoor(KEY1)',4).
spec_trans(4,'closedoor(KEY2)',4).
spec_trans(6,'insert(KEY1,KEY1)',6).
spec_trans(6,'insert(KEY2,KEY2)',6).
spec_trans(6,'extract(KEY2,KEY2)',5).
spec_trans(6,'unlock(KEY1)',6).
spec_trans(6,'unlock(KEY2)',11).
spec_trans(6,'quicklock(KEY1)',3).
spec_trans(6,'quicklock(KEY2)',5).
spec_trans(6,'opendoor(KEY1)',9).
spec_trans(6,'lockdoor(KEY1)',4).
spec_trans(6,'lockdoor(KEY2)',6).
spec_trans(6,'closedoor(KEY1)',6).
spec_trans(6,'closedoor(KEY2)',6).
spec_trans(8,'insert(KEY1,KEY1)',10).
spec_trans(8,'insert(KEY2,KEY2)',8).
spec_trans(8,'extract(KEY1,KEY1)',8).
spec_trans(8,'unlock(KEY2)',8).
spec_trans(8,'quicklock(KEY1)',8).
spec_trans(8,'quicklock(KEY2)',1).
spec_trans(8,'opendoor(KEY2)',12).
spec_trans(8,'lockdoor(KEY1)',8).
spec_trans(8,'lockdoor(KEY2)',3).
spec_trans(8,'closedoor(KEY1)',8).
spec_trans(8,'closedoor(KEY2)',8).
spec_trans(12,'insert(KEY1,KEY1)',13).
spec_trans(12,'insert(KEY2,KEY2)',12).
spec_trans(12,'extract(KEY1,KEY1)',12).
spec_trans(12,'unlock(KEY2)',12).
spec_trans(12,'quicklock(KEY1)',12).
spec_trans(12,'opendoor(KEY2)',12).
spec_trans(12,'lockdoor(KEY1)',12).
spec_trans(12,'closedoor(KEY1)',12).
spec_trans(12,'closedoor(KEY2)',8).
spec_trans(9,'insert(KEY1,KEY1)',9).
spec_trans(9,'insert(KEY2,KEY2)',9).
spec_trans(9,'extract(KEY2,KEY2)',7).
spec_trans(9,'unlock(KEY1)',9).
spec_trans(9,'unlock(KEY2)',14).
spec_trans(9,'quicklock(KEY2)',7).
spec_trans(9,'opendoor(KEY1)',9).
spec_trans(9,'lockdoor(KEY2)',9).
spec_trans(9,'closedoor(KEY1)',6).
spec_trans(9,'closedoor(KEY2)',9).
spec_trans(13,'insert(KEY1,KEY1)',13).
spec_trans(13,'insert(KEY2,KEY2)',13).
spec_trans(13,'extract(KEY1,KEY1)',12).
spec_trans(13,'unlock(KEY1)',15).
spec_trans(13,'unlock(KEY2)',13).
spec_trans(13,'quicklock(KEY1)',12).
spec_trans(13,'opendoor(KEY2)',13).
spec_trans(13,'lockdoor(KEY1)',13).
spec_trans(13,'closedoor(KEY1)',13).
spec_trans(13,'closedoor(KEY2)',10).
spec_trans(10,'insert(KEY1,KEY1)',10).
spec_trans(10,'insert(KEY2,KEY2)',10).
spec_trans(10,'extract(KEY1,KEY1)',8).
spec_trans(10,'unlock(KEY1)',11).
spec_trans(10,'unlock(KEY2)',10).
spec_trans(10,'quicklock(KEY1)',8).
spec_trans(10,'quicklock(KEY2)',2).
spec_trans(10,'opendoor(KEY2)',13).
spec_trans(10,'lockdoor(KEY1)',10).
spec_trans(10,'lockdoor(KEY2)',4).
spec_trans(10,'closedoor(KEY1)',10).
spec_trans(10,'closedoor(KEY2)',10).
spec_trans(15,'insert(KEY1,KEY1)',15).
spec_trans(15,'insert(KEY2,KEY2)',15).
spec_trans(15,'unlock(KEY1)',15).
spec_trans(15,'unlock(KEY2)',15).
spec_trans(15,'quicklock(KEY1)',12).
spec_trans(15,'opendoor(KEY1)',16).
spec_trans(15,'opendoor(KEY2)',15).
spec_trans(15,'lockdoor(KEY1)',13).
spec_trans(15,'closedoor(KEY1)',15).
spec_trans(15,'closedoor(KEY2)',11).
spec_trans(16,'insert(KEY1,KEY1)',16).
spec_trans(16,'insert(KEY2,KEY2)',16).
spec_trans(16,'unlock(KEY1)',16).
spec_trans(16,'unlock(KEY2)',16).
spec_trans(16,'opendoor(KEY1)',16).
spec_trans(16,'opendoor(KEY2)',16).
spec_trans(16,'closedoor(KEY1)',15).
spec_trans(16,'closedoor(KEY2)',14).
spec_trans(11,'insert(KEY1,KEY1)',11).
spec_trans(11,'insert(KEY2,KEY2)',11).
spec_trans(11,'unlock(KEY1)',11).
spec_trans(11,'unlock(KEY2)',11).
spec_trans(11,'quicklock(KEY1)',8).
spec_trans(11,'quicklock(KEY2)',5).
spec_trans(11,'opendoor(KEY1)',14).
spec_trans(11,'opendoor(KEY2)',15).
spec_trans(11,'lockdoor(KEY1)',10).
spec_trans(11,'lockdoor(KEY2)',6).
spec_trans(11,'closedoor(KEY1)',11).
spec_trans(11,'closedoor(KEY2)',11).
spec_trans(14,'insert(KEY1,KEY1)',14).
spec_trans(14,'insert(KEY2,KEY2)',14).
spec_trans(14,'unlock(KEY1)',14).
spec_trans(14,'unlock(KEY2)',14).
spec_trans(14,'quicklock(KEY2)',7).
spec_trans(14,'opendoor(KEY1)',14).
spec_trans(14,'opendoor(KEY2)',16).
spec_trans(14,'lockdoor(KEY2)',9).
spec_trans(14,'closedoor(KEY1)',11).
spec_trans(14,'closedoor(KEY2)',14).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
