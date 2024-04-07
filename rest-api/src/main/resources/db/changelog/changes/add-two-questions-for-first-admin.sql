--liquibase formatted sql
--changeset <olehkvasha>:<add-two-questions-for-first-admin>
INSERT INTO public.question (id, question, answer, user_id)
VALUES (nextval('question_id_seq'), 'What is the color of your hair?', 'brown', 1),
       (nextval('question_id_seq'), 'What is your favorite actor?', 'Ryan Gosling', 1);
--rollback DELETE FROM question WHERE user_id=1;
