# 🐕 DogsApp

Una aplicación Android moderna que muestra una hermosa galería de perros con información detallada de cada uno.

## 📱 Descripción

DogsApp es una aplicación nativa para Android desarrollada con las últimas tecnologías de Android. Permite a los usuarios explorar una lista de adorables perros, ver sus detalles como nombre, descripción, edad e imágenes.

## ✨ Características

- 📋 **Lista de Perros**: Visualiza una colección de perros en una interfaz intuitiva
- 🔍 **Detalles del Perro**: Ve información completa de cada perro (nombre, descripción, edad, imagen)
- 🎨 **UI Moderna**: Interfaz construida con Jetpack Compose y Material Design 3
- 🌐 **Datos en Tiempo Real**: Obtiene información de perros desde una API REST
- 📱 **Navegación Fluida**: Transiciones suaves entre pantallas
- 🌙 **Tema Dinámico**: Soporte para temas claro/oscuro

## 🏗️ Arquitectura

Este proyecto implementa **Clean Architecture** con una estructura modular:

```
📦 DogsApp
├── 🎯 app/          # Módulo de presentación (UI, ViewModels, Navigation)
├── 💾 data/         # Módulo de datos (API, Repository, Mappers)
├── 🏛️ domain/       # Módulo de dominio (Models, Use Cases, Repository Interface)
└── ⚙️ buildSrc/     # Configuración de build centralizada
```

### 🎯 Módulo App
- **UI Components**: Pantallas construidas con Jetpack Compose
- **ViewModels**: Manejo de estado con arquitectura MVVM
- **Navigation**: Sistema de navegación con Navigation Compose
- **Themes**: Implementación de Material Design 3

### 💾 Módulo Data
- **API Service**: Cliente Retrofit para consumo de API
- **Repository**: Implementación del patrón Repository
- **Mappers**: Transformación entre DTOs y modelos de dominio
- **Network Module**: Configuración de dependencias de red

### 🏛️ Módulo Domain
- **Models**: Entidades de dominio puras
- **Use Cases**: Lógica de negocio encapsulada
- **Repository Interface**: Contratos para acceso a datos

## 🛠️ Tecnologías Utilizadas

### 🚀 Core
- ![Kotlin](https://img.shields.io/badge/Kotlin-1.9.21-7F52FF?logo=kotlin&logoColor=white)
- ![Android](https://img.shields.io/badge/Android-API%2024+-3DDC84?logo=android&logoColor=white)
- ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.7.7-4285F4?logo=jetpackcompose&logoColor=white)

### 🎨 UI/UX
- **Material Design 3**: Sistema de diseño moderno
- **Coil**: Carga eficiente de imágenes
- **Navigation Compose**: Navegación declarativa

### 🌐 Networking
- **Retrofit**: Cliente HTTP type-safe
- **OkHttp**: Interceptors y logging
- **Gson**: Serialización JSON

### 🏗️ Architecture & DI
- **Hilt**: Inyección de dependencias
- **ViewModel**: Gestión de estado UI
- **Coroutines**: Programación asíncrona
- **StateFlow**: Flujos de datos reactivos

### 🧪 Testing
- **JUnit**: Testing unitario
- **Mockk**: Mocking framework
- **Espresso**: Testing de UI
- **MockWebServer**: Testing de red

## 📋 Requisitos

- **Android Studio**: Narwhal
- **JDK**: 21
- **Android SDK**: Mínimo API 24, Target API 33
- **Gradle**: 8.7.3

## 🚀 Instalación y Configuración

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/DogsApp.git
cd DogsApp
```

### 2️⃣ Abrir en Android Studio
1. Abre Android Studio
2. Selecciona "Open an existing project"
3. Navega al directorio del proyecto y ábrelo

### 3️⃣ Sincronizar Dependencias
```bash
./gradlew build
```

### 4️⃣ Ejecutar la Aplicación
1. Conecta un dispositivo Android o inicia un emulador
2. Haz clic en el botón "Run" en Android Studio
3. O ejecuta desde terminal:
```bash
./gradlew installDebug
```

## 🗂️ Estructura del Proyecto

```
app/
├── src/main/java/com/example/dogsapp/
│   ├── MainActivity.kt                    # Actividad principal
│   ├── DogViewModel.kt                   # ViewModel principal
│   ├── navigation/                       # Configuración de navegación
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── dog_list/                # Pantalla de lista de perros
│   │   │   └── dog_detail/              # Pantalla de detalle del perro
│   │   ├── state/                       # Manejo de estados UI
│   │   └── theme/                       # Temas y estilos
│   └── utils/                           # Utilidades

data/
├── src/main/java/com/example/data/
│   ├── ApiService.kt                    # Interfaz de API
│   ├── di/NetworkModule.kt              # Módulo de red
│   ├── mapper/DogMapper.kt              # Mapeo de datos
│   ├── repository/DogRepositoryImpl.kt   # Implementación del repositorio
│   └── response/DogResponse.kt          # DTOs de respuesta

domain/
├── src/main/java/com/example/domain/
│   ├── models/Dog.kt                    # Modelo de dominio
│   ├── repository/DogRepository.kt      # Interfaz del repositorio
│   └── usecase/GetDogsUseCase.kt        # Caso de uso principal
```

## 🎮 Uso de la Aplicación

1. **🏠 Pantalla Principal**: Al abrir la app, verás una lista de perros cargados desde la API
2. **🔄 Recarga**: Desliza hacia abajo para recargar la lista
3. **👆 Selección**: Toca cualquier perro para ver sus detalles
4. **📱 Detalle**: En la pantalla de detalle, podrás ver toda la información del perro
5. **⬅️ Navegación**: Usa el botón de retroceso para volver a la lista

## 🔧 Configuración de Build

El proyecto utiliza **buildSrc** para centralizar la configuración:

- **AndroidBuildConfig.kt**: Versiones y configuraciones centralizadas
- **libs.versions.toml**: Catálogo de versiones de dependencias

### Variantes de Build
- **Debug**: Configuración de desarrollo con logs habilitados
- **Release**: Configuración optimizada para producción

## 📚 Documentación Adicional

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Hilt Dependency Injection](https://dagger.dev/hilt/)
- [Material Design 3](https://m3.material.io/)

## 👨‍💻 Autor

**MARLON ARTEAGA MORALES**
- GitHub: [marlonpya](https://github.com/marlonpya)
- Email: marlon.arteaga.m@hotmail.com

---

⭐ ¡Dale una estrella al proyecto si te resulta útil!

🐕 *"Los perros no son toda nuestra vida, pero hacen que nuestras vidas sean completas"* - Roger Caras 