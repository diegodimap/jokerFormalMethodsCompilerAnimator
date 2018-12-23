spec_trans(root,'$initialise_machine',0).
spec_trans(0,'new_project(TITLE1,STAFF1)-->FALSE',0).
spec_trans(0,'new_project(TITLE1,STAFF2)-->FALSE',0).
spec_trans(0,'new_project(TITLE2,STAFF1)-->FALSE',0).
spec_trans(0,'new_project(TITLE2,STAFF2)-->FALSE',0).
spec_not_all_transitions_added(_) :-
        fail.
spec_max_reached_for_node(_) :-
        fail.
spec_completely_explored.
