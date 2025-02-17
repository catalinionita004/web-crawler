FROM maven:3.9.6-eclipse-temurin-21 AS build

# Crearea unui director nou pentru copierea fișierelor sursă și a pom.xml
RUN mkdir -p /app/src
WORKDIR /app

# Copierea surselor și a fișierului pom.xml
COPY src /app/src/
COPY pom.xml /app/

# Rularea comenzii Maven pentru construirea proiectului
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

ARG GCP_PROJECT_ID
ENV GCP_PROJECT_ID=$GCP_PROJECT_ID

# Copierea artefactului construit din etapa anterioară în imaginea finală
COPY --from=build /app/target/web-crawler-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

#install wget && zip
RUN apt-get update && apt-get install -y gnupg gnupg2 gnupg1 wget unzip xvfb

# Descarcarea și instalarea Chrome 126
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Descarcarea și instalarea ChromeDriver 126
RUN wget https://storage.googleapis.com/chrome-for-testing-public/126.0.6478.126/linux64/chromedriver-linux64.zip && \
    unzip chromedriver-linux64.zip && rm chromedriver-linux64.zip && \
    mv chromedriver-linux64/chromedriver /usr/bin/chromedriver && \
    chmod +x /usr/bin/chromedriver

ADD personal-project-428121-3c114e11c925.json /root/home/personal-project-428121-3c114e11c925.json
ENV GOOGLE_APPLICATION_CREDENTIALS=/root/home/personal-project-428121-3c114e11c925.json

