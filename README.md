# Tennis Data Collection System

A legacy project demonstrating data collection systems for tennis. The term "legacy" here refers not to technological obsolescence, but to an organically grown structure that has evolved over time and deviate from the domain.

> **Note**: This example intentionally showcases a "realistic" legacy system structure, rather than focusing on best practices or clean code. The project serves as a reference for how existing systems might be organized.

A multi-module version of this project, including Spring Modulith integration (branch [`integrate-spring-modulith-into-multi-module-project`)](https://github.com/rudolfgrauberger/tennis-data-collection-multi-module-project/tree/integrate-spring-modulith-into-multi-module-project), is available at [tennis-data-collection-multi-module-project](https://github.com/rudolfgrauberger/tennis-data-collection-multi-module-project).

## Overview

This project implements a comprehensive data collection and management system for tennis analytics. The system handles various aspects of sports data processing, from raw data collection to master data management and identifier mapping.

The architecture consists of several specialized modules:
- **Main**: Core application and entry point
- **Masterdata**: Reference data management for players, tournaments, and matches
- **Collection**: Manual data collection
- **FDI**: Data integration and transformation layer
- **IDMapping**: Entity resolution and identifier mapping across different data sources

The system is designed to handle the complexities typically found in sports data environments, including multiple data formats, varying update frequencies, and the need to correlate information across different external data providers.

## Technology Stack

- **Java**: 24
- **Spring Boot**: 3.5
- **Build Tool**: Maven

## Module Structure
```
tennis-date-collection/
├── pom.xml
├── main/
├── masterdata/
├── collection/
├── fdi/
└── idmapping/
```

## Getting Started

The application can be easily started on the local developer environment with the following command:

```bash
./mvnw spring-boot:test-run
```

### API Documentation

The application provides interactive API documentation through Swagger UI. Once the application is running, you can access the REST API endpoints documentation at:

http://localhost:8080/swagger-ui/index.html

This interface allows you to explore and test all available API endpoints directly from your browser.

## Requirements

- Java 24
