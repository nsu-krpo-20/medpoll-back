CREATE TABLE IF NOT EXISTS reports
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    prescription_id BIGINT     NOT NULL REFERENCES prescriptions ON DELETE CASCADE,
    meds_taken      JSON       NOT NULL,
    metrics         JSON       NOT NULL,
    feedback        MEDIUMTEXT NOT NULL,
    time            TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP
);