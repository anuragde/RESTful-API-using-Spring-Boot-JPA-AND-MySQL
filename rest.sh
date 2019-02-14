mvn clean install spring-boot:repackage
sudo docker build -t springboot_docker .
sudo docker-compose up
