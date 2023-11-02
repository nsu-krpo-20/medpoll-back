CREATE TABLE IF NOT EXISTS patient_tokens
(
    card_id bigint PRIMARY KEY REFERENCES patient_cards ON DELETE CASCADE,
    token   binary(16) NOT NULL UNIQUE
)