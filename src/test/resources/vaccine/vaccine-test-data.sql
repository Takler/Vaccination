INSERT INTO vaccine
    (name, type, storage_temperature, age_limit_min, age_limit_max, shots_needed, next_shot_id,
    days_until_next_shot, fully_vaccinated_time_period, applicable, applicable_for_pregnant,
    applicable_for_chronic)
    VALUES
    ('Pfizer', 'mRNA', -70, 16, 999, 2, 1, 28, 42, 1, 1, 1),
    ('Moderna', 'mRNA', -20, 18, 999, 2, 2, 28, 42, 1, 1, 1),
    ('AstraZeneca', 'adenovirus', 4, 18, 999, 2, 3, 84, 96, 1, 1, 1),
    ('Gamaleja', 'adenovirus', 4, 18, 999, 2, 5, 21, 35, 1, 0, 0),
    ('Gamaleja 2nd', 'adenovirus', 4, 18, 999, 2, -1, 0, 14, 1, 0, 0),
    ('Sinopharm', 'inactivated virus', 4, 18, 999, 2, 6, 28, 42, 1, 0, 0),
    ('Janssen', 'adenovirus', 4, 18, 999, 1, -1, 0, 14, 1, 0, 1);