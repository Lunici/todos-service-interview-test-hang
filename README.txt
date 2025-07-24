1. Instalar la base de datos Postgresql con username=admin, password=admin, database=ohmycode, puerto 5432.
Abajo he preparado un comando de docker para crear la BD directamente.
docker run --name db-ohmycode-hang-xing -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=ohmycode -p 5432:5432 -d postgres:17.5

2. Entrar a la carpeta del project y ejecutar en terminal "mvn spring-boot:run" (o ejecutarlo en intellij idea)

NOTA: en 03-fake-datas.xml he preparado datos falsos para hacer pruebas, por defecto se ejecuta a la primera vez de arranque.
Si quiere desactivarlo, borra este fichero en db.changelog-master.yaml
