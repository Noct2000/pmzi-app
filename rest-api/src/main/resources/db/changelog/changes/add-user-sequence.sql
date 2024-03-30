--liquibase formatted sql
--changeset <olehkvasha>:<create-user-sequence-id>
CREATE SEQUENCE IF NOT EXISTS public.user_id_seq INCREMENT 1 START 2 MINVALUE 1;

--rollback DROP SEQUENCE public.user_id_seq;
