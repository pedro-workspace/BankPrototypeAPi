CREATE TABLE credit(
    CREDIT_ID BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL UNIQUE,
    CREDIT_VALUE DECIMAL NOT NULL,
    DAY_ONE_INSTALLMENT DATE NOT NULL,
    NUMBER_OF_INSTALLMENTS INT NOT NULL,
    STATUS INT NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    CONSTRAINT foreign_key_from_customer FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER (CUSTOMER_ID)
);