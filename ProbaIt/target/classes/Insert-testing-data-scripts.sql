--insert users ->

INSERT INTO "POLLIT".users(
	id, email, username, password)
	VALUES (1, 'user1@em.ro', 'user1', '123');

INSERT INTO "POLLIT".users(
	id, email, username, password)
	VALUES (2, 'user2@em.ro', 'user2', '123');

-- insert polls ->

INSERT INTO "POLLIT".polls(
	id, creator_id, question, is_multiple_choice)
	VALUES (1, 1, 'Care este cel mai tare animal ?', true);

INSERT INTO "POLLIT".polls(
	id, creator_id, question, is_multiple_choice)
	VALUES (2, 1, 'You like jazz ?', false);

INSERT INTO "POLLIT".polls(
	id, creator_id, question, is_multiple_choice)
	VALUES (3, 2, 'Cine este coordonatorul departamentului de IT ?', false);

--insert options ->

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (1, 1, 'Caine');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (2, 1, 'Pisica');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (3, 1, 'Papagal');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (4, 1, 'Toate');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (5, 2, 'Yes');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (6, 2, 'No');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (7, 3, 'Edi');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (8, 3, 'Mari');

INSERT INTO "POLLIT".options(
	id, poll_id, option_text)
	VALUES (9, 3, 'Bogdan');






