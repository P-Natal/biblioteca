FLYWAY_MIGRATE= mvn flyway:migrate
FLYWAY_CLEAN= mvn flyway:clean

FLYWAY_DB_USER_LOCAL= -Dflyway.user=postgres
FLYWAY_DB_PASSWORD_LOCAL= -Dflyway.password=postgres
FLYWAY_DB_URL_LOCAL=-Dflyway.url=jdbc:postgresql://localhost:5432/bibliotecadb


build-run: build
	docker-compose up

run-d: build
	docker-compose up -d

build:
	mvn clean install



flyway-clean:
	$(FLYWAY_CLEAN) $(FLYWAY_DB_URL_LOCAL) $(FLYWAY_DB_USER_LOCAL) $(FLYWAY_DB_PASSWORD_LOCAL)

flyway-migrate:
	$(FLYWAY_MIGRATE) $(FLYWAY_DB_URL_LOCAL) $(FLYWAY_DB_USER_LOCAL) $(FLYWAY_DB_PASSWORD_LOCAL)

flyway-reset: flyway-clean_local flyway-migrate_local