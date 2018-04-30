## Barren-Land

Starts with a predefined green rectangle. Marks any number of passed rectangles brown. Returns a sorted list of continuous green patches that remain. 

#### Input

Input is passed to the application via standard in.
Each rectangle is defined by four space-separated numbers inside of quotation marks.
A brace-enclosed comma-separated list of rectangles is passed to standard in. 

#### Output

After the passed rectangles are removed, the areas of each connected patch of green is calculated and returned in ascending order separated by spaces.

### Building locally

1. clone the repo: `git clone https://github.com/brianberzins/barren-land.git` 
1. build and test: `./gradlew clean build`
1. build the docker container: `docker build .`

### Running the app

Couple options:
1. pipe input into the jar file after building `echo {\"0 292 399 307\"} | java -jar build/libs/barren-land.jar`
1. run via docker: `echo {\"0 292 399 307\"} | docker run --interactive brianberzins/barren-land:latest`

### Open ended conversation points

1. optimization of the algorithm (merger of adjacent rectangles)
1. computational complexity (best/worst/average)
1. how easy/hard is this to extend
1. how easy/hard is the algorithm to understand (and is that worth it vs a trivial solution)
