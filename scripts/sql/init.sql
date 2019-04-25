-- DROP ROLE IF EXISTS payments_user;
CREATE ROLE payments_user WITH CREATEDB INHERIT LOGIN ENCRYPTED PASSWORD 'MGY5MzkzZTMxYQo';

-- DROP DATABASE IF EXISTS payments;
CREATE DATABASE payments ENCODING = 'UTF8' OWNER = payments_user;

-- DROP SCHEMA IF EXISTS payments CASCADE;
CREATE SCHEMA payments;

ALTER SCHEMA payments OWNER TO payments_user;

SET search_path TO pg_catalog,public,payments;
