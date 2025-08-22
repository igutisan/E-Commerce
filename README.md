# E-Commerce API

Una aplicación de comercio electrónico desarrollada con Spring Boot 3.5.4 que proporciona un sistema completo de gestión de productos, usuarios y pedidos con autenticación JWT.

## 🚀 Características

- **Autenticación y Autorización**: Sistema de autenticación JWT con roles (ADMIN, CLIENT)
- **Gestión de Usuarios**: Registro, inicio de sesión y gestión de perfiles
- **Gestión de Productos**: CRUD completo para productos con imágenes
- **Gestión de Pedidos**: Sistema de pedidos con detalles
- **Base de Datos**: PostgreSQL con auditoría automática
- **Seguridad**: Spring Security con JWT
- **Documentación**: API REST bien estructurada
- **Mapeo de Datos**: MapStruct para conversión entre DTOs y entidades
- **Datos de Prueba**: Seed data automática para desarrollo

## 📋 Requisitos Previos

- **Java 24** o superior
- **Maven 3.6+**
- **PostgreSQL 15** (o Docker para ejecutar con contenedor)
- **Git**

## 🛠️ Instalación y Configuración

### 1. Clonar el Repositorio



### 2. Configurar Variables de Entorno

Crear un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
# Base de datos
DB_URL=jdbc:postgresql://localhost:5432/ecommerce
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña

# JWT Secret (usar un secreto seguro en producción)
JWT_SECRET_KEY=404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970
```

### 3. Configurar Base de Datos

#### Opción A: Usar Docker (Recomendado)

```bash
# Configurar las variables de entorno para Docker
export DB_USER=ecommerce_user
export DB_PASSWORD=ecommerce_pass

# Iniciar PostgreSQL con Docker Compose
docker-compose up -d
```

#### Opción B: PostgreSQL Local

1. Instalar PostgreSQL 15
2. Crear base de datos:

```sql
CREATE DATABASE ecommerce;
CREATE USER ecommerce_user WITH PASSWORD 'ecommerce_pass';
GRANT ALL PRIVILEGES ON DATABASE ecommerce TO ecommerce_user;
```

### 4. Compilar el Proyecto

```bash
mvn clean compile
```

### 5. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 🔧 Configuración de Perfiles

### Desarrollo (dev) - Por defecto
- Muestra consultas SQL
- Actualización automática del esquema de BD
- Carga de datos de prueba automática

### Producción (prod)
```bash
mvn spring-boot:run -Dspring.profiles.active=prod
```

## 📚 API Endpoints

### Autenticación (`/api/auth`)

| Método | Endpoint | Descripción | Cuerpo de la Petición |
|--------|----------|-------------|----------------------|
| POST | `/api/auth/register` | Registrar usuario | UserRequestDTO |
| POST | `/api/auth/login` | Iniciar sesión | UserLoginRequestDTO |

#### Ejemplo de Registro:
```json
{
  "name": "Juan Pérez",
  "email": "juan@email.com",
  "password": "contraseña123",
  "dni": "12345678A",
  "role": "CLIENT"
}
```

#### Ejemplo de Login:
```json
{
  "email": "juan@email.com",
  "password": "contraseña123"
}
```

### Productos (`/api/v1/products`)

| Método | Endpoint | Descripción | Autorización |
|--------|----------|-------------|--------------|
| GET | `/api/v1/products` | Listar productos | No requerida |
| GET | `/api/v1/products/{id}` | Obtener producto | No requerida |
| POST | `/api/v1/products` | Crear producto | JWT Token |
| PATCH | `/api/v1/products/{id}` | Actualizar producto | JWT Token |
| DELETE | `/api/v1/products/{id}` | Eliminar producto | JWT Token |

#### Ejemplo de Producto:
```json
{
  "name": "Laptop Gaming",
  "description": "Laptop de alto rendimiento para gaming",
  "price": 1299.99,
  "stock": 10
}
```

### Usuarios (`/api/v1/users`)

| Método | Endpoint | Descripción | Autorización |
|--------|----------|-------------|--------------|
| GET | `/api/v1/users` | Listar usuarios | ADMIN |
| GET | `/api/v1/users/{id}` | Obtener usuario | JWT Token |
| PATCH | `/api/v1/users/{id}` | Actualizar usuario | JWT Token |
| DELETE | `/api/v1/users/{id}` | Eliminar usuario | JWT Token |

## 🔐 Autenticación

La aplicación utiliza JWT (JSON Web Tokens) para la autenticación:

- **Access Token**: Válido por 24 horas
- **Refresh Token**: Válido por 7 días (funcionalidad pendiente)

### Uso de Tokens

Incluir el token en el header de autorización:
```
Authorization: Bearer <tu-jwt-token>
```

## 💾 Datos de Prueba

Al iniciar la aplicación por primera vez, se cargan automáticamente datos de prueba:

### Usuarios Predeterminados:
- **Admin**: admin@example.com / adminpass
- **Cliente 1**: client1@example.com / clientpass1
- **Cliente 2**: client2@example.com / clientpass2

### Productos de Ejemplo:
- Laptop Pro ($1,200.00)
- Wireless Mouse ($25.99)
- Mechanical Keyboard ($80.50)
- USB-C Hub ($35.00)

## 🚀 Desarrollo

### Estructura del Proyecto

```
src/main/java/com/ecommerce/ecommerce/
├── auth/                   # Autenticación y JWT
├── products/              # Gestión de productos
├── orders/                # Gestión de pedidos
├── users/                 # Gestión de usuarios
├── security/              # Configuración de seguridad
├── seed/                  # Datos de prueba
└── EcommerceApplication.java
```

### Tecnologías Utilizadas

- **Spring Boot 3.5.4**
- **Spring Security** - Seguridad y autenticación
- **Spring Data JPA** - Acceso a datos
- **PostgreSQL** - Base de datos
- **JWT** - Autenticación sin estado
- **MapStruct** - Mapeo de objetos
- **Lombok** - Reducción de código boilerplate
- **Spring Validation** - Validación de datos
- **Elasticsearch** - Búsqueda (configurado pero no implementado)

### Comandos Útiles

```bash
# Compilar sin ejecutar tests
mvn clean compile -DskipTests

# Ejecutar solo tests
mvn test

# Crear JAR ejecutable
mvn clean package

# Ejecutar con perfil específico
mvn spring-boot:run -Dspring.profiles.active=prod

# Limpiar base de datos (eliminar volumen Docker)
docker-compose down -v
```

## 📝 Notas de Seguridad

- ⚠️ **Importante**: Cambiar el `JWT_SECRET_KEY` en producción
- Las contraseñas deben ser codificadas (implementar BCrypt)
- Usar HTTPS en producción
- Configurar CORS apropiadamente para el frontend
- Implementar rate limiting para APIs públicas

## 🐛 Resolución de Problemas

### Error de Conexión a Base de Datos
1. Verificar que PostgreSQL esté ejecutándose
2. Comprobar las variables de entorno en `.env`
3. Verificar que la base de datos existe

### Error de JWT
1. Verificar que `JWT_SECRET_KEY` esté configurado
2. Comprobar que el token no haya expirado
3. Verificar formato del header Authorization

### Problemas de Compilación
1. Verificar versión de Java (24+)
2. Ejecutar `mvn clean compile`
3. Verificar dependencias en `pom.xml`

## 🤝 Contribución

1. Fork el proyecto
2. Crear rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

---
