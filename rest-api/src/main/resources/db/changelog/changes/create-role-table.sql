--liquibase formatted sql
--changeset <olehkvasha>:<create-role-table>
CREATE TABLE IF NOT EXISTS public.role
(
    id bigint NOT NULL,
    name character varying(256) UNIQUE NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);
--rollback DROP TABLE role;
