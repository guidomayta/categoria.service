-- Migración 2: Alterar tabla CATEGORIA para MySQL (producción)
-- Cambiar tipo de datos y añadir constraints apropiados para MySQL

ALTER TABLE categoria 
  MODIFY COLUMN estado BOOLEAN NOT NULL DEFAULT TRUE,
  MODIFY COLUMN fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  MODIFY COLUMN fecha_actualizacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

-- Crear índice para búsquedas activas
CREATE INDEX IF NOT EXISTS idx_estado_nombre ON categoria(estado, nombre);
