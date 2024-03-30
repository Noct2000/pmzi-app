--liquibase formatted sql
--changeset <olehkvasha>:<create-user-table>
CREATE TABLE IF NOT EXISTS public.user
(
    id bigint NOT NULL,
    username character varying(256) NOT NULL,
    password character varying(256) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
    );

--rollback DROP TABLE user;