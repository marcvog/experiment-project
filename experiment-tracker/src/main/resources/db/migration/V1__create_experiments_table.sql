CREATE TABLE experiments (
                             id UUID PRIMARY KEY,
                             name TEXT NOT NULL,
                             parameters JSONB,
                             status TEXT NOT NULL,
                             created_at TIMESTAMP NOT NULL,
                             started_at TIMESTAMP,
                             finished_at TIMESTAMP
);