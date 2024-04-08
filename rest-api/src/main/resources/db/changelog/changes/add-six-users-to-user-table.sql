--liquibase formatted sql
--changeset <olehkvasha>:<add-first-user-admin-row-to-user-table>
WITH inserted_users AS (
    INSERT INTO public."user" (id, username, password)
        VALUES
            (nextval('user_id_seq'), 'user1', 'DGPLQ'),
            (nextval('user_id_seq'), 'user2', 'DGPLQ'),
            (nextval('user_id_seq'), 'user3', 'DGPLQ'),
            (nextval('user_id_seq'), 'user4', 'DGPLQ'),
            (nextval('user_id_seq'), 'user5', 'DGPLQ'),
            (nextval('user_id_seq'), 'user6', 'DGPLQ')
        RETURNING id
)
INSERT INTO user_role (user_id, role_id)
SELECT id, 2 FROM inserted_users;
