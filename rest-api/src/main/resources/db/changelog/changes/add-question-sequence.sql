--liquibase formatted sql
--changeset <olehkvasha>:<create-question-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.question_id_seq INCREMENT 1 START 1 MINVALUE 1;

--rollback DROP SEQUENCE public.question_id_seq;