--liquibase formatted sql
--changeset <olehkvasha>:<add-first-user-admin-row-to-user-table>
WITH inserted_users AS (
    INSERT INTO public."user" (id, username, password)
        VALUES
            (nextval('user_id_seq'), 'user1', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141'),
            (nextval('user_id_seq'), 'user2', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141'),
            (nextval('user_id_seq'), 'user3', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141'),
            (nextval('user_id_seq'), 'user4', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141'),
            (nextval('user_id_seq'), 'user5', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141'),
            (nextval('user_id_seq'), 'user6', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141')
        RETURNING id
)
INSERT INTO user_role (user_id, role_id)
SELECT id, 2 FROM inserted_users;
