# Full SDET Automation Platform

##  Overview
End-to-end automation framework combining UI, API, and BDD testing.

## 🧰 Tech Stack
- Java 21
- Selenium
- REST Assured
- TestNG
- Cucumber
- Maven
- Docker
- Jenkins
- OWASP Dependency Check

## 🔧 Features
- UI automation (Page Object Model)
- API testing (REST Assured)
- BDD scenarios (Cucumber)
- CI/CD ready
- Security scanning (OWASP)

## ▶️ Run Tests
```bash
mvn clean install
```
## Run Tagged Test
```bash
mvn test -Dgroups=smoke
mvn test -Dgroups=regression
```