CREATE SCHEMA fdi;

CREATE TABLE fdi.examplecom_competition (
    id TEXT NOT NULL,
    city TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE fdi.examplecom_match (
    id TEXT NOT NULL,
    name TEXT NOT NULL,
    match_date DATE,
    competition_id TEXT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS fdi.examplecom_match
    ADD CONSTRAINT fk_examplecom_match_competition_id FOREIGN KEY (competition_id) REFERENCES fdi.examplecom_competition;


CREATE TABLE fdi.otherapi_competition (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE fdi.otherapi_match (
    id UUID NOT NULL,
    name TEXT NOT NULL,
    match_date DATE,
    competition_id UUID NOT NULL,
    team_one_first_player_id UUID NOT NULL,
    team_one_second_player_id UUID,
    team_two_first_player_id UUID NOT NULL,
    team_two_second_player_id UUID,
    PRIMARY KEY (id)
);

CREATE TABLE fdi.otherapi_player (
    id UUID NOT NULL,
    birth_date date,
    name TEXT NOT NULL,
    gender TEXT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS fdi.otherapi_match
    ADD CONSTRAINT fk_otherapi_match_competition_id FOREIGN KEY (competition_id) REFERENCES fdi.otherapi_competition,
    ADD CONSTRAINT fk_otherapi_match_team_one_first_player_id FOREIGN KEY (team_one_first_player_id) REFERENCES fdi.otherapi_player,
    ADD CONSTRAINT fk_otherapi_match_team_one_second_player_id FOREIGN KEY (team_one_second_player_id) REFERENCES fdi.otherapi_player,
    ADD CONSTRAINT fk_otherapi_match_team_two_first_player_id FOREIGN KEY (team_two_first_player_id) REFERENCES fdi.otherapi_player,
    ADD CONSTRAINT fk_otherapi_match_team_two_second_player_id FOREIGN KEY (team_two_second_player_id) REFERENCES fdi.otherapi_player;
