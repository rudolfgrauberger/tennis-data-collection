CREATE TABLE public.competition (
    id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by uuid,
    last_modified_at TIMESTAMPTZ,
    last_modified_by uuid,
    tournament_id BIGINT,
    type TEXT NOT NULL CHECK (type in ('SINGLES','DOUBLES')),
    gender TEXT NOT NULL CHECK (gender in ('MEN','WOMEN','MIXED')),
    surface TEXT NOT NULL CHECK (surface in ('HARD','CLAY','GRASS')),
    countryiso TEXT NOT NULL,
    city TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE public.match (
    id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by uuid,
    last_modified_at TIMESTAMPTZ,
    last_modified_by uuid,
    competition_id BIGINT NOT NULL,
    match_date DATE NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.double_match (
    id BIGINT NOT NULL,
    first_team_first_player_id BIGINT NOT NULL,
    first_team_second_player_id BIGINT NOT NULL,
    second_team_first_player_id BIGINT NOT NULL,
    second_team_second_player_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.single_match (
    id BIGINT NOT NULL,
    first_player_id BIGINT NOT NULL,
    second_player_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE public.player (
    id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by uuid,
    last_modified_at TIMESTAMPTZ,
    last_modified_by uuid,
    date_of_birth date,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    gender TEXT CHECK (gender in ('MEN','WOMEN')),
    PRIMARY KEY (id)
);

CREATE TABLE public.tournament (
    id BIGINT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    created_by uuid,
    last_modified_at TIMESTAMPTZ,
    last_modified_by uuid,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    countryiso TEXT NOT NULL,
    city TEXT NOT NULL,
    surface TEXT NOT NULL CHECK (surface in ('HARD','CLAY','GRASS')),
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.competition_seq START WITH 1 INCREMENT BY 50 OWNED BY public.competition.id;
CREATE SEQUENCE public.match_seq START WITH 1 INCREMENT BY 50 OWNED BY public.match.id;
CREATE SEQUENCE public.player_seq START WITH 1 INCREMENT BY 50 OWNED BY public.player.id;
CREATE SEQUENCE public.tournament_seq START WITH 1 INCREMENT BY 50 OWNED BY public.tournament.id;

ALTER TABLE IF EXISTS public.competition
    ADD CONSTRAINT fk_competition_tournament_id FOREIGN KEY (tournament_id) REFERENCES public.tournament;

ALTER TABLE IF EXISTS public.double_match
    ADD CONSTRAINT fk_double_match_first_team_first_player_id FOREIGN KEY (first_team_first_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_double_match_first_team_second_player_id FOREIGN KEY (first_team_second_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_double_match_second_team_first_player_id FOREIGN KEY (second_team_first_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_double_match_seocnd_team_second_player_id FOREIGN KEY (second_team_second_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_double_match_match_id FOREIGN KEY (id) REFERENCES public.match;

ALTER TABLE IF EXISTS public.match
    ADD CONSTRAINT fk_match_competition_id FOREIGN KEY (competition_id) REFERENCES public.competition;

ALTER TABLE IF EXISTS public.single_match
    ADD CONSTRAINT fk_single_match_first_player_id FOREIGN KEY (first_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_signle_match_second_player_id FOREIGN KEY (second_player_id) REFERENCES public.player,
    ADD CONSTRAINT fk_single_match_match_id FOREIGN KEY (id) REFERENCES public.match;
