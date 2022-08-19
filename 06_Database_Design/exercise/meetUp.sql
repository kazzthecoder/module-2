BEGIN TRANSACTION;

DROP TABLE IF EXISTS member, interest_group, event, event_attendee, group_member;

CREATE TABLE member (
	member_id serial,
    last_name varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    phone_number varchar(12),
    date_of_birth date NOT NULL,
    flag_reminder_emails boolean NOT NULL,
	CONSTRAINT PK_member PRIMARY KEY(member_id)
);

CREATE TABLE interest_group  (
    group_id serial,
	name varchar(50) NOT NULL,
    CONSTRAINT PK_interest_group PRIMARY KEY(group_id),
    CONSTRAINT UQ_interest_group_name UNIQUE(name)
);

CREATE TABLE event (
    event_id serial,
    name varchar(50) NOT NULL,
    description varchar(300) NOT NULL,
    start_date date NOT NULL,
    start_time time NOT NULL,
    duration int NOT NULL,
    host_group_id int,
    CONSTRAINT PK_event PRIMARY KEY(event_id),
    CONSTRAINT CHK_event_duration CHECK(duration >= 30)
);

CREATE TABLE event_attendee (
    event_id int NOT NULL,
    member_id int NOT NULL,
    CONSTRAINT PK_event_attendee PRIMARY KEY(event_id, member_id)
);

CREATE TABLE group_member (
    group_id int NOT NULL,
    member_id int NOT NULL,
    CONSTRAINT PK_group_member PRIMARY KEY(group_id, member_id)
);

-- four events
INSERT INTO event(name, description, start_date, start_time, duration, host_group_id) values('Dunk Contest', 'Contestents judged 1-10 on best slam-dunk', '2022-2-14', '18:00:00', 120, 1);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_id) values('3-pt-Shootout', 'Most 3 pointers in 30 attempts', '2022-02-14', '15:00:00', 90, 2);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_id) values('Skills Challenge', 'Passing, dribbling, shooting', '2022-02-13', '15:00:00', 60, 3);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_id) values('All-Star-Game', 'The main event', '2022-02-15', '20:00:00', 180, 1);

-- three groups
INSERT INTO interest_group(name) values('Dunkers');
INSERT INTO interest_group(name) values('Shooters');
INSERT INTO interest_group(name) values('Stars');

-- eight members
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Johnson','Magic', 'magicjohnson@gmail.com', '513-123-4567', '1965-05-27', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Bird','Larry','larrybird@gmail.com', '987-543-321', '1968-02-18', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Michael', 'Jordan', 'mj23@yahoo.com', '513-222-5676', '1975-10-24', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Bird', 'Sue', 'sbird90@gmail.com', '444-444-4444', '1988-12-25', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values ('Taurassi', 'Diana', 'dianT123@gmail.com', '987-345-754', '1998-04-20', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('James', 'Lebron', 'kingjames@gmail.com', '845-721-0787', '1990-06-21', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Curry', 'Steph', 'stephcurry@aol.com', '430-123-4567', '2003-06-20', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Durant', 'Kevin', 'easymoney@yahoo.com', '419-222-0932', '2000-01-01', true);



-- event 1
INSERT INTO event_attendee(event_id, member_id) values(1, 1);
INSERT INTO event_attendee(event_id, member_id) values(1, 2);
INSERT INTO event_attendee(event_id, member_id) values(1, 3);
INSERT INTO event_attendee(event_id, member_id) values(1, 4);

-- event 2
INSERT INTO event_attendee(event_id, member_id) values(2, 4);
INSERT INTO event_attendee(event_id, member_id) values(2, 5);
INSERT INTO event_attendee(event_id, member_id) values(2, 6);

-- event 3
INSERT INTO event_attendee(event_id, member_id) values(3, 5);
INSERT INTO event_attendee(event_id, member_id) values(3, 6);
INSERT INTO event_attendee(event_id, member_id) values(3, 7);
INSERT INTO event_attendee(event_id, member_id) values(3, 8);

-- event 4
INSERT INTO event_attendee(event_id, member_id) values(4, 4);
INSERT INTO event_attendee(event_id, member_id) values(4, 3);
INSERT INTO event_attendee(event_id, member_id) values(4, 2);
INSERT INTO event_attendee(event_id, member_id) values(4, 1);

-- group 1
INSERT INTO group_member(group_id, member_id) values(1, 1);
INSERT INTO group_member(group_id, member_id) values(1, 2);
INSERT INTO group_member(group_id, member_id) values(1, 8);
INSERT INTO group_member(group_id, member_id) values(1, 7);
INSERT INTO group_member(group_id, member_id) values(1, 4);

-- group 2
INSERT INTO group_member(group_id, member_id) values(2, 6);
INSERT INTO group_member(group_id, member_id) values(2, 5);
INSERT INTO group_member(group_id, member_id) values(2, 4);
INSERT INTO group_member(group_id, member_id) values(2, 3);
INSERT INTO group_member(group_id, member_id) values(2, 2);

-- group 3
INSERT INTO group_member(group_id, member_id) values(3, 6);
INSERT INTO group_member(group_id, member_id) values(3, 8);
INSERT INTO group_member(group_id, member_id) values(3, 7);
INSERT INTO group_member(group_id, member_id) values(3, 1);

-- foreign keys
ALTER TABLE event ADD CONSTRAINT FK_event FOREIGN KEY(host_group_id) REFERENCES interest_group(group_id);

ALTER TABLE event_attendee ADD CONSTRAINT FK_event FOREIGN KEY(event_id) REFERENCES event(event_id);
ALTER TABLE event_attendee ADD CONSTRAINT FK_member FOREIGN KEY(member_id) REFERENCES member(member_id);


ALTER TABLE group_member ADD CONSTRAINT FK_group FOREIGN KEY(group_id) REFERENCES interest_group(group_id);
ALTER TABLE group_member ADD CONSTRAINT FK_member FOREIGN KEY(member_id) REFERENCES member(member_id);

COMMIT;