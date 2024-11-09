CREATE TABLE public.lessons (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
);

ALTER TABLE public.lessons OWNER TO vocabcheck;

CREATE SEQUENCE public.lessons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lessons_id_seq OWNER TO vocabcheck;

ALTER SEQUENCE public.lessons_id_seq OWNED BY public.lessons.id;

CREATE TABLE public.words (
    id integer NOT NULL,
    lesson_id integer NOT NULL,
    english character varying(255) NOT NULL,
    hiragana character varying(255) NOT NULL,
    kanji character varying(255) NOT NULL
);

ALTER TABLE public.words OWNER TO vocabcheck;

CREATE SEQUENCE public.words_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.words_id_seq OWNER TO vocabcheck;

ALTER SEQUENCE public.words_id_seq OWNED BY public.words.id;

ALTER TABLE ONLY public.lessons ALTER COLUMN id SET DEFAULT nextval('public.lessons_id_seq'::regclass);

ALTER TABLE ONLY public.words ALTER COLUMN id SET DEFAULT nextval('public.words_id_seq'::regclass);

ALTER TABLE ONLY public.words
    ADD CONSTRAINT words_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.lessons
    ADD CONSTRAINT lessons_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.words
    ADD CONSTRAINT fkmied7el0kcl27ul1mn6384hki FOREIGN KEY (lesson_id) REFERENCES public.lessons(id);