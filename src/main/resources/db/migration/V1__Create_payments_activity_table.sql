-- object: payments.payment_instrument_type | type: TYPE --
-- DROP TYPE IF EXISTS payments.payment_instrument_type CASCADE;
CREATE
TYPE payments.payment_instrument_type AS
 ENUM ('ACH','CARD');
-- ddl-end --
ALTER
TYPE payments.payment_instrument_type OWNER TO payments_user;
-- ddl-end --

-- object: payments.payment_instrument | type: TABLE --
-- DROP TABLE IF EXISTS payments.payment_instrument CASCADE;
CREATE TABLE payments.payment_activity
(
    id             BIGINT        NOT NULL GENERATED ALWAYS AS IDENTITY,
    created_at     TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP WITH TIME ZONE,
    invoice_number VARCHAR(128)  NOT NULL,
    currency       VARCHAR(128)  NOT NULL,
    amount         NUMERIC(5, 2) NOT NULL,
    type payments.payment_instrument_type NOT NULL
);
-- ddl-end --
ALTER TABLE payments.payment_activity OWNER TO payments_user;
-- ddl-end --
