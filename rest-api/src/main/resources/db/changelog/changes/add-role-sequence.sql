--liquibase formatted sql
--changeset <olehkvasha>:<add-role-sequence>
CREATE SEQUENCE IF NOT EXISTS public.role_id_seq INCREMENT 1 START 3 MINVALUE 1;

--rollback DROP SEQUENCE public.role_id_seq;
