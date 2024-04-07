--liquibase formatted sql
--changeset <olehkvasha>:<create-question-table>
CREATE TABLE IF NOT EXISTS public.question
(
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    question varchar(500) NOT NULL,
    answer varchar(500) NOT NULL,
    CONSTRAINT question_pk PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

--rollback DROP TABLE IF EXISTS question;