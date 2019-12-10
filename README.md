# Trains and Towns Solution

Application is developed via **Java** programming language for calculation of *distance of the route*, *number of trips*, 
*length of the shortest route*, *number of different routes* 

## Solutions

#### *Distance of the route (problems from 1-5)*

 - Algorithm builds queue of towns when it is visited, adds up its distance to result distance.<br/> 
 Class: **Solutions**, method: **getDistance**
 
 #### **Get all possible routes(problems from 6-7)**
 
  - The method **getNumberOfTrips** implements **Depth-First Search(DFS)** algorithm. From the starting point it 
  recursively search for destination point. All possible routes stored in list of routes there we can filter it 
  by other conditions
  
 #### **Find shortest route(problems 8-9)**
 
  - The method **getShortestDistanceRoute** implements **Djikstra** algorithm. It initialize map that store shortest 
  distance to all possible routes from starting point. Then we can get desired destination value.
  
 #### **Find all possible routes with a distance limit(problem 10)**
 
  - The method **getNumberOfTripsLessThan** like method **getNumberOfTrips** implements **Depth-First Search(DFS)** 
  algorithm. After getting all possible routes, we can filter it by distance limit

## Run

If you use:

 - Gradle: `$ ./gradlew run`
 - Docker: `$ docker-compose run app`
 
## Example

``` bash
Creating network "trains_and_towns_default" with the default driver
Please provide your graph (ex: AB5,DC4) :
AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
Output #1: 9
Output #2: 5
Output #3: 13
Output #4: 22
Output #5: NO SUCH ROUTE
Output #6: 2
Output #7: 2
Output #8: 9
Output #9: 9
Output 10: 7
If you want to restart program please type: restart

```