#!/bin/bash
# EPITECH 2019 

echo "**************************************************************************************************************"
command -v pg_isready

echo "$PGUSER $PGHOST:$PGPORT ---- VERYFYING"

while ! pg_isready -q -h $PGHOST -p $PGPORT --username=$PGUSER
do
  echo "$(date) - waiting database to start"
  sleep 2
done

# exec java -jar app.jar


# # Create, migrate, and seed database if it doesn't exist.
if [[ -z `psql -Atqc "\\list $PGDATABASE" --username=$PGUSER` ]]; then
  echo "Database $PGDATABASE does not exist. Creating..."
  createdb -E UTF8 $PGDATABASE -l en_US.UTF-8 -T template0
  echo "Database $PGDATABASE created."
  fi
# fi
# echo "RUNNING API SERVER PHOENIX || LET'S GET READY TO RUMBLE !!"
# exec mvn install compile