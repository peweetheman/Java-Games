Patrick Phillips
id: 103

Project Partner: Brandon Toops


Run the project from command line, compile the Main.java class and then run with arguments listed as in project description. So for example:
nys.txt --show --directions i10 i22
nys.txt --show
The program will print that path and distance if directions are asked for. 

We split the program into two parts which were the GUI and the dijkstra algorithm.
Patrick worked on the GUI and Brandon worked on the dijkstra algorithm. For the larger map, 
 
The dijkstra's algorithm portion of the project uses the Princeton EdgeWeightedGraph class and an inner Vertex class which contains a name, longitude, latitude, haversin distance, the previous vertex, and a boolean to check if the vertex has been visited. In this algorithm, the vertices were not all added into the priority queue in the beginning. Instead, the initial vertex was added and then once the vertex was encountered, the adjacent vertices were added to the priority queue. This allowed the algorithm to perform quickly on the larger nys and monroe maps. 

The GUI portion of the project uses an inner class that is a jframe
that then adds a jpanel with a paint component inside of it. The paint component
draws lines between each vertex and then draws a pink line along the vertices
that dikjstra's algorithm lists in the path. Variables used for this portion are 
the x and y coordinates of each vertex and an iterator to iterate over all the edges.

Runtime Analysis:
The runtime of the paint method is O(|E| + |V|) -- for the loop drawing lines between vertices. So overall its O(|E|).
The runtime of our Dikjstra’s algorithm implementation is |E|log|V|. Every edge is checked and the relax method runs with each in log V time to choose the vertex with the smallest distance.
Thus the overall runtime is O(|E|log|V|)!

