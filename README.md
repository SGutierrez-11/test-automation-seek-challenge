# 🚀 Red Social - QA Automation Framework (Seek Challenge)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.28.0-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7.x-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Testing-FF7F00?style=for-the-badge&logo=testng&logoColor=white)
![Allure](https://img.shields.io/badge/Allure_Report-Active-ff69b4?style=for-the-badge)
![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white)

> **Marco de automatización de pruebas End-to-End (E2E) y repositorio de documentación de Calidad (QA) desarrollado para el Reto Técnico de Seek.**

Este proyecto no es solo un conjunto de scripts; es una infraestructura de calidad completa diseñada bajo estándares de la industria, aplicando patrones de diseño de software avanzados, Integración Continua (CI/CD) y una estrategia de pruebas híbrida orientada a la mitigación de riesgos.

---

## 📑 Tabla de Contenidos
1. [Descripción y Estrategia del Proyecto](#1--descripción-y-estrategia-del-proyecto)
2. [Arquitectura y Patrones de Diseño](#2--arquitectura-y-patrones-de-diseño)
3. [Stack Tecnológico](#3--stack-tecnológico)
4. [Estructura del Proyecto](#4--estructura-del-proyecto)
5. [Integración Continua (CI/CD) y Reportes](#5--integración-continua-cicd-y-reportes)
6. [Ejecución Local](#6--ejecución-local)
7. [Documentación Anexa (Artefactos QA)](#7--documentación-anexa-artefactos-qa)
8. [Evidencias de Ejecución (Nube)](#8--evidencias-de-ejecución-nube)

---

## 1. 🎯 Descripción y Estrategia del Proyecto

Para validar los flujos críticos de la Red Social objetivo, se implementó una **Estrategia de Pruebas Híbrida**, dividiendo el alcance según el nivel de riesgo técnico y la estabilidad de la plataforma:

* **Workstream Automatizado (Regresión Core):** Automatización robusta de los flujos de *Gestión de Identidad (Registro y Login)* y *Engagement (Comentarios/Reviews)*. Se ejecutó sobre un entorno estable simulado para demostrar el dominio de frameworks E2E.
* **Workstream Manual (Exploratorio en UI Dinámica):** Los flujos de interacción compleja nativa (Chat Privado, Subida de Imágenes en Facebook) se ejecutaron manualmente. 
    * *Nota Técnica:* Se desarrolló una Prueba de Concepto (PoC) para automatizar Facebook manejando su agresivo A/B Testing y DOM mutante. El código experimental se encuentra versionado en la rama `dev` para proteger la estabilidad del Pipeline en la rama `main`, demostrando madurez en la toma de decisiones arquitectónicas frente a entornos hostiles (Anti-Bot).

---

## 2. 🏗️ Arquitectura y Patrones de Diseño

El framework ha sido construido buscando la máxima mantenibilidad, encapsulación y escalabilidad:

* **Behavior-Driven Development (BDD):** Uso de Cucumber (Gherkin) para garantizar que los casos de prueba sean legibles como documentación viva por cualquier Stakeholder (Negocio, Devs, QA).
* **Diseño Híbrido POM + Screenplay-ish:** Separación estricta de responsabilidades.
    * `Pages`: Contienen exclusivamente los WebElements (Localizadores) y lógica del DOM, utilizando herramientas como `@FindAll` para soportar versiones A/B de la interfaz.
    * `Tasks`: Acciones de alto nivel del usuario, implementando una **Fluent Interface** (`return this;`) para encadenamiento de métodos, lo que reduce drásticamente la verbosidad en los *Steps*.
    * `StepDefinitions`: Orquestadores limpios que vinculan Gherkin con las *Tasks*.
* **Gestión de Estado y Data Dinámica:** * Implementación de **JavaFaker** para inyección de datos sintéticos, asegurando que cada prueba sea independiente.
    * Uso de una clase `TestDataManager` para persistir estados (como el usuario recién registrado) en memoria durante la ejecución del hilo, evitando colisiones de datos y eliminando datos *hardcodeados*.
* **Sincronización Inteligente:** Creación de `SeleniumActions` personalizadas que manejan esperas explícitas (FluentWaits) neutralizando el *Implicit Wait* global para evitar falsos positivos (*flaky tests*).

---

## 3. 🛠️ Stack Tecnológico

| Herramienta / Tecnología | Propósito dentro del Framework |
| :--- | :--- |
| **Java 17** | Lenguaje de programación core (Tipado estático y robustez). |
| **Selenium WebDriver 4.28.0** | Interacción directa con el navegador y el DOM. |
| **Cucumber 7 / Gherkin** | Capa de abstracción de negocio y definición de BDD. |
| **TestNG** | Motor de aserciones y gestión del ciclo de vida de la ejecución. |
| **Maven** | Orquestador de dependencias y construcción del proyecto (`pom.xml`). |
| **Allure Reports** | Generación de reportes de calidad visuales, dinámicos e históricos. |
| **GitHub Actions** | Pipeline CI/CD para ejecución automática en la nube (Servidores Linux). |

---

## 4. 📂 Estructura del Proyecto

```text
📦 test-automation-seek-challenge
 ┣ 📂 docs/                   # 📄 Documentación formal (Plan de Pruebas, RTM, Informe Final)
 ┣ 📂 src/
 ┃ ┣ 📂 main/java/com/seek/challenge
 ┃ ┃ ┣ 📂 config/           # Configuraciones base (DriverManager, ConfigReader)
 ┃ ┃ ┣ 📂 pages/            # Page Object Models (WebElements puros)
 ┃ ┃ ┣ 📂 tasks/            # Lógica de negocio e interacciones fluídas
 ┃ ┃ ┗ 📂 utils/            # Utilidades (DataGenerator, SeleniumActions, TestDataManager)
 ┃ ┗ 📂 test/
 ┃   ┣ 📂 java/com/seek/challenge
 ┃   ┃ ┣ 📂 hooks/          # @Before (Setup) y @After (Teardown/Screenshots)
 ┃   ┃ ┣ 📂 runners/        # Clases TestNG para disparar los features
 ┃   ┃ ┗ 📂 stepdefinitions/# Mapeo de Gherkin a código Java
 ┃   ┗ 📂 resources/
 ┃     ┣ 📂 features/       # Escenarios de prueba escritos en formato Gherkin
 ┃     ┗ ⚙️ config.properties # Variables de entorno (Browser, Headless mode, URLs)
 ┣ 📂 .github/workflows/      # ⚙️ Pipeline YAML de GitHub Actions
 ┣ 📜 pom.xml                 # Dependencias y configuración de Maven/Surefire
 ┗ 📜 README.md               # Este documento

## 5. 🚀 Integración Continua (CI/CD) y Reportes

El framework está completamente integrado con la nube. Se ha configurado un Pipeline en **GitHub Actions** (`.github/workflows/pipeline.yml`) que se dispara automáticamente con cada `push` a la rama `main` o mediante ejecución manual (`workflow_dispatch`).

### Características del Pipeline:
1.  **Infraestructura:** Corre en `ubuntu-latest` aprovisionando JDK 17 y Google Chrome v120+.
2.  **Ejecución Headless & Xvfb:** Ejecuta los tests mediante Maven. Se implementó `xvfb-run mvn clean test` para simular un *display* virtual en Linux, garantizando que el navegador renderice correctamente el DOM a nivel de píxel sin fallos de UI.
3.  **Manejo de Fallos Estratégico:** Se utiliza `continue-on-error: true` en la etapa de ejecución de pruebas. Esto asegura que si un test falla, el pipeline *no* se detiene abruptamente y continúa hacia la generación de reportes.
4.  **Generación de Allure Report & History:** El pipeline descarga el historial previo desde la rama `gh-pages`, compila el nuevo reporte uniendo tendencias históricas, y publica automáticamente el HTML estático resultante.

📊 **Reporte Dinámico en Vivo:** [👉 HAZ CLIC AQUÍ PARA VER EL ÚLTIMO REPORTE DE ALLURE](https://sgutierrez-11.github.io/test-automation-seek-challenge)

---

## 6. 💻 Ejecución Local

Si deseas correr este framework en tu máquina local, sigue estos pasos:

### Pre-requisitos
* Java JDK 17+ instalado y configurado en el `PATH`.
* Apache Maven instalado.
* Navegador Google Chrome actualizado.

### Comandos de Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/](https://github.com/)SGutierrez-11/test-automation-seek-challenge.git
    cd test-automation-seek-challenge
    ```
2.  **Ejecutar todas las pruebas:**
    ```bash
    mvn clean test
    ```
3.  **Generar y visualizar el reporte Allure localmente:**
    *(Requiere tener Allure Commandline instalado localmente o usar el plugin de Maven)*
    ```bash
    mvn allure:serve
    ```

---

## 7. 📁 Documentación Anexa (Artefactos QA)

La excelencia técnica no solo reside en el código, sino en la trazabilidad del proceso de calidad. En el directorio `/docs` de este repositorio, encontrarás la documentación formal exigida para este reto, redactada bajo los más altos estándares de QA:

* 📄 **[Plan Maestro de Pruebas (PDF)](./docs/PLAN MAESTRO DE PRUEBAS DE SOFTWARE (MTP).pdf):** Define la estrategia híbrida, el alcance, los entornos y el catálogo detallado de casos de prueba.
* 📊 **[Matriz de Trazabilidad - RTM (PDF)](./docs/Matriz de Trazabilidad Red Social .pdf):** Dashboard de cobertura cruzando los 5 requerimientos de negocio con los 11 escenarios de prueba (Automáticos y Manuales), garantizando un 100% de Test Coverage.
* 📋 **[Informe Final de Pruebas y Defectos (PDF)](./docs/INFORME FINAL DE EJECUCIÓN DE PRUEBAS Y ANÁLISIS DE CALIDAD.pdf):** Conclusiones funcionales, análisis teórico de rendimiento y seguridad, y el **Reporte de Defectos (Bugs)** con hallazgos arquitectónicos y recomendaciones Senior.

---

## 8. 🎥 Evidencias de Ejecución (Nube)

Dado el enfoque híbrido de la estrategia de pruebas, los flujos dinámicos nativos de la red social (Facebook) se ejecutaron mediante Pruebas Exploratorias Manuales. Todas las evidencias en video han sido centralizadas en la nube para su revisión:

📁 **[Enlace a Carpeta Principal de Evidencias en Google Drive](URL_DE_TU_CARPETA_DRIVE_AQUI)**

**Desglose de Evidencias por Módulo:**
* 🎬 **Módulo de Comentarios (Engagement):** [Ver Video CRUD Comentarios]([URL_DEL_VIDEO_COMENTARIOS_AQUI](https://drive.google.com/file/d/1nmQwvFMMfINy8OUFe3ZkVQnr-2E1D_jq/view?usp=drive_link))
* 🎬 **Módulo de Chat Privado (Messaging):** [Ver Video Chat y Defecto Anular Envío]([URL_DEL_VIDEO_CHAT_AQUI](https://drive.google.com/file/d/1TYbS8ehi5cr-33wTO4uNVavCUImzTANU/view?usp=drive_link))
* 🎬 **Módulo de Autenticación (Login):** [Ver Video Happy Path y Edge Cases]([URL_DEL_VIDEO_LOGIN_AQUI](https://drive.google.com/file/d/1ttORp-eQiFLe80YzOztUAEygUVlrFS8g/view?usp=drive_link))

---
*Desarrollado con ☕ y mentalidad de calidad continua por **Santiago Gutierrez** - QA Automation Engineer.*
