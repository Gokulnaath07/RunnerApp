CREATE TABLE IF NOT EXISTS run (
    id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    miles INTEGER NOT NULL,
    location VARCHAR(10) NOT NULL,
    Version INT,
    PRIMARY KEY (id)
);