
# ABOUT PROJECT
#### [ GestionAppel ] Développement d'un site web en Java : Application de gestion de l'appel en ligne (Inscription, Connexion, ) <br><span style="font-size:15px">*( 2021.02.07 ~ 2021.02.11 )*</span>

## 1. Membres de l'équipe

|*Members*|*Contact*|
|:---:|---|
|**Zijing ZHOU**|[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white)](https://github.com/StevenZZJ) [![Gmail Badge](https://img.shields.io/badge/-steven.zhouzijing@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:steven.zhouzijing@gmail.com)](mailto:steven.zhouzijing@gmail.com)|
|**Joohyun ANN**|[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white)](http://github.com/catwithhumanface) [![Gmail Badge](https://img.shields.io/badge/-annjh11@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:annjh11@gmail.com)](mailto:annjh11@gmail.com)|
|**Tong LIU**|[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white)](https://github.com/Gabrielle07) [![Gmail Badge](https://img.shields.io/badge/-tongliu024@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:tongliu024@gmail.com)](mailto:tongliu024@gmail.com)|
|**Fares MEGARI**|[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white)](https://github.com/faresmegari) [![Gmail Badge](https://img.shields.io/badge/-faares.mega@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:faares.mega@gmail.com)](mailto:faares.mega@gmail.com)|

## 2. Installation du projet
1. Copier URL Github 
2. Import Projet
3. Connexion DB to MySQL
   1. Adapation de hibernate.cfg.xml
      1. hibernate.connection.url
      2. hibernate.connection.username
      3. hibernate.connection.password
      4. hibernate.hbm2ddl.auto (create)
   2. Exécution HQL (GenererBD.java)
   3. Changement de hibernate.cfg.xml - hibernate.hbmdd1.auto (validate)
   4. Changement de chemin d'accès absolu (JustificatifConstant.java)
   5. Configuration de Tomcat

## 3. Présentation du projet
<br>
&nbsp;L’UFR d’informatique de l’UT1 Capitole souhaite se doter d’un outil de gestion de l’appel en ligne.<br><br>

Pour cela, l’UFR souhaite disposer rapidement d’un PoC (Proof of Concept).<br><br>

Un recueil des besoins a été mené durant le mois de décembre auprès des deux types d’acteurs impliqués dans le projet :<br>
Les étudiants<br>
Les enseignants<br><br>

Le recueil des besoins a permis d’élaborer une liste de User stories, point de départ du projet. Il s’agit du backlog de démarrage, il sera enrichi.<br><br>

**User Stories**<br>
![US](img/us.png)

## 4. Structure (MVC pattern)
![MVC](img/mvc.png)

## 5. Technologie

### 5.1 APIs

**Développement**

- JSTL

- javax.servlet

- commons-fileupload

- javax.mail

- org.apache.pdfbox

**Test**

- com.masterxml.jaskson.dataformat

- com.github.servanter

- Junit


