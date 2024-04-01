--liquibase formatted sql
--changeset <olehkvasha>:<add-blocked-column-to-user-table>
ALTER TABLE public.user
    ADD COLUMN IF NOT EXISTS blocked BOOLEAN DEFAULT FALSE NOT NULL;

--rollback ALTER TABLE user DROP COLUMN IF EXISTS blocked;