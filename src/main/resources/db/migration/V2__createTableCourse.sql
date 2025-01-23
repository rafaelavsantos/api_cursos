CREATE TABLE Course (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    createdAt datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    name varchar(50) NOT NULL,
    code varchar(50) NOT NULL,
    instructorEmail varchar(50) NOT NULL,
    description varchar(100) NOT NULL,
    status enum('ACTIVE', 'INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
    inactivedDate DATE,
    PRIMARY KEY (id),
    CONSTRAINT UC_Code UNIQUE (code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;