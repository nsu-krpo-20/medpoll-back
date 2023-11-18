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
    id           bigint AUTO_INCREMENT PRIMARY KEY,
    card_id      bigint REFERENCES patient_cards ON DELETE CASCADE,
    created_by   bigint NOT NULL REFERENCES users,
    is_active    tinyint NOT NULL DEFAULT 1,
    created_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    edited_time  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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

CREATE TABLE IF NOT EXISTS prescription_presets (
    `id` INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` TEXT NOT NULL,
    `suggested_meds` JSON NULL,
    `suggested_metrics` JSON NULL
);

CREATE TABLE IF NOT EXISTS patient_tokens
(
    card_id bigint PRIMARY KEY REFERENCES patient_cards ON DELETE CASCADE,
    token   binary(16) NOT NULL UNIQUE
)