BEGIN TRANSACTION;

DROP TABLE IF EXISTS member, interest_group, event, event_attendees, group_members;

CREATE TABLE member (
    member_number serial,
    last_name varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL,
    phone_number varchar(12),
    date_of_birth date NOT NULL,
    flag_reminder_emails boolean NOT NULL,
	CONSTRAINT PK_member PRIMARY KEY(member_number)
);

CREATE TABLE interest_group (
    group_number serial,
	name varchar(50) NOT NULL,
    CONSTRAINT PK_interest_group PRIMARY KEY(group_number),
    CONSTRAINT UQ_interest_group_name UNIQUE(name)
);

CREATE TABLE event (
    event_number serial,
    name varchar(50) NOT NULL,
    description varchar(300) NOT NULL,
    start_date date NOT NULL,
    start_time time NOT NULL,
    duration int NOT NULL,
    host_group_number int,
    CONSTRAINT PK_event PRIMARY KEY(event_number),
    CONSTRAINT CHK_event_duration CHECK(duration >= 30)
);

CREATE TABLE event_attendees (
    event_number int NOT NULL,
    member_number int NOT NULL,
    CONSTRAINT PK_event_attendees PRIMARY KEY(event_number, member_number)
);

CREATE TABLE group_members (
    group_number int NOT NULL,
    member_number int NOT NULL,
    CONSTRAINT PK_group_members PRIMARY KEY(group_number, member_number)
);

-- four events
INSERT INTO event(name, description, start_date, start_time, duration, host_group_number) values('Dunk Contest', 'Contestents judged 1-10 on best slam-dunk', '2022-2-14', '18:00:00', 120, 1);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_number) values('3-pt-Shootout', 'Most 3 pointers in 30 attempts', '2022-02-14', '15:00:00', 90, 2);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_number) values('Skills Challenge', 'Passing, dribbling, shooting', '2022-02-13', '15:00:00', 60, 3);
INSERT INTO event(name, description, start_date, start_time, duration, host_group_number) values('All-Star-Game', 'The main event', '2022-02-15', '20:00:00', 180, 1);

-- three groups
INSERT INTO interest_group(name) values('Dunkers');
INSERT INTO interest_group(name) values('Shooters');
INSERT INTO interest_group(name) values('All-stars');

-- eight members
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Johnson','Magic', 'magicjohnson@gmail.com', '513-123-4567', '1965-05-27', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Bird','Larry','larrybird@gmail.com', '987-543-321', '1968-02-18', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Michael', 'Jordan', 'mj23@yahoo.com', '513-222-5676', '1975-10-24', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Bird', 'Sue', 'sbird90@gmail.com', '444-444-4444', '1988-12-25', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values ('Taurassi', 'Diana', 'dianT123@gmail.com', '987-345-754', '1998-04-20', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('James', 'Lebron', 'kingjames@gmail.com', '845-721-0787', '1990-06-21', true);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Curry', 'Steph', 'stephcurry@aol.com', '430-123-4567', '2003-06-20', false);
INSERT INTO member(last_name, first_name, email_address, phone_number, date_of_birth, flag_reminder_emails) values('Durant', 'Kevin', 'easymoney@yahoo.com', '419-222-0932', '2000-01-01', true);

-- add members to events
-- event 1
INSERT INTO event_attendees(event_number, member_number) values(1, 1);
INSERT INTO event_attendees(event_number, member_number) values(1, 2);
INSERT INTO event_attendees(event_number, member_number) values(1, 3);

-- event 2
INSERT INTO event_attendees(event_number, member_number) values(2, 4);
INSERT INTO event_attendees(event_number, member_number) values(2, 5);
INSERT INTO event_attendees(event_number, member_number) values(2, 2);


-- event 3
INSERT INTO event_attendees(event_number, member_number) values(3, 6);
INSERT INTO event_attendees(event_number, member_number) values(3, 7);
INSERT INTO event_attendees(event_number, member_number) values(3, 8);

-- event 4
INSERT INTO event_attendees(event_number, member_number) values(4, 1);
INSERT INTO event_attendees(event_number, member_number) values(4, 2);
INSERT INTO event_attendees(event_number, member_number) values(4, 7);
INSERT INTO event_attendees(event_number, member_number) values(4, 8);

-- add members to groups
-- group 1
INSERT INTO group_members(group_number, member_number) values(1, 1);
INSERT INTO group_members(group_number, member_number) values(1, 2);
INSERT INTO group_members(group_number, member_number) values(1, 3);
INSERT INTO group_members(group_number, member_number) values(1, 8);

-- group 2
INSERT INTO group_members(group_number, member_number) values(2, 7);
INSERT INTO group_members(group_number, member_number) values(2, 6);
INSERT INTO group_members(group_number, member_number) values(2, 5);
INSERT INTO group_members(group_number, member_number) values(2, 4);

-- group 3
INSERT INTO group_members(group_number, member_number) values(3, 2);
INSERT INTO group_members(group_number, member_number) values(3, 6);
INSERT INTO group_members(group_number, member_number) values(3, 1);
INSERT INTO group_members(group_number, member_number) values(3, 8);

-- foreign keys
ALTER TABLE event ADD CONSTRAINT FK_event FOREIGN KEY(host_group_number) REFERENCES interest_group(group_number);

ALTER TABLE event_attendees ADD CONSTRAINT FK_event FOREIGN KEY(event_number) REFERENCES event(event_number);
ALTER TABLE event_attendees ADD CONSTRAINT FK_member FOREIGN KEY(member_number) REFERENCES member(member_number);

ALTER TABLE group_members ADD CONSTRAINT FK_group FOREIGN KEY(group_number) REFERENCES interest_group(group_number);
ALTER TABLE group_members ADD CONSTRAINT FK_member FOREIGN KEY(member_number) REFERENCES member(member_number);

COMMIT;