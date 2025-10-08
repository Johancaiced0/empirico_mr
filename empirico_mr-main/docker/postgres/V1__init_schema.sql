-- ==============================
-- Roles de usuario
-- ==============================
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE -- nombre del rol (ej: ADMIN, TECH, SUPERVISOR, CLIENT)
);

-- ==============================
-- Usuarios del sistema
-- ==============================
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE, -- nombre de usuario
    password VARCHAR(255) NOT NULL,        -- contraseña encriptada
    full_name VARCHAR(200),                -- nombre completo
    email VARCHAR(150) UNIQUE,             -- correo electrónico
    active BOOLEAN DEFAULT TRUE,           -- indica si el usuario está activo
    role_id INT NOT NULL REFERENCES role(id) ON DELETE RESTRICT -- cada usuario tiene un rol
);

-- ==============================
-- Equipos registrados
-- ==============================
CREATE TABLE device (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,             -- nombre del equipo
    ram VARCHAR(100),                       -- memoria RAM
    rom VARCHAR(100),                       -- memoria ROM
    hard_drive VARCHAR(200),                -- disco duro
    graphics_card VARCHAR(200),             -- tarjeta gráfica
    serial VARCHAR(150) UNIQUE,             -- número de serie único
    client_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE, -- cliente dueño del equipo
    created_at TIMESTAMP DEFAULT now(),     -- fecha de registro
    updated_at TIMESTAMP DEFAULT now()      -- fecha de última actualización
);

-- ==============================
-- Tipos ENUM para mantenimiento
-- ==============================
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'maintenance_type') THEN
        CREATE TYPE maintenance_type AS ENUM('PREVENTIVE','CORRECTIVE'); -- tipo de mantenimiento
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'maintenance_status') THEN
        CREATE TYPE maintenance_status AS ENUM(
            'CREATED', 'PENDING', 'ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'
        );
    END IF;
END$$;

-- ==============================
-- Tickets de mantenimiento
-- ==============================
CREATE TABLE maintenance (
    id SERIAL PRIMARY KEY,
    device_id INT NOT NULL REFERENCES device(id) ON DELETE CASCADE, -- equipo relacionado
    problem_description TEXT,                                       -- problema reportado
    type maintenance_type DEFAULT 'CORRECTIVE',                     -- tipo de mantenimiento
    status maintenance_status DEFAULT 'CREATED',                    -- estado actual
    created_at TIMESTAMP DEFAULT now(),                             -- fecha de creación
    updated_at TIMESTAMP DEFAULT now(),                             -- fecha de última actualización
    technician_id INT REFERENCES users(id),                         -- técnico asignado
    admin_id INT REFERENCES users(id),                              -- administrador que creó el ticket
    supervisor_id INT REFERENCES users(id),                         -- supervisor que valida
    resolution_notes TEXT                                           -- notas de resolución
);

-- ==============================
-- Historial de mantenimiento (acciones)
-- ==============================
CREATE TABLE maintenance_history (
    id SERIAL PRIMARY KEY,
    maintenance_id INT NOT NULL REFERENCES maintenance(id) ON DELETE CASCADE, -- ticket relacionado
    user_id INT REFERENCES users(id) ON DELETE SET NULL,                      -- usuario que registró la acción
    description TEXT NOT NULL,                                                -- descripción de la acción
    created_at TIMESTAMP DEFAULT now(),                                       -- fecha de la acción
    details JSONB                                                             -- detalles adicionales (ej. piezas cambiadas)
);

-- ==============================
-- Índices recomendados
-- ==============================
CREATE INDEX idx_device_client ON device(client_id);
CREATE INDEX idx_maintenance_status ON maintenance(status);
CREATE INDEX idx_maintenance_technician ON maintenance(technician_id);
