DROP ALL OBJECTS;
CREATE TABLE vaccine
    (id INT PRIMARY KEY);
CREATE TABLE patient
    (id INT PRIMARY KEY);
CREATE TABLE shift
    (id INT PRIMARY KEY);
CREATE TABLE vaccination
    (id INT PRIMARY KEY AUTO_INCREMENT,
    vaccine_id INT NOT NULL,
    patient_id INT NOT NULL,
    shift_id INT NOT NULL,
    date DATE NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    FOREIGN KEY (vaccine_id) REFERENCES vaccine(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (shift_id) REFERENCES shift(id));