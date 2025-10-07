#!/bin/sh
DB_HOST=${DB_HOST:-db}
DB_PORT=${DB_PORT:-5432}
TIMEOUT=60
ELAPSED=0

echo "Esperando que PostgreSQL esté listo en $DB_HOST:$DB_PORT..."

while ! pg_isready -h "$DB_HOST" -p "$DB_PORT" -U "$SPRING_DATASOURCE_USERNAME" >/dev/null 2>&1; do
  sleep 2
  ELAPSED=$((ELAPSED + 2))
  if [ $ELAPSED -ge $TIMEOUT ]; then
    echo "❌ Error: PostgreSQL no respondió después de $TIMEOUT segundos."
    exit 1
  fi
  echo "Esperando..."
done

echo "✅ PostgreSQL listo, iniciando la app..."
