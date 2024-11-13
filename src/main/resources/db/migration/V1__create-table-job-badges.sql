CREATE TABLE badges (
                        identifier UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        friendly_badge BOOLEAN NOT NULL
);

CREATE TABLE job (
                     identifier UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                     id BIGINT NOT NULL,
                     company_id BIGINT NOT NULL,
                     name VARCHAR(255),
                     description TEXT,
                     career_page_id BIGINT,
                     career_page_name VARCHAR(255),
                     career_page_logo VARCHAR(255),
                     type VARCHAR(50),
                     published_date VARCHAR(50),
                     application_deadline VARCHAR(50),
                     is_remote_work BOOLEAN NOT NULL,
                     city VARCHAR(255),
                     state VARCHAR(255),
                     country VARCHAR(255),
                     job_url VARCHAR(255),
                     badges_identifier UUID,
                     disabilities BOOLEAN NOT NULL,
                     workplace_type VARCHAR(255),
                     career_page_url VARCHAR(255),
                     FOREIGN KEY (badges_identifier) REFERENCES badges(identifier)
);
