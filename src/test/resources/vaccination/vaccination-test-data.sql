INSERT INTO vaccine 
    (id)
    VALUES 
    (1), (2), (3), (4), (5), (6), (7);
INSERT INTO patient 
    (id) 
    VALUES 
    (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12), (13), (14), (15), (16), (17), (18), (19), (20), (21);
INSERT INTO shift 
    (id)
    VALUES 
    (1), (2), (3), (4), (5), (6), (7), (8);
INSERT INTO vaccination 
    (id, vaccine_id, patient_id, shift_id, date, deleted) 
    VALUES 
    (1, 1, 3, 1, '2021-05-20', FALSE), 
    (2, 1, 4, 2, '2021-05-21', FALSE), 
    (3, 1, 5, 3, '2021-05-22', FALSE), 
    (4, 3, 6, 4, '2021-05-23', FALSE), 
    (5, 4, 7, 5, '2021-05-24', FALSE), 
    (6, 2, 8, 6, '2021-05-25', FALSE), 
    (7, 2, 9, 1, '2021-05-20', FALSE), 
    (8, 1, 10, 2, '2021-05-21', FALSE), 
    (9, 2, 11, 2, '2021-05-22', FALSE), 
    (10, 3, 12, 4, '2021-05-23', FALSE), 
    (11, 3, 14, 5, '2021-05-24', FALSE), 
    (12, 6, 15, 1, '2021-05-20', FALSE), 
    (13, 1, 17, 2, '2021-05-21', FALSE), 
    (14, 6, 18, 3, '2021-05-22', FALSE), 
    (15, 7, 20, 5, '2021-05-24', FALSE), 
    (16, 2, 21, 5, '2021-05-24', FALSE), 
    (17, 1, 4, 6, '2021-05-26', FALSE), 
    (18, 3, 6, 6, '2021-05-26', FALSE), 
    (19, 5, 7, 7, '2021-05-27', FALSE), 
    (20, 3, 14, 7, '2021-05-27', FALSE), 
    (21, 1, 17, 8, '2021-05-28', FALSE), 
    (22, 6, 18, 8, '2021-05-28', FALSE), 
    (23, 2, 21, 8, '2021-05-28', FALSE);