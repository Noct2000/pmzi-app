--liquibase formatted sql
--changeset <olehkvasha>:<add-first-user-admin-row-to-user-table>
INSERT INTO public.user (id, username, password)
VALUES (1, 'ADMIN', '0.0-0.89792768068929131.9990403171614626-2.0303158706619232.055913823950141');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

--rollback DELETE FROM user_role WHERE user_id = 1;
--rollback DELETE FROM user WHERE user_id = 1;
