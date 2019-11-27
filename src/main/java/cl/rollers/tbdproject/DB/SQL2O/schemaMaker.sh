# Script to run Schema Makers in docker.

# Enter in the directory to run it by IDE.
# cd src/main/java/cl/rollers/tbdproject/DB/

# First Schema Maker.
docker exec -i postgres psql -U postgres -d tbd1 < firstSchemaMaker.sql

# Second Schema Maker.
docker exec -i postgres psql -U postgres -d tbd2 < secondSchemaMaker.sql

# Add more following the same structure.
#   docker exec -i containerName psql -U postgres -d dbName < sqlFile