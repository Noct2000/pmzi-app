--liquibase formatted sql
--changeset <olehkvasha>:<add-first-user-admin-row-to-user-table>
INSERT INTO public.user (id, username, password)
VALUES (1, 'ADMIN', 'ADMIN');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

--rollback DELETE FROM user_role WHERE user_id = 1;
--rollback DELETE FROM user WHERE user_id = 1;
