# Sử dụng image Ubuntu hoặc một image có thể cài đặt unzip
FROM ubuntu:22.04

# Cài đặt unzip và Java
RUN apt-get update && \
    apt-get install -y --no-install-recommends unzip openjdk-21-jdk && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR vào container
COPY target/demo-service-deploy-package.zip .

# Giải nén file zip
RUN unzip demo-service-deploy-package.zip

# Khai báo port mà ứng dụng sẽ sử dụng
EXPOSE 18081

# Chạy ứng dụng Java
#java -server -Dspring.config.location=config/application.yml -cp "ebanking-api-limit-0.0.1.jar:lib/*:" vn.hdbank.ebanking.api.limit.EbankingApiLimitApplication
CMD ["java", "-cp", "/app/demo-service/demo-service-0.0.1.jar:/app/demo-service/lib/*", "-Dspring.config.location=/app/demo-service/config/application.yml", "com.hdbank.demo.DemoApplication"]