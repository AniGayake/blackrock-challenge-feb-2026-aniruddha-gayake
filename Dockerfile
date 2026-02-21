# docker build -t blk-hacking-ind-aniruddha-gayake .

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

COPY target/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

# Expose port 5477 as required by challenge spec
EXPOSE 5477

ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", "app.jar", \
            "--server.port=5477"]


 # docker build -t blk-hacking-ind-aniruddha-gayake .
# FROM eclipse-temurin:17-jdk
# WORKDIR /app
#
# COPY target/*.jar app.jar
#
# EXPOSE 5477
#
# ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=5477"]