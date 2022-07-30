git pull ||exit
mvn clean install ||exit
find freelance-example* -type f -name *dep*jar -exec cp -v  {} ../  \;
