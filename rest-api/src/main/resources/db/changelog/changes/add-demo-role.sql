--liquibase formatted sql
--changeset <olehkvasha>:<add-basic-roles>

INSERT INTO role (id, name) VALUES (nextval('role_id_seq'), 'DEMO');
--rollback DELETE FROM role WHERE name='DEMO';
