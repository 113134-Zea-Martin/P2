[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ihP449Jy)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=15330429)
<p align="center">
  <img src="./docs/_images/img.png" alt="Copa Americas 2024" style="max-width:500px"/>
</p>

# Exámen - PRODE Copa Americas 2024

La Copa América es el torneo de selecciones nacionales de fútbol organizado por la Confederación Sudamericana de Fútbol
(CONMEBOL). La misma ha reunido a las mejores selecciones de Sudamérica para competir por el prestigioso título de 
campeón continental. Este torneo no solo destaca por su rica historia y tradición, sino también por el talento y la 
pasión que despliegan los equipos y jugadores participantes.

La Copa América 2024 será la 48.ª edición de este torneo, la principal competencia futbolística entre las selecciones 
nacionales de América del Sur y la más antigua del mundo. Por segunda vez el torneo se realizará en Estados Unidos 
después de la edición centenário de 2016. Será disputada del 20 de junio al 14 de julio.

### Formato de competición

El torneo se desarrolla dividido en cuatro etapas: fase de grupos, cuartos de final, semifinales y final.

En la fase de grupos, los 16 equipos participantes se dividen en cuatro grupos de cuatro equipos cada uno. Se juega con 
un sistema de todos contra todos, donde cada equipo juega un partido con todos sus rivales de grupo, por equipo como 
mínimo serán tres partidos.

### Grupos

Los grupos de la Copa América 2024 son los siguientes:

| Grupo A   | Grupo B   | Grupo C        | Grupo D    |
|-----------|-----------|----------------|------------|
| Argentina | Ecuador   | Bolivia        | Brasil     |
| Canada    | Jamaica   | Estados Unidos | Colombia   |
| Chile     | Mexico    | Panamá         | Costa Rica |
| Perú      | Venezuela | Uruguay        | Paraguay   |

### PRODE (Pronósticos Deportivos)

El "Prode" es un juego de pronósticos deportivos muy popular en varios países de América Latina, especialmente en 
Argentina. Su nombre proviene de las palabras "pronósticos deportivos" y consiste en predecir los resultados de una 
serie de partidos, en este caso, los de la Copa América.

Para jugar al Prode, los participantes deben completar un formulario en el que indican el resultado que creen que se 
dará en cada partido (victoria local, empate o victoria visitante) especificando los goles que se darán por cada equipo.
_Por ejemplo, si se pronostica un partido entre Argentina y Chile, el participante en caso de pronosticar un empate
deberá indicar el resultado en goles, por ejemplo 1-1, 0-0, 2-2, etc._

A medida que se desarrollan los partidos, los pronósticos se comparan con los resultados reales, y los jugadores 
acumulan puntos según la precisión de sus predicciones. Al finalizar el torneo o una jornada específica, el participante 
con más puntos es declarado ganador.

# Desafío

En este desafío, deberás implementar un sistema que permita a los jugadores ingresar sus pronósticos para los partidos
de la Copa América 2024 y calcular sus puntajes en base a los resultados reales de los partidos.

## Requerimientos funcionales

1. **Carga de pronósticos**: El sistema debe permitir a los usuarios ingresar sus pronósticos para los partidos de la 
   fase de grupos de la Copa América 2024. Para ello, deberá solicitar al usuario que ingrese el resultado de cada 
   partido indicando los goles que se darán por cada equipo. Los pronósticos deberán ser ingresados antes del inicio de
   cada partido. El sistema deberá validar que los pronósticos ingresados sean válidos y que no se ingresen pronósticos
   para partidos que ya se hayan iniciado y que la cantidad de goles ingresados sea un entero positivo o cero.
2. **Cálculo de puntajes**: El sistema deberá calcular los puntajes de los usuarios en base a los resultados reales de 
   los partidos. Para ello, deberá comparar los pronósticos de los usuarios con los resultados reales y asignar puntos 
   según la precisión de las predicciones. Los puntajes se calcularán cada vez que el administrador cargue o modifique 
   el resultado de un partido. Los puntajes se asignarán de la siguiente manera:
   - 1 punto por acertar el resultado de un partido.
   - 3 puntos adicionales por acertar el resultado exacto de un partido.
3. **Ranking de usuarios**: El sistema deberá mostrar un ranking de usuarios ordenado por la cantidad de puntos 
   acumulados. En caso de empate, el sistema deberá ordenar los jugadores por orden alfabético del apellido primero y 
   nombre después.
4. **Usuario registrado**: El sistema deberá permitir a los usuarios registrarse ingresando su nombre, apellido, email y 
   password. Solo se podrá registrar un usuario por email. El usuario deberá estar registrado para poder ingresar sus 
   pronósticos.
5. **Registro de resultados partidos**: El sistema deberá permitir a un administrador ingresar los resultados reales de 
   los partidos de la fase de grupos de la Copa América 2024. Adicionalmente debe permitir modificar los resultados de
   los resultados de los partidos en caso de ser necesario.

## Requerimientos no funcionales

1. **Spring Boot**: El sistema deberá ser desarrollado utilizando el framework Spring Boot.
2. **Base de datos**: El sistema deberá utilizar una base de datos H2 en memoria para almacenar los datos de los usuarios, 
   pronósticos y resultados de los partidos.
3. **Testing**: El sistema deberá incluir pruebas unitarias y de integración para validar su correcto funcionamiento. 
   Debe darse una cobertura de a los métodos marcados.
   - ```GameServiceImplTest.setGameResultByGameIdTest()```
   - ```GameServiceImplTest.predictTest()```
   - ```ScoreServiceImplTest.calculateScore()```
   - ```UserServiceImplTest.createUserTest()```
   - ```UserServiceImplTest.getUserByEmailTest()```
4. **Contratos de la las API**: El sistema deberá respetar las API REST indicadas para garantizar su correcta 
   integración con el frontend.

> IMPORTANTE:
>  - No modificar los controladores
>  - No se requiere modificar los DTO, Modelos ni Entities.
>  - Solo trabajar sobre los métodos marcados por TODO's

## Modo de evaluación

| Requerimiento                   | Puntaje   |
|---------------------------------|-----------|
| Carga de pronósticos            | 20        |
| Cálculo de puntajes             | 20        |
| Ranking de usuarios             | 10        |
| Usuario registrado              | 10        |
| Registro de resultados partidos | 20        |
| Testing                         | 20        |
| _**TOTAL**_                     | _**100**_ |
