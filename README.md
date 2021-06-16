# Projet SafetyNet
***

Le but de cet application est d'envoyer des informations aux systèmes de services d'urgence.
Grâce à une API REST traitant les données des habitants et des casernes.

#### Lien Github : https://github.com/OrionBS/Projet5_SafetyNet

## Pour commencer

### Pré-requis

- IntelliJ (Environnement de développement)
- Java 11
- MySQL
- Maven 3.6.3

### Installation

```
$ cd /"dossier de téléchargement"/
$ git clone https://github.com/OrionBS/Projet5_SafetyNet
$ cd /Projet5_SafetyNet
$ mvn clean verify
```
### Paramétrage MySQL

Dans la console, démarrez MySQL
```
$ mysql
```
Puis créez la base de donnée et l'utilisateur avec ses privilèges.
```
$ create database safetynet;
$ create user 'safetynetUser'@'localhost' identified by 'safetynetPass';
$ grant all privileges on safetynet.* to 'safetynetUser'@'localhost';
$ flush privileges;
```


## Démarrage

```
$ java -jar SafetyNetApp.jar
```

## Fabriqué avec

* [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows) - Environnement de développement
* [Java SE JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - JDK Java
* [Maven 3.6.3](http://maven.apache.org/download.cgi) - Maven

### Rapport SureFire

```
$ mvn surefire-report:report
```

## Versions

**Dernière version stable :** 1.0

## Auteurs
Listez le(s) auteur(s) du projet ici !
* **Orion Beauny** _alias_ [@OrionBS](https://github.com/OrionBS)
* Tous les [contributeurs](https://github.com/OrionBS/Projet5_SafetyNet/contributors)

## License

Ce projet est conçu pour répondre en tant que travail d'étudiant lors d'une soutenance.


