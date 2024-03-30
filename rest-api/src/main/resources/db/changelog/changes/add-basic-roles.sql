--liquibase formatted sql
--changeset <olehkvasha>:<add-basic-roles>

INSERT INTO role (id, name) VALUES
                                (1, 'ADMIN'),
                                (2, 'USER');
--rollback DELETE FROM role WHERE id IN (1, 2);
