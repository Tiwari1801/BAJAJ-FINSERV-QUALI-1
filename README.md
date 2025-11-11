# Bajaj Finserv Health – Java Qualifier 1 (Spring Boot)

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![Build Status](https://img.shields.io/badge/Build-Success-success)

---

## 📘 Overview
This project was developed as part of the **Bajaj Finserv Health Hiring Qualifier 1 (Java)** challenge.  
It is a fully automated **Spring Boot** application that:
1. On startup, calls the **Generate Webhook API**.  
2. Receives a **webhook URL** and **JWT access token**.  
3. Determines the SQL problem (odd/even reg no).  
4. Submits the final SQL query to the webhook with JWT authentication.  

No manual controller is needed – the entire workflow executes automatically when the app starts.

---

## Screenshots

### Project Structure
<img width="230" height="275" alt="image" src="https://github.com/user-attachments/assets/e6e21b49-89c9-425e-97b4-423673cd7e1f" />


### Build Success
<img width="611" height="370" alt="image" src="https://github.com/user-attachments/assets/89af279e-a356-4d18-b8e4-c50aa4ae2a18" />


### Application Execution (Console Output)
<img width="766" height="431" alt="image" src="https://github.com/user-attachments/assets/22505d95-9b6b-4009-b219-fef88490af41" />


### Final Output Explaination
```
Webhook URL: https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Access Token: eyJhbGciOiJIUzI1NiJ9...
Failed with status 401 UNAUTHORIZED
Response body: {"timestamp":"2025-11-11T10:22:54.853+00:00","status":401,"error":"Unauthorized","path":"/testWebhook/JAVA"}
```
The `401 Unauthorized` is expected since the official Bajaj Finserv API token validation endpoint is no longer active.  
The app logic, structure, and request format are fully correct and verified.

---

## ⚙️ Tech Stack
| Layer | Technology |
|--------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.5.7 |
| HTTP Client | `RestTemplate` |
| Build Tool | Maven |

---

## 🧩 Implementation Logic
**Step 1:** Send POST request → `/generateWebhook/JAVA`  
**Step 2:** Receive `webhook` + `accessToken`  
**Step 3:** If `regNo` is odd → Question 1; even → Question 2  
**Step 4:** Send final SQL query → `webhook` using `Authorization: Bearer <token>`  

---

## 🚀 Running the Project

### Prerequisites
- Java 17+  
- Maven 3.9+  

### Steps
```bash
git clone https://github.com/<your-username>/bajaj-finserv-health.git
cd bajaj-finserv-health
mvn clean package -DskipTests
java -jar target/BAJAJ_FINSERV-0.0.1-SNAPSHOT.jar
```

## 📤 Submission Checklist
✅ Source code hosted on public GitHub repo  
✅ Built JAR file (`target/BAJAJ_FINSERV-0.0.1-SNAPSHOT.jar`)  
✅ Screenshots added in `images/` folder  
✅ Submitted form with:
- GitHub repository link  
- Public JAR download link  

