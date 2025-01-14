
# Spring Boot API - Docker and Kubernetes Deployment

This project is a Spring Boot application that demonstrates building and running a REST API using Docker and deploying it on Kubernetes. The application is designed to interact with a database and provides a simple API interface.

---

## Features

- **Spring Boot**: A Java-based framework for building production-grade web applications.
- **Docker**: Containerize the application for consistent deployment across environments.
- **Kubernetes**: Deploy and manage the application at scale.
- **MySQL**: Integrated database connection for data persistence.

---

## Prerequisites

1. **Docker**: Installed and configured ([Install Docker](https://docs.docker.com/get-docker/)).
2. **Kubernetes**: Installed (e.g., `kubectl`, Minikube, or Kind).
3. **Java 17**: Required to build and run the application locally.

---

## Project Structure

- **Dockerfile**: Multi-stage Docker build configuration.
- **pom.xml**: Maven project configuration.
- **deployment.yaml**: Kubernetes deployment definition for managing pods.
- **service.yaml**: Kubernetes service definition for exposing the application.

---

## Building and Running the Application

### **1. Build the Docker Image**

Run the following command to build the Docker image:

```bash
docker build -t spring-boot-api .
```

This command will:
1. Use Maven to build the Spring Boot JAR in the build stage.
2. Use a lightweight OpenJDK base image to run the application.

---

### **2. Run the Docker Container**

Run the container using:

```bash
docker run -p 8080:8080 spring-boot-api
```

- The application will be available on `http://localhost:8080`.
- Logs will confirm successful startup.

---

## Kubernetes Deployment

### **1. Apply the Deployment**

Deploy the application to Kubernetes using:

```bash
kubectl apply -f deployment.yaml
```

This will:
- Create a Deployment resource with 2 replicas.
- Deploy the `spring-boot-k8s` container.

---

### **2. Expose the Service**

Expose the application using the service file:

```bash
kubectl apply -f service.yaml
```

This will:
- Expose the application on a `NodePort`.
- Map port `8080` for external access.

---

### **3. Access the Application**

1. Retrieve the NodePort:
   ```bash
   kubectl get services
   ```

   Example output:
   ```
   NAME                TYPE       CLUSTER-IP     EXTERNAL-IP   PORT(S)          AGE
   springboot-k8s-svc  NodePort   10.100.200.50  <none>        8080:32000/TCP   5m
   ```

2. Access the application:
   ```
   http://<node-ip>:<node-port>
   ```
   Replace `<node-ip>` with the cluster node IP and `<node-port>` with the assigned NodePort (e.g., `32000`).

---

## Cleanup

To delete the deployment and service from Kubernetes:

```bash
kubectl delete -f deployment.yaml
kubectl delete -f service.yaml
```

To stop and remove the Docker container:

```bash
docker stop $(docker ps -q --filter "ancestor=spring-boot-api")
docker rm $(docker ps -aq --filter "ancestor=spring-boot-api")
```

---

## Notes

- Ensure that the MySQL database is accessible and running.
- Update the `datasource.url` in the `application.yml` to match your database settings.
- Customize the `deployment.yaml` and `service.yaml` as needed for production environments.

---

Feel free to reach out if you have any questions!
