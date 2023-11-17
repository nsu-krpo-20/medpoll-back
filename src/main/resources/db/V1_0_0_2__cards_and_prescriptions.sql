CREATE TABLE IF NOT EXISTS patient_cards
(
    id           bigint AUTO_INCREMENT PRIMARY KEY,
    full_name    text NOT NULL,
    snils        varchar(14) UNIQUE,
    phone_number varchar(11) UNIQUE,
    description  text
);

CREATE TABLE IF NOT EXISTS prescriptions
(
    id      bigint AUTO_INCREMENT PRIMARY KEY,
    card_id bigint REFERENCES patient_cards ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS prescription_meds
(
    id              bigint AUTO_INCREMENT PRIMARY KEY,
    prescription_id bigint REFERENCES prescriptions ON DELETE CASCADE,
    name            text NOT NULL,
    dose            text NOT NULL,
    period_type     int  NOT NULL,
    `period`        text NOT NULL
);

CREATE TABLE IF NOT EXISTS prescription_metrics
(
    id              bigint AUTO_INCREMENT PRIMARY KEY,
    prescription_id bigint REFERENCES prescriptions ON DELETE CASCADE,
    name            text NOT NULL,
    period_type     int  NOT NULL,
    `period`        text NOT NULL
);

CREATE TABLE IF NOT EXISTS patient_tokens
(
    card_id bigint PRIMARY KEY REFERENCES patient_cards ON DELETE CASCADE,
    token   binary(16) NOT NULL UNIQUE
)