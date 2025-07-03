CREATE TABLE public.competition_mapping (
    competition_id BIGINT NOT NULL,
    provider TEXT NOT NULL CHECK (provider IN ('EXAMPLE_COM','OTHER_API')),
    created_at TIMESTAMPTZ NOT NULL,
    provider_id TEXT NOT NULL,
    PRIMARY KEY (competition_id, provider)
);

ALTER TABLE IF EXISTS public.competition_mapping
    ADD CONSTRAINT fk_competition_mapping_competition_id FOREIGN KEY (competition_id) REFERENCES public.competition ON DELETE CASCADE;

CREATE TABLE public.match_mapping (
    match_id BIGINT NOT NULL,
    provider TEXT NOT NULL CHECK (provider IN ('EXAMPLE_COM','OTHER_API')),
    created_at TIMESTAMPTZ NOT NULL,
    provider_id TEXT NOT NULL,
    PRIMARY KEY (match_id, provider)
);

ALTER TABLE IF EXISTS public.match_mapping
    ADD CONSTRAINT fk_match_mapping_match_id FOREIGN KEY (match_id) REFERENCES public.match ON DELETE CASCADE;

CREATE TABLE public.player_mapping (
    player_id BIGINT NOT NULL,
    provider TEXT NOT NULL CHECK (provider IN ('EXAMPLE_COM','OTHER_API')),
    created_at TIMESTAMPTZ NOT NULL,
    provider_id TEXT NOT NULL,
    PRIMARY KEY (player_id, provider)
);

ALTER TABLE IF EXISTS public.player_mapping
    ADD CONSTRAINT fk_player_mapping_player_id FOREIGN KEY (player_id) REFERENCES public.player ON DELETE CASCADE;
