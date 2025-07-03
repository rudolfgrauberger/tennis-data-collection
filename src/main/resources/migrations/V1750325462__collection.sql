CREATE TABLE public.collection_order (
    id BIGINT NOT NULL,
    due_date TIMESTAMPTZ NOT NULL,
    assigned_collector TEXT NOT NULL,
    status TEXT NOT NULL CHECK (status IN ('PENDING', 'COLLECTED')),
    PRIMARY KEY (id)
);

CREATE TABLE public.event (
    id BIGINT NOT NULL,
    type TEXT NOT NULL CHECK (type IN ('SERVE', 'WINS')),
    collection_order_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.collection_order
    ADD CONSTRAINT fk_collection_oder_match_id FOREIGN KEY (id) REFERENCES public.match;

CREATE SEQUENCE public.event_seq START WITH 1 INCREMENT BY 50 OWNED BY public.event.id;

ALTER TABLE IF EXISTS public.event
    ADD CONSTRAINT fk_event_collection_order_id FOREIGN KEY (collection_order_id) REFERENCES public.collection_order,
    ADD CONSTRAINT fk_event_player_id FOREIGN KEY (player_id) REFERENCES public.player;


CREATE TABLE public.player_kpi (
    id BIGINT NOT NULL,
    name TEXT NOT NULL CHECK (name IN ('SERVES','WINS')),
    value BIGINT NOT NULL,
    match_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.player_kpi_seq START WITH 1 INCREMENT BY 50 OWNED BY public.player_kpi.id;

ALTER TABLE IF EXISTS public.player_kpi
    ADD CONSTRAINT fk_player_kpi_match_id FOREIGN KEY (match_id) REFERENCES public.match,
    ADD CONSTRAINT fk_player_kpi_player_id FOREIGN KEY (player_id) REFERENCES public.player;


CREATE TABLE public.match_result (
    match_id BIGINT NOT NULL,
    created_date TIMESTAMPTZ NOT NULL,
    result TEXT NOT NULL,
    PRIMARY KEY (match_id)
);

ALTER TABLE IF EXISTS public.match_result
    ADD CONSTRAINT fk_match_result_match_id FOREIGN KEY (match_id) REFERENCES public.match;
